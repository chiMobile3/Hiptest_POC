package com.iMobile3.features.pages;

import com.iMobile3.framework.base.BasePage;
import com.iMobile3.framework.base.DriverContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;


public class LoginPage extends BasePage {

    @FindBy(how = How.XPATH, using = "//h5[contains(.,'Alerts have occurred')]")
    public WebElement msgErrors;

    @FindBy(how = How.XPATH, using = "//a[@href='/support']")
    public WebElement lnkSupport;

    @FindBy(how = How.XPATH, using = "//a[@href='http://help.imobile3.com']")
    public WebElement lnkHelp;

    @FindBy(how = How.LINK_TEXT, using = "Log In")
    public WebElement lnkLogIn;

    @FindBy(how = How.NAME, using = "Email")
    public WebElement txtUserName;

    @FindBy(how = How.NAME, using = "Password")
    public WebElement txtPassword;

    @FindBy(how = How.XPATH, using = "//button[@type='submit']")
    public WebElement btnLogin;

    @FindBy(how = How.XPATH, using = "//input[@id='Remember']")
    public WebElement chkboxRememberEmail;

    @FindBy(how = How.LINK_TEXT, using = "Forgot password?")
    public WebElement lnkForgotPassword;

    @FindBy(how = How.LINK_TEXT, using = "Terms of Use")
    public WebElement lnkTermsOfUse;

    @FindBy(how = How.LINK_TEXT, using = "Privacy Policy")
    public WebElement lnkPrivacyPolicy;

    //Methods
    public void EnterLoginInfo(String userName, String password) {
        txtUserName.sendKeys(userName);
        txtPassword.sendKeys(password);
    }

    public void ClickLogin() {
        btnLogin.submit();
    }

    public void CheckRememberBox() {
        if (chkboxRememberEmail.isSelected()) {
            System.out.println("The box has already been checked");
        } else {
            chkboxRememberEmail.click();
        }
    }

    public void UncheckRememberBox() {
        if (chkboxRememberEmail.isSelected()) {
            chkboxRememberEmail.click();
        } else {
            System.out.println("The box is not checked");
        }
    }

    public String LogInError() {
        //DriverContext.WaitForElementtVisible(msgErrors);
        return msgErrors.getText();
    }

    public boolean IsLoginDisplayed() {

        return lnkLogIn.isDisplayed();
    }

}

