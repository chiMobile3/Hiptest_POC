package com.iMobile3.steps;

import com.iMobile3.framework.Settings;
import com.iMobile3.framework.base.Base;
import com.iMobile3.framework.utilities.CucumberUtil;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

import com.iMobile3.features.pages.HomePage;
import com.iMobile3.features.pages.LoginPage;

public class LoginSteps extends Base{
//    @Given("^User sees POS Portal$")
//    public void userSeesPOSPortal() {
//    }

    @And("^User sees POS Portal$")
    public void userSeesPOSPortal() throws Throwable {
        // Ensure the Browser launches to the correct Site
        CurrentPage = GetInstance(LoginPage.class);
        Assert.assertTrue("The Portal is not loaded", CurrentPage.As(LoginPage.class).IsLoginDisplayed());
    }
    @When("User enters UserName and Password$")
    public void userEntersUserNameAndPassword(DataTable table) throws Throwable {
        // Grab the credentials from the Scenario table
        CucumberUtil.ConvertDataTableToDict(table);
        CurrentPage.As(LoginPage.class).EnterLoginInfo(CucumberUtil.GetCellValue("UserName"), CucumberUtil.GetCellValue("Password"));
    }

    @And("^User clicks Log in Button$")
    public void userClicksLoginButton() throws Throwable {
        // Click the login button
        CurrentPage.As(LoginPage.class).ClickLogin();
    }

    @Then("^User should see the homepage$")
    public void userShouldSeeTheHomepage() throws Throwable {
        CurrentPage = GetInstance(HomePage.class);
        // Ensure User is Logged in
        Assert.assertTrue(CurrentPage.As(HomePage.class).IsLogin());
        //Settings.Logs.Write("Logged into Portal");
    }

}
