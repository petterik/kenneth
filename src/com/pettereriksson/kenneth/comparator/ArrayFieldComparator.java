package com.pettereriksson.kenneth.comparator;

import java.lang.reflect.Array;

import com.pettereriksson.kenneth.ObjectState;
import com.pettereriksson.kenneth.objectfield.ArrayField;

public class ArrayFieldComparator extends FieldComparator {

	private final ObjectStateComparator objectStateComparator;

	ArrayFieldComparator(ObjectStateComparator objectStateComparator, ArrayField objectField, ArrayField objectField2) {
		super(objectField, objectField2);
		this.objectStateComparator = objectStateComparator;
	}

	public boolean isEqual() {
		Object[] array = createArray(field.getValue());
		Object[] array2 = createArray(field2.getValue());
		if (array.length != array2.length)
			return false;
		for (int i = 0; i < array.length; i++)
			if (!isEqual(array[i], array2[i]))
				return false;
		return true;
	}

	private boolean isEqual(Object object, Object object2) {
		if (object == null && object2 == null)
			return true;
		else
			return isNonNullObjectsEqual(object, object2);
	}

	private boolean isNonNullObjectsEqual(Object object, Object object2) {
		if (isEqualsImplemented(object) && isEqualsImplemented(object2))
			return object.equals(object2);
		else
			return isEqualObjectState(object, object2);
	}

	private boolean isEqualsImplemented(Object object) {
		return new EqualsImplementationChecker(object).hasOwnEqualsImplementation();
	}

	private Object[] createArray(Object fieldValue) {
		int length = Array.getLength(fieldValue);
		Object[] array = new Object[length];
		for (int i = 0; i < length; i++)
			array[i] = Array.get(fieldValue, i);
		return array;
	}

	private boolean isEqualObjectState(Object object, Object object2) {
		ObjectState state = ObjectState.SaveState(object);
		ObjectState state2 = ObjectState.SaveState(object2);
		return objectStateComparator.isEqualStates(state, state2);
	}

}
