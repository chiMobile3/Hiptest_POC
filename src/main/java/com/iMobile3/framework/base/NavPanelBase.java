package com.iMobile3.framework.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.sql.Driver;
import java.util.List;
import java.util.stream.Collectors;

public class NavPanelBase extends BasePage {

    @FindBy(how = How.XPATH, using = "//a[@class='navbar-brand']")
    public WebElement lnkLogo;

    @FindBy(how = How.XPATH, using = "//a[@href='/pos']")
    public WebElement lnkGridPOS;

    @FindBy(how = How.XPATH, using = "//i[@class='fa fa-home fa-2x']")
    public WebElement lnkGridHome;

    @FindBy(how = How.XPATH, using = "(//i[@class='lin-timeclock text-timeclock'])[1]")
    public WebElement lnkGridTimeCLock;

    @FindBy(how = How.XPATH, using = "//a[@href='/admin']")
    public WebElement lnkGridAdmin;

    @FindBy(how = How.XPATH, using = "//i[@class='lin-grid-navigation']")
    public WebElement lnkGrid;

    @FindBy(how = How.XPATH, using = "//*[@id='navItems']/li[2]/a/div[1]/span[2]")
    //@FindBy(how = How.XPATH, using = "//span[@class='title']")
    public WebElement strAccountTitle;

    @FindBy(how = How.XPATH, using = "//li[@class='dropdown open']")
    public WebElement drpMenu;

    @FindBy(how = How.XPATH, using = "//a[@href='http://help.imobile3.com']")
    public WebElement lnkHelp;

    @FindBy(how = How.XPATH, using = "//a[@href='/legal']")
    public WebElement lnkLegal;

    @FindBy(how = How.XPATH, using = "//a[@id='lnkRebuildContext']")
    public WebElement lnkRebuildContext;

    @FindBy(how = How.XPATH, using = "//a[@href='/user-profile']")
    public WebElement lnkUserProfile;

    @FindBy(how = How.XPATH, using = "//a[@href='/logout']")
    public WebElement lnkLogOut;

//Methods
    public String GetCurrentAgency() {
        DriverContext.WaitForElementtVisible(strAccountTitle);
        String accountTitle = strAccountTitle.getText();
        return accountTitle;
    }

    private static void UserNameDropDown(String toClick){
        WebElement userName = DriverContext.Driver.findElement(By.id("nav-username"));
        WebElement ParentOfuserName = userName.findElement(By.xpath("../.."));
        //DriverContext.drawBorder(ParentOfuserName);//TO DEBUG
        ParentOfuserName.click();
        List<WebElement> navList = ParentOfuserName.findElements(By.cssSelector("li"));
        navList
                .stream()
                .filter(li->li.getText().trim().equalsIgnoreCase(toClick))
                .forEach(WebElement::click);
    }


    public static void UserPOSDrpDown(String toClick) {
        UserNameDropDown(toClick);
    }


    //Method to get the Account info from the Context Picker in the NavPanel
    public static String getAccountInfoFromContext(){
        return DriverContext.Driver.findElement(By.cssSelector(".context-picker")).getText();
    }

    //Method to get the Account info from the Context Picker in the NavPanel - This can be used to validate the two Items on the nav panel that disclose the Account information
    public static String getAccountInfoFromContext2() {
        List<WebElement> x = DriverContext.Driver.findElements(By.cssSelector(".context-picker span"));
        return
                x.stream()
                        .map(WebElement::getText)
                        .collect(Collectors.joining("-"));
    }

}
