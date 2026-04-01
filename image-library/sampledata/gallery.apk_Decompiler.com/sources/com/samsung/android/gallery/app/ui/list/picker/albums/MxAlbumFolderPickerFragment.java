package com.samsung.android.gallery.app.ui.list.picker.albums;

import com.samsung.android.gallery.app.ui.list.picker.albums.IMxAlbumFolderPickerView;
import com.samsung.android.gallery.app.ui.list.picker.albums.MxAlbumFolderPickerPresenter;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.listviewholder.ViewHolderValue;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MxAlbumFolderPickerFragment<V extends IMxAlbumFolderPickerView, P extends MxAlbumFolderPickerPresenter<V>> extends AlbumFolderPickerFragment<V, P> implements IMxAlbumFolderPickerView {
    private boolean mIsEssentialMode = true;

    private String appendViewAllArgs(String str) {
        return ArgumentsUtil.appendArgs(str, "ViewAll", String.valueOf(!this.mIsEssentialMode));
    }

    public boolean isEssentialMode() {
        return this.mIsEssentialMode;
    }

    public void onListItemClick(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7) {
        if (ViewHolderValue.isDivider(listViewHolder.getItemViewType())) {
            ((MxAlbumFolderPickerPresenter) this.mPresenter).onViewAllClicked();
        } else {
            super.onListItemClick(listViewHolder, i2, mediaItem, i7);
        }
    }

    public void refreshView(String str, int i2) {
        super.refreshView(appendViewAllArgs(str), i2);
    }

    public void setEssentialMode(boolean z) {
        this.mIsEssentialMode = z;
    }

    public MxAlbumFolderPickerPresenter createPresenter(IMxAlbumFolderPickerView iMxAlbumFolderPickerView) {
        return new MxAlbumFolderPickerPresenter(this.mBlackboard, iMxAlbumFolderPickerView);
    }

    public MxAlbumFolderPickerAdapter createListViewAdapter(GalleryListView galleryListView) {
        return new MxAlbumFolderPickerAdapter(this, getLocationKey());
    }
}
