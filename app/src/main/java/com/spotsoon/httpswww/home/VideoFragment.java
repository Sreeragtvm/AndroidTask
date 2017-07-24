package com.spotsoon.httpswww.home;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import adapter.ItemListAdapter;

/**
 * Created by IMR on 22-07-2017.
 */

public class VideoFragment extends Fragment {
    private RecyclerView recyclerView;
    private ItemListAdapter adapter_item;
    private List<ItemListActivity> albumList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.video_fragment, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        albumList = new ArrayList<>();
        adapter_item = new ItemListAdapter(getActivity(), albumList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new VideoFragment.GridSpacingItemDecoration(1, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter_item);

        prepareAlbums();

//        try {
//            Glide.with(this).load(R.drawable.cover).into((ImageView) findViewById(R.id.backdrop));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        return view;
    }
    /**
     * Adding few albums for testing
     */
    private void prepareAlbums() {
        int[] covers = new int[]{
                R.drawable.album1,
                R.drawable.album2,
                R.drawable.album3,
                R.drawable.album4,
                R.drawable.album5,
                R.drawable.album6,
                R.drawable.album7,
                R.drawable.album8,
                R.drawable.album9,
                R.drawable.album10,
                R.drawable.album11};

        ItemListActivity a = new ItemListActivity("True Romance", 13, covers[0],getResources().getString(R.string.song_description));
        albumList.add(a);

        a = new ItemListActivity("Xscpae", 8, covers[1],getResources().getString(R.string.song_description));
        albumList.add(a);

        a = new ItemListActivity("Maroon 5", 11, covers[2],getResources().getString(R.string.song_description));
        albumList.add(a);

        a = new ItemListActivity("Born to Die", 12, covers[3],getResources().getString(R.string.song_description));
        albumList.add(a);

        a = new ItemListActivity("Honeymoon", 14, covers[4],getResources().getString(R.string.song_description));
        albumList.add(a);

        a = new ItemListActivity("I Need a Doctor", 1, covers[5],getResources().getString(R.string.song_description));
        albumList.add(a);

        a = new ItemListActivity("Loud", 11, covers[6],getResources().getString(R.string.song_description));
        albumList.add(a);

        a = new ItemListActivity("Legend", 14, covers[7],getResources().getString(R.string.song_description));
        albumList.add(a);

        a = new ItemListActivity("Hello", 11, covers[8],getResources().getString(R.string.song_description));
        albumList.add(a);

        a = new ItemListActivity("Greatest Hits", 17, covers[9],getResources().getString(R.string.song_description));
        albumList.add(a);

        adapter_item.notifyDataSetChanged();
    }
    /**
     * RecyclerView item decoration - give equal margin around grid item
     */
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}