package com.pettereriksson.kenneth;


import org.junit.Test;

public class PrimitiveFieldComparatorTest {

	@Test(expected=IllegalArgumentException.class)
	public void should_raiseIllegalArgumentExceptionWhenFirstFieldIsNotPrimitive () {
		ObjectField objectField = ObjectFieldHelper.getNonPrimitiveObjectField();
		PrimitiveFieldComparator.Make(objectField, ObjectFieldHelper.getPrimitiveObjectField());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void should_raiseIllegalArgumentExceptionWhenSecondFieldIsNotPrimitive () {
		ObjectField objectField = ObjectFieldHelper.getNonPrimitiveObjectField();
		PrimitiveFieldComparator.Make(ObjectFieldHelper.getPrimitiveObjectField(), objectField);
	}

}
