package com.iMobile3.features.pages;

import com.iMobile3.framework.Settings;
import com.iMobile3.framework.base.DriverContext;
import com.iMobile3.framework.base.NavPanelBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class HomePage extends NavPanelBase {

    //Page Specific Elements
    @FindBy(how = How.XPATH, using = "//i[@class='lin-timeclock text-timeclock']")
    public WebElement iconTimeClock;

    @FindBy(how = How.XPATH, using = "//h4[contains(text(),'THE LEADING POINT OF SALE SOLUTION')]")
    public WebElement headerDisplay;

    @FindBy(how = How.XPATH, using = "//i[@class='lin-cashier text-pos']")
    public WebElement iconPOS;

    @FindBy(how = How.XPATH, using = "//i[contains(@class,'lin-library2 text-admin')]")
    public WebElement iconADMIN;

    //Methods

    public String LoggedInHeader() {
        return headerDisplay.getText();
    }

    public boolean IsLogin() {
        DriverContext.WaitForElementtVisible(iconTimeClock);
        return iconTimeClock.isDisplayed();
    }

    public void GoToPartnerDashboard() {
        DriverContext.WaitForElementtVisible(iconADMIN);
        iconADMIN.click();
    }

    public void Launch() {
        DriverContext.Browser.GoToUrl(Settings.URLSite);
    }

    public void ChooseIcon(String Icon) {
        List<WebElement> IconList = DriverContext.Driver.findElements(By.tagName("h4"));
        for (WebElement icons : IconList)
            if ((icons.getText().toUpperCase()).equals(Icon.toUpperCase())) {
                //  System.out.println(icons.getText() + " This is the Icon to click");
                icons.click();
                break;
            }
    }


}
