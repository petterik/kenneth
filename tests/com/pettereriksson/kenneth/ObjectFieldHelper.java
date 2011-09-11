package com.pettereriksson.kenneth;

import com.pettereriksson.kenneth.objectfield.ArrayField;
import com.pettereriksson.kenneth.objectfield.ObjectField;
import com.pettereriksson.kenneth.objectfield.ObjectFieldFactory;
import com.pettereriksson.kenneth.testclasses.ClassWithArray;
import com.pettereriksson.kenneth.testclasses.ClassWithNonPrimitiveFields;
import com.pettereriksson.kenneth.testclasses.ClassWithTwoPrimitiveFields;

public class ObjectFieldHelper {

	private static ObjectField makeObjectField(Object object) {
		ObjectField firstField = ObjectState.SaveState(object).getObjectFields ().get(0);
		return ObjectFieldFactory.get(firstField);
	}
	
	public static ObjectField getPrimitiveObjectField () {
		Object object = new ClassWithTwoPrimitiveFields();
		return makeObjectField(object);
	}

	public static ObjectField getNonPrimitiveObjectField() {
		Object object = new ClassWithNonPrimitiveFields();
		return makeObjectField(object);
	}
	
	public static ArrayField getArrayWithValues (int i, int j) {
		Object object = new ClassWithArray(i, j);
		return (ArrayField)makeObjectField(object);
	}
}
