package com.pettereriksson.kenneth;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ObjectState {

	private final Object object;
	private List<Field> fields;
	
	public static ObjectState Save(Object object) {
		return new ObjectState(object).saveFields();
	}
	
	private ObjectState(Object object) {
		this.object = object;
	}

	private ObjectState saveFields () {
		fields = new ArrayList<Field>();
		Collections.addAll(fields, object.getClass().getDeclaredFields());
		return this;
	}

	public List<Field> getFields() {
		return fields;
	}
	
	public Field getField (int i) {
		return fields.get(i);
	}
	
	public Object getObject () {
		return object;
	}
	
}
