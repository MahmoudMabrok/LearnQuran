package az.gulf.learnquran.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import az.gulf.learnquran.R;
import az.gulf.learnquran.activity.MainActivity;
import az.gulf.learnquran.fragment.ABContentsHeaderFragment;
import az.gulf.learnquran.fragment.ABIntroductionFragment;

/**
 * Created by www.ucuz.az on 12.01.2016.
 */
public class AlphabetAdapter extends RecyclerView.Adapter<AlphabetAdapter.ViewHolder> {
    private ArrayList<String> mDataset;
    private Context mainContext;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView txtHeader;

        public ViewHolder(View v) {
            super(v);
            txtHeader = (TextView) v.findViewById(R.id.headerName);
        }
    }

    public void add(int position, String item) {
        mDataset.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(String item) {
        int position = mDataset.indexOf(item);
        mDataset.remove(position);
        notifyItemRemoved(position);
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public AlphabetAdapter(ArrayList<String> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public AlphabetAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {

        this.mainContext = parent.getContext();
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_row, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final String name = mDataset.get(position);
        holder.txtHeader.setText(mDataset.get(position));
        holder.txtHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                processViewClick(holder, position);
//                remove(name);

            }
        });


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }


    public void processViewClick(ViewHolder holder, final int position) {


        switch (position) {

            case 0:
                System.out.println("0");

                ABIntroductionFragment abIntroductionFragment = new ABIntroductionFragment();
                switchContent(R.id.drawer_layout, abIntroductionFragment);



                break;
            case 1:

                ABContentsHeaderFragment mFragment = new ABContentsHeaderFragment();
                switchContent(R.id.drawer_layout, mFragment);


                break;
            case 2:
                System.out.println("2");
                break;
            case 3:
                System.out.println("3");
                break;

            default:
                System.out.println("default: " + position);
                break;
        }





    }


    public void switchContent(int id, Fragment fragment) {

        if (mainContext == null)
            return;
        if (mainContext instanceof MainActivity) {
            MainActivity mainActivity = (MainActivity) mainContext;
            Fragment frag = fragment;
            mainActivity.switchContent(id, frag);
        }


    }


}
