package com.pettereriksson.kenneth;

import static org.junit.Assert.assertTrue;

import java.lang.reflect.Field;

import org.junit.Before;
import org.junit.Test;

import com.pettereriksson.kenneth.testclasses.ClassWithPrivateIntField;


public class FieldTest {

	@Before
	public void setUp() throws Exception {
	}
	
	@Test
	public void ClassWithOneIntegerField_shouldContainOneIntegerField () {
		ClassWithPrivateIntField c1 = new ClassWithPrivateIntField ();
		assertTrue(hasOneIntegerField(c1));
	}
	
	private boolean hasOneIntegerField(ClassWithPrivateIntField c1) {
		for (Field f : c1.getClass().getDeclaredFields())
			if (isFieldInteger (c1, f))
				return true;
		return false;
	}

	private boolean isFieldInteger(Object obj, Field f) {
		Object object = null;
		try {
			object = getField (obj, f);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return isInteger(object);
	}

	private Object getField(Object obj, Field f) throws IllegalAccessException {
		f.setAccessible(true);
		return f.get(obj);
	}
	
	private boolean isInteger(Object object) {
		return object instanceof Integer;
	}
}
