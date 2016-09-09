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
package com.sol.reflect;

import java.lang.reflect.Array;
import java.util.Objects;
import java.util.Optional;

/**
 * This class is meant to be a substitute to {@link Array}, since said class
 * contains various native methods, which are better implemented in java directly
 * for performance reasons.
 * @author Shlomi Reuveni
 * @version %I%, %G%
 */
public final class ArrayHandler {

	private ArrayHandler() { }

	// TODO Write Javadoc for class methods
	public static Object get(final Object array, final int index)
			throws NullPointerException, IllegalArgumentException,
			ArrayIndexOutOfBoundsException {

		if (requireValidArray(array) instanceof Object[]) {
			return ((Object[]) array)[index];
		}

		if (array instanceof boolean[]) {
			return ((boolean[]) array)[index];
		}

		if (array instanceof byte[]) {
			return ((byte[]) array)[index];
		}

		if (array instanceof char[]) {
			return ((char[]) array)[index];
		}

		if (array instanceof int[]) {
			return ((int[]) array)[index];
		}

		if (array instanceof long[]) {
			return ((long[]) array)[index];
		}

		if (array instanceof float[]) {
			return ((float[]) array)[index];
		}

		if (array instanceof double[]) {
			return ((double[]) array)[index];
		}

		throw new IllegalArgumentException();
	}

	public static boolean getBoolean(final Object array, final int index)
			throws NullPointerException, IllegalArgumentException,
			ArrayIndexOutOfBoundsException {

		if (Objects.requireNonNull(array, "Array argument is null")
				instanceof boolean[]) {
			return ((boolean[]) array)[index];
		}

		throw new IllegalArgumentException();
	}

	public static byte getbyte(final Object array, final int index)
			throws NullPointerException, IllegalArgumentException,
			ArrayIndexOutOfBoundsException {

		if (Objects.requireNonNull(array, "Array argument is null")
				instanceof byte[]) {
			return ((byte[]) array)[index];
		}

		throw new IllegalArgumentException();
	}

	public static char getChar(final Object array, final int index)
			throws NullPointerException, IllegalArgumentException,
			ArrayIndexOutOfBoundsException {

		if (Objects.requireNonNull(array, "Array argument is null")
				instanceof byte[]) {
			return ((char[]) array)[index];
		}

		throw new IllegalArgumentException();		
	}

	public static short getShort(final Object array, final int index)
			throws NullPointerException, IllegalArgumentException,
			ArrayIndexOutOfBoundsException {
		if (requireValidArray(array) instanceof short[]) {
			return ((short[]) array)[index];
		}

		if (array instanceof byte[]) {
			return ((byte[]) array)[index];
		}


		throw new IllegalArgumentException("Array is of illegal type");
	}

	public static int getInt(final Object array, final int index)
			throws NullPointerException, IllegalArgumentException,
			ArrayIndexOutOfBoundsException {

		if (requireValidArray(array) instanceof int[]) {
			return ((int[]) array)[index];
		}

		if (array instanceof short[]) {
			return ((short[]) array)[index];
		}

		if (array instanceof char[]) {
			return ((char[]) array)[index];
		}

		if (array instanceof byte[]) {
			return ((byte[]) array)[index];
		}

		throw new IllegalArgumentException("Array is of illegal type");
	}

	public static long getLong(final Object array, final int index)
			throws NullPointerException, IllegalArgumentException,
			ArrayIndexOutOfBoundsException {

		if (requireValidArray(array) instanceof long[]) {
			return ((long[]) array)[index];
		}

		if (array instanceof int[]) {
			return ((int[]) array)[index];
		}

		if (array instanceof short[]) {
			return ((short[]) array)[index];
		}

		if (array instanceof byte[]) {
			return ((byte[]) array)[index];
		}

		throw new IllegalArgumentException("Array is of illegal type");
	}

	public static float getFloat(final Object array, final int index)
			throws NullPointerException, IllegalArgumentException,
			ArrayIndexOutOfBoundsException {

		if (requireValidArray(array) instanceof float[]) {
			return ((float[]) array)[index];
		}

		if (array instanceof int[]) {
			return ((int[]) array)[index];
		}

		if (array instanceof short[]) {
			return ((short[]) array)[index];
		}

		if (array instanceof byte[]) {
			return ((byte[]) array)[index];
		}

		throw new IllegalArgumentException("Array is of illegal type");
	}

	public static double getDouble(final Object array, final int index)
			throws NullPointerException, IllegalArgumentException,
			ArrayIndexOutOfBoundsException {

		if (requireValidArray(array) instanceof double[]) {
			return ((double[]) array)[index];
		}

		if (array instanceof float[]) {
			return ((float[]) array)[index];
		}

		if (array instanceof long[]) {
			return ((long[]) array)[index];
		}

		if (array instanceof int[]) {
			return ((int[]) array)[index];
		}

		if (array instanceof short[]) {
			return ((short[]) array)[index];
		}

		if (array instanceof byte[]) {
			return ((byte[]) array)[index];
		}

		throw new IllegalArgumentException("Array is of illegal type");
	}

	public static int getLength(final Object array) {

		if (requireValidArray(array) instanceof Object[]) {
			return ((Object[]) array).length;
		}

		if (array instanceof boolean[]) {
			return ((boolean[]) array).length;
		}

		if (array instanceof byte[]) {
			return ((byte[]) array).length;
		}

		if (array instanceof char[]) {
			return ((char[]) array).length;
		}

		if (array instanceof int[]) {
			return ((int[]) array).length;
		}

		if (array instanceof long[]) {
			return ((long[]) array).length;
		}

		if (array instanceof float[]) {
			return ((float[]) array).length;
		}

		if (array instanceof double[]) {
			return ((double[]) array).length;
		}

		throw new IllegalArgumentException();

	}

	public static Object newInstance(final Class<?> componentType,
			final int... dimentions) throws NullPointerException,
	IllegalArgumentException, NegativeArraySizeException {

		Objects.requireNonNull(componentType);

		if (0 == dimentions.length || dimentions.length > 255) {
			throw new IllegalArgumentException();
		}

		for (int dimention : dimentions) {
			if (dimention < 0) {
				throw new NegativeArraySizeException();
			}
		}

		return Array.newInstance(componentType, dimentions);
	}

	public static Object newInstance(final Class<?> componentType,
			final int length) throws NullPointerException,
	IllegalArgumentException, NegativeArraySizeException {

		Objects.requireNonNull(componentType);

		if (length < 0) {
			throw new NegativeArraySizeException();
		}

		return Array.newInstance(componentType, length);
	}

	public static void set(final Object array, final int index, final Object value)
			throws NullPointerException, IllegalArgumentException,
			ArrayIndexOutOfBoundsException {

		if (requireValidArray(array) instanceof Object[]) {
			if (value.getClass().isInstance(array)) {
				// TODO after testing store value without try-catch
				try {
					((Object[]) array)[index] = value;
				} catch (ArrayStoreException e) {
					throw new IllegalArgumentException(e);
				}
			} else {
				throw new IllegalArgumentException();
			}
		} else if (value instanceof Boolean) {
			int val = (int) value;
			System.out.println(val);
			setBoolean(array, index, (boolean) value);
		} else if (value instanceof Byte) {
			setByte(array, index, (byte) value);
		} else if (value instanceof Short) {
			setShort(array, index, (short) value);
		} else if (value instanceof Character) {
			setChar(array, index, (char) value);
		} else if (value instanceof Integer) {
			setInt(array, index, (int) value);
		} else if (value instanceof Long) {	
			setLong(array, index, (long) value);
		} else if (value instanceof Float) {
			setFloat(array, index, (float) value);
		} else if (value instanceof Double) {
			setDouble(array, index, (double) value);
		} else {
			throw new IllegalArgumentException();
		}
	}

	public static void setBoolean(final Object array, final int index,
			final boolean value) throws NullPointerException,
	IllegalArgumentException, ArrayIndexOutOfBoundsException {

		if (Objects.requireNonNull(array) instanceof boolean[]) {
			((boolean[]) array)[index] = value;
		}

		throw new IllegalArgumentException();
	}

	public static void setByte(final Object array, final int index,
			final byte value) throws NullPointerException,
	IllegalArgumentException, ArrayIndexOutOfBoundsException {

		if (requireValidArray(array) instanceof byte[]) {
			((byte[]) array)[index] = value;
		} else if (array instanceof short[]) {
			((short[]) array)[index] = value;
		} else if (array instanceof int[]) {
			((int[]) array)[index] = value;
		} else if (array instanceof long[]) {
			((long[]) array)[index] = value;
		} else if (array instanceof float[]) {
			((float[]) array)[index] = value;
		} else if (array instanceof double[]){
			((double[]) array)[index] = value;
		} else {
			throw new IllegalArgumentException();
		}

	}

	public static void setShort(final Object array, final int index,
			final short value) throws NullPointerException,
	IllegalArgumentException, ArrayIndexOutOfBoundsException {

		if (requireValidArray(array) instanceof short[]) {
			((short[]) array)[index] = value;
		} else if (array instanceof int[]) {
			((int[]) array)[index] = value;
		} else if (array instanceof long[]) {
			((long[]) array)[index] = value;
		} else if (array instanceof float[]) {
			((float[]) array)[index] = value;
		} else if (array instanceof double[]){
			((double[]) array)[index] = value;
		} else {
			throw new IllegalArgumentException();
		}

	}

	public static void setChar(final Object array, final int index,
			final char value) throws NullPointerException,
	IllegalArgumentException, ArrayIndexOutOfBoundsException {

		if (requireValidArray(array) instanceof char[]) {
			((char[]) array)[index] = value;
		} else if (array instanceof int[]) {
			((int[]) array)[index] = value;
		} else if (array instanceof long[]) {
			((long[]) array)[index] = value;
		} else if (array instanceof float[]) {
			((float[]) array)[index] = value;
		} else if (array instanceof double[]){
			((double[]) array)[index] = value;
		} else {
			throw new IllegalArgumentException();
		}

	}

	public static void setInt(final Object array, final int index,
			final int value) throws NullPointerException,
	IllegalArgumentException, ArrayIndexOutOfBoundsException {

		if (requireValidArray(array) instanceof int[]) {
			((int[]) array)[index] = value;
		} else if (array instanceof long[]) {
			((long[]) array)[index] = value;
		} else if (array instanceof float[]) {
			((float[]) array)[index] = value;
		} else if (array instanceof double[]){
			((double[]) array)[index] = value;
		} else {
			throw new IllegalArgumentException();
		}

	}

	public static void setLong(final Object array, final int index,
			final long value) throws NullPointerException,
	IllegalArgumentException, ArrayIndexOutOfBoundsException {

		if (requireValidArray(array) instanceof long[]) {
			((long[]) array)[index] = value;
		} else if (array instanceof float[]) {
			((float[]) array)[index] = value;
		} else if (array instanceof double[]){
			((double[]) array)[index] = value;
		} else {
			throw new IllegalArgumentException();
		}

	}

	public static void setFloat(final Object array, final int index,
			final float value) throws NullPointerException,
	IllegalArgumentException, ArrayIndexOutOfBoundsException {

		if (requireValidArray(array) instanceof float[]) {
			((float[]) array)[index] = value;
		} else {
			setDouble(array, index, value);
		}

	}

	public static void setDouble(final Object array, final int index,
			double value) throws NullPointerException,
	IllegalArgumentException, ArrayIndexOutOfBoundsException {

		if (Objects.requireNonNull(array) instanceof double[]) {
			((double[]) array)[index] = value;
		}

		throw new IllegalArgumentException();
	}

	/**
	 * Return the component type of an array, or an empty {@link Optional} if
	 * the argument is not an array
	 * @param array - this is the array that the component type is extracted from.
	 * @return an {@code Optional} instance. If the argument is an array, than
	 * the instance contains the component type. If the argument is not an array,
	 * than an empty {@code Optional} is returned.
	 * @throws NullPointerException if {@code array} is null
	 */
	public static Optional<Class<?>> getComponentType(Object array)
			throws NullPointerException {
		if (null == array) {
			throw new NullPointerException();
		}
		return Optional.of(array.getClass().getComponentType());
	}

	//Method that checks whether an array is valid
	private static Object requireValidArray(final Object array)
			throws NullPointerException, IllegalArgumentException{

		if (null == array) {
			throw new NullPointerException("Array argument is null");
		}

		if (!array.getClass().isArray()) {
			throw new IllegalArgumentException("Object is not array");
		}

		return array;
	}

}
