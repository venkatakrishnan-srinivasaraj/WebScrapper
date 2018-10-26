package com.utdevelopers.webscraper.model;

import lombok.Getter;

@Getter
public enum ZipcodeType {
  UNIQUE("UNIQUE"), STANDARD("STANDARD"), PO_BOX("PO BOX"), MILITARY("MILITARY");

  private final String value;

  ZipcodeType(String value) {
    this.value = value;
  }
  
  @Override
  public String toString() {
      return value;
  }
}
