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

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import com.sol.propertyfiles.BrowserPropertyFile;

/**
 * A driver factory for the WebDriver object. Currently supports the Firefox
 * driver, Chrome driver, and HTMLUnit driver
 * @author Shlomi Reuveni
 * @version %I%, %G%
 * @since Dec 23 2015
 */
public final class DriverFactory {
	/**
	 * An Enum for the DriverFactory class. Used to determine the WebDriver instance
	 * type received from the factory
	 * @author Shlomi Reuveni
	 */
	public enum DriverType {
		CHROME, FIREFOX, HTMLUNIT
	}

	/**
	 * Returns an instance of the requested WebDriver.
	 * This method also sets the implicite waiting time according to the
	 * property in the property file.
	 * @param driverType - name of the requested driver
	 * @return if the requested driver is valid, returns an instant of the
	 * requested driver. If the requested driver is invalid, null is returned.
	 * @throws FileNotFoundException if property file is not found
	 * @throws IOException
	 * @throws NullPointerException if {@code browser} is {@code null}
	 * @throws IllegalArgumentException if the driver type is not one of the
	 * following: Chrome, Firefox, or HTMLUnit.
	 * <br><b>Note:</b> The following keys <em>must</em> be present in the
	 * properties file (and containing values): the <em>absolute</em> path
	 * to the requested WebDriver (in case of ChromeDriver), and the default
	 * waiting time for the implicit wait. Missing keys may cause undefined
	 * behavior.
	 * @see BrowserPropertyFile
	 */
	public static WebDriver getDriver(final DriverType driverType)
			throws FileNotFoundException, IOException, NullPointerException,
			IllegalArgumentException {

		Objects.requireNonNull(driverType);
		WebDriver driver = null;
		BrowserPropertyFile properties = BrowserPropertyFile.getInstance();

		switch (driverType) {
		case CHROME:
			String pathToDriver =
			properties.getProperty(BrowserPropertyConstants.PATH_TO_DRIVER);
			System.setProperty("webdriver.chrome.driver", pathToDriver);
			driver = new ChromeDriver();
			break;
		case FIREFOX:
			driver = new FirefoxDriver();
			break;
		case HTMLUNIT:
			driver = new HtmlUnitDriver();
			break;
		default:
			throw new IllegalArgumentException("Illegal driver type");
		}

		String waitingTime = properties.getProperty(BrowserPropertyConstants.IMPLICITE_WAITING_TIME);
		long implicitWaitingTime = Long.parseLong(waitingTime);

		driver.manage().timeouts().implicitlyWait(implicitWaitingTime, TimeUnit.SECONDS);

		return driver;
	}
}
