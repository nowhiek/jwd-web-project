package by.epam.jwd.bean;

import java.io.Serializable;

public class Exam implements Serializable {

	private static final long serialVersionUID = 108310923280723461L;

	private int id;
	private int idUser;
	private int idMatriculant;
	private int idSpecialtyHasSubject;
	private int resultMark;
	
	public Exam() {}

	public Exam(int id, int idUser, int idMatriculant, int idSpecialtyHasSubject, int resultMark) {
		this.id = id;
		this.idUser = idUser;
		this.idMatriculant = idMatriculant;
		this.idSpecialtyHasSubject = idSpecialtyHasSubject;
		this.resultMark = resultMark;
	}

	public final int getId() {
		return id;
	}

	public final void setId(int id) {
		this.id = id;
	}

	public final int getIdUser() {
		return idUser;
	}

	public final void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public final int getIdMatriculant() {
		return idMatriculant;
	}

	public final void setIdMatriculant(int idMatriculant) {
		this.idMatriculant = idMatriculant;
	}

	public final int getIdSpecialtyHasSubject() {
		return idSpecialtyHasSubject;
	}

	public final void setIdSpecialtyHasSubject(int idSpecialtyHasSubject) {
		this.idSpecialtyHasSubject = idSpecialtyHasSubject;
	}

	public final int getResultMark() {
		return resultMark;
	}

	public final void setResultMark(int resultMark) {
		this.resultMark = resultMark;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		
		result = prime * result + id;
		result = prime * result + idMatriculant;
		result = prime * result + idSpecialtyHasSubject;
		result = prime * result + idUser;
		result = prime * result + resultMark;
		
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
		
		Exam other = (Exam) obj;
		
		if (id != other.id) {
			return false;
		}
		if (idMatriculant != other.idMatriculant) {
			return false;
		}
		if (idSpecialtyHasSubject != other.idSpecialtyHasSubject) {
			return false;
		}
		if (idUser != other.idUser) {
			return false;
		}
		if (resultMark != other.resultMark) {
			return false;
		}
		
		return true;
	}

	@Override
	public String toString() {
		return "Exam [id=" + id + ", idUser=" + idUser + ", idMatriculant=" + idMatriculant + ", idSpecialtyHasSubject="
				+ idSpecialtyHasSubject + ", resultMark=" + resultMark + "]";
	}
}
