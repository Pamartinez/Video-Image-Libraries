package com.samsung.android.gallery.app.ui.list.sharings.storage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SharingStorageUseViewHolderFactory {
    protected final LayoutInflater mLayoutInflater;

    public SharingStorageUseViewHolderFactory(Context context) {
        this(LayoutInflater.from(context));
    }

    public ListViewHolder createViewHolder(ViewGroup viewGroup, int i2) {
        return new SharingStorageUseItemViewHolder(this.mLayoutInflater.inflate(getItemViewLayoutId(), viewGroup, false), i2);
    }

    public int getItemViewLayoutId() {
        return R.layout.recycler_item_sharing_storage_use_image_layout;
    }

    public SharingStorageUseViewHolderFactory(LayoutInflater layoutInflater) {
        this.mLayoutInflater = layoutInflater;
    }
}
