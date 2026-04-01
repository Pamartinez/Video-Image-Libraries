package com.samsung.android.gallery.app.ui.list.picker.albums;

import android.content.Context;
import android.view.ViewGroup;
import com.samsung.android.gallery.app.ui.list.albums.choice.AlbumFolderChoiceViewHolderFactory;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class AlbumFolderPickerViewHolderFactory extends AlbumFolderChoiceViewHolderFactory {
    public AlbumFolderPickerViewHolderFactory(Context context) {
        super(context);
    }

    public ListViewHolder createHeaderViewHolder(ViewGroup viewGroup, int i2) {
        return new AlbumFolderPickerHeaderViewHolder(this.mLayoutInflater.inflate(this.mHeaderLayoutId, viewGroup, false), i2);
    }

    public int getGridLayoutId() {
        if (!isFlipWidgetWithCoverScreen()) {
            return super.getGridLayoutId();
        }
        if (PreferenceFeatures.OneUi8x.MX_ALBUM_BLUR) {
            return R.layout.recycler_item_mx_blur_album_cover_widget_image_grid_layout;
        }
        return R.layout.recycler_item_album_cover_widget_image_grid_layout;
    }

    public int getHeaderLayoutId() {
        return R.layout.recycler_item_album_picker_header_layout;
    }

    public int getListLayoutId() {
        if (isFlipWidgetWithCoverScreen()) {
            return R.layout.recycler_item_album_cover_widget_image_list_layout;
        }
        if (!PreferenceFeatures.OneUi5x.MX_ALBUMS) {
            return R.layout.recycler_item_choice_album_image_list_layout;
        }
        if (PreferenceFeatures.OneUi8x.MX_ALBUM_BLUR) {
            return R.layout.recycler_item_mx_choice_blur_album_image_list_layout;
        }
        return R.layout.recycler_item_mx_choice_album_image_list_layout;
    }

    public abstract boolean isFlipWidgetWithCoverScreen();
}
