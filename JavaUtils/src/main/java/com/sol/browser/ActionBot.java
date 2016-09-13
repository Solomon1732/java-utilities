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
package com.sol.browser;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.sol.propertyfiles.BrowserPropertyFile;

/**
 * An actionbot
 * @author Shlomi Reuveni
 * @version %I%, %G%
 * @since Dec 22 2015
 */
public final class ActionBot {
	private final WebDriver driver;
	private static final BrowserPropertyFile properties = BrowserPropertyFile.getInstance();

	/**
	 * A constructor for the actionbot
	 * @param driver - Webdriver instance used by the actionbot to manipulate
	 * pages
	 */
	public ActionBot(final WebDriver driver) {
		this.driver = Objects.requireNonNull(driver);
	}

	/**
	 * Click on an element specified by the locator
	 * @param locator - locator of the element to be clicked
	 */
	public void click(final By locator) {
		driver.findElement(locator).click();
	}

	/**
	 * Submit an element (usually by clicking on it)
	 * @param locator - locator of the element to be submitted
	 */
	public void submit(final By locator) {
		driver.findElement(locator).submit();
	}

	/**
	 * Locates an element in the page
	 * @param locator - locator of the element to be located
	 * @return the element found
	 */
	public WebElement findElement(final By locator) {
		return driver.findElement(locator);
	}

	/**
	 * A method to check if the current page is a page containing the element
	 * @param locator - locator to check if the current page is the page the
	 * element is located in
	 * @return true if the element is found; otherwise false
	 * <br><b>Note:</b> if the element is not a unique element (e.g. tagName)
	 * it may result in undefined behavior
	 */
	public boolean isPage(final By locator) {
		return !driver.findElements(locator).isEmpty();
	}

	/**
	 * Used to locate and select an element in a page (for example, in a table
	 * in the page)
	 * @param locator - locator of the element to be selected
	 * @param value  - String that is the value to be selected
	 */
	public void selectByValue(final By locator, final String value) {
		new Select(driver.findElement(locator)).selectByValue(value);
	}

	/**
	 * Used to locate and select an element in a page (for example, in a table
	 * in the page)
	 * @param locator - locator of the element to be selected
	 * @param text - String containing the visible text of the option to be
	 * selected
	 */
	public void selectByVisibleText(final By locator, final String text) {
		new Select(driver.findElement(locator)).selectByVisibleText(text);;
	}

	/**
	 * Type something into an input field. WebDriver doesn't normally clear these
	 * before typing, so this method does that first.
	 * @param locator - locator of the element to be typed into (e.g. search
	 * bar)
	 * @param text - text to be written into the element
	 */
	public void type(final By locator, final String text) { 
		WebElement element = driver.findElement(locator);
		element.clear();
		element.sendKeys(text);
	}

	/**
	 * Checks if the element specified by the locator parameter exists in the
	 * page
	 * @param locator - locator of the element to be located
	 * @return true if the element is located; otherwise false
	 */
	public boolean isElementPresent(final By locator) {
		return this.isPage(locator);
	}

	/**
	 * Quit the driver instant
	 */
	public void quit() {
		driver.quit();
	}

	/**
	 * Sets an explicit waiting time
	 * @param waitingTimeInMillis - waiting time in milliseconds
	 * @param expectedCondition - expected condition with which the wait is set by
	 * @throws FileNotFoundException - if the properties file is not found
	 * @throws IOException
	 * @throws NullPointerException if {@code expectedCondition} is {@code null}
	 */
	public void explicitWait(final long waitingTimeInMillis, final ExpectedCondition<Boolean> expectedCondition)
			throws FileNotFoundException, IOException, NullPointerException {
		Objects.requireNonNull(expectedCondition);
		//Sets implicit wait at 0
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);

		//The explicit waiting
		new WebDriverWait(driver, waitingTimeInMillis).until(expectedCondition);

		//Sets the implicit wait back at the property time set in the properties file
		String waitingTime = properties.getProperty(BrowserPropertyConstants
				.IMPLICITE_WAITING_TIME);

		driver.manage().timeouts().implicitlyWait(Long.parseLong(waitingTime),
				TimeUnit.SECONDS);
	}

}
