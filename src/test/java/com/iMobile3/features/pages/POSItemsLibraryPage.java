//package com.iMobile3.features.pages;
//
//import com.iMobile3.framework.Settings;
//import com.iMobile3.framework.base.Browser;
//import com.iMobile3.framework.base.DriverContext;
//import com.iMobile3.framework.base.POSLocationLVLBase;
//import com.iMobile3.framework.utilities.CleanDirectoryUtil;
//import com.iMobile3.framework.utilities.DataTableUtil;
//import cucumber.api.DataTable;
//import org.openqa.selenium.By;
//import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.FindBy;
//import org.openqa.selenium.support.How;
//import org.openqa.selenium.support.ui.Select;
//
//import java.util.*;
//import java.util.stream.Collectors;
//
//import static com.iMobile3.framework.base.Browser.initialItemLibraryCategoriesCount;
//import static com.iMobile3.framework.base.Browser.posOfNewLine;
//import static com.iMobile3.framework.base.DriverContext.Driver;
//import static com.iMobile3.framework.base.TablesPOS.HeaderIndex;
//import static com.iMobile3.framework.utilities.DataTableUtil.convertDataTableToList;
//
//
//public class POSItemsLibraryPage extends POSLocationLVLBase {
//
//    //Page Specificbecause its Elements
//    //Download & Update
//    @FindBy(how = How.XPATH, using = "//button[@data-toggle='dropdown']")
//    public WebElement drpDownloadAndUpdate;
//
//    @FindBy(how = How.NAME, using = "Download & Update")
//    public WebElement selectDownloadAndUpdate;
//
//    @FindBy(how = How.XPATH, using = "//a[@id='importLink']")
//    public WebElement lnkImportLibrary;
//
//    @FindBy(how = How.XPATH, using = "//a[@href='/pos/items/products/export?active=True']")
//    public WebElement lnkExportActiveRecords;
//
//    @FindBy(how = How.XPATH, using = "//a[@href='/pos/items/products/export']")
//    public WebElement lnkExportInactiveRecords;
//
//    @FindBy(how = How.XPATH, using = "//a[@href='/pos/items/products/exportlocationproducts?quantityOnly=True'][contains(.,'Download for Physical Count')]")
//    public WebElement lnkDownloadPhysicalCount;
//
//    @FindBy(how = How.XPATH, using = "//a[@href='/pos/items/products/exportlocationproducts?quantityOnly=False'][contains(.,'Download for Item Receiving')]")
//    public WebElement lnkDownloadForItemReceiving;
//
//    @FindBy(how = How.XPATH, using = "//a[@id='importCounts']")
//    public WebElement lnkUpdatePhysicalCounts;
//
//    @FindBy(how = How.XPATH, using = "//a[@id='importProducts']")
//    public WebElement lnkUpdateItemsReceived;
//
//    //Choose file
//    @FindBy(how = How.XPATH, using = "(//input[contains(@id,'fileInput')])[3]")
//    public WebElement lnkChooseFile;
//
//    @FindBy(how = How.XPATH, using = "//button[contains(.,'Import')]")
//    public WebElement btnImport;
//
//    @FindBy(how = How.XPATH, using = "//li[contains(.,'Invalid file format.')]")
//    public WebElement msgInvalidFileFormat;
//
//    //Import Inventory Items From CSV File
//    @FindBy(how = How.XPATH, using = "//a[contains(@href,'Retail.csv')]")
//    public WebElement lnkSampleFile;
//
//    @FindBy(how = How.XPATH, using = "(//a[@href='#'][contains(.,'Cancel')])[3]")
//    public WebElement lnkCancel;
//
//    //Actions dropDown
//    @FindBy(how = How.XPATH, using = "(//button[contains(@data-toggle,'dropdown')])[2]")
//    public WebElement drpActions;
//
//    @FindBy(how = How.XPATH, using = "(//a[contains(@class,'deleteCategoryButton')])[1]")
//    public WebElement drpDeleteCategory;
//
//    @FindBy(how = How.XPATH, using = "//button[@id='confirmDeleteCategoryButton']")
//    public WebElement btnDeleteCategories;
//
//    @FindBy(how = How.XPATH, using = "//button[@id='runDeleteCategoryButton']")
//    public WebElement btnConfirmDeleteCategories;
//
//    @FindBy(how = How.XPATH, using = "//button[@id='deleteCategorySuccessButton']")
//    public WebElement btnDoneDeletingCategoris;
//    //New menu added under Action dropdown
//    @FindBy(how = How.XPATH, using = "//a[@href='products/add?id=2288656']")
//    public WebElement lnkAddItem_Actions;
//
//    //Search
//    @FindBy(how = How.XPATH, using = "//button[contains(.,'Search')]")
//    public WebElement btnSearch;
//
//    @FindBy(how = How.XPATH, using = "//input[contains(@placeholder,'Item Name/Code')]")
//    public WebElement strItemNameCode;
//
//    @FindBy(how = How.XPATH, using = "//span[@data-bind='text: Name']")
//    public WebElement strFirstItemSearched;
//
//    @FindBy(how = How.XPATH, using = "//span[@data-bind='text: $data'][contains(.,'Juices & Water')]")
//    public WebElement strFirstCategoryHeader;
//
//    //ItemLibrary Table
//    @FindBy(how = How.CLASS_NAME, using = "table table-hover table-striped")
//    public WebElement baseTable;
//
//    @FindBy(how = How.XPATH, using = "(//input[@name='Updates[-1].Price'])[1]")
//    public WebElement strFirstCategoryPrice;
//
//    @FindBy(how = How.XPATH, using = "(//input[@name='Updates[-1].Cost'])[1]")
//    public WebElement strFirstCategoryCost;
//
//    //Import Csv elements
//    @FindBy(how = How.XPATH, using = "(//p[contains(.,'Your task has been submitted. Please wait while the task completes. You can navigate to the tasks details page to check the status of this task or close this dialog at any time.')])[3]")
//    public WebElement msgImportMessage;
//
//    @FindBy(how = How.XPATH, using = "(//a[@href='#'][contains(.,'Done')])[8]")
//    public WebElement btnUploadDone;
//
//    @FindBy(how = How.XPATH, using = "//div[@id='csv_import_inventory']//p[contains(text(),'Your task has been submitted. Please wait while th')]")
//    public WebElement msgFileUploading;
//
//    @FindBy(how = How.XPATH, using = "(//p[contains(.,'Your task has completed. Click here to review the task results.')])[3]")
//    public WebElement msgFileUploaded;
//
//    @FindBy(how = How.XPATH, using = "(//button[contains(.,'Save')])[1]")
//    public WebElement btnTopSave;
//
//    @FindBy(how = How.XPATH, using = "//a[@class='toggle-advanced-options']")
//    public WebElement toggleAdvancedOptions;
//
//    @FindBy(how = How.XPATH, using = "(//button[contains(.,'Save')])[2]")
//    public WebElement btnBottomSave;
//
//    @FindBy(how = How.XPATH, using = "//span[@title='All Categories']")
//    public WebElement srtAllCategories;
//
//    @FindBy(how = How.XPATH, using = "//span[@title='Subcategory']")
//    public WebElement srtAllSubcategories;
//
//    @FindBy(how = How.XPATH, using = "//span[@title='All Groups']")
//    public WebElement srtAllGroups;
//
//    @FindBy(how = How.XPATH, using = "//button[@id='moveProductButton']")
//    public WebElement btnMoveSelectedItems;
//
//    @FindBy(how = How.XPATH, using = "//button[@id='deleteProductButton']")
//    public WebElement btnDeleteSelectedItems;
//
//    @FindBy(how = How.XPATH, using = "//button[contains(.,'Add Category')]")
//    public WebElement btnAddCategory;
//
//    @FindBy(how = How.XPATH, using = "//button[contains(.,'Add Item')]")
//    public WebElement btnAddItem;
//
//    @FindBy(how = How.XPATH, using = "//a[@href='http://help.tsyssmartpos.com/library.htm']")
//    public WebElement lnkClickHere;
//
//    //Header
//    @FindBy(how = How.XPATH, using = "//h2[contains(.,'Item Library')]")
//    public WebElement strItemLibrary;
//
//    //No Items in Library
//    @FindBy(how = How.XPATH, using = "//span[contains(.,'No items to list. Click here to learn about the Item Library.')]")
//    public WebElement strNoItems;
//
//    @FindBy(how = How.XPATH, using = "//div[@class='alert alert-danger validation-summary-errors']//h5[contains(text(),'Alerts have occurred')]")
//    public WebElement msgErrors;
//
//    @FindBy(how = How.XPATH, using = "(//i[@class='fa fa-fw fa-lg fa-pencil '])[1]")
//    public WebElement btnEditItem;
//
//
//
//    //Methods to interact with page elements
//    public String ItemLibraryPageDisplayed() {
//        //To verify driver is on the Item Library page
//        DriverContext.WaitForElementtVisible(strItemLibrary);
//        return strItemLibrary.getText();
//    }
//
//    public boolean NoItemsToList() {
//        //To verify there are no items in the Item Library
//        boolean Flag = true;
//        if (DriverContext.isElementPresent(drpActions) == Flag) {
//            try {
//                DeleteCategories();
//                Flag = true;
//            } catch (Exception e) {
////                Settings.Logs.Write("Element does not exist Error ");
//                Flag = false;
//            }
//        }
//        return Flag;
//    }
//
//    public Select sExpandDownloadAndUpdate() {
//        return new Select(selectDownloadAndUpdate);
//    }
//
//    public void ExpandDownloadAndUpdate() {
//        DriverContext.WaitForElementtVisible(drpDownloadAndUpdate);
//        drpDownloadAndUpdate.click();
//    }
//
//    public void ImportLibrary() {
//        DriverContext.WaitForElementtVisible(lnkImportLibrary);
//        lnkImportLibrary.click();
//    }
//
//    public void DownloadSampleFile() {
//        CleanDirectoryUtil.RemoveFiles();
//        DriverContext.WaitForElementtVisible(lnkSampleFile);
//        lnkSampleFile.click();
//        lnkCancel.click();
//    }
//
//    public String LatestFileDownloaded() {
//        DriverContext.WaitForElementNotVisible(lnkSampleFile);
//        String fileName = Browser.getLatestFilefromDir().getName();
//        return fileName;
//    }
//
//    public void ImportItemFile(String FileName) throws InterruptedException {
//        DriverContext.WaitForElementtVisible(lnkChooseFile);
//        lnkChooseFile.sendKeys(Browser.ImportFile(FileName));
//        Thread.sleep(500);
//        btnImport.click();
//    }
//
//    public String InvalidFileAlert() {
//        DriverContext.WaitForElementtVisible(msgErrors);
//        //Adding carriage return and new line to match bootstrap error message
//        String strAlert = msgErrors.getText() + "\r\n" + msgInvalidFileFormat.getText();
//        return strAlert;
//    }
//
//    public String ImportMessageDisplayed() {
//        DriverContext.WaitForElementtVisible(msgImportMessage);
//        String strMsgImport = msgFileUploading.getText(); //msgImportMessage.getText();
//        return strMsgImport;
//    }
//
//    public boolean VerifyDoneButton() {
//        DriverContext.WaitForElementtVisible(btnUploadDone);
//        return btnUploadDone.isDisplayed();
//    }
//
//    public String TaskCompleteMessageDisplayed() {
//        DriverContext.WaitForElementtVisible(msgFileUploaded);
//        String strMsgTaskComplete = msgFileUploaded.getText();
//        return strMsgTaskComplete;
//    }
//
//    public void ImportLibraryProcess(String strFileName) throws InterruptedException {
//        NoItemsToList();
//        ExpandDownloadAndUpdate();
//        ImportLibrary();
//        ImportItemFile(strFileName);
//        DriverContext.WaitForElementtVisible(btnUploadDone);
//        btnUploadDone.click();
//    }
//
//    public void SearchForItem(String SearchForItem) {
//        DriverContext.WaitForElementtVisible(strItemNameCode);
//        strItemNameCode.sendKeys(SearchForItem);
//        btnSearch.click();
//    }
//    public static void SearchItem(String SearchForItem) {
//        WebElement ItemName = DriverContext.Driver.findElement(By.xpath("//input[contains(@placeholder,'Item Name/Code')]"));
//        ItemName.clear();
//        ItemName.sendKeys(SearchForItem);
//    }
//    public String SearchedItemDisplayed() {
//        DriverContext.WaitForElementtVisible(strFirstItemSearched);
//        String strVerifySearch = strFirstItemSearched.getText() + strFirstCategoryHeader.getText();
//        return strVerifySearch;
//    }
//    public static String ValidateSearchResult(String SearchForItem) {
//        WebElement searchItem = DriverContext.Driver.findElement(By.xpath("//span[@data-bind='text: Name']"));
//        String strVerifySearch = searchItem.getText();
//        return strVerifySearch;
//    }
//    public String FindPrice() {
//        DriverContext.WaitForElementtVisible(strFirstCategoryPrice);
//        String strPrice = strFirstCategoryPrice.getAttribute("value");
//        return strPrice;
//    }
//
//    public String FindCost() {
//        DriverContext.WaitForElementtVisible(strFirstCategoryCost);
//        String strCost = strFirstCategoryCost.getAttribute("value");
//        return strCost;
//    }
//
//    //Click Add Item under Actions dropdown
//    public void ClicklnkAddItem() {
//        DriverContext.WaitForElementtVisible(lnkAddItem_Actions);
//        lnkAddItem_Actions.click();
//    }
//
//    //Click on the Actions Drop down
//    public void ClickdrpActions() {
//        DriverContext.WaitForElementtVisible(drpActions);
//        drpActions.click();
//    }
//
//    // Verifing Add Item under Actions dropdown
//    public boolean VerifyAdditem_drpActions() {
//        DriverContext.WaitForElementtVisible(lnkAddItem_Actions);
//        return lnkAddItem_Actions.isDisplayed();
//    }
//
//    //Method to delete categories from Item Library when there is one category during test
//    public void DeleteCategories() {
//        drpActions.click();
//        DriverContext.WaitForElementtVisible(drpDeleteCategory);
//        drpDeleteCategory.click();
//        DriverContext.WaitForElementtVisible(btnDeleteCategories);
//        btnDeleteCategories.click();
//        DriverContext.WaitForElementtVisible(btnConfirmDeleteCategories);
//        btnConfirmDeleteCategories.click();
//        DriverContext.WaitForElementtVisible(btnDoneDeletingCategoris);
//        btnDoneDeletingCategoris.click();
//    }
//
//    public void EditFirstItem() {
//        DriverContext.WaitForElementtVisible(btnEditItem);
//        btnEditItem.click();
//    }
//
//    //This method will click the Action dropdown under specific category and Select a given menu item from the droDown
//    public static void SelectAction_category(String item, String drpDown, String category) throws InterruptedException {
//        DriverContext.waitForPageFullyLoaded(50);
//
//        List<WebElement> CategoryList = DriverContext.Driver.findElements(By.xpath("//span[contains(@data-bind,'text: $data')]"));
//        for (WebElement categoryname : CategoryList) {
//            if (categoryname.getText().toUpperCase().equals(category.toUpperCase())) {
//                int position = CategoryList.indexOf(categoryname);
//                //List<WebElement> Actionbuttons =  DriverContext.Driver.findElements(By.xpath("//button[contains(@data-toggle,'dropdown')]")); //
//                List<WebElement> Actionbuttons = DriverContext.Driver.findElements(By.xpath("//button[contains(text(),'" + drpDown.trim() + "')]")); //
//                //Action drodown clicked
//                System.out.println("\n" + Actionbuttons.get(position).getText() + " Dropdown Under " + categoryname.getText() + " clicked\r\n");
//                Actionbuttons.get(position).click();
//                //Select Options from the Action dropdown options
//                select_ActionItem(item, position);
//                break;
//            }
//        }
//    }
//
//    // validate a specific category is in the item library page
//    public static boolean ValidateCategory(String categoryName) throws InterruptedException {
//        POSPage.ScrolltoEnd();
//
//        Boolean bol = false;
//        List<WebElement> CategoryList = DriverContext.Driver.findElements(By.xpath("//span[contains(@data-bind,'text: $data')]"));
//        for (WebElement categoryname : CategoryList) {
//            //System.out.println(categoryname.getText().toUpperCase()); // Added for debug
//            if (categoryname.getText().toUpperCase().equals(categoryName.toUpperCase())) {
//                bol = true;
//                System.out.println("\n" + categoryname.getText() + " found\r\n");
//                break;
//            }
//        }
//        if (!bol) {
//            System.out.println("\n" + categoryName + " not found\r\n");
//        }
//        return bol;
//    }
//
//    //Clicking drop down items under Action
//    public static void select_ActionItem(String item, int position) throws InterruptedException {
//        DriverContext.waitForPageFullyLoaded(50);
//        List<WebElement> drpDownItems = DriverContext.Driver.findElements(By.xpath("//a[contains(text(),'" + item.trim() + "')]"));
//        drpDownItems.get(position).click();
//    }
//
//    // This method will validate an item under specific category of Item Library page
//    public static boolean FindItemUnderCategory(String item, String category) throws InterruptedException {
//        boolean found = false;
//        DriverContext.waitForPageFullyLoaded(50);
//        //JavascriptExecutor js = (JavascriptExecutor) DriverContext.Driver;
//        ((JavascriptExecutor) Driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
//        DriverContext.waitForPageFullyLoaded(50);
//
//        List<WebElement> panels = DriverContext.Driver.findElements(By.className("panel panel-default"));
//        for (WebElement panel : panels) {
//            List<WebElement> CategoryList = panel.findElements(By.xpath("//span[contains(@data-bind,'text: $data')]"));
//            for (WebElement categoryname : CategoryList) {
//                if (categoryname.getText().contains(category)) {
//                    int start = CategoryList.indexOf(categoryname);
//                    WebElement tblItemTable = DriverContext.Driver.findElement(By.className("container"));
//                    List<WebElement> itemLists = tblItemTable.findElements(By.xpath("//span[contains(@data-bind,'text: Name')]"));
//
//                    for (WebElement items : itemLists) {
//                        if (items.getText().equals(item)) {
//                            System.out.println("\n" + items.getText() + " This is it");
//                            found = true;
//                            break;
//                        }
//                    }
//                    break;
//                }
//            }
//        }
//        return found;
//    }
//
//
//    //Method to count the number of items that have been selected in the Item Library
//    public static Integer ItemsSelected() throws InterruptedException {
//        DriverContext.waitForPageFullyLoaded(10);
//        List<WebElement> selectedItemsList = DriverContext.Driver.findElements(By.cssSelector("tr>td:nth-child(1) input[type=checkbox]")); //The first checkbox in each table excluding the Check All Items checkbox
//        selectedItemsList.forEach(chkBox -> DriverContext.drawBorder(chkBox.findElement(By.xpath(".//..")))); //TO DEBUG
//        List<WebElement> newList = selectedItemsList.stream()
//                .filter(chkBox -> chkBox.isSelected())
//                .collect(Collectors.toList());
//        System.out.println("\nThere are " + selectedItemsList.size() + " items in the Item Library and " + newList.size() + " selected.");
//        return newList.size();
//    }
//
//
//    //Method to list all elements on the main page (up to intElements) and compare to collection of items from dataTable
//    public static Collection<String> FindAllElements(Integer intElements) {
//        List<WebElement> allElements = Driver.findElements(By.cssSelector("#main-page *"));
//        //allElements.forEach(DriverContext::drawBorder); // TO DEBUG
//        Collection<String> allElementsDisplayed = (List<String>) allElements.stream()
//                .filter(WebElement::isDisplayed)
//                .limit(intElements)
//                .distinct()
//                .map(WebElement::getText)
//                .collect(Collectors.toList());
//        //System.out.println(allElementsDisplayed); //TO DEBUG
//        return allElementsDisplayed;
//    }
//
//
//    //Method to compare Listed WebElements on the page to collection of items from dataTable
//    public static Boolean CompareElementsToCollection(DataTable listShown){
//        Boolean flag = null;
//        Collection<String> convertedDatTable = listShown.asList(String.class).stream().collect(Collectors.toCollection(TreeSet::new));
//        if (FindAllElements(100).containsAll(convertedDatTable) == true)
//            flag = true;
//        else
//            flag = false;
//        return flag;
//    }
//
//
//
//    //Method to compare initial count of library items to filtered list of library items count
//    public static Integer finalItemLibraryCategoriesCount () {
//        Integer currentItemLibraryCategoriesCount = DriverContext.Driver.findElements(By.cssSelector(".panel-heading")).size();
//        Integer currCount = initialItemLibraryCategoriesCount - currentItemLibraryCategoriesCount;
//        return currCount;
//    }
//
//
//
//    //Method to compare list of items given with list of items in the item library
//    public static boolean listItems(DataTable dataTable, String colHeader) {
//        boolean flag = false;
//        Collection<String> convertedDatTable = convertDataTableToList(dataTable);
//        System.out.println(convertedDatTable);//TO DEBUG
//        System.out.println(listAllItems(colHeader));//TO DEBUG
//        Set<String> expectedValues1 = new HashSet<>(listAllItems(colHeader));
//        Set<String> expectedValues2 = convertedDatTable.stream().collect(Collectors.toSet());
//        if(expectedValues1.equals(expectedValues2)){flag = true;}
//        return flag;
//    }
//
//
//    //Method to getText of all items under a given column name
//    public static Collection<String> listAllItems(String colHeader) {
//        Integer intHeader = HeaderIndex(colHeader);
//        //System.out.println(HeaderIndex(intHeader)); //TO DEBUG
//        List<WebElement> allItemsListed = Driver.findElements(By.cssSelector("td:nth-child(" + intHeader + ")"));
//        return allItemsListed.stream()
//                .map(element -> element.getText())
//                .map(txt-> txt.substring(0,posOfNewLine(txt)))
//                .map(String::toUpperCase)
//                //.forEach(System.out::println); //TO DEBUG
//                .collect(Collectors.toList());
//    }
//
//
//
//}
