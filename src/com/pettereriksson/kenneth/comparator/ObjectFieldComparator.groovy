package com.pettereriksson.kenneth.comparator;

import com.pettereriksson.kenneth.ObjectState;
import com.pettereriksson.kenneth.objectfield.ObjectField;

public class ObjectFieldComparator extends FieldComparator {

	private final ObjectStateComparator objectStateComparator;

	public ObjectFieldComparator(ObjectStateComparator objectStateComparator, ObjectField field, ObjectField field2) {
		super(field, field2);
		this.objectStateComparator = objectStateComparator;
	}

	@Override
	public boolean isEqual() {
		return compareState ()
	}

	private boolean compareState() {
		ObjectState fieldState = ObjectState.SaveState field.value
		ObjectState fieldState2 = ObjectState.SaveState field2.value
		return objectStateComparator.isEqualStates(fieldState, fieldState2);
	}
}
