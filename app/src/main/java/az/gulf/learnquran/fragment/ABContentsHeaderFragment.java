package az.gulf.learnquran.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;


import az.gulf.learnquran.R;
import az.gulf.learnquran.activity.MainActivity;
import az.gulf.learnquran.adapter.BodyExpandableRecyclerAdapter;
import az.gulf.learnquran.domain.Constants;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ABContentsHeaderFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ABContentsHeaderFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ABContentsHeaderFragment extends Fragment {

    SharedPreferences sharedpreferences;


    @Bind(R.id.ab_cont_head_recycler_view)
    RecyclerView recycler;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ABContentsHeaderFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ABContentsHeaderFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ABContentsHeaderFragment newInstance(String param1, String param2) {
        ABContentsHeaderFragment fragment = new ABContentsHeaderFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setHasOptionsMenu(true);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.fragment_abcontents_header, container, false);

        //*******************************************************************************************

        Toolbar toolbar = (Toolbar) rootView.findViewById(R.id.ab_cont_head_toolbar);
        toolbar.inflateMenu(R.menu.sub_main);//changed

        toolbar.setTitle("Qurani-Kərim Əlifbası");

        toolbar.setNavigationIcon(getColoredArrow(R.drawable.ic_arrow_back_24dp));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        sharedpreferences = getContext().getSharedPreferences(Constants.LEAR_QURAN_PREFERENCES, Context.MODE_PRIVATE);

        //toolbar2 menu items CallBack listener
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem arg0) {
                if (arg0.getItemId() == R.id.action_save_page_index) {

//                    System.out.println(" fragmentdeee " + arg0);


                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putInt(Constants.MARKED_COURSE_ID, 2);
                    editor.apply();

                }
                return false;
            }
        });

        //*******************************************************************************************

        //*******************************************************************************************

//        // use this setting to improve performance if you know that changes
//        // in content do not change the layout size of the RecyclerView
//        RecyclerView mRecyclerView = (RecyclerView) rootView.findViewById(R.id.ab_cont_head_recycler_view);
//        mRecyclerView.setHasFixedSize(true);
//
//        // use a linear layout manager
//        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
//        mRecyclerView.setLayoutManager(mLayoutManager);
//
//
//        // specify an adapter (see also next example)
//        RecyclerView.Adapter mAdapter = new AlphabetAdapter(prepareFragmentDataSet());
//        mRecyclerView.setAdapter(mAdapter);
        //*******************************************************************************************


        ButterKnife.bind(this, rootView);

        BodyExpandableRecyclerAdapter adapter = new BodyExpandableRecyclerAdapter(getActivity());
        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycler.setAdapter(adapter);

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


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


}
