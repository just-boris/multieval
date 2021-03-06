package it.multieval;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static java.lang.String.format;

@Component
public class ScriptEvaluator {

    @Autowired
    Config config;

    private RemoteWebDriver getDriver(String browserName, String browserVersion) throws Exception {
        final DesiredCapabilities browser = new DesiredCapabilities(browserName, browserVersion, Platform.ANY);
        try {
            return new RemoteWebDriver(config.getSeleniumUrl(), browser);
        } catch(Exception e) {
            e.printStackTrace();
            throw new Exception(format("Could not get browser %s %s", browserName, browserVersion));
        }
    }

    public Object evaluate(String browserName, String browserVersion, String javascript) throws Exception {
        RemoteWebDriver driver = getDriver(browserName, browserVersion);
        driver.get("about:blank");
        Object result;
        try {
            result = ((JavascriptExecutor) driver).executeScript(javascript);
        } catch (Exception e) {
            String message = e.getMessage();
            message = message.substring(0, message.indexOf(System.getProperty("line.separator")));
            throw new Exception("Could not execute script: " + message);
        }
        finally {
            driver.quit();
        }
        return result;
    }
    
}
