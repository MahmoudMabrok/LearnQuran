package az.gulf.learnquran.adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import az.gulf.learnquran.R;
import az.gulf.learnquran.activity.MainActivity;
import az.gulf.learnquran.fragment.ABContentsHeaderTheoryFragment;
import az.gulf.learnquran.fragment.ABContentsPractiseFragment;
import az.gulf.learnquran.fragment.ABContentsTestFragment;
import butterknife.Bind;
import butterknife.ButterKnife;

public abstract class ExpandableRecyclerAdapter<T extends ExpandableRecyclerAdapter.ListItem> extends RecyclerView.Adapter<ExpandableRecyclerAdapter.ViewHolder> {
    protected Context mainContext;
    protected List<T> allItems = new ArrayList<>();
    protected List<T> visibleItems = new ArrayList<>();
    protected List<Integer> indexList = new ArrayList<>();
    private SparseIntArray expandMap = new SparseIntArray();

    protected static final int TYPE_HEADER_ORDINARY = 2000;
    protected static final int TYPE_HEADER_OPENABLE = 1000;

    private static final int ARROW_ROTATION_DURATION = 150;

    ProgressDialog progress;

    public ExpandableRecyclerAdapter(Context context) {
        mainContext = context;
    }

    public static class ListItem {
        public int ItemType;
        public int customId;

        public ListItem(int itemType, int customId) {
            ItemType = itemType;
            this.customId=customId;
        }
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getItemCount() {
        return visibleItems == null ? 0 : visibleItems.size();
    }

    protected View inflate(int resourceID, ViewGroup viewGroup) {
        return LayoutInflater.from(mainContext).inflate(resourceID, viewGroup, false);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View view) {
            super(view);

            ButterKnife.bind(this, view);
        }
    }

    public class HeaderViewHolder extends ViewHolder {
        @Bind(R.id.item_arrow) ImageView arrow;

        public HeaderViewHolder(View view) {
            super(view);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    toggleExpandedItems(getLayoutPosition());

                    int index = indexList.get(getLayoutPosition());
                    System.out.println("Header: " + allItems.get(index).customId);



                  switch(allItems.get(index).customId){

                      case 1:

                          // Open video from youtube




                      break;

                      case 2:

//                          System.out.println("geldiiiiii");

                                  ABContentsHeaderTheoryFragment mFragment = new ABContentsHeaderTheoryFragment();
                                  switchContent(R.id.drawer_layout, mFragment);


                          break;

                      case 3:

                          break;

                      case 4:


                          ABContentsPractiseFragment abContentsPractiseFragment = new ABContentsPractiseFragment();
                          switchContent(R.id.drawer_layout, abContentsPractiseFragment);


                          break;

                      case 5:
                          ABContentsTestFragment abContentsTestFragment = new ABContentsTestFragment();
                          switchContent(R.id.drawer_layout, abContentsTestFragment);
                          break;


                  }







                }
            });
        }

        private void toggleExpandedItems(int position) {
            int allItemsPosition = indexList.get(position);

            if (isExpanded(position)) {
                collapseItems(position);
                expandMap.delete(allItemsPosition);
                closeArrow(arrow);
            } else {
                expandItems(position);
                expandMap.put(allItemsPosition, 1);
                openArrow(arrow);
            }
        }

        private void expandItems(int position) {
            int count = 0;
            int index = indexList.get(position);
            int insert = position;

            for (int i=index+1; i<allItems.size()
                    && allItems.get(i).ItemType != TYPE_HEADER_OPENABLE
                    && allItems.get(i).ItemType != TYPE_HEADER_ORDINARY; i++) {
                insert++;
                count++;
                visibleItems.add(insert, allItems.get(i));
                indexList.add(insert, i);
            }

            notifyItemRangeInserted(position + 1, count);
        }

        private void collapseItems(int position) {
            int count = 0;
            int index = indexList.get(position);

            for (int i=index+1; i<allItems.size()
                    && allItems.get(i).ItemType != TYPE_HEADER_OPENABLE
                    && allItems.get(i).ItemType != TYPE_HEADER_ORDINARY; i++) {
                count++;
                visibleItems.remove(position + 1);
                indexList.remove(position + 1);
            }

            notifyItemRangeRemoved(position + 1, count);
        }

        public void bind(int position) {
            arrow.setRotation(isExpanded(position) ? 90 : 0);
        }
    }

    public class StaticViewHolder extends ViewHolder {
        public StaticViewHolder(View view) {
            super(view);
        }
    }

    public class ItemViewHolder extends ViewHolder {
        public ItemViewHolder(View view) {
            super(view);
        }
    }

    protected boolean isExpanded(int position) {
        int allItemsPosition = indexList.get(position);
        return expandMap.get(allItemsPosition, -1) >= 0;
    }

    @Override
    public int getItemViewType(int position) {
        return visibleItems.get(position).ItemType;
    }

    public void setItems(List<T> items) {
        allItems = items;
        List<T> visibleItems = new ArrayList<>();
        expandMap.clear();
        indexList.clear();

        for (int i=0; i<items.size(); i++) {
            if (items.get(i).ItemType == TYPE_HEADER_OPENABLE || items.get(i).ItemType == TYPE_HEADER_ORDINARY) {
                indexList.add(i);
                visibleItems.add(items.get(i));
            }
        }

        this.visibleItems = visibleItems;
        notifyDataSetChanged();
    }

    protected void notifyItemInserted(int allItemsPosition, int visiblePosition) {
        incrementIndexList(allItemsPosition, visiblePosition, 1);
        incrementExpandMapAfter(allItemsPosition, 1);

        if (visiblePosition >= 0) {
            notifyItemInserted(visiblePosition);
        }
    }

    protected void removeItemAt(int visiblePosition) {
        int allItemsPosition = indexList.get(visiblePosition);

        allItems.remove(allItemsPosition);
        visibleItems.remove(visiblePosition);

        incrementIndexList(allItemsPosition, visiblePosition, -1);
        incrementExpandMapAfter(allItemsPosition, -1);

        notifyItemRemoved(visiblePosition);
    }

    private void incrementExpandMapAfter(int position, int direction) {
        SparseIntArray newExpandMap = new SparseIntArray();

        for (int i=0; i<expandMap.size(); i++) {
            int index = expandMap.keyAt(i);
            newExpandMap.put(index < position ? index : index + direction, 1);
        }

        expandMap = newExpandMap;
    }

    private void incrementIndexList(int allItemsPosition, int visiblePosition, int direction) {
        List<Integer> newIndexList = new ArrayList<>();

        for (int i=0; i<indexList.size(); i++) {
            if (i == visiblePosition) {
                if (direction > 0) {
                    newIndexList.add(allItemsPosition);
                }
            }

            int val = indexList.get(i);
            newIndexList.add(val < allItemsPosition ? val : val + direction);
        }

        indexList = newIndexList;
    }

    public static void openArrow(View view) {
        view.animate().setDuration(ARROW_ROTATION_DURATION).rotation(90);
    }

    public static void closeArrow(View view) {
        view.animate().setDuration(ARROW_ROTATION_DURATION).rotation(0);
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
