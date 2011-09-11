package com.pettereriksson.kenneth;

import com.pettereriksson.kenneth.objectfield.ArrayField;
import com.pettereriksson.kenneth.objectfield.ObjectField;
import com.pettereriksson.kenneth.objectfield.ObjectFieldFactory;
import com.pettereriksson.kenneth.objectfield.PrimitiveField;
import com.pettereriksson.kenneth.testclasses.ClassWithArray;
import com.pettereriksson.kenneth.testclasses.ClassWithNonPrimitiveFields;
import com.pettereriksson.kenneth.testclasses.ClassWithPrivateIntField;
import com.pettereriksson.kenneth.testclasses.ClassWithTwoPrimitiveFields;

public class ObjectFieldHelper {

	private static ObjectField makeObjectFieldOutOfObjectsFirstField(Object object) {
		ObjectField firstField = ObjectState.SaveState(object).getObjectFields ().get(0);
		return ObjectFieldFactory.get(firstField);
	}
	
	public static ObjectField getPrimitiveObjectField () {
		Object object = new ClassWithTwoPrimitiveFields();
		return makeObjectFieldOutOfObjectsFirstField(object);
	}

	public static ObjectField getNonPrimitiveObjectField() {
		Object object = new ClassWithNonPrimitiveFields();
		return makeObjectFieldOutOfObjectsFirstField(object);
	}
	
	public static ArrayField getArrayWithValues (int i, int j) {
		Object object = new ClassWithArray(i, j);
		return (ArrayField)makeObjectFieldOutOfObjectsFirstField(object);
	}

	public static PrimitiveField getPrimitiveObjectFieldWithValue(int i) {
		Object object = new ClassWithPrivateIntField(i);
		return (PrimitiveField)makeObjectFieldOutOfObjectsFirstField(object);
	}
}
