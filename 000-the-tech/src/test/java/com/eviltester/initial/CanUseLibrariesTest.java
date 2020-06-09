package com.eviltester.initial;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class CanUseLibrariesTest {

    // demonstrate that we understand the technology first,
    // prior to building a 'keyword driven framework to make it all easier'

    @Test
    public void canUseWebDriverLibraries(){

        // NOTE: this will be the initial script we implement using the keywords

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.get("https://testpages.herokuapp.com/");

        // this is for the visual effect of showing that it works, not for sync
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.close();
    }
}
