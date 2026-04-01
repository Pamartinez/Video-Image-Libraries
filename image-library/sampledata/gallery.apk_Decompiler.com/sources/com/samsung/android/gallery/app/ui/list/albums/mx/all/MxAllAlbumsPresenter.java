package com.samsung.android.gallery.app.ui.list.albums.mx.all;

import C3.C0391a;
import android.content.Context;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import com.samsung.android.gallery.app.ui.list.albums.AlbumsPresenter;
import com.samsung.android.gallery.app.ui.list.albums.IAlbumsView;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.SubscriberInfo;
import com.samsung.android.gallery.support.utils.Log;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MxAllAlbumsPresenter<V extends IAlbumsView> extends AlbumsPresenter<V> {
    public MxAllAlbumsPresenter(Blackboard blackboard, V v) {
        super(blackboard, v);
    }

    /* access modifiers changed from: private */
    public void onEssentialAlbumsChanged(Object obj, Bundle bundle) {
        if (!((IAlbumsView) this.mView).isDrawerMode()) {
            ((IAlbumsView) this.mView).finish();
        }
    }

    public void createGlobalSubscriberList(ArrayList<SubscriberInfo> arrayList) {
        arrayList.add(new SubscriberInfo("global://setting/albums/selectEssentialAlbums", new C0391a(10, this)).setWorkingOnUI());
    }

    public int getAppbarTitle() {
        return R.string.all_albums_title;
    }

    public String getLabelForAccessibility(Context context) {
        return context.getString(R.string.all_albums_title);
    }

    public void onFolderCreationFailed(boolean z, boolean z3) {
        if (isSkipOnFolderCreationFailed()) {
            Log.d(this.TAG, "skip onFolderCreationFailed");
        } else if (!z || z3) {
            super.onFolderCreationFailed(z, z3);
        } else {
            V v = this.mView;
            ((IAlbumsView) v).refreshAllAlbum(((IAlbumsView) v).getLocationKey());
        }
    }

    public boolean supportLocalDatabaseUpdate() {
        return false;
    }

    public void updateToolbar(Toolbar toolbar) {
        super.updateToolbar(toolbar);
        toolbar.setTitle((CharSequence) null);
        if (!((IAlbumsView) this.mView).isDrawerMode()) {
            setNavigationUpButton(toolbar);
        }
    }
}
