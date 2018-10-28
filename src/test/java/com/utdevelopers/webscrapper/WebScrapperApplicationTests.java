package com.utdevelopers.webscrapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.utdevelopers.webscraper.config.ApartmentScrapperConfig;
import com.utdevelopers.webscraper.config.ImporterConfig;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes= {ApartmentScrapperConfig.class,ImporterConfig.class},loader=AnnotationConfigContextLoader.class)
public class WebScrapperApplicationTests {

	@Test
	public void contextLoads() {
	}

}
