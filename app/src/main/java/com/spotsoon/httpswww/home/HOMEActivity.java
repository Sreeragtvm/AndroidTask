package com.spotsoon.httpswww.home;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import adapter.PagerAdapter;
import adapter.SlidingAdapter;
import me.relex.circleindicator.CircleIndicator;

/**
 * Created by Sreerag on 23-07-2017.
 */
public class HOMEActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private static ViewPager mPager;
    private static int currentPage = 0;
    private static final Integer[] SLIDE = {R.drawable.slide_1,R.drawable.slide_2,R.drawable.slide_3,R.drawable.slide_4,R.drawable.slide_5};
    private ArrayList<Integer> SLIDEArray = new ArrayList<Integer>();
    TabLayout tabLayout;
    private static final String[] title_slide ={"GREETINGS","DISCUSS","IDEA","TRANSLATE IDEA","DELIVER PRODUCT"};
    private ArrayList<String> TitleArray = new ArrayList<String>();
    private static final String[] description_slide ={"Greetings","Discuss","Idea","Translate Idea","Deliver Product"};
    private ArrayList<String> DescriptionArray = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        init();
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        final int[] ICONS = new int[]{
                R.drawable.video,
                R.drawable.image,
                R.drawable.milestone
        };
        final int[] ICONS_select = new int[]{
                R.drawable.select_video,
                R.drawable.select_image,
                R.drawable.select_milestone
        };
        tabLayout.addTab(tabLayout.newTab().setText(getResources().getString(R.string.video_tab_title)));
        tabLayout.addTab(tabLayout.newTab().setText(getResources().getString(R.string.image_tab_title)));
        tabLayout.addTab(tabLayout.newTab().setText(getResources().getString(R.string.milestone_tab_title)));
        tabLayout.getTabAt(0).setIcon(ICONS[0]);
        tabLayout.getTabAt(1).setIcon(ICONS[1]);
        tabLayout.getTabAt(2).setIcon(ICONS[2]);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setBackgroundColor(getResources().getColor(R.color.tab_unselected));
        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.getTabAt(viewPager.getCurrentItem()).setIcon(ICONS_select[viewPager.getCurrentItem()]);
        tabLayout.setTabTextColors(
                ContextCompat.getColor(HOMEActivity.this, R.color.album_title),
                ContextCompat.getColor(HOMEActivity.this, R.color.colorAccent)
        );
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                tabLayout.getTabAt(tab.getPosition()).setIcon(ICONS_select[tab.getPosition()]);
                tabLayout.setTabTextColors(
                        ContextCompat.getColor(HOMEActivity.this, R.color.album_title),
                        ContextCompat.getColor(HOMEActivity.this, R.color.colorAccent)
                );
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tabLayout.getTabAt(tab.getPosition()).setIcon(ICONS[tab.getPosition()]);
                tabLayout.setTabTextColors(
                        ContextCompat.getColor(HOMEActivity.this, R.color.album_title),
                        ContextCompat.getColor(HOMEActivity.this, R.color.colorAccent)
                );
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private void init() {
        for(int i=0;i<SLIDE.length;i++){
            SLIDEArray.add(SLIDE[i]);
            TitleArray.add(title_slide[i]);
            DescriptionArray.add(description_slide[i]);
        }
        mPager = (ViewPager) findViewById(R.id.pager_sliding);
        mPager.setAdapter(new SlidingAdapter(HOMEActivity.this,SLIDEArray,TitleArray,DescriptionArray));
        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator);
        indicator.setViewPager(mPager);

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == SLIDE.length) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 4500, 4500);
    }

}
