package com.pettereriksson.kenneth.comparator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.pettereriksson.kenneth.ObjectFieldHelper;
import com.pettereriksson.kenneth.comparator.ArrayFieldComparator;
import com.pettereriksson.kenneth.objectfield.ArrayField;

public class ArrayFieldComperatorTest {

	@Test
	public void should_handleArrayFields_same () {
		ArrayField arrayWithValues = ObjectFieldHelper.getArrayWithValues(0, 0);
		assertTrue(new ArrayFieldComparator(arrayWithValues, arrayWithValues).isEqual());
	}
	
	@Test
	public void should_handleArrayFields_differ () {
		ArrayField instance = ObjectFieldHelper.getArrayWithValues(0, 1);
		ArrayField different = ObjectFieldHelper.getArrayWithValues(2, 3);
		assertFalse(new ArrayFieldComparator(instance, different).isEqual());
	}
	
}
