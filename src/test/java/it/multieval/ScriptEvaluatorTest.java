package it.multieval;

import com.google.common.collect.ImmutableMap;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.io.Zip;
import org.openqa.selenium.remote.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.yandex.qatools.allure.annotations.Attachment;

import java.io.File;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:META-INF/spring/context.xml")
public class ScriptEvaluatorTest {

    @Autowired
    Config config;

    @Value("classpath:page.html")
    Resource pageFile;

    RemoteWebDriver driver;

    @Attachment
    byte[] makeScreenshot() {
        return driver.getScreenshotAs(OutputType.BYTES);
    }

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities browser = DesiredCapabilities.internetExplorer();
        driver = new RemoteWebDriver(config.getSeleniumUrl(), browser);
    }

    @Test
    public void testFileUpload() throws Exception {
        File webpage = pageFile.getFile();
        String zip = new Zip().zipFile(webpage.getParentFile(), webpage);
        Response response = driver.getCommandExecutor().execute(new Command(driver.getSessionId(), DriverCommand.UPLOAD_FILE, ImmutableMap.of("file", zip)));
        driver.get("file://" + response.getValue());
        assertThat(driver.getTitle(), equalTo("Test page"));
        try {
            Object result = driver.executeScript("return document.documentMode;");
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        makeScreenshot();
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }
}
