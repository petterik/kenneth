package com.pettereriksson.kenneth.testclasses;

public class ClassWithArray {

	@SuppressWarnings("unused")
	private final int[] array;
	
	public ClassWithArray() {
		this(0, 0);
	}

	public ClassWithArray(int i, int j) {
		array = new int[]{i, j};
	}
	
	
}
