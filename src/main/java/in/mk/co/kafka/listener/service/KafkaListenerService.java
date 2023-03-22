package in.mk.co.kafka.listener.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service("kafkaListenerService")
public class KafkaListenerService {

	@KafkaListener(topics = "his-dc-topic",groupId = "TEST_CO")
	public String listener(String msg) {
		System.out.println("CO ::: MSG Receivd From Kafka TOPICS :  " + msg);
		return msg;

	}
}
