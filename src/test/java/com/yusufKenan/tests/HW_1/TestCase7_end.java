package com.yusufKenan.tests.HW_1;

import com.github.javafaker.Faker;
import com.yusufKenan.utilities.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestCase7_end {
    public Random rnd = new Random();
    String actualResult = "";
    String expectedResult = "";
    WebDriver driver;

    @BeforeSuite(alwaysRun = true)
    public void setupSuite() {
        driver = WebDriverFactory.getDriver("chrome");

    }

    @AfterTest(alwaysRun = true)
    public void tearDown() {
        driver.close();
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
    public void fakeMail() throws InterruptedException {
        WebDriver driver = WebDriverFactory.getDriver("chrome");
        driver.get("https://www.fakemail.net/");
        Faker jf = new Faker();

        WebElement email = driver.findElement(By.xpath("//span[@id='email']"));
        String emailData = email.getText();
        System.out.println(driver.findElement(By.xpath("//span[@id='email']")).getText());
        driver.navigate().to("https://practice-cybertekschool.herokuapp.com/");
        driver.findElement(By.xpath("//a[@href='/sign_up']")).click();
        driver.findElement(By.name("full_name")).sendKeys(jf.name().fullName());
        driver.findElement(By.name("email")).sendKeys(emailData);
        driver.findElement(By.name("wooden_spoon")).click();
        Thread.sleep(2000);
        String actualResult = driver.findElement(By.name("signup_message")).getText();
        Assert.assertTrue(driver.findElement(By.name("signup_message")).isDisplayed());
        String expectedResult = "Thank you for signing up. Click the button below to return to the home page.";
        Assert.assertEquals(actualResult, expectedResult, "Verify signup OK");
        driver.navigate().to("https://www.fakemail.net/");
        Thread.sleep(5000);
        driver.findElement(By.xpath("//td[contains(.,' do-not-reply@practice.cybertekschool.com')]")).click();
        actualResult = driver.findElement(By.id("odesilatel")).getText();
        expectedResult = "do-not-reply@practice.cybertekschool.com";
        Assert.assertEquals(actualResult, expectedResult);
        actualResult = driver.findElement(By.id("predmet")).getText();
        expectedResult = "Thanks for subscribing to practice.cybertekschool.com!";
        Assert.assertEquals(actualResult, expectedResult);
        //Step 9. Navigate back to the “https://www.tempmailaddress.com/
        // ”Step 10. Verify that you’ve received an email from “do-not-reply@practice.cybertekschool.com”
        // Step 11. Click on that email to open it.
        // Step 12. Verify that email is from: “do-not-reply@practice.cybertekschool.com”Step
        // 13. Verify that subtject is: “Thanks for subscribing to practice.cybertekschool.com!”
    }

    @Test
    public void testCase9() {

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
        for (int i = 0; i <codes.size() ; i++) {
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

