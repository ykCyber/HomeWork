package com.yusufKenan.tests.homeWork1;

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

public class HomeWork1 {
    WebDriver driver = WebDriverFactory.getDriver("chrome");
    public Random rnd = new Random();

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
//        driver.manage().window().maximize();
    }

    @BeforeTest
    public void before() {
        driver.get("https://practice-cybertekschool.herokuapp.com/");
        driver.findElement(By.linkText("Registration Form")).click();

    }




    @Test
    public void testCase1() {
        //DOB not valid


        WebElement element = driver.findElement(By.xpath("//small[contains(.,'The date of birth is not valid')]"));
        driver.findElement(By.name("birthday")).sendKeys("12*12*1212");
        String actual = element.getText();
        String expected = "The date of birth is not valid";
        Assert.assertEquals(actual, expected, "Verifiy not valid dob message");
    }

    @Test
    public void testcase2() {
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
        System.out.println("bitti kapayıyorum");

    }

    @Test
    public void testcase3() {
        //name invalid data

        char c = ((char) (rnd.nextInt(26) + 'a'));
        WebElement element = driver.findElement(By.name("firstname"));
        element.sendKeys(Character.toString(c));
        String actual = driver.findElement(By.xpath("//small[contains(.,'first name must be more than 2 and less than 64 characters long')]")).getText();
        String expexted = "first name must be more than 2 and less than 64 characters long";
        Assert.assertEquals(actual, expexted, "Verify invalid last name input");
    }

    @Test
    public void testCase4() {
        //last name invalid data

        char c = ((char) (rnd.nextInt(26) + 'a'));
        driver.findElement(By.name("lastname")).sendKeys(Character.toString(c));
        String actual = driver.findElement(By.xpath("//small[contains(.,'The last name must be more than 2 and less than 64 characters long')]")).getText();
        String expexted = "The last name must be more than 2 and less than 64 characters long";
        Assert.assertEquals(actual, expexted, "Verify invalid last name input");
    }

    @Test
    public void testCase5() throws InterruptedException {
        //fill the form
        driver.get("https://practice-cybertekschool.herokuapp.com/");
        driver.findElement(By.linkText("Registration Form")).click();
        char c = ((char) (rnd.nextInt(26) + 'a'));
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
        element = driver.findElement(By.name("job_title"));
        select = new Select(element);
        select.selectByIndex(3);
        driver.findElement(By.id("inlineCheckbox2")).click();
        driver.findElement(By.id("wooden_spoon")).click();
        Thread.sleep(3000);
        String actual = driver.findElement(By.xpath("//p")).getText();
        Assert.assertEquals(actual, "You've successfully completed registration!");


    }




    @AfterClass
    public void
    afterClass() {
        System.out.println("after class");
        driver.quit();
    }
}
