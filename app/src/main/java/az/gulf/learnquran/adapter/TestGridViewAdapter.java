package az.gulf.learnquran.adapter;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import az.gulf.learnquran.R;
import az.gulf.learnquran.item.GridViewTestItem;


public class TestGridViewAdapter extends ArrayAdapter<GridViewTestItem> {

    private Context context;
    private int layoutResourceId;
    private ArrayList<GridViewTestItem> data = new ArrayList<>();
    private boolean imageVisible = false;

    public TestGridViewAdapter(Context context, int layoutResourceId, ArrayList<GridViewTestItem> data, boolean imageVisible) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
        this.imageVisible = imageVisible;
    }
    public TestGridViewAdapter(Context context, int layoutResourceId, ArrayList<GridViewTestItem> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new ViewHolder();
            holder.answer = (TextView) row.findViewById(R.id.ab_test_grid_view_item_text);
            holder.image = (ImageView) row.findViewById(R.id.ab_test_grid_view_item_img);
            holder.isItTrueAnswer = (TextView) row.findViewById(R.id.ab_test_grid_view_item_text_answer);

            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }


        GridViewTestItem item = data.get(position);

        holder.answer.setText(item.getAnswer());
        holder.image.setImageResource(item.getImageId());
        holder.isItTrueAnswer.setText(item.getIsTrueAnswer());


        System.out.println("imageVisible: "+imageVisible);

        if(!imageVisible){
            holder.image.setVisibility(View.INVISIBLE);
        }else{
            holder.image.setVisibility(View.VISIBLE);
        }

        return row;
    }

    static class ViewHolder {
        TextView answer;
        ImageView image;
        TextView isItTrueAnswer;

    }

    public void updateImageVisibility(ArrayList<GridViewTestItem> data, boolean imageVisible){
        this.data = data;
        this.imageVisible = imageVisible;
       notifyDataSetChanged();

    }


    public void processClickItemResult(ArrayList<GridViewTestItem> currentData,ArrayList<GridViewTestItem> nextData,int soundId,Context context){

        new MyAsyncTask(currentData,nextData,soundId,context).execute();

//        this.data = currentData;
//        this.imageVisible = imageVisible;
//        notifyDataSetChanged();
//
//
//        this.data = nextData;
//        this.imageVisible = false;
//        notifyDataSetChanged();


    }



    private class MyAsyncTask extends AsyncTask<Void, Void, Void> {

        ArrayList<GridViewTestItem> currentData;
        ArrayList<GridViewTestItem> nextData;
        int soundId;
        Context context;

        MyAsyncTask(ArrayList<GridViewTestItem> currentData,ArrayList<GridViewTestItem> nextData,int soundId, Context context){
            this.currentData=currentData;
            this.nextData=nextData;
            this.soundId=soundId;
            this.context=context;
        }


        @Override
        protected void onPreExecute(){
            // show your progress dialog
            TestGridViewAdapter.this.data=currentData;
            TestGridViewAdapter.this.imageVisible = true;
            notifyDataSetChanged();
        }
        @Override
        protected Void doInBackground(Void... voids){

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void params)
        {
            TestGridViewAdapter.this.data=nextData;
            TestGridViewAdapter.this.imageVisible = false;
            notifyDataSetChanged();

                MediaPlayer mediaPlayer = MediaPlayer.create(context, R.raw.alif);
                mediaPlayer.start(); // no need to call prepare(); create() does that for you


        }

    }



}
