package isfaaghyth.app.recorder.model;

/**
 * Created by isfaaghyth on 22/1/17.
 */

public class ItemObject {
    private int id;
    private String filename;
    private String path;

    public ItemObject(int id, String filename, String path) {
        this.id = id;
        this.filename = filename;
        this.path = path;
    }

    public int getId() {
        return id;
    }

    public String getFilename() {
        return filename;
    }

    public String getPath() {
        return path;
    }
}
