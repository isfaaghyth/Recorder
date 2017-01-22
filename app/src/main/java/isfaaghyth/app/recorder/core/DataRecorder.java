package isfaaghyth.app.recorder.core;

import android.content.Context;

import java.util.ArrayList;

import isfaaghyth.app.recorder.interfaces.RecorderListener;
import isfaaghyth.app.recorder.model.ItemObject;

/**
 * Created by isfaaghyth on 23/1/17.
 */

public class DataRecorder implements RecorderListener {

    private HelperRealm helperRealm;

    public DataRecorder(Context context) {
        helperRealm = new HelperRealm(context);
    }

    @Override public void dataAdd(String filename, String path) {
        helperRealm.add(filename, path);
    }

    @Override public void dataDelete(int id) {
        helperRealm.delete(id);
    }

    @Override public void dataModify(int id, String filename, String path) {
        helperRealm.modify(id, filename, path);
    }

    @Override public ArrayList<ItemObject> showData() {
        return helperRealm.show();
    }

}