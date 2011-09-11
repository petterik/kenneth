package com.pettereriksson.kenneth;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.List;

import org.junit.Test;

public class ObjectStateTest {

	private ClassWithTwoPrimitiveFields createClassWithTwoFields(int i, int j) {
		return new ClassWithTwoPrimitiveFields(i, j);
	}
	
	private void checkStateEqualityIsEqual(Object instance1, Object instance2) {
		ObjectState state = ObjectState.Save(instance1);
		ObjectState state2 = ObjectState.Save(instance2);
		assertTrue (state.equals(state2));
	}
	
	private static class ClassWithTwoPrimitiveFields {
		private int i = 0;
		private double d = 2;
		
		public ClassWithTwoPrimitiveFields() {
		}
		
		public ClassWithTwoPrimitiveFields(int i, double d) {
			this.i = i;
			this.d = d;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			ClassWithTwoPrimitiveFields other = (ClassWithTwoPrimitiveFields) obj;
			if (Double.doubleToLongBits(d) != Double.doubleToLongBits(other.d))
				return false;
			if (i != other.i)
				return false;
			return true;
		}
	}
	
	private static class ClassWithNonPrimitiveFields {
		@SuppressWarnings("unused")
		ClassWithTwoPrimitiveFields classWithTwoPrimitiveFields = new ClassWithTwoPrimitiveFields();
		
		@Override
		public boolean equals(Object obj) {
			return false;
		}
	}
	
	private static class ClassWithSelfPointer {
		ClassWithSelfPointer c = this;
	}
	
	@Test
	public void should_saveAllFields () {
		ObjectState state = ObjectState.Save (new ClassWithTwoPrimitiveFields());
		List<Field> fields = state.getFields ();
		int expectedSize = 2;
		assertEquals (expectedSize, fields.size ());
	}
	
	@Test
	public void should_beEqualsToTwoSavedStatesWithSameInstanceOfAnObject () {
		ClassWithTwoPrimitiveFields instance = createClassWithTwoFields(0, 0);
		checkStateEqualityIsEqual(instance, instance);
	}

	@Test
	public void should_beEqualsToSavedStatesWithDifferentButEqualObjects () {
		ClassWithTwoPrimitiveFields instance1 = createClassWithTwoFields(0, 0);
		ClassWithTwoPrimitiveFields instance2 = createClassWithTwoFields(0, 0);
		assertTrue (instance1.equals(instance2));
		
		checkStateEqualityIsEqual(instance1, instance2);
	}
	
	@Test
	public void shouldNot_beEqualWhenFieldsHasDifferentValues () {
		ClassWithTwoPrimitiveFields instance = createClassWithTwoFields(0, 1);
		ClassWithTwoPrimitiveFields different = createClassWithTwoFields(1, 2);
		assertFalse (instance.equals(different));
		
		ObjectState state1 = ObjectState.Save(instance);
		ObjectState state2 = ObjectState.Save(different);
		assertFalse (state1.equals(state2));
	}
	
	@Test
	public void should_objectsThatAreNotEqualButHasTheSamePrimitiveFieldsDoesHaveEqualState () {
		ClassWithNonPrimitiveFields instance = new ClassWithNonPrimitiveFields();
		ClassWithNonPrimitiveFields different = new ClassWithNonPrimitiveFields();
		assertFalse (instance.equals(different));
		
		checkStateEqualityIsEqual(instance, different);
	}
	
	//@Test
	public void shouldNot_infiniteLoopWithFieldsPointingBackwards () {
		ObjectState state1 = ObjectState.Save(new ClassWithSelfPointer());
		checkStateEqualityIsEqual(state1, state1);
	}

}
