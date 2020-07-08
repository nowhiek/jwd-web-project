package by.epam.jwd.dao.connectionpool;

import java.util.ResourceBundle;

public class ResourseManager {

	private static ResourseManager instance;
    private ResourceBundle bundle = ResourceBundle.getBundle("db");

    private ResourseManager() {}

    public static ResourseManager getInstance() {
        if (instance == null) {
            instance = new ResourseManager();
        }

        return instance;
    }

    public String getValue(String key) {
        return bundle.getString(key);
    }
}
