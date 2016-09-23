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
//import java.util.Arrays;
//import java.util.Objects;
//import java.util.stream.IntStream;
import java.util.Objects;

/**
 * A class for handling arrays.
 * @author shlomi
 * @version %I%, %G%
 * @since Apr 29 2016
 */
public final class ArrayUtililities {

	// Constructor. Class ArrayHandler is not instantiable.
	private ArrayUtililities() { }

	/**
	 * Receives an array and creates a new array with the same type and
	 * length. Similar to
	 * {@code #getArrayInstance(someArray, someArray.length)}
	 * @param array - array used as the class for the new array.
	 * @return - A newly allocated array with the same type as the input array.
	 * Note that the new array is initialized with nulls.
	 * @throws NullPointerException if {@code array} is {@code null}
	 */
	public static <E> E[] getArrayInstance(final E[] array)
			throws NullPointerException {
		return createArrayInstance(Objects.requireNonNull(array), array.length);
	}

	/**
	 * Receives an array and creates a new array with the same type as the
	 * original array
	 * @param array - array used as the class for the new array.
	 * @param length - length for the new array.
	 * @return A newly allocated array with the same type as the input array.
	 * Note that the new array is initialized with nulls.
	 * @throws NegativeArraySizeException if {@code length} is negative
	 * @throws NullPointerException if {@code array} is {@code null}
	 */
	public static <E> E[] getArrayInstance(final E[] array, final int length)
			throws NegativeArraySizeException, NullPointerException {
		/*
		 * *Unneeded* but better to detect a negative size at this stage rather
		 * than in Array.newInstance, since the whole class is a JNI wrapper 
		 */		 
		if(length < 0) {
			throw new NegativeArraySizeException();
		}
		return createArrayInstance(Objects.requireNonNull(array), length);
	}

	private static <E> E[] createArrayInstance(final E[] array, final int length)
			throws NegativeArraySizeException {
		assert Objects.nonNull(array) : "Array is null";
		assert length >= 0 : "Array length is negative";

		final Class<?> arrayComponentClass = array.getClass().getComponentType();

		@SuppressWarnings("unchecked")
		E[] newArray = (E[]) Array.newInstance(arrayComponentClass, length);

		return newArray;		
	}

	//	/**
	//	 * Returns an array of {@code Booleans} containing the same elements as
	//	 * the original {@code boolean} array in the same order. The new array is
	//	 * set in parallel
	//	 * @param array - {@code boolean} array to wrap
	//	 * @return new array of {@code Booleans} containing the same elements as
	//	 * as the original {@code boolean} array in the same order
	//	 * @throws NullPointerException if {@code array == null}
	//	 */
	//	public static Boolean[] ParallelToBoxedArray(final boolean[] array)
	//			throws NullPointerException {
	//
	//		Objects.requireNonNull(array);
	//		Boolean[] newArray = new Boolean[array.length];
	//
	//		Arrays.parallelSetAll(newArray, index -> Boolean.valueOf(array[index]));
	//		return newArray;
	//	}
	//
	//	/**
	//	 * Returns an array of {@code Bytes} containing the same elements as
	//	 * the original {@code byte} array in the same order. The new array is
	//	 * set in parallel
	//	 * @param array - {@code byte} array to wrap
	//	 * @return new array of {@code Bytes} containing the same elements as
	//	 * as the original {@code byte} array in the same order
	//	 * @throws NullPointerException if {@code array == null}
	//	 */
	//	public static Byte[] ParallelToBoxedArray(final byte[] array)
	//			throws NullPointerException {
	//
	//		Objects.requireNonNull(array);
	//		Byte[] newArray = new Byte[array.length];
	//
	//		Arrays.parallelSetAll(newArray, index -> Byte.valueOf(array[index]));
	//		return newArray;
	//	}
	//
	//	/**
	//	 * Returns an array of {@code Shorts} containing the same elements as
	//	 * the original {@code short} array in the same order. The new array is
	//	 * set in parallel
	//	 * @param array - {@code short} array to wrap
	//	 * @return new array of {@code Shorts} containing the same elements as
	//	 * as the original {@code short} array in the same order
	//	 * @throws NullPointerException if {@code array == null}
	//	 */
	//	public static Short[] ParallelToBoxedArray(final short[] array)
	//			throws NullPointerException {
	//
	//		Objects.requireNonNull(array);
	//		Short[] newArray = new Short[array.length];
	//
	//		Arrays.parallelSetAll(newArray, index -> Short.valueOf(array[index]));
	//		return newArray;
	//	}
	//
	//	/**
	//	 * Returns an array of {@code Characters} containing the same elements as
	//	 * the original {@code char} array in the same order. The new array is
	//	 * set in parallel
	//	 * @param array - {@code char} array to wrap
	//	 * @return new array of {@code Characters} containing the same elements as
	//	 * as the original {@code char} array in the same order
	//	 * @throws NullPointerException if {@code array == null}
	//	 */
	//	public static Character[] ParallelToBoxedArray(final char[] array)
	//			throws NullPointerException {
	//
	//		Objects.requireNonNull(array);
	//		Character[] newArray = new Character[array.length];
	//
	//		Arrays.parallelSetAll(newArray, index -> Character.valueOf(array[index]));
	//		return newArray;
	//	}
	//
	//	/**
	//	 * Returns an array of {@code Integers} containing the same elements as
	//	 * the original {@code int} array in the same order. The new array is
	//	 * set in parallel
	//	 * @param array - {@code int} array to wrap
	//	 * @return new array of {@code Integers} containing the same elements as
	//	 * as the original {@code int} array in the same order
	//	 * @throws NullPointerException if {@code array == null}
	//	 */
	//	public static Integer[] ParallelToBoxedArray(final int[] array)
	//			throws NullPointerException {
	//
	//		Objects.requireNonNull(array);
	//		Integer[] newArray = new Integer[array.length];
	//
	//		Arrays.parallelSetAll(newArray, index -> Integer.valueOf(array[index]));
	//		return newArray;
	//	}
	//
	//	/**
	//	 * Returns an array of {@code Longs} containing the same elements as
	//	 * the original {@code long} array in the same order. The new array is
	//	 * set in parallel
	//	 * @param array - {@code long} array to wrap
	//	 * @return new array of {@code Longs} containing the same elements as
	//	 * as the original {@code long} array in the same order
	//	 * @throws NullPointerException if {@code array == null}
	//	 */
	//	public static Long[] ParallelToBoxedArray(final long[] array)
	//			throws NullPointerException {
	//
	//		Objects.requireNonNull(array);
	//		Long[] newArray = new Long[array.length];
	//
	//		Arrays.parallelSetAll(newArray, index -> Long.valueOf(array[index]));
	//		return newArray;
	//	}
	//
	//	/**
	//	 * Returns an array of {@code Floats} containing the same elements as
	//	 * the original {@code float} array in the same order. The new array is
	//	 * set in parallel
	//	 * @param array - {@code float} array to wrap
	//	 * @return new array of {@code Floats} containing the same elements as
	//	 * as the original {@code float} array in the same order
	//	 * @throws NullPointerException if {@code array == null}
	//	 */
	//	public static Float[] ParallelToBoxedArray(final float[] array)
	//			throws NullPointerException {
	//
	//		Objects.requireNonNull(array);
	//		Float[] newArray = new Float[array.length];
	//
	//		Arrays.parallelSetAll(newArray, index -> Float.valueOf(array[index]));
	//		return newArray;
	//	}
	//
	//	/**
	//	 * Returns an array of {@code Doubles} containing the same elements as
	//	 * the original {@code double} array in the same order. The new array is
	//	 * set in parallel
	//	 * @param array - {@code double} array to wrap
	//	 * @return new array of {@code Doubles} containing the same elements as
	//	 * as the original {@code double} array in the same order
	//	 * @throws NullPointerException if {@code array == null}
	//	 */
	//	public static Double[] ParallelToBoxedArray(final double[] array)
	//			throws NullPointerException {
	//
	//		Objects.requireNonNull(array);
	//		Double[] newArray = new Double[array.length];
	//
	//		Arrays.parallelSetAll(newArray, index -> Double.valueOf(array[index]));
	//		return newArray;
	//	}
	//
	//	/**
	//	 * Takes an array of {@code Boolean} and returns a new array of {@code boolean}
	//	 * containing the same values (unboxed) in the same order. The new array is
	//	 * set in parallel
	//	 * @param array - {@code Boolean} array to be unboxed
	//	 * @return new array of primitive {@code boolean} containing the same
	//	 * values in the same order as the original array
	//	 * @throws NullPointerException if {@code array == null} or one of the
	//	 * elements is null
	//	 */
	//	public static boolean[] ParallelToPrimitiveArray(final Boolean[] array)
	//			throws NullPointerException {
	//
	//		Objects.requireNonNull(array);
	//		boolean[] newArray = new boolean[array.length];
	//
	//		IntStream.range(0, array.length).parallel()
	//		.forEach(index -> newArray[index] = array[index].booleanValue());
	//		return newArray;
	//	}
	//
	//	/**
	//	 * Takes an array of {@code Byte} and returns a new array of {@code byte}
	//	 * containing the same values (unboxed) in the same order. The new array is
	//	 * set in parallel
	//	 * @param array - {@code Byte} array to be unboxed
	//	 * @return new array of primitive {@code byte} containing the same
	//	 * values in the same order as the original array
	//	 * @throws NullPointerException if {@code array == null} or one of the
	//	 * elements is null
	//	 */
	//	public static byte[] ParallelToPrimitiveArray(final Byte[] array)
	//			throws NullPointerException {
	//
	//		Objects.requireNonNull(array);
	//		byte[] newArray = new byte[array.length];
	//
	//		IntStream.range(0, array.length).parallel()
	//		.forEach(index -> newArray[index] = array[index].byteValue());
	//		return newArray;
	//	}
	//
	//	/**
	//	 * Takes an array of {@code Short} and returns a new array of {@code short}
	//	 * containing the same values (unboxed) in the same order. The new array is
	//	 * set in parallel
	//	 * @param array - {@code short} array to be unboxed
	//	 * @return new array of primitive {@code short} containing the same
	//	 * values in the same order as the original array
	//	 * @throws NullPointerException if {@code array == null} or one of the
	//	 * elements is null
	//	 */
	//	public static short[] ParallelToPrimitiveArray(final Short[] array)
	//			throws NullPointerException {
	//
	//		Objects.requireNonNull(array);
	//		short[] newArray = new short[array.length];
	//
	//		IntStream.range(0, array.length).parallel()
	//		.forEach(index -> newArray[index] = array[index].shortValue());
	//		return newArray;
	//	}
	//
	//	/**
	//	 * Takes an array of {@code Character} and returns a new array of {@code char}
	//	 * containing the same values (unboxed) in the same order. The new array is
	//	 * set in parallel
	//	 * @param array - {@code Character} array to be unboxed
	//	 * @return new array of primitive {@code char} containing the same
	//	 * values in the same order as the original array
	//	 * @throws NullPointerException if {@code array == null} or one of the
	//	 * elements is null
	//	 */
	//	public static char[] ParallelToPrimitiveArray(final Character[] array)
	//			throws NullPointerException {
	//
	//		Objects.requireNonNull(array);
	//		char[] newArray = new char[array.length];
	//
	//		IntStream.range(0, array.length).parallel()
	//		.forEach(index -> newArray[index] = array[index].charValue());
	//		return newArray;
	//	}
	//
	//	/**
	//	 * Takes an array of {@code Integer} and returns a new array of {@code int}
	//	 * containing the same values (unboxed) in the same order. The new array is
	//	 * set in parallel
	//	 * @param array - {@code Integer} array to be unboxed
	//	 * @return new array of primitive {@code int} containing the same
	//	 * values in the same order as the original array
	//	 * @throws NullPointerException if {@code array == null} or one of the
	//	 * elements is null
	//	 */
	//	public static int[] ParallelToPrimitiveArray(final Integer[] array)
	//			throws NullPointerException {
	//
	//		Objects.requireNonNull(array);
	//		int[] newArray = new int[array.length];
	//
	//		Arrays.parallelSetAll(newArray, index -> array[index].intValue());
	//		return newArray;
	//	}
	//
	//	/**
	//	 * Takes an array of {@code Long} and returns a new array of {@code long}
	//	 * containing the same values (unboxed) in the same order. The new array is
	//	 * set in parallel
	//	 * @param array - {@code Long} array to be unboxed
	//	 * @return new array of primitive {@code long} containing the same
	//	 * values in the same order as the original array
	//	 * @throws NullPointerException if {@code array == null} or one of the
	//	 * elements is null
	//	 */
	//	public static long[] ParallelToPrimitiveArray(final Long[] array)
	//			throws NullPointerException {
	//
	//		Objects.requireNonNull(array);
	//		long[] newArray = new long[array.length];
	//
	//		Arrays.parallelSetAll(newArray, index -> array[index].longValue());
	//		return newArray;
	//	}
	//
	//	/**
	//	 * Takes an array of {@code Float} and returns a new array of {@code float}
	//	 * containing the same values (unboxed) in the same order. The new array is
	//	 * set in parallel
	//	 * @param array - {@code Float} array to be unboxed
	//	 * @return new array of primitive {@code float} containing the same
	//	 * values in the same order as the original array
	//	 * @throws NullPointerException if {@code array == null} or one of the
	//	 * elements is null
	//	 */
	//	public static float[] ParallelToPrimitiveArray(final Float[] array)
	//			throws NullPointerException {
	//
	//		Objects.requireNonNull(array);
	//		float[] newArray = new float[array.length];
	//
	//		IntStream.range(0, array.length).parallel()
	//		.forEach(index -> newArray[index] = array[index].floatValue());
	//		return newArray;
	//	}
	//
	//	/**
	//	 * Takes an array of {@code Double} and returns a new array of {@code double}
	//	 * containing the same values (unboxed) in the same order. The new array is
	//	 * set in parallel
	//	 * @param array - {@code Double} array to be unboxed
	//	 * @return new array of primitive {@code double} containing the same
	//	 * values in the same order as the original array
	//	 * @throws NullPointerException if {@code array == null} or one of the
	//	 * elements is null
	//	 */
	//	public static double[] ParallelToPrimitiveArray(final Double[] array)
	//			throws NullPointerException {
	//
	//		Objects.requireNonNull(array);
	//		double[] newArray = new double[array.length];
	//
	//		Arrays.parallelSetAll(newArray, index -> array[index].doubleValue());
	//		return newArray;
	//	}
	//
	//	/**
	//	 * Swap the contents of the two indexes.
	//	 * @param array - array to have its indexes swapped
	//	 * @param first - first index to swap
	//	 * @param second - second index to swap
	//	 */
	//	public static void swap(final boolean[] array, final int first, final int second) {
	//		boolean tmp = array[first];
	//		array[first] = array[second];
	//		array[second] = tmp;
	//	}
	//
	//	/**
	//	 * Swap the contents of the two indexes.
	//	 * @param array - array to have its indexes swapped
	//	 * @param first - first index to swap
	//	 * @param second - second index to swap
	//	 */
	//	public static void swap(final byte[] array, final int first, final int second) {
	//		byte tmp = array[first];
	//		array[first] = array[second];
	//		array[second] = tmp;
	//	}
	//
	//	/**
	//	 * Swap the contents of the two indexes.
	//	 * @param array - array to have its indexes swapped
	//	 * @param first - first index to swap
	//	 * @param second - second index to swap
	//	 */
	//	public static void swap(final short[] array, final int first, final int second) {
	//		short tmp = array[first];
	//		array[first] = array[second];
	//		array[second] = tmp;
	//	}
	//
	//	/**
	//	 * Swap the contents of the two indexes.
	//	 * @param array - array to have its indexes swapped
	//	 * @param first - first index to swap
	//	 * @param second - second index to swap
	//	 */
	//	public static void swap(final char[] array, final int first, final int second) {
	//		char tmp = array[first];
	//		array[first] = array[second];
	//		array[second] = tmp;
	//	}
	//
	//	/**
	//	 * Swap the contents of the two indexes.
	//	 * @param array - array to have its indexes swapped
	//	 * @param first - first index to swap
	//	 * @param second - second index to swap
	//	 */
	//	public static void swap(final int[] array, final int first, final int second) {
	//		int tmp = array[first];
	//		array[first] = array[second];
	//		array[second] = tmp;
	//	}
	//
	//	/**
	//	 * Swap the contents of the two indexes.
	//	 * @param array - array to have its indexes swapped
	//	 * @param first - first index to swap
	//	 * @param second - second index to swap
	//	 */
	//	public static void swap(final float[] array, final int first, final int second) {
	//		float tmp = array[first];
	//		array[first] = array[second];
	//		array[second] = tmp;
	//	}
	//
	//	/**
	//	 * Swap the contents of the two indexes.
	//	 * @param array - array to have its indexes swapped
	//	 * @param first - first index to swap
	//	 * @param second - second index to swap
	//	 */
	//	public static void swap(final double[] array, final int first, final int second) {
	//		double tmp = array[first];
	//		array[first] = array[second];
	//		array[second] = tmp;
	//	}
	//
	//	/**
	//	 * Swap the contents of the two indexes.
	//	 * @param array - array to have its indexes swapped
	//	 * @param first - first index to swap
	//	 * @param second - second index to swap
	//	 */
	//	public static void swap(Object[] array, int first, int second) {
	//		Object tmp = array[first];
	//		array[first] = array[second];
	//		array[second] = tmp;
	//	}
	//
	//	/**
	//	 * Returns an array of {@code Booleans} containing the same elements as
	//	 * the original {@code boolean} array in the same order
	//	 * @param array - {@code boolean} array to wrap
	//	 * @return new array of {@code Booleans} containing the same elements as
	//	 * as the original {@code boolean} array in the same order
	//	 * @throws NullPointerException if {@code array == null}
	//	 */
	//	public static Boolean[] toBoxedArray(final boolean[] array)
	//			throws NullPointerException {
	//
	//		Objects.requireNonNull(array);
	//		Boolean[] newArray = new Boolean[array.length];
	//
	//		for (int i = 0; i < array.length; i++) {
	//			newArray[i] = Boolean.valueOf(array[i]);
	//		}
	//
	//		return newArray;
	//	}
	//
	//	/**
	//	 * Returns an array of {@code Bytes} containing the same elements as
	//	 * the original {@code byte} array in the same order
	//	 * @param array - {@code byte} array to wrap
	//	 * @return new array of {@code Bytes} containing the same elements as
	//	 * as the original {@code byte} array in the same order
	//	 * @throws NullPointerException if {@code array == null}
	//	 */
	//	public static Byte[] toBoxedArray(final byte[] array)
	//			throws NullPointerException {
	//
	//		Objects.requireNonNull(array);
	//		Byte[] newArray = new Byte[array.length];
	//
	//		for (int i = 0; i < array.length; i++) {
	//			newArray[i] = Byte.valueOf(array[i]);
	//		}
	//
	//		return newArray;
	//	}
	//
	//	/**
	//	 * Returns an array of {@code Shorts} containing the same elements as
	//	 * the original {@code short} array in the same order
	//	 * @param array - {@code short} array to wrap
	//	 * @return new array of {@code Shorts} containing the same elements as
	//	 * as the original {@code short} array in the same order
	//	 * @throws NullPointerException if {@code array == null}
	//	 */
	//	public static Short[] toBoxedArray(final short[] array)
	//			throws NullPointerException {
	//
	//		Objects.requireNonNull(array);
	//		Short[] newArray = new Short[array.length];
	//
	//		for (int i = 0; i < array.length; i++) {
	//			newArray[i] = Short.valueOf(array[i]);
	//		}
	//
	//		return newArray;
	//	}
	//
	//	/**
	//	 * Returns an array of {@code Characters} containing the same elements as
	//	 * the original {@code char} array in the same order
	//	 * @param array - {@code char} array to wrap
	//	 * @return new array of {@code Characters} containing the same elements as
	//	 * as the original {@code char} array in the same order
	//	 * @throws NullPointerException if {@code array == null}
	//	 */
	//	public static Character[] toBoxedArray(final char[] array)
	//			throws NullPointerException {
	//
	//		Objects.requireNonNull(array);
	//		Character[] newArray = new Character[array.length];
	//
	//		for (int i = 0; i < array.length; i++) {
	//			newArray[i] = Character.valueOf(array[i]);
	//		}
	//
	//		return newArray;
	//	}
	//
	//	/**
	//	 * Returns an array of {@code Integers} containing the same elements as
	//	 * the original {@code int} array in the same order
	//	 * @param array - {@code int} array to wrap
	//	 * @return new array of {@code Integers} containing the same elements as
	//	 * as the original {@code int} array in the same order
	//	 * @throws NullPointerException if {@code array == null}
	//	 */
	//	public static Integer[] toBoxedArray(final int[] array)
	//			throws NullPointerException {
	//
	//		Objects.requireNonNull(array);
	//		Integer[] newArray = new Integer[array.length];
	//
	//		for (int i = 0; i < array.length; i++) {
	//			newArray[i] = Integer.valueOf(array[i]);
	//		}
	//
	//		return newArray;
	//	}
	//
	//	/**
	//	 * Returns an array of {@code Longs} containing the same elements as
	//	 * the original {@code long} array in the same order
	//	 * @param array - {@code long} array to wrap
	//	 * @return new array of {@code Longs} containing the same elements as
	//	 * as the original {@code long} array in the same order
	//	 * @throws NullPointerException if {@code array == null}
	//	 */
	//	public static Long[] toBoxedArray(final long[] array)
	//			throws NullPointerException {
	//
	//		Objects.requireNonNull(array);
	//		Long[] newArray = new Long[array.length];
	//
	//		for (int i = 0; i < array.length; i++) {
	//			newArray[i] = Long.valueOf(array[i]);
	//		}
	//
	//		return newArray;
	//	}
	//
	//	/**
	//	 * Returns an array of {@code Floats} containing the same elements as
	//	 * the original {@code float} array in the same order
	//	 * @param array - {@code float} array to wrap
	//	 * @return new array of {@code Floats} containing the same elements as
	//	 * as the original {@code float} array in the same order
	//	 * @throws NullPointerException if {@code array == null}
	//	 */
	//	public static Float[] toBoxedArray(final float[] array)
	//			throws NullPointerException {
	//
	//		Objects.requireNonNull(array);
	//		Float[] newArray = new Float[array.length];
	//
	//		for (int i = 0; i < array.length; i++) {
	//			newArray[i] = Float.valueOf(array[i]);
	//		}
	//
	//		return newArray;
	//	}
	//
	//	/**
	//	 * Returns an array of {@code Doubles} containing the same elements as
	//	 * the original {@code double} array in the same order
	//	 * @param array - {@code double} array to wrap
	//	 * @return new array of {@code Doubles} containing the same elements as
	//	 * as the original {@code double} array in the same order
	//	 * @throws NullPointerException if {@code array == null}
	//	 */
	//	public static Double[] toBoxedArray(final double[] array)
	//			throws NullPointerException {
	//
	//		Objects.requireNonNull(array);
	//		Double[] newArray = new Double[array.length];
	//
	//		for (int i = 0; i < array.length; i++) {
	//			newArray[i] = Double.valueOf(array[i]);
	//		}
	//
	//		return newArray;
	//	}
	//
	//	/**
	//	 * Takes an array of {@code Boolean} and returns a new array of {@code boolean}
	//	 * containing the same values in the same order.
	//	 * @param array - {@code Boolean} array to be unboxed
	//	 * @return new array of primitive {@code boolean} containing the same
	//	 * values in the same order as the original array
	//	 * @throws NullPointerException if {@code array == null} or one of the
	//	 * elements is null
	//	 */
	//	public static boolean[] toPrimitiveArray(final Boolean[] array)
	//			throws NullPointerException {
	//
	//		Objects.requireNonNull(array);
	//		boolean[] newArray = new boolean[array.length];
	//
	//		for (int i = 0; i < array.length; i++) {
	//			newArray[i] = array[i];
	//		}
	//
	//		return newArray;
	//	}
	//
	//	/**
	//	 * Takes an array of {@code Byte} and returns a new array of {@code byte}
	//	 * containing the same numbers in the same order.
	//	 * @param array - {@code Byte} array to be unboxed
	//	 * @return new array of primitive {@code byte} containing the same
	//	 * values in the same order as the original array
	//	 * @throws NullPointerException if {@code array == null} or one of the
	//	 * elements is null
	//	 */
	//	public static byte[] toPrimitiveArray(final Byte[] array)
	//			throws NullPointerException {
	//
	//		Objects.requireNonNull(array);
	//		byte[] newArray = new byte[array.length];
	//
	//		for (int i = 0; i < array.length; i++) {
	//			newArray[i] = array[i];
	//		}
	//
	//		return newArray;
	//	}
	//
	//	/**
	//	 * Takes an array of {@code Short} and returns a new array of {@code short}
	//	 * containing the same numbers in the same order.
	//	 * @param array - {@code short} array to be unboxed
	//	 * @return new array of primitive {@code short} containing the same
	//	 * values in the same order as the original array
	//	 * @throws NullPointerException if {@code array == null} or one of the
	//	 * elements is null
	//	 */
	//	public static short[] toPrimitiveArray(final Short[] array)
	//			throws NullPointerException {
	//
	//		Objects.requireNonNull(array);
	//		short[] newArray = new short[array.length];
	//
	//		for (int i = 0; i < array.length; i++) {
	//			newArray[i] = array[i];
	//		}
	//
	//		return newArray;
	//	}
	//
	//	/**
	//	 * Takes an array of {@code Character} and returns a new array of {@code char}
	//	 * containing the same numbers in the same order.
	//	 * @param array - {@code Character} array to be unboxed
	//	 * @return new array of primitive {@code char} containing the same
	//	 * values in the same order as the original array
	//	 * @throws NullPointerException if {@code array == null} or one of the
	//	 * elements is null
	//	 */
	//	public static char[] toPrimitiveArray(final Character[] array)
	//			throws NullPointerException {
	//
	//		Objects.requireNonNull(array);
	//		char[] newArray = new char[array.length];
	//
	//		for (int i = 0; i < array.length; i++) {
	//			newArray[i] = array[i];
	//		}
	//
	//		return newArray;
	//	}
	//
	//	/**
	//	 * Takes an array of {@code Integer} and returns a new array of {@code int}
	//	 * containing the same numbers in the same order.
	//	 * @param array - {@code Integer} array to be unboxed
	//	 * @return new array of primitive {@code int} containing the same
	//	 * values in the same order as the original array
	//	 * @throws NullPointerException if {@code array == null} or one of the
	//	 * elements is null
	//	 */
	//	public static int[] toPrimitiveArray(final Integer[] array)
	//			throws NullPointerException {
	//
	//		Objects.requireNonNull(array);
	//		int[] newArray = new int[array.length];
	//
	//		for (int i = 0; i < array.length; i++) {
	//			newArray[i] = array[i];
	//		}
	//
	//		return newArray;
	//	}
	//
	//	/**
	//	 * Takes an array of {@code Long} and returns a new array of {@code long}
	//	 * containing the same numbers in the same order.
	//	 * @param array - {@code Long} array to be unboxed
	//	 * @return new array of primitive {@code long} containing the same
	//	 * values in the same order as the original array
	//	 * @throws NullPointerException if {@code array == null} or one of the
	//	 * elements is null
	//	 */
	//	public static long[] toPrimitiveArray(final Long[] array)
	//			throws NullPointerException {
	//
	//		Objects.requireNonNull(array);
	//		long[] newArray = new long[array.length];
	//
	//		for (int i = 0; i < array.length; i++) {
	//			newArray[i] = array[i];
	//		}
	//
	//		return newArray;
	//	}
	//
	//	/**
	//	 * Takes an array of {@code Float} and returns a new array of {@code float}
	//	 * containing the same numbers in the same order.
	//	 * @param array - {@code Float} array to be unboxed
	//	 * @return new array of primitive {@code float} containing the same
	//	 * values in the same order as the original array
	//	 * @throws NullPointerException if {@code array == null} or one of the
	//	 * elements is null
	//	 */
	//	public static float[] toPrimitiveArray(final Float[] array)
	//			throws NullPointerException {
	//
	//		Objects.requireNonNull(array);
	//		float[] newArray = new float[array.length];
	//
	//		for (int i = 0; i < array.length; i++) {
	//			newArray[i] = array[i];
	//		}
	//
	//		return newArray;
	//	}
	//
	//	/**
	//	 * Takes an array of {@code Double} and returns a new array of {@code double}
	//	 * containing the same numbers in the same order.
	//	 * @param array - {@code Double} array to be unboxed
	//	 * @return new array of primitive {@code double} containing the same
	//	 * values in the same order as the original array
	//	 * @throws NullPointerException if {@code array == null} or one of the
	//	 * elements is null
	//	 */
	//	public static double[] toPrimitiveArray(final Double[] array)
	//			throws NullPointerException {
	//
	//		Objects.requireNonNull(array);
	//		double[] newArray = new double[array.length];
	//
	//		for (int i = 0; i < array.length; i++) {
	//			newArray[i] = array[i];
	//		}
	//
	//		return newArray;
	//	}
}
