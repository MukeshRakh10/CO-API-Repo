package in.mk.co.rest.service.impl;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import in.mk.co.dto.CoTriggerDTO;
import in.mk.co.entity.CitizenApp;
import in.mk.co.entity.CoTrigger;
import in.mk.co.entity.DcCase;
import in.mk.co.entity.EdEligDetails;
import in.mk.co.repository.CitizenAppRepository;
import in.mk.co.repository.CoTriRepo;
import in.mk.co.repository.DcCaseRepository;
import in.mk.co.repository.EdEligRepository;
import in.mk.co.rest.service.ICoTriggerService;

@Service
public class CoTriggerServiceImpl implements ICoTriggerService {

	@Autowired
	private CoTriRepo coTriggerRepo;

	@Autowired
	private EdEligRepository eligRespository;

	@Autowired
	private DcCaseRepository dcCaseRepository;

	@Autowired
	private CitizenAppRepository citizenAppRepo;

//    @Autowired
//    private AmazonS3 amazonS3Client;
	@Autowired
	private AmazonS3Client awsS3Client;

	@Override
	public CoTriggerDTO processTriggers() throws Exception {
		final Long failed = 0l;
		final Long success = 0l;

		// Multithreading...
		ExecutorService service = Executors.newFixedThreadPool(10);

		CoTriggerDTO cotrgDto = new CoTriggerDTO();

		List<CoTrigger> pendingCotrg = coTriggerRepo.findByTrgStatus("pending");
		System.out.println("Totla Records  is " + pendingCotrg.size());

		for (CoTrigger coTrigger : pendingCotrg) {
			service.submit(new Callable<Object>() {
				public Object call() throws Exception {
					processTrigger(cotrgDto, coTrigger);
					return null;
				};
			});
		}

		//Single thread
//		for (CoTrigger coTrigger : pendingCotrg) {
//			processTrigger(cotrgDto, coTrigger);
//		}

		cotrgDto.setTotalTriggers(Long.valueOf(pendingCotrg.size()));
		cotrgDto.setSuccessTrigger(success);
		cotrgDto.setFailedTrigger(failed);

		return cotrgDto;
	}

	private CitizenApp processTrigger(CoTriggerDTO cotrgDto, CoTrigger coTrigger) throws Exception {
		CitizenApp citizenApp = null;
		Optional<EdEligDetails> elig = eligRespository.findById(coTrigger.getCaseId());
		Optional<DcCase> findById = dcCaseRepository.findById(coTrigger.getCaseId());

		if (findById.isPresent()) {
			DcCase dcCaseEntity = findById.get();
			Long appId = dcCaseEntity.getAppId();

			Optional<CitizenApp> appEntity = citizenAppRepo.findById(appId);

			if (appEntity.isPresent()) {
				citizenApp = appEntity.get();
			}

		}
		generateAndSendPDF(elig, citizenApp);

		return citizenApp;

	}

	private void generateAndSendPDF(Optional<EdEligDetails> elig, CitizenApp citizenApp) throws Exception {

		Document document = new Document(PageSize.A4);

		EdEligDetails ed = elig.get();
		File file = new File(ed.getCaseId() + ".pdf");

		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(file);

		} catch (Exception e) {
			e.printStackTrace();
		}
		PdfWriter.getInstance(document, fos);
		document.open();
		Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		font.setSize(18);
		font.setColor(Color.BLUE);

		Paragraph p = new Paragraph("Eligibility Report ", font);
		p.setAlignment(Paragraph.ALIGN_CENTER);
		document.add(p);

		PdfPTable table = new PdfPTable(6);
		table.setWidthPercentage(100f);
		table.setWidths(new float[] { 1.5f, 3.5f, 3.0f, 3.0f, 1.5f, 1.5f }); // width defined for each columns
		table.setSpacingBefore(10);

		writeTableHeader(table);
		writeTableData(table, elig);

		document.add(table);
		document.close();
		// send Email
		// sendEmail("", file);

		updateTrigger(ed.getCaseId(), file);
		file.delete();

	}

	private void updateTrigger(Long caseId, File f) throws Exception {
		CoTrigger coEntity = coTriggerRepo.findByCaseId(caseId);
		byte[] arr = new byte[(int) f.length()];
		FileInputStream fis = new FileInputStream(f);
		fis.read(arr);
		coEntity.setPdf(arr);
		coEntity.setTrgStatus("Completed");
		coTriggerRepo.save(coEntity);

		ObjectMetadata metadata = new ObjectMetadata();
//        metadata.setContentLength(1175);
		awsS3Client.putObject("copdfbucket", "pdf_" + caseId, fis, metadata);
		System.out.println("File Upload Successfully !!!");
		fis.close();

	}

	private void writeTableHeader(PdfPTable table) {

		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(Color.BLUE);
		cell.setPadding(5);

		Font font = FontFactory.getFont(FontFactory.HELVETICA);
		font.setColor(Color.WHITE);

		cell.setPhrase(new Phrase("Id", font));

		table.addCell(cell);

		cell.setPhrase(new Phrase("Citizen Name", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("SSN", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Gender", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Plan Name", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Plan Status", font));
		table.addCell(cell);

	}

	private void writeTableData(PdfPTable table, Optional<EdEligDetails> e) {
		EdEligDetails user = e.get();

		table.addCell(String.valueOf(user.getCaseId().toString()));
		table.addCell(user.getHolderName());
//			table.addCell(String.valueOf(null != user.getHolderSsn() ? user.getHolderSsn().toString() : "TEST SSN"));
		table.addCell("MALE");
		table.addCell(user.getPlanName());
		table.addCell(user.getPlanStatus());
	}

}
