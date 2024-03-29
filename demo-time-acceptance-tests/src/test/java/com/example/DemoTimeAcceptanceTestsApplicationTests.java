package com.example;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
public class DemoTimeAcceptanceTestsApplicationTests {

	@Value("${AppUrl:http://localhost:8080}")
	private String appURL; 
	
	@Test
	public void testDemoTime() {

		System.out.println("+++ Testing endpoint : " + appURL);
		RestTemplate template = new RestTemplate();
		String result = template.getForObject(appURL, String.class);
		Assertions.assertThat(result.startsWith("Version")).isTrue();
	}

}
