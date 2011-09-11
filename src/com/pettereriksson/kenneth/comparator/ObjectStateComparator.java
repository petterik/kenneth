package com.pettereriksson.kenneth.comparator;

import com.pettereriksson.kenneth.ObjectState;

public interface ObjectStateComparator {

	public boolean isEqualStates(ObjectState state, ObjectState other);
}
