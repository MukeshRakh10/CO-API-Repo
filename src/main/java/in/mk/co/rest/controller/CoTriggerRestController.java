package in.mk.co.rest.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import in.mk.co.dto.CoTriggerDTO;
import in.mk.co.rest.service.ICoTriggerService;
import in.mk.co.yml.read.AppProperties;

@RestController
public class CoTriggerRestController {
	
	@Autowired
	private ICoTriggerService coServiceImpl;

	@Autowired
	private AppProperties appProperties;
	
	@Value("${app.messages.greetMsg}")
	private String greetMsg;
	
	@GetMapping("/yml/read")
	public String readYmlProperties( ) {
		Map<String,String> messages = appProperties.getMessages();
		String msg = messages.get("welcomeMsg");
		return msg + "    "+greetMsg;
	}
	
	@GetMapping("/process")
	public  CoTriggerDTO processTrigger() throws Exception {
		return coServiceImpl.processTriggers();
	}
}
