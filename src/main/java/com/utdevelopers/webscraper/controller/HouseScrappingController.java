package com.utdevelopers.webscraper.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.utdevelopers.webscraper.model.House;
import com.utdevelopers.webscraper.service.HouseScrapperService;
import com.utdevelopers.webscraper.service.HouseService;

@RestController
public class HouseScrappingController{
  
  @Autowired
  private HouseService houseService;
  
  @Autowired
  @Qualifier("selenium")
  private HouseScrapperService houseScrapperService;
  
  @RequestMapping(value = "/scraphouses/{location}")
  public void scrapHousesFrom(@PathVariable String location) {
    List<House> houses = houseScrapperService.getListOfHouses(location);
    houseService.saveHouse(houses);
  }

}