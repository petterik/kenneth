package com.pettereriksson.kenneth;

import com.pettereriksson.kenneth.testclasses.ClassWithArray;
import com.pettereriksson.kenneth.testclasses.ClassWithNonPrimitiveFields;
import com.pettereriksson.kenneth.testclasses.ClassWithTwoPrimitiveFields;

public class ObjectFieldHelper {

	private static ObjectField makeObjectField(Object object) {
		return ObjectState.SaveState(object).getObjectFields ().get(0);
	}
	
	public static ObjectField getPrimitiveObjectField () {
		Object object = new ClassWithTwoPrimitiveFields();
		return makeObjectField(object);
	}

	public static ObjectField getNonPrimitiveObjectField() {
		Object object = new ClassWithNonPrimitiveFields();
		return makeObjectField(object);
	}
	
	public static ObjectField getArrayWithValues (int i, int j) {
		Object object = new ClassWithArray(i, j);
		return makeObjectField(object);
	}
}
