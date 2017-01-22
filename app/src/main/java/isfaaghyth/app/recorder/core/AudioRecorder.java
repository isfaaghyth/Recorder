package isfaaghyth.app.recorder.core;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.util.Log;

import java.io.File;

/**
 * Created by isfaaghyth on 22/1/17.
 */

public class AudioRecorder {

    private final String DIR_NAME = "Rekamanku";
    private String audioPath;

    private MediaRecorder recorder;
    private MediaPlayer audioPlayer;

    public AudioRecorder() {
        recorder = new MediaRecorder();
        audioPlayer = new MediaPlayer();
    }

    public void setAudioPath(String filename) {
        String basePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + DIR_NAME;
        File folder = new File(basePath);
        if (!folder.exists()) folder.mkdirs();
        this.audioPath = basePath + "/" + filename + ".3gp";
    }

    public String getAudioPath() {
        return audioPath;
    }

    public void startRecording(){
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        recorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        recorder.setOutputFile(audioPath);

        try {
            recorder.prepare();
            recorder.start();
        } catch (Exception e) {
            Log.d("ERR", e.getMessage());
        }
    }

    public void stopRecording() {
        recorder.stop();
    }

    public void playRecording(String audioPath) {
        try {
            audioPlayer.stop();
            audioPlayer.reset();

            audioPlayer.setDataSource(audioPath);
            audioPlayer.prepare();
            audioPlayer.start();
        } catch (Exception e) {
            Log.d("ERR", e.getMessage());
        }
    }
}
