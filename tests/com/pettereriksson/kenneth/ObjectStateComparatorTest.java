package com.pettereriksson.kenneth;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.pettereriksson.kenneth.comparator.ObjectStateComparator;
import com.pettereriksson.kenneth.comparator.ObjectStateComparatorService;
import com.pettereriksson.kenneth.testclasses.ClassWithNonPrimitiveFields;
import com.pettereriksson.kenneth.testclasses.ClassWithSelfPointer;
import com.pettereriksson.kenneth.testclasses.ClassWithTwoPrimitiveFields;
import com.pettereriksson.kenneth.testclasses.ClassWithoutFields;
import com.pettereriksson.kenneth.testclasses.SubclassOfSuperClassWithPrimitiveFields;

public class ObjectStateComparatorTest {

	ObjectStateComparator objectStateComparator;

	@Before
	public void setUp() {
		objectStateComparator = ObjectStateComparatorService.get();
	}

	private ClassWithTwoPrimitiveFields createClassWithTwoFields(int i, int j) {
		return new ClassWithTwoPrimitiveFields(i, j);
	}

	private void checkStateEquality_IsEqual(Object instance1, Object instance2) {
		ObjectState fieldState = ObjectState.SaveState(instance1);
		ObjectState fieldState2 = ObjectState.SaveState(instance2);
		assertTrue(objectStateComparator.isEqualStates(fieldState, fieldState2));
	}

	private void checkStateEquality_NotEqual(Object instance, Object different) {
		assertFalse(instance.equals(different));
		ObjectState fieldState = ObjectState.SaveState(instance);
		ObjectState fieldState2 = ObjectState.SaveState(different);
		assertFalse(objectStateComparator.isEqualStates(fieldState, fieldState2));
	}

	@Test
	public void should_beEqualsToTwoSavedStatesWithSameInstanceOfAnObject() {
		ClassWithTwoPrimitiveFields instance = createClassWithTwoFields(0, 0);
		checkStateEquality_IsEqual(instance, instance);
	}

	@Test
	public void should_beEqualsToSavedStatesWithDifferentButEqualObjects() {
		ClassWithTwoPrimitiveFields instance1 = createClassWithTwoFields(0, 0);
		ClassWithTwoPrimitiveFields instance2 = createClassWithTwoFields(0, 0);
		assertTrue(instance1.equals(instance2));

		checkStateEquality_IsEqual(instance1, instance2);
	}

	@Test
	public void shouldNot_beEqualWhenFieldsHasDifferentValues() {
		ClassWithTwoPrimitiveFields instance = createClassWithTwoFields(0, 1);
		ClassWithTwoPrimitiveFields different = createClassWithTwoFields(1, 2);
		checkStateEquality_NotEqual(instance, different);
	}

	@Test
	public void should_objectsThatAreNotEqualButHasTheSamePrimitiveFieldsDoesHaveEqualState() {
		ClassWithNonPrimitiveFields instance = new ClassWithNonPrimitiveFields();
		ClassWithNonPrimitiveFields different = new ClassWithNonPrimitiveFields();
		assertFalse(instance.equals(different));

		checkStateEquality_IsEqual(instance, different);
	}

	@Test
	public void shouldNot_infiniteLoopWithFieldsPointingBackwards() {
		checkStateEquality_IsEqual(new ClassWithSelfPointer(), new ClassWithSelfPointer());
	}

	@Test
	public void should_handleObjectsWithoutFields() {
		checkStateEquality_IsEqual(new ClassWithoutFields(), new ClassWithoutFields());
	}

	@Test
	public void should_handleSubClassesFields_whereTheyDiffer() {
		Object object = new SubclassOfSuperClassWithPrimitiveFields(0, 1);
		Object object2 = new SubclassOfSuperClassWithPrimitiveFields(2, 3);
		checkStateEquality_NotEqual(object, object2);
	}

	@Test
	public void should_notDifferInStateIfBothParametersAreNull() {
		checkStateEquality_IsEqual(null, null);
	}
}
