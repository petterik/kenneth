package com.pettereriksson.kenneth.testclasses;

public class ClassWithTwoPrimitiveFields {
	private int i = 0;
	private double d = 2;

	public ClassWithTwoPrimitiveFields() {
	}

	public ClassWithTwoPrimitiveFields(int i, double d) {
		this.i = i;
		this.d = d;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClassWithTwoPrimitiveFields other = (ClassWithTwoPrimitiveFields) obj;
		if (Double.doubleToLongBits(d) != Double.doubleToLongBits(other.d))
			return false;
		if (i != other.i)
			return false;
		return true;
	}
}