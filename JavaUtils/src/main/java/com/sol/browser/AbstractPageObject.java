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
	public AbstractPageObject(WebDriver driver) {
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
