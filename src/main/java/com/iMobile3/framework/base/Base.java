package com.iMobile3.framework.base;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.PageFactory;

import java.io.File;

import static com.iMobile3.framework.base.DriverContext.Driver;

public class Base {

    public static JavascriptExecutor js = (JavascriptExecutor) Driver;

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static String ToUploadPath = System.getProperty("user.dir") + File.separator + "externalFiles";
    public static String DownloadPath = System.getProperty("user.dir") + File.separator + "Downloads";
    public static String DriverPath = System.getProperty("user.dir") + File.separator + "Drivers" + File.separator;

    public static String parentWinHandle;


    public static BasePage CurrentPage;
    public static <TPage extends BasePage> TPage GetInstance(Class<TPage> page) {
        Object obj = PageFactory.initElements(DriverContext.Driver, page);
        return page.cast(obj);
    }

    public <TPage extends BasePage> TPage As(Class<TPage> pageInstance) {
        try {
            return (TPage) this;
        } catch (Exception e) {
            e.getStackTrace();
        }
        return null;
    }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}
