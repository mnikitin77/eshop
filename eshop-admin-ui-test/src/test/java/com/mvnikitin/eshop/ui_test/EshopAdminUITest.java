package com.mvnikitin.eshop.ui_test;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.Ignore;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        publish = true,
        features = {"classpath:features"},
        glue = {"com.mvnikitin.eshop.ui_test.steps"},
        snippets = CucumberOptions.SnippetType.CAMELCASE)
public class EshopAdminUITest {

}
