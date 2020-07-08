package by.epam.jwd.bean;

import java.io.Serializable;

public class TypeStudy implements Serializable {
	
	private static final long serialVersionUID = -8232450688841870063L;
	
	private int id;
	private String studyName;
	
	public TypeStudy() {
	}
	
	public TypeStudy(int id, String studyName) {
		this.id = id;
		this.studyName = studyName;
	}

	public final int getId() {
		return id;
	}

	public final void setId(int id) {
		this.id = id;
	}

	public final String getStudyName() {
		return studyName;
	}

	public final void setStudyName(String studyName) {
		this.studyName = studyName;
	}

	@Override
	public String toString() {
		return "TypeStudy [id=" + id + ", studyName=" + studyName + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		
		result = prime * result + id;
		result = prime * result + ((studyName == null) ? 0 : studyName.hashCode());
		
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
		
		TypeStudy tmp = (TypeStudy) obj;
		
		if (id != tmp.id) {
			return false;
		}
		if (studyName == null) {
			if (tmp.studyName != null) {
				return false;
			}
		} else if (!studyName.equals(tmp.studyName)) {
			return false;
		}
		
		return true;
	}
	
	
}
