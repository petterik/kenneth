package com.pettereriksson.kenneth.comparator;

import com.pettereriksson.kenneth.objectfield.ObjectField;

public class ObjectFieldComparatorProvider {

	private ObjectStateComparator objectStateComparator;

	public ObjectFieldComparatorProvider setObjectStateComparator(ObjectStateComparator objectStateComparator) {
		this.objectStateComparator = objectStateComparator;
		return this;
	}
	
	public ObjectFieldComparator get(ObjectField objectField, ObjectField objectField2) {
		return new ObjectFieldComparator(objectStateComparator, objectField, objectField2);
	}

}
