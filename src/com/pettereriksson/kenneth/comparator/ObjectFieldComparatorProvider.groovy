package com.pettereriksson.kenneth.comparator;

import com.pettereriksson.kenneth.objectfield.ObjectField

public class ObjectFieldComparatorProvider {

	private final ObjectStateComparator objectStateComparator

	public ObjectFieldComparatorProvider(ObjectStateComparator objectStateComparator) {
		this.objectStateComparator = objectStateComparator
	}

	public ObjectFieldComparator get(ObjectField objectField, ObjectField objectField2) {
		new ObjectFieldComparator(objectStateComparator, objectField, objectField2)
	}
}
