package com.uscold.GoliveSetup.test.label;

import com.uscold.GoliveSetup.test.Abstract;
import com.uscold.GoliveSetup.test.utility.AssistPage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static com.uscold.GoliveSetup.test.utility.AssistPage.chooseValueFromStandardDropDownByTextMatch;
import static com.uscold.GoliveSetup.test.utility.AssistPage.getTestData;
import static com.uscold.GoliveSetup.test.utility.AssistPage.isElementPresen;

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
        AssistPage.chooseModule(driver,"Label Setup");
        AssistPage.chooseWarehouse(driver, WHSE_NUM);


        AssistPage.sendInput(driver,"id","gs_lblFormat",labelForm);
        driver.findElement(By.id("gs_lblFormat")).sendKeys(Keys.ENTER);

        //xpath for clicking on the first radio button
        AssistPage.click(driver.findElement(By.xpath("//tr[2][@class='ui-widget-content jqgrow ui-row-ltr']/td[1]")));
        AssistPage.click(driver.findElement(By.id("editRecord")));

        //AssistPage.click(driver.findElement(By.id("editRecord")));

//        AssistPage.chooseValueFromStandardDropDownBySubstring(driver,"1_lblType",,"a.chosen-single","chosen-container chosen-container-single");
        //AssistPage.chooseValueFromDropDown(driver,"//*[@id=\"1_lblType\"]",labelType,"a.chosen-single","//div[@class='chosen-drop']/ul/li");
        //*[@id="1_lblType"]/value['Outbound USCS Label']
        //selectDD(driver,".//div[@class='chosen-drop']//ul[@class='chosen-results']/li",labelType);
        //AssistPage.chooseValueFromStandardDropDownBySubstring(driver,"8_lblType",labelType);
        //click(driver.findElement(By.xpath("//*[@name='lblType']/option[text()='"+labelType+"']")));
//        System.out.println("This label was send from excel" + labelType);
//        driver.findElement(By.xpath("//table[@id='list']//a[contains(@class, 'chosen-single')]")).click();
//
//        Select objSelect = new Select(driver.findElement(By.xpath(".//div[@class='chosen-drop']//ul[@class='chosen-results']/li")));
//        driver.findElement(By.xpath("//table[@id='list']//div[contains(@class, 'chosen-drop')]/div[contains(@class, 'chosen-search')]/input")).sendKeys(labelType);
//        objSelect.getFirstSelectedOption().click();
//        System.out.println("This label was selected from dropdown" + labelType);

        //click(chooseValueFromBarcodePopUpGridDataFieldDropDown("new_row2_dataFieldDesc_chosen", labelType));

        driver.findElement(By.xpath("//table[@id='list']//a[contains(@class, 'chosen-single')]")).click();
        List<WebElement> accTypes = driver.findElements(By.xpath(".//div[@class='chosen-drop']//ul[@class='chosen-results']/li"));
        //((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", accTypes);
        accTypes.stream().filter(el -> el.getAttribute("innerText").trim().equalsIgnoreCase(labelType)).findFirst().get().click();
//        click(accTypes);

        AssistPage.click(driver.findElement(By.id("updateRecord")));
//        AssistPage.isElementPresen(driver.findElement(By.id("editRecord")));
        System.out.println("This label was selected from dropdown" + labelType);
    }

    //td[@role='gridcell']/*[@role="select"]/option
    @DataProvider()
    public Object[][]  getLoginData(){
        Object data [][] = AssistPage.getTestData("sheet1");
        return data;
    }

//    public static void selectDD (WebDriver driver,String xp, String valueName) {
//        List<WebElement> dd_menu = driver.findElements(By.id(xp));
//        //Select val = new Select(driver.findElement(By.xpath(id)));
//        for (int i = 0; i < dd_menu.size(); i++)
//        {
//            WebElement element = dd_menu.get(i);
//            String innerhtml = element.getText();
//
//            if (innerhtml.contentEquals(valueName))
//            {
//                element.click();
//                break;
//            }
//            System.out.println("values from dropdown" + innerhtml);
//            //val.selectByVisibleText(valueName);
//        }
//    }
//    public static void selectDD (WebDriver driver,String xp, String valueName) {
//        List<WebElement> dd_menu = driver.findElements(By.xpath(xp));
//        //List<WebElement> val =  driver.findElement(By.xpath(xp));
//        System.out.println("values from dropdown" + dd_menu);
//        for (int i = 0; i < dd_menu.size(); i++)
//        {
//            WebElement element = dd_menu.get(i);
//            String innerhtml = element.getText();
//
//            if (innerhtml.contains(valueName))
//            {
//                element.click();
//                break;
//            }
//            System.out.println("values from dropdown" + innerhtml);
//            //val.selectByVisibleText(valueName);
//        }
//    }


    //Capture New field - Grid - Data Field Dropdown
    private WebElement chooseValueFromBarcodePopUpGridDataFieldDropDown(String id, String value) {

        WebElement accTypeContainer = driver.findElement(By.id(id));
        WebElement aElem = accTypeContainer.findElement(By.cssSelector("a.chosen-single"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", aElem);
        click(aElem);
        List<WebElement> accTypes = accTypeContainer.findElements(By.xpath(".//div[@class='chosen-drop']//ul[@class='chosen-results']/li"));
//*[@id="new_row3_dataFieldDesc_chosen"]/a
        return accTypes.stream().filter(el -> el.getAttribute("innerText").trim().equalsIgnoreCase(value)).findFirst().get();


    }


}
