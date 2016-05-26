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

import java.util.Objects;

import org.openqa.selenium.WebDriver;

/**
 * An abstract page object class
 * @author Shlomi Reuveni
 * @version %I%, %G%
 * @since Dec 22 2015
 */
public abstract class AbstractPageObject {
	/**
	 * A web driver instance
	 */
	protected WebDriver driver;
	/**
	 * An actionbot instance
	 */
	protected ActionBot actionbot;

	/**
	 * A constructor
	 * @param driver - driver used to manipulate the page
	 */
	public AbstractPageObject(final WebDriver driver) {
		this.driver = Objects.requireNonNull(driver);
		actionbot = new ActionBot(driver);
	}

	/**
	 * Close the window. Quits the driver instance if it's the last window
	 * currently open
	 */
	public void closeWindow() {
		driver.close();
	}
	
	/**
	 * Closes all the windows and quit the the driver instance
	 */
	public void quit() {
		actionbot.quit();
	}
}
