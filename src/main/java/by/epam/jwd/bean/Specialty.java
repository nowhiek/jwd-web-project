package by.epam.jwd.bean;

import java.io.Serializable;

public class Specialty implements Serializable{
	
	private static final long serialVersionUID = -5241260361729771526L;
	
	private int id;
	private int idTypeStudy;
	private int idFaculty;
	private int idPlan;
	private String specialtyName;
	private int countYearStudy;
	private String qualification;
	
	public Specialty() {
	}

	public Specialty(int id, int idTypeStudy, int idFaculty, int idPlan, String specialtyName, int countYearStudy, String qualification) {
		this.id = id;
		this.idTypeStudy = idTypeStudy;
		this.idFaculty = idFaculty;
		this.idPlan = idPlan;
		this.specialtyName = specialtyName;
		this.countYearStudy = countYearStudy;
		this.qualification = qualification;
	}

	public final int getId() {
		return id;
	}

	public final void setId(int id) {
		this.id = id;
	}

	public final int getIdTypeStudy() {
		return idTypeStudy;
	}

	public final void setIdTypeStudy(int idTypeStudy) {
		this.idTypeStudy = idTypeStudy;
	}

	public final int getIdFaculty() {
		return idFaculty;
	}

	public final void setIdFaculty(int idFaculty) {
		this.idFaculty = idFaculty;
	}

	public final int getIdPlan() {
		return idPlan;
	}

	public final void setIdPlan(int idPlan) {
		this.idPlan = idPlan;
	}

	public final String getSpecialtyName() {
		return specialtyName;
	}

	public final void setSpecialtyName(String specialtyName) {
		this.specialtyName = specialtyName;
	}

	public final int getCountYearStudy() {
		return countYearStudy;
	}

	public final void setCountYearStudy(int countYearStudy) {
		this.countYearStudy = countYearStudy;
	}

	public final String getQualification() {
		return qualification;
	}

	public final void setQualification(String qualification) {
		this.qualification = qualification;
	}

	@Override
	public String toString() {
		return "Specialty [id=" + id + ", idTypeStudy=" + idTypeStudy + ", idFaculty=" + idFaculty + ", idPlan="
				+ idPlan + ", specialtyName=" + specialtyName + ", countYearStudy=" + countYearStudy
				+ ", qualification=" + qualification + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		
		result = prime * result + countYearStudy;
		result = prime * result + id;
		result = prime * result + idFaculty;
		result = prime * result + idPlan;
		result = prime * result + idTypeStudy;
		result = prime * result + ((specialtyName == null) ? 0 : specialtyName.hashCode());
		result = prime * result + ((qualification == null) ? 0 : qualification.hashCode());
		
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
		
		Specialty tmp = (Specialty) obj;
		
		if (countYearStudy != tmp.countYearStudy) {
			return false;
		}
		if (id != tmp.id) {
			return false;
		}
		if (idFaculty != tmp.idFaculty) {
			return false;
		}
		if (idPlan != tmp.idPlan) {
			return false;
		}
		if (idTypeStudy != tmp.idTypeStudy) {
			return false;
		}
		if (specialtyName == null) {
			if (tmp.specialtyName != null) {
				return false;
			}
		} else if (!specialtyName.equals(tmp.specialtyName)) {
			return false;
		}
		if (qualification == null) {
			if (tmp.qualification != null) {
				return false;
			}
		} else if (!qualification.equals(tmp.qualification)) {
			return false;
		}
		
		return true;
	}
	
	
}
