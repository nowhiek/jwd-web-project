package by.epam.jwd.bean;

import java.io.Serializable;

public class SpecialtyView implements Serializable {
	
	private static final long serialVersionUID = -7244895409366519228L;
	
	private int id;
	private String nameTypeStudy;
	private String nameFaculty;
	private int countPlan;
	private String specialtyName;
	private int countYearStudy;

	public SpecialtyView() {
	}

	public SpecialtyView(int id, String nameTypeStudy, String nameFaculty, int countPlan, String specialtyName, int countYearStudy) {
		this.id = id;
		this.nameTypeStudy = nameTypeStudy;
		this.nameFaculty = nameFaculty;
		this.countPlan = countPlan;
		this.specialtyName = specialtyName;
		this.countYearStudy = countYearStudy;
	}

	public final int getId() {
		return id;
	}

	public final void setId(int id) {
		this.id = id;
	}

	public final String getNameTypeStudy() {
		return nameTypeStudy;
	}

	public final void setNameTypeStudy(String nameTypeStudy) {
		this.nameTypeStudy = nameTypeStudy;
	}

	public final String getNameFaculty() {
		return nameFaculty;
	}

	public final void setNameFaculty(String nameFaculty) {
		this.nameFaculty = nameFaculty;
	}

	public final int getNamePlan() {
		return countPlan;
	}

	public final void setNamePlan(int countPlan) {
		this.countPlan = countPlan;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		
		result = prime * result + countYearStudy;
		result = prime * result + id;
		result = prime * result + countPlan;
		result = prime * result + ((nameFaculty == null) ? 0 : nameFaculty.hashCode());
		result = prime * result + ((nameTypeStudy == null) ? 0 : nameTypeStudy.hashCode());
		result = prime * result + ((specialtyName == null) ? 0 : specialtyName.hashCode());
		
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
		
		SpecialtyView other = (SpecialtyView) obj;
		
		if (countYearStudy != other.countYearStudy) {
			return false;
		}
		if (id != other.id) {
			return false;
		}
		if (nameFaculty == null) {
			if (other.nameFaculty != null) {
				return false;
			}
		} else if (!nameFaculty.equals(other.nameFaculty)) {
			return false;
		}
		if (countPlan != other.countPlan) {
			return false;
		}
		if (nameTypeStudy == null) {
			if (other.nameTypeStudy != null) {
				return false;
			}
		} else if (!nameTypeStudy.equals(other.nameTypeStudy)) {
			return false;
		}
		if (specialtyName == null) {
			if (other.specialtyName != null) {
				return false;
			}
		} else if (!specialtyName.equals(other.specialtyName)) {
			return false;
		}
		
		return true;
	}

	@Override
	public String toString() {
		return "SpecialtyView [id=" + id + ", nameTypeStudy=" + nameTypeStudy + ", nameFaculty=" + nameFaculty
				+ ", countPlan=" + countPlan + ", specialtyName=" + specialtyName + ", countYearStudy=" + countYearStudy
				+ "]";
	}
}
