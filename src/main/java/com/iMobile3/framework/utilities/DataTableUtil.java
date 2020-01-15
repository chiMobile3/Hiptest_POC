package com.iMobile3.framework.utilities;

import com.iMobile3.framework.base.Alerts;
import com.iMobile3.framework.base.Base;
import com.iMobile3.framework.base.DriverContext;
import cucumber.api.DataTable;
import org.openqa.selenium.WebElement;

import java.util.Collection;
import java.util.Map;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class DataTableUtil extends Base {

    public static void AutoFillFromTable( DataTable dataTable) throws InterruptedException {
        DriverContext.waitForPageFullyLoaded(100);
        TableMap(dataTable);
    }

    private static void TableMap(DataTable dataTable) throws InterruptedException {
        WebElement field = null;
        String value = null;
        Map<String, String> m = dataTable.asMap(String.class, String.class);
        System.out.println("\nFilling form with below data\n");
        for (String k : m.keySet()) {
            field = Alerts.ReturnFieldElement(k);
            value = m.get(k);
            if(field.getTagName().contains("select")){
                DriverContext.SelectDropDown(value, field);
                //Assert.assertTrue(DriverContext.WaitAndVerifyDropDownSelection(value, field));
                DriverContext.waitForPageFullyLoaded(300);
            }
            else{
                field.clear();
                field.sendKeys(value);
                //DriverContext.WaitAndVerifyTextEntry(value,field);
            }
            System.out.println("Key -> " + k + " Value -> " + m.get(k));
        }

        //return m;
    }


    //Method to convert a datTable to an uppercase collection
    public static Collection<String> convertDataTableToList(DataTable dataTable) {
        return dataTable.asList(String.class).stream()
                .map(String::toUpperCase)
                .collect(Collectors.toCollection(TreeSet::new));
    }


}
