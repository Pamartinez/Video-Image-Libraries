package com.samsung.android.gallery.app.ui.list.search.pictures.cluster.clusteritem;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class IconTitleDividerViewHolder extends ListViewHolder {
    static final HashMap<Integer, Integer> CLUSTER_CATEGORY_ITEM_MAP = new HashMap<Integer, Integer>() {
        {
            Integer valueOf = Integer.valueOf(R.string.tab_tag_albums);
            Integer valueOf2 = Integer.valueOf(R.drawable.gallery_ic_search_filtered_album);
            put(valueOf, valueOf2);
            put(Integer.valueOf(R.string.album_names), valueOf2);
            Integer valueOf3 = Integer.valueOf(R.string.searchview_location);
            Integer valueOf4 = Integer.valueOf(R.drawable.gallery_ic_search_filtered_location);
            put(valueOf3, valueOf4);
            put(Integer.valueOf(R.string.story_category_people), Integer.valueOf(R.drawable.gallery_ic_search_filtered_person));
            put(Integer.valueOf(R.string.text_found_in_image), Integer.valueOf(R.drawable.gallery_ic_search_filtered_text));
            put(Integer.valueOf(R.string.my_tags), Integer.valueOf(R.drawable.gallery_ic_search_filtered_tag));
            put(Integer.valueOf(R.string.place), valueOf4);
            put(Integer.valueOf(R.string.pets), Integer.valueOf(R.drawable.gallery_ic_search_filtered_pets));
            if (IconTitleDividerViewHolder.SUPPORT_PDC) {
                put(Integer.valueOf(R.string.events), Integer.valueOf(R.drawable.gallery_ic_search_suggested_special_day));
            }
            if (PreferenceFeatures.OneUi8x.VISUAL_SEARCH_85) {
                put(Integer.valueOf(R.string.visual_search_category_people_and_pets), Integer.valueOf(R.drawable.gallery_ic_search_cluster_people_and_pet));
            }
        }
    };
    static final ArrayList<Integer> CLUSTER_CATEGORY_ITEM_TITLE = new ArrayList<Integer>() {
        {
            add(Integer.valueOf(R.string.tab_tag_albums));
            add(Integer.valueOf(R.string.album_names));
            add(Integer.valueOf(R.string.searchview_location));
            add(Integer.valueOf(R.string.story_category_people));
            add(Integer.valueOf(R.string.text_found_in_image));
            add(Integer.valueOf(R.string.place));
            add(Integer.valueOf(R.string.my_tags));
            add(Integer.valueOf(R.string.pets));
            if (IconTitleDividerViewHolder.SUPPORT_PDC) {
                add(Integer.valueOf(R.string.events));
            }
            if (PreferenceFeatures.OneUi8x.VISUAL_SEARCH_85) {
                add(Integer.valueOf(R.string.visual_search_category_people_and_pets));
            }
        }
    };
    /* access modifiers changed from: private */
    public static final boolean SUPPORT_PDC = Features.isEnabled(Features.SUPPORT_PDC_CLUSTER);
    private ImageView mImage;
    private TextView mTitle;

    public IconTitleDividerViewHolder(View view, int i2) {
        super(view, i2);
    }

    private Drawable getClusterIcon(int i2) {
        Drawable drawable = this.mImage.getContext().getDrawable(CLUSTER_CATEGORY_ITEM_MAP.get(Integer.valueOf(i2)).intValue());
        if (drawable != null) {
            drawable.setTint(this.mImage.getContext().getColor(R.color.search_category_arrow_color));
        }
        return drawable;
    }

    private boolean isFirstDivider() {
        if (getViewType() == -1) {
            return true;
        }
        return false;
    }

    private void setClusterIcon(String str) {
        if (str != null) {
            Iterator<Integer> it = CLUSTER_CATEGORY_ITEM_TITLE.iterator();
            while (it.hasNext()) {
                int intValue = it.next().intValue();
                if (str.equals(this.mTitle.getResources().getString(intValue))) {
                    this.mImage.setImageDrawable(getClusterIcon(intValue));
                    ViewUtils.setVisibleOrGone(this.mImage, true);
                }
            }
        }
    }

    public void bind(MediaItem mediaItem) {
        super.bind(mediaItem);
        String title = mediaItem.getTitle();
        ViewUtils.setText(this.mTitle, title);
        if (this.mImage != null) {
            setClusterIcon(title);
        }
    }

    public void bindView(View view) {
        int i2;
        this.mTitle = (TextView) view.findViewById(R.id.title);
        Resources resources = view.getResources();
        if (isFirstDivider()) {
            i2 = R.dimen.search_category_location_first_title_margin_top;
        } else {
            i2 = R.dimen.search_category_location_title_margin_top;
        }
        ViewMarginUtils.setTopMargin(view.findViewById(R.id.divider_layout), resources.getDimensionPixelOffset(i2));
        this.mImage = (ImageView) view.findViewById(R.id.search_cluster_type_icon);
    }
}
