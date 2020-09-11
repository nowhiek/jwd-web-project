package by.epam.jwd.bean;

public enum Status {
	ONLINE(1, "Online"),
    OFFLINE(2, "Offline");

	private int id;
    private String status;

    public static Status getStatus(int id) {
    	switch (id) {
	    	case 1 : return ONLINE;
	    	case 2 : return OFFLINE;
	    	default : return OFFLINE;
    	}
    }
    
    Status() {
    }

    Status(int id, String status) {
    	this.id = id;
        this.status = status;
    }

    public String getTitle() {
        return status;
    }
    
    public int getId() {
    	return id;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("[".concat(getClass().getSimpleName()).concat(" - status : ").concat(status).concat("]"));

        return sb.toString();
    }
}
