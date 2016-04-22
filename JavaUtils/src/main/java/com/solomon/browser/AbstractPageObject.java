package com.solomon.browser;

import org.openqa.selenium.WebDriver;

/**
 * An abstract page object class
 * @author Shlomi Reuveni
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
	 * @param driver - the driver used to manipulate the page
	 */
	public AbstractPageObject(WebDriver driver) {
		this.driver = driver;
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
