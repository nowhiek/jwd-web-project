package by.epam.jwd.dao.connectionpool;

import java.util.ResourceBundle;

public class ResourceManager {

	private final static ResourceManager instance = new ResourceManager();
    private ResourceBundle bundle = ResourceBundle.getBundle("db");

    private ResourceManager() {}

    public static ResourceManager getInstance() {
        return instance;
    }

    public String getValue(String key) {
        return bundle.getString(key);
    }
}
