package library.javaScript;

import library.Library;

public class JavaScriptLibrary extends Library {
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
}
