package com.mvnikitin.eshop.ui_test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage  {

    protected WebDriver driver;

    private String url;
    private String title;

    public LoginPage(WebDriver driver, String url) {
        this.driver = driver;

        driver.get(url);
        // It will be redirected to the login page
        this.url = driver.getCurrentUrl();
        title = driver.getTitle();

        if (!"login | eshop management".equals(title)) {
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

    public void setUsername(String username) {
        WebElement usernameField = driver.findElement(By.name("username"));
        usernameField.sendKeys(username);
    }

    public void setPassword(String password) {
        WebElement passwordField = driver.findElement(By.name("password"));
        passwordField.sendKeys(password);
    }

    public IndexPage login() {
        driver.findElement(By
                .xpath("/html/body/div/div/div/div/div/form/div[3]/div/button"))
                .click();

        if (driver.getTitle().equals(title)) {
            return null;
        }

        return new IndexPage(driver, driver.getCurrentUrl());
    }

    public String getErrorMessage() {
        WebElement errorBlock = driver.findElement(By.id("error_message"));
        return errorBlock != null ? errorBlock.getText() : null;
    }
}
