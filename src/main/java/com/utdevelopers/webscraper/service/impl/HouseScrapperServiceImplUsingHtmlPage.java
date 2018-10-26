package com.utdevelopers.webscraper.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.utdevelopers.webscraper.config.ApartmentScrapperConfig;
import com.utdevelopers.webscraper.model.House;
import com.utdevelopers.webscraper.service.HouseScrapperService;

import lombok.extern.slf4j.Slf4j;

@Service(value = "htmlPage")
@Slf4j
public class HouseScrapperServiceImplUsingHtmlPage implements HouseScrapperService {

  @Autowired
  private WebClient webClient;

  @Autowired
  private ApartmentScrapperConfig webScrapperConfig;

  @Override
  public List<House> getListOfHouses(String location) {
    webClient.getOptions().setCssEnabled(false);
    webClient.getOptions().setJavaScriptEnabled(false);
    List<House> houses = new ArrayList<>();
    try {
      HtmlPage homePage = webClient.getPage(webScrapperConfig.getBaseUrl());
      DomNode searchTextBox = homePage.getElementById("quickSearchLookup");
      searchTextBox.setTextContent(location);
      HtmlAnchor goAnchor = homePage.getAnchorByHref("https://www.apartments.com/search/");
      HtmlPage searchResultPage = goAnchor.click();
      searchResultPage.save(new File("C:\\Node workspace\\"));
      List<DomNode> listOfHouses =
          searchResultPage.getElementById("placardContainer").getChildren().iterator().next().getChildNodes();
      if (listOfHouses.isEmpty()) {
        System.out.println("No items found !");
      }
    } catch (IOException e) {
      System.out.println(e);
      log.error("Exception while scrapping the webpage", e);
    }
    return houses;

  }


}
