package com.utdevelopers.webscraper.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.utdevelopers.webscraper.model.House;
import com.utdevelopers.webscraper.repository.HouseRepository;
import com.utdevelopers.webscraper.service.HouseService;

@Service
public class HouseServiceImpl implements HouseService {

  @Autowired
  private HouseRepository houseRepository;

  @Override
  public void saveHouse(List<House> houses) {
    houseRepository.saveAll(houses);
  }

}