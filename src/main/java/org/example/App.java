package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.example.config.SeleniumConfiguration;
import org.example.dto.BirdInfo;
import org.example.pages.SpeciesPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        WebDriver driver=new SeleniumConfiguration().initialiseDriver();
        driver.get("https://pasaridinromania.sor.ro/ornitodata/specii?page=1");

        SpeciesPage speciesPage=new SpeciesPage(driver);

        while(speciesPage.hasNext()) {
            List<WebElement>birdSpecies = driver.findElements(By.xpath("//tbody/tr/td[2]/a"));

            for(WebElement element:birdSpecies)
            {
                String title=element.getText().trim();
                String[]titleSeparated=title.split(",");
                speciesPage.birdSpecies.add(new BirdInfo(titleSeparated[0],titleSeparated[1].trim(),element.getAttribute("href")));
            }
            speciesPage.nextPage();

        }
        List<WebElement>birdSpecies = driver.findElements(By.xpath("//tbody/tr/td[2]/a"));

        for(WebElement element:birdSpecies)
        {
            String title=element.getText().trim();
            String[]titleSeparated=title.split(",");
            speciesPage.birdSpecies.add(new BirdInfo(titleSeparated[0],titleSeparated[1].trim(),element.getAttribute("href")));
        }


        // Convert the ArrayList to a JSON array
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(Paths.get("bird_info.json").toFile(), speciesPage.birdSpecies);
        }
        catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        driver.quit();

    }
}
