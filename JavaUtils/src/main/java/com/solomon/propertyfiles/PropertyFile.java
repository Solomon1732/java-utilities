package com.solomon.propertyfiles;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

import com.solomon.browser.BrowserPropertyConstants;

/**
 * A properties file manager. This class is a singleton. The class is
 * thread-safe.
 * This class can be modifies by replacing the enum type and the property file name this class uses by
 * default.
 * @author Shlomi Reuveni
 * @since Dec 28 2015
 */
public class PropertyFile {
	/**
	 * A Properties instance to manage the file
	 */
	private static final Properties PROPERTIES = new Properties();
	/**
	 * The default file name
	 */
	private static final  String FILE_NAME = "conf.properties";

	/**
	 * An instance holder. Used for lazy initialization
	 * @author Shlomi Reuveni
	 * @since Jan 5 2016
	 */
	private static class InstanceHolder {
		/**
		 * A singleton instance of the class
		 */
		private static final PropertyFile INSTANCE = new PropertyFile();
	}

	/**
	 * An overwriting of the default class constructor
	 */
	private PropertyFile() { }

	/**
	 * Get an instance of the class
	 * @return a reference for an instance of the class
	 */
	public static PropertyFile getInstance() {
		return InstanceHolder.INSTANCE;
	}

	/**
	 * Set a property in the file
	 * @param value - the value of the property
	 * @param comment - a comment for the property. If null in received, than no
	 * comment is written
	 * @return the value previously stored in the property. If there was no
	 * value, null is returned
	 * @throws IOException
	 */
	public String setProperty(BrowserPropertyConstants key, String value,
			String comment) throws IOException {

		String previousValue = (String) PROPERTIES.setProperty(key.getPropertyValue(), value);

		try (FileWriter writer = new FileWriter(FILE_NAME)) {
			PROPERTIES.store(writer, comment);
		} catch (IOException e) {
			throw e;
		}
		return previousValue;
	}

	/**
	 * Get a property from the configuration file
	 * @param key - the name of the property which holds the value
	 * @return a string containing the value of the property. The method
	 * returns null if the property is not found
	 * @throws IOException 
	 * @throws FileNotFoundException
	 */
	public String getProperty(BrowserPropertyConstants key) throws IOException, FileNotFoundException {
		String property = null;

		if(!(new File("conf.properties")).exists()) {
			throw new FileNotFoundException();
		}

		try (FileReader reader = new FileReader(FILE_NAME)) {
			PROPERTIES.load(reader);
			property = PROPERTIES.getProperty(key.getPropertyValue());
		} catch (IOException e) {
			throw e;
		}

		return property;
	}
}

