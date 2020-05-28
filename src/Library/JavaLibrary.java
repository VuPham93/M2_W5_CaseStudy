package Library;

public class  JavaLibrary extends Library{
    private String info;

    public JavaLibrary() {
    }

    public JavaLibrary(String serial, String name, String size, String path, String info) {
        super(serial, name, size, path);
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
