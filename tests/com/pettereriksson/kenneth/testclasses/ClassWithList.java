package com.pettereriksson.kenneth.testclasses;

import java.util.ArrayList;
import java.util.List;

public class ClassWithList {

	List<Integer> list = new ArrayList<Integer>();
	
	public ClassWithList() {
		list.add(1);
		list.add(2);
	}

	public ClassWithList(int i, int j) {
		list.add(i);
		list.add(j);
	}
	
}
