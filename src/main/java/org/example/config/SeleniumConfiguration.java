package org.example.config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SeleniumConfiguration {
    WebDriver driver;


    public void waitForElementToAppear(By selector)
    {
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(selector));
    }

    public void waitForElementToDisappear(By selector)
    {  WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(selector));
    }
    public WebDriver initialiseDriver(){
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        return driver;
    }
}
