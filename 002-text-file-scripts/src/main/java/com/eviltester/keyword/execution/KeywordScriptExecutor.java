package com.eviltester.keyword.execution;

import com.eviltester.keyword.script.KeywordCommand;
import com.eviltester.keyword.script.KeywordScript;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class KeywordScriptExecutor {

    WebDriver driver;

    public KeywordScriptExecutor execute(final KeywordScript script) {

        WebDriverManager.chromedriver().setup();

        for(int lineIndex=0; lineIndex<script.getLineCount(); lineIndex++){
            executeCommand(script.getLine(lineIndex));
        }

        return this;
    }

    private void executeCommand(final KeywordCommand line) {

        switch (line.getCommand()){
            case "OPEN":
                String browserName = line.getArgument(0).toUpperCase();
                if(browserName.equals("CHROME")){
                    driver = new ChromeDriver();
                }else{
                    throw new RuntimeException("Unknown Driver: " + browserName);
                }
                break;
            case "CLOSE":
                driver.close();
                break;
            case "GET":
                driver.get(line.getArgument(0));
                break;
            case "SLEEP":
                int millis = Integer.parseInt(line.getArgument(0));
                try {
                    Thread.sleep(millis);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            default:
                throw new RuntimeException("Unknown command " + line);
        }

        return;
    }
}
