package by.epam.jwd.bean;

public enum Role {
	USER(1, "User"),
    ADMIN(2, "Admin");

	private int id;
    private String role;
    
    public static Role getRole(int id) {
    	switch (id) {
	    	case 1 : return USER;
	    	case 2 : return ADMIN;
	    	default : return USER;
    	}
    }

    Role() {
    }

    Role(int id, String role) {
    	this.id = id;
        this.role = role;
    }

    public String getTitle() {
        return role;
    }
    
    public int getId() {
    	return id;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("[".concat(getClass().getSimpleName()).concat(" - role : ").concat(role).concat("]"));

        return sb.toString();
    }
}
