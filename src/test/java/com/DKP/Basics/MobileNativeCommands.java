package com.DKP.Basics;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class MobileNativeCommands {
	
	//static AppiumDriver driver;
	static AndroidDriver driver;//polymorphic reference
	public static void main(String[] args) throws MalformedURLException, InterruptedException {
		System.out.println("hi");
		testApp();
	}
	public static void testApp() throws MalformedURLException, InterruptedException{
		//startAPP("D:\\MobileAutomationLearning\\AppsApk\\Facebook.apk");
		startAPP("D:\\MobileAutomationLearning\\AppsApk\\SauceLabs.2.7.1.apk");
		Thread.sleep(3);
		//driver.activateApp("");//Accepts app package and launches that app
		Thread.sleep(3);
		
		driver.pressKey(new KeyEvent(AndroidKey.HOME));
		//System.out.println(driver.getOrientation());//This Sauce Lab App does not support Orientation
		Thread.sleep(3);
		System.out.println(driver.getConnection().toString());
		driver.toggleWifi();
		//driver.toggleAirplaneMode();//check 
		Thread.sleep(3);

/*		  try 
		  { 
			  driver.rotate(ScreenOrientation.LANDSCAPE);// This will throw error if
			  System.out.println("App got landscape");
		  }
		  //APP does not supports Landscape //Assert.fail(); } 
		  catch (Exception e) {
			 // Assert.Success(); 
			  System.out.println("App did not got landscape");
		  }
		  */
		 
		/*
		System.out.println(driver.isDeviceLocked());
		if(!driver.isDeviceLocked())
		{
			driver.lockDevice();
			System.out.println("Device got locked");
		}
		Thread.sleep(2);
		driver.unlockDevice();
		*/
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
		cap.setCapability("appium:appPackage", "com.swaglabsmobileapp");
		cap.setCapability("appium:appActivity", "com.swaglabsmobileapp.MainActivity");
		//cap.setCapability("appium:app", "D:\\MobileAutomationLearning\\AppsApk\\SauceLabs.2.7.1.apk");
		//cap.setCapability("appium:appPackage", "com.google.android.apps.messaging");
		//cap.setCapability("appium:appActivity", "com.google.android.apps.messaging.ui.ConversationListActivity");
		//cap.setCapability("appium:appPackage", "com.google.android.apps.messaging");
		//cap.setCapability("appium:appActivity", "com.google.android.apps.messaging.gaia.expresssignin.BugleExpressSignInActivity");
		
		cap.setCapability("appium:app",apkPath);
		//URL url= new URL("http://127.0.0.1:4723/wd/hub");
		URL url= new URL("http://192.168.32.29:4723/");
		//driver= new AppiumDriver(url,cap);
		driver = new AndroidDriver(url, cap);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		System.out.println("Application Launched!!!");
		//String msg=driver.findElement(By.xpath("(//*[@class='android.widget.TextView'])[3]")).getText();
		//System.out.println("Message is "+msg);
		Thread.sleep(1000);
		
	}
	
}
