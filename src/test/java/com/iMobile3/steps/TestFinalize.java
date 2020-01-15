package com.iMobile3.steps;

import com.iMobile3.framework.Settings;
import com.iMobile3.framework.base.DriverContext;
import com.iMobile3.framework.base.FrameworkInitialize;
import cucumber.api.java.After;


//import static com.iMobile3.framework.config.Settings.URLSite;

public class TestFinalize extends FrameworkInitialize {

    @After
    public void TearDown() throws Exception {

        //Close the browser
        DriverContext.Browser.Close();
//        Settings.Logs.Write("Closed " + Settings.BrowserType);
//        Settings.Logs.Write("End of scenario");
    }

}
