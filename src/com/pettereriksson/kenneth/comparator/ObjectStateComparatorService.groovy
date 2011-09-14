package com.pettereriksson.kenneth.comparator;

import com.pettereriksson.kenneth.ObjectState;

public class ObjectStateComparatorService {

	public static ObjectStateComparator get() {
		ObjectStateComparatorWrapper wrapper = new ObjectStateComparatorWrapper();
		ObjectFieldComparatorProvider objectFieldComparatorProvider = new ObjectFieldComparatorProvider(wrapper);
		ArrayFieldComparatorProvider arrayFieldComparatorProvider = new ArrayFieldComparatorProvider(wrapper);
		FieldComparatorFactory fieldComparatorFactory = new FieldComparatorFactory(objectFieldComparatorProvider,
				arrayFieldComparatorProvider);
		ObjectStateComparator objectStateComparator = new ObjectStateComparatorImpl(fieldComparatorFactory);
		wrapper.setObjectStateComparator(objectStateComparator);
		return objectStateComparator;
	}

	private static class ObjectStateComparatorWrapper implements ObjectStateComparator {

		private ObjectStateComparator objectStateComparator;

		public void setObjectStateComparator(ObjectStateComparator objectStateComparator) {
			this.objectStateComparator = objectStateComparator;
		}

		@Override
		public boolean isEqualStates(ObjectState state, ObjectState other) {
			return objectStateComparator.isEqualStates(state, other);
		}

	}
}
