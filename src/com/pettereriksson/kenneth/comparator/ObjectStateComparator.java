package com.pettereriksson.kenneth.comparator;

import java.util.ArrayList;
import java.util.List;

import com.pettereriksson.kenneth.ObjectState;
import com.pettereriksson.kenneth.objectfield.ObjectField;

public class ObjectStateComparator {

	private final FieldComparatorFactory fieldComparatorFactory;
	private final List<Object> checkedObjects;
	private ObjectState state;
	private ObjectState other;

	public ObjectStateComparator(FieldComparatorFactory fieldComparatorFactory) {
		this.fieldComparatorFactory = fieldComparatorFactory;
		this.checkedObjects = new ArrayList<Object>();
	}

	public boolean isEqualStates(ObjectState state, ObjectState other) {
		this.state = state;
		this.other = other;
		return areFieldsEqual();
	}

	private boolean areFieldsEqual() {
		if (state.getObjectFields().size() != other.getObjectFields().size())
			return false;
		else
			return doCheckFieldsEquality();
	}

	private boolean doCheckFieldsEquality() {
		for (int i = 0; i < state.getObjectFields().size(); i++)
			if (!areInduvidualFieldEqual(objectFieldAtIndex(state, i), objectFieldAtIndex(other, i)))
				return false;
		return true;
	}

	private ObjectField objectFieldAtIndex(ObjectState state, int index) {
		return state.getObjectFields().get(index);
	}

	private boolean areInduvidualFieldEqual(ObjectField objectField, ObjectField objectField2) {
		if (!areObjectsAlreadyChecked (objectField.getValue(), objectField2.getValue())) {
			markObjectsAsChecked (objectField.getValue (), objectField.getValue());
			return checkEquality(objectField, objectField2);
		}
		return true;
	}
	
	private boolean areObjectsAlreadyChecked(Object object, Object object2) {
		return isObjectContainedInCheckedObjects (object) || isObjectContainedInCheckedObjects(object2);
	}

	private boolean isObjectContainedInCheckedObjects(Object object) {
		for (Object o : checkedObjects)
			if (o == object)
				return true;
		return false;
	}	

	private void markObjectsAsChecked(Object value, Object value2) {
		checkedObjects.add(value);
		checkedObjects.add(value2);
	}

	private boolean checkEquality(ObjectField objectField, ObjectField objectField2) {
		FieldComparator comparator = fieldComparatorFactory.get (objectField, objectField2);
		return comparator.isEqual();
	}

}
