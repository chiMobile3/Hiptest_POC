package com.iMobile3.framework.base;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.iMobile3.framework.base.Browser.FindModal;
import static com.iMobile3.framework.base.DriverContext.Driver;
import static com.iMobile3.framework.base.DriverContext.MakeListUpperCase;

public class TablesPOS extends POSLocationLVLBase {



        public static  Integer HeaderIndex (String strColumn){
            WebElement thead = DriverContext.Driver.findElement(By.xpath("//table/thead"));

            List<WebElement> heads = thead.findElements(By.tagName("th"));
            int head_size = heads.size();
            System.out.println(head_size);
            Map indexOfHeadLabels = new HashMap();

            int i = 1;
                    for(WebElement header : heads) {
                        indexOfHeadLabels.put(header.getText().toUpperCase(), i);
                        i++;
                    }


            System.out.println(indexOfHeadLabels.get(strColumn.toUpperCase()));
            System.out.println(indexOfHeadLabels);

            return (Integer) indexOfHeadLabels.get(strColumn.toUpperCase());

        }

    //Method to click the edit icon for a specified Element under a specified column name
    public static void FindIconAndClick(String icon, String searchItem, String searchColumn) throws InterruptedException {
        DriverContext.waitForPageFullyLoaded(50);
        switch (icon.toUpperCase()) {
            case "EDIT":
                icon = ".fa-pencil";
                break;
            case "TRASH":
                icon = ".fa-trash";
                break;
            case "DELETE":
                icon = ".fa-trash";
                break;
            case "NAVIGATION":
                icon = ".fa-share";
                break;
        }
        String finalIcon = icon;
        Integer x = TablesPOS.HeaderIndex(searchColumn);
        DriverContext.ScrollToEndOfPage();
        WebElement tblItemTable = DriverContext.Driver.findElement(By.className("container"));
        List<WebElement> selectionsList = tblItemTable.findElements(By.xpath(".//tr/td[" + x + "]"));
        ((selectionsList.stream()
                .filter(element -> element.getText().toUpperCase().contains(searchItem.toUpperCase())) //Todo: ensure contains not impacting other scenarios
                .findFirst()
                .orElse(null)).findElement(By.xpath(".//.."))).findElement(By.cssSelector(finalIcon)).click();





    }


    //This Method will validate the items of a specific Column :usually it needed when items are be the same
    public static Boolean  ValidateColumnData(String item ,String searchColumn) throws InterruptedException {
        boolean flag = false;
        DriverContext.waitForPageFullyLoaded(50);
        Integer x = TablesPOS.HeaderIndex(searchColumn);

        DriverContext.ScrollToEndOfPage();
        WebElement tblItemTable = DriverContext.Driver.findElement(By.className("container"));
        List<WebElement> selectionsList = tblItemTable.findElements(By.xpath(".//tr/td[" + x + "]"));
        for (WebElement selection : selectionsList) {
            if(selection.getText().toLowerCase().contains(item.toLowerCase()))
            {
                flag =true;
            }
            System.out.println(searchColumn + ": " + selection.getText());
        }
        return flag;

 }


    //Method to list all text in panel heading for comparison
    public static List<String> ListAllTextInPanelHeading(WebElement elementFindby)  {
        List<String> panelHeader = new ArrayList<>();
        panelHeader.add(elementFindby.getText());
        System.out.println(panelHeader); //TO DEBUG
        return panelHeader;

    }



    //Helper method to retrieve All text from panel heading and return list for comparison
    public static List<String> makePanelList(List<WebElement> panels){
        return panels.stream()
                .map(element -> element.findElement(By.cssSelector("div > a")).getText())
                .collect(Collectors.toList());
    }


    private static boolean hasTextInTitle(WebElement element, String categoryText) {
        String[] classes = element.getAttribute("class").split("\\s+");
        //String modalTitle;
        if (classes != null) {
            for (String classAttr : classes) {
                //modalTitle = (String) ((JavascriptExecutor) Driver).executeScript("return arguments[0].textContent.trim();", element.findElement(By.cssSelector(".modal-title")));
                if (classAttr.contains(categoryText)) {

                    return true;
                }
            }
        }
        return false;
    }

/*    //Method to return specified element of section if it exists (panel)
    public static WebElement FindSection(List<String> strSectionName) throws Exception {
        WebElement thisSection = null;
        List<WebElement> Panels = DriverContext.Driver.findElements(By.className("panel-title"));
        for (WebElement panel : Panels) {
            if (MakeListUpperCase(strSectionName).equals(MakeListUpperCase(ListAllTextInPanelHeading(panel))))
                return thisSection = panel;
            break;
        }
        return null;
    }*/

    //Method to return specified element of section if it exists (panel)
    public static WebElement FindSection(List<String> strSectionName) {
        List<WebElement> Panels = DriverContext.Driver.findElements(By.className("panel-title"));
        ///////
        WebElement x = (Panels.stream()
                .filter(element -> MakeListUpperCase(strSectionName).equals(MakeListUpperCase(ListAllTextInPanelHeading(element))))
                .findFirst()
                .orElse(null));
       return  FindNeededParentNode(x, "panel panel-default");

    }


    //Method to find the needed Parent Node by class name
    public static WebElement FindNeededParentNode(WebElement element, String parentNodeClassName){
        WebElement x = element.findElement(By.xpath("./."));
        do{
            try {
                x = (WebElement) ((JavascriptExecutor) Driver).executeScript("return arguments[0].parentNode;", x);
                //System.out.println("\n" + "Class Name = " + x.getAttribute("class") + "\n"); //TO DEBUG
            }catch(Exception e)
            {
                System.out.println("\n"+ "The following specified parent node could not be found: " + parentNodeClassName + "\n"+e+"\n");
            }
        } while (!x.getAttribute("class").equalsIgnoreCase(parentNodeClassName));
        System.out.println("\n"+"Class Name = "+ x.getAttribute("class")+"\n");//TO DEBUG
        DriverContext.drawBorder(x);//TO DEBUG
        return x;
    }


//Todo: Come back and finish this method
    //Method to locate a specific item in a specific column under a specific section
    public static Long LocateItemInColumnUnderSection(List<String> searchSection, String searchItem, String searchColumn) throws InterruptedException {
        DriverContext.waitForPageFullyLoaded(50);
        Integer x = TablesPOS.HeaderIndex(searchColumn);
        DriverContext.ScrollToEndOfPage();
        DriverContext.drawBorder(FindSection(searchSection));//TO DEBUG
        List<WebElement> selectionsList = FindSection(searchSection).findElements(By.xpath(".//tr/td[" + x + "]"));
        return selectionsList.stream()
                .filter(element -> element.getText().toUpperCase().equalsIgnoreCase(searchItem.toUpperCase()))
                .count();
    }


    //This Method will click the navigation arrow next to given item under given column
    public static void  ClickNavigationArrowNextTo(String itemName ,String searchColumn) throws InterruptedException {
        DriverContext.waitForPageFullyLoaded(50);
        Integer i = TablesPOS.HeaderIndex(searchColumn);
        DriverContext.ScrollUntilDoneLoading();
        WebElement tblItemTable = DriverContext.Driver.findElement(By.className("container"));
        List<WebElement> selectionsList = tblItemTable.findElements(By.xpath(".//tr/td[" + i + "]"));
        selectionsList.stream()
                .filter(element->element.getText().trim().toLowerCase().contains(itemName.toLowerCase())) //Todo: May have to change to equalsIgnoreCase
                .forEach(element -> element.findElement(By.xpath(".//preceding::td[1]")).click() );

    }


    //Method to Click any panel section
    public static void ExpandModalSection(List<String> strSectionName) throws InterruptedException {
        DriverContext.waitForPageFullyLoaded(5);
        //Must scroll in order to show full list
        DriverContext.ScrollToEndOfPage();
        List<WebElement> Panels = FindModal().findElements(By.cssSelector(".panel-heading"));
        Panels.stream()
                .filter(p-> MakeListUpperCase(ListAllTextInPanelHeading(p)).equals(MakeListUpperCase(strSectionName)))
                .forEach(p-> p.click());
    }





}
