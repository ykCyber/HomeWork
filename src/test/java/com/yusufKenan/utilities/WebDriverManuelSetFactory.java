package com.cybertek.utilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
public class WebDriverManuelSetFactory {
    public static WebDriver getDriver(String browserType){
        System.setProperty("webdriver.chrome.driver","C:\\Users\\Mustafa\\IdeaProjects\\EUTestNGProject\\drivers\\chromedriver.exe");
        WebDriver driver= null;
        switch (browserType.toLowerCase()){
            case "chrome":

                driver=new ChromeDriver();
                break;
            case "firefox":

                driver=new FirefoxDriver();
                break;
        }
        return driver;
    }
}