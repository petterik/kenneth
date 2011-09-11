package com.pettereriksson.kenneth.comparator;

public class ObjectStateComparatorService {

	public static ObjectStateComparator get () {
		ObjectFieldComparatorProvider objectFieldComparatorProvider = new ObjectFieldComparatorProvider();
		FieldComparatorFactory fieldComparatorFactory = new FieldComparatorFactory(objectFieldComparatorProvider);
		ObjectStateComparator objectStateComparator = new ObjectStateComparator(fieldComparatorFactory);
		objectFieldComparatorProvider.setObjectStateComparator(objectStateComparator);
		return objectStateComparator;
	}
}
