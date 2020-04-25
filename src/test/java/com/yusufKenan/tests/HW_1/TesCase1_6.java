package com.yusufKenan.tests.HW_1;

import com.github.javafaker.Faker;
import com.yusufKenan.utilities.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class TesCase1_6 {
    public Random rnd = new Random();
    String actualResult = "";
    String expectedlResult = "";
    WebDriver driver;

    @BeforeSuite(alwaysRun = true)
    public void setupSuite() {

    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() {

    }


    @BeforeClass
    public void
    beforeClass() {

        System.out.println("Before class");
        driver = WebDriverFactory.getDriver("chrome");

    }

    @Test
    public void testCase01() {
        driver.get("https://practice-cybertekschool.herokuapp.com/registration_form");
        WebElement element = driver.findElement(By.xpath("//small[contains(.,'The date of birth is not valid')]"));
        driver.findElement(By.name("birthday")).sendKeys("12*12*1212");
        String actual = element.getText();
        String expected = "The date of birth is not valid";
        Assert.assertEquals(actual, expected, "Verifiy not valid dob message");
    }

    @Test
    public void testcase02() {
        // verify c++, java, displayed
        List<WebElement> actualList = driver.findElements(By.className("form-check-inline"));

        String[] expected = new String[]{"c++", "java", "JavaScript"};
        List<String> expectedList = new ArrayList(Arrays.asList(expected));


        for (int i = 0; i < actualList.size(); i++) {
            Assert.assertEquals(actualList.get(i).getText().toLowerCase(), expected[i].toLowerCase(), "TestCase 2 Verify contains java c++....");


        }
    }

    @AfterMethod
    public void after() throws InterruptedException {
        Thread.sleep(5000);
        System.out.println("end of test");

    }

    @Test
    public void testcase03() {
        //name invalid data
        driver.get("https://practice-cybertekschool.herokuapp.com/registration_form");
        WebElement element1 = driver.findElement(By.xpath("(//i[@data-bv-icon-for])[1]"));
        System.out.println("element.isDisplayed() = " + element1.isDisplayed());
        System.out.println("element1 = " + element1.getAttribute("class"));
        WebElement element2 = driver.findElement(By.name("firstname"));
        char c = ((char) (rnd.nextInt(26) + 'a'));
        element2.sendKeys(Character.toString(c));
        element2.sendKeys(Character.toString(c));
        System.out.println("element1.isDisplayed() = " + element2.isDisplayed());
        System.out.println("element1 = " + element1.getAttribute("class"));


        String actual = driver.findElement(By.xpath("//small[contains(.,'first name must be more than 2 and less than 64 characters long')]")).getText();
        String expexted = "first name must be more than 2 and less than 64 characters long";
        Assert.assertEquals(actual, expexted, "Verify invalid last name input");
    }

    @Test
    public void testCase04() {
        //last name invalid data

        char c = ((char) (rnd.nextInt(26) + 'a'));
        driver.findElement(By.name("lastname")).sendKeys(Character.toString(c));
        String actual = driver.findElement(By.xpath("//small[contains(.,'The last name must be more than 2 and less than 64 characters long')]")).getText();
        String expexted = "The last name must be more than 2 and less than 64 characters long";
        Assert.assertEquals(actual, expexted, "Verify invalid last name input");
    }

    @Test
    public void testCase05() throws InterruptedException {
        //fill the form
        driver.get("https://practice-cybertekschool.herokuapp.com/");
        driver.findElement(By.linkText("Registration Form")).click();
        Faker jf = new Faker();
        String firstName = jf.name().firstName();
        String lastName = jf.name().lastName();
        WebElement element;
        element = driver.findElement(By.name("firstname"));
        element.sendKeys(firstName);
        element = driver.findElement(By.name("lastname"));
        element.sendKeys(lastName);
        element = driver.findElement(By.name("username"));
        element.sendKeys(jf.funnyName().name().replace(" ", ""));
        element = driver.findElement(By.name("email"));
        element.sendKeys(jf.internet().emailAddress());
        element = driver.findElement(By.name("password"));
        element.sendKeys(jf.internet().password(8, 10));
        element = driver.findElement((By.name("phone")));
        element.sendKeys("587-589-8000");
        element = driver.findElement(By.xpath("//input[@name='gender']"));
        element.click();
        System.out.println(element.getClass().toString());
        driver.findElement(By.name("birthday")).sendKeys("12/12/1212");
        element = driver.findElement(By.name("department"));
        Select select = new Select(element);
        select.selectByIndex(3);
        System.out.println("driver.findElement(By.xpath(\"(//i[@style])[8]\")).isDisplayed() = " + driver.findElement(By.xpath("(//i[@style])[8]")).isDisplayed());
        System.out.println("driver.findElement(By.xpath(\"(//i[@style])[8]\")).getText() = " + driver.findElement(By.xpath("(//i[@style])[8]")).getText());
        element = driver.findElement(By.name("job_title"));
        select = new Select(element);
        select.selectByIndex(3);
        Thread.sleep(3000);
        driver.findElement(By.id("inlineCheckbox2")).click();
        List<WebElement> element1 = driver.findElements(By.xpath("//i[@data-bv-icon-for]"));
            int i = 0;
        for (WebElement elementa : element1) {
            i++;
            actualResult = elementa.getAttribute("class");
            System.out.println("actualResult = " + actualResult);
            expectedlResult = "form-control-feedback glyphicon glyphicon-ok";
            System.out.println(elementa.getText());
            Assert.assertEquals(actualResult, expectedlResult,i+".verify OK tickMArk");
        }

        driver.findElement(By.id("wooden_spoon")).click();
        Thread.sleep(3000);
        String actual = driver.findElement(By.xpath("//p")).getText();
        Assert.assertEquals(actual, "You've successfully completed registration!");
    }

    @AfterClass
    public void
   afterClass() {
   //     driver.quit();
    }

    @Test
    public void testCase06() throws InterruptedException {
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
}
