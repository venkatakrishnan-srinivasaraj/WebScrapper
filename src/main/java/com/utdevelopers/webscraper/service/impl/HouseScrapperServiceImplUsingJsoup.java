package com.utdevelopers.webscraper.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.utdevelopers.webscraper.config.ApartmentScrapperConfig;
import com.utdevelopers.webscraper.model.House;
import com.utdevelopers.webscraper.service.HouseScrapperService;

import lombok.extern.slf4j.Slf4j;

@Service(value = "jsoup")
@Slf4j
public class HouseScrapperServiceImplUsingJsoup implements HouseScrapperService {

  @Autowired
  private ApartmentScrapperConfig webScrapperConfig;

  @Override
  public List<House> getListOfHouses(String location) {
    List<House> houses = new ArrayList<>();
    try {
      Document documentFromWeb = Jsoup.connect(webScrapperConfig.getBaseUrl() + location).get();
      Elements listOfHouseListing = getListingOfHouses(documentFromWeb);
      if (listOfHouseListing != null) {
        for (Element eachListing : listOfHouseListing) {
          contructHouseObjectFromHouseListing(houses, eachListing);
        }
      }
    } catch (IOException e) {
      System.out.println(e);
      log.error("Exception while scrapping the webpage", e);
    }
    return houses;
  }

  private void contructHouseObjectFromHouseListing(List<House> houses, Element eachListing) {
    House house = new House();
    house.setUrlOfHouse(eachListing.getElementsByAttributeStarting(webScrapperConfig.getHouseUrlField()).attr(webScrapperConfig.getHouseUrlField()));
//    house.setGeoLocation(eachListing.getElementsByClass(webScrapperConfig.getLocationField()).text());
    house.setRent(eachListing.getElementsByClass(webScrapperConfig.getRentField()).text());
    house.setAvailability(eachListing.getElementsByClass(webScrapperConfig.getAvailabilityField()).text());
    houses.add(house);
  }

  private Elements getListingOfHouses(Document documentFromWeb) {
    Elements placardContainer = documentFromWeb.getElementsByClass("placardContainer");
    if (placardContainer != null && placardContainer.iterator().hasNext()) {
      Elements unOrderedList = placardContainer.iterator().next().children();
      if (unOrderedList != null && unOrderedList.iterator().hasNext()) {
        Elements listOfHouseListing = unOrderedList.iterator().next().children();
        return listOfHouseListing;
      }
    }
    return null;
  }

}
