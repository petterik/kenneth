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
		if (areObjectEqual())
			return true;
		else
			return areFieldsEqual();
	}

	private boolean areBothObjectsNull() {
		return !state.object && !other.object
	}

	private boolean areObjectEqual() {
		if (new EqualsImplementationChecker(state.object).hasOwnEqualsImplementation())
			return state.object == other.object
		else
			return false;
	}

	private boolean areFieldsEqual() {
		if (state.objectFields.size() != other.objectFields.size())
			return false;
		else
			return doCheckFieldsEquality ()
	}

	private boolean doCheckFieldsEquality() {
		def objectFieldAtIndex = {  objectState, int i -> objectState.objectFields.get(i) }
		for (int i = 0; i < state.objectFields.size(); i++)
			if (!areInduvidualFieldEqual(objectFieldAtIndex(state, i), objectFieldAtIndex(other, i)))
				return false
		return true
	}

	private boolean areInduvidualFieldEqual(ObjectField objectField, ObjectField objectField2) {
		if (!areObjectsAlreadyChecked(objectField.value, objectField2.value)) {
			markObjectsAsChecked(objectField.value, objectField.value)
			return checkEquality(objectField, objectField2)
		}
		return true;
	}

	private boolean areObjectsAlreadyChecked(Object object, Object object2) {
		isObjectContainedInCheckedObjects(object) || isObjectContainedInCheckedObjects(object2)
	}

	private boolean isObjectContainedInCheckedObjects(Object object) {
		for (o in checkedObjects)
			if (o.is(object))
				return true
		return false
	}

	private void markObjectsAsChecked(Object value, Object value2) {
		checkedObjects.add(value)
		checkedObjects.add(value2)
	}

	private boolean checkEquality(ObjectField objectField, ObjectField objectField2) {
		FieldComparator comparator = fieldComparatorFactory.get(objectField, objectField2)
		comparator.isEqual()
	}
}
