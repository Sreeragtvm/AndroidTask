package adapter;

/**
 * Created by Sreerag on 23-07-2017.
 */

import android.content.Context;
        import android.support.v4.view.PagerAdapter;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageView;
import android.widget.TextView;

import com.spotsoon.httpswww.home.R;

import java.util.ArrayList;

public class SlidingAdapter extends PagerAdapter {

    private ArrayList<Integer> images;
    private LayoutInflater inflater;
    private Context context;
    private ArrayList<String> title;
    private ArrayList<String> description;

    public SlidingAdapter(Context context, ArrayList<Integer> images,ArrayList<String> title,ArrayList<String> description) {
        this.context = context;
        this.images=images;
        this.title=title;
        this.description = description;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public Object instantiateItem(ViewGroup view, int position) {
        View myImageLayout = inflater.inflate(R.layout.sliding_view, view, false);
        ImageView myImage = (ImageView) myImageLayout
                .findViewById(R.id.image);
        TextView title_txt = (TextView) myImageLayout.findViewById(R.id.slider_title);
        TextView description_txt = (TextView) myImageLayout.findViewById(R.id.slider_description);
        myImage.setImageResource(images.get(position));
        title_txt.setText(title.get(position));
        description_txt.setText(description.get(position));
        view.addView(myImageLayout, 0);
        return myImageLayout;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }
}