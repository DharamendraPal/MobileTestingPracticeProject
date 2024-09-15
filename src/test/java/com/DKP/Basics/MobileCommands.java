package com.DKP.Basics;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.appium.java_client.android.AndroidDriver;

public class MobileCommands {
	
	//static AppiumDriver driver;
	static AndroidDriver driver;//polymorphic reference
	public static void main(String[] args) throws MalformedURLException, InterruptedException {
		System.out.println("hi");
		testApp();
	}
	public static void testApp() throws MalformedURLException, InterruptedException{
		startAPP("D:\\MobileAutomationLearning\\AppsApk\\Facebook.apk");
		Thread.sleep(3);
		driver.activateApp("");//Accepts app package and launches that app
		Thread.sleep(3);
		driver.close();
		driver.quit();
	}
	
	public static void startAPP(String apkPath) throws MalformedURLException, InterruptedException {
		System.out.println("Starting Commands Execution");
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability("appium:deviceName", "OPPO A77s");
		cap.setCapability("appium:udid", "f348518");
		cap.setCapability("platformName", "Android");
		cap.setCapability("appium:platformVersion", "14");
		cap.setCapability("appium:automationName", "UiAutomator2");
		cap.setCapability("appium:autoGrantPermissions", true);//it is always good to have this config
		cap.setCapability("appium:fullReset", "true");//if it is true then install APP again and if it is false then it will not 
		cap.setCapability("appium:noReset", "false");//if it is true do not clear Cache
		//cap.setCapability("appium:appPackage", "com.google.android.apps.messaging");
		//cap.setCapability("appium:appActivity", "com.google.android.apps.messaging.ui.ConversationListActivity");
		//cap.setCapability("appium:appPackage", "com.google.android.apps.messaging");
		//cap.setCapability("appium:appActivity", "com.google.android.apps.messaging.gaia.expresssignin.BugleExpressSignInActivity");
		
		cap.setCapability("appium:app",apkPath);
		//URL url= new URL("http://127.0.0.1:4723/wd/hub");
		URL url= new URL("http://192.168.116.29:4723/");
		//driver= new AppiumDriver(url,cap);
		driver = new AndroidDriver(url, cap);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		System.out.println("Application Launched!!!");
		//String msg=driver.findElement(By.xpath("(//*[@class='android.widget.TextView'])[3]")).getText();
		//System.out.println("Message is "+msg);
		Thread.sleep(1000);
		
	}
	
}
