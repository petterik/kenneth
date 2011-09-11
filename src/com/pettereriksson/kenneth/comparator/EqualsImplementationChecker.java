package com.pettereriksson.kenneth.comparator;

import java.lang.reflect.Method;

public class EqualsImplementationChecker {

	private final Object object;

	public EqualsImplementationChecker(Object object) {
		this.object = object;
	}

	public boolean hasOwnEqualsImplementation() {
		for (Method m : object.getClass().getMethods())
			if (isTheEqualsMethod (m) && !isImplementedByObjectClass (m))
				return true;
		return false;
	}

	private boolean isTheEqualsMethod(Method m) {
		return m.getName().equals("equals");
	}

	private boolean isImplementedByObjectClass(Method m) {
		return m.getDeclaringClass() == Object.class;
	}

}
