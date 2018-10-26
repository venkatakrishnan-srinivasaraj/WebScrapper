package com.utdevelopers.webscraper.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Document(collection = "House")
@Getter
@Setter
@ToString
public class House {

  @Id
  private String houseId;
  private String urlOfHouse;
  private GeoLocation geoLocation;
  private String rent;
  private String availability;
  private String description;
}
