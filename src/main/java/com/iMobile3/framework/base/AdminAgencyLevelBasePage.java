package com.iMobile3.framework.base;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class AdminAgencyLevelBasePage extends NavPanelBase {


    @FindBy(how = How.XPATH, using = "(//a[@href='/admin'])[2]")
    public WebElement lnkDashboard;

    @FindBy(how = How.XPATH, using = "//a[@href='#'][contains(.,'Reports')]")
    public WebElement lnkReports;

    @FindBy(how = How.XPATH, using = "//a[@href='/admin/reports/accounts']")
    public WebElement lnkReportsAccounts;

    @FindBy(how = How.XPATH, using = "//a[@href='/admin/apps']")
    public WebElement lnkApp;

    @FindBy(how = How.XPATH, using = "//a[@href='/admin/agencies/edit'][contains(.,'Settings')]")
    public WebElement lnkSettings;

    @FindBy(how = How.XPATH, using = "//a[@href='/admin/agencies/permissions']")
    public WebElement lnkMerchantPermissions;

    @FindBy(how = How.XPATH, using = "//a[@href='#'][contains(.,'Staff')]")
    public WebElement lnkStaff;

    @FindBy(how = How.XPATH, using = "//a[@href='/admin/staff/agency-users']")
    public WebElement lnkAgencyUsers;

    @FindBy(how = How.XPATH, using = "//a[@href='/admin/staff/agency-groups']")
    public WebElement lnkAgencyGroups;

    @FindBy(how = How.XPATH, using = "//a[@href='#'][contains(.,'Accounts')]")
    public WebElement lnkAccounts;

    @FindBy(how = How.XPATH, using = "//a[@href='/admin/accounts/production']")
    public WebElement lnkProduction;

    @FindBy(how = How.XPATH, using = "//a[@href='/admin/accounts/inprogress']")
    public WebElement lnkInProgress;

    @FindBy(how = How.XPATH, using = "//a[@href='/admin/accounts/test']")
    public WebElement lnkTest;

    @FindBy(how = How.XPATH, using = "//a[@href='/admin/accounts/templates']")
    public WebElement lnkTemplates;

    @FindBy(how = How.XPATH, using = "//a[@href='/admin/agencies/switch'][contains(.,'Back To Partner')]")
    public WebElement lnkBackToPartner;

    @FindBy(how = How.XPATH, using = "//a[@href='/admin/partners/switch-back'][contains(.,'Back To Parent')]")
    public WebElement lnkBackToParent;

}
