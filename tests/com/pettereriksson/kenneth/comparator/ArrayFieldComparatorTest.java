package com.pettereriksson.kenneth.comparator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.pettereriksson.kenneth.ObjectFieldHelper;
import com.pettereriksson.kenneth.objectfield.ArrayField;
import com.pettereriksson.kenneth.objectfield.ObjectField;
import com.pettereriksson.kenneth.objectfield.ObjectFieldFactory;
import com.pettereriksson.kenneth.testclasses.ClassWithoutEqualsImplementation;

public class ArrayFieldComparatorTest {

	private ArrayFieldComparator getComperator(ArrayField field, ArrayField field2) {
		return new ArrayFieldComparator(ObjectStateComparatorService.get(), field, field2);
	}

	@Test
	public void should_handleArrayFields_same() {
		ArrayField arrayWithValues = ObjectFieldHelper.getArrayWithValues(0, 0);
		assertTrue(getComperator(arrayWithValues, arrayWithValues).isEqual());
	}

	@Test
	public void should_handleArrayFields_differ() {
		ArrayField instance = ObjectFieldHelper.getArrayWithValues(0, 1);
		ArrayField different = ObjectFieldHelper.getArrayWithValues(2, 3);
		assertFalse(getComperator(instance, different).isEqual());
	}

	@Test
	public void should_handleArraysWithOutEqualImplementation() {
		ArrayField arrayField = createArrayFieldWithObjectsWithoutEqualImplementation();
		ArrayField arrayField2 = createArrayFieldWithObjectsWithoutEqualImplementation();
		assertTrue(getComperator(arrayField, arrayField2).isEqual());
	}

	private ArrayField createArrayFieldWithObjectsWithoutEqualImplementation() {
		ObjectField object = createClassesWithArrayWithObjectsWithoutEqualImplementation();
		return (ArrayField) ObjectFieldFactory.get(object);
	}

	private ObjectField createClassesWithArrayWithObjectsWithoutEqualImplementation() {
		Object object = new ClassWithArrayWithObjectsWithoutEqualImplementation();
		return ObjectFieldHelper.makeObjectFieldOutOfObjectsFirstField(object);
	}

	private static class ClassWithArrayWithObjectsWithoutEqualImplementation {

		final Object[] objects;

		public ClassWithArrayWithObjectsWithoutEqualImplementation() {
			objects = new Object[3];
			for (int i = 0; i < objects.length; i++)
				objects[i] = new ClassWithoutEqualsImplementation();
		}
	}

}
