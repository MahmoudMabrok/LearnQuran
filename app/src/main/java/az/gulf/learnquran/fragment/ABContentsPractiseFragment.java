package az.gulf.learnquran.fragment;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import az.gulf.learnquran.R;
import az.gulf.learnquran.adapter.ExtendedPagerAdapter;
import az.gulf.learnquran.domain.ViewPagerObject;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ABContentsPractiseFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ABContentsPractiseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ABContentsPractiseFragment extends Fragment {

   final List<ViewPagerObject> viewPagerItems = new ArrayList<>();

    int currentViewPagerIndex = 0;

    int currentSpinnerPosition = 0;

    boolean textIsVisible = true;


    ViewPager viewPager;

    Spinner spinner;

    TextView descriptionTextView;

    private static final String LOG_TAG = "ABContPractiseFragment";
    private static String mFileName = null;

    private RecordButton mRecordButton = null;
    private MediaRecorder mRecorder = null;

    private PlayButton   mPlayButton = null;
    private MediaPlayer mPlayer = null;

    {
        mFileName = Environment.getExternalStorageDirectory().getAbsolutePath();
        mFileName += "/practice_audio_record.3gp";
    }



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ABContentsPractiseFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ABContentsPractiseFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ABContentsPractiseFragment newInstance(String param1, String param2) {
        ABContentsPractiseFragment fragment = new ABContentsPractiseFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

      final  View rootView = inflater.inflate(R.layout.fragment_abcontents_practise, container, false);

        //******************************************************************************************

        Toolbar toolbar = (Toolbar) rootView.findViewById(R.id.ab_cont_practice_toolbar);

        toolbar.setTitle("Məşğələ");

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });


        //******************************************************************************************

        fillViewPagerItems();

        descriptionTextView = (TextView) rootView.findViewById(R.id.ab_cont_practice_text_alphabet);
        descriptionTextView.setText(viewPagerItems.get(0).getDescriptionText());





         viewPager = (ViewPager)rootView.findViewById(R.id.ab_cont_practice_viewPager);
        ExtendedPagerAdapter customPagerAdapter = new ExtendedPagerAdapter(getActivity(),viewPagerItems);
        viewPager.setAdapter(customPagerAdapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                System.out.println("onPageScrolled position: "+position+" positionOffset: "+positionOffset+" positionOffsetPixels: "+positionOffsetPixels );
            }

            @Override
            public void onPageSelected(int position) {

                currentViewPagerIndex = position;
                System.out.println("onPageSelected position: "+position);

                descriptionTextView.setText(viewPagerItems.get(currentViewPagerIndex).getDescriptionText());
                spinner.setSelection(currentViewPagerIndex);


                switch(currentViewPagerIndex){
                    case 6:
                    case 13:
                    case 14:
                    case 15:
                    case 20:
                        descriptionTextView.setPaintFlags(descriptionTextView.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);
                        default:
                        descriptionTextView.setPaintFlags(descriptionTextView.getPaintFlags() & (~ Paint.UNDERLINE_TEXT_FLAG));

                }


            }

            @Override
            public void onPageScrollStateChanged(int state) {
//                System.out.println("onPageScrollStateChanged state: "+state);
            }
        });

        //******************************************************************************************

        // Spinner element
          spinner = (Spinner) rootView.findViewById(R.id.ab_cont_practice_button_spinner);

        Drawable spinnerDrawable = spinner.getBackground().getConstantState().newDrawable();
        spinnerDrawable.setColorFilter(getColor(getContext(), R.color.colorPrimaryDark), PorterDuff.Mode.SRC_ATOP);

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
        {
            spinner.setBackground(spinnerDrawable);
        } else {
            spinner.setBackgroundDrawable(spinnerDrawable);
        }


        // Spinner click listener
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                // On selecting a spinner item
                String item = parent.getItemAtPosition(position).toString();

                currentSpinnerPosition = position;


                TextView selectedText = (TextView) parent.getChildAt(0);

                if (selectedText != null) {
                    selectedText.setText(selectedText.getText()+"/28");
                    selectedText.setTextColor(Color.RED);
//                  selectedText.setBackground(null);
                    selectedText.setBackgroundColor(getColor(getContext(),R.color.colorPrimary));
                }

                // Showing selected spinner item
//                Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();


                viewPager.setCurrentItem(currentSpinnerPosition);
                descriptionTextView.setText(viewPagerItems.get(currentViewPagerIndex).getDescriptionText());
                switch(currentViewPagerIndex){
                    case 6:
                    case 13:
                    case 14:
                    case 15:
                    case 20:
                        descriptionTextView.setPaintFlags(descriptionTextView.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);
                }



            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        // Spinner Drop down elements
        List<String> categories = new ArrayList<>();

        categories.add("1");
        categories.add("2");
        categories.add("3");
        categories.add("4");
        categories.add("5");
        categories.add("6");
        categories.add("7");
        categories.add("8");
        categories.add("9");
        categories.add("10");
        categories.add("11");
        categories.add("12");
        categories.add("13");
        categories.add("14");
        categories.add("15");
        categories.add("16");
        categories.add("17");
        categories.add("18");
        categories.add("19");
        categories.add("20");
        categories.add("21");
        categories.add("22");
        categories.add("23");
        categories.add("24");
        categories.add("25");
        categories.add("26");
        categories.add("27");
        categories.add("28");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(getActivity(), R.layout.spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(R.layout.spinner_item);

        // attaching data adapter to spinner

        spinner.setAdapter(dataAdapter);




//******************************************************************************************


        ImageButton clickButtonCirclePlay = (ImageButton) rootView.findViewById(R.id.ab_cont_practice_button_circle_play);
        clickButtonCirclePlay.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                ViewPagerObject vPObject =  viewPagerItems.get(currentViewPagerIndex);




                MediaPlayer mediaPlayer = MediaPlayer.create(container.getContext(), R.raw.alif);
                mediaPlayer.start(); // no need to call prepare(); create() does that for you







            }
        });

//******************************************************************************************

        ImageButton clickButtonAlphabet = (ImageButton) rootView.findViewById(R.id.ab_cont_practice_button_circle_alphabet);
        clickButtonAlphabet.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                if(textIsVisible) {

                    TextView textView = (TextView) rootView.findViewById(R.id.ab_cont_practice_text_alphabet);
                    textView.setVisibility(View.INVISIBLE);


                    textIsVisible = false;

                }else{

                    TextView textView = (TextView) rootView.findViewById(R.id.ab_cont_practice_text_alphabet);
                    textView.setVisibility(View.VISIBLE);

                    textIsVisible = true;

                }

            }
        });






//******************************************************************************************

        ImageButton clickButtonPlayRecord = (ImageButton) rootView.findViewById(R.id.ab_cont_practice_button_circle_play_record);
        clickButtonPlayRecord.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View v) {

startPlaying();

            }
        });


        //******************************************************************************************


        final ImageButton buttonRecord = (ImageButton) rootView.findViewById(R.id.ab_cont_practice_button_circle_record);

        View.OnTouchListener listener = new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction() & MotionEvent.ACTION_MASK) {

                    case MotionEvent.ACTION_DOWN:
                        v.setPressed(true);


                        // Start action ...

                        System.out.println("Pressedddd");



                        startRecording();

                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_OUTSIDE:
                    case MotionEvent.ACTION_CANCEL:
                        v.setPressed(false);

                        System.out.println("Unnn presseddd");

                        stopRecording();

                        // Stop action ...
                        break;
                    case MotionEvent.ACTION_POINTER_DOWN:
                        break;
                    case MotionEvent.ACTION_POINTER_UP:
                        break;
                    case MotionEvent.ACTION_MOVE:
                        break;
                }

                return true;
            }
        };


        buttonRecord.setOnTouchListener(listener);






        //******************************************************************************************


        ImageButton clickButtonNext= (ImageButton) rootView.findViewById(R.id.ab_cont_practice_button_next);
        clickButtonNext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(currentSpinnerPosition<27){
                    spinner.setSelection(++currentSpinnerPosition);
                }

            }
        });


        //******************************************************************************************



        ImageButton clickButtonPrevious= (ImageButton) rootView.findViewById(R.id.ab_cont_practice_button_previous);
        clickButtonPrevious.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(currentSpinnerPosition>0){
                    spinner.setSelection(--currentSpinnerPosition);
                }

            }
        });




        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

//**************************************************************************************************

    private void onRecord(boolean start) {
        if (start) {
            startRecording();
        } else {
            stopRecording();
        }
    }

    private void onPlay(boolean start) {
        if (start) {
            startPlaying();
        } else {
            stopPlaying();
        }
    }

    private void startPlaying() {
        mPlayer = new MediaPlayer();
        try {
            mPlayer.setDataSource(mFileName);
            mPlayer.prepare();
            mPlayer.start();
        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }
    }

    private void stopPlaying() {
        mPlayer.release();
        mPlayer = null;
    }

    private void startRecording() {
        mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mRecorder.setOutputFile(mFileName);
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        try {
            mRecorder.prepare();
        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }

        mRecorder.start();
    }

    private void stopRecording() {
        mRecorder.stop();
        mRecorder.release();
        mRecorder = null;
    }

    class RecordButton extends Button {
        boolean mStartRecording = true;

        OnClickListener clicker = new OnClickListener() {
            public void onClick(View v) {
                onRecord(mStartRecording);
                if (mStartRecording) {
                    setText("Stop recording");
                } else {
                    setText("Start recording");
                }
                mStartRecording = !mStartRecording;
            }
        };

        public RecordButton(Context ctx) {
            super(ctx);
            setText("Start recording");
            setOnClickListener(clicker);
        }
    }

    class PlayButton extends Button {
        boolean mStartPlaying = true;

        OnClickListener clicker = new OnClickListener() {
            public void onClick(View v) {
                onPlay(mStartPlaying);
                if (mStartPlaying) {
                    setText("Stop playing");
                } else {
                    setText("Start playing");
                }
                mStartPlaying = !mStartPlaying;
            }
        };

        public PlayButton(Context ctx) {
            super(ctx);
            setText("Start playing");
            setOnClickListener(clicker);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mRecorder != null) {
            mRecorder.release();
            mRecorder = null;
        }

        if (mPlayer != null) {
            mPlayer.release();
            mPlayer = null;
        }
    }



    public static final int getColor(Context context, int id) {
        final int version = Build.VERSION.SDK_INT;
        if (version >= 23) {
            return ContextCompat.getColor(context, id);
        } else {
            return context.getResources().getColor(id);
        }
    }

    private void fillViewPagerItems() {


        TypedArray alphabets = getResources().obtainTypedArray(R.array.alphabets);

        int index = 1;

        for (int i = 0; i < alphabets.length(); i += 2) {
            viewPagerItems.add(new ViewPagerObject(alphabets.getString(i), alphabets.getString(i + 1), index));

            index++;
        }

    }

}
