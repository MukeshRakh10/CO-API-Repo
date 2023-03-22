package in.mk.co.kafka.listener.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.kafka.support.serializer.ToStringSerializer;

@Configuration
@EnableKafka
public class KafkaConsumerConfig {
	
	  @Bean
	  public Map<String, Object> consumerConfigs() {
			Map<String, Object> props = new HashMap<String,Object>();
			props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
			props.put(ConsumerConfig.GROUP_ID_CONFIG, "TEST_CO");
	 		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
			props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
			return props;
	  }

	  @Bean
	  public ConsumerFactory<String, Object> consumerFactory() {
	    return new DefaultKafkaConsumerFactory<>(consumerConfigs());
	  }

	  @Bean
	  public ConcurrentKafkaListenerContainerFactory<String, Object> kafkaListenerContainerFactory() {
	    ConcurrentKafkaListenerContainerFactory<String, Object> factory =
	      new ConcurrentKafkaListenerContainerFactory<>();
	    factory.setConsumerFactory(consumerFactory());
	    return factory;
	  }
	

}
