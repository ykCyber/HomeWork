package com.yusufKenan.tests.homeWork1;

import com.github.javafaker.Faker;
import com.yusufKenan.utilities.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class TestCase2 {
    /*Test case #6 Step 1. Go to "https://www.tempmailaddress.com/"Step
    2. Copy and save email as a string.Step
    3. Then go to “https://practice-cybertekschool.herokuapp.com”Step
    4. And click on “Sign Up For Mailing List".Step 5. Enter any valid name.Step
    6. Enter email from the Step 2.Step 7. Click Sign Up
     */
    @Test
    public void test() throws InterruptedException {
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
        Assert.assertEquals(actualResult,expectedResult);
        actualResult = driver.findElement(By.id("predmet")).getText();
        expectedResult = "Thanks for subscribing to practice.cybertekschool.com!";
        Assert.assertEquals(actualResult,expectedResult);
        //Step 9. Navigate back to the “https://www.tempmailaddress.com/
        // ”Step 10. Verify that you’ve received an email from “do-not-reply@practice.cybertekschool.com”
        // Step 11. Click on that email to open it.
        // Step 12. Verify that email is from: “do-not-reply@practice.cybertekschool.com”Step
        // 13. Verify that subtject is: “Thanks for subscribing to practice.cybertekschool.com!”
    }
}
