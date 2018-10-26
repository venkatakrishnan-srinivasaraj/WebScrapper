package com.utdevelopers.webscraper.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.utdevelopers.webscraper.config.ApartmentScrapperConfig;
import com.utdevelopers.webscraper.model.GeoLocation;
import com.utdevelopers.webscraper.model.House;
import com.utdevelopers.webscraper.model.LocationType;
import com.utdevelopers.webscraper.service.HouseScrapperService;

import lombok.extern.slf4j.Slf4j;

@Service(value = "selenium")
@Slf4j
public class HouseScrapperServiceImplUsingSelenium implements HouseScrapperService {

  @Autowired
  private ApartmentScrapperConfig webScrapperConfig;

  @Autowired
  @Qualifier("firefox")
  private WebDriver webDriver;


  @Override
  public List<House> getListOfHouses(String location) {
    System.out.println(location);
    List<House> listOfHousesForTheGivenLocation = null;
    String baseUrlForThisLocation = getBaseUrlForThisLocation(location);
    int pageNumber = 1;
    String currentPageUrl = baseUrlForThisLocation + pageNumber + "/";
    webDriver.get(currentPageUrl);
    String actualPageUrl = webDriver.getCurrentUrl();
    List<String> listOfHouseUrlsForThisLocation = new ArrayList<>();
    // while (currentPageUrl != actualPageUrl) {
    while (pageNumber < 2) {
      List<String> listOfHouseUrlsForThisPage = getListOfHouseUrlsFromTheGivenPage();
      listOfHouseUrlsForThisLocation.addAll(listOfHouseUrlsForThisPage);
      currentPageUrl = baseUrlForThisLocation + ++pageNumber + "/";
      System.out.println(currentPageUrl);
      webDriver.get(currentPageUrl);
      actualPageUrl = webDriver.getCurrentUrl();
    }

    listOfHousesForTheGivenLocation = getListOfHouseFromUrls(listOfHouseUrlsForThisLocation);
    System.out.println(listOfHousesForTheGivenLocation);
    return listOfHousesForTheGivenLocation;
  }

  private List<House> getListOfHouseFromUrls(List<String> listOfHouseUrlsForThisLocation) {
    List<House> listOfHousesInThisLocation = new ArrayList<>();
    for (String urlOfHouse : listOfHouseUrlsForThisLocation) {
      House house = getHouseInfoFromEachUrl(urlOfHouse);
      if(house!=null) {
        listOfHousesInThisLocation.add(house);
      }
    }
    return listOfHousesInThisLocation;
  }

  private House getHouseInfoFromEachUrl(String urlOfHouse) {
    if(StringUtils.isEmpty(urlOfHouse)) {
      return null;
    }
    House house = new House();
    webDriver.get(urlOfHouse);
    Double longitude = null;
    Double latitude = null;
    List<WebElement> listOfMetaTags = webDriver.findElements(By.tagName("meta"));
    for (WebElement eachMetaTag : listOfMetaTags) {
      if (eachMetaTag.getAttribute("property") != null
          && "place:location:longitude".equalsIgnoreCase(eachMetaTag.getAttribute("property"))) {
        longitude = Double.parseDouble(eachMetaTag.getAttribute("content"));
      }
      if (eachMetaTag.getAttribute("property") != null
          && "place:location:latitude".equalsIgnoreCase(eachMetaTag.getAttribute("property"))) {
        latitude = Double.parseDouble(eachMetaTag.getAttribute("content"));
      }
    }
    GeoLocation geoLocation = new GeoLocation();
    geoLocation.setLocationType(LocationType.POINT);
    Double[] coordinates = {longitude, latitude};
    if (longitude != null && latitude != null) {
      geoLocation.setCoordinates(coordinates);
      house.setGeoLocation(geoLocation);
    }
    house.setUrlOfHouse(urlOfHouse);
    System.out.println(house);
    return house;
  }

  private String getBaseUrlForThisLocation(String location) {
    webDriver.get(webScrapperConfig.getBaseUrl());
    WebElement searchTextBox = webDriver.findElement(By.id("quickSearchLookup"));
    searchTextBox.sendKeys(location);
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    WebElement goButton =
        webDriver.findElement(By.xpath("/html/body/div[1]/div/section[1]/div[1]/section/div/div/a/span"));
    goButton.click();
    WebElement newSearchTextBox = webDriver.findElement(By.id("searchBarLookup"));
    return webDriver.getCurrentUrl();
  }

  private List<String> getListOfHouseUrlsFromTheGivenPage() {
    List<WebElement> listOfHouses =
        webDriver.findElements(By.xpath("/html/body/div[1]/main/section/div[1]/section[2]/div[2]/ul/li/article"));
    List<String> listOfHouseUrlsInThisPage = new ArrayList<String>();
    for (WebElement eachHouse : listOfHouses) {
      listOfHouseUrlsInThisPage.add(eachHouse.getAttribute("data-url"));
    }
    return listOfHouseUrlsInThisPage;
  }

  @Deprecated
  private void getListOfHouseUrlsByUsingNextButtonClick(String baseUrlForThisLocation) {
    boolean isNextButtonPresent = true;
    try {
      while (isNextButtonPresent) {
        isNextButtonPresent = retryingFindClick(By.className("menuArrowRightIcon"));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Deprecated
  private boolean retryingFindClick(By by) {
    boolean result = false;
    int attempts = 0;
    while (attempts < 5) {
      try {
        webDriver.findElement(by).click();
        result = true;
        break;
      } catch (Exception e) {
      }
      attempts++;
    }
    return result;
  }

}
