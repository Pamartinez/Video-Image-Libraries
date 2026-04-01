package com.samsung.android.gallery.app.ui.list.albums;

import B4.c;
import B4.d;
import B4.e;
import B4.f;
import android.content.Context;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import com.samsung.android.gallery.app.controller.mxalbum.AlbumCreatePopupDialogCmd;
import com.samsung.android.gallery.app.ui.list.albums.IAlbumsView;
import com.samsung.android.gallery.app.ui.list.albums.abstraction.MenuFactory;
import com.samsung.android.gallery.app.ui.list.albums.drag.AlbumsDragPresenter;
import com.samsung.android.gallery.app.ui.menu.MenuDataBinding;
import com.samsung.android.gallery.database.dal.local.recovery.RecoverFiles;
import com.samsung.android.gallery.module.voc.FindHiddenFiles;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.SubscriberInfo;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.trace.Trace;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AlbumsPresenter<V extends IAlbumsView> extends AlbumsDragPresenter<V> {
    public AlbumsPresenter(Blackboard blackboard, V v) {
        super(blackboard, v);
    }

    private MenuDataBinding createMenuForFirstEntry() {
        try {
            Trace.beginSection("createMenuDataBinding");
            return MenuFactory.createMenuForFirstEntry();
        } finally {
            Trace.endSection();
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$handleEvent$5(boolean z) {
        new AlbumCreatePopupDialogCmd().execute(this, Boolean.valueOf(z), Boolean.valueOf(supportSharedAlbumCreation()));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$handleEvent$6() {
        ThreadUtil.postOnUiThread(new c((Object) this, supportFamilySharedAlbumCreation(), 1));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onLoadFullMenu$1() {
        onLoadFullMenu((Object) null, (Bundle) null);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onLoadFullMenu$2(Object obj, Bundle bundle) {
        ThreadUtil.postOnUiThread(new e(this, 0));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onLoadFullMenu$3() {
        onLoadFullMenu((Object) null, (Bundle) null);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onLoadFullMenu$4(Object obj, Bundle bundle) {
        ThreadUtil.postOnUiThread(new e(this, 3));
    }

    /* access modifiers changed from: private */
    public void onItemsAddedToFolder(Object obj, Bundle bundle) {
        forceReloadData();
    }

    /* access modifiers changed from: private */
    public void onPrepareUngrouping(Object obj, Bundle bundle) {
        if (obj != null) {
            Object[] objArr = (Object[]) obj;
            if (((Boolean) objArr[0]).booleanValue()) {
                ((IAlbumsView) this.mView).onPrepareUngrouping((ArrayList) objArr[1]);
            }
        }
        forceReloadData();
    }

    public MenuDataBinding createFullMenuDataBinding() {
        return MenuFactory.create(this.mBlackboard, getLocationKey());
    }

    public MenuDataBinding createMenuDataBinding() {
        return createMenuForFirstEntry();
    }

    public void createSubscriberList(ArrayList<SubscriberInfo> arrayList) {
        super.createSubscriberList(arrayList);
        arrayList.add(new SubscriberInfo("command://AlbumsViewChanged", new d(this, 0)).setWorkingOnUI());
        arrayList.add(new SubscriberInfo("data://userfolder_ungrouping", new d(this, 1)).setWorkingOnUI());
        arrayList.add(new SubscriberInfo("data://useradd_items_to_folder", new d(this, 2)).setWorkingOnUI());
        arrayList.add(new SubscriberInfo("command://LoadFullMenu", new d(this, 3)).setTriggerPreloadedData().setWorkingOnUI());
    }

    public int getAppbarTitle() {
        return R.string.tab_tag_albums;
    }

    public String getLabelForAccessibility(Context context) {
        return context.getString(R.string.tab_tag_albums);
    }

    public boolean handleEvent(EventMessage eventMessage) {
        if (eventMessage.what != 1014) {
            return super.handleEvent(eventMessage);
        }
        SimpleThreadPool.getInstance().execute(new e(this, 1));
        return true;
    }

    public void initMenuOnViewResume() {
        initMenu();
    }

    public boolean isSkipOnFolderCreationFailed() {
        return !((IAlbumsView) this.mView).isViewResumed();
    }

    public boolean onFolderCreatedFromMenu(int i2, String str, ArrayList<Integer> arrayList) {
        return ((IAlbumsView) this.mView).onFolderCreated(i2, str, arrayList);
    }

    public void onFolderCreationFailed(boolean z, boolean z3) {
        if (isSkipOnFolderCreationFailed()) {
            Log.d(this.TAG, "skip onFolderCreationFailed");
            return;
        }
        if (z) {
            forceReloadData();
        } else {
            ThreadUtil.postOnUiThreadDelayed(new e(this, 2), 600);
        }
        super.onFolderCreationFailed(z, z3);
    }

    public void onLoadFullMenu(Object obj, Bundle bundle) {
        Trace.beginSection("onLoadFullMenu");
        if (isActivityBusy()) {
            Log.e(this.TAG, "onLoadFullMenu : skip activity busy");
            subscribeInstant("lifecycle://on_thumbnail_load_done", new f(this, 0));
            Trace.endSection();
        } else if (!((IAlbumsView) this.mView).isActivityResumed()) {
            Log.e(this.TAG, "onLoadFullMenu : skip not resumed");
            subscribeInstant("lifecycle://on_activity_resume", new f(this, 1));
            Trace.endSection();
        } else {
            MenuDataBinding menuDataBinding = getMenuDataBinding();
            if (menuDataBinding == null || menuDataBinding.getId() == R.menu.menu_albums_first) {
                setMenuDataBinding(createFullMenuDataBinding());
                if (getMenuHandler() == null) {
                    setMenuHandler(createMenuHandler());
                    ((IAlbumsView) this.mView).setOptionsMenu(true);
                }
                ((IAlbumsView) this.mView).invalidateOptionsMenu();
            }
            Trace.endSection();
        }
    }

    public void onTopOverScroll(int i2) {
        FindHiddenFiles.execute();
        RecoverFiles.execute();
    }

    public boolean showDeleteAllWarning() {
        return isSelectAll();
    }

    public boolean supportFamilySharedAlbumCreation() {
        return false;
    }

    public boolean supportLocalDatabaseUpdate() {
        return true;
    }

    public boolean supportSharedAlbumCreation() {
        return false;
    }

    public void updateToolbar(Toolbar toolbar) {
        if (getContext() != null) {
            ((IAlbumsView) this.mView).getAppbarLayout().setTitle(getAppbarTitle());
        }
        super.updateToolbar(toolbar);
    }
}
