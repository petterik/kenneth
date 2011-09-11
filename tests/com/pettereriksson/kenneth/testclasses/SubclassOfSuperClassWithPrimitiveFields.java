package com.pettereriksson.kenneth.testclasses;

public class SubclassOfSuperClassWithPrimitiveFields extends ClassWithTwoPrimitiveFields {

	@SuppressWarnings("unused")
	private final int k = 0;

	public SubclassOfSuperClassWithPrimitiveFields() {
		super(0, 0);
	}

	public SubclassOfSuperClassWithPrimitiveFields(int a, int b) {
		super(a, b);
	}

}
