package com.sol.testutil.arrayutils;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.sol.util.ArrayUtililities;

public class TestGetArrayInstance {
	private final String[] regularArray = {"This", "array", "is", "not", "empty"};
	private final String[] emptyArray = new String[0];
	private final int positiveLength = 10;
	private final int negativeLength = -1;

	@Test(description = "Test getArrayLength(array) with a regular array")
	public void testRegularArray() {
		String[] newArray = ArrayUtililities.getArrayInstance(regularArray);

		Assert.assertEquals(regularArray.length, newArray.length);
		Assert.assertTrue(newArray.getClass().isInstance(regularArray));
	}

	@Test(description = "Test getArrayLength(array) with an empty array")
	public void testEmptyArray() {
		String[] newArray = ArrayUtililities.getArrayInstance(emptyArray);

		Assert.assertEquals(newArray.length, emptyArray.length);
		Assert.assertTrue(newArray.getClass().isInstance(emptyArray));
	}

	@Test(description = "Test getArrayLength(array) with null")
	public void testNull() {
		Assert.assertThrows(NullPointerException.class,
				() -> ArrayUtililities.getArrayInstance(null));
	}

	@Test(description = "Test getArrayLength(array, length) with a regular "
			+ "array and a positive length")
	public void testRegularArrayWithPositiveLength() {
		String[] newArray =
				ArrayUtililities.getArrayInstance(regularArray, positiveLength);

		Assert.assertEquals(positiveLength, newArray.length);
		Assert.assertTrue(newArray.getClass().isInstance(regularArray));
	}

	@Test(description = "Test getArrayLength(array, length) with a regular "
			+ "array and a length of 0")
	public void testRegularArrayWithZeroLength() {
		String[] newArray = ArrayUtililities.getArrayInstance(regularArray, 0);

		Assert.assertEquals(0, newArray.length);
		Assert.assertTrue(newArray.getClass().isInstance(regularArray));
	}

	@Test(description = "Test getArrayLength(array, length) with a regular "
			+ "array and a negative length")
	public void testRegularArrayWithNegativeLength() {
		Assert.assertThrows(NegativeArraySizeException.class,
				() -> ArrayUtililities
				.getArrayInstance(regularArray, negativeLength));
	}

	@Test(description = "Test getArrayLength(array, length) with an empty "
			+ "array and a positive length")
	public void testEmptyArrayWithPositiveLength() {
		String[] newArray =
				ArrayUtililities.getArrayInstance(emptyArray, positiveLength);

		Assert.assertEquals(positiveLength, newArray.length);
		Assert.assertTrue(newArray.getClass().isInstance(regularArray));
	}

	@Test(description = "Test getArrayLength(array, length) with an empty"
			+ "array and a length of 0")
	public void testEmptyArrayWithZeroLength() {
		String[] newArray =
				ArrayUtililities.getArrayInstance(emptyArray, 0);

		Assert.assertEquals(0, newArray.length);
		Assert.assertTrue(newArray.getClass().isInstance(regularArray));
	}

	@Test(description = "Test getArrayLength(array, length) with a regular "
			+ "array and a negative length")
	public void testEmptyArrayWithNegativeLength() {
		Assert.assertThrows(NegativeArraySizeException.class,
				() -> ArrayUtililities.getArrayInstance(emptyArray, negativeLength));
	}

	@Test(description = "Test getArrayLength(array, length) with a null "
			+ "and a positive length")
	public void testNullWithPositiveLength() {
		Assert.assertThrows(NullPointerException.class,
				() -> ArrayUtililities.getArrayInstance(null, positiveLength));
	}

	@Test(description = "Test getArrayLength(array, length) with a null "
			+ "and a length of 0")
	public void testNullWithZeroLength() {
		Assert.assertThrows(NullPointerException.class,
				() -> ArrayUtililities.getArrayInstance(null, 0));
	}

	@Test(description = "Test getArrayLength(array, length) with a null "
			+ "and a negative length")
	public void testNullWithNegativeLength() {
		Assert.assertThrows(() -> ArrayUtililities
				.getArrayInstance(null, negativeLength));
	}

}
