package com.utdevelopers.webscraper.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.utdevelopers.webscraper.model.Zipcode;
import com.utdevelopers.webscraper.repository.ZipcodeRepository;
import com.utdevelopers.webscraper.service.ZipcodeService;

@Service
public class ZipcodeServiceImpl implements ZipcodeService {

  @Autowired
  private ZipcodeRepository zipcodeRepository;

  @Override
  public void saveZipcodes(List<Zipcode> listOfZipcode) {
    zipcodeRepository.saveAll(listOfZipcode);
  }

}
