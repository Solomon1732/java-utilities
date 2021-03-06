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
