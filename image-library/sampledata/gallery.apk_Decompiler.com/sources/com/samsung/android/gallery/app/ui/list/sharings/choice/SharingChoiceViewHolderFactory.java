package com.samsung.android.gallery.app.ui.list.sharings.choice;

import android.content.Context;
import android.view.ViewGroup;
import com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsViewHolderFactory;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SharingChoiceViewHolderFactory extends AlbumsViewHolderFactory {
    public SharingChoiceViewHolderFactory(Context context) {
        super(context);
    }

    public ListViewHolder createHeaderViewHolder(ViewGroup viewGroup, int i2) {
        return new SharingAlbumChoiceViewHolder(this.mLayoutInflater.inflate(this.mHeaderLayoutId, viewGroup, false), i2);
    }

    public ListViewHolder createListViewHolder(ViewGroup viewGroup, int i2) {
        return new SharingAlbumChoiceViewHolder(this.mLayoutInflater.inflate(this.mListLayoutId, viewGroup, false), i2);
    }

    public int getHeaderLayoutId() {
        return R.layout.recycler_item_sharing_choice_list_layout;
    }

    public int getListLayoutId() {
        return R.layout.recycler_item_sharing_choice_list_layout;
    }
}
