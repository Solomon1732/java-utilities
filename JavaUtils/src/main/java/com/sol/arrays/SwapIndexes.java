package com.sol.arrays;

/**
 * This class is a utility class used to swap two indexes of an array.
 * @author Shlomi Reuveni
 * @since Feb 9 2015
 * @deprecated Use the class {@link ArrayHandler} instead.
 */
public class SwapIndexes {

	/**
	 * Swap the contents of the two indexes.
	 * @param array - the array to have its indexes swapped
	 * @param first - the first index to swap
	 * @param second - the second index to swap
	 */
	public static <E> void swap(E[] array, int first, int second) {
		E tmp = array[first];
		array[first] = array[second];
		array[second] = tmp;
	}
	
}
