package com.mvnikitin.eshop.ui_test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class BrandsPage {

    protected WebDriver driver;

    private String url;
    private String title;

    public BrandsPage(WebDriver driver) {
        this.driver = driver;

        driver.findElement(By.xpath(
                "/html/body/div/div[1]/ul/li[2]/a")).click();

        WebElement brandsLink = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//*[@id=\"sm_expand_content\"]/li[2]/a")));
        brandsLink.click();

        url = driver.getCurrentUrl();
        title = driver.getTitle();

        if (!"brands | eshop management".equals(title)) {
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

    public BrandPage openBrandPage() {
        driver.findElement(By.xpath(
                "/html/body/div/div[2]/div[1]/div/a")).click();
        return new BrandPage(driver);
    }

    public boolean isBrandPresent(String name) {
        List<WebElement> brandLineItems = findBrandLineItems(name);

        for (WebElement e : brandLineItems) {
            if (e.getText().equals(name)) {
                return true;
            }
        }

        return false;
    }

    public void deleteBrand(String name) {
        List<WebElement> brandLineItems = findBrandLineItems(name);

        for (WebElement e : brandLineItems) {

            String classValue = e.getAttribute("class");

            if (classValue.trim().equals("actions")) {
                e.findElement(By.tagName("button")).click();
                return;
            }
        }
    }

    public BrandPage editBrand(String name) {
        List<WebElement> brandLineItems = findBrandLineItems(name);

        for (WebElement e : brandLineItems) {
            if (e.getText().equals(name)) {
                e.findElement(By.xpath(
                        "//*[@id=\"brandstable\"]/tbody/tr/td[3]/a"))
                        .click();

                return new BrandPage(driver);
            }
        }

        return null;
    }

    public void resetTableFilter() {
        driver.findElement(
                By.xpath("//*[@id=\"brandstable_filter\"]/button[2]"))
                .click();
    }

    private List<WebElement> findBrandLineItems(String name) {
        WebElement searchField = driver.findElement(By.xpath(
                "//*[@id=\"brandstable_filter\"]/label/input"));
        searchField.sendKeys(name);
        WebElement searchButton = driver.findElement(
                By.xpath("//*[@id=\"brandstable_filter\"]/button[1]"));
        searchButton.click();

        List<WebElement> rowColumns = driver.findElements(By.tagName("td"));
        return rowColumns;
    }
}
