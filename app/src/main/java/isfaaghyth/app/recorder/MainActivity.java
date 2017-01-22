package isfaaghyth.app.recorder;

import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import isfaaghyth.app.recorder.adapter.RecorderAdapter;
import isfaaghyth.app.recorder.interfaces.OnClickedListener;
import isfaaghyth.app.recorder.interfaces.RecorderListener;
import isfaaghyth.app.recorder.core.AudioRecorder;
import isfaaghyth.app.recorder.core.DataRecorder;

/**
 * Created by isfaaghyth on 22/1/17.
 */

public class MainActivity extends BaseActivity implements OnClickedListener {

    @BindView(R.id.lst_recorder)
    RecyclerView lstRecorder;

    RecyclerView.Adapter adapter;
    RecorderListener dataRecord;
    AudioRecorder recorder;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.initPermission();
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        recorder = new AudioRecorder();
        dataRecord = new DataRecorder(this);

        initialize();
    }

    private void initialize() {
        lstRecorder.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecorderAdapter(this, dataRecord.showData());
        lstRecorder.setAdapter(adapter);
    }

    @OnClick(R.id.float_recorder)
    public void btnStartRecorder() {
        String filename = Long.toString(System.currentTimeMillis());
        recorder.setAudioPath(filename);
        recorder.startRecording();
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Recording");
        alert.setMessage("say something, dude!");
        alert.setPositiveButton("Stop and Save", (dialogInterface, i) -> {
            recorder.stopRecording();
            dataRecord.dataAdd(filename, recorder.getAudioPath());
            initialize();
        });
        alert.show();
    }

    @Override public void clicked(int id, String path) {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setTitle("Select Action");
        builder.setItems(new CharSequence[]{"Play", "Delete"}, (dialogInterface, i) -> {
            switch (i) {
                case 0:
                    recorder.playRecording(path);
                    break;
                case 1:
                    dataRecord.dataDelete(id);
                    new File(path).delete();
                    initialize();
                    break;
            }
        });
        builder.show();
    }
}
