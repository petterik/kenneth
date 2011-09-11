package com.pettereriksson.kenneth.objectfield;

import java.lang.reflect.Field;

public class ObjectField {

	private final Object object;
	private final Field field;
	ObjectField(Object object, Field field) {
		this.object = object;
		this.field = field;
		field.setAccessible(true);
	}
	
	ObjectField(ObjectField objectField) {
		this(objectField.getObject(), objectField.getField());
	}

	public Object getObject() {
		return object;
	}

	public Field getField() {
		return field;
	}

	public boolean isPrimitive() {
		return field.getType().isPrimitive();
	}
	
	public boolean isArray () {
		return field.getType().isArray();
	}
	
	public Object getValue () {
		try {
			return field.get(object);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
}
