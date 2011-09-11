package com.pettereriksson.kenneth;

import java.util.ArrayList;
import java.util.List;

public class ObjectStateComparator {

	private final ObjectState state;
	private final ObjectState other;
	private List<Object> checkedObjects;

	public static ObjectStateComparator Make(ObjectState state, ObjectState other) {
		return new ObjectStateComparator(state, other).setCheckedObjects(new ArrayList<Object>());
	}
	
	private static ObjectStateComparator Make(ObjectState state, ObjectState other, List<Object> checkedObjects) {
		return new ObjectStateComparator(state, other).setCheckedObjects (checkedObjects);
	}

	private ObjectStateComparator setCheckedObjects(List<Object> checkedObjects) {
		this.checkedObjects = checkedObjects;
		return this;
	}

	private ObjectStateComparator(ObjectState state, ObjectState other) {
		this.state = state;
		this.other = other;
	}

	public boolean isEqual() {
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

	private void markObjectsAsChecked(Object value, Object value2) {
		checkedObjects.add(value);
		checkedObjects.add(value2);
	}

	private boolean checkEquality(ObjectField objectField, ObjectField objectField2) {
		if (areFieldsPrimitive(objectField, objectField2))
			return PrimitiveFieldComparator.Make(objectField, objectField2).isEqual();
		else
			return isNonPrimitiveFieldsPrimitiveSubPartsEqual(objectField, objectField2);
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

	private boolean areFieldsPrimitive(ObjectField objectField, ObjectField objectField2) {
		return objectField.isPrimitive() && objectField2.isPrimitive();
	}

	private boolean isNonPrimitiveFieldsPrimitiveSubPartsEqual(ObjectField objectField, ObjectField objectField2) {
		ObjectState save = ObjectState.SaveState(objectField.getValue());
		ObjectState save2 = ObjectState.SaveState(objectField2.getValue());
		return ObjectStateComparator.Make(save, save2, checkedObjects).isEqual();
	}

}
