package com.sol.androidphone;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;

import io.appium.java_client.android.AndroidDriver;

/**
 * An abstract page object for the android phone
 * @author Shlomi Reuveni
 * @since Dec 29 2015
 */
public abstract class AbstractAndroidPageObject {
	/**
	 * An android driver instance
	 */
	protected AndroidDriver<WebElement> driver;
	
	/**
	 * A constructor
	 * @param driver the driver used to manipulate the page
	 */
	public AbstractAndroidPageObject(final AndroidDriver<WebElement> driver) {
		this.driver = driver;
	}
	
	/**
	 * Quits this driver, closing every associated window
	 */
	public void quit() {
		driver.quit();
	}
	
	/**
	 * Type text into the specified element
	 * @param element - the element to be typed into
	 * @param textToType - the text to be typed
	 * @param toClearBefore - if true the element will be cleared before typing
	 * (as in the method clear()). false indicates that the method will not be
	 * cleared
	 */
	protected void type(final WebElement element, final String textToType,
			boolean toClearBefore) {
		if(toClearBefore) {
			element.clear();
		}
		element.sendKeys(textToType);
	}
	
	/**
	 * Set an implicit waiting time
	 * @param time - the time for the implicit wait
	 * @param unit - the unit for the waiting time (seconds, nanoseconds, minutes,
	 * etc.)
	 */
	protected void implicitwait(long time, TimeUnit unit) {
		driver.manage().timeouts().implicitlyWait(time, unit);
	}
}
