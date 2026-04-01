package com.samsung.android.gallery.app.ui.list.albums.choice;

import A4.C0372g;
import Ab.b;
import C3.C0391a;
import android.content.Context;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import com.samsung.android.gallery.app.controller.DataCollectionDelegate;
import com.samsung.android.gallery.app.controller.album.CreateAlbumCmd;
import com.samsung.android.gallery.app.ui.list.albums.abstraction.MenuFactory;
import com.samsung.android.gallery.app.ui.list.albums.choice.IAlbumChoiceView;
import com.samsung.android.gallery.app.ui.list.albums.choice.abstraction.AlbumChoiceBasePresenter;
import com.samsung.android.gallery.app.ui.menu.MenuDataBinding;
import com.samsung.android.gallery.module.album.AlbumHelper;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.SubscriberInfo;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AlbumChoicePresenter<V extends IAlbumChoiceView> extends AlbumChoiceBasePresenter<V> {
    public AlbumChoicePresenter(Blackboard blackboard, V v) {
        super(blackboard, v);
    }

    private boolean checkValidAlbumPath(int i2, String str) {
        if (isCreateButton(i2) || str != null) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public void copyMoveToNewAlbum(Object obj, Bundle bundle) {
        int i2;
        if (!isRootLocation()) {
            String peekLocationStack = getPeekLocationStack();
            i2 = Integer.parseInt(peekLocationStack.substring(peekLocationStack.lastIndexOf(47) + 1));
        } else {
            i2 = 0;
        }
        this.mBlackboard.publish("album_copy_move_tgt_folder", Integer.valueOf(i2));
        DataCollectionDelegate.getInstance(getBlackboard()).replace(new CreateAlbumCmd().getTargetKey(this));
        postAnalyticsLog(AnalyticsEventId.EVENT_MENU_CREATE_ALBUM);
        exitChoiceFragment();
    }

    private void handleItemClick(int i2, String str) {
        ThreadUtil.postOnUiThread(new b((Object) this, i2, (Object) str, 7));
    }

    private boolean isCreateButton(int i2) {
        if (i2 < 0) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$exitChoiceFragment$1() {
        if (((IAlbumChoiceView) this.mView).isMoveBarMode()) {
            ((IAlbumChoiceView) this.mView).onExitMoveMode();
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$handleItemClick$2(int i2, String str) {
        if (isCreateButton(i2)) {
            DataCollectionDelegate.getInstance(getBlackboard()).replace(new CreateAlbumCmd().getTargetKey(this));
            postAnalyticsLog(AnalyticsEventId.EVENT_MENU_CREATE_ALBUM);
        } else {
            getBlackboard().post("data://user/move/AlbumChoice", new Object[]{FileUtils.getNameFromPath(str), str, Boolean.FALSE});
            postAnalyticsLog(AnalyticsEventId.EVENT_COPY_MOVE_TO_ALBUM_ALBUM_LIST);
        }
        exitChoiceFragment();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onListItemClickInternal$0(MediaItem mediaItem, int i2) {
        String validPath = AlbumHelper.getInstance().getValidPath(mediaItem);
        if (checkValidAlbumPath(i2, validPath)) {
            StringCompat stringCompat = this.TAG;
            Log.e(stringCompat, "path : null, isCloudOnly : " + mediaItem.isCloudOnly());
            return;
        }
        handleItemClick(i2, validPath);
    }

    public MenuDataBinding createMenuDataBinding() {
        return MenuFactory.createMenuForAlbumChoice();
    }

    public void createSubscriberList(ArrayList<SubscriberInfo> arrayList) {
        super.createSubscriberList(arrayList);
        arrayList.add(new SubscriberInfo("command://CopyMoveToNewAlbum", new C0391a(2, this)));
    }

    public void exitChoiceFragment() {
        ThreadUtil.runOnUiThread(new C0372g(26, this));
        getBlackboard().publish("command://MoveCMD", "command://MoveCMD/FinishFragment");
    }

    public void exitMoveMode(Object obj, Bundle bundle) {
        if (!((IAlbumChoiceView) this.mView).isMoveBarMode()) {
            super.exitMoveMode(obj, bundle);
        } else if (!((Boolean) obj).booleanValue()) {
            ((IAlbumChoiceView) this.mView).onCanceled();
        } else {
            ((IAlbumChoiceView) this.mView).onExitMoveMode();
        }
    }

    public int getDividerButtonHeight() {
        Context context = getContext();
        if (context != null) {
            return context.getResources().getDimensionPixelOffset(R.dimen.divider_button_layout_height);
        }
        return 0;
    }

    public boolean needRestoreMoveBar() {
        return ((IAlbumChoiceView) this.mView).isMoveBarMode();
    }

    public boolean onBackPressed() {
        if (super.onBackPressed()) {
            return true;
        }
        if (!((IAlbumChoiceView) this.mView).isMoveBarMode()) {
            return false;
        }
        ((IAlbumChoiceView) this.mView).onExitMoveMode();
        return false;
    }

    public void onEnterMoveMode() {
        if (((IAlbumChoiceView) this.mView).isMoveBarMode()) {
            addMoveBottomBar();
        }
    }

    public void onExitMoveMode() {
        if (((IAlbumChoiceView) this.mView).isMoveBarMode()) {
            this.mBlackboard.post("command://HideBottomMoveBar", Boolean.TRUE);
            this.mBlackboard.erase("data://album_move");
        }
    }

    public void onListItemClickInternal(int i2, MediaItem mediaItem) {
        if (mediaItem.isFolder()) {
            onFolderClicked(mediaItem);
            postAnalyticsLog(AnalyticsEventId.EVENT_COPY_MOVE_TO_GROUP);
            return;
        }
        ThreadUtil.postOnBgThread(new b((Object) this, (Object) mediaItem, i2, 6));
    }

    public void showMoveBottomBar(boolean z) {
        if (((IAlbumChoiceView) this.mView).isMoveBarMode()) {
            this.mBlackboard.post("command://ShowBottomMoveBar", new Object[]{Boolean.valueOf(z), Boolean.FALSE, Boolean.TRUE});
        } else {
            super.showMoveBottomBar(z);
        }
    }

    public void updateToolbar(Toolbar toolbar) {
        if (toolbar != null) {
            toolbar.setTitle((CharSequence) null);
            toolbar.setSubtitle((CharSequence) null);
            setNavigationUpButton(toolbar);
            return;
        }
        Log.e(this.TAG, "fail updateToolbar");
    }
}
