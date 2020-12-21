package com.mvnikitin.eshop.ui_test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Properties;

public class WebDriverInitializer {

    private static Properties properties = null;

    static {
        try {
            properties = new Properties();
            properties.load(WebDriverInitializer.class.getClassLoader()
                    .getResourceAsStream("application-ui_test.properties"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static WebDriver getDriver() {
        switch (getProperty("browser")) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver();
            case "chrome":
            default:
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver();
        }
    }

    public static String getProperty(String key) {
        return properties == null ? null : properties.getProperty(key, "");
    }
}
