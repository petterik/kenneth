package com.pettereriksson.kenneth;


import static org.junit.Assert.*;
import org.junit.Test;

import com.pettereriksson.kenneth.testclasses.ClassWithNonPrimitiveFields;
import com.pettereriksson.kenneth.testclasses.ClassWithTwoPrimitiveFields;

public class PrimitiveFieldComparatorTest {

	private ObjectField getPrimitiveObjectField () {
		Object object = new ClassWithTwoPrimitiveFields();
		return makeObjectField(object);
	}

	private ObjectField makeObjectField(Object object) {
		return ObjectState.SaveState(object).getObjectFields ().get(0);
	}
	
	private ObjectField getNonPrimitiveObjectField() {
		Object object = new ClassWithNonPrimitiveFields();
		return makeObjectField(object);
	}
	
	@Test
	public void getNonPrimitiveObjectField_isNotPrimitive () {
		assertTrue (!getNonPrimitiveObjectField().isPrimitive());
	}
	
	@Test
	public void getPrimitiveObjectField_isPrimitive () {
		assertTrue(getPrimitiveObjectField().isPrimitive());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void should_raiseIllegalArgumentExceptionWhenFirstFieldIsNotPrimitive () {
		ObjectField objectField = getNonPrimitiveObjectField();
		PrimitiveFieldComparator.Make(objectField, getPrimitiveObjectField());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void should_raiseIllegalArgumentExceptionWhenSecondFieldIsNotPrimitive () {
		ObjectField objectField = getNonPrimitiveObjectField();
		PrimitiveFieldComparator.Make(getPrimitiveObjectField(), objectField);
	}

}
