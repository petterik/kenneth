package com.pettereriksson.kenneth;

import java.lang.reflect.Field;

public class ObjectField {

	private final Object object;
	private final Field field;
	public ObjectField(Object object, Field field) {
		this.object = object;
		this.field = field;
	}
	
	public Object getObject() {
		return object;
	}

	public Field getField() {
		return field;
	}
	
}
