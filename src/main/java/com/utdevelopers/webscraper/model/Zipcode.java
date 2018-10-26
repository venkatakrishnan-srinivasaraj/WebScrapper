package com.utdevelopers.webscraper.model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Document(collection = "Zipcode")
@Getter
@Setter
@ToString
public class Zipcode {

  private String id;
  private ZipcodeType zipcodeType;
  private String city;
  private String state;
  private String county;
  private String country;
  private GeoLocation geolocation;
  private String locationText;
  private Boolean isDecommisioned;

}
