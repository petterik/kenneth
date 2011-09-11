package com.pettereriksson.kenneth.comparator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.pettereriksson.kenneth.testclasses.ClassWithEqualsImplementation;
import com.pettereriksson.kenneth.testclasses.ClassWithoutEqualsImplementation;

public class EqualsImplementationCheckerTest {

	@Test
	public void should_verifyThatClassWithEqualsImplementation_hasItsOwnImplementation() {
		ClassWithEqualsImplementation object = new ClassWithEqualsImplementation();
		assertTrue(new EqualsImplementationChecker(object).hasOwnEqualsImplementation());
	}

	@Test
	public void should_verifyThatClassWithoutEqualsImplementation_doesntHaveOwnImplementation() {
		ClassWithoutEqualsImplementation object = new ClassWithoutEqualsImplementation();
		assertFalse(new EqualsImplementationChecker(object).hasOwnEqualsImplementation());
	}

	@Test
	public void should_verifyThatIntegerClassHasEqualsImplementation() {
		assertTrue(new EqualsImplementationChecker(new Integer(5)).hasOwnEqualsImplementation());
	}

}
