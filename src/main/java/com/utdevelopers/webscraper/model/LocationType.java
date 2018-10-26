package com.utdevelopers.webscraper.model;

import lombok.Getter;

@Getter
public enum LocationType {
  POINT("Point"), POLYGON("Polygon"), CIRCLE("Circle");

  private final String value;

  LocationType(String value) {
    this.value = value;
  }
  
  @Override
  public String toString() {
      return value;
  }
}
