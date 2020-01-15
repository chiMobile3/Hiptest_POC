package com.iMobile3.framework.base;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class POSLocationLVLBase extends NavPanelBase {

    @FindBy(how = How.XPATH, using = "//a[@href='/admin'][contains(.,'Dashboard')]")
    public WebElement itmDashboard;

    @FindBy(how = How.XPATH, using = "//a[@href='#'][contains(.,'Analytics')]")
    public WebElement lnkAnalytics;

    @FindBy(how = How.XPATH, using = "//a[@href='/pos/reports/sales-summary']")
    public WebElement lnkSalesSummary;

    @FindBy(how = How.XPATH, using = "//a[@href='/pos/reports/profit-margin']")
    public WebElement lnkProfitMargin;

    @FindBy(how = How.XPATH, using = "//a[@href='/pos/reports/prior-period-comparison']")
    public WebElement lnkPriorPeriodComparison;

    @FindBy(how = How.XPATH, using = "//a[@href='/pos/reports/merchant-settlement']")
    public WebElement lnkMerchantSettlement;

    @FindBy(how = How.XPATH, using = "//a[@href='/pos/reports/calendar']")
    public WebElement lnkCalendar;

    @FindBy(how = How.XPATH, using = "//a[@href='/pos/reports/sales-trend']")
    public WebElement lnkSalesTrend;

    @FindBy(how = How.XPATH, using = "//a[@href='/pos/reports/sales-dayofweek']")
    public WebElement lnkSalesDayOfWeek;

    @FindBy(how = How.XPATH, using = "//a[@href='/pos/reports/sales-hourofday']")
    public WebElement lnkSalesHourOfDay;

    @FindBy(how = How.XPATH, using = "//a[@href='/pos/reports/sales-day']")
    public WebElement lnkSalesDay;

    @FindBy(how = How.XPATH, using = "//a[@href='/pos/reports/sales-monthofyear']")
    public WebElement lnkSalesMonthOfYear;

    @FindBy(how = How.XPATH, using = "//a[@href='/pos/reports/sales-week']")
    public WebElement lnkSalesWeek;

    @FindBy(how = How.XPATH, using = "//a[@href='/pos/reports/sales-register']")
    public WebElement lnkSalesRegister;

    @FindBy(how = How.XPATH, using = "//a[@href='/pos/reports/sales-user']")
    public WebElement lnkSalesUser;

    @FindBy(how = How.XPATH, using = "//a[@href='/pos/reports/productmix-topitems']")
    public WebElement lnkItemMixTopItems;

    @FindBy(how = How.XPATH, using = "//a[@href='/pos/reports/productmix-topcategories']")
    public WebElement lnkItemMixTopCategories;

    @FindBy(how = How.XPATH, using = "//a[@href='/pos/reports/other-paymentsummary']")
    public WebElement lnkOtherPaymentSummary;

    @FindBy(how = How.XPATH, using = "//a[@href='/pos/reports/other-discounts']")
    public WebElement lnkOtherDiscounts;

    @FindBy(how = How.XPATH, using = "//a[@href='/pos/reports/other-modifiers']")
    public WebElement lnkOtherModifierSales;

    @FindBy(how = How.XPATH, using = "//a[@href='/pos/reports/other-taxes']")
    public WebElement lnkOtherTaxes;

    @FindBy(how = How.XPATH, using = "//a[@href='/pos/reports/other-taxexemptorders']")
    public WebElement lnkOtherTaxExemptOrders;

    @FindBy(how = How.XPATH, using = "//a[@href='/pos/transactions']")
    public WebElement lnkTrxTransactions;

    @FindBy(how = How.XPATH, using = "//a[@href='/pos/batches']")
    public WebElement lnkTrxBatches;

    @FindBy(how = How.XPATH, using = "//a[@href='/pos/tenders']")
    public WebElement lnkTrxTenders;

    @FindBy(how = How.XPATH, using = "//a[@href='/pos/audits']")
    public WebElement lnkTrxAudits;

    @FindBy(how = How.XPATH, using = "//a[@href='/pos/items/products']")
    public WebElement lnkItmItemLibrary;

    @FindBy(how = How.XPATH, using = "//a[@href='/pos/items/screenlayouts']")
    public WebElement lnkItmScreenLayouts;

    @FindBy(how = How.XPATH, using = "//a[@href='/pos/items/modifiers']")
    public WebElement lnkItmModifiers;

    @FindBy(how = How.XPATH, using = "//a[@href='/pos/items/modifiersets']")
    public WebElement lnkItmModifierSets;

    @FindBy(how = How.XPATH, using = "//a[@href='/pos/items/discounts']")
    public WebElement lnkItmDiscounts;

    @FindBy(how = How.XPATH, using = "//a[@href='/pos/items/taxes']")
    public WebElement lnkItmTaxes;

    @FindBy(how = How.XPATH, using = "//a[@href='/pos/settings/messages']")
    public WebElement lnkItmItemAlertMessages;

    @FindBy(how = How.XPATH, using = "//a[@href='/pos/locations/edit']")
    public WebElement lnkStoreOperations;

    @FindBy(how = How.XPATH, using = "//a[@href='/pos/storesetup/registers']")
    public WebElement lnkStoreRegisters;

    @FindBy(how = How.XPATH, using = "//a[@href='/pos/storesetup/receipts']")
    public WebElement lnkStoreReceipts;

    @FindBy(how = How.XPATH, using = "//a[@href='/pos/storesetup/stations']")
    public WebElement lnkStoreStations;

    @FindBy(how = How.XPATH, using = "//a[@href='/pos/storesetup/identifiers']")
    public WebElement lnkStoreIdentifiers;

    @FindBy(how = How.XPATH, using = "//a[@href='/pos/storesetup/accessories']")
    public WebElement lnkStoreAccessories;

    @FindBy(how = How.XPATH, using = "//a[@href='/pos/settings/tenders']")
    public WebElement lnkPmtSetupTenders;

    @FindBy(how = How.XPATH, using = "//a[@href='/pos/settings/payment-options']")
    public WebElement lnkPmtSetupPaymentOptions;

    @FindBy(how = How.XPATH, using = "//a[@href='/pos/settings/processors']")
    public WebElement lnkPmtSetupProcessors;

    @FindBy(how = How.XPATH, using = "//a[@href='/pos/staff/users']")
    public WebElement lnkStaffUsers;

    @FindBy(how = How.XPATH, using = "//a[@href='/pos/staff/groups']")
    public WebElement lnkStaffGroups;

    @FindBy(how = How.XPATH, using = "//a[@href='/pos/tasks']")
    public WebElement lnkTasks;

}
