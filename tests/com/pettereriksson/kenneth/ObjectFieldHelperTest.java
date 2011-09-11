package com.pettereriksson.kenneth;

import static org.junit.Assert.assertTrue;

import org.junit.Test;


public class ObjectFieldHelperTest {

	@Test
	public void getNonPrimitiveObjectField_isNotPrimitive () {
		assertTrue (!ObjectFieldHelper.getNonPrimitiveObjectField().isPrimitive());
	}
	
	@Test
	public void getPrimitiveObjectField_isPrimitive () {
		assertTrue(ObjectFieldHelper.getPrimitiveObjectField().isPrimitive());
	}

}
