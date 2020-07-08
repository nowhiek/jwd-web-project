package by.epam.jwd.bean;

import java.io.Serializable;
import java.sql.Date;

public class UserDetail implements Serializable {
	
	private static final long serialVersionUID = -4333618218929475592L;

	private int id;
	private int idUser;
	private String firstName;
	private String secondName;
	private Date birthday;
	private String sex;
	private String serialPassport;
	private int numberPassport;
	
	public UserDetail() {
	}
	
	public UserDetail(int id, int idUser, String firstName, String secondName, Date birthday, String sex, String serialPassport, int numberPassport) {
		this.id = id;
		this.idUser = idUser;
		this.firstName = firstName;
		this.secondName = secondName;
		this.birthday = birthday;
		this.sex = sex;
		this.setSerialPassport(serialPassport);
		this.setNumberPassport(numberPassport);
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
	
	public final String getFirstName() {
		return firstName;
	}
	
	public final void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public final String getSecondName() {
		return secondName;
	}
	
	public final void setSecondName(String secondName) {
		this.secondName = secondName;
	}
	
	public final Date getBirthday() {
		return birthday;
	}
	
	public final void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	public final String getSex() {
		return sex;
	}
	
	public final void setSex(String sex) {
		this.sex = sex;
	}

	public final String getSerialPassport() {
		return serialPassport;
	}

	public final void setSerialPassport(String serialPassport) {
		this.serialPassport = serialPassport;
	}

	public final int getNumberPassport() {
		return numberPassport;
	}

	public final void setNumberPassport(int numberPassport) {
		this.numberPassport = numberPassport;
	}
	
	@Override
	public String toString() {
		return "UserDetail [id=" + id + ", idUser=" + idUser + ", firstName=" + firstName + ", secondName=" + secondName
				+ ", birthday=" + birthday + ", sex=" + sex + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		
		result = prime * result * id * idUser + ((firstName == null) ? 0 : firstName.hashCode()); 
		result = prime * result + ((secondName == null) ? 0 : secondName.hashCode());
		result = prime * result + ((birthday == null) ? 0 : birthday.hashCode());
		result = prime * result + ((sex == null) ? 0 : sex.hashCode());
		
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
		
		UserDetail tmp = (UserDetail) obj;
		
		if (birthday == null) {
			if (tmp.birthday != null) {
				return false;
			}
		} else if (!birthday.equals(tmp.birthday)) {
			return false;
		}
		if (firstName == null) {
			if (tmp.firstName != null) {
				return false;
			}
		} else if (!firstName.equals(tmp.firstName)) {
			return false;
		}
		if (id != tmp.id) {
			return false;
		}
		if (idUser != tmp.idUser) {
			return false;
		}
		if (secondName == null) {
			if (tmp.secondName != null) {
				return false;
			}
		} else if (!secondName.equals(tmp.secondName)) {
			return false;
		}
		if (sex == null) {
			if (tmp.sex != null) {
				return false;
			}
		} else if (!sex.equals(tmp.sex)) {
			return false;
		}
		
		return true;
	}
}
