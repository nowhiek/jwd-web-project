package by.epam.jwd.bean;

import java.io.Serializable;

public class Matriculant implements Serializable {

	private static final long serialVersionUID = 9198603860354799160L;
	
	private int id;
	private int idUser;
	private int idSpecialty;
	private int certificate;
	private boolean isActivated;
	
	public Matriculant() {
	}
	
	public Matriculant(int id, int idUser, int idSpecialty, int certificate, boolean isActivated) {
		this.id = id;
		this.idUser = idUser;
		this.idSpecialty = idSpecialty;
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
	
	public final int getIdSpecialty() {
		return idSpecialty;
	}
	
	public final void setIdSpecialty(int idSpecialty) {
		this.idSpecialty = idSpecialty;
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
		result = prime * result + idSpecialty;
		result = prime * result + idUser;
		result = prime * result + (isActivated ? 1231 : 1237);
		
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
		
		Matriculant tmp = (Matriculant) obj;
		
		if (certificate != tmp.certificate) {
			return false;
		}
		if (id != tmp.id) {
			return false;
		}
		if (idSpecialty != tmp.idSpecialty) {
			return false;
		}
		if (idUser != tmp.idUser) {
			return false;
		}
		if (isActivated != tmp.isActivated) {
			return false;
		}
		
		return true;
	}

	@Override
	public String toString() {
		return "Matriculant [id=" + id + ", idUser=" + idUser + ", idSpecialty=" + idSpecialty + ", certificate="
				+ certificate + ", isActivated=" + isActivated + "]";
	}
}
