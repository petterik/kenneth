package com.pettereriksson.kenneth

import java.lang.reflect.Field
import java.util.ArrayList
import java.util.List

import com.pettereriksson.kenneth.objectfield.ObjectField
import com.pettereriksson.kenneth.objectfield.ObjectFieldFactory

public class ObjectState {

	final Object object
	final List<ObjectField> objectFields

	public static ObjectState SaveState(Object object) {
		new ObjectState(object).saveFields()
	}

	private ObjectState(Object object) {
		this.object = object
		objectFields = new ArrayList<ObjectField>()
	}

	private ObjectState saveFields() {
		addFieldsFromClassHierarcy()
		return this
	}

	private void addFieldsFromClassHierarcy() {
		Class clazz = objectsClass
		while (clazz) {
			addFieldsFromClass clazz
			clazz = clazz.superclass
		}
	}

	private Class getObjectsClass() {
		object ? object.class: null
	}

	private void addFieldsFromClass(Class class1) {
		class1.declaredFields.each { field -> objectFields.add(wrapField(field)) }
	}

	private ObjectField wrapField (Field f) {
		ObjectFieldFactory.get(object, f);
	}
}
