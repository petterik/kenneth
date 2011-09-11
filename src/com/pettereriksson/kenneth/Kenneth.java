package com.pettereriksson.kenneth;

import com.pettereriksson.kenneth.comparator.ObjectStateComparator;
import com.pettereriksson.kenneth.comparator.ObjectStateComparatorService;

public class Kenneth {

	public static boolean isObjectsStateTheSame(Object object, Object object2) {
		ObjectState saveState = ObjectState.SaveState(object);
		ObjectState saveState2 = ObjectState.SaveState(object2);
		ObjectStateComparator objectStateComparator = ObjectStateComparatorService.get();
		return objectStateComparator.isEqualStates(saveState, saveState2);
	}

}
