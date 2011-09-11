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
	
	private void checkStateEqualityIsEqual(Object instance1, Object instance2) {
		ObjectState state = ObjectState.Save(instance1);
		ObjectState state2 = ObjectState.Save(instance2);
		assertTrue (ObjectStateComparator.Make(state, state2).isEqual());
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
		assertFalse (ObjectStateComparator.Make(state1, state2).isEqual());
	}
	
	@Test
	public void should_objectsThatAreNotEqualButHasTheSamePrimitiveFieldsDoesHaveEqualState () {
		ClassWithNonPrimitiveFields instance = new ClassWithNonPrimitiveFields();
		ClassWithNonPrimitiveFields different = new ClassWithNonPrimitiveFields();
		assertFalse (instance.equals(different));
		
		checkStateEqualityIsEqual(instance, different);
	}
	
//	@Test
	public void shouldNot_infiniteLoopWithFieldsPointingBackwards () {
		ObjectState state1 = ObjectState.Save(new ClassWithSelfPointer());
		checkStateEqualityIsEqual(state1, state1);
	}

}
