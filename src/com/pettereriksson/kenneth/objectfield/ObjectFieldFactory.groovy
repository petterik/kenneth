package com.pettereriksson.kenneth.objectfield;

import java.lang.reflect.Field;

public class ObjectFieldFactory {

	public static ObjectField get(Object object, Field field) {
		ObjectField objectField = new ObjectField(object, field);
		if (objectField.isArray())
			return new ArrayField (objectField);
		if (objectField.isPrimitive())
			return new PrimitiveField (objectField);
		return objectField;
	}

	public static ObjectField get(ObjectField objectField) {
		return get(objectField.getObject(), objectField.getField());
	}

}
