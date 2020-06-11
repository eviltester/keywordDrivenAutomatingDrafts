package com.eviltester.keyword.execution;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ExecutionStateStore {

    WebDriver driver;
    private WebElement lastElement;

    public void setDriver(final WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setLastElement(final WebElement element) {
        this.lastElement = element;
    }
}
