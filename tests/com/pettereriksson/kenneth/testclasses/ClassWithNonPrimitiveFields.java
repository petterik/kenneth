package com.pettereriksson.kenneth.testclasses;

public class ClassWithNonPrimitiveFields {
	@SuppressWarnings("unused")
	private ClassWithTwoPrimitiveFields classWithTwoPrimitiveFields = new ClassWithTwoPrimitiveFields();
	
	@Override
	public boolean equals(Object obj) {
		return false;
	}
}