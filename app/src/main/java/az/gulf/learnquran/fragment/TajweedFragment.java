package az.gulf.learnquran.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import az.gulf.learnquran.R;
import az.gulf.learnquran.adapter.TajweedAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TajweedFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TajweedFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TajweedFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public TajweedFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TajweedFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TajweedFragment newInstance(String param1, String param2) {
        TajweedFragment fragment = new TajweedFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_tajweed, container, false);

        RecyclerView mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view_main_tajweed);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);




        // specify an adapter (see also next example)
        RecyclerView.Adapter mAdapter = new TajweedAdapter(prepareFragmentDataSet());
        mRecyclerView.setAdapter(mAdapter);




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



    public ArrayList<String> prepareFragmentDataSet(){

        ArrayList<String> myDataset = new ArrayList<>();
        myDataset.add("01. Giriş");
        myDataset.add("02. Qurani-Kərimin oxunmasının fəziləti");
        myDataset.add("03. Qurani-Kərimin adları və vəsfləri");
        myDataset.add("04. Quranın nazil olması");
        myDataset.add("05. Qiraətlər");
        myDataset.add("06. Qurani-Kərimin toplanması");
        myDataset.add("07. İsnadlar");
        myDataset.add("08. Quran oxumağın ədəbləri");
        myDataset.add("09. Qiraətin mərtəbələri");
        myDataset.add("10. Quran oxunan zaman buraxılan səhvlər");
        myDataset.add("11. Bəsmələ");
        myDataset.add("12. İstiazə");
        myDataset.add("13. Səcdə ayələri");
        myDataset.add("14. Təcvid elmi");
        myDataset.add("15. Hərflərin məxrəcləri");
        myDataset.add("16. Hərflərin sifətləri");
        myDataset.add("17. Sukunlu nunun və tənvinlərin hökmləri");
        myDataset.add("18. İzhar");
        myDataset.add("19. İdğam");
        myDataset.add("20. İqləb");
        myDataset.add("21. İxfə");
        myDataset.add("22. Sukunlu mimin hökmləri");
        myDataset.add("23. İxfə-şəfəvi");
        myDataset.add("24. İdğam-şəfəvi");
        myDataset.add("25. İzhar-şəfəvi");
        myDataset.add("26. Şədddəli nun və şəddəli mim");
        myDataset.add("27. İdğam misleyn");
        myDataset.add("28. İdğam mutəcəniseyn");
        myDataset.add("29. İdğam mutəqaribeyn");
        myDataset.add("30. Ləfzətullah");
        myDataset.add("31. Qalqalə");
        myDataset.add("32. İdğam şəmsiyyə və izhar qəməriyyə");
        myDataset.add("33. Ra ( ر) hərfinin hökmləri");
        myDataset.add("34. Mədd");
        myDataset.add("35. Əsli mədd");
        myDataset.add("36. Təbii Mədd");
        myDataset.add("37. Məddul ivad");
        myDataset.add("38. Məddul bədəl");
        myDataset.add("39. Fəri mədd");
        myDataset.add("40. Məddul muttəsıl");
        myDataset.add("41. Məddul munfəsıl");
        myDataset.add("42. Məddul aridu lissukun");
        myDataset.add("43. Məddul ləzim");
        myDataset.add("44. Məddul lin");
        myDataset.add("45. Məddus sılə");
        myDataset.add("46. Vəsləli həmzə");
        myDataset.add("47. Səktə");
        myDataset.add("48. Vəqflər");
        myDataset.add("49. İstisnalar");
        myDataset.add("50. Qurani-Kərimdə olan bəzi terminlər");


        return myDataset;

    }


}
