package com.yusufKenan.tests.HW_1;

import com.github.javafaker.Faker;
import com.yusufKenan.utilities.WebDriverFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestCase7_12 {
    public Random rnd = new Random();
    String actualResult = "";
    String expectedResult = "";
    WebDriver driver;

    @BeforeSuite(alwaysRun = true)
    public void setupSuite() {
        driver = WebDriverFactory.getDriver("chrome");
    }

    @AfterTest()
    public void tearDown() {
        driver.quit();
    }
    @BeforeClass
    public void
    beforeClass() {

        System.out.println("Before class");
        driver.manage().window().maximize();
    }

    /*Test case #6 Step 1. Go to "https://www.tempmailaddress.com/"Step
    2. Copy and save email as a string.Step
    3. Then go to “https://practice-cybertekschool.herokuapp.com”Step
    4. And click on “Sign Up For Mailing List".Step 5. Enter any valid name.Step
    6. Enter email from the Step 2.Step 7. Click Sign Up
     */
    @Test
    public void test() {
        driver.get("https://practice-cybertekschool.herokuapp.com/autocomplete");
        expectedResult = "United States of America";
        driver.findElement(By.id("myCountry")).sendKeys(expectedResult);
        driver.findElement(By.xpath("//input[@onclick='log()']")).click();
        actualResult = driver.findElement(By.id("result")).getText();
        Assert.assertTrue(actualResult.contains(expectedResult), "Verify message");
    }

    @Test
    public void testCase08() throws InterruptedException {
        driver.get("https://practice-cybertekschool.herokuapp.com");
        System.out.println(driver.getCurrentUrl());


        driver.findElement(By.partialLinkText("Autocomplete")).click();

        System.out.println("driver.getCurrentUrl() = " + driver.getCurrentUrl());
        WebElement element = driver.findElement(By.id("myCountry"));
        element.sendKeys("united");
        element.sendKeys(Keys.ARROW_DOWN);
        element.sendKeys(Keys.ARROW_DOWN);
        element.sendKeys(Keys.ARROW_DOWN);
        element.sendKeys(Keys.ENTER);
        // driver.findElement(By.xpath("//*[@value='United States of America']")).click();
        //element = driver.findElement(By.id("myCountry"));
        element.sendKeys(Keys.TAB);
        element.sendKeys(Keys.ENTER);
        element.sendKeys(Keys.ENTER);
        Thread.sleep(2000);
        System.out.println("driver.findElement(By.id(\"result\")).getText().toString() = " + driver.findElement(By.id("result")).getText().toString());

    }

    @Test
    public void testCase007() throws InterruptedException {
        driver.get("https://practice-cybertekschool.herokuapp.com/upload");
        WebElement uploadElement = driver.findElement(By.id("file-upload"));
        // enter the file path onto the file-selection input field
        uploadElement.sendKeys("C:\\Users\\mksimsir\\Desktop\\asd.txt");
        Thread.sleep(2000);
        driver.findElement(By.id("file-submit")).click();
        Thread.sleep(2000);
        actualResult = driver.findElement(By.id("uploaded-files")).getText();
        expectedResult = "asd.txt";
        Assert.assertEquals(actualResult, expectedResult, "Uploaded file name displayed");
//        driver.findElement(By.id("uploaded-files")).click();
//        Thread.sleep(2000);
//        driver.findElement(By.id("uploaded-files")).click();

    }

    @Test

    public void testCase09() {

        driver.get("https://practice-cybertekschool.herokuapp.com/status_codes/200");

        actualResult = driver.findElement(By.xpath("//p")).getText();
        expectedResult = "This page returned a 200 status code";
        System.out.println("expectedlResult = " + expectedResult);
        System.out.println("actualResult = " + actualResult);
        Assert.assertTrue(actualResult.contains(expectedResult), "Verifiy status code 200");
    }

    @Test
    public void TestCase10forLoopClick() throws InterruptedException {
        driver.get("https://practice-cybertekschool.herokuapp.com/status_codes");
        // there is 4 status code under same parent, create a WebElement list of them
        List<WebElement> allCodes = driver.findElements(By.xpath("//div//ul/li"));
        List<String> codes = new ArrayList<>();
        for (WebElement allElement : allCodes) {
            codes.add(allElement.getText());
        }
        for (int i = 0; i < codes.size(); i++) {
            driver.findElement(By.partialLinkText(codes.get(i))).click();
            expectedResult = "This page returned a " + codes.get(i).toString() + " status code";
            actualResult = driver.findElement(By.xpath("//p")).getText();
            System.out.println("actualResult = " + actualResult);
            System.out.println("expectedResult = " + expectedResult);

            Assert.assertTrue(actualResult.contains(expectedResult), "verify code");
            Thread.sleep(2000);
            driver.navigate().back();
            Thread.sleep(2000);
        }

    }


    @Test
    public void test10alternate() throws InterruptedException {
        //navigate to related link
        driver.get("https://practice-cybertekschool.herokuapp.com/status_codes");
        // there is 4 status code under same parrent, create a WebElement list of them
        List<WebElement> allElements = driver.findElements(By.xpath("//div//ul/li"));

        // get the codes "text"  from allElemets LIST one by one,
        List codes = new ArrayList();
        for (WebElement allElement : allElements) {
            codes.add(allElement.getText());
            //code.add(200);
            //code.add(301);
        }

        for (int i = 0; i < codes.size(); i++) {
            String xpathAdress = "//a[contains(.,'" + codes.get(i).toString() + "')]";
            driver.findElement(By.xpath(xpathAdress)).click();
            expectedResult = "This page returned a " + codes.get(i).toString() + " status code";
            actualResult = driver.findElement(By.xpath("//p")).getText();
            System.out.println("actualResult = " + actualResult);
            System.out.println("expectedResult = " + expectedResult);
            Assert.assertTrue(actualResult.contains(expectedResult), "verify code");
            driver.navigate().back();
        }
    }
}

