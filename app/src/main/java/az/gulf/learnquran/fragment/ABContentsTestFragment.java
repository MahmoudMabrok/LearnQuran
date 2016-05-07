package az.gulf.learnquran.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import az.gulf.learnquran.R;
import az.gulf.learnquran.adapter.TestGridViewAdapter;
import az.gulf.learnquran.domain.Constants;
import az.gulf.learnquran.domain.TestObject;
import az.gulf.learnquran.domain.ViewPagerObject;
import az.gulf.learnquran.item.GridViewTestItem;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ABContentsTestFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ABContentsTestFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ABContentsTestFragment extends Fragment {

    private GridView gridView;
    private TestGridViewAdapter gridAdapter;
    int gridViewTestStage = 0;
    int rightAnswers = 0;

    private Context context;

    private List<TestObject> testObjectContent = new ArrayList<>();
    private ArrayList<GridViewTestItem> currentImageItems = new ArrayList<>();


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ABContentsTestFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ABContentsTestFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ABContentsTestFragment newInstance(String param1, String param2) {
        ABContentsTestFragment fragment = new ABContentsTestFragment();
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


        final View rootView = inflater.inflate(R.layout.fragment_abcontents_test, container, false);

        context = container.getContext();

        //******************************************************************************************

        Toolbar toolbar = (Toolbar) rootView.findViewById(R.id.ab_cont_test_toolbar);

        toolbar.setTitle("Alphabet Test section");

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                new AlertDialog.Builder(context)
                        .setTitle("Geri dön")
                        .setMessage("Testi bititib geri dönmək istədiyinizə əminsiniz?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                getActivity().onBackPressed();
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();


            }
        });


//*************************************************************************************************

        fillAllData();

//*************************************************************************************************


        gridView = (GridView) rootView.findViewById(R.id.ab_cont_test_grid_view);

        gridAdapter = new TestGridViewAdapter(container.getContext(),
                R.layout.gw_simple_text_item, getData(0));

        gridView.setAdapter(gridAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {


                if (gridViewTestStage < 27) {

                    View row = parent.getChildAt(position);
                    TextView innerAnswerTextView = (TextView) row.findViewById(R.id.ab_test_grid_view_item_text_answer);

                    if (innerAnswerTextView.getText().equals("1")) {
                        rightAnswers++;
                    }


                    System.out.println("rightAnswers: " + rightAnswers);


                    gridViewTestStage++;

                    ((TestGridViewAdapter) gridView.getAdapter()).
                            processClickItemResult(currentImageItems,
                                    getData(gridViewTestStage),
                                    testObjectContent.get(gridViewTestStage).soundId, context);


                    TextView stageTextView = (TextView) rootView.findViewById(R.id.ab_cont_test_stage_text);
                    stageTextView.setText(String.valueOf(gridViewTestStage + 1) + "/28");


                }else{

                    replaceFragment();
                }

            }
        });


        //------------------------------------------------------------------------------------------


//**************************************************************************************************
        Button skipButton = (Button) rootView.findViewById(R.id.ab_cont_test_button_skip);
        skipButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                if (gridViewTestStage < 1) {

                    gridViewTestStage++;

                    ((TestGridViewAdapter) gridView.getAdapter()).
                            processClickItemResult(currentImageItems,
                                    getData(gridViewTestStage),
                                    testObjectContent.get(gridViewTestStage).soundId, context);

                    TextView stageTextView = (TextView) rootView.findViewById(R.id.ab_cont_test_stage_text);
                    stageTextView.setText(String.valueOf(gridViewTestStage + 1) + "/28");

                }else{

                    replaceFragment();

                }


            }
        });


//**************************************************************************************************

        ImageButton buttonTestPlay = (ImageButton) rootView.findViewById(R.id.ab_cont_test_button_play);
        buttonTestPlay.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                MediaPlayer mediaPlayer = MediaPlayer.create(container.getContext(), testObjectContent.get(gridViewTestStage).soundId);
                mediaPlayer.start(); // no need to call prepare(); create() does that for you


            }
        });


        return rootView;
    }


    @Override
    public void onStart() {
        super.onStart();

//        System.out.println("okkkkk");

        MediaPlayer mediaPlayer = MediaPlayer.create(context, R.raw.alif);
        mediaPlayer.start(); // no need to call prepare(); create() does that for you

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

    private ArrayList<GridViewTestItem> getData(int index) {

        final ArrayList<GridViewTestItem> imageItems = new ArrayList<>();
        final ArrayList<Integer> selectedValues = new ArrayList<>();

        Random rand = new Random();
        int random = rand.nextInt(4);

        System.out.println(" getData random : " + random);

        for (int i = 0; i < 4; i++) {

            if (i == random) {
                imageItems.add(new GridViewTestItem(testObjectContent.get(index).getCorrectValue(), R.drawable.ic_done_black_24dp, "1"));

            } else {
                int randSelectedValue = generateRandom(0, 27, selectedValues);

                System.out.println(" randSelectedValue : " + randSelectedValue);

                imageItems.add(new GridViewTestItem(testObjectContent.get(randSelectedValue).getCorrectValue(), R.drawable.ic_incorrect_black_24dp, "0"));
                selectedValues.add(randSelectedValue);
            }

        }


        currentImageItems = imageItems;

        System.out.println("Right answer: " + testObjectContent.get(index).getCorrectValue());

//        switch (index) {
//            case 0:
//                imageItems.add(new GridViewTestItem("a", R.drawable.ic_incorrect_black_24dp, "0"));
//                imageItems.add(new GridViewTestItem("b", R.drawable.ic_incorrect_black_24dp, "0"));
//                imageItems.add(new GridViewTestItem("n", R.drawable.ic_incorrect_black_24dp, "0"));
//                imageItems.add(new GridViewTestItem("h", R.drawable.ic_done_black_24dp, "1"));
//                break;
//            default:
//                imageItems.add(new GridViewTestItem("g", R.drawable.ic_incorrect_black_24dp, "0"));
//                imageItems.add(new GridViewTestItem("h", R.drawable.ic_incorrect_black_24dp, "1"));
//                imageItems.add(new GridViewTestItem("j", R.drawable.ic_incorrect_black_24dp, "0"));
//                imageItems.add(new GridViewTestItem("k", R.drawable.ic_done_black_24dp, "0"));
//        }

        return imageItems;
    }

    public int getSoundForTestByIndex(int index) {

        int soundId = R.raw.alif;

        switch (index) {

            case 0:
                soundId = R.raw.alif;
                break;
            case 1:
                soundId = R.raw.alif;
                break;
            case 2:
                soundId = R.raw.alif;
                break;
            case 3:
                soundId = R.raw.alif;
                break;

            default:


        }
        return soundId;

    }

    private void fillAllData() {

        TypedArray alphabets = getResources().obtainTypedArray(R.array.alphabets);
        int index = 0;
        for (int i = 0; i < alphabets.length(); i += 2) {
            testObjectContent.add(new TestObject(index, alphabets.getString(i), getSoundForTestByIndex(index)));
            index++;
        }


        Collections.shuffle(testObjectContent);

//        testObjectContent.

    }

    public int generateRandom(int start, int end, ArrayList<Integer> excludeRows) {
        Random rand = new Random();
        int range = end - start + 1;

        int random = rand.nextInt(range);
        while (excludeRows.contains(random)) {
            random = rand.nextInt(range);
        }

        return random;
    }

    public void replaceFragment(){

        Fragment newFragment = new TestResultFragment();
        Bundle args = new Bundle();

        args.putString(Constants.TEST_LESSON_NAME,"Qurani-Kərim Əlifbası");
        args.putInt(Constants.TEST_CORRECT_ANSWERS,rightAnswers);
        args.putInt(Constants.TEST_INCORRECT_ANSWERS,28-rightAnswers);

        newFragment.setArguments(args);

        FragmentTransaction transaction = getFragmentManager().beginTransaction();


        transaction.replace(R.id.ab_cont_test_main_layout, newFragment);
//        transaction.addToBackStack(null);

        transaction.commit();



    }

}
