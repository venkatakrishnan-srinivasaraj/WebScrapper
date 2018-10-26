package com.utdevelopers.webscraper.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;

@Getter
@Configuration
public class ImporterConfig {
  
  @Value("${import.csvlocation}")
  private String locationOfCsvFile;

}
