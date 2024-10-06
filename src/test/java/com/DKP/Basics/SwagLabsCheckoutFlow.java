package com.DKP.Basics;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Collections;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.RemoteWebElement;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class SwagLabsCheckoutFlow {
	public static AndroidDriver driver;
	public static void main(String[] args) throws MalformedURLException {
		UiAutomator2Options option = new UiAutomator2Options();
		option.setPlatformName("Android");
		option.setDeviceName("OPPO A77s");
		option.setUdid("f348518");
		option.setAutomationName("UiAutomator2");
		option.setAppPackage("com.swaglabsmobileapp");
		option.setAppActivity("com.swaglabsmobileapp.SplashActivity");
		option.setApp("D:\\MobileAutomationLearning\\AppsApk\\SauceLabs.2.7.1.apk");

		URL url= new URL("http://192.168.32.29:4723/");

		
		driver = new AndroidDriver(url, option);

		driver.findElement(AppiumBy.accessibilityId("test-Username")).sendKeys("standard_user");
		driver.findElement(AppiumBy.accessibilityId("test-Password")).sendKeys("secret_sauce");
		
		
		//driver.findElement(AppiumBy.xpath("android.widget.TextView[@text=\"standard_user\"]")).click();
		driver.findElement(AppiumBy.accessibilityId("test-LOGIN")).click();
		// Adding First Product
		driver.findElement(AppiumBy.xpath("(//android.widget.TextView[@text=\"ADD TO CART\"])[1]")).click();
		driver.findElement(AppiumBy.xpath("(//android.widget.TextView[@text=\"ADD TO CART\"])[1]")).click();

		

		WebElement element = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"2\"]"));

		int height = element.getSize().getHeight();// 51
		int width = element.getSize().getWidth();// 51

		Point point = element.getLocation();

		System.out.println("X-->" + point.x);// 369
		System.out.println("Y-->" + point.y);// 29

		int desiredX = point.x + (width / 2);
		int desiredY = point.y + (height / 2)+10;
		System.out.println("desiredX-->" + desiredX);// 369
		System.out.println("desiredY-->" + desiredY);// 29

		tap(desiredX, desiredY);
		
		By el = AppiumBy.accessibilityId("test-CHECKOUT");
		scrollToElement(el);
		driver.findElement(AppiumBy.accessibilityId("test-CHECKOUT")).click();
		
		
		driver.findElement(AppiumBy.accessibilityId("test-First Name")).sendKeys("Abhigya");
		driver.findElement(AppiumBy.accessibilityId("test-Last Name")).sendKeys("Jha");
		driver.findElement(AppiumBy.accessibilityId("test-Zip/Postal Code")).sendKeys("560103");
		
		driver.findElement(AppiumBy.accessibilityId("test-CONTINUE")).click();
		
		 el = AppiumBy.accessibilityId("test-FINISH");
		scrollToElement(el);
		driver.findElement(AppiumBy.accessibilityId("test-FINISH")).click();
		System.out.println("Clicked o Finish Button");
		
		driver.findElement(AppiumBy.accessibilityId("test-BACK HOME")).click();
		System.out.println("Return To Home Page");
	}
	
	

	public static void tap(int x, int y) {
		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
		Sequence sequence = new Sequence(finger, 1)
				.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), x, y))
				.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
				.addAction(new Pause(finger, Duration.ofMillis(150)))
				.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
		driver.perform(Collections.singletonList(sequence));
		System.out.println("Tap with Coordinates");
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


}