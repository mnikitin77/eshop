package com.mvnikitin.eshop.ui_test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class IndexPage {

    protected WebDriver driver;

    private String url;
    private String title;

    public IndexPage(WebDriver driver, String url) {
        this.driver = driver;
        this.url = url;

        driver.get(url);
        title = driver.getTitle();

        if (!"eshop management".equals(title)) {
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

    public String getLoggedInUser() {
        WebElement loggedInUserItem = driver.findElement(By.id("dd_user"));
        return loggedInUserItem.findElement(By.tagName("span")).getText();
    }

    public void openDropDownMenu() {
        WebElement loggedInUserItem = driver.findElement(By.id("dd_user"));

        // By some misterious reason there must be 2 clicks here...
        loggedInUserItem.click();

        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(loggedInUserItem));

        loggedInUserItem.click();
    }

    public String logout() {
        driver.findElement(By.id("logout")).click();
        return driver.getCurrentUrl();
    }
}
