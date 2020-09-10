package by.epam.jwd.bean;

import java.io.Serializable;

public class Subject implements Serializable {

	private static final long serialVersionUID = -2978311201970601289L;

	private int id;
	private String subjectName;
	
	public Subject() {}
	
	public Subject(int id, String subjectName) {
		this.id = id;
		this.subjectName = subjectName;
	}

	public final int getId() {
		return id;
	}

	public final void setId(int id) {
		this.id = id;
	}

	public final String getSubjectName() {
		return subjectName;
	}

	public final void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		
		result = prime * result + id;
		result = prime * result + ((subjectName == null) ? 0 : subjectName.hashCode());
		
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		
		Subject other = (Subject) obj;
		
		if (id != other.id) {
			return false;
		}
		if (subjectName == null) {
			if (other.subjectName != null) {
				return false;
			}
		} else if (!subjectName.equals(other.subjectName)) {
			return false;
		}
		
		return true;
	}

	@Override
	public String toString() {
		return "Subject [id=" + id + ", subjectName=" + subjectName + "]";
	}
}
