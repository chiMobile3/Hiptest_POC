package com.iMobile3.framework.base;


import com.iMobile3.framework.Settings;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;

import java.util.ArrayList;

import java.util.List;
import java.util.stream.Collectors;



public class Alerts extends Base {

    //Method for checking Field Validation Alerts
    public static String FieldValidationError(String FieldName) throws InterruptedException {
        String alertMessage = null;
        DriverContext.waitForPageFullyLoaded(50);
        List<WebElement> Groups = DriverContext.Driver.findElements(By.className("form-group"));
        for (WebElement group : Groups) {
            List<WebElement> Labels = group.findElements(By.tagName("label"));
            for (WebElement label : Labels) {
                //System.out.println(label.getText());
                if (label.getText().toUpperCase().equals(FieldName.toUpperCase())) {
                    WebElement alert = group.findElement(By.className("field-validation-error"));
                    //System.out.println(alert.getText());
                    alertMessage = alert.getText().toUpperCase();
                }
            }
        }


        return alertMessage;
    }


    //Method for checking List of Fields for Validation Alerts
    public static List<String> FieldValidationError() throws InterruptedException {
        List<String> alertMessage = new ArrayList<>();
        DriverContext.waitForPageFullyLoaded(50);
        List<WebElement> warnings = DriverContext.Driver.findElements(By.className("field-validation-error"));
        for (WebElement warning : warnings) //System.out.println(label.getText());
            alertMessage.add(warning.getText().toUpperCase());
        System.out.println(alertMessage);

        System.out.println("\n" + alertMessage + "\n");
        return alertMessage;
    }


    public Boolean FindFieldValidationErrors(List<String> FieldError) throws InterruptedException {
        DriverContext.waitForPageFullyLoaded(50);
        Boolean flag = true;

        for (String matches : FieldValidationError())
            if (!matches.equals(FieldError)) {
                flag = false;
            }
        System.out.println("\n" + flag + "\n");
        return flag;
    }



    //Method for returning the element of the field name
    public static WebElement ReturnFieldElement(String FieldName) throws InterruptedException {
        WebElement fieldName = null;
        DriverContext.waitForPageFullyLoaded(50);
        List<WebElement> Groups = DriverContext.Driver.findElements(By.className("form-group"));
        for (WebElement group : Groups) {
            List<WebElement> Labels = group.findElements(By.tagName("label"));
            for (WebElement label : Labels) //System.out.println(label.getText());
                if (label.getText().toUpperCase().equals(FieldName.toUpperCase())) {
                    fieldName = group.findElement(By.className("form-control"));
                }
        }
        return fieldName;
    }

    //Method for checking the color of the field when an error occurs
    public static boolean IsFieldValidationBorderRed(final WebElement elementFindBy) {
        boolean result = false;
        DriverContext.WaitForElementtVisible(elementFindBy);
        String Hex = BorderColorToHex(elementFindBy);
        if
        (
                Hex.matches("#ae8d8d|#a27473|#975c5b|#a94442|#843534") //These colors are all the one's that I have seen so far. -Chris
        )
            result = true;
        return result;
    }

    private static String BorderColorToHex(final WebElement elementFindBy) {
        String color = elementFindBy.getCssValue("border-color");
        String hex = Color.fromString(color).asHex();
        return hex;
    }


    //Method for checking entire page for Red Box alerts
        //This method will check the entire page for field validation errors with a red box and return a list of the field names.
    private static List<String>CheckAllFieldsForRedBorder() throws InterruptedException {
        List<String> alertMessage = new ArrayList<>();
        DriverContext.waitForPageFullyLoaded(50);
        List<WebElement> warnings = DriverContext.Driver.findElements(By.className("required"));
        for (WebElement warning : warnings)
            if (IsFieldValidationBorderRed(warning)){
                alertMessage.add(warning.findElement(By.xpath(".//preceding-sibling::label")).getText().toUpperCase());
            }
        System.out.println(alertMessage);
        return alertMessage;
    }

    //Method to compare list of error messages passed from Gherkin step to list of required fields error messages
    public static boolean AreFieldsHighlighted(List<String> fieldsRequired) throws InterruptedException {
        DriverContext.waitForPageFullyLoaded(50);
        Boolean result = false;
        List<String> compareList = MakeListUpperCase(fieldsRequired);
        if (compareList.equals(CheckAllFieldsForRedBorder())) {
            result = true;
        }
        //System.out.println(fieldsRequired); //to debug
        return result;
    }


    //Method for checking entire page for Red error message alerts
    //This method will check the entire page for field validation errors with a red font and return a list of the text.
    private static List<String>CheckAllFieldsForValidationMessage() throws InterruptedException {
        List<String> alertMessage = new ArrayList<>();
        DriverContext.waitForPageFullyLoaded(50);
        List<WebElement> warnings = DriverContext.Driver.findElements(By.xpath("//*[@class='help-block' or @class='field-validation-error']"));
        for (WebElement warning : warnings) //System.out.println(label.getText());
            if (IsFieldValidationBorderRed(warning)){
                alertMessage.add(warning.getText().toUpperCase());
            }
        System.out.println("\n" + alertMessage + "From the Page Element " + "\n"); //to debug
        return alertMessage;
    }

    //Method to compare list of required field passed to method to list of required fields on the page
    public static boolean AllFieldsRequired(List<String> requiredMessage) throws InterruptedException {
        DriverContext.waitForPageFullyLoaded(50);
        Boolean result = false;
        List<String> compareList = MakeListUpperCase(requiredMessage);
        if (compareList.equals(CheckAllFieldsForValidationMessage())) {
            result = true;
        }
        //System.out.println("\n" + requiredMessage + "Passing This " + "\n"); //to debug
        return result;
    }


    //Method to UpperCase all values in a string list
    //Todo: move this out of alerts and into a utility class
    public static List<String> MakeListUpperCase(List<String> NewList){
        //NewList.replaceAll(String::toUpperCase);
        List<String> allCapsList= NewList.stream().map(String::toUpperCase).collect(Collectors.toList());
        return allCapsList;
    }

    //Method for checking Page Level Alert(Info, Danger, Warning)
    public static String PageLevelAlert(String WarningType) throws InterruptedException {
        String alertMessage = null;
        String alertType = WarningType.toLowerCase();
        DriverContext.waitForPageFullyLoaded(50);
        List<WebElement> Alerts = DriverContext.Driver.findElements(By.className("alert-" + alertType));
        for (WebElement alert : Alerts) {
            List<WebElement> Warnings = alert.findElements(By.tagName("p"));
            for (WebElement warning : Warnings)
            {
                //System.out.println(warning.getText()); //TO DEBUG
                alertMessage = warning.getText().toUpperCase();
            }
        }
        return alertMessage;
    }

    //Method for checking Toast Alerts
    public static String ToastAlert(String ToastMessage) throws InterruptedException {
//        Settings.Logs.Write("Validating Toast Message: " + ToastMessage);
        String alertMessage = null;
        DriverContext.waitForPageFullyLoaded(500);
        List<WebElement> Alerts = DriverContext.Driver.findElements(By.className("toast-message"));
        for (WebElement alert : Alerts) {
            if (alert.getText().toUpperCase().equals(ToastMessage.toUpperCase()))
                System.out.println(alert.getText());
            alertMessage = alert.getText().toUpperCase();
            DriverContext.WaitForElementNotVisible(alert);
    }
        return alertMessage;
    }

    //Method to check bootstrap errors at the top of a page
    public static String BootstrapTest(String BootStrapMessage) throws InterruptedException {
        WebElement AlertClass = DriverContext.WaitForElementToBeVisibleAndReturn(DriverContext.Driver.findElement(By.className("alert-danger")));
        return BootstrapReturnMethod(BootStrapMessage, AlertClass);
    }

    //Method to check bootstrap errors at the top of a modal
    public static String ModalBootStrapTest(String ModalBootStrapMessage) throws InterruptedException {
        WebElement AlertClass = Browser.FindModal().findElement(By.className("alert-danger"));
        return BootstrapReturnMethod(ModalBootStrapMessage, AlertClass);
    }

    //Helper method for BootstrapTest Methods
    private static String BootstrapReturnMethod(String BootStrapMessage, WebElement AlertClass){
        String LogMessage = BootStrapMessage.replaceAll("\r\n", "|");
//        Settings.Logs.Write("Validating Bootstrap Message: " + LogMessage);
        //Thread.sleep(3000);
        StringBuilder strErrors = new StringBuilder();

        WebElement AlertHeader = AlertClass.findElement(By.tagName("h5"));
        List<WebElement> Alerts = AlertClass.findElements(By.tagName("li"));

        java.util.Iterator<WebElement> i = ((List) Alerts).iterator();
        while (i.hasNext()) {
            WebElement alert = i.next();
            strErrors.append(alert.getText());
            if (i.hasNext()) {
                strErrors.append("\r\n");
            }
        }
        System.out.println("\n"+ AlertHeader.getText() +"\r\n" + strErrors + "\n"); //TO DEBUG
        return (AlertHeader.getText() + "\r\n" + strErrors);
    }







    //Click the ok button of an alert popup
    public static void AcceptAlert(){
        DriverContext.WaitForAlert();
        DriverContext.Driver.switchTo().alert().accept();
    }
    //Clicks the cancel button of an alert popup
    public static void DismissAlert(){
        DriverContext.WaitForAlert();
        DriverContext.Driver.switchTo().alert().dismiss();
    }
    //Get the text from the alert popup
    public static String GetAlertText(){
        DriverContext.WaitForAlert();
        String s = DriverContext.Driver.switchTo().alert().getText();
        return s;

    }
    //Sends a string to the alert input box
    public static void SendTextToAlert(String s){
        DriverContext.WaitForAlert();
        DriverContext.Driver.switchTo().alert().sendKeys(s);
    }

}
