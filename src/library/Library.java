package library;

import java.io.Serializable;

public abstract class Library implements Serializable {
    private String serial;
    private String name;
    private String size;
    private String path;

    public Library() {
    }

    public Library(String serial, String name, String size, String path) {
        this.serial = serial;
        this.name = name;
        this.size = size;
        this.path = path;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
