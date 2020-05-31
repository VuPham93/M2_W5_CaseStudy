package library.java;

import library.Library;

public class  JavaLibrary extends Library {
    private String detail;

    public JavaLibrary() {
    }

    public JavaLibrary(String serial, String name, String size, String path, String detail) {
        super(serial, name, size, path);
        this.detail = detail;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
