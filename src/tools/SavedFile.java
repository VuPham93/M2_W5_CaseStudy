package tools;

import java.io.Serializable;

public class SavedFile implements Serializable {
    private String name;
    private String saveLocation;

    public SavedFile() {
    }

    public SavedFile(String name, String saveLocation) {
        this.name = name;
        this.saveLocation = saveLocation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSaveLocation() {
        return saveLocation;
    }

    public void setSaveLocation(String saveLocation) {
        this.saveLocation = saveLocation;
    }
}
