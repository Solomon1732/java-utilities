package com.sol.util;

import java.lang.reflect.Array;
import java.util.Objects;

/**
 * A class for handling arrays.
 * @author shlomi
 * @version %I%, %G%
 * @since Apr 29 2016
 */
public class ArrayHandler {

	private ArrayHandler() { }

	/**
	 * Receives an array and allocates a new array with the same type and
	 * length. Similar to
	 * {@code getArrayInstance(someArray, someArray.length)}
	 * @param array - array used as the class for the new array.
	 * @return - A newly allocated array with the same type as the input array.
	 * @throws NullPointerException if {@code array} is {@code null}
	 */
	public static <E> E[] getArrayInstance(E[] array) throws NullPointerException {
		Class<?> arrayClass = Objects.requireNonNull(array).getClass().getComponentType();

		@SuppressWarnings("unchecked")
		E[] newArray = (E[]) Array.newInstance(arrayClass, array.length);

		return newArray;
	}

	/**
	 * Receives an array and allocates a new array with the same type as the
	 * original array
	 * @param array - array used as the class for the new array.
	 * @param length - length for the new array.
	 * @return - A newly allocated array with the same type as the input array.
	 * @throws IllegalArgumentException for {@code length < 0}
	 * @throws NullPointerException if {@code array} is {@code null}
	 */
	public static <E> E[] getArrayInstance(E[] array, int length)
			throws IllegalArgumentException, NullPointerException {
		if(0 > length) {
			throw new IllegalArgumentException();
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
		requireValidIndex(array, first);
		requireValidIndex(array, second);
		E tmp = array[first];
		array[first] = array[second];
		array[second] = tmp;
	}

	/**
	 * Checks that the specified index is valid. This method is designed primarily
	 * for doing parameter validation in methods and constructors, as
	 * demonstrated below:
	 * {@code public Foo(Bar[] array, int index) {
	 * 		ArrayHandler(array, index);
	 * 		this.bar = array[index];
	 * }}
	 * @param array - array that is used for validation
	 * @param index - index that is checked against this array
	 * @throws IllegalArgumentException if {@code index < 0} or
	 * {@code index > (array.length - 1)}
	 */
	public static <E> void requireValidIndex(E[] array, int index)
			throws IllegalArgumentException {

		if(!isIndexValid(array, index)) {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * This method is identical to {@code #requireValidIndex(Object[], int)}
	 * except that the parameter {@code index}, is of type {@code long} instead
	 * of {@code int}.
	 * @param array - array that is used for validation
	 * @param index - index that is checked against this array
	 * @throws IllegalArgumentException if {@code index < 0} or
	 * {@code index > (array.length - 1)}
	 */
	public static <E> void requireValidIndex(E[] array, long index)
			throws IllegalArgumentException {

		if(!isIndexValid(array, index)) {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Returns true if provided index is valid for the provided array, otherwise
	 * return false.
	 * The index is considered valid if and only if {@code index >= 0}
	 * and {@code index < array.length} (the last part is because the last
	 * index of any array is array.length - 1, therefore the index needs to be
	 * smaller that the length of the array).
	 * @param array - array that is used for validation
	 * @param index - index that is checked against this array
	 * @return true if the provided index is valid for the provided array
	 * otherwise false
	 */
	public static <E> boolean isIndexValid(E[] array, int index) {
		return  index >= 0 | index < array.length;
	}

	/**
	 * This method is identical to {@code #isIndexValid(Object[], int)}
	 * except that the parameter {@code index}, is of type {@code long} instead
	 * of {@code int}.
	 * @param array - array that is used for validation
	 * @param index - index that is checked against this array
	 * @return true if the provided index is valid for the provided array
	 * otherwise false
	 */
	public static <E> boolean isIndexValid(E[] array, long index) {
		return  index >= 0 | index < array.length;
	}
}
