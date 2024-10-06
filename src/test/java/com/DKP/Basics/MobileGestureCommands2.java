package com.DKP.Basics;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebElement;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class MobileGestureCommands2 {

	public static AndroidDriver driver;

	public static void main(String[] args) throws MalformedURLException, InterruptedException {

		DesiredCapabilities cap = new DesiredCapabilities();

		cap.setCapability("appium:deviceName", "");
		cap.setCapability("appium:udid", "");
		cap.setCapability("platformName", "Android");
		cap.setCapability("appium:platformVersion", "14");
		cap.setCapability("appium:automationName", "uiAutomator2");
		cap.setCapability("appium:autoGrantPermissions", true);//it is always good to have this config
		//cap.setCapability("appium:fullReset", "true");//if it is true then install APP again and if it is false then it will not 
		//cap.setCapability("appium:noReset", "false");//if it is true do not clear Cache
		cap.setCapability("appium:app", "D:\\MobileAutomationLearning\\AppsApk\\ApiDemosApp.apk");
		cap.setCapability("appium:appPackage", "io.appium.android.apis");
		cap.setCapability("appium:appActivity", "io.appium.android.apis.ApiDemos");
		 //cap.setCapability("appium:appPackage", "com.google.android.apps.maps");
		 //cap.setCapability("appium:appActivity", "com.google.android.maps.MapsActivity");

		URL url= new URL("http://192.168.32.29:4723/");
		driver = new AndroidDriver(url, cap);
		Thread.sleep(3);
		
		// click on Views
		driver.findElement(AppiumBy.accessibilityId("Views")).click();
		driver.findElement(AppiumBy.accessibilityId("Gallery")).click();
		driver.findElement(AppiumBy.accessibilityId("1. Photos")).click();
		//driver.findElement(AppiumBy.accessibilityId("Date Widgets")).click();
		//driver.findElement(AppiumBy.accessibilityId("1. Dialog")).click();
		//driver.findElement(AppiumBy.accessibilityId("change the date")).click();
		//driver.findElement(AppiumBy.id("android:id/date_picker_header_year")).click();
		
		//scrollToElement(By.xpath("//android.widget.TextView[@resource-id=\"android:id/text1\" and @text=\"1988\"]"));
		//driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"android:id/text1\" and @text=\"1988\"]")).click();
		
		
		SwipeToLastElement();
		//swipe();
		
		//String command=scrollToSpecificElement("Spinner");
		//driver.findElement(AppiumBy.androidUIAutomator(command)).click();
		
		//By el = AppiumBy.accessibilityId("Picker");
		//scrollToElement(el);
		//driver.findElement(AppiumBy.accessibilityId("Picker")).click();
		
		//Scroll();
		 //zoomIn();
		//Thread.sleep(4);
		//zoomOut();
	

	}
	
	
	
	
	public static void swipe() {
		WebElement element=driver.findElement(AppiumBy.xpath("//android.widget.Gallery[@resource-id=\"io.appium.android.apis:id/gallery\"]/android.widget.ImageView[1]"));
		  
		driver.executeScript("mobile: swipeGesture", ImmutableMap.of(
			    "elementId", ((RemoteWebElement) element).getId(),
			    "direction", "left",
			    "percent", 0.2
			));
	}
	 
	public static void SwipeToLastElement() {
	    // Define the index of the first element to swipe
	    int index = 1;
	    boolean lastElementReached = false;
	    while (!lastElementReached) {
	        try {
	            // Locate the element by the current index
	            WebElement element = driver.findElement(AppiumBy.xpath("//android.widget.Gallery[@resource-id=\"io.appium.android.apis:id/gallery\"]/android.widget.ImageView[" + index + "]"));
	            
	            // Swipe to the left
	            driver.executeScript("mobile: swipeGesture", ImmutableMap.of(
	                "elementId", ((RemoteWebElement) element).getId(),
	                "direction", "left",
	                "percent", 0.2
	            ));
	            // Move to the next element
	            index++;
	          
	            Thread.sleep(1000);
	        } catch (NoSuchElementException e) {
	            // If an element is not found, assume you've reached the last element
	            lastElementReached = true;
	            System.out.println("Reached the last element in the gallery.");
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	    }
	}

	
	//Scroll to Specific element by using UiSelector
	public static String scrollToSpecificElement(String elementText) {
		String uiSelector = "new UiSelector().textMatches(\""+elementText+"\")";
		//System.out.println(uiSelector);

		String command = "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(" + uiSelector
				+ ");";
		return command;
		
	}
	
	
	public static void scrollToElement(By by) {
		

		while (true) {
			System.out.println("Inside While element");
			try {
				WebElement element = driver.findElement(by);
				break;
			} catch (Exception e) {
				Scroll();
			}
		}

	}

	public static void Scroll() {

		boolean canScrollMore = (Boolean) driver.executeScript("mobile: scrollGesture", ImmutableMap.of(
				"left", 500,
				"top", 500, 
				"width", 200, 
				"height", 200, 
				"direction", "down", 
				"percent", 1.0));

	}

	public static void ScrollToEnd() {
		boolean canScrollMore = true;

		while (canScrollMore) {

			canScrollMore = (Boolean) driver.executeScript("mobile: scrollGesture", ImmutableMap.of(
					"left", 100, 
					"top",100, 
					"width", 600, 
					"height", 600, 
					"direction", "down", 
					"percent", 1.0));
		}

	}
	public static void zoomIn() {
		driver.executeScript("mobile: pinchOpenGesture",
				ImmutableMap.of(
					"left", 200, 
					"top", 400, 
					"width", 800, 
					"height", 800, 
					"percent", 0.75));
	}

	public static void zoomOut() {
		driver.executeScript("mobile: pinchCloseGesture",
				ImmutableMap.of(
						"left", 1000, 
						"top", 1000, 
						"width", 200,
						"height", 200, 
						"percent", 0.75));
	}
	

	
}
