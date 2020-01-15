package com.iMobile3.framework.base;

import com.iMobile3.framework.Settings;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.stream.Collectors;

public class DriverContext extends BasePage {

    public static WebDriver Driver;
    public static Browser Browser;

    @FindBy(how = How.ID_OR_NAME, using = "Name")
    public static WebElement txtBoxName;

    public static void setDriver(WebDriver driver) {
        Driver = driver;
    }

    //////////Methods/////////////////////////////////////////////////////////////////

    ////////////////////////////////////////
    public static void WaitForPageToLoad() {
//        Settings.Logs.Write("Waiting for page to load");
        String url = null;

        WebDriverWait wait = new WebDriverWait(Driver, 10);
        JavascriptExecutor jsExecutor = (JavascriptExecutor) Driver;

        ExpectedCondition<Boolean> jsLoad = webDriver -> ((JavascriptExecutor) Driver)
                .executeScript("return document.readyState").toString().equals("complete");

        //Get JS Ready
        boolean jsReady = jsExecutor.executeScript("return document.readyState").toString().equals("complete");

        if (!jsReady)
            wait.until(jsLoad);
        else
            url = Driver.getCurrentUrl();
        Settings.Logs.Write("Page has loaded: " + url);
    }

    public static void WaitForWebelementToBeSelected(final WebElement elementFindBy) {
        WebDriverWait wait = new WebDriverWait(Driver, 10);
        wait.until(ExpectedConditions.elementToBeSelected(elementFindBy));
    }

    public static void WaitForWebelementToBeClickable(final WebElement elementFindBy) {
        WebDriverWait wait = new WebDriverWait(Driver, 15);
        wait.until(ExpectedConditions.elementToBeClickable(elementFindBy));

    }

    public static void WaitForAttributeTextToBePresent(final WebElement elementFindBy) {
        WebDriverWait wait = new WebDriverWait(Driver, 15);
        wait.until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver d) {
                return elementFindBy.getAttribute("value").length() != 0;
            }
        });
    }

    public static void WaitForRedirectedPageToLoadWithThisTitle(String NewTitle) {
        WebDriverWait wait = new WebDriverWait(Driver, 10);
        wait.until(ExpectedConditions.titleContains(NewTitle));
    }


    public static void WaitForElementtVisible(final WebElement elementFindBy) {
        WebDriverWait wait = new WebDriverWait(Driver, 10);
        wait.until(ExpectedConditions.visibilityOf(elementFindBy));
    }

    public static void WaitForTextVisible(final WebElement elementFindBy, String text) {
        WebDriverWait wait = new WebDriverWait(Driver, 10);
        wait.until(ExpectedConditions.textToBePresentInElement(elementFindBy, text));
    }

    public static void WaitForElementNotVisible(final WebElement elementFindBy) {
        WebDriverWait wait = new WebDriverWait(Driver, 15);
        wait.until(ExpectedConditions.invisibilityOf(elementFindBy));
    }

    public static void WaitForURLToChange(String strOldUrl) {
        //String strOldUrl = Driver.getCurrentUrl();
        WebDriverWait wait = new WebDriverWait(Driver, 20);
        wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(strOldUrl)));
    }

    public static boolean WaitIsElementDisplayed(final WebElement elementFindBy) {
        WebDriverWait wait = new WebDriverWait(Driver, 15);
        wait.until(ExpectedConditions.invisibilityOf(elementFindBy));
        return elementFindBy.isDisplayed();
    }

    public static boolean isElementPresent(final WebElement elementFindBy) {
        boolean Flag = false;
        WebDriverWait wait = new WebDriverWait(Driver, 0);
        try {
            elementFindBy.isEnabled();
            //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.valueOf(elementFindBy))));
            Flag = true;
        } catch (Exception ignored) {
            Flag = false;
        }
        return Flag;
    }

    public static String GetPageTitle() {
        // WaitForURLToChange();
        String strActualPageTitle = Driver.getTitle();
        return strActualPageTitle.toUpperCase();
    }

    public static String ListAllErrors(WebElement ErrorHeading, List AllErrors) {
        DriverContext.WaitForElementtVisible(ErrorHeading);
        String strAlert = ErrorHeading.getText();
        StringBuilder strErrors = new StringBuilder();
        List<WebElement> Errors = AllErrors;
/*        for(WebElement Error:Alerts)
            strErrors.append(Error.getText()).append("\r\n");*/
        java.util.Iterator<WebElement> i = ((List) Errors).iterator();
        while (i.hasNext()) {
            WebElement Error = i.next();
            strErrors.append(Error.getText());

            if (i.hasNext()) {
                strErrors.append("\r\n");
            }
        }
        // System.out.println(strAlert +"\r\n" + strErrors);
        return (strAlert + "\r\n" + strErrors);
    }

    public static boolean waitForPageFullyLoaded(int timeoutMs) throws InterruptedException {
        int previous;
        int current = 0;
        int timeSliceMs = 1000;
        do {
            previous = current;
            Thread.sleep(timeSliceMs);
            timeoutMs -= timeSliceMs;
            current = Driver.findElements(By.xpath("//*")).size();
        } while (current > previous && timeoutMs > 0);
        if (timeoutMs > 0) {
            return true;
        }
        return false;
    }

    public static boolean IsDropDownPopulated(final WebElement elementFindBy) {
        WaitForElementtVisible(elementFindBy);
        if (elementFindBy.getText() != null) {
            return true;
        } else {
            return false;
        }
    }

    public static void DropDownEquals(String Text, final WebElement elementFindBy) {
        WaitForElementtVisible(elementFindBy);
        elementFindBy.sendKeys(Text + Keys.ENTER + Keys.TAB);
    }

    public static void SelectDropDown(String Text, final WebElement elementFindBy) {
        WaitForElementtVisible(elementFindBy);
        new Select(elementFindBy).selectByVisibleText(Text);
    }

    //driver.findElement(By.xpath("//div[@role='combox']")).sendKeys("text to select exp: selenium");
    public static void SelectComboBox(String Text, final WebElement elementFindBy) {
        WaitForElementtVisible(elementFindBy);
        //elementFindBy.click();
        //elementFindBy.sendKeys(Text);
        Actions actions = new Actions(Driver);
        actions.moveToElement(elementFindBy);
        actions.click();
        actions.sendKeys(Text);
        //actions.sendKeys(Keys.ARROW_DOWN);
        actions.sendKeys(Keys.ENTER);
        actions.build().perform();
        //actions.clickAndHold();
    }

    public static void ButtonClick(final WebElement elementFindBy) {
        WaitForElementtVisible(elementFindBy);
        WaitForWebelementToBeClickable(elementFindBy);
        elementFindBy.click();
    }

    public static void EnterText(String Text, final WebElement elementFindBy) {
        WaitForElementtVisible(elementFindBy);
        WaitForWebelementToBeClickable(elementFindBy);
        elementFindBy.sendKeys(Text);
    }

    public static WebElement WaitForElementToBeVisibleAndReturn(final WebElement elementFindBy) {
        WebDriverWait wait = new WebDriverWait(Driver, 15);
        wait.until(ExpectedConditions.visibilityOf(elementFindBy));
        return elementFindBy;
    }

    public static void WaitAndVerifyTextEntry(String strValue, final WebElement elementFindBy) {
        WebDriverWait wait = new WebDriverWait(Driver, 15);
        wait.until(ExpectedConditions.textToBePresentInElementValue(elementFindBy, strValue));
    }



    public static boolean WaitAndVerifyDropDownSelection(String strValue, final WebElement elementFindBy) {
        Select select = new Select(elementFindBy);
        String SelectedValue = select.getFirstSelectedOption().getText();
        if (SelectedValue.equals(strValue)) {
            return true;
        } else {
            return false;
        }
    }

    public static void WaitForAlert (){
        try {
            Thread.sleep(250);
            WebDriverWait wait = new WebDriverWait(Driver, 5);
            wait.until(ExpectedConditions.alertIsPresent());
            Alert alert = Driver.switchTo().alert();
        } catch (NoAlertPresentException | InterruptedException e) {
            e.printStackTrace();
            e.getMessage();
        }

    }

    public static void drawBorder(final WebElement elementFindBy) {
        //WebElement element_node = driver.findElement(By.xpath(xpath));
        JavascriptExecutor jse = (JavascriptExecutor) Driver;
        jse.executeScript("arguments[0].style.border='3px solid red'", elementFindBy);
    }

    public static void ScrollToEndOfPage() throws InterruptedException {
        ScrollUntilDoneLoading();
        waitForPageFullyLoaded(50);
    }

    public static void ScrollUntilDoneLoading(){
        WebDriverWait wait = new WebDriverWait(Driver, 5);
        JavascriptExecutor js = (JavascriptExecutor) Driver;
        By dataLocator = By.cssSelector(".panel");

        int dataSize = Driver.findElements(dataLocator).size();
        while (true){
            js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
            By loadingLocator = By.cssSelector("span[data-bind='visible: Loading()']");
            wait.ignoring(NoSuchElementException.class)
                    .until(ExpectedConditions.invisibilityOfElementLocated(loadingLocator));

            if (Driver.findElements(dataLocator).size()== dataSize)
                break;
            dataSize = Driver.findElements(dataLocator).size();
        }

    }



    //Method to scroll element found into view before performing action
    public static void ScrollElementIntoView(final WebElement elementFindby) {
        ((JavascriptExecutor)Driver).executeScript("arguments[0].scrollIntoView(true);", elementFindby);
    }

    //Method to UpperCase all values in a string list
    public static List<String> MakeListUpperCase(List<String> NewList){
        return NewList.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());
    }


    //Method to Find and return the active Webelement
    public static WebElement ActiveElement() {
        WebElement currentElement = Driver.switchTo().activeElement();
        return currentElement;
    }



    public static void ExecuteJavaScript(String scripts)
    {
        JavascriptExecutor js = (JavascriptExecutor)Driver;
        js.executeScript(scripts);
    }


    public static void WaitForAjax() throws InterruptedException {
        Settings.Logs.Write("Waiting for Ajax to finish");
        JavascriptExecutor executor = (JavascriptExecutor)Driver;
        if((Boolean) executor.executeScript("return window.jQuery != undefined")){
            while(!(Boolean) executor.executeScript("return jQuery.active == 0")){
                Thread.sleep(1000);
            }
        }
    }


    public static void HoverOverElement(final WebElement elementFindBy) {
        Actions action = new Actions(Driver);
        action.moveToElement(elementFindBy).build().perform();
    }

    public static void WaitForMouseToChange() {
 /*       WebDriverWait wait = new WebDriverWait(Driver, 10);
        wait.until((ExpectedCondition<Boolean>) d -> {
            return (Driver.findElement(By.cssSelector("body")).getCssValue("cursor")).equalsIgnoreCase("not-allowed");
        });*/
        System.out.println(Driver.findElement(By.cssSelector("body")).getCssValue("cursor")+ " -----testing");
    }


    //This method will search for all buttons on page by name
    public static WebElement ReturnButtonElement(String btnName) throws InterruptedException {

        String value = btnName.toUpperCase();
        DriverContext.waitForPageFullyLoaded(50);
        List<WebElement> Buttons = DriverContext.Driver.findElements(By.className("btn"));
        //Buttons.forEach(button -> button.getText().trim().toUpperCase().compareToIgnoreCase(value) );

        for (WebElement button : Buttons)
            if (button.getText().trim().toUpperCase().equals(value)) {
                //System.out.println(button.getText() + " This is the button"); //TO DEBUG
                return button;
        }
        return null;
    }


}
