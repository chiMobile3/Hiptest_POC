package com.iMobile3.framework.base;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage extends Base {


    public BasePage() {
        PageFactory.initElements(DriverContext.Driver, this);
    }

    WebDriverWait wait = new WebDriverWait(DriverContext.Driver, 10);

}
