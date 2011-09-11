package com.pettereriksson.kenneth;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ObjectState {

	private final Object object;
	private List<ObjectField> objectFields;
	
	public static ObjectState SaveState(Object object) {
		return new ObjectState(object).saveFields();
	}
	
	private ObjectState(Object object) {
		this.object = object;
	}

	private ObjectState saveFields () {
		objectFields = new ArrayList<ObjectField>();
		for (Field f : object.getClass().getDeclaredFields())
			objectFields.add(new ObjectField(object, f));
		return this;
	}

	public List<ObjectField> getObjectFields() {
		return objectFields;
	}
	
	public Field getField (int i) {
		return objectFields.get(i).getField();
	}
	
}
