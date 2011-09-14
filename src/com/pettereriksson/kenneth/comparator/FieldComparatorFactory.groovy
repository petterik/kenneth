package com.pettereriksson.kenneth.comparator;

import com.pettereriksson.kenneth.objectfield.ArrayField
import com.pettereriksson.kenneth.objectfield.ObjectField
import com.pettereriksson.kenneth.objectfield.ObjectFieldFactory
import com.pettereriksson.kenneth.objectfield.PrimitiveField

public class FieldComparatorFactory {

	final ObjectFieldComparatorProvider objectFieldComparatorProvider
	final ArrayFieldComparatorProvider arrayFieldComparatorProvider

	public FieldComparatorFactory(ObjectFieldComparatorProvider objectFieldComparatorProvider,
	ArrayFieldComparatorProvider arrayFieldComparatorProvider) {
		this.objectFieldComparatorProvider = objectFieldComparatorProvider
		this.arrayFieldComparatorProvider = arrayFieldComparatorProvider
	}

	public FieldComparator get(ObjectField objectField, ObjectField objectField2) {
		ObjectField realField = ObjectFieldFactory.get(objectField)
		if (realField instanceof PrimitiveField)
			return new PrimitiveFieldComparator((PrimitiveField) objectField, (PrimitiveField) objectField2)
		else if (realField instanceof ArrayField)
			return arrayFieldComparatorProvider.get((ArrayField) objectField, (ArrayField) objectField2)
		else
			return objectFieldComparatorProvider.get(objectField, objectField2)
	}
}
