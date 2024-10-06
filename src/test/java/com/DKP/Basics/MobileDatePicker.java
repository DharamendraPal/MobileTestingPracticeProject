package com.DKP.Basics;


import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebElement;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class MobileDatePicker {

	public static AndroidDriver driver;

	public static void main(String[] args) throws MalformedURLException, InterruptedException {
		DesiredCapabilities cap = new DesiredCapabilities();

		cap.setCapability("appium:deviceName", "");
		cap.setCapability("appium:udid", "");
		cap.setCapability("platformName", "Android");
		cap.setCapability("appium:platformVersion", "14");
		cap.setCapability("appium:automationName", "uiAutomator2");
		cap.setCapability("appium:appPackage", "io.appium.android.apis");
		cap.setCapability("appium:appActivity", "io.appium.android.apis.ApiDemos");
		URL url= new URL("http://192.168.32.29:4723/");
		//URL url = new URL("http://127.0.0.1:4723/");
		driver = new AndroidDriver(url, cap);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		// Thread.sleep(Duration.ofSeconds(3));
		// click on Views
		driver.findElement(AppiumBy.accessibilityId("Views")).click();
		driver.findElement(AppiumBy.accessibilityId("Date Widgets")).click();
		driver.findElement(AppiumBy.accessibilityId("1. Dialog")).click();
		driver.findElement(AppiumBy.accessibilityId("change the date")).click();
		driver.findElement(AppiumBy.id("android:id/date_picker_header_year")).click();
		
		// Select the Day/Month /Year
        String day = "21";
        String month = "Jan";
		String year = "2000";
		
		scrollToElement(By.xpath("//android.widget.TextView[@resource-id=\"android:id/text1\" and @text=\""+year+"\"]"));
		driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"android:id/text1\" and @text=\""+year+"\"]")).click();
		selectMonthAndDay(month, day);
		
		driver.findElement(AppiumBy.id("android:id/button1")).click();
		
	}

	public static void scrollToElement(By by) {

		while (true) {

			try {
				WebElement element = driver.findElement(by);
				break;
			} catch (Exception e) {
				Scroll();
			}
		}

	}

	public static void Scroll() {

		WebElement element = driver.findElement(By.id("android:id/date_picker_year_picker"));
		boolean canScrollMore = (Boolean) driver.executeScript("mobile: scrollGesture", ImmutableMap.of(
				// "left", 550,"top", 1050,
				"elementId", ((RemoteWebElement) element).getId(), "width", 600, "height", 600, "direction", "up",
				"percent", 1.0));

	}
	
	// Method to select month and day dynamically
    public static void selectMonthAndDay(String targetMonth, String targetDay) throws InterruptedException {
        // Get the current month displayed in the calendar
    	driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\""+targetDay+"\")")).click();
        WebElement currentMonthElement = driver.findElement(By.id("android:id/date_picker_header_date"));
        String currentMonthText = currentMonthElement.getText().split(" ")[2]; // Extracting the current month text
        
        // Get numeric values for the months
        int currentMonthNumeric = getMonthNumeric(currentMonthText);
        int targetMonthNumeric = getMonthNumeric(targetMonth);

        // Click the arrows based on the comparison of target and current months
        while (currentMonthNumeric != targetMonthNumeric) 
        {
            if (targetMonthNumeric < currentMonthNumeric) 
            {
                // Target month is before the current month, click the left arrow
                driver.findElement(By.id("android:id/prev")).click();
            } 
            else {
                // Target month is after the current month, click the right arrow
                driver.findElement(By.id("android:id/next")).click();
            }
            driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\""+targetDay+"\")")).click();
            // Update the current month after clicking the arrow
            currentMonthText = driver.findElement(By.id("android:id/date_picker_header_date")).getText().split(" ")[2];
            currentMonthNumeric = getMonthNumeric(currentMonthText);
        }

        // Select the day
        driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"" + targetDay + "\")")).click();
    }

    // Method to return numeric value for a month 
    public static int getMonthNumeric(String month) 
    {
        switch (month) 
        {
            case "Jan" :
                return 1;
            case "Feb":
                return 2;
            case "Mar":
                return 3;
            case "Apr":
                return 4;
            case "May":
                return 5;
            case "Jun":
                return 6;
            case "Jul":
                return 7;
            case "Aug":
                return 8;
            case "Sept":
                return 9;
            case "Oct":
                return 10;
            case "Nov":
                return 11;
            case "Dec":
                return 12;
            default:
                return 0;
        }
    }
}
