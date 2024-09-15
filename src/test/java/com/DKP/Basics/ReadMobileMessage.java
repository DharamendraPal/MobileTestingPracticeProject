package com.DKP.Basics;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.appium.java_client.android.AndroidDriver;

public class ReadMobileMessage {
	
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
		cap.setCapability("appium:appPackage", "com.google.android.apps.messaging");
		cap.setCapability("appium:appActivity", "com.google.android.apps.messaging.ui.ConversationListActivity");
		//cap.setCapability("appium:appPackage", "com.google.android.apps.messaging");
		//cap.setCapability("appium:appActivity", "com.google.android.apps.messaging.gaia.expresssignin.BugleExpressSignInActivity");
		
		
		//URL url= new URL("http://127.0.0.1:4723/wd/hub");
		URL url= new URL("http://192.168.32.29:4723/");
		//driver= new AppiumDriver(url,cap);
		driver = new AndroidDriver(url, cap);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

		//
		// Handle the pop-up
        try {
            // Wait for the pop-up to be visible
            WebElement popUp = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@resource-id='com.google.android.apps.messaging:id/secondary_action_button']"))); 
            System.out.println("Selecting Account Pop-up is visible.");

            // Optionally handle the pop-up
            WebElement useWithoutAccountButton = driver.findElement(By.xpath("//*[@resource-id='com.google.android.apps.messaging:id/secondary_action_button']"));
            useWithoutAccountButton.click();
            System.out.println("Selected 'Use Messages without an account' .");
        } catch (Exception e) {
            System.out.println("Selecting Account Pop-up did not appear or could not be handled.");
        }
        
		System.out.println("Message Application Launched!!!");
		String msg=driver.findElement(By.xpath("(//*[@class='android.widget.TextView'])[3]")).getText();
		System.out.println("Message is "+msg);
		
	}	
}
