package utils;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import java.io.File;
import java.util.concurrent.TimeUnit;

public class Hooks {
    public static WebDriver driver;
    static String libWithDriverLocation = System.getProperty("user.dir") + File.separator + "lib" + File.separator;

    @Before
    public void openBrowser() {
        System.setProperty("webdriver.edge.driver", libWithDriverLocation + "msedgedriver.exe");
        driver = new EdgeDriver();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @After
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }

    }
}

