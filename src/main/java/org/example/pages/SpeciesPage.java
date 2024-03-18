package org.example.pages;

import org.example.config.SeleniumConfiguration;
import org.example.dto.BirdInfo;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class SpeciesPage extends SeleniumConfiguration {
    WebDriver driver;
    public List<BirdInfo> birdSpecies;
    @FindBy(xpath = "//a[contains(text(),'›')]")
    WebElement buttonNext;

    public SpeciesPage(WebDriver driver){
        this.driver=driver;
        this.birdSpecies=new ArrayList<>();
        PageFactory.initElements(driver,this);
    }

    public void nextPage()
    {
        buttonNext.click();
    }
    public boolean hasNext(){
        try {
            if (buttonNext.isEnabled())
                return true;
        }catch (Exception e)
        {
            System.out.println("FINISHED!");
        }
        return false;
    }

}
