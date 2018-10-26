package com.utdevelopers.webscraper.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.utdevelopers.webscraper.model.Zipcode;
import com.utdevelopers.webscraper.service.ZipcodeImportService;
import com.utdevelopers.webscraper.service.ZipcodeService;

@RestController
public class ZipcodeImporterController {

  @Autowired
  private ZipcodeImportService zipcodeImportService;
  
  @Autowired
  private ZipcodeService zipcodeService;

  @RequestMapping(value = "/import/")
  public void scrapHousesFrom() {
    List<Zipcode> zipcodes = zipcodeImportService.getZipCodes();
    zipcodeService.saveZipcodes(zipcodes);
  }

}
