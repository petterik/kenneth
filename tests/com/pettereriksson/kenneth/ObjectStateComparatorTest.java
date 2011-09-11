package com.pettereriksson.kenneth;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.pettereriksson.kenneth.testclasses.ClassWithNonPrimitiveFields;
import com.pettereriksson.kenneth.testclasses.ClassWithSelfPointer;
import com.pettereriksson.kenneth.testclasses.ClassWithTwoPrimitiveFields;

public class ObjectStateComparatorTest {

	private ClassWithTwoPrimitiveFields createClassWithTwoFields(int i, int j) {
		return new ClassWithTwoPrimitiveFields(i, j);
	}
	
	private void checkStateEquality_IsEqual(Object instance1, Object instance2) {
		ObjectState state = ObjectState.SaveState(instance1);
		ObjectState state2 = ObjectState.SaveState(instance2);
		assertTrue (ObjectStateComparator.Make(state, state2).isEqual());
	}

	private void checkStateEquality_NotEqual(Object instance, Object different) {
		assertFalse (instance.equals(different));
		ObjectState state1 = ObjectState.SaveState(instance);
		ObjectState state2 = ObjectState.SaveState(different);
		assertFalse (ObjectStateComparator.Make(state1, state2).isEqual());
	}
	
	@Test
	public void should_beEqualsToTwoSavedStatesWithSameInstanceOfAnObject () {
		ClassWithTwoPrimitiveFields instance = createClassWithTwoFields(0, 0);
		checkStateEquality_IsEqual(instance, instance);
	}

	@Test
	public void should_beEqualsToSavedStatesWithDifferentButEqualObjects () {
		ClassWithTwoPrimitiveFields instance1 = createClassWithTwoFields(0, 0);
		ClassWithTwoPrimitiveFields instance2 = createClassWithTwoFields(0, 0);
		assertTrue (instance1.equals(instance2));
		
		checkStateEquality_IsEqual(instance1, instance2);
	}
	
	@Test
	public void shouldNot_beEqualWhenFieldsHasDifferentValues () {
		ClassWithTwoPrimitiveFields instance = createClassWithTwoFields(0, 1);
		ClassWithTwoPrimitiveFields different = createClassWithTwoFields(1, 2);
		checkStateEquality_NotEqual(instance, different);
	}
	
	@Test
	public void should_objectsThatAreNotEqualButHasTheSamePrimitiveFieldsDoesHaveEqualState () {
		ClassWithNonPrimitiveFields instance = new ClassWithNonPrimitiveFields();
		ClassWithNonPrimitiveFields different = new ClassWithNonPrimitiveFields();
		assertFalse (instance.equals(different));
		
		checkStateEquality_IsEqual(instance, different);
	}
	
	@Test
	public void shouldNot_infiniteLoopWithFieldsPointingBackwards () {
		checkStateEquality_IsEqual(new ClassWithSelfPointer(), new ClassWithSelfPointer());
	}

}
