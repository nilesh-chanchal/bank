package com.management.bank;

import org.junit.Before;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource("classpath:application.properties")
@ContextConfiguration(classes = ApplicationConfiguration.class)
public abstract class BaseTest {

	@Before
	public void init() {
		MockitoAnnotations.openMocks(this);
	}

}

@Configuration
class ApplicationConfiguration {

}
