package com.samsung.android.gallery.app.controller.internals;

import K4.a;
import O3.j;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import androidx.appcompat.app.AlertDialog;
import com.samsung.android.gallery.app.controller.DataCollectionDelegate;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.service.abstraction.AbsProgressService;
import com.samsung.android.gallery.module.cloud.CloudStateCompat;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.secured.PrivateDatabase;
import com.samsung.android.gallery.module.service.message.DeleteMsgMgr;
import com.samsung.android.gallery.module.service.support.DeleteAlbumInfo;
import com.samsung.android.gallery.module.service.support.DeleteInfo;
import com.samsung.android.gallery.module.service.support.DeleteItemInfo;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.ServiceManager;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.samsung.android.gallery.support.utils.Utils;
import com.samsung.android.gallery.widget.dialog.ProgressCircleBuilder;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DeleteCmd extends DeleteBaseCmd {
    public static int DELETE_FROM_SELECTION_VIEW = 1;
    private DeleteInfo mDeleteInfo;
    private EventContext mEventContext;
    private boolean mIsAutoAlbumOnly = false;
    private boolean mIsEmptyAlbumDeleted = false;
    protected boolean mIsGroupDelete;
    MediaItem[] mItems;
    String mLocationKey = null;
    private Dialog mProgressDialog;
    boolean mQuickDelete;
    private long mSelectedNum;
    boolean mShowDeleteAllWarning;
    boolean mUseTrash;

    private void dismissDialog() {
        Dialog dialog = this.mProgressDialog;
        if (dialog != null && dialog.isShowing()) {
            this.mProgressDialog.dismiss();
        }
        this.mProgressDialog = null;
    }

    private String[] getAlbumTitleAndDescription() {
        DeleteAlbumInfo albumInfo = this.mDeleteInfo.getAlbumInfo();
        if (albumInfo == null || albumInfo.getTotalCount() == 0) {
            return null;
        }
        return DeleteMsgMgr.getAlbumMessage(getContext(), albumInfo, CloudStateCompat.isNewGalleryAvailable());
    }

    private String getDeleteOptionName() {
        if (this.mUseTrash && !this.mIsEmptyAlbumDeleted && !this.mIsAutoAlbumOnly) {
            return getContext().getString(R.string.move_to_trash);
        }
        boolean z = this.mShowDeleteAllWarning;
        Context context = getContext();
        if (z) {
            return context.getString(R.string.delete_everything);
        }
        return context.getString(R.string.delete);
    }

    private String[] getItemTitleAndDescription(boolean z) {
        DeleteItemInfo itemInfo = this.mDeleteInfo.getItemInfo();
        if (itemInfo == null || itemInfo.getTotalCount() == 0) {
            return null;
        }
        return DeleteMsgMgr.getItemMessage(getContext(), itemInfo, z);
    }

    private boolean isAlbumsNFolders() {
        if (LocationKey.isAlbumsMatch(this.mLocationKey) || LocationKey.isFolder(this.mLocationKey) || LocationKey.isAllAlbumsMatch(this.mLocationKey)) {
            return true;
        }
        return false;
    }

    private boolean isDeleteServiceRunning() {
        return ServiceManager.getInstance().hasRunningServiceForTrash(getContext());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onConfirmed$3() {
        int trash = PrivateDatabase.getInstance().trash(this.mItems, true);
        getBlackboard().postEvent(EventMessage.obtain(1003));
        Log.d(this.TAG, "trash(pv)", Integer.valueOf(this.mItems.length), Integer.valueOf(trash));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$operateDelete$0() {
        dismissDialog();
        operateDeleteInternal();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$operateDeleteInternal$1() {
        int trash = PrivateDatabase.getInstance().trash(this.mItems, this.mIsGroupDelete);
        getBlackboard().postEvent(EventMessage.obtain(1057));
        getBlackboard().postEvent(EventMessage.obtain(1003));
        Log.d(this.TAG, "trash(pv)", Integer.valueOf(this.mItems.length), Integer.valueOf(trash));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$startConfirmDialog$2() {
        Utils.showToast(getContext(), (int) R.string.delete_item_failed);
    }

    private void showDialog() {
        dismissDialog();
        AlertDialog create = new ProgressCircleBuilder(getContext()).create();
        this.mProgressDialog = create;
        create.show();
    }

    public void addExtraInfo(Intent intent) {
        boolean z;
        intent.putExtra("blackboard_name", getBlackboard().getName());
        intent.putExtra("location_key", getLocationKey());
        boolean z3 = true;
        if (!this.mUseTrash || this.mIsEmptyAlbumDeleted || this.mIsAutoAlbumOnly) {
            z = true;
        } else {
            z = false;
        }
        intent.putExtra("is_direct_delete", z);
        if (!this.mIsEmptyAlbumDeleted && !this.mIsAutoAlbumOnly) {
            z3 = false;
        }
        intent.putExtra("is_force_update_needed", z3);
        intent.putExtra("screenId", getScreenId());
    }

    public Long getAnalyticsValue() {
        long j2 = this.mSelectedNum;
        if (j2 > 0) {
            return Long.valueOf(j2);
        }
        return null;
    }

    public String getEventId() {
        return AnalyticsEventId.EVENT_MENU_DELETE.toString();
    }

    public String getEventIdOfDelete(boolean z, boolean z3) {
        return getEventIdOfDelete(z3);
    }

    public String getLocationKey() {
        return this.mLocationKey;
    }

    public String[] getTitleAndDescription(boolean z, boolean z3) {
        if (isAlbumsNFolders()) {
            return getAlbumTitleAndDescription();
        }
        return getItemTitleAndDescription(z3);
    }

    public boolean isPrivateAlbum(MediaItem[] mediaItemArr) {
        MediaItem mediaItem;
        if (LocationKey.isPrivateAlbum(getLocationKey())) {
            return true;
        }
        if (mediaItemArr == null || mediaItemArr.length <= 0 || (mediaItem = mediaItemArr[0]) == null || !mediaItem.isPrivateItem()) {
            return false;
        }
        return true;
    }

    public void onConfirmed(EventContext eventContext, ArrayList<Object> arrayList) {
        if (!PocFeatures.SUPPORT_PRIVATE_ALBUM || !isPrivateAlbum(this.mItems) || !this.mUseTrash || getLastOption(arrayList) != 1) {
            super.onConfirmed(eventContext, arrayList);
        } else {
            ThreadUtil.runOnBgThread(new j(this, 2));
        }
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        boolean z;
        this.mEventContext = eventContext;
        MediaItem[] mediaItemArr = objArr[0];
        this.mItems = mediaItemArr;
        if (mediaItemArr == null || mediaItemArr.length == 0) {
            Log.e(this.TAG, "unable to operate. no item selected.");
        } else if (isLowStorageWithTrash()) {
            Log.e(this.TAG, "not enough storage");
            new ShowLowStorageCmd().execute(eventContext, new Object[0]);
        } else {
            this.mLocationKey = this.mEventContext.getLocationKey();
            this.mUseTrash = PreferenceFeatures.isEnabled(PreferenceFeatures.UseTrash);
            this.mShowDeleteAllWarning = this.mEventContext.showDeleteAllWarning();
            if (!PocFeatures.SUPPORT_QUICK_DELETE || isConfigured(DELETE_FROM_SELECTION_VIEW)) {
                z = false;
            } else {
                z = true;
            }
            this.mQuickDelete = z;
            if (PocFeatures.SUPPORT_PRIVATE_ALBUM && isPrivateAlbum(this.mItems)) {
                this.mUseTrash = true;
                this.mQuickDelete = false;
            }
            this.mSelectedNum = (long) this.mItems.length;
            this.mDeleteInfo = new DeleteInfo(isAlbumsNFolders(), isVirtualAlbum(this.mLocationKey), this.mUseTrash, this.mShowDeleteAllWarning);
            SimpleThreadPool.getInstance().execute(new j(this, 0));
        }
    }

    public void operateDelete() {
        if (!isDeleteServiceRunning() || ((Integer) getBlackboard().read("data://progress_service_state", -1)).intValue() == AbsProgressService.ServiceState.PROGRESS.ordinal()) {
            operateDeleteInternal();
            return;
        }
        String str = this.TAG;
        Log.w(str, "service state is not executable:" + getBlackboard().read("data://progress_service_state", -1));
        showDialog();
        ThreadUtil.postOnUiThreadDelayed(new j(this, 3), 2000);
    }

    public void operateDeleteInternal() {
        String str;
        if (!PocFeatures.SUPPORT_PRIVATE_ALBUM || !isPrivateAlbum(this.mItems)) {
            Log.d(this.TAG, "operateDeleteInternal");
            getBlackboard().publish("data://user/selected", this.mItems);
            Intent intent = new Intent();
            if (this.mQuickDelete) {
                str = "com.samsung.android.gallery.app.service.QuickDeleteService";
            } else {
                str = "com.samsung.android.gallery.app.service.DeleteService";
            }
            intent.setClassName("com.sec.android.gallery3d", str);
            intent.setAction("com.samsung.android.gallery.app.service.START_SERVICE");
            addExtraInfo(intent);
            try {
                startService(intent);
                getBlackboard().postEvent(EventMessage.obtain(1057));
                getBlackboard().postEvent(EventMessage.obtain(1003));
            } catch (IllegalStateException e) {
                String str2 = this.TAG;
                Log.e(str2, "unable to start delete service e=" + e.getMessage());
            }
        } else {
            ThreadUtil.runOnBgThread(new j(this, 4));
        }
    }

    public void startConfirmDialog() {
        boolean z;
        boolean z3;
        Log.d(this.TAG, "show confirm dialog");
        MediaItem[] validItems = getValidItems(this.mItems);
        this.mItems = validItems;
        this.mDeleteInfo.calculateCount(validItems);
        String[] titleAndDescription = getTitleAndDescription(this.mUseTrash, CloudStateCompat.isNewGalleryAvailable());
        if (titleAndDescription == null) {
            Log.e(this.TAG, "unable to operate. no item selected, exclude null");
            ThreadUtil.postOnUiThread(new j(this, 1));
            return;
        }
        if (titleAndDescription.length > 2) {
            if (Integer.parseInt(titleAndDescription[2]) <= 0) {
                z = true;
            } else {
                z = false;
            }
            this.mIsEmptyAlbumDeleted = z;
            if (titleAndDescription.length > 3) {
                if (Integer.parseInt(titleAndDescription[2]) == Integer.parseInt(titleAndDescription[3])) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                this.mIsAutoAlbumOnly = z3;
            }
        }
        DataCollectionDelegate.getInitInstance(this.mEventContext).setRequestData(new UriBuilder("data://user/dialog/SimpleConfirm").appendArg("type", 0).appendArg("title", titleAndDescription[0]).appendArg("description", titleAndDescription[1]).appendArg("option1", getDeleteOptionName()).appendArg("screenId", getScreenId()).appendArg("option1EventId", getEventIdOfDelete(this.mIsEmptyAlbumDeleted, this.mUseTrash)).appendArg("option1ColorRed", !this.mUseTrash).appendArg("cancelEventId", getEventIdOfCancel(this.mUseTrash)).build()).setOnDataCompleteListener(new a(21, this)).start();
    }

    public MediaItem[] getValidItems(MediaItem[] mediaItemArr) {
        return mediaItemArr;
    }
}
