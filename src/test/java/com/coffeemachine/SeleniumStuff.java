package com.coffeemachine;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.sql.Driver;

public class SeleniumStuff {

    public static void main(String[] args) {
        // declaration and instantiation of objects/variables
        System.setProperty("webdriver.chrome.driver","C:\\Users\\DAntoine\\Desktop\\Temp\\hps-cucumber-java-master\\Drivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        String baseUrl = "https://previewportal.imobile3.com/login";
        String expectedTitle = "Log In | iMobile3";
        String actualTitle = "";
        // launch Fire fox and direct it to the Base URL
        driver.get(baseUrl);


    }

    public static void LaunchMainWebSite() {

    }

    public static void InputLogin(String arg0) {
        //WebDriver driver =
    }

    public static void InputPassword(String arg1) {
    }
}