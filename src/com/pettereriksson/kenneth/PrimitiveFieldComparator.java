package com.pettereriksson.kenneth;


public class PrimitiveFieldComparator {

	private final ObjectField objectField1;
	private final ObjectField objectField2;
	
	public static final PrimitiveFieldComparator Make(ObjectField objectField1, ObjectField objectField2) {
		if (!objectField1.isPrimitive() || !objectField2.isPrimitive())
			throw new IllegalArgumentException();
		return new PrimitiveFieldComparator(objectField1, objectField2);
	}
	
	public PrimitiveFieldComparator(ObjectField objectField1, ObjectField objectField2) {
		this.objectField1 = objectField1;
		this.objectField2 = objectField2;
	}

	public boolean isEqual() {
		try {
			return doCheckEquality ();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private boolean doCheckEquality() throws Exception {
		return objectField1.getValue().equals(objectField2.getValue());
	}

}
