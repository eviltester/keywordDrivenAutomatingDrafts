package com.eviltester.keyword.execution;

import com.eviltester.keyword.logging.KeywordScriptLog;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ExecutionStateStore {

    WebDriver driver;
    private WebElement lastElement;
    KeywordScriptLog log;

    public ExecutionStateStore(final KeywordScriptLog log) {
        this.log = log;
    }

    public void setDriver(final WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setLastElement(final WebElement element) {
        this.lastElement = element;
    }

    public void close() {
        if(driver!=null){
            try{
                driver.close();
            }catch (Exception e){
                log.debug("Something went wrong when closing browser " + e.getMessage());
                log.exceptionRaised(e);
            }
        }
    }

    public KeywordScriptLog getLog() {
        return this.log;
    }
}
