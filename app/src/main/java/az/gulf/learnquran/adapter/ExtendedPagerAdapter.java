package az.gulf.learnquran.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import az.gulf.learnquran.R;
import az.gulf.learnquran.domain.ViewPagerObject;

/**
 * Created by www.ucuz.az on 24.01.2016.
 */
public class ExtendedPagerAdapter  extends PagerAdapter {

    List<ViewPagerObject> items;
    LayoutInflater inflater;

    public ExtendedPagerAdapter(Context context, List<ViewPagerObject> items)
    {
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.items = items;
    }


    @Override
    public boolean isViewFromObject(View view, Object object)
    {
        return view == ((RelativeLayout) object);
    }

    @Override
    public int getCount()
    {
        return items.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object)
    {
        ((ViewPager) container).removeView((View)object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position)
    {

        View itemView;
        itemView = inflater.inflate(R.layout.vp_item_ab_contents_practice_layout, container, false);

        TextView topTextItem = (TextView) itemView.findViewById(R.id.ab_cont_practice_viewPager_text);
//        TextView bottomTextItem = (TextView) itemView.findViewById(R.id.bottomText);
        topTextItem.setLayerType(View.LAYER_TYPE_SOFTWARE, null);

        ViewPagerObject customObject = items.get(position);
        topTextItem.setText(customObject.getText());




//        bottomTextItem.setText(customObject.bottomText);
        ((ViewPager) container).addView(itemView);

        return itemView;
    }}