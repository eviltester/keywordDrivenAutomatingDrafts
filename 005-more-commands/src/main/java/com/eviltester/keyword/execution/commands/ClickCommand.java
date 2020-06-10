package com.eviltester.keyword.execution.commands;

import com.eviltester.keyword.execution.ExecutionStateStore;
import com.eviltester.keyword.execution.KeywordExecutor;
import com.eviltester.keyword.script.KeywordCommand;
import org.apache.commons.math3.analysis.function.Exp;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ClickCommand implements KeywordExecutor {
    @Override
    public boolean execute(final ExecutionStateStore state, final KeywordCommand command) {

        WebDriver driver = state.getDriver();

        String locator = command.getArgument(0);

        // assume the locator is any of the following - link text, css selector, classname, id etc.
        // TODO: add a second parameter which defines TEXT, NAME, CSS, XPATH, ID etc.
        // TODO: have TIMEOUT-AFTER command to set the timeout for waiting
        final Boolean found = new WebDriverWait(driver, 10).until(
                ExpectedConditions.or(
                        ExpectedConditions.elementToBeClickable(By.linkText(locator)),
                        ExpectedConditions.elementToBeClickable(By.cssSelector(locator)),
                        ExpectedConditions.elementToBeClickable(By.className(locator)),
                        ExpectedConditions.elementToBeClickable(By.xpath(locator)),
                        ExpectedConditions.elementToBeClickable(By.id(locator))
                )
        );

        // since I don't know how we found it, I have to try them all
        WebElement element = tryAndFindElement(driver, By.linkText(locator));
        if(element==null){
            element = tryAndFindElement(driver, By.cssSelector(locator));
        }
        if(element==null){
            element = tryAndFindElement(driver, By.className(locator));
        }
        if(element==null){
            element = tryAndFindElement(driver, By.xpath(locator));
        }
        if(element==null){
            element = tryAndFindElement(driver, By.id(locator));
        }

        if(element!=null){
            state.setLastElement(element);
            element.click();
        }

        return false;
    }

    private WebElement tryAndFindElement(final WebDriver driver, final By using) {
        try{
            return driver.findElement(using);
        }catch(Exception e) {
            return null;
        }
    }

    @Override
    public String getName() {
        return "CLICK";
    }
}
