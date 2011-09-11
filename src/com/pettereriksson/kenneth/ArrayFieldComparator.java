package com.pettereriksson.kenneth;

import java.lang.reflect.Array;
import java.util.Arrays;

public class ArrayFieldComparator {

	private final ObjectField objectField;
	private final ObjectField objectField2;
	
	public static ArrayFieldComparator Make(ObjectField objectField, ObjectField objectField2) {
		if (!objectField.isArray() || !objectField2.isArray())
			throw new IllegalArgumentException();
		return new ArrayFieldComparator(objectField, objectField2);
	}
	
	public ArrayFieldComparator(ObjectField objectField, ObjectField objectField2) {
		this.objectField = objectField;
		this.objectField2 = objectField2;
	}

	public boolean isEqual () {
		return Arrays.equals(createArray(objectField.getValue()), createArray(objectField2.getValue()));
	}
	
	private Object[] createArray (Object fieldValue) {
		int length = Array.getLength(fieldValue);
		Object[] array = new Object[length];
		for (int i = 0; i < length; i++)
			array[i] = Array.get(fieldValue, i);
		return array;
	}

}
