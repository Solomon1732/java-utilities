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
package com.sol.util;

import java.lang.reflect.Array;
import java.util.Objects;

import com.google.common.base.Supplier;

/**
 * A class for handling arrays.
 * @author shlomi
 * @version %I%, %G%
 * @since Apr 29 2016
 */
public class ArrayHandler {

    /**
     * Constructor.  Class ArrayHandler is not instantiable.
     */
	private ArrayHandler() { }

	/**
	 * Receives an array and creates a new array with the same type and
	 * length. Similar to
	 * {@code getArrayInstance(someArray, someArray.length)}
	 * @param array - array used as the class for the new array.
	 * @return - A newly allocated array with the same type as the input array.
	 * @throws NullPointerException - if {@code array} is {@code null}
	 */
	public static <E> E[] getArrayInstance(E[] array) throws NullPointerException {
		Class<?> arrayClass = Objects.requireNonNull(array).getClass().getComponentType();

		@SuppressWarnings("unchecked")
		E[] newArray = (E[]) Array.newInstance(arrayClass, array.length);

		return newArray;
	}

	/**
	 * Receives an array and creates a new array with the same type as the
	 * original array
	 * @param array - array used as the class for the new array.
	 * @param length - length for the new array.
	 * @return - A newly allocated array with the same type as the input array.
	 * @throws IllegalArgumentException - for {@code length < 0}
	 * @throws NullPointerException - if {@code array} is {@code null}
	 */
	public static <E> E[] getArrayInstance(E[] array, int length)
			throws NegativeArraySizeException, NullPointerException {
		
		if(length < 0) {
			throw new NegativeArraySizeException();
		}
		Class<?> arrayClass = Objects.requireNonNull(array).getClass().getComponentType();

		@SuppressWarnings("unchecked")
		E[] newArray = (E[]) Array.newInstance(arrayClass, length);

		return newArray;
	}

	/**
	 * Swap the contents of the two indexes.
	 * @param array - array to have its indexes swapped
	 * @param first - first index to swap
	 * @param second - second index to swap
	 */
	public static <E> void swap(E[] array, int first, int second) {
		E tmp = array[first];
		array[first] = array[second];
		array[second] = tmp;
	}

	/**
	 * Checks that the specified index is valid. This method is designed primarily
	 * for doing parameter validation in methods and constructors, as
	 * demonstrated below:
	 * {@code public Foo(Bar[] array, int index) {
	 * 		ArrayHandler.requireValidIndex(array, index);
	 * 		this.bar = array[index];
	 * }}
	 * @param array - array that is used for validation
	 * @param index - index that is checked against this array
	 * @throws IndexOutOfBoundsException - if {@code index < 0} or
	 * {@code index > (array.length - 1)}
	 */
	public static void requireValidIndex(Object[] array, int index)
			throws IndexOutOfBoundsException {

		if(!isIndexValid(array, index)) {
			throw new IndexOutOfBoundsException();
		}
	}

	/**
	 * Checks that the specified index is valid. This method is designed primarily
	 * for doing parameter validation in methods and constructors, as
	 * demonstrated below:
	 * {@code public Foo(Bar[] array, int index) {
	 * 		ArrayHandler.requireValidIndex(array, index);
	 * 		this.bar = array[index];
	 * }}
	 * @param array - array that is used for validation
	 * @param index - index that is checked against this array
	 * @param message - detail message to be used in the event that an
	 * {@code IndexOutOfBoundsException} is thrown
	 * @return 
	 * @throws IndexOutOfBoundsException - if {@code index < 0} or
	 * {@code index > (array.length - 1)}
	 */
	public static void requireValidIndex(Object[] array, int index, String message)
			throws IndexOutOfBoundsException {

		if(!isIndexValid(array, index)) {
			throw new IndexOutOfBoundsException(message);
		}
	}

	/**
	 * Checks that the specified index is valid. This method is designed primarily
	 * for doing parameter validation in methods and constructors, as
	 * demonstrated below:
	 * {@code public Foo(Bar[] array, int index) {
	 * 		ArrayHandler.requireValidIndex(array, index);
	 * 		this.bar = array[index];
	 * }}
	 * @param <E>
	 * @param array - array that is used for validation
	 * @param index - index that is checked against this array
	 * @param messageSupplier
	 * @return 
	 * @throws IndexOutOfBoundsException - if {@code index < 0} or
	 * {@code index > (array.length - 1)}
	 */
	public static void requireValidIndex(Object[] array, int index,
			Supplier<String> messageSupplier) throws IndexOutOfBoundsException {

		if(!isIndexValid(array, index)) {
			throw new IndexOutOfBoundsException(messageSupplier.get());
		}
	}


	/**
	 * Returns true if provided index is valid for the provided array, otherwise
	 * return false.
	 * The index is considered valid if and only if {@code index >= 0
	 * && index < array.length} (the last part is because the last index of any
	 * array is array.length - 1, therefore the index needs to be smaller that
	 * the length of the array).
	 * @param array - array that is used for validation
	 * @param index - index that is checked against this array
	 * @return true if the provided index is valid for the provided array
	 * otherwise false
	 */
	public static boolean isIndexValid(Object[] array, int index) {
		return  0 <= index & index < array.length;
	}

}
