package com.iMobile3.steps;

import com.iMobile3.framework.base.Browser;
import cucumber.api.java.en.And;

public class TestingSteps {

    @And("^the user clicks the \"([^\"]*)\" (?:Button|button|button on the confirmation modal)$")
    public void theUserClicksTheButton(String Button) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Browser.ClickButton(Button);
    }
}
