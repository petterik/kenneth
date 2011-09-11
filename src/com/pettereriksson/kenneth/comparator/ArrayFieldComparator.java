package com.pettereriksson.kenneth.comparator;

import java.lang.reflect.Array;
import java.util.Arrays;

import com.pettereriksson.kenneth.objectfield.ArrayField;

public class ArrayFieldComparator extends FieldComparator {

	ArrayFieldComparator(ArrayField objectField, ArrayField objectField2) {
		super (objectField, objectField2);
	}

	public boolean isEqual () {
		return Arrays.equals(createArray(field.getValue()), createArray(field2.getValue()));
	}
	
	private Object[] createArray (Object fieldValue) {
		int length = Array.getLength(fieldValue);
		Object[] array = new Object[length];
		for (int i = 0; i < length; i++)
			array[i] = Array.get(fieldValue, i);
		return array;
	}

}
