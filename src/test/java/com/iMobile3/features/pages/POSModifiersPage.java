package com.iMobile3.features.pages;

import com.iMobile3.framework.base.DriverContext;
import com.iMobile3.framework.base.POSLocationLVLBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class POSModifiersPage extends POSLocationLVLBase {

    public static void placeHoldertextbox(String textBoxName,String text)
    {

        List<WebElement> Groups = DriverContext.Driver.findElements(By.className("panel-body"));
        for (WebElement group : Groups) {
            List<WebElement> textBox = group.findElements(By.cssSelector("input[type=text]"));
            for (WebElement textitem : textBox) {//System.out.println(label.getText());
                if (textitem.getAttribute("placeholder").toUpperCase().equals(textBoxName.toUpperCase())) {
                    textitem.sendKeys(text);
                    System.out.println("\n"+text +" is entered in the field" + textitem.getAttribute("placeholder") + "\n");
                }
            }
        }

    }
}
