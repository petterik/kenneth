package com.pettereriksson.kenneth.testclasses;

public class ClassWithEqualsImplementation {

	String s = this.getClass().getSimpleName();

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClassWithEqualsImplementation other = (ClassWithEqualsImplementation) obj;
		if (s == null) {
			if (other.s != null)
				return false;
		} else if (!s.equals(other.s))
			return false;
		return true;
	}
}
