package com.samsung.android.gallery.app.ui.list.albums.pictures.setting;

import C3.C0391a;
import M9.o;
import O3.l;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.album.ChangeAlbumCoverCmd;
import com.samsung.android.gallery.app.controller.album.FileOpCmd;
import com.samsung.android.gallery.app.controller.album.FileOpCmdHelper;
import com.samsung.android.gallery.app.ui.list.albums.pictures.setting.IAlbumSettingView;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.settings.ui.BasePresenter;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.SubscriberInfo;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import java.util.ArrayList;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AlbumSettingPresenter<V extends IAlbumSettingView> extends BasePresenter<V> implements EventContext {
    public AlbumSettingPresenter(V v) {
        super(v);
    }

    private void announceScreenLabel() {
        ThreadUtil.postOnBgThread(new o(18, this));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$announceScreenLabel$1() {
        if (!isDestroyed()) {
            Optional.ofNullable(getContext()).ifPresent(new l(8));
        }
    }

    /* access modifiers changed from: private */
    public void onDataChanged(Object obj, Bundle bundle) {
        ((IAlbumSettingView) this.mView).onDataChanged(obj, bundle);
    }

    public Activity getActivity() {
        return ((IAlbumSettingView) this.mView).getActivity();
    }

    public Context getApplicationContext() {
        return ((IAlbumSettingView) this.mView).getApplicationContext();
    }

    public Blackboard getBlackboard() {
        return ((IAlbumSettingView) this.mView).getBlackboard();
    }

    public Context getContext() {
        return ((IAlbumSettingView) this.mView).getContext();
    }

    public String getScreenId() {
        return ((IAlbumSettingView) this.mView).getScreenId();
    }

    public void onChangeCoverClicked(MediaItem mediaItem) {
        new ChangeAlbumCoverCmd().execute(this, mediaItem);
    }

    public void onRenameClicked(MediaItem mediaItem) {
        new FileOpCmd().execute(this, FileOpCmdHelper.CmdType.TYPE_RENAME_ALBUM, mediaItem);
    }

    public void onResume() {
        super.onResume();
        announceScreenLabel();
    }

    public void setGlobalSubscriberList(ArrayList<SubscriberInfo> arrayList) {
        arrayList.add(new SubscriberInfo("global://album/setting/dataChanged", new C0391a(15, this)).setWorkingOnUI());
    }
}
