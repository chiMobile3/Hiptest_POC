package com.iMobile3.framework;

import com.iMobile3.framework.base.BrowserType;

import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    public static void PopulateSettings() throws IOException {
        ConfigReader reader = new ConfigReader();
        reader.ReadProperty();
    }

    public static void URLSettings(String PageName) throws IOException {
        ConfigReader reader = new ConfigReader();
        reader.NewReadProperty(PageName);
    }

    private void NewReadProperty(String PageName) throws IOException {
        Properties p = new Properties();
        p.load(getClass().getClassLoader().getResourceAsStream("URLConfig.properties"));
        //Settings.PageName = p.getProperty(DriverContext.GetPageName(PageName));
        Settings.PageName = p.getProperty(PageName);
    }

    private void ReadProperty() throws IOException {
        //Create Property object
        Properties p = new Properties();
        //Load the Property file available in the same package
        p.load(getClass().getClassLoader().getResourceAsStream("GlobalConfig.properties"));
        //Get ExcelSheetPath
        Settings.ExcelSheetPath = p.getProperty("ExcelSheetPath");
        //Get LogPath
        Settings.LogPath = p.getProperty("LogPath");
        //Get BrowserType
        Settings.BrowserType = BrowserType.valueOf(p.getProperty("BrowserType"));
        //Get URLSite
        Settings.URLSite = p.getProperty("URLSite");
        //Get Download Directory for browser
        Settings.DownloadDir = p.getProperty("DownloadDir");

        //Get Login Credentials for Background Hooks
        Settings.FTUserPageBackground_Username = p.getProperty("FTUserPageBackground_Username");
        Settings.FTUserPageBackground_Password = p.getProperty("FTUserPageBackground_Password");

        //Get Step Configs

        //Item Library page
        Settings.ItemLibrary = p.getProperty("ItemLibrary");

    }
}
