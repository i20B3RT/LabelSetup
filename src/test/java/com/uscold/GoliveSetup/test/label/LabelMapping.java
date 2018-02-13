package com.uscold.GoliveSetup.test.label;

import com.uscold.GoliveSetup.test.Abstract;
import com.uscold.GoliveSetup.test.utility.AssistPage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.uscold.GoliveSetup.test.utility.AssistPage.getTestData;

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
    @Test(priority = 1,dataProvider = "getLoginData")
    public void LabelMapping(String labelForm, String labelType) throws InterruptedException {

        extentTest =extent.startTest("TC: Validate driver on the Yard overlook page at warehouse"+WHSETEST +".");

//        //Fetch today's date and convert to a string plus_100.
//        Calendar dateTwo = Calendar.getInstance();
//        Date dateOne = dateTwo.getTime();
//        DateFormat dateForm = new SimpleDateFormat("YYMMddhhmmss");
//        String tDay = dateForm.format(dateOne);
        AssistPage.chooseWarehouse(driver,WHSE_NUM);
        AssistPage.chooseModule(driver,"Label Setup");


        AssistPage.sendInput(driver,"id","gs_lblFormat",labelForm);
        driver.findElement(By.id("gs_lblFormat")).sendKeys(Keys.ENTER);

        AssistPage.click(driver.findElement(By.xpath("//tr[@id='1']/td[1]/input[contains(@class, 'gridRadio')]")));
        AssistPage.click(driver.findElement(By.id("editRecord")));

        //AssistPage.click(driver.findElement(By.id("editRecord")));

//        AssistPage.chooseValueFromStandardDropDownBySubstring(driver,"1_lblType",,"a.chosen-single","chosen-container chosen-container-single");
        //AssistPage.chooseValueFromDropDown(driver,"//*[@id=\"1_lblType\"]",labelType,"a.chosen-single","//div[@class='chosen-drop']/ul/li");
        //*[@id="1_lblType"]/value['Outbound USCS Label']
        AssistPage.selectDD(driver,"//*[@id=\"1_lblType_chosen\"]",labelType);

    }

    @DataProvider()
    public Object[][]  getLoginData(){
        Object data [][] = AssistPage.getTestData("sheet1");
        return data;
    }




}
