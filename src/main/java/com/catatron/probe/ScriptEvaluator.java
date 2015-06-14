package com.catatron.probe;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static java.lang.String.format;

@Component
public class ScriptEvaluator {

    @Autowired
    Config config;

    private WebDriver getDriver(String browserName, String browserVersion) throws Exception {
        final DesiredCapabilities browser = new DesiredCapabilities(browserName, browserVersion, Platform.ANY);
        try {
            return new RemoteWebDriver(config.getSeleniumUrl(), browser);
        } catch(Exception e) {
            throw new Exception(format("Could not get browser %s %s", browserName, browserVersion));
        }
    }

    public Object evaluate(String browserName, String browserVersion, String javascript) throws Exception {
        WebDriver driver = getDriver(browserName, browserVersion);
        driver.get("about:blank");
        Object result;
        try {
            result = ((JavascriptExecutor) driver).executeScript(javascript);
        } catch (Exception e) {
            throw new Exception("Could not execute script");
        }
        driver.quit();
        return result;
    }
    
}
