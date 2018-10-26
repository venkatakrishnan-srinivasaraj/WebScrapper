package com.utdevelopers.webscraper.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.utdevelopers.webscraper.model.House;

@Service
public interface HouseScrapperService {

  public List<House> getListOfHouses(String location);
  
}
