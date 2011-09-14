package com.pettereriksson.kenneth.objectfield;

import java.lang.reflect.Field

public class ObjectField {

	final Object object;
	final Field field;

	ObjectField(Object object, Field field) {
		this.object = object;
		this.field = field;
		field.setAccessible(true);
	}

	ObjectField(ObjectField objectField) {
		this(objectField.getObject(), objectField.getField());
	}

	public boolean isPrimitive() {
		return field.type.isPrimitive()
	}

	public boolean isArray() {
		return field.type.isArray()
	}

	public Object getValue() {
		try {
			return field.get(object);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public String toString() {
		return "ObjectField [object=${object}, field=${field.getName()}";
	}
}
