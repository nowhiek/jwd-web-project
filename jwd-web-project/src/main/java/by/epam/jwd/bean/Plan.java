package by.epam.jwd.bean;

import java.io.Serializable;

public class Plan implements Serializable {

	private static final long serialVersionUID = 8223805006526769090L;
	
	private int id;
	private int countPlaces;
	
	public Plan() {
	}
	
	public Plan(int id, int countPlaces){
		this.id = id;
		this.countPlaces = countPlaces;
	}

	public final int getId() {
		return id;
	}

	public final void setId(int id) {
		this.id = id;
	}

	public final int getCountPlaces() {
		return countPlaces;
	}

	public final void setCountPlaces(int countPlaces) {
		this.countPlaces = countPlaces;
	}
	
	@Override
	public String toString() {
		return "Plan [id=" + id + ", countPlaces=" + countPlaces + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
	
		result = prime * result + countPlaces;
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
		
		Plan tmp = (Plan) obj;
		
		if (countPlaces != tmp.countPlaces) {
			return false;
		}
		if (id != tmp.id) {
			return false;
		}
		
		return true;
	}	
}
