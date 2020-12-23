package com.mvnikitin.eshop.ui_test.steps;

import com.mvnikitin.eshop.ui_test.WebDriverInitializer;
import com.mvnikitin.eshop.ui_test.pages.IndexPage;
import com.mvnikitin.eshop.ui_test.pages.LoginPage;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginSteps {

    private WebDriver driver;

    private LoginPage loginPage;
    private IndexPage indexPage;

    @Given("^I open a web browser")
    public void iOpenAWebBrowser() {
        driver = WebDriverInitializer.getDriver();
        driver.manage().window().maximize();
    }

    @And("^I navigate to the login page$")
    public void iNavigateToTheLoginPage() {
        loginPage = new LoginPage(
                driver, WebDriverInitializer.getProperty("index.url"));
        assertThat(loginPage.getUrl())
                .isEqualTo(WebDriverInitializer.getProperty("login.url"));
    }

    @Given("^I provide username as \"([^\"]*)\" and password as \"([^\"]*)\"$")
    public void iProvideUsernameAsAndPasswordAs(String username, String password) {
        loginPage.setUsername(username);
        loginPage.setPassword(password);
    }

    @When("^I click on login button$")
    public void iClickOnLoginButton() {
        indexPage = loginPage.login();
    }

    @Then("^page title must be \"([^\"]*)\"$")
    public void pageTitleMustBe(String title) {
        assertThat(indexPage.getTitle()).isEqualTo(title);
    }

    @And("^name should be \"([^\"]*)\"$")
    public void nameShouldBe(String name) {
        assertThat(indexPage.getLoggedInUser()).isEqualTo(name);
    }

    @When("^Open dropdown menu$")
    public void openDropdownMenu() {
        indexPage.openDropDownMenu();
    }

    @Then("^logout$")
    public void userLoggedOut() {
        assertThat(indexPage.logout())
                .isEqualTo(WebDriverInitializer.getProperty("login.url"));
    }

    @Then("^page is the same and error message is \"([^\"]*)\"$")
    public void pageIsTheSameAndErrorMessageIs(String errorMessage) {
        assertThat(loginPage.getErrorMessage()).isEqualTo(errorMessage);
    }

    @After(value = "@logintest")
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
