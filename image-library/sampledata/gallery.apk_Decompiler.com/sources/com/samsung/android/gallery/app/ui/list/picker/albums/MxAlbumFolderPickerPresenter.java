package com.samsung.android.gallery.app.ui.list.picker.albums;

import com.samsung.android.gallery.app.ui.list.picker.albums.IMxAlbumFolderPickerView;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import i.C0212a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MxAlbumFolderPickerPresenter<V extends IMxAlbumFolderPickerView> extends AlbumFolderPickerPresenter<V> {
    public MxAlbumFolderPickerPresenter(Blackboard blackboard, V v) {
        super(blackboard, v);
    }

    public boolean onBackPressed() {
        if (this.mLocationKeyStack.size() == 2) {
            ((IMxAlbumFolderPickerView) this.mView).setEssentialMode(true);
        }
        return super.onBackPressed();
    }

    public void onFolderClicked(MediaItem mediaItem) {
        ((IMxAlbumFolderPickerView) this.mView).setEssentialMode(false);
        super.onFolderClicked(mediaItem);
    }

    public void onViewAllClicked() {
        String removeArgs = ArgumentsUtil.removeArgs(((IMxAlbumFolderPickerView) this.mView).getLocationKey());
        String A10 = C0212a.A(removeArgs, "/ViewAll");
        this.mLocationKeyStack.push(A10);
        this.mLastScrollPosition.put(removeArgs, Integer.valueOf(((IMxAlbumFolderPickerView) this.mView).getListView().findFirstCompletelyVisibleItemPositionCompat()));
        ((IMxAlbumFolderPickerView) this.mView).setEssentialMode(false);
        ((IMxAlbumFolderPickerView) this.mView).refreshView(A10, 0);
    }
}
