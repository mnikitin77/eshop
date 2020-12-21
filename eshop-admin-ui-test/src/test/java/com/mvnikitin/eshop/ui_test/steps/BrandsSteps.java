package com.mvnikitin.eshop.ui_test.steps;

import com.mvnikitin.eshop.ui_test.WebDriverInitializer;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BrandsSteps {

    private WebDriver driver;

    @Before(value = "@login_require")
    public void setUp() {
        driver = WebDriverInitializer.getDriver();
        driver.manage().window().maximize();
        driver.get(WebDriverInitializer.getProperty("index.url"));

        WebElement usernameField = driver.findElement(By.name("username"));
        usernameField.sendKeys(WebDriverInitializer.getProperty("username"));
        WebElement passwordField = driver.findElement(By.name("password"));
        passwordField.sendKeys(WebDriverInitializer.getProperty("password"));

        WebElement element = driver.findElement(By.
                xpath("/html/body/div/div/div/div/div/form/div[3]/div/button"));
        element.click();
    }

    @Given("^I open Brands page$")
    public void iOpenBrandsPage() {
        WebElement contentItem = driver.findElement(By.xpath("/html/body/div/div[1]/ul/li[2]/a"));
        contentItem.click();

        WebElement brandsLink = new WebDriverWait(driver, 5)
                .until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//*[@id=\"sm_expand_content\"]/li[2]/a")));
        brandsLink.click();

        assertThat(driver.getCurrentUrl()).isEqualTo(
                WebDriverInitializer.getProperty("brands.url"));
    }

    @When("^I click on Add Brand button$")
    public void iClickOnAddBrandButton() {
        WebElement addBrandButton = driver.findElement(By.xpath("/html/body/div/div[2]/div[1]/div/a"));
        addBrandButton.click();
    }

    @Then("^New Brand page is open$")
    public void newBrandPageIsOpen() {
        assertThat(driver.getCurrentUrl())
                .isEqualTo(WebDriverInitializer.getProperty("brand.url"));
    }

    @When("^I provide brand \"([^\"]*)\"$")
    public void iProvideBrand(String newBrand) {
        WebElement brandInputField = driver.findElement(By.id("brand"));
        brandInputField.sendKeys(newBrand);
    }

    @When("^click OK button$")
    public void clickOKButton() {
        WebElement okButton = driver.findElement(By.xpath(
                "/html/body/div/div[2]/div/div/div[2]/div/div/button"));
        okButton.click();
    }

    @Then("^Brands page is open$")
    public void brandsPageIsOpen() {
        assertThat(driver.getCurrentUrl())
                .isEqualTo(WebDriverInitializer.getProperty("brands.url"));
    }

    @Then("^new brand is in the list \"([^\"]*)\"$")
    public void newBrandIsInTheList(String newBrand) {
        WebElement searchField = driver.findElement(By.xpath(
                "//*[@id=\"brandstable_filter\"]/label/input"));
        searchField.sendKeys(newBrand);
        WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"brandstable_filter\"]/button[1]"));
        searchButton.click();

        List<WebElement> rowColumns = driver.findElements(By.tagName("td"));

        boolean isFound = false;

        for (WebElement e: rowColumns) {
            if (e.getText().equals(newBrand)) {
                isFound = true;
            } else {
                String classValue = e.getAttribute("class");
                if (classValue.trim().equals("actions")) {
                    WebElement deleteButton = e.findElement(By.tagName("button"));
                    deleteButton.click();
                }
            }
        }

        assert(isFound);
    }

    @After(value = "@login_require")
    public void tearDown() {
        try {
            driver.navigate().to(
                    WebDriverInitializer.getProperty("logout.url"));
        } finally {
            driver.quit();
        }
    }
}
