package library.javaScript;

import library.Library;

import java.io.Serializable;

public class JavaScriptLibrary extends Library implements Serializable {
    private String detail;

    public JavaScriptLibrary() {
    }

    public JavaScriptLibrary(int serial, String name, String size, String path, String detail) {
        super(serial, name, size, path);
        this.detail = detail;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String toString() {
        return "Name: " + getName() + " | path: " + getPath();
    }
}
