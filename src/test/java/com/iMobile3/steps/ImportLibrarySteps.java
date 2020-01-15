package com.iMobile3.steps;

//import com.iMobile3.features.pages.POSItemsLibraryPage;
import com.iMobile3.framework.base.Base;
import com.iMobile3.framework.base.DriverContext;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

public class ImportLibrarySteps extends Base {






    @Given("^the user is on the \"([^\"]*)\" page$")
    public void theUserIsOnThePage(String SiteName) throws Throwable {
        // Grab the credentials from the Scenario table
        DriverContext.Browser.GoToThisURL(SiteName);
        //Assert.assertTrue("Page not found", Browser.verifyPage(SiteName));
    }

//    @When("^the user searches \"([^\"]*)\" in the \"([^\"]*)\" field$")
//    public void theUserSearchesInTheField(String strSearchFor, String arg1) throws Throwable {
//        POSItemsLibraryPage.SearchItem(strSearchFor);
//    }
}
