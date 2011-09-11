package com.pettereriksson.kenneth.comparator;

import com.pettereriksson.kenneth.objectfield.ObjectField;

public abstract class FieldComparator {

	protected final ObjectField field;
	protected final ObjectField field2;
	
	public FieldComparator(ObjectField field, ObjectField field2) {
		this.field = field;
		this.field2 = field2;
	}

	public abstract boolean isEqual ();
}
