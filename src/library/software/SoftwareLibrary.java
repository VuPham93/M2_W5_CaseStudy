package library.software;

import library.Library;

public class SoftwareLibrary extends Library {
    private String detail;

    public SoftwareLibrary() {
    }

    public SoftwareLibrary(String serial, String name, String size, String path, String developer) {
        super(serial, name, size, path);
        this.detail = developer;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
