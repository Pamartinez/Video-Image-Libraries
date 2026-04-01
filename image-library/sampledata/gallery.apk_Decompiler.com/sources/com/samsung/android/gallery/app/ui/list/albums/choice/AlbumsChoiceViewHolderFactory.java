package com.samsung.android.gallery.app.ui.list.albums.choice;

import android.content.Context;
import android.view.ViewGroup;
import com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsViewHolderFactory;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.listviewholder.ViewHolderValue;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class AlbumsChoiceViewHolderFactory extends AlbumsViewHolderFactory {
    private boolean mIsGridView;

    public AlbumsChoiceViewHolderFactory(Context context, boolean z) {
        super(context);
        this.mIsGridView = z;
    }

    public ListViewHolder createGridViewHolder(ViewGroup viewGroup, int i2) {
        return new AlbumChoiceViewHolder(this.mLayoutInflater.inflate(this.mGridLayoutId, viewGroup, false), i2);
    }

    public ListViewHolder createHeaderViewHolder(ViewGroup viewGroup, int i2) {
        AlbumChoiceViewHolder albumChoiceViewHolder = new AlbumChoiceViewHolder(this.mLayoutInflater.inflate(getHeaderLayoutId(), viewGroup, false), i2);
        if (ViewHolderValue.isHeader(i2)) {
            albumChoiceViewHolder.enableImageColorFilter(false);
        }
        return albumChoiceViewHolder;
    }

    public ListViewHolder createListViewHolder(ViewGroup viewGroup, int i2) {
        return new AlbumChoiceViewHolder(this.mLayoutInflater.inflate(this.mListLayoutId, viewGroup, false), i2);
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

    public int getHeaderLayoutId() {
        if (this.mIsGridView) {
            return getGridLayoutId();
        }
        return getListLayoutId();
    }

    public int getListLayoutId() {
        if (PreferenceFeatures.OneUi5x.MX_ALBUMS) {
            return R.layout.recycler_item_mx_choice_album_image_list_layout;
        }
        return R.layout.recycler_item_choice_album_image_list_layout;
    }
}
