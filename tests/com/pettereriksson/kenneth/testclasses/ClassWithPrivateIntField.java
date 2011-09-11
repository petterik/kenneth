package com.pettereriksson.kenneth.testclasses;

public class ClassWithPrivateIntField {

	@SuppressWarnings("unused")
	private final int i;

	public ClassWithPrivateIntField(int i) {
		this.i = i;
	}

	public ClassWithPrivateIntField() {
		i = -1;
	}

}
