package com.mvnikitin.eshop.ui_test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BrandPage {

    protected WebDriver driver;

    private String url;
    private String title;

    public BrandPage(WebDriver driver) {
        this.driver = driver;
        url = driver.getCurrentUrl();
        title = driver.getTitle();

        if (!"brand | eshop management".equals(title)) {
            throw new RuntimeException("Incorrect page, title: " +
                    title + ", url: " + url);
        }
    }

    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }

    public void setBrandName(String name) {
        driver.findElement(By.id("brand")).sendKeys(name);
    }

    public String ok() {
        driver.findElement(By.xpath(
                "/html/body/div/div[2]/div/div/div[2]/div/div/button"))
                .click();
        return driver.getCurrentUrl();
    }

    public String cancel() {
        driver.findElement(By.xpath(
                "/html/body/div/div[2]/div/div/div[2]/div/div/a"))
                .click();
        return driver.getCurrentUrl();
    }

    public String getBrandName() {
        return driver.findElement(By.id("brand")).getAttribute("value");
    }

    public void clearBrandField() {
        driver.findElement(By.id("brand")).clear();
    }
}
