package com.pettereriksson.kenneth;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Field;
import java.util.List;

import org.junit.Test;

import com.pettereriksson.kenneth.testclasses.ClassWithTwoPrimitiveFields;

public class ObjectStateTest {

	@Test
	public void should_saveAllFields () {
		ObjectState state = ObjectState.Save (new ClassWithTwoPrimitiveFields());
		List<Field> fields = state.getFields ();
		int expectedSize = 2;
		assertEquals (expectedSize, fields.size ());
	}

}
