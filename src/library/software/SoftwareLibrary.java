package library.software;

import library.Library;

public class SoftwareLibrary extends Library {
    private String developer;

    public SoftwareLibrary() {
    }

    public SoftwareLibrary(String serial, String name, String size, String path, String developer) {
        super(serial, name, size, path);
        this.developer = developer;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }
}
