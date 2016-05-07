package az.gulf.learnquran.activity;


import android.annotation.TargetApi;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.RingtonePreference;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;


import com.rarepebble.colorpicker.ColorPreference;

import az.gulf.learnquran.R;
import az.gulf.learnquran.domain.Constants;

import java.util.List;

/**
 * A {@link PreferenceActivity} that presents a set of application settings. On
 * handset devices, settings are presented as a single list. On tablets,
 * settings are split by category, with category headers shown to the left of
 * the list of settings.
 * <p/>
 * See <a href="http://developer.android.com/design/patterns/settings.html">
 * Android Design: Settings</a> for design guidelines and the <a
 * href="http://developer.android.com/guide/topics/ui/settings.html">Settings
 * API Guide</a> for more information on developing a Settings UI.
 */
public class SettingsActivity extends AppCompatPreferenceActivity {

    static SharedPreferences sharedpreferences;


    /**
     * A preference value change listener that updates the preference's summary
     * to reflect its new value.
     */
    private static Preference.OnPreferenceChangeListener sBindPreferenceSummaryToValueListener = new Preference.OnPreferenceChangeListener() {
        @Override
        public boolean onPreferenceChange(Preference preference, Object value) {
            String stringValue = value.toString();

//            System.out.println(stringValue+" 0000 "+preference.getKey());


            if (preference instanceof ListPreference) {
//**************************************************************************************************
                switch (preference.getKey()) {

                    case "font_size_list":
                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        editor.putString(Constants.READ_TEXT_FONT_SIZE_PREF, stringValue);
                        editor.apply();
                        break;

                }

//--------------------------------------------------------------------------------------------------
                ListPreference listPreference = (ListPreference) preference;
                int index = listPreference.findIndexOfValue(stringValue);

                // Set the summary to reflect the new value.
                preference.setSummary(
                        index >= 0
                                ? listPreference.getEntries()[index]
                                : null);
//**************************************************************************************************
            } else {
//**************************************************************************************************

                switch (preference.getKey()) {

                    case "1":



                        break;

                }









                preference.setSummary(stringValue);
//**************************************************************************************************
            }
            return true;
        }
    };

    /**
     * Helper method to determine if the device has an extra-large screen. For
     * example, 10" tablets are extra-large.
     */
    private static boolean isXLargeTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_XLARGE;
    }

    /**
     * Binds a preference's summary to its value. More specifically, when the
     * preference's value is changed, its summary (line of text below the
     * preference title) is updated to reflect the value. The summary is also
     * immediately updated upon calling this method. The exact display format is
     * dependent on the type of preference.
     *
     * @see #sBindPreferenceSummaryToValueListener
     */
    private static void bindPreferenceSummaryToValue(Preference preference) {


        // Set the listener to watch for value changes.
        preference.setOnPreferenceChangeListener(sBindPreferenceSummaryToValueListener);

        // Trigger the listener immediately with the preference's
        // current value.
        sBindPreferenceSummaryToValueListener.onPreferenceChange(preference,
                PreferenceManager
                        .getDefaultSharedPreferences(preference.getContext())
                        .getString(preference.getKey(), ""));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setupActionBar();

        sharedpreferences = getSharedPreferences(Constants.LEAR_QURAN_PREFERENCES, Context.MODE_PRIVATE);

        getFragmentManager().beginTransaction().replace(android.R.id.content, new GeneralPreferenceFragment()).commit();
    }

    /**
     * Set up the {@link android.app.ActionBar}, if the API is available.
     */
    private void setupActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            // Show the Up button in the action bar.
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean onIsMultiPane() {
        return isXLargeTablet(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void onBuildHeaders(List<Header> target) {
//        loadHeadersFromResource(R.xml.pref_headers, target);
//        setContentView(R.layout.settings_layout);
//
//        Toolbar toolbar = (Toolbar) findViewById(R.id.settings_head_toolbar);
//        setSupportActionBar(toolbar);
//
//        ActionBar bar = getSupportActionBar();
//        bar.setHomeButtonEnabled(true);
//        bar.setDisplayHomeAsUpEnabled(true);
//        bar.setDisplayShowTitleEnabled(true);
//        bar.setTitle("Parametrlər");


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * This method stops fragment injection in malicious applications.
     * Make sure to deny any unknown fragments here.
     */
    protected boolean isValidFragment(String fragmentName) {
        return PreferenceFragment.class.getName().equals(fragmentName)
                || GeneralPreferenceFragment.class.getName().equals(fragmentName)
                || DataSyncPreferenceFragment.class.getName().equals(fragmentName)
                || NotificationPreferenceFragment.class.getName().equals(fragmentName);
    }


    //*************************************************************************************************


    /**
     * This fragment shows general preferences only. It is used when the
     * activity is showing a two-pane settings UI.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static class GeneralPreferenceFragment extends PreferenceFragment
            implements NavigationView.OnNavigationItemSelectedListener {

        View layout;


        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.pref_general);


            setHasOptionsMenu(true);


            // Bind the summaries of EditText/List/Dialog/Ringtone preferences
            // to their values. When their values change, their summaries are
            // updated to reflect the new value, per the Android Design
            // guidelines.

//            bindPreferenceSummaryToValue(findPreference("example_text"));
//            bindPreferenceSummaryToValue(findPreference("reset_text_appearance_key"));


            Preference readFontSizePref = findPreference("font_size_list");
            ((ListPreference) readFontSizePref).setValue(
                    sharedpreferences.getString(Constants.READ_TEXT_FONT_SIZE_PREF, Constants.READ_TEXT_COLOR_DEFAULT_VALUE));
            bindPreferenceSummaryToValue(readFontSizePref);


        }


        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            int id = item.getItemId();

            if (id == android.R.id.home) {
                startActivity(new Intent(getActivity(), SettingsActivity.class));
                return true;
            }
            return super.onOptionsItemSelected(item);
        }


        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

            layout = inflater.inflate(R.layout.settings_layout, container, false);

            if (layout != null) {
                AppCompatPreferenceActivity activity = (AppCompatPreferenceActivity) getActivity();
                Toolbar toolbar = (Toolbar) layout.findViewById(R.id.settings_head_toolbar);
                activity.setSupportActionBar(toolbar);

                ActionBar bar = activity.getSupportActionBar();
                bar.setHomeButtonEnabled(true);
                bar.setDisplayHomeAsUpEnabled(true);
                bar.setDisplayShowTitleEnabled(true);
                bar.setHomeAsUpIndicator(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
                bar.setTitle(R.string.name_settings);


                DrawerLayout drawer = (DrawerLayout) layout.findViewById(R.id.settings_drawer_layout);
                ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                        getActivity(), drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
                drawer.setDrawerListener(toggle);
                toggle.syncState();

                NavigationView navigationView = (NavigationView) layout.findViewById(R.id.settings_nav_view);
                navigationView.setNavigationItemSelectedListener(this);

                navigationView.getMenu().getItem(1).setChecked(true);




               //**********************************************************************************

              final Preference fontColorPref = findPreference("font_color_pref");

                ((ColorPreference) fontColorPref).setColor(
                        sharedpreferences.getInt(Constants.READ_TEXT_COLOR_PREF, Color.parseColor(Constants.READ_TEXT_COLOR_DEFAULT_VALUE)));

                fontColorPref.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                    @Override
                    public boolean onPreferenceChange(Preference preference, Object newValue) {
//                        System.out.println("font_color_pref : " + newValue);

                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        editor.putInt(Constants.READ_TEXT_COLOR_PREF, (Integer)newValue);
                        editor.apply();


                        return true;
                    }
                });

                //-----------------------------------------------------------------------------------



                //**********************************************************************************


                Preference backgroundFontColorPref = findPreference("background_color_pref");

                ((ColorPreference) backgroundFontColorPref).setColor(
                        sharedpreferences.getInt(Constants.READ_BACKGROUND_COLOR_PREF, Color.parseColor(Constants.READ_BACKGROUND_COLOR_DEFAULT_VALUE)));


                backgroundFontColorPref.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                    @Override
                    public boolean onPreferenceChange(Preference preference, Object newValue) {

//                        System.out.println("background_color_pref : " + newValue);

                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        editor.putInt(Constants.READ_BACKGROUND_COLOR_PREF, (Integer)newValue);
                        editor.apply();


                        return true;
                    }
                });
                //**********************************************************************************

             final Preference resetTextAppearanceKey = findPreference("reset_text_appearance_key");



                resetTextAppearanceKey.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                    @Override
                    public boolean onPreferenceClick(Preference preference) {

                        System.out.println("resetTextAppearanceKey : ");

//                        SharedPreferences.Editor editor = sharedpreferences.edit();
//                        editor.putInt(Constants.READ_BACKGROUND_COLOR_PREF, (Integer)newValue);
//                        editor.apply();


                        new AlertDialog.Builder(resetTextAppearanceKey.getContext())
                                .setTitle(R.string.reset_text_appearance)
                                .setMessage(R.string.reset_text_appearance_ask)
                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {


                                            SharedPreferences.Editor editor = sharedpreferences.edit();

                                            editor.putString(Constants.READ_TEXT_FONT_SIZE_PREF, Constants.READ_TEXT_FONT_SIZE_DEFAULT_VALUE);
                                            editor.putInt(Constants.READ_TEXT_COLOR_PREF, Color.parseColor(Constants.READ_TEXT_COLOR_DEFAULT_VALUE));
                                            editor.putInt(Constants.READ_BACKGROUND_COLOR_PREF, Color.parseColor(Constants.READ_BACKGROUND_COLOR_DEFAULT_VALUE));

                                            editor.apply();


                                       Preference innerFontSizePreference = findPreference("font_size_list");

                                        ((ListPreference) innerFontSizePreference).setValue(
                                                sharedpreferences.getString(Constants.READ_TEXT_FONT_SIZE_PREF, Constants.READ_TEXT_FONT_SIZE_DEFAULT_VALUE));

                                        bindPreferenceSummaryToValue(innerFontSizePreference);


                                        ((ColorPreference) findPreference("font_color_pref")).setColor(
                                                sharedpreferences.getInt(Constants.READ_TEXT_COLOR_PREF, Color.parseColor(Constants.READ_TEXT_COLOR_DEFAULT_VALUE)));



                                        ((ColorPreference) findPreference("background_color_pref")).setColor(
                                                sharedpreferences.getInt(Constants.READ_BACKGROUND_COLOR_PREF, Color.parseColor(Constants.READ_BACKGROUND_COLOR_DEFAULT_VALUE)));






                                    }
                                })
                                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        // do nothing

                                    }
                                })
                                .setIcon(android.R.drawable.ic_dialog_alert)
                                .show();








                        return true;
                    }
                });




                //**********************************************************************************









//-----------------------------------------------------------------------------------------------------------


            }


            return layout;
        }


        @SuppressWarnings("StatementWithEmptyBody")
        @Override
        public boolean onNavigationItemSelected(MenuItem item) {
            // Handle navigation view item clicks here.
            int id = item.getItemId();

            if (id == R.id.nav_lessons) {

                System.out.println("nav_lessons");


                Intent intent = new Intent();
                intent.setClassName(getActivity(), "az.gulf.learnquran.activity.MainActivity");
                startActivity(intent);


                // Handle the camera action
            } else if (id == R.id.nav_settings) {

                System.out.println("nav_settings");

            } else if (id == R.id.nav_share) {

                System.out.println("nav_share");


            } else if (id == R.id.nav_about) {

                System.out.println("nav_about");
            }

            DrawerLayout drawer = (DrawerLayout) layout.findViewById(R.id.settings_drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            return true;
        }

    }

    //*************************************************************************************************

    /**
     * This fragment shows notification preferences only. It is used when the
     * activity is showing a two-pane settings UI.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static class NotificationPreferenceFragment extends PreferenceFragment {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.pref_notification);
            setHasOptionsMenu(true);

            // Bind the summaries of EditText/List/Dialog/Ringtone preferences
            // to their values. When their values change, their summaries are
            // updated to reflect the new value, per the Android Design
            // guidelines.
            bindPreferenceSummaryToValue(findPreference("notifications_new_message_ringtone"));
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            int id = item.getItemId();
            if (id == android.R.id.home) {
                startActivity(new Intent(getActivity(), SettingsActivity.class));
                return true;
            }
            return super.onOptionsItemSelected(item);
        }
    }

    /**
     * This fragment shows data and sync preferences only. It is used when the
     * activity is showing a two-pane settings UI.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static class DataSyncPreferenceFragment extends PreferenceFragment {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.pref_data_sync);
            setHasOptionsMenu(true);

            // Bind the summaries of EditText/List/Dialog/Ringtone preferences
            // to their values. When their values change, their summaries are
            // updated to reflect the new value, per the Android Design
            // guidelines.
            bindPreferenceSummaryToValue(findPreference("sync_frequency"));
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            int id = item.getItemId();
            if (id == android.R.id.home) {
                startActivity(new Intent(getActivity(), SettingsActivity.class));
                return true;
            }
            return super.onOptionsItemSelected(item);
        }
    }


    //************************************************************************************************





}
