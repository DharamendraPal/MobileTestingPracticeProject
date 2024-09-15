package com.DKP.Basics;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class ShopersStopTest {
	
	//static AppiumDriver driver;
	static AndroidDriver driver;
	public static void main(String[] args) throws MalformedURLException, InterruptedException {
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
		//cap.setCapability("appium:noReset", "false");//if it is true do not clear Cache
		
		//cap.setCapability("appium:appPackage", "shoppersstop.shoppersstop");
		//cap.setCapability("appium:appActivity", "com.shoppersstop.apps.ui.login.LoginSignUpActivity");
		
		cap.setCapability("appium:app", "D:\\MobileAutomationLearning\\AppsApk\\Shoppersstop.apk");
	
		//URL url= new URL("http://127.0.0.1:4723/wd/hub");
		URL url= new URL("http://192.168.116.29:4723/");
		//driver= new AppiumDriver(url,cap);
		driver = new AndroidDriver(url, cap);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		System.out.println("ShopersStop Application Launched!!!");
		driver.findElement(AppiumBy.xpath("//*[@class='android.widget.EditText']")).sendKeys("");
		driver.findElement(AppiumBy.xpath("//*[@class='android.widget.ImageButton']")).click();
		//driver.findElement(AppiumBy.xpath("//*[@resource-id='shoppersstop.shoppersstop:id/txtSkip']")).click();
		Thread.sleep(2000);
		
		// Step 2: Retrieve OTP from Messaging App
		driver.activateApp("com.google.android.apps.messaging");
		Thread.sleep(1000);
		System.out.println("Message Application Launched!!!");
		
		// Read the OTP from the message
		//String msg = driver.findElement(By.xpath("(//*[@class='android.widget.TextView'])[4]")).getText();
		String msg=driver.findElement(By.xpath("(//*[@resource-id=\"com.google.android.apps.messaging:id/conversation_snippet\"])[1]")).getText();
		System.out.println("Message is: " + msg);
		String OTPNUmber = msg.replaceAll("[^0-9]", "");  // Extract the numeric part
		System.out.println("OTP is: " + OTPNUmber);
		
		// Step 3: Switch back to the Shoppers Stop app
		driver.runAppInBackground(Duration.ofSeconds(30));  // Minimize the messaging app and bring ShoppersStop back to the foreground
		System.out.println("Switched back to Shoppers Stop app!");
		Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("//*[@resource-id='shoppersstop.shoppersstop:id/edittext_otp_0']")));
		// Step 4: Wait for the OTP input fields to appear and enter the OTP
		for (int i = 0; i < OTPNUmber.length(); i++) 
		{
			driver.findElement(AppiumBy.xpath("//*[@resource-id='shoppersstop.shoppersstop:id/edittext_otp_" + i + "']")).sendKeys(String.valueOf(OTPNUmber.charAt(i)));
		}
		System.out.println("OTP entered successfully!");
				
		/*String OTPNUmber =msg.substring(0, 5);
		System.out.println("OTP is"+OTPNUmber);
		char [] OTPNUmber1 =msg.substring(0, 5).toCharArray();
		 */
		/*
		// Loop through each digit in the OTP and enter it into the corresponding field
		for (int i = 0; i < OTPNUmber1.length; i++) {
		    // Find the OTP input field by resource-id and input the corresponding OTP digit
		    WebElement otpField = driver.findElement(AppiumBy.xpath("//*[@resource-id='shoppersstop.shoppersstop:id/edittext_otp_" + i + "']"));
		    otpField.sendKeys(Character.toString(OTPNUmber1[i]));
		    System.out.println("Entered OTP digit: " + OTPNUmber1[i] + " in field: " + i);
		}	
		*/
		
		
		//click on Submit OTP Button
		driver.findElement(AppiumBy.xpath("//*[@resource-id=\"shoppersstop.shoppersstop:id/button_submit_otp\"]")).click();
		//Click on Resent OTP Button
		//driver.findElement(AppiumBy.xpath("//*[@resource-id=\"shoppersstop.shoppersstop:id/textview_not_recieved_otp_lbl\"]")).click();
	}	
	
}
