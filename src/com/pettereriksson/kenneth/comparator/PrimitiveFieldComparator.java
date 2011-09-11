package com.pettereriksson.kenneth.comparator;

import com.pettereriksson.kenneth.objectfield.PrimitiveField;


public class PrimitiveFieldComparator extends FieldComparator {

	PrimitiveFieldComparator(PrimitiveField objectField1, PrimitiveField objectField2) {
		super(objectField1, objectField2);
	}

	public boolean isEqual() {
		try {
			return doCheckEquality ();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private boolean doCheckEquality() throws Exception {
		return field.getValue().equals(field2.getValue());
	}

}
