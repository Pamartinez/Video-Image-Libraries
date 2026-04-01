package com.samsung.android.gallery.app.ui.list.suggestions.cleanout;

import android.content.Context;
import android.view.View;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesViewHolderFactory;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CleanOutDuplicatedViewHolderFactory extends PicturesViewHolderFactory {
    public CleanOutDuplicatedViewHolderFactory(Context context) {
        super(context);
    }

    public int getDataLayoutId() {
        return R.layout.recycler_item_duplicate_previewable_image_layout;
    }

    public ListViewHolder getDataViewHolder(View view, int i2) {
        return new DuplicateItemViewHolder(view, i2);
    }

    public ListViewHolder getDateLocationViewHolder(View view, int i2) {
        return new DuplicateDividerViewHolder(view, i2);
    }

    public int getFirstTimelineLayoutId() {
        return R.layout.clean_out_duplicate_pictures_divider_layout;
    }

    public int getTimelineLayoutId() {
        return R.layout.clean_out_duplicate_pictures_divider_layout;
    }
}
