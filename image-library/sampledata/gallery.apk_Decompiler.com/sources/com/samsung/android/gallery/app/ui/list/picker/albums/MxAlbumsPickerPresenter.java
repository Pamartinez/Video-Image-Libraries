package com.samsung.android.gallery.app.ui.list.picker.albums;

import com.samsung.android.gallery.app.ui.list.picker.albums.IAlbumsPickerView;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.UriBuilder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MxAlbumsPickerPresenter<V extends IAlbumsPickerView> extends AlbumsPickerPresenter<V> {
    public MxAlbumsPickerPresenter(Blackboard blackboard, V v) {
        super(blackboard, v);
    }

    public void moveToAllAlbums() {
        this.mBlackboard.post("command://MoveURL", new UriBuilder("location://albums/all").appendArg("disabledAlbumType", ArgumentsUtil.getArgValue(((IAlbumsPickerView) this.mView).getLocationKey(), "disabledAlbumType", (String) null)).build());
    }
}
