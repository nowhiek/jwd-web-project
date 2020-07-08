package by.epam.jwd.bean;

import java.io.Serializable;

public class Faculty implements Serializable{
	
	private static final long serialVersionUID = -2536740826883434888L;
	
	private int id;
	private String facultyName;
	
	public Faculty() {
	}
	
	public Faculty(int id, String facultyName) {
		this.id = id;
		this.facultyName =facultyName;
	}

	public final int getId() {
		return id;
	}

	public final void setId(int id) {
		this.id = id;
	}

	public final String getFacultyName() {
		return facultyName;
	}

	public final void setFacultyName(String facultyName) {
		this.facultyName = facultyName;
	}

	@Override
	public String toString() {
		return "Faculty [id=" + id + ", facultyName=" + facultyName + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		
		result = prime * result + ((facultyName == null) ? 0 : facultyName.hashCode());
		result = prime * result + id;
		
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
		
		Faculty tmp = (Faculty) obj;
		
		if (facultyName == null) {
			if (tmp.facultyName != null) {
				return false;
			}
		} else if (!facultyName.equals(tmp.facultyName)) {
			return false;
		}
		if (id != tmp.id) {
			return false;
		}
		
		return true;
	}
	
	
}
