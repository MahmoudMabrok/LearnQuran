package az.gulf.learnquran.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import az.gulf.learnquran.fragment.AboutAppFragment;
import az.gulf.learnquran.fragment.AboutAuthorFragment;

public class AboutPageAdapter extends FragmentStatePagerAdapter {
    private final int NUM_ITEMS = 2;

    public AboutPageAdapter(FragmentManager fm) {
        super(fm);
    }

    public int getCount() {
        return NUM_ITEMS;
    }

    public Fragment getItem(int position) {


        if (position == 0) {
            return AboutAppFragment.newInstance();
        }else if (position == 1) {
            return AboutAuthorFragment.newInstance();
        }
        return null;
    }
}
