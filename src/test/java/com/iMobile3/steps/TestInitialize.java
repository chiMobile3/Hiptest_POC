package com.iMobile3.steps;

import com.iMobile3.framework.ConfigReader;
import com.iMobile3.framework.Settings;
import com.iMobile3.framework.base.DriverContext;
import com.iMobile3.framework.base.FrameworkInitialize;

//import com.iMobile3.framework.utilities.LogUtil;
import cucumber.api.java.Before;

//import static com.iMobile3.framework.config.Settings.URLSite;

public class TestInitialize extends FrameworkInitialize {

    @Before
    public void Initialize() throws Exception {
        //Initialize Configurations
        ConfigReader.PopulateSettings();

        //Logging
//        Settings.Logs = new LogUtil();
//        Settings.Logs.CreateLogFile();
//        Settings.Logs.Write("Framework Initialized.");

        //Open the browser
        InitializeBrowser(Settings.BrowserType);
//        Settings.Logs.Write(Settings.BrowserType + " browser initialized.");

        DriverContext.Browser.GoToUrl(Settings.URLSite);
//        Settings.Logs.Write("Navigated to " + Settings.URLSite);

//        InitializeExcel(Settings.ExcelSheetPath);

    }
   /* @Before("@FTUserPageBackground")
    public void beforeFT7UserPageFunctionalityScenario() throws InterruptedException {
        String userName = Settings.FTUserPageBackground_Username;
        String passWord = Settings.FTUserPageBackground_Password;
        CurrentPage = GetInstance(LoginPage.class);
        Assert.assertTrue("The Portal is not loaded",CurrentPage.As(LoginPage.class).IsLoginDisplayed());
        CurrentPage.As(LoginPage.class).EnterLoginInfo(userName,passWord);
        Browser.ClickButton("Log In");
        CurrentPage = GetInstance(HomePage.class);
        Assert.assertTrue(CurrentPage.As(HomePage.class).IsLogin());
    }
*/


}
