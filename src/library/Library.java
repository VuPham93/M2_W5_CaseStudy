package library;

import java.io.Serializable;

public abstract class Library implements Serializable {
    private int serial;
    private String name;
    private String size;
    private String path;

    public Library() {
    }

    public Library(int serial, String name, String size, String path) {
        this.serial = serial;
        this.name = name;
        this.size = size;
        this.path = path;
    }

    public int getSerial() {
        return serial;
    }

    public void setSerial(int serial) {
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
