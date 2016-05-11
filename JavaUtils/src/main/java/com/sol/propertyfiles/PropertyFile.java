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
package com.sol.propertyfiles;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

import com.sol.browser.BrowserPropertyConstants;

/**
 * A properties file manager. This class is a singleton. The class is
 * thread-safe.
 * This class can be modifies by replacing the enum type and the property file name this class uses by
 * default.
 * @author Shlomi Reuveni
 * @version %I%, %G%
 * @since Dec 28 2015
 */
public final class PropertyFile {
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
	 * Set a property in the file.
	 * 
	 * Note that a call to this method erases any comments previously written
	 * to the file.
	 * @param value - value of the property
	 * @param comment - a description of the property list. If null is
	 * received, than no comment is written
	 * @return the value previously stored in the property. If there was no
	 * value, null is returned
	 * @throws IOException
	 * @throws NullPointerException if either {@code key} or {@code value} is
	 * {@code null}
	 */
	public String setProperty(BrowserPropertyConstants key, String value,
			String comment) throws IOException, NullPointerException {
		Objects.requireNonNull(key);

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
	 * @throws NullPointerException if the {@code key} is {@code null}
	 */
	public String getProperty(BrowserPropertyConstants key)
			throws IOException, FileNotFoundException, NullPointerException {
		Objects.requireNonNull(key);
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

