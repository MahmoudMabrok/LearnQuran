package az.gulf.learnquran.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import az.gulf.learnquran.R;
import az.gulf.learnquran.domain.Constants;
import az.gulf.learnquran.fragment.AboutFragment;
import az.gulf.learnquran.fragment.AlphabetFragment;
import az.gulf.learnquran.fragment.TajweedFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    SharedPreferences sharedpreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.app_main_toolbar);
        toolbar.inflateMenu(R.menu.main);//changed

        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ViewPager viewPager = (ViewPager) findViewById(R.id.app_main_viewpager);
        setupViewPager(viewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.app_main_tabs);
        tabLayout.setupWithViewPager(viewPager);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

//**************************************************************************************************


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        navigationView.getMenu().getItem(0).setChecked(true);

//***************************************************************************************************

        sharedpreferences = getSharedPreferences(Constants.LEAR_QURAN_PREFERENCES, Context.MODE_PRIVATE);
        String fontTextSize = sharedpreferences.getString(Constants.READ_TEXT_FONT_SIZE_PREF, null);

        if (fontTextSize == null) {
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putString(Constants.READ_TEXT_FONT_SIZE_PREF, Constants.READ_TEXT_FONT_SIZE_DEFAULT_VALUE);
            editor.apply();
        }

        int fontTextColor = sharedpreferences.getInt(Constants.READ_TEXT_COLOR_PREF, -1);

        if (fontTextColor == -1) {
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putInt(Constants.READ_TEXT_COLOR_PREF, Color.parseColor(Constants.READ_TEXT_COLOR_DEFAULT_VALUE));
            editor.apply();
        }

        int textBackgroundColor = sharedpreferences.getInt(Constants.READ_BACKGROUND_COLOR_PREF, -1);

        if (textBackgroundColor == -1) {
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putInt(Constants.READ_BACKGROUND_COLOR_PREF, Color.parseColor(Constants.READ_BACKGROUND_COLOR_DEFAULT_VALUE));
            editor.apply();
        }


//        System.out.println("main--- "+restoredText);


//***************************************************************************************************


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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        System.out.println("Main onCreateOptionsMenu");
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_move_to_added_mark) {
//            System.out.println(" maindeee " + sharedpreferences.getInt(Constants.MARKED_COURSE_ID, -1));
            switch (sharedpreferences.getInt(Constants.MARKED_COURSE_ID, -1)) {
                case 1:
                    break;
                case 2:
                    break;
                default:
                    break;
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_lessons) {
            System.out.println("nav_lessons");
            // Handle the camera action
        } else if (id == R.id.nav_settings) {
            System.out.println("nav_settings");
            Intent intent = new Intent();
            intent.setClassName(this, "az.gulf.learnquran.activity.SettingsActivity");
            startActivity(intent);
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            return false;
        } else if (id == R.id.nav_share) {
            System.out.println("nav_share");
            shareTextUrl();
        } else if (id == R.id.nav_about) {
            System.out.println("nav_about");
            AboutFragment mFragment = new AboutFragment();
            switchContent(R.id.drawer_layout, mFragment);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new AlphabetFragment(), this.getString(R.string.alphabet));
        adapter.addFragment(new TajweedFragment(), this.getString(R.string.tajweed));

        viewPager.setAdapter(adapter);
    }
    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
    public void switchContent(int id, Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(id, fragment, fragment.toString());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
    private void shareTextUrl() {

        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_SUBJECT, "Proqramı paylaş");
        sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
        sendIntent.setType("text/plain");
        startActivity(Intent.createChooser(sendIntent, "Proqramı paylaş"));

    }
}
