package com.pettereriksson.kenneth;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import com.pettereriksson.kenneth.objectfield.ObjectField;
import com.pettereriksson.kenneth.testclasses.ClassWithTwoPrimitiveFields;

public class ObjectStateTest {

	@Test
	public void should_saveAllFields() {
		ObjectState state = ObjectState.SaveState(new ClassWithTwoPrimitiveFields());
		List<ObjectField> fields = state.getObjectFields();
		int expectedSize = 2;
		assertEquals(expectedSize, fields.size());
	}

	@Test
	public void should_haveNoFields_when_objectIsNull() {
		ObjectState state = ObjectState.SaveState(null);
		assertTrue(state.getObjectFields().isEmpty());
		assertNull(state.getObject());
	}

}
