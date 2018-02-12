package com.uscold.labelsetup.test.label;

import com.uscold.labelsetup.test.Abstract;
import com.uscold.labelsetup.test.utility.AssistPage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;

public class LabelMapping extends Abstract {

    final static String WHSE = "800 - BETHLEHEM";
    final static String WHSE_NUM = "800";
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

    final static String WHSETEST = "800 - BETHLEHEM";
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


    @Test(priority = 1, description = "TC: Search drivers at the whse level")
    public void LabelMapping(String labelForm, String labelType) throws InterruptedException {

        extentTest =extent.startTest("TC: Validate driver on the Yard overlook page at warehouse"+WHSETEST +".");

//        //Fetch today's date and convert to a string plus_100.
//        Calendar dateTwo = Calendar.getInstance();
//        Date dateOne = dateTwo.getTime();
//        DateFormat dateForm = new SimpleDateFormat("YYMMddhhmmss");
//        String tDay = dateForm.format(dateOne);

        AssistPage.chooseModule(driver,"Label Setup");
        AssistPage.chooseWarehouse(driver,WHSE_NUM);

        AssistPage.sendInput(driver,"id","gs_lblFormat",labelForm);
        driver.findElement(By.id("gs_lblFormat")).sendKeys(Keys.ENTER);
        AssistPage.click(driver.findElement(By.id("labelForm")));
        AssistPage.chooseValueFromStandardDropDownBySubstring(driver,"9_lblType_chosen",labelType,"a.chosen-single");


    }
}
