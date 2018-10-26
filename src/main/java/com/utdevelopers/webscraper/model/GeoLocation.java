package com.utdevelopers.webscraper.model;

import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GeoLocation {
  
  private LocationType locationType;
  @GeoSpatialIndexed
  private Double[] coordinates;

}
