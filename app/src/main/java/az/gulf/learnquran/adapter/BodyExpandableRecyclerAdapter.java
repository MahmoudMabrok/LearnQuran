package az.gulf.learnquran.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.etiennelawlor.imagegallery.library.activities.ImageGalleryActivity;
import com.etiennelawlor.imagegallery.library.enums.PaletteColorType;

import java.util.ArrayList;
import java.util.List;

import az.gulf.learnquran.R;


import butterknife.Bind;

public class BodyExpandableRecyclerAdapter extends ExpandableRecyclerAdapter<BodyExpandableRecyclerAdapter.BodyListItem> {
    public static final int TYPE_BODY = 1001;

    public BodyExpandableRecyclerAdapter(Context context) {
        super(context);

        setItems(getSampleItems());
    }

    public static class BodyListItem extends ExpandableRecyclerAdapter.ListItem {
        public String Text;

        public BodyListItem(String text, int customId, boolean open) {
            super(open ? TYPE_HEADER_OPENABLE : TYPE_HEADER_ORDINARY, customId);

            Text = text;
        }

        public BodyListItem(String text, int customId) {
            super(TYPE_BODY, customId);

            Text = text;
        }
    }

    public class HeaderViewHolder extends ExpandableRecyclerAdapter.HeaderViewHolder {
        @Bind(R.id.item_header_name)
        TextView name;
        @Bind(R.id.item_header_image)
        ImageView headerImage;

        public HeaderViewHolder(View view, boolean open) {
            super(view);
//            System.out.println(" public HeaderViewHolder(View view, boolean open) "+open);
            if (open) {
                ((ImageView) view.findViewById(R.id.item_arrow)).setImageResource(R.drawable.ic_keyboard_arrow_right_36dp);
            }
        }

        public void bind(int position) {
//            System.out.println("pos " + position + " " + visibleItems.get(position).Text);
            name.setText(visibleItems.get(position).Text);
            switch (position) {

                case 0:
                    headerImage.setImageResource(R.drawable.ic_video_library_24dp);
                    break;
                case 1:
                    headerImage.setImageResource(R.drawable.theory_header_vector);
                    break;
                case 2:
                    headerImage.setImageResource(R.drawable.tasks_header_vector);
                    break;
                case 3:
                    headerImage.setImageResource(R.drawable.practice_head_vector);
                    break;
                case 4:
                    headerImage.setImageResource(R.drawable.test_head_vector);
                    break;
                default:
                    headerImage.setImageResource(R.drawable.ic_menu_send);
                    break;

            }

        }
    }

    public class BodyViewHolder extends ExpandableRecyclerAdapter.ViewHolder {
        @Bind(R.id.item_name)
        TextView name;

        public BodyViewHolder(View view) {
            super(view);


            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    int index = indexList.get(getLayoutPosition());

                    System.out.println("Body: " + allItems.get(index).customId);

                    Intent intent;
                    ArrayList<String> images;


                    switch(allItems.get(index).customId){

                        case 1:

                            // Open video from youtube



                            v.getContext().startActivity(new Intent(Intent.ACTION_VIEW,
                                    Uri.parse("https://www.youtube.com/watch?v=SLKQKTZPL3I")));


                            break;

                        case 2:


                            v.getContext().startActivity(new Intent(Intent.ACTION_VIEW,
                                    Uri.parse("https://www.youtube.com/watch?v=6vnGVbYUUi0")));

                            break;

                        case 3:



                            v.getContext().startActivity(new Intent(Intent.ACTION_VIEW,
                                    Uri.parse("https://www.youtube.com/watch?v=jsubFXNlBYk")));

                            break;

                        case 4:



                            break;

                        case 5:


                             intent = new Intent(v.getContext(), ImageGalleryActivity.class);

                           images = new ArrayList<>();

                            images.add("android.resource://az.gulf.learnquran/" + R.drawable.img_alif_pr);
                            images.add("android.resource://az.gulf.learnquran/" + R.drawable.img_ba_pr);
                            images.add("android.resource://az.gulf.learnquran/" + R.drawable.img_ta_pr);
                            images.add("android.resource://az.gulf.learnquran/" + R.drawable.img_tsa_pr);
                            images.add("android.resource://az.gulf.learnquran/" + R.drawable.img_cim_pr);
                            images.add("android.resource://az.gulf.learnquran/" + R.drawable.img_hha_pr);
                            images.add("android.resource://az.gulf.learnquran/" + R.drawable.img_kha_pr);

                            intent.putStringArrayListExtra("images", images);
                           // optionally set background color using Palette
                            intent.putExtra("palette_color_type", PaletteColorType.DARK_MUTED);

                            v.getContext().startActivity(intent);




                            break;


                        case 6:


                             intent = new Intent(v.getContext(), ImageGalleryActivity.class);

                            images = new ArrayList<>();

                            images.add("android.resource://az.gulf.learnquran/" + R.drawable.img_dal_pr);
                            images.add("android.resource://az.gulf.learnquran/" + R.drawable.img_zal_pr);
                            images.add("android.resource://az.gulf.learnquran/" + R.drawable.img_ra_pr);
                            images.add("android.resource://az.gulf.learnquran/" + R.drawable.img_zayn_pr);
                            images.add("android.resource://az.gulf.learnquran/" + R.drawable.img_sin_pr);
                            images.add("android.resource://az.gulf.learnquran/" + R.drawable.img_shin_pr);
                            images.add("android.resource://az.gulf.learnquran/" + R.drawable.img_sad_pr);

                            intent.putStringArrayListExtra("images", images);
                            // optionally set background color using Palette
                            intent.putExtra("palette_color_type", PaletteColorType.DARK_MUTED);

                            v.getContext().startActivity(intent);




                            break;


                        case 7:


                             intent = new Intent(v.getContext(), ImageGalleryActivity.class);

                            images = new ArrayList<>();

                            images.add("android.resource://az.gulf.learnquran/" + R.drawable.img_dad_pr);
                            images.add("android.resource://az.gulf.learnquran/" + R.drawable.img_tda_pr);
                            images.add("android.resource://az.gulf.learnquran/" + R.drawable.img_tha_pr);
                            images.add("android.resource://az.gulf.learnquran/" + R.drawable.img_ayn_pr);
                            images.add("android.resource://az.gulf.learnquran/" + R.drawable.img_ghayn_pr);
                            images.add("android.resource://az.gulf.learnquran/" + R.drawable.img_fa_pr);
                            images.add("android.resource://az.gulf.learnquran/" + R.drawable.img_kaf_pr);

                            intent.putStringArrayListExtra("images", images);
                            // optionally set background color using Palette
                            intent.putExtra("palette_color_type", PaletteColorType.DARK_MUTED);

                            v.getContext().startActivity(intent);




                            break;



                        case 8:


                             intent = new Intent(v.getContext(), ImageGalleryActivity.class);

                           images = new ArrayList<>();

                            images.add("android.resource://az.gulf.learnquran/" + R.drawable.img_kaf_pr);
                            images.add("android.resource://az.gulf.learnquran/" + R.drawable.img_lam_pr);
                            images.add("android.resource://az.gulf.learnquran/" + R.drawable.img_mim_pr);
                            images.add("android.resource://az.gulf.learnquran/" + R.drawable.img_nun_pr);
                            images.add("android.resource://az.gulf.learnquran/" + R.drawable.img_ha_pr);
                            images.add("android.resource://az.gulf.learnquran/" + R.drawable.img_vav_pr);
                            images.add("android.resource://az.gulf.learnquran/" + R.drawable.img_ya_pr);

                            intent.putStringArrayListExtra("images", images);
                            // optionally set background color using Palette
                            intent.putExtra("palette_color_type", PaletteColorType.DARK_MUTED);

                            v.getContext().startActivity(intent);




                            break;


                    }












                }
            });


        }

        public void bind(int position) {
            name.setText(visibleItems.get(position).Text);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

//        System.out.println("onCreateViewHolder "+" "+viewType);


        switch (viewType) {
            case TYPE_HEADER_OPENABLE:
                return new HeaderViewHolder(inflate(R.layout.rw_abcontents_item_header, parent), true);
            case TYPE_HEADER_ORDINARY:
                return new HeaderViewHolder(inflate(R.layout.rw_abcontents_item_header, parent), false);
            case TYPE_BODY:
            default:
                return new BodyViewHolder(inflate(R.layout.rw_abcontents_item_body, parent));
        }
    }

    @Override
    public void onBindViewHolder(ExpandableRecyclerAdapter.ViewHolder holder, int position) {

//        System.out.println("onBindViewHolder "+" "+position);

        switch (getItemViewType(position)) {
            case TYPE_HEADER_OPENABLE:
            case TYPE_HEADER_ORDINARY:
                ((HeaderViewHolder) holder).bind(position);
                break;
            case TYPE_BODY:
            default:
                ((BodyViewHolder) holder).bind(position);
                break;
        }
    }

    private List<BodyListItem> getSampleItems() {
        List<BodyListItem> items = new ArrayList<>();

        items.add(new BodyListItem("Video Dərslik", 1, true));
        items.add(new BodyListItem("1-ci video dərslik", 1));
        items.add(new BodyListItem("2-ci video dərslik", 2));
        items.add(new BodyListItem("3-cü video dərslik", 3));
        items.add(new BodyListItem("4-cü video dərslik", 4));

        items.add(new BodyListItem("Nəzəriyyə", 2, false));

        items.add(new BodyListItem("Tapşırıqlar", 3, true));
        items.add(new BodyListItem("Tapşırıq 1", 5));
        items.add(new BodyListItem("Tapşırıq 2", 6));
        items.add(new BodyListItem("Tapşırıq 3", 7));
        items.add(new BodyListItem("Tapşırıq 4", 8));

        items.add(new BodyListItem("Məşğələ", 4, false));

        items.add(new BodyListItem("Test", 5, false));


        return items;
    }
}
