package com.mvnikitin.eshop.ui_test.steps;

import com.mvnikitin.eshop.ui_test.WebDriverInitializer;
import com.mvnikitin.eshop.ui_test.pages.BrandPage;
import com.mvnikitin.eshop.ui_test.pages.BrandsPage;
import com.mvnikitin.eshop.ui_test.pages.LoginPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.Assertions.assertThat;

public class BrandsSteps {

    private WebDriver driver;

    private BrandsPage brandsPage;
    private BrandPage brandPage;

    @Before(value = "@login_require")
    public void setUp() {
        driver = WebDriverInitializer.getDriver();
        driver.manage().window().maximize();

        LoginPage loginPage = new LoginPage(driver,
                WebDriverInitializer.getProperty("index.url"));
        loginPage.setUsername(WebDriverInitializer.getProperty("username"));
        loginPage.setPassword(WebDriverInitializer.getProperty("password"));
        loginPage.login();
    }

    @Given("^I open Brands page$")
    public void iOpenBrandsPage() {
        brandsPage = new BrandsPage(driver);
        assertThat(brandsPage.getUrl()).isEqualTo(
            WebDriverInitializer.getProperty("brands.url"));
    }

    @When("^I click on Add Brand button$")
    public void iClickOnAddBrandButton() {
        brandPage = brandsPage.openBrandPage();
    }

    @Then("^New Brand page \"([^\"]*)\" is open$")
    public void newBrandPageIsOpen(String title) {
        assertThat(brandPage.getTitle()).isEqualTo(title);
    }

    @When("^I provide brand \"([^\"]*)\"$")
    public void iProvideBrand(String newBrand) {
        brandPage.setBrandName(newBrand);
    }

    @And("^click OK button and return to Brands page$")
    public void clickOKButton() {
        String url = brandPage.ok();
        assertThat(url)
                .isEqualTo(WebDriverInitializer.getProperty("brands.url"));
    }

    @Then("^new brand is on the list \"([^\"]*)\"$")
    public void newBrandIsOnTheList(String newBrand) {
        boolean isPresent = brandsPage.isBrandPresent(newBrand);
        brandsPage.resetTableFilter();
        brandsPage.deleteBrand(newBrand);
        assertThat(isPresent).isEqualTo(true);
    }

    @When("^I find the brand \"([^\"]*)\" and I click the Edit button$")
    public void iFindTheBrandAndIClickTheEditButton(String oldBrand) {
        brandPage = brandsPage.editBrand(oldBrand);
    }

    @And("^I see the current brand \"([^\"]*)\"$")
    public void iSeeTheCurrentBrand(String newBrand) {
        assertThat(brandPage.getBrandName()).isEqualTo(newBrand);
    }

    @And("I clear the Brand field")
    public void iClearTheBrandField() {
        brandPage.clearBrandField();
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
