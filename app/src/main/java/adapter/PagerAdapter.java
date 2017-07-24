package adapter;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.spotsoon.httpswww.home.ImageFragment;
import com.spotsoon.httpswww.home.MilestoneFragment;
import com.spotsoon.httpswww.home.VideoFragment;

/**
 * Created by IMR on 22-07-2017.
 */

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                VideoFragment tab1 = new VideoFragment();
                return tab1;
            case 1:
                ImageFragment tab2 = new ImageFragment();
                return tab2;
            case 2:
                MilestoneFragment tab3 = new MilestoneFragment();
                return tab3;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}