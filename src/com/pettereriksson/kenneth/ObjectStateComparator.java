package com.pettereriksson.kenneth;

public class ObjectStateComparator {

	private final ObjectState state;
	private final ObjectState other;

	public static ObjectStateComparator Make(ObjectState state, ObjectState other) {
		return new ObjectStateComparator(state, other);
	}
	
	private ObjectStateComparator(ObjectState state, ObjectState other) {
		this.state = state;
		this.other = other;
	}
	
	public boolean isEqual() {
		return areFieldsEqual ();
	}

	private boolean areFieldsEqual () {
		if (state.getFields().size() != other.getFields().size())
			return false;
		else 
			return doCheckFieldsEquality ();
	}

	private boolean doCheckFieldsEquality() {
		for (int i = 0; i < state.getFields ().size(); i++)
			if (!areInduvidualFieldEqual (objectFieldAtIndex(state, i), objectFieldAtIndex(other, i)))
				return false;
		return true;
	}

	private ObjectField objectFieldAtIndex(ObjectState state, int index) {
		return new ObjectField(state.getObject(), state.getField (index));
	}
	
	private boolean areInduvidualFieldEqual(ObjectField objectField, ObjectField objectField2) {
		return ObjectFieldComparator.Make(objectField, objectField2).isEqual();
	}

}
