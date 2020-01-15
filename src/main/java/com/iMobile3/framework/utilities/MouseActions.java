package com.iMobile3.framework.utilities;

import com.iMobile3.framework.base.Base;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;



public class MouseActions extends Base {




    public static void mouseHoverJScript(WebElement HoverElement) {
        try {
            if (isElementPresent(HoverElement)) {

                String mouseOverScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('mouseover', true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject) { arguments[0].fireEvent('onmouseover');}";
                js.executeScript(mouseOverScript, HoverElement);

        } else {
            System.out.println("Element was not visible to hover " + "\n");

        }
    } catch (StaleElementReferenceException e) {
        System.out.println("Element with " + HoverElement
                + "is not attached to the page document"
                + e.getStackTrace());
    } catch (NoSuchElementException e) {
        System.out.println("Element " + HoverElement + " was not found in DOM"
                + e.getStackTrace());
    } catch (Exception e) {
        e.printStackTrace();
        System.out.println("Error occurred while hovering"
                + e.getStackTrace());
    }
}

    public static boolean isElementPresent(WebElement element) {
        boolean flag = false;
        try {
            if (element.isDisplayed()
                    || element.isEnabled())
                flag = true;
        } catch (NoSuchElementException e) {
            flag = false;
        } catch (StaleElementReferenceException e) {
            flag = false;
        }
        return flag;
    }

}



