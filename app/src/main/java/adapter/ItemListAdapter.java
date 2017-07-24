package adapter;

import com.spotsoon.httpswww.home.ItemListActivity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.spotsoon.httpswww.home.R;

import java.util.List;

/**
 * Created by Sreerag on 23-07-2017.
 */

public class ItemListAdapter  extends RecyclerView.Adapter<ItemListAdapter.MyViewHolder> {

    private Context mContext;
    private List<ItemListActivity> albumList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, count, description;
        public ImageView thumbnail, overflow;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            count = (TextView) view.findViewById(R.id.count);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
            description = (TextView) view.findViewById(R.id.song_description);
        }
    }


    public ItemListAdapter(Context mContext, List<ItemListActivity> albumList) {
        this.mContext = mContext;
        this.albumList = albumList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        ItemListActivity album = albumList.get(position);
        holder.title.setText(album.getName());
        holder.count.setText(album.getNumOfSongs() + " HOURS AGO");
        holder.description.setText(album.getDescription());
        // loading album cover using Glide library
        Glide.with(mContext).load(album.getThumbnail()).into(holder.thumbnail);
    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }
}