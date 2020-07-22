package by.epam.jwd.bean;

import java.io.Serializable;

public class MatriculantView implements Serializable {

	private static final long serialVersionUID = -8963811872358247904L;

	private int id;
	private int idUser;
	private String nameSpecialty;
	private int certificate;
	private boolean isActivated;
	
	public MatriculantView() {
	}

	public MatriculantView(int id, int idUser, String nameSpecialty, int certificate, boolean isActivated) {
		this.id = id;
		this.idUser = idUser;
		this.nameSpecialty = nameSpecialty;
		this.certificate = certificate;
		this.isActivated = isActivated;
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

	public final String getNameSpecialty() {
		return nameSpecialty;
	}

	public final void setNameSpecialty(String nameSpecialty) {
		this.nameSpecialty = nameSpecialty;
	}

	public final int getCertificate() {
		return certificate;
	}

	public final void setCertificate(int certificate) {
		this.certificate = certificate;
	}

	public final boolean isActivated() {
		return isActivated;
	}

	public final void setActivated(boolean isActivated) {
		this.isActivated = isActivated;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		
		result = prime * result + certificate;
		result = prime * result + id;
		result = prime * result + idUser;
		result = prime * result + (isActivated ? 1231 : 1237);
		result = prime * result + ((nameSpecialty == null) ? 0 : nameSpecialty.hashCode());
		
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
		
		MatriculantView other = (MatriculantView) obj;
		
		if (certificate != other.certificate) {
			return false;
		}
		if (id != other.id) {
			return false;
		}
		if (idUser != other.idUser) {
			return false;
		}
		if (isActivated != other.isActivated) {
			return false;
		}
		if (nameSpecialty == null) {
			if (other.nameSpecialty != null) {
				return false;
			}
		} else if (!nameSpecialty.equals(other.nameSpecialty)) {
			return false;
		}
		
		return true;
	}

	@Override
	public String toString() {
		return "MatriculantView [id=" + id + ", idUser=" + idUser + ", nameSpecialty=" + nameSpecialty
				+ ", certificate=" + certificate + ", isActivated=" + isActivated + "]";
	}
}
