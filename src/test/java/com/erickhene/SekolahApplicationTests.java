package com.erickhene;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class SekolahApplicationTests {

	@Test
	void contextLoads() {
		log.error("Error");
		log.info("Info");
		log.warn("Warn");
	}

}
