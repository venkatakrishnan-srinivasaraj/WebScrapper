package com.utdevelopers.webscraper.service.impl;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opencsv.CSVReaderHeaderAware;
import com.utdevelopers.webscraper.config.ImporterConfig;
import com.utdevelopers.webscraper.model.GeoLocation;
import com.utdevelopers.webscraper.model.LocationType;
import com.utdevelopers.webscraper.model.Zipcode;
import com.utdevelopers.webscraper.service.ZipcodeImportService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ZipcodeImportServiceImplFromCsvFile implements ZipcodeImportService {

  @Autowired
  private ImporterConfig importerConfig;

  @Override
  public List<Zipcode> getZipCodes() {
    List<Zipcode> listOfZipCodes = new ArrayList<>();
    System.out.println(importerConfig.getLocationOfCsvFile());
    // ClassLoader classLoader = getClass().getClassLoader();
    try (CSVReaderHeaderAware csvReader =
        new CSVReaderHeaderAware(new FileReader(importerConfig.getLocationOfCsvFile()))) {
      Map<String, String> keyValueMap = csvReader.readMap();
      while (keyValueMap != null) {
        listOfZipCodes.add(convertKeyValueMapToZipCode(keyValueMap));
        keyValueMap = csvReader.readMap();
      }
    } catch (FileNotFoundException e) {
      log.error("File not found exception : {}", e);
    } catch (IOException e) {
      log.error("Exception while reading the csv : {}", e);
    }

    return listOfZipCodes;
  }

  private Zipcode convertKeyValueMapToZipCode(Map<String, String> keyValueMap) {
    Zipcode zipcode = new Zipcode();
    zipcode.setId(keyValueMap.get("Zipcode"));
    String longitude = keyValueMap.get("Long");
    String latitude = keyValueMap.get("Lat");
    if(StringUtils.isNotBlank(latitude) && StringUtils.isNotBlank(longitude)) {
      GeoLocation geolocation = new GeoLocation();
      geolocation.setLocationType(LocationType.POINT);
      Double[] coordinates = {Double.parseDouble(longitude), Double.parseDouble(latitude)};
      geolocation.setCoordinates(coordinates);
      zipcode.setGeolocation(geolocation);
    } 
    System.out.println(zipcode);
    return zipcode;
  }

}
