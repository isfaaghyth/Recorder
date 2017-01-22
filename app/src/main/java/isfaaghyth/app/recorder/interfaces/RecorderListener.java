package isfaaghyth.app.recorder.interfaces;

import java.util.ArrayList;

import isfaaghyth.app.recorder.model.ItemObject;

/**
 * Created by isfaaghyth on 23/1/17.
 */

public interface RecorderListener {

    void dataAdd(String filename, String path);

    void dataDelete(int id);

    void dataModify(int id, String filename, String path);

    ArrayList<ItemObject> showData();
}
