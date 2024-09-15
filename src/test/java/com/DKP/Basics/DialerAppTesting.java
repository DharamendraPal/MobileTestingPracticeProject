package com.DKP.Basics;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;

public class DialerAppTesting {
	
	//static AppiumDriver driver;
	static AndroidDriver driver;
	public static void main(String[] args) throws MalformedURLException, InterruptedException {
		System.out.println("hi");
		launchApp();	
	}
	
	public static void launchApp() throws MalformedURLException, InterruptedException {
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability("appium:deviceName", "OPPO A77s");
		cap.setCapability("appium:udid", "");
		cap.setCapability("platformName", "Android");
		cap.setCapability("appium:platformVersion", "14");
		cap.setCapability("appium:automationName", "UiAutomator2");
		cap.setCapability("appium:autoGrantPermissions", true);//it is always good to have this config
		//cap.setCapability("appium:fullReset", "true");//if it is true then install APP again and if it is false then it will not 
		cap.setCapability("appium:noReset", "false");//if it is true do not clear Cache
		cap.setCapability("appium:appPackage", "com.google.android.dialer");
		cap.setCapability("appium:appActivity", "com.google.android.dialer.extensions.GoogleDialtactsActivity");
		//URL url= new URL("http://127.0.0.1:4723/wd/hub");
		URL url= new URL("http://192.168.116.29:4723/");
		driver = new AndroidDriver(url, cap);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		System.out.println("Dailer Application Launched!!!");
		driver.findElement(By.xpath("(//*[@class='android.widget.ImageButton'])[1]")).click();
		Thread.sleep(1000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@text='9']"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.TextView[@text='9']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@text='6']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@text='9']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@text='1']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@text='3']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@text='5']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@text='4']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@text='5']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@text='5']"))).click();
		driver.findElement(By.xpath("//*[@class='android.widget.Button']")).click();	
	}	
}
