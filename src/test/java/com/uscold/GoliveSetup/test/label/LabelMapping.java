package com.uscold.GoliveSetup.test.label;

import com.uscold.GoliveSetup.test.Abstract;
import com.uscold.GoliveSetup.test.utility.AssistPage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class LabelMapping extends Abstract {

    final static String WHSE = "800 - BETHLEHEM";
    final static String WHSE_NUM = "160";
    int WHSE_int = Integer.parseInt(WHSE.substring(1, 3));
    final static String driverID = "BTH12345";
    final static String driverFirstName = "JOHN";
    final static String driverLastName = "SMITH";
    final static String driverContactNumber = "8888888888";
    final static String driverTractorNumber = "TRK";
    final static String trailerNumber = "TRL";
    final static String carrierNumber = "90090";
    final static String lengthVal = "53";
    final static String typeVal = "REEFER";
    final static String tempVal = "5";

    final static String WHSETEST = "160 - MILFORD";
    final static String productnum = "";
    final static String entNum = "Enterprise";

    //Validation and cache data
    public String driverIDFromArrival = "";
    public String trailerNumberFromArrival = "";
    int expectednumber = 1;
    private String offNumTotal = "";
    private String offNumOnsiteTotal = "";
    public String fistDriverFromGridList = "";
    private String firstRecordFoundOne = "";
    private String firstRecordFoundOne_int = "";
//    private String recordSearched = "";




//priority = 1, description = "TC: Search drivers at the whse level",
@Test(priority = 1, dataProvider = "LabelMappingData")
public void LabelMapping(String labelForm, String labelType) {

    extentTest = extent.startTest("Label " + labelForm + " mapped to " + labelType + " at warehouse " + WHSETEST + ".");

    //Fetch today's date and convert to a string plus_100.
    Calendar dateTwo = Calendar.getInstance();
    Date dateOne = dateTwo.getTime();
    DateFormat dateForm = new SimpleDateFormat("YYMMddhhmmss");
    String tDay = dateForm.format(dateOne);
//        AssistPage.chooseModule(driver,"Label Setup");
//        AssistPage.chooseWarehouse(driver, WHSE_NUM);

    driver.findElement(By.id("searchText")).clear();
    driver.findElement(By.id("searchText")).sendKeys("label setup");
    //driver.manage().wait();
    driver.findElement(By.linkText("Label Setup")).click();


        AssistPage.sendInput(driver,"id","gs_lblFormat",labelForm);
        driver.findElement(By.id("gs_lblFormat")).sendKeys(Keys.ENTER);

        //xpath for clicking on the first radio button
        AssistPage.click(driver.findElement(By.xpath("//tr[2][@class='ui-widget-content jqgrow ui-row-ltr']/td[1]")));
        AssistPage.click(driver.findElement(By.id("editRecord")));


    //This will click on teh dropdown to display the list
        driver.findElement(By.xpath("//table[@id='list']//a[contains(@class, 'chosen-single')]")).click();
    //This will create the list
        List<WebElement> accTypes = driver.findElements(By.xpath(".//div[@class='chosen-drop']//ul[@class='chosen-results']/li"));
        //((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", accTypes);

    //This will filter and match and click on the appropriate value
        accTypes.stream().filter(el -> el.getAttribute("innerText").trim().equalsIgnoreCase(labelType)).findFirst().get().click();
//        click(accTypes);

    //save the changes
        AssistPage.click(driver.findElement(By.id("updateRecord")));

    WebElement statusMsg = driver.findElement(By.id("msgDisp"));
    try {
        if (!statusMsg.isDisplayed() && !statusMsg.getText().contains("Label Mapped Successfully."))
            throw new RuntimeException("[ERROR] This label form " + labelForm + " " + labelType + " failed to be mapped");
    } catch (RuntimeException e) {
        System.out.println("[ERROR] This label form " + labelForm + " " + labelType + " failed to be mapped");
        e.printStackTrace();
    }

    //This will print the action
    System.out.println("[Added Successfully] [update_ts: " + tDay + "] [INFO]" + "This label form " + labelForm + " was mapped to this label type=================>" + labelType);
    }


    //priority = 1, description = "TC: Search drivers at the whse level",
    @Test(priority = 1, dataProvider = "getLabelAssignmentData")
    public void LabelAssignment(
            String labelType,
            String labelForm,
            String numofcopies,
            String customernumber,
            String consigneenumber,
            String product

    ) {
        extentTest = extent.startTest("Label " + labelForm + " mapped to " + labelType + " at warehouse " + WHSETEST + ".");

        //Fetch today's date and convert to a string plus_100.
        Calendar dateTwo = Calendar.getInstance();
        Date dateOne = dateTwo.getTime();
        DateFormat dateForm = new SimpleDateFormat("YYMMddhhmmss");
        String tDay = dateForm.format(dateOne);
//        AssistPage.chooseModule(driver,"Label Setup");
//        AssistPage.chooseWarehouse(driver, WHSE_NUM);

        //Navigate to Label assignment
        driver.findElement(By.id("searchText")).clear();
        driver.findElement(By.id("searchText")).sendKeys("label setup");
        //driver.manage().wait();
        driver.findElement(By.linkText("Label Setup")).click();

        //Click on the Label Mapping tab
        driver.findElement(By.xpath("//div[@id='labelAsignment']/a/span")).click();

        //Click on the Add new record button
        driver.findElement(By.id("addNewRecord")).click();

        //This will click on the label type dropdown to display the list
        driver.findElement(By.xpath("//div[@title='Please Select The Label Type.']/a[contains(@class, 'chosen-single')]/span")).click();
        //This will create the list
        List<WebElement> accTypess = driver.findElements(By.xpath(".//div[@class='chosen-drop']//ul[@class='chosen-results']/li"));
        //((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", accTypes);
        //This will filter and match and click on the appropriate value
        accTypess.stream().filter(el -> el.getAttribute("innerText").trim().equalsIgnoreCase(labelType)).findFirst().get().click();
//        click(accTypes);


        //This will click on the label format dropdown to display the list
        driver.findElement(By.xpath("//div[@title='Please Select The Label Format.']/a[contains(@class, 'chosen-single')]/span")).click();
        //This will create the list
        List<WebElement> accTypesss = driver.findElements(By.xpath("//div[@title='Please Select The Label Format.']/div[@class='chosen-drop']//ul[@class='chosen-results']/li"));
        //((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", accTypes);
        //This will filter and match and click on the appropriate value
        accTypesss.stream().filter(el -> el.getAttribute("innerText").trim().equalsIgnoreCase(labelForm)).findFirst().get().click();
//        click(accTypes);

        AssistPage.sendInput(driver, "xpath", "//input[@title='Enter The # of Copies.']", numofcopies);

        //This will click on the label format dropdown to display the list
        driver.findElement(By.xpath("//div[@title='Please Select The Customer #.']/a[contains(@class, 'chosen-single')]/span")).click();
        //This will create the list
        List<WebElement> accTypessss = driver.findElements(By.xpath("//div[@title='Please Select The Customer #.']/div[@class='chosen-drop']//ul[@class='chosen-results']/li"));
        //((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", accTypes);
        //This will filter and match and click on the appropriate value
        accTypessss.stream().filter(el -> el.getAttribute("innerText").trim().equalsIgnoreCase(customernumber)).findFirst().get().click();
//        click(accTypes);


        driver.findElement(By.xpath("//input[@class='consigneeTextBox editable ui-autocomplete-input']")).sendKeys(consigneenumber);
        click(By.xpath("//ul[@class='ui-autocomplete ui-front ui-menu ui-widget ui-widget-content ui-corner-all']/li[@class='ui-menu-item']/a[contains(text(),'" + consigneenumber + "')]"));
        //Validate that the product description was autofilled correctly
        WebElement descProd = driver.findElement(By.xpath("//tr[@id='OEDCREATEPOPUP-1']/td[contains(@class, 'wordWrap')][2]"));
        if (!descProd.isDisplayed() && !descProd.getText().toLowerCase().contains(consigneenumber))
            throw new RuntimeException("Failed to click on product code from autosuggest");


        if (!productnum.trim().equals("")) {
            //This will click on the label format dropdown to display the list
            driver.findElement(By.xpath("//div[@title='Please Select The Product.']/a[contains(@class, 'chosen-single')]/span")).click();
            //This will create the list
            List<WebElement> accTypesssss = driver.findElements(By.xpath("//div[@title='Please Select The Product.']/div[@class='chosen-drop']//ul[@class='chosen-results']/li"));
            //((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", accTypes);
            //This will filter and match and click on the appropriate value
            accTypesssss.stream().filter(el -> el.getAttribute("innerText").trim().equalsIgnoreCase(product)).findFirst().get().click();
//        click(accTypes);
        }


        AssistPage.click(driver.findElement(By.xpath("//button[@id='saveNewRecord']")));

        //check whether the assginment was saved
        WebElement statusMsg = driver.findElement(By.id("msgDisp"));
        try {
            if (!statusMsg.isDisplayed() && !statusMsg.getText().contains("Label Assignment created Succesfully."))
                throw new RuntimeException("[ERROR] This label form " + labelForm + " " + labelType + " failed to be assigned to " + customernumber + "/" + consigneenumber + "/" + productnum + " at the " + WHSE + " warehouse.");
        } catch (RuntimeException e) {
            System.out.println("[ERROR] This label form " + labelForm + " " + labelType + " failed to be mapped");
            e.printStackTrace();
        }

        //This will print the action
        System.out.println("[Added Successfully] [update_ts: " + tDay + "] [INFO]" + "This label format " + labelForm + " was mapped to this label type=================>" + labelType + " and assigned to " + customernumber + "/" + consigneenumber + "/" + productnum + " at the " + WHSE + " warehouse.");
    }


    @DataProvider()
    public Object[][] LabelMappingData() {
        Object data[][] = AssistPage.getLabelMappingData("LabelMapping");
        return data;
    }

//    @DataProvider()
//    public Object [][][][][][] getLabelAssignmentData(){
////        Object data [][][][][][] = AssistPage.getTestData("LabelMapping");
////
////        return data;
//        Object data [][][][][][] = AssistPage.getLabelAssignmentData("LabelAssignment");
//        return data;
//    }

}
