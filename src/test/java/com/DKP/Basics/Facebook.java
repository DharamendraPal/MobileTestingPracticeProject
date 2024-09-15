package com.DKP.Basics;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.appium.java_client.android.AndroidDriver;

public class Facebook {
	
	//static AppiumDriver driver;
	static AndroidDriver driver;
	public static void main(String[] args) throws MalformedURLException {
		System.out.println("hi");
		
		launchApp();
		
	}
	
	public static void launchApp() throws MalformedURLException {
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability("appium:deviceName", "OPPO A77s");
		cap.setCapability("appium:udid", "");
		cap.setCapability("platformName", "Android");
		cap.setCapability("appium:platformVersion", "14");
		cap.setCapability("appium:automationName", "UiAutomator2");
		cap.setCapability("appium:noReset", "false");
		cap.setCapability("appium:fullReset", "true");
		cap.setCapability("appium:app", "D:\\MobileAutomationLearning\\AppsApk\\Facebook.apk");
		cap.setCapability("appium:appPackage", "com.facebook.katana");
		cap.setCapability("appium:appActivity", "com.facebook.katana.LoginActivity");
		//URL url= new URL("http://127.0.0.1:4723/wd/hub");
		URL url= new URL("http://192.168.116.29:4723/");
		//driver= new AppiumDriver(url,cap);
		driver = new AndroidDriver(url, cap);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		System.out.println("Application Launched!!!");
		driver.findElement(By.xpath("(//*[@class='android.widget.EditText'])[1]")).sendKeys("Modi12348@gmail.com");
		driver.findElement(By.xpath("(//*[@class='android.widget.EditText'])[2]")).sendKeys("abc133");
		driver.findElement(By.xpath("//android.widget.Button[@content-desc=\"Log in\"]")).click();
		String errorDialogXPATH="//*[@resource-id='android:id/content']";		
		if(isElementPresent(errorDialogXPATH))
		{
			String errorMessage = driver.findElement(By.xpath("//*[@resource-id='android:id/content']")).getText();
	        System.out.println("Error message displayed: " + errorMessage);
	        driver.findElement(By.xpath("//*[@class='android.widget.Button']")).click();
	        System.out.println("Handled invalid credentials dialog.");
	        System.out.println("Test Case Passed");
			
		}
		else
		{
			  System.out.println("Not able to Handled invalid credentials dialog.box");
		      System.out.println("Test Case Failed");
		}	
	}
	public static boolean isElementPresent(String key) 
	{

		try {
				
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(key)));
				return true;
			
		} catch (Throwable t) 
		{
			
			return false;
		}
	}
	
}
