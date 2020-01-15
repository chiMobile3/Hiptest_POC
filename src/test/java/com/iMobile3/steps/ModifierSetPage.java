package com.iMobile3.steps;

import com.iMobile3.features.pages.*;
import com.iMobile3.framework.base.Base;
import com.iMobile3.framework.base.TablesPOS;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;


public class ModifierSetPage extends Base {

    @When("^the user enters \"([^\"]*)\" in the \"([^\"]*)\" placeholder field$")
    public void theUserEntersInThePlaceholderField(String text, String textBoxName) throws Throwable {
        POSModifiersPage.placeHoldertextbox(textBoxName,text);
    }

    @Then("^\"([^\"]*)\" will be displayed in the \"([^\"]*)\" Column$")
    public void willBeDisplayedInTheColumn(String item, String searchColumn) throws Throwable {
        Assert.assertTrue(TablesPOS.ValidateColumnData(item,searchColumn));
    }
}
