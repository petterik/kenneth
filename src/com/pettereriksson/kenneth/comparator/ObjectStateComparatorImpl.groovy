package com.pettereriksson.kenneth.comparator;

import java.util.ArrayList
import java.util.List

import com.pettereriksson.kenneth.ObjectState
import com.pettereriksson.kenneth.objectfield.ObjectField

public class ObjectStateComparatorImpl implements ObjectStateComparator {
	private final FieldComparatorFactory fieldComparatorFactory
	private final List<Object> checkedObjects
	private ObjectState state
	private ObjectState other

	public ObjectStateComparatorImpl(FieldComparatorFactory fieldComparatorFactory) {
		this.fieldComparatorFactory = fieldComparatorFactory;
		this.checkedObjects = new ArrayList<Object>();
	}

	public boolean isEqualStates(ObjectState state, ObjectState other) {
		this.state = state;
		this.other = other;
		if (areBothObjectsNull())
			return true;
		if (areObjectEquals())
			return true;
		else
			return areFieldsEqual();
	}

	private boolean areBothObjectsNull() {
		return state.getObject() == null && other.getObject() == null;
	}

	private boolean areObjectEquals() {
		Object object = state.getObject();
		Object object2 = other.getObject();
		if (new EqualsImplementationChecker(object).hasOwnEqualsImplementation())
			return object.equals(object2);
		else
			return false;
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
		if (objectField.getField().getName().equals("cb"))
			"hej".substring(1);
		if (!areObjectsAlreadyChecked(objectField.getValue(), objectField2.getValue())) {
			markObjectsAsChecked(objectField.getValue(), objectField.getValue());
			return checkEquality(objectField, objectField2);
		}
		return true;
	}

	private boolean areObjectsAlreadyChecked(Object object, Object object2) {
		return isObjectContainedInCheckedObjects(object) || isObjectContainedInCheckedObjects(object2);
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
		FieldComparator comparator = fieldComparatorFactory.get(objectField, objectField2);
		return comparator.isEqual();
	}
}
