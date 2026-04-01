package com.samsung.android.gallery.app.ui.list.albums.mx.all;

import H.a;
import com.samsung.android.gallery.app.ui.list.albums.AlbumsFragment;
import com.samsung.android.gallery.app.ui.list.albums.IAlbumsView;
import com.samsung.android.gallery.app.ui.list.albums.mx.all.MxAllAlbumsPresenter;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MxAllAlbumsFragment<V extends IAlbumsView, P extends MxAllAlbumsPresenter<V>> extends AlbumsFragment<V, P> {
    public MxAllAlbumsFragment() {
        setLocationKey("location://albums/all");
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$refreshAllAlbum$0(String str) {
        MediaData mediaData = this.mMediaData;
        if (mediaData != null) {
            mediaData.reInit(str);
        }
    }

    public int getLayoutId() {
        return R.layout.fragment_all_albums_layout;
    }

    public String getScreenId() {
        if (isSelectionMode()) {
            return AnalyticsScreenId.SCREEN_ALL_ALBUM_VIEW_EDIT.toString();
        }
        return AnalyticsScreenId.SCREEN_ALL_ALBUM_VIEW_NORMAL.toString();
    }

    public void refreshAllAlbum(String str) {
        ThreadUtil.postOnUiThread(new a(21, this, str));
        onInitMoveMode();
    }

    public void savePinchDepth(String str, int i2) {
        super.savePinchDepth(str, i2);
        P p6 = this.mPresenter;
        if (p6 != null) {
            Blackboard.publishGlobal("command://AlbumsViewChanged", Integer.valueOf(((MxAllAlbumsPresenter) p6).getCurrentViewDepth()));
        }
    }

    public boolean supportEnterDefaultTransition() {
        return true;
    }

    public boolean supportExitDefaultTransition() {
        return true;
    }

    public boolean supportTabLayout() {
        return false;
    }

    public MxAllAlbumsPresenter<IAlbumsView> createPresenter(IAlbumsView iAlbumsView) {
        return new MxAllAlbumsPresenter<>(this.mBlackboard, iAlbumsView);
    }
}
