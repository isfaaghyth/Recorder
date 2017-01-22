package isfaaghyth.app.recorder.core;

import android.content.Context;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import isfaaghyth.app.recorder.model.ItemObject;
import isfaaghyth.app.recorder.model.ItemRealm;

/**
 * Created by isfaaghyth on 22/1/17.
 */

public class HelperRealm {
    private Realm realm;
    private RealmResults<ItemRealm> realmResults;

    public HelperRealm(Context context) {
        Realm.init(context);
        RealmConfiguration config = new RealmConfiguration
                .Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        realm = Realm.getInstance(config);
    }

    private int getCount() {
        realmResults = realm.where(ItemRealm.class).findAll();
        realmResults.sort("id");
        return realmResults.size();
    }

    public ArrayList<ItemObject> show() {
        int id;
        String name, path;
        ArrayList<ItemObject> data = new ArrayList<>();

        realmResults = realm.where(ItemRealm.class).findAll();
        realmResults.sort("id");

        if (realmResults.size() > 0) {
            for (int i = 0; i < realmResults.size(); i++) {
                id = realmResults.get(i).getId();
                name = realmResults.get(i).getFilename();
                path = realmResults.get(i).getPath();
                data.add(new ItemObject(id, name, path));
            }
        }

        return data;
    }

    public void add(String filename, String path) {
        ItemRealm data = new ItemRealm();
        data.setId(getCount() + 1);
        data.setFilename(filename);
        data.setPath(path);

        realm.beginTransaction();
        realm.copyToRealm(data);
        realm.commitTransaction();
    }

    public void delete(int id) {
        RealmResults<ItemRealm> dataRealms = realm.where(ItemRealm.class).equalTo("id", id).findAll();
        realm.beginTransaction();
        dataRealms.get(0).deleteFromRealm();
        realm.commitTransaction();
    }

    public void modify(int id, String filename, String path) {
        realm.beginTransaction();
        ItemRealm dataRealm = realm.where(ItemRealm.class).equalTo("id", id).findFirst();
        dataRealm.setFilename(filename);
        dataRealm.setPath(path);
        realm.commitTransaction();
    }
}
