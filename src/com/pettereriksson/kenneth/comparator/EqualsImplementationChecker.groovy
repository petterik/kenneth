package com.pettereriksson.kenneth.comparator;

import java.lang.reflect.Method

public class EqualsImplementationChecker {

	private final Object object;

	public EqualsImplementationChecker(Object object) {
		this.object = object;
	}

	public boolean hasOwnEqualsImplementation() {
		for (method in object.getClass().getMethods())
			if (isTheEqualsMethod (method) && !isImplementedByObjectClass (method))
				return true
		return false
	}

	private boolean isTheEqualsMethod(Method m) {
		return m.name == "equals"
	}

	private boolean isImplementedByObjectClass(Method m) {
		return m.declaringClass == Object.class;
	}
}
