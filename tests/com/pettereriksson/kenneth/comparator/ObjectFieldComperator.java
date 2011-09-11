package com.pettereriksson.kenneth.comparator;

import static org.junit.Assert.*;

import org.junit.Test;

import com.pettereriksson.kenneth.ObjectFieldHelper;
import com.pettereriksson.kenneth.objectfield.ObjectField;

public class ObjectFieldComperator {

	private ObjectFieldComparator getComperator (ObjectField field, ObjectField field2) {
		return new ObjectFieldComparator(ObjectStateComparatorService.get(), field, field2);
	}
	
	@Test
	public void should_handleList_same () {
		ObjectField objectField = ObjectFieldHelper.getListField ();
		assertTrue(getComperator(objectField, objectField).isEqual());
	}
	
	@Test
	public void should_handleList_different () {
		ObjectField objectField = ObjectFieldHelper.getListField (0, 1);
		ObjectField objectField2 = ObjectFieldHelper.getListField (2, 3);
		assertFalse(getComperator(objectField, objectField2).isEqual());
	}
	
}
