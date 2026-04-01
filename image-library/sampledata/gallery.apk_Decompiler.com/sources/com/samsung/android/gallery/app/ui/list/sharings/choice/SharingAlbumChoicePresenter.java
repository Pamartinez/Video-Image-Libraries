package com.samsung.android.gallery.app.ui.list.sharings.choice;

import A4.A;
import C3.C0391a;
import M4.d;
import M5.a;
import M5.b;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import com.samsung.android.gallery.app.controller.mxalbum.SharedAlbumCreatePopupDialogCmd;
import com.samsung.android.gallery.app.controller.sharing.RequestSetupWizardType;
import com.samsung.android.gallery.app.controller.sharing.RequestSharedAlbumCmd;
import com.samsung.android.gallery.app.controller.sharing.StartSharingServiceSetupWizardCmd;
import com.samsung.android.gallery.app.controller.sharing.request.RequestCmdType;
import com.samsung.android.gallery.app.ui.list.albums.choice.abstraction.AlbumChoiceBasePresenter;
import com.samsung.android.gallery.app.ui.list.albums.choice.abstraction.IAlbumChoiceBaseView;
import com.samsung.android.gallery.app.ui.menu.MenuDataBinding;
import com.samsung.android.gallery.module.abstraction.LaunchIntent;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemMde;
import com.samsung.android.gallery.module.mde.MdeAuthHelper;
import com.samsung.android.gallery.module.mde.MdeSharingService;
import com.samsung.android.gallery.module.mde.abstraction.ConnectListener;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.SubscriberInfo;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SharingAlbumChoicePresenter<V extends IAlbumChoiceBaseView> extends AlbumChoiceBasePresenter<V> {
    public SharingAlbumChoicePresenter(Blackboard blackboard, V v) {
        super(blackboard, v);
    }

    private void addToSharedAlbum(MediaItem mediaItem) {
        SimpleThreadPool.getInstance().execute(new a(1, this, mediaItem));
    }

    /* access modifiers changed from: private */
    /* renamed from: executeCreateSharedAlbumCmd */
    public void lambda$createSharingAlbum$0() {
        SharedAlbumCreatePopupDialogCmd sharedAlbumCreatePopupDialogCmd = new SharedAlbumCreatePopupDialogCmd();
        Boolean bool = Boolean.TRUE;
        sharedAlbumCreatePopupDialogCmd.execute(this, bool, AnalyticsEventId.EVENT_SHARED_VIEW_ADD_TO_SHARED_ALBUM_CREATE_SHARED_ALBUM);
        if (isAddContentsFromExternal()) {
            this.mBlackboard.publish("data://add_to_shared_album_from_external", bool);
        }
    }

    /* access modifiers changed from: private */
    public void finish(Object obj, Bundle bundle) {
        ((IAlbumChoiceBaseView) this.mView).finish();
    }

    private boolean isAddContentsFromExternal() {
        LaunchIntent launchIntent = (LaunchIntent) this.mBlackboard.read("data://launch_intent");
        if (launchIntent == null || !launchIntent.isAddContentsToSharedAlbum()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addToSharedAlbum$1(MediaItem mediaItem, Object obj) {
        new RequestSharedAlbumCmd().execute(this, RequestCmdType.REQUEST_ADD_CONTENTS, MediaItemMde.getSpaceId(mediaItem), MediaItemMde.getGroupId(mediaItem), obj);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addToSharedAlbum$3() {
        Optional.ofNullable(this.mBlackboard).ifPresent(new d(14));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addToSharedAlbum$4(MediaItem mediaItem) {
        Optional.ofNullable(this.mBlackboard.read("data://user/selected")).ifPresent(new A(28, (Object) this, (Object) mediaItem));
        ThreadUtil.postOnUiThread(new b(this, 1));
    }

    private void launchMdeSetUpWizard() {
        new StartSharingServiceSetupWizardCmd().execute(this, RequestSetupWizardType.SETUP_SHARING_SERVICE);
    }

    private void retryConnectSessionAfterSALogin() {
        MdeSharingService.getInstance().connectSessionAsync(2, new ConnectListener() {
            public void onFailure(int i2) {
                StringCompat access$100 = SharingAlbumChoicePresenter.this.TAG;
                Log.sh(access$100, "connectSession - onFailure - " + i2);
            }

            public void onSuccess() {
                Log.sh(SharingAlbumChoicePresenter.this.TAG, "connectSession: onSuccess");
                SharingAlbumChoicePresenter.this.retryCreateSharingAlbum();
            }
        });
    }

    /* access modifiers changed from: private */
    public void retryCreateSharingAlbum() {
        lambda$createSharingAlbum$0();
    }

    public MenuDataBinding createMenuDataBinding() {
        return null;
    }

    public void createSharingAlbum() {
        if (!MdeAuthHelper.isReadyToUseShareService()) {
            launchMdeSetUpWizard();
        } else {
            ThreadUtil.postOnUiThreadDelayed(new b(this, 0), 100);
        }
    }

    public void createSubscriberList(ArrayList<SubscriberInfo> arrayList) {
        super.createSubscriberList(arrayList);
        arrayList.add(new SubscriberInfo("command://FinishSharingAlbumChoice", new C0391a(13, this)).setWorkingCurrent());
    }

    public String getScreenId() {
        return AnalyticsScreenId.SCREEN_SHARED_VIEW_ADD_TO_SHARED_ALBUM.toString();
    }

    public boolean handleEvent(EventMessage eventMessage) {
        if (eventMessage.what != 6006) {
            return super.handleEvent(eventMessage);
        }
        retryConnectSessionAfterSALogin();
        return true;
    }

    public boolean onBackPressed() {
        if (this.mBlackboard.read("data://user/selected") != null) {
            this.mBlackboard.erase("data://user/selected");
        }
        if (this.mBlackboard.read("data://user/storyInfo") != null) {
            this.mBlackboard.erase("data://user/storyInfo");
        }
        return super.onBackPressed();
    }

    public void onListItemClickInternal(int i2, MediaItem mediaItem) {
        if (i2 >= 0) {
            if (isAddContentsFromExternal()) {
                addToSharedAlbum(mediaItem);
            } else {
                getBlackboard().post("data://user/move/SharingAlbumChoice", new Object[]{MediaItemMde.getSpaceId(mediaItem), MediaItemMde.getGroupId(mediaItem)});
                if (this.mBlackboard.read("data://user/storyInfo") != null) {
                    this.mBlackboard.erase("data://user/storyInfo");
                }
            }
            postAnalyticsLog(AnalyticsEventId.EVENT_SHARED_VIEW_ADD_TO_SHARED_ALBUM_VIEW_SHARED_ALBUM);
            return;
        }
        createSharingAlbum();
    }

    public void updateToolbar(Toolbar toolbar) {
        if (toolbar != null) {
            toolbar.setTitle((int) R.string.add_to_shared_album);
            toolbar.setSubtitle((CharSequence) null);
            setNavigationUpButton(toolbar);
            return;
        }
        Log.e(this.TAG, "fail updateToolbar");
    }
}
