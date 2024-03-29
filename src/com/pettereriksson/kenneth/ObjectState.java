package com.pettereriksson.kenneth;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.pettereriksson.kenneth.objectfield.ObjectField;
import com.pettereriksson.kenneth.objectfield.ObjectFieldFactory;

public class ObjectState {

	private final Object object;
	private List<ObjectField> objectFields;

	public static ObjectState SaveState(Object object) {
		return new ObjectState(object).saveFields();
	}

	private ObjectState(Object object) {
		this.object = object;
	}

	private ObjectState saveFields() {
		objectFields = new ArrayList<ObjectField>();
		addFieldsFromClassHierarcy();
		return this;
	}

	@SuppressWarnings("rawtypes")
	private void addFieldsFromClassHierarcy() {
		for (Class clazz = getObjectsClass(); clazz != null; clazz = clazz.getSuperclass())
			addFieldsFromClass(clazz);
	}

	@SuppressWarnings("rawtypes")
	private Class getObjectsClass() {
		return object == null ? null : object.getClass();
	}

	@SuppressWarnings("rawtypes")
	private void addFieldsFromClass(Class class1) {
		for (Field f : class1.getDeclaredFields())
			objectFields.add(ObjectFieldFactory.get(object, f));
	}

	public List<ObjectField> getObjectFields() {
		return objectFields;
	}

	public Field getField(int i) {
		return objectFields.get(i).getField();
	}

	public Object getObject() {
		return object;
	}

}
