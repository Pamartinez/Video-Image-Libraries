package com.samsung.android.gallery.app.ui.list.albums.choice;

import android.content.Context;
import android.view.ViewGroup;
import com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsViewHolderFactory;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AlbumFolderChoiceViewHolderFactory extends AlbumsViewHolderFactory {
    public AlbumFolderChoiceViewHolderFactory(Context context) {
        super(context);
    }

    public ListViewHolder createGridViewHolder(ViewGroup viewGroup, int i2) {
        return new AlbumFolderChoiceViewHolder(this.mLayoutInflater.inflate(this.mGridLayoutId, viewGroup, false), i2);
    }

    public ListViewHolder createListViewHolder(ViewGroup viewGroup, int i2) {
        return new AlbumFolderChoiceViewHolder(this.mLayoutInflater.inflate(this.mListLayoutId, viewGroup, false), i2);
    }

    public int getGridLayoutId() {
        if (!PreferenceFeatures.OneUi5x.MX_ALBUMS) {
            return R.layout.recycler_item_choice_album_image_grid_layout;
        }
        if (PreferenceFeatures.OneUi8x.MX_ALBUM_BLUR) {
            return R.layout.recycler_item_mx_choice_blur_album_image_grid_layout;
        }
        return R.layout.recycler_item_mx_choice_album_image_grid_layout;
    }

    public int getListLayoutId() {
        if (PreferenceFeatures.OneUi5x.MX_ALBUMS) {
            return R.layout.recycler_item_mx_choice_album_image_list_layout;
        }
        return R.layout.recycler_item_choice_album_image_list_layout;
    }
}
