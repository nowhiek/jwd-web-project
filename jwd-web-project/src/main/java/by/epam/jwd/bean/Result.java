package by.epam.jwd.bean;

import java.io.Serializable;

public class Result implements Serializable{

	private static final long serialVersionUID = 6994743355154851569L;
	
	private int id;
	private int idMatriculant;
	private int points;
	private boolean isAccepted;
	
	public Result() {
	}
	
	public Result(int id, int idMatriculant, int points, boolean isAccepted) {
		this.id = id;
		this.idMatriculant = idMatriculant;
		this.points = points;
		this.isAccepted = isAccepted;
	}

	public final int getId() {
		return id;
	}
	
	public final void setId(int id) {
		this.id = id;
	}
	
	public final int getIdMatriculant() {
		return idMatriculant;
	}
	
	public final void setIdMatriculant(int idMatriculant) {
		this.idMatriculant = idMatriculant;
	}
	
	public final int getPoints() {
		return points;
	}
	
	public final void setPoints(int points) {
		this.points = points;
	}
	
	public final boolean isAccepted() {
		return isAccepted;
	}
	
	public final void setAccepted(boolean isAccepted) {
		this.isAccepted = isAccepted;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		
		result = prime * result + id;
		result = prime * result + idMatriculant;
		result = prime * result + points;
		result = prime * result + (isAccepted ? 1231 : 1237);
		
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
		
		Result tmp = (Result) obj;
		
		if (id != tmp.id) {
			return false;
		}
		if (idMatriculant != tmp.idMatriculant) {
			return false;
		}
		if (points != tmp.points) {
			return false;	
		}
		if (isAccepted != tmp.isAccepted) {
			return false;
		}
		
		return true;
	}
}
