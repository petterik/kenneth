package com.pettereriksson.kenneth.comparator;

import com.pettereriksson.kenneth.objectfield.ArrayField;

public class ArrayFieldComparatorProvider {

	private final ObjectStateComparator objectStateComparator;

	public ArrayFieldComparatorProvider(ObjectStateComparator objectStateComparator) {
		this.objectStateComparator = objectStateComparator;
	}

	public FieldComparator get(ArrayField arrayField, ArrayField arrayField2) {
		return new ArrayFieldComparator(objectStateComparator, arrayField, arrayField2);
	}

}
