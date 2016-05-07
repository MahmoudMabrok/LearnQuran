package az.gulf.learnquran.adapter;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import az.gulf.learnquran.R;
import az.gulf.learnquran.item.GridViewItem;


public class ABContTheoryGridViewAdapter  extends ArrayAdapter<GridViewItem> {

    private Context context;
    private int layoutResourceId;
    private ArrayList<GridViewItem> data = new ArrayList<>();

    public ABContTheoryGridViewAdapter(Context context, int layoutResourceId, ArrayList<GridViewItem> data) {
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
            holder.title = (TextView) row.findViewById(R.id.text);
            holder.value = (TextView) row.findViewById(R.id.value);
            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }


        GridViewItem item = data.get(position);
        holder.title.setText(item.getTitle());
        holder.value.setText(item.getValue());



        switch(position){
            case 6:
            case 13:
            case 14:
            case 15:
            case 20:
                holder.title.setPaintFlags(holder.value.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);
        }







        return row;
    }

    static class ViewHolder {
        TextView title;
        TextView value;
    }
}
