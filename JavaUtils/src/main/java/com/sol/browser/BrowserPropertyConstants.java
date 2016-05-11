package com.sol.browser;

/**
 * An enum containing the most common properties
 * @author Shlomi Reuveni
 * @version %I%, %G%
 * @since Dec 29 2015
 */
public enum BrowserPropertyConstants {
	USERNAME("username"), PASSWORD("password"), IMPLICITE_WAITING_TIME("implicit-waiting-time"),
	URL("url"), PATH_TO_DRIVER("path-to-driver");

	private final String value;

	private BrowserPropertyConstants(final String value) {
		assert null != value : "value is null!";
		this.value = value;
	}
	
	/**
	 * Get the string contained in the enum
	 * @return a string of the value contained within the enum
	 */
	public String getPropertyValue() {
		return value;
	}
}
