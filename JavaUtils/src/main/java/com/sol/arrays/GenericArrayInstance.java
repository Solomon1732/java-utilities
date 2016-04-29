package com.sol.arrays;

import java.lang.reflect.Array;

/**
 * A class that produces array instances for generic type arrays.
 * @author Shlomi Reuveni
 * @since Feb 9 2015
 * @deprecated Use the class {@link ArrayHandler} instead.
 */
public class GenericArrayInstance {
	
	/**
	 * Receives an array and allocates a new array with the same type and
	 * length. The same as
	 * {@code getArrayInstance(someArray, someArray.length)}
	 * @param array - The array that is used as the class for the new array.
	 * @return - A newly allocated array with the same type as the input array.
	 */
	public static <E> E[] getArrayInstance(E[] array) {
		Class<?> arrayClass = array.getClass().getComponentType();

		@SuppressWarnings("unchecked")
		E[] newArray = (E[]) Array.newInstance(arrayClass, array.length);

		return newArray;
	}
	
	/**
	 * Receives an array and allocates a new array with the same type as the
	 * original array
	 * @param array - The array that is used as the class for the new array.
	 * @param length - The length for the new array.
	 * @return - A newly allocated array with the same type as the input array.
	 * @throws IllegalArgumentException for a negative size array.
	 */
	public static <E> E[] getArrayInstance(E[] array, int length)
			throws IllegalArgumentException {
		if(0 > length) {
			throw new IllegalArgumentException();
		}
		Class<?> arrayClass = array.getClass().getComponentType();

		@SuppressWarnings("unchecked")
		E[] newArray = (E[]) Array.newInstance(arrayClass, length);

		return newArray;
	}	
	
}
