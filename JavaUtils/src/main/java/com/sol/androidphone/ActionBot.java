package com.sol.androidphone;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;

import io.appium.java_client.android.AndroidDriver;

/**
 * An {@code actionbot} for use with {@link AbstractAndroidPageObject}
 * @author Shlomi Reuveni
 * @version %I%, %G%
 */
public class ActionBot {
	/**
	 * An {@link AndroidDriver} instance for the use of the class
	 */
	private final AndroidDriver<WebElement> driver;

	public ActionBot(AndroidDriver<WebElement> driver) {
		this.driver = Objects.requireNonNull(driver);
	}

	/**
	 * Type text into the specified element
	 * @param element - element to be typed into
	 * @param textToType - text to be typed
	 */
	public void type(final WebElement element, final String textToType) {
		element.clear();
		element.sendKeys(textToType);
	}

	/**
	 * Type text into the specified element
	 * @param element - element to be typed into
	 * @param textToType - text to be typed
	 * @param toClearBefore - if true the element will be cleared before typing
	 * (as in the method clear()). false indicates that the method will not be
	 * cleared
	 */
	public void type(final WebElement element, final String textToType,
			boolean toClearBefore) {
		if(toClearBefore) {
			element.clear();
		}
		element.sendKeys(textToType);
	}

	/**
	 * Scroll down to the desired element. After the element is found (assuming
	 * no exception was thrown) the implicit waiting time is set to 30 seconds.
	 * @param elementxPath - xpath to the element
	 * @param initialSleepingTime - initial sleeping time in milliseconds
	 * before starting the search
	 * @return the element that is searched for
	 */
	public WebElement scrollDownToElement(String elementxPath,
			long initialSleepingTime) {

		Dimension pageSize = driver.manage().window().getSize();
		int width = pageSize.width / 2;
		int startingHeight = pageSize.height * 9 / 10;
		int endingHeight = pageSize.height / 10;

		try {
			Thread.sleep(initialSleepingTime);
		} catch (InterruptedException e) {
		}

		this.implicitwait(0, TimeUnit.SECONDS);

		while(driver.findElementsByXPath(elementxPath).isEmpty()) {
			driver.swipe(width, startingHeight, width, endingHeight, 1500);
		}

		this.implicitwait(30, TimeUnit.SECONDS);

		return driver.findElementByXPath(elementxPath);
	}

	/**
	 * Set an implicit waiting time
	 * @param time - time for the implicit wait
	 * @param unit - unit for the waiting time (seconds, nanoseconds, minutes,
	 * etc.)
	 */
	private void implicitwait(long time, TimeUnit unit) {
		assert time >= 0 : "Time is a negetive value!";
		assert null != unit;
		driver.manage().timeouts().implicitlyWait(time, unit);
	}

}