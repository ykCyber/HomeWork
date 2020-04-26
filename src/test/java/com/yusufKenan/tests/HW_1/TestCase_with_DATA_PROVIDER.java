package com.yusufKenan.tests.HW_1;

//import com.yusufKenan.utilities.WebDriverFactory;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.testng.Assert;
//import org.testng.annotations.AfterClass;
import com.yusufKenan.utilities.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class TestCase_with_DATA_PROVIDER {
//    WebDriver driver = WebDriverFactory.getDriver("chrome");
//
    @DataProvider
    public static Object[] bigiAL() { //              todo Data provider annotation helps to provide data var test cases
        //TODO-->return type should be Object Array (Object[]), set any name to your method(like: getDATA() or bilgiAL()
        //TODO-->                                                   Rows - Number of times your test has to be repeated.
        //TODO-->                                                           Columns - Number of parameters in test data.
        String[][] data = new String[4][1];// TODO-->             -->test will run 4 times and  need 1 data(status code)
        data[0][0] = "200";
        data[1][0] = "301";
        data[2][0] = "404";
        data[3][0] = "500";
        return new String[][]{{"ahmet"},{"mehmet"},{"hasan"},{"hüseyin"}};
    }
//
//    @Test(dataProvider = "bigiAL")
//      //TODO--->impliment (dataProvider = "bigiAL") next to your @Test annotation
//    public void test(String statusCode)  {
//        //TODO-->enter type of variable and name to your test constructor as param (String statusCode)
//        driver.get("https://practice-cybertekschool.herokuapp.com/");
//
//        driver.findElement(By.linkText("Status Codes")).click();
//
//        System.out.println(driver.getCurrentUrl());
//        driver.findElement(By.partialLinkText(statusCode)).click();//statusCode comming from dataProvider
//
//
//        String actualResult =       driver.findElement(By.xpath("//p")).getText().split("code.")[0];
//        System.out.println(driver.getCurrentUrl());
//        System.out.println("statusCode = " + statusCode);
//
//        System.out.println("actualResult = " + actualResult);
//
//        Assert.assertTrue(actualResult.contains(statusCode));
//
//    }
    @DataProvider(name = "takeData")
    public Object[][] takeData (Method a) {
       System.out.println(a.getName());  // print test method name
        return new Object[][] {{ "Cedric"},{ "Ken"}};
    }

    @Test (dataProvider = "takeData")
    public void test1(String s) {
        for (int i = 0; i < 10; i++) {


        }
        System.out.println("s = " + s);
    }



    @Test(dataProvider = "takeData")
    public void test2(String data) {
        System.out.println("data = " + data);
    }
}

