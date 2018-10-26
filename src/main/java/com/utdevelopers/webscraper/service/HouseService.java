package com.utdevelopers.webscraper.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.utdevelopers.webscraper.model.House;

@Service
public interface HouseService {
  
  public void saveHouse(List<House> houses);

}
