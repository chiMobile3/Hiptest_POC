package com.iMobile3.framework.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Sidebar extends NavPanelBase {

    ///////////Methods//////////////

    public void FindSideBarItem(String Item) throws InterruptedException {
        DriverContext.waitForPageFullyLoaded(50);
        String value = Item;

        WebElement SideBar = DriverContext.Driver.findElement(By.id("side-menu"));
        List<WebElement> ItemList = SideBar.findElements(By.tagName("li"));

        for (WebElement items : ItemList)
            if (items.getText().equals(value)) {
                //System.out.println(items.getText() + " This is the 1st item");
                items.click();
                break;
            }
    }

    public void FindSideBarItem(String Item1, String Item2) throws InterruptedException {

        FindSideBarItem(Item1);
        String value = Item2;

        DriverContext.waitForPageFullyLoaded(50);
        WebElement SideBar = DriverContext.Driver.findElement(By.className("mm-active"));
        List<WebElement> ItemList = SideBar.findElements(By.tagName("li"));
        for (WebElement items : ItemList)
            if (items.getText().equals(value)) {
                //System.out.println(items.getText() + " This is the 2nd item");
                items.click();
                break;
            }
    }

    //Method to get the Heading of the Sidebar Navigation section
    public static String findNameOfModule(){
         return DriverContext.Driver.findElement(By.cssSelector(".sidebar-module")).getText();
    }


}
