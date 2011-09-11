package com.pettereriksson.kenneth.comparator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.pettereriksson.kenneth.ObjectFieldHelper;
import com.pettereriksson.kenneth.objectfield.PrimitiveField;

public class PrimitiveFieldComparatorTest {

	@Test
	public void should_handleInts_same() {
		PrimitiveField field = ObjectFieldHelper.getPrimitiveObjectFieldWithValue(1);
		assertTrue(new PrimitiveFieldComparator(field, field).isEqual());
	}

	@Test
	public void should_handleInts_different() {
		PrimitiveField field = ObjectFieldHelper.getPrimitiveObjectFieldWithValue(1);
		PrimitiveField field2 = ObjectFieldHelper.getPrimitiveObjectFieldWithValue(3);
		assertFalse(new PrimitiveFieldComparator(field, field2).isEqual());
	}
}
