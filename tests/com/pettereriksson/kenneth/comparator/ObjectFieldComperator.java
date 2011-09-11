package com.pettereriksson.kenneth.comparator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.pettereriksson.kenneth.ObjectFieldHelper;
import com.pettereriksson.kenneth.objectfield.ObjectField;
import com.pettereriksson.kenneth.testclasses.ClassWithEqualsImplementation;
import com.pettereriksson.kenneth.testclasses.ClassWithoutEqualsImplementation;

public class ObjectFieldComperator {

	private void assertTrue_HandlingObjectsFirstField(Object object) {
		ObjectField field = ObjectFieldHelper.makeObjectFieldOutOfObjectsFirstField(object);
		ObjectField field2 = ObjectFieldHelper.makeObjectFieldOutOfObjectsFirstField(object);
		assertTrue(getComperator(field, field2).isEqual());
	}

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
	
	@Test
	public void should_handleObjectsWithEqualImplementation () {
		Object object = new ClassWithEqualsImplementation();
		assertTrue_HandlingObjectsFirstField(object);
	}
	
	@Test
	public void should_handleObjectsWithoutEqualImplementation () {
		Object object = new ClassWithoutEqualsImplementation();
		assertTrue_HandlingObjectsFirstField(object);
	}
	
}
