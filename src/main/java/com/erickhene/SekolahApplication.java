package com.erickhene;

import com.erickhene.util.ObjectMapperUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Slf4j
@SpringBootApplication
public class SekolahApplication {

	public static void main(String[] args) {
		log.info("Read Environment [{}]", "CONFIGURATION");
		String configuration = System.getenv("CONFIGURATION");
		if (configuration != null && configuration.isEmpty() == Boolean.FALSE){
			log.info("CONFIGURATION = {}", configuration);
			Properties properties = System.getProperties();
			TypeReference<HashMap<String,Object>> typeRef = new TypeReference<>() {};
			try {
				HashMap<String,Object> config = ObjectMapperUtil.generateObjectMapper().readValue(configuration, typeRef);
				for (Map.Entry<String, Object> entry: config.entrySet()) {
					log.info("Set {} = {}", entry.getKey(), entry.getValue());
					properties.put(entry.getKey(), entry.getValue());
				}
			} catch (JsonProcessingException e) {
				log.error("Error [{}]", e.getMessage());
			}

			System.setProperties(properties);
		}
		SpringApplication.run(SekolahApplication.class, args);
	}

}
