/*******************************************************************************
 * Copyright (c) 2016 Shlomi Reuveni.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
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
public final class ActionBot {
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
	public WebElement scrollDownToElement(final String elementxPath,
			final long initialSleepingTime) {

		Dimension pageSize = driver.manage().window().getSize();
		final int width = pageSize.width / 2;
		final int startingHeight = pageSize.height * 9 / 10;
		final int endingHeight = pageSize.height / 10;

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
	private void implicitwait(final long time, final TimeUnit unit) {
		assert time >= 0 : "Time is a negetive value!";
		assert null != unit : "unit is null!";
		driver.manage().timeouts().implicitlyWait(time, unit);
	}

}
