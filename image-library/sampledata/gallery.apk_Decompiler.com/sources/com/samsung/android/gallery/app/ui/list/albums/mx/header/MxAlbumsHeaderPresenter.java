package com.samsung.android.gallery.app.ui.list.albums.mx.header;

import A4.C0371f;
import C3.g;
import H3.l;
import android.content.Context;
import android.content.DialogInterface;
import androidx.appcompat.app.AlertDialog;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.sharing.RequestSharedAlbumCmd;
import com.samsung.android.gallery.app.controller.sharing.request.RequestCmdType;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemMde;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.mde.MdeGroupHelper;
import com.samsung.android.gallery.module.mde.MdeNotificationManager;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.helper.DrawerUtil;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.sec.android.gallery3d.R;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MxAlbumsHeaderPresenter {
    private final EventContext mHandler;
    private final MediaData.OnDataChangeListener mMediaDataChangeListener = new MediaData.SimpleDataChangeListener() {
        public void onDataChanged() {
            MxAlbumsHeaderPresenter.this.onDataChanged();
        }

        public void onDataRangeChanged(int i2, int i7) {
            MxAlbumsHeaderPresenter.this.onDataChanged();
        }

        public void onDataRangeChanged(int i2, int i7, Object obj) {
            MxAlbumsHeaderPresenter.this.onDataChanged();
        }

        public void onDataRangeInserted(int i2, int i7) {
        }
    };
    private MxAlbumsHeaderModel mModel;
    private final IMxAlbumsHeaderView mView;

    public MxAlbumsHeaderPresenter(IMxAlbumsHeaderView iMxAlbumsHeaderView, EventContext eventContext) {
        this.mView = iMxAlbumsHeaderView;
        this.mHandler = eventContext;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$showNoticeProfileSharingDialog$0(MediaItem mediaItem, DialogInterface dialogInterface, int i2) {
        requestInvitationAction(RequestCmdType.REQUEST_INVITATION_ACCEPTANCE, MediaItemMde.getGroupId(mediaItem));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$showNoticeProfileSharingDialog$1(String str, MediaItem mediaItem, Context context) {
        new AlertDialog.Builder(context).setTitle((CharSequence) str).setMessage((CharSequence) this.mHandler.getContext().getString(R.string.your_profile_and_name_will_be_shared, new Object[]{mediaItem.getTitle()})).setPositiveButton((int) R.string.join, (DialogInterface.OnClickListener) new g(3, this, mediaItem)).setNeutralButton((int) R.string.cancel, (DialogInterface.OnClickListener) null).create().show();
    }

    /* access modifiers changed from: private */
    public void onDataChanged() {
        Log.d("MxAlbumsHeaderPresenter", "onDataChanged");
        ThreadUtil.postOnUiThread(new l(17, this));
    }

    /* access modifiers changed from: private */
    public void onDataChangedOnUi() {
        MxAlbumsHeaderModel mxAlbumsHeaderModel = this.mModel;
        if (mxAlbumsHeaderModel != null && this.mView.updateHeaderView(mxAlbumsHeaderModel.getLatestInvitation())) {
            this.mView.onDataChanged();
        }
    }

    private void requestInvitationAction(RequestCmdType requestCmdType, String str) {
        new RequestSharedAlbumCmd().execute(this.mHandler, requestCmdType, str);
        MdeNotificationManager.getInstance(this.mHandler.getContext()).cancel(MediaItemMde.toUid(str));
    }

    private void showNoticeProfileSharingDialog(MediaItem mediaItem) {
        MdeGroupHelper.saveNoticeProfileSharingDialogPreferenceState(false);
        Optional.ofNullable(this.mHandler.getContext()).ifPresent(new C0371f((Object) this, (Object) this.mHandler.getContext().getString(R.string.shared_album_invitation_join_dialog_title, new Object[]{mediaItem.getTitle()}), mediaItem, 6));
    }

    private void tipCardClicked(MxAlbumsHeaderViewState mxAlbumsHeaderViewState) {
        mxAlbumsHeaderViewState.onTipCardClicked(this.mHandler);
        this.mView.refreshHeader(true, true);
    }

    public void close() {
        MxAlbumsHeaderModel mxAlbumsHeaderModel = this.mModel;
        if (mxAlbumsHeaderModel != null) {
            mxAlbumsHeaderModel.unregisterDataChangeListener(this.mMediaDataChangeListener);
            this.mModel.closeMediaData();
        }
    }

    public Blackboard getBlackboard() {
        return this.mHandler.getBlackboard();
    }

    public void loadInvitation() {
        if (this.mModel == null) {
            MxAlbumsHeaderModel mxAlbumsHeaderModel = new MxAlbumsHeaderModel(this.mView.getLocationKey());
            this.mModel = mxAlbumsHeaderModel;
            mxAlbumsHeaderModel.openMediaData(getBlackboard());
            this.mModel.registerDataChangeListener(this.mMediaDataChangeListener);
            return;
        }
        onDataChanged();
    }

    public void onInvitationAcceptClicked(MediaItem mediaItem) {
        if (MdeGroupHelper.isNeedToShowNoticeProfileSharingDialog()) {
            showNoticeProfileSharingDialog(mediaItem);
        } else {
            requestInvitationAction(RequestCmdType.REQUEST_INVITATION_ACCEPTANCE, MediaItemMde.getGroupId(mediaItem));
        }
    }

    public void onInvitationDeclineClicked(MediaItem mediaItem) {
        requestInvitationAction(RequestCmdType.REQUEST_INVITATION_REJECTION, MediaItemMde.getGroupId(mediaItem));
    }

    public void onTipCardAcceptClicked(MxAlbumsHeaderViewState mxAlbumsHeaderViewState) {
        mxAlbumsHeaderViewState.onAcceptClicked(this.mHandler);
        tipCardClicked(mxAlbumsHeaderViewState);
    }

    public void onTipCardDeclineClicked(MxAlbumsHeaderViewState mxAlbumsHeaderViewState) {
        tipCardClicked(mxAlbumsHeaderViewState);
    }

    public boolean supportDrawerLayout() {
        return DrawerUtil.supportDrawerLayout(getBlackboard());
    }
}
