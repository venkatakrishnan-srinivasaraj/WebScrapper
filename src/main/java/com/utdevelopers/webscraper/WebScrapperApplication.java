package com.utdevelopers.webscraper;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.gargoylesoftware.htmlunit.WebClient;

@SpringBootApplication
public class WebScrapperApplication {
    
  @Bean("firefox")
  public WebDriver webDriverForFirefox() {
    WebDriver firefoxWebDriver = new FirefoxDriver();
    firefoxWebDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    return firefoxWebDriver;
  }
  
//  @Bean("chrome")
//  public WebDriver webDriverForChrome() {
//    WebDriver chromeWebDriver = new ChromeDriver();
//    chromeWebDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//    return chromeWebDriver;
//  }
  
  @Bean
  public WebClient webClient() {
      return new WebClient();
  }
  
  public static void main(String[] args) {
    System.setProperty("webdriver.gecko.driver","C:\\java_installations\\geckodriver.exe");
    System.setProperty("webdriver.chrome.driver", "C:\\java_installations\\chromedriver.exe");
    SpringApplication.run(WebScrapperApplication.class, args);
  }
}
