package az.gulf.learnquran.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;

import com.emilsjolander.components.StickyScrollViewItems.StickyScrollView;

import java.util.ArrayList;

import az.gulf.learnquran.R;
import az.gulf.learnquran.adapter.ABContTheoryGridViewAdapter;
import az.gulf.learnquran.components.MyGridView;
import az.gulf.learnquran.domain.Constants;
import az.gulf.learnquran.item.GridViewItem;
import az.gulf.learnquran.util.CustomUtil;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ABContentsHeaderTheoryFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ABContentsHeaderTheoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ABContentsHeaderTheoryFragment extends Fragment {

    SharedPreferences sharedpreferences;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private MyGridView gridView;
    private ABContTheoryGridViewAdapter gridAdapter;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ABContentsHeaderTheoryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ABContentsHeaderTheoryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ABContentsHeaderTheoryFragment newInstance(String param1, String param2) {
        ABContentsHeaderTheoryFragment fragment = new ABContentsHeaderTheoryFragment();
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

        View rootView = inflater.inflate(R.layout.fragment_abcontents_header_theory, container, false);


        Toolbar toolbar = (Toolbar) rootView.findViewById(R.id.ab_cont_head_theory_toolbar);

        toolbar.setTitle(R.string.theory);

        toolbar.setNavigationIcon(getColoredArrow(R.drawable.ic_arrow_back_24dp));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });


        gridView = (MyGridView) rootView.findViewById(R.id.ab_cont_head_theory_grid_view);
        gridAdapter = new ABContTheoryGridViewAdapter(container.getContext(), R.layout.gw_abcontents_header_theory_item, getData());
        gridView.setAdapter(gridAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

//                System.out.println(position);

                playSoundForPosition(position, container);

            }
        });



        sharedpreferences = getContext().getSharedPreferences(Constants.LEAR_QURAN_PREFERENCES, Context.MODE_PRIVATE);

        setColorsAndFontsOfFragment(rootView);

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
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


    private Drawable getColoredArrow(int drawableId) {
        Drawable arrowDrawable;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            arrowDrawable = getResources().getDrawable(drawableId, null);
        } else {
            arrowDrawable = getResources().getDrawable(drawableId);
        }

        Drawable wrapped = DrawableCompat.wrap(arrowDrawable);

        if (arrowDrawable != null && wrapped != null) {
            // This should avoid tinting all the arrows
            arrowDrawable.mutate();
            DrawableCompat.setTint(wrapped, Color.WHITE);
        }

        return wrapped;
    }

    private ArrayList<GridViewItem> getData() {
        final ArrayList<GridViewItem> imageItems = new ArrayList<>();

        TypedArray alphabets = getResources().obtainTypedArray(R.array.alphabets);


        for (int i = 0; i < alphabets.length(); i += 2) {
            imageItems.add(new GridViewItem(alphabets.getString(i), alphabets.getString(i + 1)));
        }
        return imageItems;
    }

    private void setColorsAndFontsOfFragment(View innerView) {

        int textBackgroundColor = sharedpreferences.getInt(Constants.READ_BACKGROUND_COLOR_PREF, -1);

        innerView.setBackgroundColor(textBackgroundColor);








    }

    private void playSoundForPosition(int position, ViewGroup container) {
        switch (position) {


            default:
                MediaPlayer mediaPlayer = MediaPlayer.create(container.getContext(), R.raw.alif);
                mediaPlayer.start(); // no need to call prepare(); create() does that for you

                break;
        }

    }


}
