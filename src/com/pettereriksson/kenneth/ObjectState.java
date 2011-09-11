package com.pettereriksson.kenneth;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ObjectState {

	private final Object object;
	private ArrayList<Field> fields;
	
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

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ObjectState other = (ObjectState) obj;
		if (!areFieldsEqual (other))
			return false;
		return true;
	}

	private boolean areFieldsEqual(ObjectState other) {
		if (fields.size() != other.fields.size())
			return false;
		else 
			return doCheckFieldsEqual (other);
	}

	private boolean doCheckFieldsEqual(ObjectState other) {
		for (int i = 0; i < getFields ().size(); i++)
			if (!isInduvidualFieldEqualAtIndex (i, other))
				return false;
		return true;
	}

	private boolean isInduvidualFieldEqualAtIndex (int index, ObjectState other) {
		ObjectField objectField = new ObjectField(this.object, fields.get(index));
		ObjectField objectField2 = new ObjectField(other.object, other.fields.get(index));
		return ObjectFieldComparator.Make(objectField, objectField2).isEqual();
	}

}
