package isfaaghyth.app.recorder.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import isfaaghyth.app.recorder.R;
import isfaaghyth.app.recorder.interfaces.OnClickedListener;
import isfaaghyth.app.recorder.model.ItemObject;

/**
 * Created by isfaaghyth on 22/1/17.
 */

public class RecorderAdapter extends RecyclerView.Adapter<RecorderAdapter.RecorderHolder> {

    OnClickedListener itemClick;
    List<ItemObject> itemObjects;

    public RecorderAdapter(OnClickedListener itemClick, List<ItemObject> itemObjects) {
        this.itemClick = itemClick;
        this.itemObjects = itemObjects;
    }

    @Override public RecorderHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecorderHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item, parent, false));
    }

    @Override public void onBindViewHolder(RecorderHolder holder, int position) {
        int id = itemObjects.get(position).getId();
        String fileName = itemObjects.get(position).getFilename();
        String path = itemObjects.get(position).getPath();

        holder.txtFileName.setText(fileName);
        holder.txtPath.setText(path);
        holder.itemResult.setOnClickListener(view -> itemClick.clicked(id, path));
    }

    @Override public int getItemCount() {
        return itemObjects.size();
    }

    class RecorderHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_result_recorder) CardView itemResult;
        @BindView(R.id.txt_file_name) TextView txtFileName;
        @BindView(R.id.txt_path) TextView txtPath;

        RecorderHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
