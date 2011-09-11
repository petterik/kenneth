package com.pettereriksson.kenneth;

import java.util.List;

import com.pettereriksson.kenneth.objectfield.ArrayField;
import com.pettereriksson.kenneth.objectfield.ObjectField;
import com.pettereriksson.kenneth.objectfield.ObjectFieldFactory;
import com.pettereriksson.kenneth.objectfield.PrimitiveField;
import com.pettereriksson.kenneth.testclasses.ClassWithArray;
import com.pettereriksson.kenneth.testclasses.ClassWithList;
import com.pettereriksson.kenneth.testclasses.ClassWithNonPrimitiveFields;
import com.pettereriksson.kenneth.testclasses.ClassWithPrivateIntField;
import com.pettereriksson.kenneth.testclasses.ClassWithTwoPrimitiveFields;
import com.pettereriksson.kenneth.testclasses.ClassWithoutEqualsImplementation;

public class ObjectFieldHelper {

	public static ObjectField makeObjectFieldOutOfObjectsFirstField(Object object) {
		ObjectField firstField = null;
		List<ObjectField> objectFields = ObjectState.SaveState(object).getObjectFields();
		if (!objectFields.isEmpty())
			firstField = ObjectFieldFactory.get(objectFields.get(0));
		return firstField;
	}

	public static ObjectField getPrimitiveObjectField() {
		Object object = new ClassWithTwoPrimitiveFields();
		return makeObjectFieldOutOfObjectsFirstField(object);
	}

	public static ObjectField getNonPrimitiveObjectField() {
		Object object = new ClassWithNonPrimitiveFields();
		return makeObjectFieldOutOfObjectsFirstField(object);
	}

	public static ArrayField getArrayWithValues(int i, int j) {
		Object object = new ClassWithArray(i, j);
		return (ArrayField) makeObjectFieldOutOfObjectsFirstField(object);
	}

	public static PrimitiveField getPrimitiveObjectFieldWithValue(int i) {
		Object object = new ClassWithPrivateIntField(i);
		return (PrimitiveField) makeObjectFieldOutOfObjectsFirstField(object);
	}

	public static ObjectField getListField() {
		return getListField(0, 0);
	}

	public static ObjectField getListField(int i, int j) {
		Object object = new ClassWithList(i, j);
		return makeObjectFieldOutOfObjectsFirstField(object);
	}

	public static Object getObjectWithoutEqualsImplementation() {
		Object object = new ClassWithoutEqualsImplementation();
		return makeObjectFieldOutOfObjectsFirstField(object);
	}
}
