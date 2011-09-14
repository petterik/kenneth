package com.pettereriksson.kenneth.comparator;

import com.pettereriksson.kenneth.objectfield.ArrayField

public class ArrayFieldComparatorProvider {

	final ObjectStateComparator objectStateComparator

	public ArrayFieldComparatorProvider(ObjectStateComparator objectStateComparator) {
		this.objectStateComparator = objectStateComparator
	}

	public FieldComparator get(ArrayField arrayField, ArrayField arrayField2) {
		new ArrayFieldComparator(objectStateComparator, arrayField, arrayField2)
	}
}
