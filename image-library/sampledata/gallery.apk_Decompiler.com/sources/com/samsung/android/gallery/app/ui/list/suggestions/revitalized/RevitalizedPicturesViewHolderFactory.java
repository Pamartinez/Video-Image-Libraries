package com.samsung.android.gallery.app.ui.list.suggestions.revitalized;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RevitalizedPicturesViewHolderFactory {
    protected final LayoutInflater mLayoutInflater;

    public RevitalizedPicturesViewHolderFactory(Context context) {
        this(LayoutInflater.from(context));
    }

    public ListViewHolder createListViewHolder(ViewGroup viewGroup, int i2) {
        return new RevitalizedPicturesViewHolder(this.mLayoutInflater.inflate(R.layout.recycler_item_revitalized_pictures_image_layout, viewGroup, false), i2);
    }

    public RevitalizedPicturesViewHolderFactory(LayoutInflater layoutInflater) {
        this.mLayoutInflater = layoutInflater;
    }
}
