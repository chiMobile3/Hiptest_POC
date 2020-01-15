package com.iMobile3.framework.base;

import com.iMobile3.framework.ConfigReader;
import com.iMobile3.framework.Settings;

import cucumber.api.DataTable;
//import org.apache.poi.ss.formula.functions.Replace;
//import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.iMobile3.framework.base.DriverContext.*;
import static com.iMobile3.framework.base.TablesPOS.ListAllTextInPanelHeading;
import static com.iMobile3.framework.base.TablesPOS.makePanelList;
//import static com.iMobile3.framework.controls.TextBoxEnter.comboBoxElements;
//import static java.util.stream.Collectors.toCollection;
//import static java.util.stream.Collectors.toList;


public class Browser extends Base {

    public static Integer initialItemLibraryCategoriesCount;
    public static String linkHREF;

    private WebDriver _driver;

    public Browser(WebDriver driver) {
        _driver = driver;
    }

    public BrowserType Type;

    public static void PageRefresh() {
        Driver.navigate().refresh();
        //please remove this try catch and add it as a separate method. we wouldn't want to click every alert. we can discuss
        try{
            Driver.switchTo().alert().accept();
        } catch(Exception e){
            System.out.println("\n"+"This page does not require confirmation to refresh"+"\n" + e);
        }

    }


    public void GoToUrl(String url) {
        _driver.get(url);
    }

    public void GoToThisURL(String strKey) throws IOException, InterruptedException {
        ConfigReader.URLSettings(strKey);
        _driver.get(Settings.PageName);
        DriverContext.waitForPageFullyLoaded(3);
        if(strKey.toUpperCase().equalsIgnoreCase("ITEMLIBRARY")){
            //Todo: Add scroll if items list is greater than 100
            initialItemLibraryCategoriesCount = DriverContext.Driver.findElements(By.cssSelector(".panel-heading")).size();
        }

    }

    public String GetWebsiteTitleString() {
        String strURL = _driver.getTitle();
        return strURL;
    }

    public String ThisURL() {
        String strURL = _driver.getCurrentUrl();
        return strURL;
    }

    public void Close() {
        _driver.close();
    }

    //Import file with sendkeys method
    public static String ImportFile(String FileName) {
       /* String dirPath = ToUploadPath + File.separator + FileName;
        String FileLocation = dirPath
        //String location = FileLocation;
        return FileLocation;*/
        return ToUploadPath + File.separator + FileName;
    }

    // will return 'True' if the document is available in the folder else 'false'.
    public boolean isFileDownloaded(String downloadPath, String fileName) {
        boolean flag = false;
        File dir = new File(downloadPath);
        File[] dir_contents = dir.listFiles();

        for (int i = 0; i < dir_contents.length; i++) {
            if (dir_contents[i].getName().equals(fileName))
                flag = true;
        }
        return flag;
    }

    /* Get the latest file from a specific directory*/
    public static File getLatestFilefromDir() {
        String dirPath = DownloadPath;
        File dir = new File(dirPath);
        File[] files = dir.listFiles();
        if (files == null || files.length == 0) {
            return null;
        }
        File lastModifiedFile = files[0];
        for (int i = 1; i < files.length; i++) {
            if (lastModifiedFile.lastModified() < files[i].lastModified()) {
                lastModifiedFile = files[i];
            }
        }
        return lastModifiedFile;
    }

    public static String getDownloadedDocumentName(String sampleFileName) {
        String downloadDir = DownloadPath;
        //String fileExtension = FilenameUtils.getExtension("bar.exe");
        String downloadedFileName = null;
        boolean valid = true;
        boolean found = false;
        String fileExtension = "";

        int i = sampleFileName.lastIndexOf('.');
        if (i > 0) {
            fileExtension = sampleFileName.substring(i + 1);
        }
        //default timeout in seconds
        long timeOut = 20;
        try {
            Path downloadFolderPath = Paths.get(downloadDir);
            WatchService watchService = FileSystems.getDefault().newWatchService();
            downloadFolderPath.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);
            long startTime = System.currentTimeMillis();
            do {
                WatchKey watchKey;
                watchKey = watchService.poll(timeOut, TimeUnit.SECONDS);
                long currentTime = (System.currentTimeMillis() - startTime) / 1000;
                if (currentTime > timeOut) {
//                    Settings.Logs.Write("Download operation timed out.. Expected file was not downloaded");
                    //System.out.println("Download operation timed out.. Expected file was not downloaded");
                    return downloadedFileName;
                }
                for (WatchEvent<?> event : watchKey.pollEvents()) {
                    WatchEvent.Kind kind = event.kind();
                    if (kind.equals(StandardWatchEventKinds.ENTRY_CREATE)) {
                        String fileName = event.context().toString();
//                        Settings.Logs.Write("New File Created:" + fileName);
                        //System.out.println("New File Created:" + fileName);
                        if (fileName.endsWith(fileExtension)) {
                            downloadedFileName = fileName;
                            Settings.Logs.Write("Downloaded file found with extension " + fileExtension + ". File name is " +

                                    fileName);
                            /*System.out.println("Downloaded file found with extension " + fileExtension + ". File name is " +

                                    fileName);*/
                            Thread.sleep(500);
                            found = true;
                            break;
                        }
                    }
                }
                if (found) {
                    return downloadedFileName;
                } else {
                    currentTime = (System.currentTimeMillis() - startTime) / 1000;
                    if (currentTime > timeOut) {
                        Settings.Logs.Write("Failed to download expected file");
                        //System.out.println("Failed to download expected file");
                        return downloadedFileName;
                    }
                    valid = watchKey.reset();
                }
            } while (valid);
        } catch (InterruptedException e) {
            Settings.Logs.Write("Interrupted error - " + e.getMessage());
            //System.out.println("Interrupted error - " + e.getMessage());
            e.printStackTrace();
        } catch (NullPointerException e) {
            Settings.Logs.Write("Download operation timed out.. Expected file was not downloaded");
            //System.out.println("Download operation timed out.. Expected file was not downloaded");
        } catch (Exception e) {
            Settings.Logs.Write("Error occured - " + e.getMessage());
            //System.out.println("Error occured - " + e.getMessage());
            e.printStackTrace();
        }
        return downloadedFileName;
    }

    //This method will search for all buttons on page by name
/*    public static void ClickButton(String btnName) throws InterruptedException {

        String value = btnName.toUpperCase();
        DriverContext.waitForPageFullyLoaded(50);
        List<WebElement> Buttons = DriverContext.Driver.findElements(By.className("btn"));

        for (WebElement button : Buttons)
            if (button.getText().trim().toUpperCase().equals(value)) {
                //System.out.println(button.getText() + " This is the button"); //TO DEBUG
                try {
                    button.click();
                    DriverContext.WaitForAjax();
                    break;
                } catch (Exception e) {
                    System.out.println("Unable to click " + btnName + " due to the following error: \n" + e);
                    break;
                }
            }
    }*/

    public static Boolean ClickButton(String btnName) throws InterruptedException {
        DriverContext.waitForPageFullyLoaded(50);
        Boolean flag = null;
        List<WebElement> Buttons = DriverContext.Driver.findElements(By.className("btn"));
         Optional<WebElement> w =  Buttons.stream().filter(element -> element.getText().trim().substring(0, posOfNewLine(element.getText().trim())).toUpperCase().equalsIgnoreCase(btnName.toUpperCase()))
                    //.forEach(DriverContext::drawBorder);//TO DEBUG
                    .findFirst();
        if(w.isPresent()){
            ScrollElementIntoView(w.get());
            //DriverContext.drawBorder(w.get());
            //w.get().click();
            ((JavascriptExecutor) Driver).executeScript("arguments[0].click();",w.get() );
            DriverContext.WaitForAjax();
            flag = true;
        }else{
            try{DriverContext.ButtonClick(w.get());}
            catch (Exception e) {
                System.out.println("Unable to click the \"" + btnName + "\" due to the following error: \n" + e);
                flag = false;
            }
        }
        return flag;
    }

    public static Boolean ClickButtonByLogoName(String buttonName, String buttonLogoName) throws InterruptedException {
        DriverContext.waitForPageFullyLoaded(50);
        Boolean flag = false;
        List<WebElement> Buttons = DriverContext.Driver.findElements(By.className("btn"));
        String s = buttonLogoName.toUpperCase();
        String t = "";
        for (int i = 0; i < Buttons.size(); i++)
        {
            t = Buttons.get(i).getAttribute("id").toUpperCase();
            if (t.contains(s)) {
                WebElement tempButton = Driver.findElement(By.id(Buttons.get(i).getAttribute("id")));
                try {
                    tempButton.click();
                    DriverContext.WaitForAjax();
                    flag = true;
                } catch (Exception e) {
                    System.out.println("Unable to click " + buttonName + " due to the following error: \n" + e);
                }
            }
        }
        return flag;
    }



    //Todo: Create method to verify the title of the currently shown modal. Right now there is no way to be inclusive of all modals as they all have different methods of display
/*    //Method to verify the title of specified modal pop-up: This will only work for a one line title...not span with h4 tags
    public static void verifyTitleOfModal(String modalName) throws InterruptedException {
        DriverContext.waitForPageFullyLoaded(5);
        String text;
*//*       // text = (String) js.executeScript("return arguments[0].textContent.trim();", FindModal().findElement(By.cssSelector(".modal-title")));
        WebElement activeTitle = FindModal().findElement(By.cssSelector("div:not([style='display: none;']"));
        List<WebElement> modalTitles = activeTitle.findElements(By.cssSelector(".modal-title"));
        //System.out.println(text); //TO DEBUG
        for (WebElement mTitle : modalTitles) {*//*
            //if (text.toUpperCase().equalsIgnoreCase(modalName.toUpperCase())) {
*//*          if(mTitle.getAttribute("style")!=("display: none;")){
            System.out.println("The title matches " + mTitle.getAttribute("innerHTML"));
            drawBorder(mTitle);
            return true;
        }*//*
            WebElement mTitle = Driver.findElement(By.cssSelector("#deleteItemsSuccess")).findElement(By.cssSelector("h4"));
        WebElement mTitle2 = FindModal().findElement(By.cssSelector("div[data-bind='visible:']"));
            String text2 = (String) js.executeScript("return arguments[0].textContent.trim();", mTitle);
            //return false;
            System.out.println("\n" + mTitle.getAttribute("innerHTML") + " <= innerHTML");
            System.out.println("\n" + mTitle.getAttribute("style") + " <= style");
            System.out.println("\n" + text2 + " <= textContent");
        //}
    }*/

    //Method to click on the button of visible modal pop up (prompt)
    public static void ClickButtonOnModal(String btnName) throws InterruptedException {
        DriverContext.waitForPageFullyLoaded(5);
        String btnElement = null;

        switch (btnName.toUpperCase()) {
            case "×":
                btnElement = "button";
                break;
            case "CANCEL":
                btnElement = "a";
                break;
            case "X":
                btnElement = "button";
                btnName = "×";
                break;
            case "CHOOSE FILE":
                btnElement = "input";
                break;
            default:
                btnElement = ".btn";
                break;
        }
        clickElementOnModal(btnElement, btnName);
    }

    //Method to click on the button of visible modal pop up (prompt)
    public static void ClickLinkOnModal(String btnName) throws InterruptedException {
        DriverContext.waitForPageFullyLoaded(5);
        String btnElement = "a";
        clickElementOnModal(btnElement, btnName);
    }

    //Method to click on the icon of visible modal pop up (prompt)
    public static void ClickIconOnModal(String btnName) throws InterruptedException {
        DriverContext.waitForPageFullyLoaded(5);
        String btnElement = null;
        switch (btnName.toUpperCase()) {
            case "SEARCH":
                btnElement = ".fa-search";
                break;
            default:
                btnElement = ".fa";
                break;
        }
        clickElementOnModal(btnElement, btnName);
    }



    //Todo Solve this computed value problem
public static void getComputedValue() throws InterruptedException {
    //String script = "return window.getComputedStyle(document.querySelector('#validationError'),':before').getPropertyValue('content')";
    //String script = "return window.getComputedStyle(element, pseudoElement)";
/*    JavascriptExecutor js = (JavascriptExecutor) Driver;
    String content = (String) js.executeScript(script);*/
WebElement btn = FindModal().findElement(By.id("fileInput"));
//btn.click();
    //String content = (String)  ((JavascriptExecutor) Driver).executeScript("return window.getComputedStyle(arguments[0],':before').getPropertyValue('content');",btn);
    String content = (String)  ((JavascriptExecutor) Driver).executeScript("return window.getComputedStyle(arguments[0],'null').content;",btn);
    String content2 = btn.getCssValue("Contents:");

    String script = "return window.getComputedStyle(arguments[0]).getPropertyValue('content');";
    //content; //(String)((JavascriptExecutor) Driver).executeScript(script, btn);

    System.out.println(content);
    System.out.println(content2);
}




    //Helper method to Clicking a button and clicking a link on a modal
    private static void clickElementOnModal(String btnElement, String btnName) throws InterruptedException {
        String btnText;
        List<WebElement> Buttons = FindModal().findElements(By.cssSelector(btnElement));
        for (WebElement button : Buttons) {
            btnText = (String) ((JavascriptExecutor) Driver).executeScript("return arguments[0].textContent.trim();", button);
            System.out.println(btnText); //TO DEBUG
            if (!(btnElement.contains(".fa"))) {
                if (btnText.toUpperCase().equalsIgnoreCase(btnName.toUpperCase())) {
                    drawBorder(button); //TO DEBUG
                    ((JavascriptExecutor) Driver).executeScript("arguments[0].click();", button);
                    DriverContext.WaitForAjax();
                    break;
                }
            } else {
                drawBorder(button); //TO DEBUG
                //((JavascriptExecutor) Driver).executeScript("arguments[0].click();", button);
                button.click();
                DriverContext.WaitForAjax();
                break;
            }
        }
    }


    public static WebElement FindModal() throws InterruptedException {
        DriverContext.waitForPageFullyLoaded(5);
        String className = "in";
        List<WebElement> Modals = Driver.findElements(By.cssSelector(".modal-dialog"));
        for (WebElement modal : Modals) {
            WebElement parentModal = (WebElement) ((JavascriptExecutor) Driver).executeScript("return arguments[0].parentNode;", modal);
            DriverContext.drawBorder(parentModal);
            if (hasClass(parentModal, className)) {
                return modal;
            }
        }
        return null;
    }

    public static boolean hasClass(WebElement element, String htmlClass) {
        String[] classes = element.getAttribute("class").split("\\s+");
        //String modalTitle;
        if (classes != null) {
            for (String classAttr : classes) {
                //modalTitle = (String) ((JavascriptExecutor) Driver).executeScript("return arguments[0].textContent.trim();", element.findElement(By.cssSelector(".modal-title")));
                if (classAttr.contains(htmlClass)) {

                    return true;
                }
            }
        }
        return false;
    }


    //This method will verify that the Page Name is contained within the current page title
    public static boolean verifyPage(String pageName) throws InterruptedException {
        DriverContext.waitForPageFullyLoaded(15);
        Boolean flag = null;
        String pageTitle = DriverContext.Driver.getTitle().toLowerCase().replaceAll(" ", "");
        String pageNameup = pageName.toLowerCase().replaceAll(" ", "");
        if (pageTitle.contains(pageNameup)) {
            flag = true;
            System.out.println(pageTitle);
        } else {
            flag = false;
        }
        return flag;
    }

    //Method to identify specified section (panel)
/*    public static Boolean VerifyCategoryAdded(List<String> strSectionName) throws Exception {
        DriverContext.waitForPageFullyLoaded(15);
        Boolean flag = null;
        //List<WebElement> Panels = DriverContext.Driver.findElements(By.className("panel-title"));
        List<WebElement> Panels = DriverContext.Driver.findElements(By.cssSelector(".panel-heading"));
        for (WebElement panel : Panels) {
            if (MakeListUpperCase(strSectionName).equals(MakeListUpperCase(ListAllTextInPanelHeading(panel)))) {
                flag = true;
                //System.out.println("Yes"); //TO DEBUG
                break;
            } else {
                flag = false;
                //System.out.println("No"); //TO DEBUG
            }

        }
        System.out.println(strSectionName); //TO DEBUG
        return flag;

    }*/


    public static Boolean VerifyCategoryAdded(List<String> strSectionName) throws Exception {
        DriverContext.waitForPageFullyLoaded(15);
        Boolean flag = null;
        List<WebElement> Panels = DriverContext.Driver.findElements(By.cssSelector(".panel-heading"));
        if(strSectionName.size() == 1) {
            for (WebElement panel : Panels) {
                if (MakeListUpperCase(strSectionName).equals(MakeListUpperCase(ListAllTextInPanelHeading(panel)))) {
                    flag = true;
                    //System.out.println("Yes"); //TO DEBUG
                    break;
                } else {
                    flag = false;
                    //System.out.println("No"); //TO DEBUG
                }
            }
        } else{
            if(DriverContext.MakeListUpperCase(strSectionName).equals(makePanelList(Panels))) flag = true;

            System.out.println(makePanelList(Panels)); //TO DEBUG
            System.out.println(DriverContext.MakeListUpperCase(strSectionName)); //TO DEBUG

        }
        //System.out.println(strSectionName); //TO DEBUG
        //System.out.println(ListAllTextInPanelHeading(panel));
        return flag;

    }


    //Method to Click any button under given section (panel)
/*    public static void ClickButtonUnderSection(String strBtnName, List<String> strSectionName) throws Exception {
        //Must scroll in order to show full list
        DriverContext.ScrollToEndOfPage();
        List<WebElement> Panels = DriverContext.Driver.findElements(By.cssSelector(".panel-heading"));
        Panels.stream()
                .filter(p-> MakeListUpperCase(ListAllTextInPanelHeading(p)).equals(MakeListUpperCase(strSectionName)))
                .forEach(p-> SectionsButtonClick(strBtnName,p));
    }*/
    public static void ClickButtonUnderSection(String strBtnName, List<String> strSectionName) throws Exception {
        //Must scroll in order to show full list
        DriverContext.ScrollToEndOfPage();
        SectionsButtonClick(strBtnName, strSectionName);
    }

    private static void SectionsButtonClick(String strBtName, List<String> strSection)  {
        WebElement Section = TablesPOS.FindSection(strSection);
        DriverContext.drawBorder(Section);

        List<WebElement> Buttons = Section.findElements(By.className("btn"));
        for (WebElement button : Buttons)
            if (button.getText().toUpperCase().equals(strBtName.toUpperCase())) {
                //System.out.println(button.getText()); //TO DEBUG
                ScrollElementIntoView(button);
                button.click();
                break;
            }
    }


    //Helper Method to click the button under a given section
/*    private static void SectionsButtonClick(String strBtName, WebElement elementFindBy)  {
        WebElement ParentOfElementFindBy = elementFindBy.findElement(By.xpath("./.."));
        DriverContext.drawBorder(elementFindBy);
        DriverContext.drawBorder(ParentOfElementFindBy);
        List<WebElement> Buttons = ParentOfElementFindBy.findElements(By.className("btn"));
        for (WebElement button : Buttons)
            if (button.getText().toUpperCase().equals(strBtName.toUpperCase())) {
                //System.out.println(button.getText()); //TO DEBUG
                ScrollElementIntoView(button);
                button.click();
                break;
            }
    }*/


    public static List<String> StringToList(String str) throws Exception {
        List<String> f = Stream.of(str.replaceAll("-", ","))
                .collect(Collectors.toList());
        //System.out.println(f);
        return f;
    }


    //Method to click on item in an already activated dropdown list within tag <a
    //Updated to select by innerHTML or getText
    public static void SelectItemFromActiveDropdownList(String strSelection) throws Exception {
        DriverContext.waitForPageFullyLoaded(10);
        WebElement currentElement = DriverContext.ActiveElement().findElement(By.xpath("../.."));
        WebElement drpDownList = currentElement.findElement(By.tagName("ul"));
        List<WebElement> Items = drpDownList.findElements(By.tagName("a"));

        Optional<WebElement> w =  Items.stream().filter(element -> element.getAttribute("innerHTML").trim().toUpperCase().equalsIgnoreCase(strSelection.toUpperCase()))
                //.forEach(DriverContext::drawBorder);//TO DEBUG
                .findFirst();
        if(w.isPresent()){
            (w.get()).click();
            DriverContext.WaitForAjax();
            linkHREF = w.get().getAttribute("href");

        }else{
            try{
                Optional<WebElement>  x =  Items.stream().filter(element -> element.getText().trim().substring(0, posOfNewLine(element.getText().trim())).toUpperCase().equalsIgnoreCase(strSelection.toUpperCase()))
                        .findFirst();
                (x.get()).click();
                DriverContext.WaitForAjax();
                linkHREF = x.get().getAttribute("href");
            }
            catch (Exception e) {
                System.out.println("Unable to click the \"" + strSelection + "\" due to the following error: \n" + e);

            }
        }



/*        for (WebElement item : Items)
            if (item.getAttribute("innerHTML").toUpperCase().equals(strSelection.toUpperCase())) {
                //System.out.println(item.getAttribute("innerHTML")); //TO DEBUG
                drawBorder(item); //TO DEBUG
                item.click();
                break;
            }*/
    }


    public static void ClickRadioButtonNextTo(String strCategory) throws InterruptedException {
        String value = strCategory;
        DriverContext.waitForPageFullyLoaded(50);
        List<WebElement> Categories = DriverContext.Driver.findElements(By.className("category"));
        List<WebElement> Categories1 = DriverContext.Driver.findElements(By.className("radio"));
        if (Categories.size() > 0) {
            for (WebElement category : Categories)
                if (category.getText().equals(strCategory)) {
//                    System.out.println(category.getText());
                    category.findElement(By.cssSelector("input[type=radio]")).click();
                }
        } else {
            for (WebElement category1 : Categories1)
                if (category1.getText().equals(strCategory)) {
//                    System.out.println(category1.getText());
                    category1.findElement(By.cssSelector("input[type=radio]")).click();
                }
        }
    }

    public static void ExpandCategory(String strCategory) throws InterruptedException {
        DriverContext.waitForPageFullyLoaded(50);
        List<WebElement> Categories = DriverContext.Driver.findElements(By.className("category"));
        for (WebElement category : Categories)
            if (category.getText().equals(strCategory)) {
                //System.out.println(category.getText());
                drawBorder(category.findElement(By.cssSelector("span"))); //TO DEBUG
                //((JavascriptExecutor) Driver).executeScript("arguments[0].click();", category.findElement(By.cssSelector("span")));
                category.findElement(By.cssSelector("span")).click();
                DriverContext.WaitForAjax();
                break;
            }
    }

    public static void clickDownLoadLink(String element) throws InterruptedException {
        DriverContext.waitForPageFullyLoaded(50);
        List<WebElement> Links = DriverContext.Driver.findElements(By.className("fa fa-download"));
        for (WebElement link : Links) {
            if (link.getText().equals(element)) {
                link.click();
            }
        }
    }

    //Method to enter specific text into specified dropdown as long as the dropdown has a label on a form group (The dropdown is not referenced by the default value)
    public static void EnterTextIntoSearchBoxOfDropDown(String enterText, String fieldName) throws InterruptedException {
        DriverContext.waitForPageFullyLoaded(5);
        ReturnSelectElementToClick(fieldName).click();
        //drawBorder(ActiveElement()); //To Debug
        ActiveElement().sendKeys(enterText);
        System.out.println("Typing into the search Bar");
    }

    //Method for returning the element of the field name for Select dropdown (Only when field name is present in a form group)
    public static WebElement ReturnSelectElementToClick(String FieldName) throws InterruptedException {
        WebElement fieldName = null;
        DriverContext.waitForPageFullyLoaded(50);
        List<WebElement> Groups = DriverContext.Driver.findElements(By.className("form-group"));
        for (WebElement group : Groups) {
            List<WebElement> Labels = group.findElements(By.cssSelector("div > label"));
            for (WebElement label : Labels) //System.out.println(label.getText());
                if (label.getText().toUpperCase().equals(FieldName.toUpperCase())) {
                    fieldName = group.findElement(By.cssSelector(".selection"));
                    drawBorder(fieldName);
                }
        }
        return fieldName;
    }

/*    //Method to ensure list passed matches list of results from dropdown with searchbox once the search field has already been filled with an item to search for
    public static Boolean VerifyListInDrpDownContainsOnlyItem(List<String> strItems) {
        List<WebElement> resultsElement = Driver.findElements(By.cssSelector(".select2-results__option"));
        resultsElement.forEach(DriverContext::drawBorder); // TO DEBUG
        resultsElement.forEach(result -> resultsList.add(result.getText().toUpperCase()));
        System.out.println("\nthis list --- " + resultsList); // TO DEBUG
        System.out.println("\nthis list passed --- " + strItems); // TO DEBUG
        if (MakeListUpperCase(strItems).equals(resultsList)) {
            return true;
            //System.out.println("Yes"); //TO DEBUG
        } else {
            return false;
            //System.out.println("No"); //TO DEBUG
        }
    }*/

    private static List<String> resultsList = new ArrayList<>();
    private static ArrayList<String> convertedStrItems = new ArrayList<>(Arrays.asList());

    //Method to ensure list passed matches list of results from dropdown with searchbox once the search field has already been filled with an item to search for
    public static Boolean VerifyListInDrpDownContainsOnlyItem(String strItems) {
        List<WebElement> resultsElement = Driver.findElements(By.cssSelector(".select2-results__option"));
        List<String> strList = Stream.of(strItems).collect(Collectors.toList());
        resultsElement.forEach(DriverContext::drawBorder); // TO DEBUG
        resultsElement.forEach(result -> resultsList.add(result.getText().toUpperCase()));
        System.out.println("\nthis list --- " + resultsList); // TO DEBUG
        System.out.println("\nthis list passed --- " + strList); // TO DEBUG
        if (MakeListUpperCase(strList).equals(resultsList)) {
            return true;
            //System.out.println("Yes"); //TO DEBUG
        } else {
            return false;
            //System.out.println("No"); //TO DEBUG
        }
    }

    //Overloaded method to ensure list passed matches list of results from dropdown with searchbox once the search field has already been filled with an item to search for
    public static Boolean VerifyListInDrpDownContainsOnlyItem(DataTable strItems) {
        List<WebElement> resultsElement = Driver.findElements(By.cssSelector(".select2-results__option"));
        List<String> convertedDatTable = strItems.asList(String.class);
        convertedDatTable.forEach(result -> convertedStrItems.add(result.toUpperCase()));
        resultsElement.forEach(DriverContext::drawBorder); // TO DEBUG
        resultsElement.forEach(result -> resultsList.add(result.getText().toUpperCase()));
        System.out.println("\nthis list Results from search --- " + resultsList); // TO DEBUG
        System.out.println("\nthis list converted DataTable --- " + convertedStrItems); // TO DEBUG

        if (resultsList.equals(convertedStrItems)) {
            return true;
            //System.out.println("Yes"); //TO DEBUG
        } else {
            return false;
            //System.out.println("No"); //TO DEBUG
        }
    }


    //Method to get the value of the cursor: is it a pointer(hand), not-allowed(Stop symbol)?
    public static String MouseCursor(String strButtonName) throws InterruptedException {
        DriverContext.waitForPageFullyLoaded(10);
        System.out.println("\n"+ ReturnButtonElement(strButtonName).getCssValue("cursor") + " <-----cursor"); //TO DEBUG
        return ReturnButtonElement(strButtonName).getCssValue("cursor");
    }




    //Method to click on the button of visible modal pop up (prompt)
    public static void ClickLinkByName(String linkName) throws InterruptedException {
        DriverContext.waitForPageFullyLoaded(5);
        WebElement Links = Driver.findElement(By.linkText(linkName));
        Links.click();
        }


    //Method to click on combo box with a default value listed: This will then allow the results to be displayed
//    public static void clickOnComboBoxWithCurrentValueof(String strComboBoxValue) throws InterruptedException {
//        List<WebElement> comboBox = comboBoxElements(strComboBoxValue, "innerHTML");
//        comboBox.stream().forEach(element -> element.click());
//    }

    //Method to select the specified Item from a combo box with the listed default value
//    public static void selectListedItemsInComboBox(String strComboBox, String strChoice) throws InterruptedException {
//        clickOnComboBoxWithCurrentValueof(strComboBox);
//        List<WebElement> resultsElement = Driver.findElements(By.cssSelector(".select2-results__option"));
//        List<WebElement> resultsElement2 = Driver.findElements(By.cssSelector(".select2-selection__rendered"));
//        if(resultsElement.size()>0){
//            try {
//                resultsElement.stream().filter(element -> element.getText().equalsIgnoreCase(strChoice))
//                        //.forEach(DriverContext::drawBorder);
//                        .forEach(WebElement::click);
//            } catch (StaleElementReferenceException e) {
//                System.out.println("Debug");
//            }
//        }
//        else if(resultsElement2.size()>0) {
//            resultsElement = Driver.findElements(By.cssSelector(".select2-selection__rendered"));
//            try {
//                Select appType = new Select(Driver.findElement(By.id("productTypePicker")));
//                appType.selectByVisibleText(strChoice);
//            } catch (StaleElementReferenceException e) {
//                System.out.println("Debug");
//            }
//        }
//    }

    //Method to return the position of a newline break if present (Used with substring)
    public static Integer posOfNewLine(String strNewline) {
        StringBuffer str = new StringBuffer(strNewline);
        int pos;
        if (str.indexOf("\n") > 0) {
            pos = str.indexOf("\n");
            System.out.println("new line found at " + pos + 1 + "\n");//TO DEBUG
        } else {
            pos = str.length();
        }
        return pos;
    }


    //Method to verify current Modal title
    public static String verifyModalTitle() throws InterruptedException {
        String strModalTitle = FindModal().findElement(By.cssSelector("h4")).getText();
        System.out.println("\nThe Modal Title is: "+ strModalTitle +"\n");//TO DEBUG
        return strModalTitle;
    }

    //Method to verify current Modal title
    public static Integer verifyModalDismissed() throws InterruptedException {
        Integer intModals;
        try {
            intModals = FindModal().findElements(By.cssSelector("h4")).size();
            System.out.println("\nModals open on the page = " + intModals + "\n");//TO DEBUG
        } catch (NullPointerException e) {
            intModals = 0;
        }

        return intModals;
    }



    //o	Method to return the heading of the latest tab created
    public static String getNewTabTitle() {
        List<String> browserTabs = new ArrayList<>(Driver.getWindowHandles());
        Driver.switchTo().window(browserTabs.get(browserTabs.size()-1));
        return DriverContext.Driver.getTitle().toUpperCase();
    }

    public static boolean isVisible(WebElement element)
    {
        boolean item = false;
        if(element.isDisplayed())
        {
            item = true;
        }
//        Driver.findElements()
        return item;
    }

//    public static void PressKeyboardButton(String arg0) {
//        DriverContext.WaitForPageToLoad();
//        Actions builder = new Actions(Driver);
//        switch(arg0.toUpperCase()) {
//            case "ENTER":
//                builder.sendKeys(Keys.ENTER).perform();
//                break;
//            case "TAB":
//                builder.sendKeys(Keys.TAB).perform();
//                break;
//            case "ARROW DOWN":
//                builder.sendKeys(Keys.ARROW_DOWN).perform();
//                break;
//            case "ARROW UP":
//                builder.sendKeys(Keys.ARROW_UP).perform();
//                break;
//            case "F5":
//                Driver.navigate().refresh();
//                break;
//            case "PAGE DOWN":
//                builder.sendKeys(Keys.PAGE_DOWN).perform();
//                break;
//            default:
//                Assert.fail("Undefined key press. Please define the key to be pressed within this switch statement.");
//        }
//    }
}