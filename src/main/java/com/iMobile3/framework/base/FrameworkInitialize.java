package com.iMobile3.framework.base;


//import com.iMobile3.framework.utilities.ExcelUtil;
//import jxl.read.biff.BiffException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.ProfilesIni;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FrameworkInitialize extends Base {

    public void InitializeBrowser(BrowserType browserType) throws Exception {

        WebDriver driver = null;
        switch (browserType) {
            case Chrome: {
                //Open the browser
                System.setProperty("webdriver.chrome.driver", DriverPath + "chromedriver.exe");
                // Setting new download directory path
                Map<String, Object> prefs = new HashMap<String, Object>();

                // Adding default download directory
                prefs.put("download.default_directory", DownloadPath);

                // Adding capabilities to ChromeOptions
                ChromeOptions options = new ChromeOptions();
                options.setExperimentalOption("prefs", prefs);
                options.addArguments("start-maximized");
                // options.addArguments("download.default_directory",Settings.DownloadDir);

                driver = new ChromeDriver(options);
                break;
            }
            case Firefox: {
                //Open the browser
                System.setProperty("webdriver.gecko.driver", DriverPath + "geckodriver.exe");

                ProfilesIni profile = new ProfilesIni();

                FirefoxProfile automationProfile = profile.getProfile("default");
                automationProfile.setPreference("browser.download.folderList", 2);
                automationProfile.setPreference("browser.download.manager.showWhenStarting", false);
                automationProfile.setPreference("browser.download.dir", DownloadPath);
                automationProfile.setPreference("browser.download.forbid_open_with", true);
                automationProfile.setPreference("browser.helperApps.neverAsk.openFile",
                        "text/csv,application/octet-stream,application/x-msexcel,application/excel,application/x-excel,application/vnd.ms-excel,image/gif,image/png,image/jpeg,text/html,text/plain,application/msword,application/xml, application/csv");
                automationProfile.setPreference("browser.helperApps.neverAsk.saveToDisk",
                        "text/csv,application/octet-stream,application/x-msexcel,application/excel,application/x-excel,application/vnd.ms-excel,image/gif,image/png,image/jpeg,text/html,text/plain,application/msword,application/xml, application/csv");
                automationProfile.setPreference("browser.helperApps.alwaysAsk.force", false);
                automationProfile.setPreference("browser.download.manager.alertOnEXEOpen", false);
                automationProfile.setPreference("browser.download.manager.focusWhenStarting", false);
                automationProfile.setPreference("browser.download.manager.useWindow", false);
                automationProfile.setPreference("browser.download.manager.showAlertOnComplete", false);
                automationProfile.setPreference("browser.download.manager.closeWhenDone", false);

                FirefoxOptions opt = new FirefoxOptions();
                opt.setProfile(automationProfile);

                driver = new FirefoxDriver(opt);
                //driver = new FirefoxDriver();
                break;
            }
        }
        //Set the Driver
        DriverContext.setDriver(driver);

        //Set the Browser
        DriverContext.Browser = new Browser(driver);
    }

//    //Initialize Excel as Utility
//    public void InitializeExcel(String excelPath) throws IOException, BiffException {
//        ExcelUtil util = new ExcelUtil(excelPath);
//    }

}
