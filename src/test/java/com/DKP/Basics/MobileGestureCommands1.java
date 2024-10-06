package com.DKP.Basics;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebElement;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class MobileGestureCommands1 {
	
	//static AppiumDriver driver;
	static AndroidDriver driver;//polymorphic reference
	public static void main(String[] args) throws MalformedURLException, InterruptedException {
		System.out.println("hi");
		testApp();
	}
	public static void testApp() throws MalformedURLException, InterruptedException{
		startAPP("D:\\MobileAutomationLearning\\AppsApk\\ApiDemosApp.apk");
		Thread.sleep(3);
		
	}
	
	public static void startAPP(String apkPath) throws MalformedURLException, InterruptedException {
		System.out.println("Starting Commands Execution");
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability("appium:deviceName", "OPPO A77s");
		cap.setCapability("appium:udid", "");
		cap.setCapability("platformName", "Android");
		cap.setCapability("appium:platformVersion", "14");
		cap.setCapability("appium:automationName", "UiAutomator2");
		cap.setCapability("appium:autoGrantPermissions", true);//it is always good to have this config
		cap.setCapability("appium:fullReset", "true");//if it is true then install APP again and if it is false then it will not 
		cap.setCapability("appium:noReset", "false");//if it is true do not clear Cache
		//cap.setCapability("appium:appPackage", "io.appium.andriod.apis");
		cap.setCapability("appium:app", "D:\\MobileAutomationLearning\\AppsApk\\ApiDemosApp.apk");
		cap.setCapability("appium:app",apkPath);
		//URL url= new URL("http://127.0.0.1:4723/wd/hub");
		//URL url= new URL("http://192.168.116.29:4723/");
		URL url= new URL("http://192.168.32.29:4723/");
		//driver= new AppiumDriver(url,cap);
		driver = new AndroidDriver(url, cap);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		System.out.println("Application Launched!!!");
		// click on Views
		driver.findElement(AppiumBy.accessibilityId("Views")).click();

		// Click on Drag and Drop
		//driver.findElement(AppiumBy.accessibilityId("Drag and Drop")).click();

		// Find the Red Dot1 element
		//WebElement element = driver.findElement(AppiumBy.id("io.appium.android.apis:id/drag_dot_1"));

		// Long CLick on the First Red Circle
		//dragAndDrop(element);
		//dragAndDrop(); // This method will be called when No Elements are used
		//Scroll Till Last Element
		ScrollTillDown();
	}
	
	
	
	public static void longCLick(WebElement element) {
		driver.executeScript("mobile: longClickGesture", ImmutableMap.of(
				"elementId", ((RemoteWebElement) element).getId(),
				"x", 200,
				"y", 585,
				"duration", 3000 // The Duration till which you want to keep the LongClick
		));
	}
	public static void dragAndDrop(WebElement element) {
		driver.executeScript("mobile: dragGesture", ImmutableMap.of(
				"elementId", ((RemoteWebElement) element).getId(),
				"endX", 483,// It is the X coordinate where you want to Drop
				"endY", 706,// It is the y coordinate where you want to Drop
				"speed",500
		));
	}
	public static void dragAndDrop() {
		driver.executeScript("mobile: dragGesture", ImmutableMap.of(
				//elementId", ((RemoteWebElement) element).getId(),
				"startX", 200,// It is the X coordinate for the Element which you want to Drag
				"startY", 585,// It is the Y coordinate for the Element which you want to Drag
				"endX", 483,// It is the X coordinate where you want to Drop
				"endY", 706,// It is the y coordinate where you want to Drop
				"speed",500
		));
	}
	
	public static void ScrollTillDown() {
		boolean canScrollMore = (boolean)driver.executeScript("mobile: scrollGesture", ImmutableMap.of(
		    "left", 500, 
		    "top", 500, 
		    "width", 200, 
		    "height", 200,
		    "direction", "down",
		    "percent", 10.0
		));
		
		String element=driver.findElement(AppiumBy.accessibilityId("WebView3")).getText();
		System.out.println(element);
		if(element.contains("WebView3"))
		{
			System.out.println("Scroll Till Last on View Page");
		}
	
	
	}
	
}
