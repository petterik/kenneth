package com.pettereriksson.kenneth;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ArrayFieldComperatorTest {

	@Test
	public void should_handleArrayFields_same () {
		ObjectField arrayWithValues = ObjectFieldHelper.getArrayWithValues(0, 0);
		assertTrue(ArrayFieldComparator.Make(arrayWithValues, arrayWithValues).isEqual());
	}
	
	@Test
	public void should_handleArrayFields_differ () {
		ObjectField instance = ObjectFieldHelper.getArrayWithValues(0, 1);
		ObjectField different = ObjectFieldHelper.getArrayWithValues(2, 3);
		assertFalse(ArrayFieldComparator.Make(instance, different).isEqual());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void should_raiseIllegalArgumentExceptionIfFieldIsNotArray () {
		ObjectField notArrayField = ObjectFieldHelper.getPrimitiveObjectField();
		ArrayFieldComparator.Make(notArrayField, null);
	}


}
