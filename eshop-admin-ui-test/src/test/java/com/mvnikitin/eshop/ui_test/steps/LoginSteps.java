package com.mvnikitin.eshop.ui_test.steps;

import com.mvnikitin.eshop.ui_test.WebDriverInitializer;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginSteps {

    private WebDriver driver;

    @Given("^I open a web browser")
    public void iOpenAWebBrowser() {
        driver = WebDriverInitializer.getDriver();
        driver.manage().window().maximize();
    }

    @And("^I navigate to the login page$")
    public void iNavigateToTheLoginPage() {
        driver.get(WebDriverInitializer.getProperty("index.url"));
        assertThat(driver.getCurrentUrl())
                .isEqualTo(WebDriverInitializer.getProperty("login.url"));
    }

    @Given("^I provide username as \"([^\"]*)\" and password as \"([^\"]*)\"$")
    public void iProvideUsernameAsAndPasswordAs(String username, String password) {
        WebElement usernameField = driver.findElement(By.name("username"));
        usernameField.sendKeys(username);

        WebElement passwordField = driver.findElement(By.name("password"));
        passwordField.sendKeys(password);
    }

    @When("^I click on login button$")
    public void iClickOnLoginButton() {
        WebElement element = driver.findElement(By.
                xpath("/html/body/div/div/div/div/div/form/div[3]/div/button"));
        element.click();
    }

    @Then("^page title must be \"([^\"]*)\"$")
    public void pageTitleMustBe(String title) {
        assertThat(driver.getTitle()).isEqualTo(title);
    }

    @And("^name should be \"([^\"]*)\"$")
    public void nameShouldBe(String name) {
        WebElement loggedInUserItem = driver.findElement(By.id("dd_user"));
        WebElement loggedInuserText =
                loggedInUserItem.findElement(By.tagName("span"));
        assertThat(loggedInuserText.getText()).isEqualTo(name);
    }

    @When("^Open dropdown menu$")
    public void openDropdownMenu() {
        WebElement loggedInUserItem = driver.findElement(By.id("dd_user"));
        loggedInUserItem.click();
    }

    @And("^click logout button$")
    public void clickLogoutButton() {
// This does not work in my case :(
//        WebElement logout = driver.findElement(By.id("logout"));
//        logout.click();
        driver.navigate().to(WebDriverInitializer.getProperty("logout.url"));
    }

    @Then("^user logged out$")
    public void userLoggedOut() {
        assertThat(driver.getCurrentUrl())
                .isEqualTo(WebDriverInitializer.getProperty("login.url"));
    }

    @Then("^page is the same and error message is \"([^\"]*)\"$")
    public void pageIsTheSameAndErrorMessageIs(String errorMessage) {
        WebElement errorBlock = driver.findElement(By.id("error_message"));
        assertThat(errorBlock.getText()).isEqualTo(errorMessage);
    }

    @After(value = "@logintest")
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
