package com.utdevelopers.webscraper.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;

@Getter
@Configuration
public class ApartmentScrapperConfig {
  
  @Value("${apartments.baseUrl}")
  private String baseUrl;
  
  @Value("${apartments.houseUrlField}")
  private String houseUrlField;
  
  @Value("${apartments.locationField}")
  private String locationField;
  
  @Value("${apartments.rentField}")
  private String rentField;
  
  @Value("${apartments.availabilityField}")
  private String availabilityField;

}
