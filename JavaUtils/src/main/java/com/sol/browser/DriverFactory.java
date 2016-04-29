package com.sol.browser;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import com.sol.propertyfiles.PropertyFile;

/**
 * A driver factory for the WebDriver object. Currently supports the Firefox
 * driver, Chrome driver, and HTMLUnit driver
 * @author Shlomi Reuveni
 * @since Dec 23 2015
 */
public class DriverFactory {
	/**
	 * An Enum for the DriverFactory class. Used to determine the WebDriver instance
	 * type received from the factory
	 * @author Shlomi Reuveni
	 * @since Dec 28 2015
	 */
	public enum DriverType {
		CHROME, FIREFOX, HTMLUNIT
	}

	/**
	 * Returns an instance of the requested WebDriver. Null in case of an
	 * invalid name
	 * @param browser - the name of the requested driver
	 * @return if the requested driver is valid, returns an instant of the
	 * requested driver. If the requested driver is invalid, null is returned.
	 * @throws FileNotFoundException
	 * @throws IOException
	 * <br><b>Note:</b> The following keys <em>must</em> be present in the
	 * properties file (and containing values): the <em>absolute</em> path
	 * to the requested WebDriver (in case of ChromeDriver), and the default
	 * waiting time for the implicit wait
	 */
	public static WebDriver getDriver(final DriverType browser)
					throws FileNotFoundException, IOException {
		WebDriver driver = null;
		PropertyFile properties = PropertyFile.getInstance();
		String pathToDriver = properties.getProperty(BrowserPropertyConstants.PATH_TO_DRIVER);

		switch (browser) {
			case CHROME:
				System.setProperty("webdriver.chrome.driver",
						pathToDriver);
				driver = new ChromeDriver();
				break;
			case FIREFOX:
				driver = new FirefoxDriver();
				break;
			case HTMLUNIT:
				driver = new HtmlUnitDriver();
				break;
			default:
				break;
		}

		String waitingTime = properties.getProperty(BrowserPropertyConstants.IMPLICITE_WAITING_TIME);
		long implicitWaitingTime = Long.parseLong(waitingTime);

		driver.manage().timeouts().implicitlyWait(implicitWaitingTime, TimeUnit.SECONDS);

		return driver;
	}
}
