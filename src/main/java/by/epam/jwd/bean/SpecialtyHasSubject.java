package by.epam.jwd.bean;

import java.io.Serializable;

public class SpecialtyHasSubject implements Serializable {
	
	private static final long serialVersionUID = 1275029343952086245L;

	private int id;
	private int idSpecialty;
	private int idSubject;
	
	public SpecialtyHasSubject() {}
	
	public SpecialtyHasSubject(int id, int idSpecialty, int idSubject) {
		this.id = id;
		this.idSpecialty = idSpecialty;
		this.idSubject = idSubject;
	}

	public final int getId() {
		return id;
	}

	public final void setId(int id) {
		this.id = id;
	}

	public final int getIdSpecialty() {
		return idSpecialty;
	}

	public final void setIdSpecialty(int idSpecialty) {
		this.idSpecialty = idSpecialty;
	}

	public final int getIdSubject() {
		return idSubject;
	}

	public final void setIdSubject(int idSubject) {
		this.idSubject = idSubject;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		
		result = prime * result + id;
		result = prime * result + idSpecialty;
		result = prime * result + idSubject;
		
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
		
		SpecialtyHasSubject other = (SpecialtyHasSubject) obj;
		
		if (id != other.id) {
			return false;
		}
		if (idSpecialty != other.idSpecialty) {
			return false;
		}
		if (idSubject != other.idSubject) {
			return false;
		}
		
		return true;
	}

	@Override
	public String toString() {
		return "SpecialtyHasSubject [id=" + id + ", idSpecialty=" + idSpecialty + ", idSubject=" + idSubject + "]";
	}
}