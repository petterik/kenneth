package com.pettereriksson.kenneth;

import java.lang.reflect.Field;

public class ObjectFieldComparator {

	private final Object object;
	private final Object otherObject;
	private final Field otherField;
	private final Field field;

	public static final ObjectFieldComparator Make(ObjectField objectField1, ObjectField objectField2) {
		return new ObjectFieldComparator(objectField1.getObject(), objectField1.getField(), objectField2.getObject(), objectField2.getField());
	}
	
	private ObjectFieldComparator(Object object, Field field, Object other, Field otherField) {
		this.object = object;
		this.field = field;
		this.otherObject = other;
		this.otherField = otherField;
	}
	
	public boolean isEqual() {
		ensureAccessibleFields ();
		boolean equals = false;
		try {
			equals = doCheckEquality ();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		}
		return equals;
	}

	private void ensureAccessibleFields() {
		field.setAccessible(true);
		otherField.setAccessible(true);
	}

	private boolean doCheckEquality() throws Exception {
		Object object1 = field.get(this.object);
		Object object2 = otherField.get(otherObject);
		if (arePrimitiveFields ())
			return compareFieldsWithEquals (object1, object2);
		else
			return compareFieldsState (object1, object2);
	}


	private boolean arePrimitiveFields() {
		return isPrimitive (field) && isPrimitive (otherField);
	}

	private boolean isPrimitive(Field field) {
		return field.getType().isPrimitive();
	}

	private boolean compareFieldsWithEquals(Object object1, Object object2) {
		return object1.equals(object2);
	}

	private boolean compareFieldsState(Object object1, Object object2) {
		ObjectState save = ObjectState.Save(object1);
		ObjectState save2 = ObjectState.Save(object2);
		return save.equals(save2);
	}

}
