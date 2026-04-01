package com.samsung.android.gallery.app.ui.viewer2.remaster.ondemand;

import B7.d;
import U5.c;
import android.content.Intent;
import android.text.TextUtils;
import com.samsung.android.gallery.app.controller.internals.NondestructiveSaveRemasterCmd;
import com.samsung.android.gallery.app.controller.internals.OnDemandRemasterCmd;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.FragmentLifeCycle;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObject;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemSuggest;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.remaster.ErrorReason;
import com.samsung.android.gallery.module.remaster.RemasterHelper;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.actioninvoker.ActionInvokeListener;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.SecureFile;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.Utils;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RemasterProcessHandler extends ViewerObject implements FragmentLifeCycle {
    private ActionInvokeListener mBackKeyListener;
    private MediaItem mRemasteredMediaItem;
    private String mRemasteredPath = "";
    private STATE mState = STATE.READY;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum STATE {
        READY,
        STARTED,
        CANCELING,
        CANCELED,
        ENDED
    }

    private void handleAutoSave() {
        boolean isEmpty = TextUtils.isEmpty(MediaItemSuggest.getOriginPath(this.mRemasteredMediaItem));
        boolean z = !isEmpty;
        if (!this.mRemasteredMediaItem.isRevitalization() || isEmpty) {
            Log.e((CharSequence) this.TAG, "failed to save a remastered image: ", Logger.v(Boolean.valueOf(this.mRemasteredMediaItem.isRevitalization()), Boolean.valueOf(z)));
            Utils.showToast(this.mModel.getContext(), (int) R.string.image_save_fail);
            quit();
            return;
        }
        if (LocationKey.isRemasterPictures(this.mModel.getContainerModel().getLocationKey())) {
            this.mActionInvoker.invoke(ViewerAction.PREPARE_FORCE_SWIPE, new Object[0]);
        }
        new NondestructiveSaveRemasterCmd().execute(this.mModel.getContainerModel().getEventContext(), this.mRemasteredMediaItem);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$removeRemasterImage$0(String str) {
        SecureFile secureFile = new SecureFile(str);
        if (secureFile.exists() && !secureFile.delete()) {
            Log.w(this.TAG, "Failed delete a remaster image");
        }
    }

    /* access modifiers changed from: private */
    public void onBackKeyPressed(Object... objArr) {
        if (this.mState != STATE.CANCELING) {
            stopRemasterService();
        }
    }

    private void onDemandRemastered(RemasterHelper.Result result) {
        this.mState = STATE.ENDED;
        this.mRemasteredPath = result.path;
        MediaItem mediaItem = (MediaItem) result.mediaItem;
        this.mRemasteredMediaItem = mediaItem;
        boolean isRemasterAutosave = MediaItemUtil.isRemasterAutosave(mediaItem);
        StringCompat stringCompat = this.TAG;
        Log.d(stringCompat, "onRemastered type=" + result.type + GlobalPostProcInternalPPInterface.SPLIT_REGEX + result.mediaItem + GlobalPostProcInternalPPInterface.SPLIT_REGEX + isRemasterAutosave + " " + Logger.getEncodedString(this.mRemasteredPath));
        String str = this.mRemasteredPath;
        if (str == null || this.mRemasteredMediaItem == null || !FileUtils.exists(str)) {
            this.mActionInvoker.invoke(ViewerAction.ON_DEMAND_REMASTER_CANCEL, ErrorReason.UNKNOWN);
            quit();
        } else if (isRemasterAutosave) {
            handleAutoSave();
        } else {
            this.mModel.setRemasteredMediaItem(this.mRemasteredMediaItem);
            this.mModel.addSubMediaItem(this.mRemasteredMediaItem);
            this.mModel.setSubItemCurrentIndex(0);
            this.mActionInvoker.invoke(ViewerAction.ON_DEMAND_REMASTERED, new Object[0]);
            this.mActionInvoker.invoke(ViewerAction.INVALIDATE_TOOLBAR_MENU, new Object[0]);
            this.mActionInvoker.invoke(ViewerAction.ON_DEMAND_REMASTER_ANALYTIC_LOGGING, Long.valueOf(result.type));
            if (Features.isEnabled(Features.SUPPORT_REMASTER_FOCUS_ROI)) {
                this.mActionInvoker.invoke(ViewerAction.ON_REMASTER_FOCUS_AREA_LOADED, result.focusRectList);
            }
        }
    }

    private void quit() {
        this.mActionInvoker.invoke(ViewerAction.BACK_KEY_PRESSED, new Object[0]);
        this.mState = STATE.CANCELED;
    }

    private void stopRemasterService() {
        Intent intent = new Intent();
        intent.setClassName("com.sec.android.gallery3d", "com.samsung.android.gallery.app.service.RemasterService");
        intent.setAction("com.samsung.android.gallery.app.service.STOP_SERVICE");
        this.mModel.getContext().startService(intent);
        Log.d(this.TAG, "stopRemasterService");
    }

    private void unRegisterBackKeyListener() {
        ActionInvokeListener actionInvokeListener = this.mBackKeyListener;
        if (actionInvokeListener != null) {
            this.mActionInvoker.removeExclusive(ViewerAction.BACK_KEY_PRESSED, actionInvokeListener);
            this.mBackKeyListener = null;
        }
    }

    public boolean handleBlackboardEvent(EventMessage eventMessage) {
        int i2 = eventMessage.what;
        if (i2 == 3040) {
            BlackboardUtils.cancelAndEraseViewerBitmap(this.mModel.getBlackboard(), (FileItemInterface) this.mModel.getOriginMediaItem());
            return false;
        } else if (i2 != 3045) {
            switch (i2) {
                case 3035:
                    Log.d(this.TAG, "onHandleEvent EVENT_VIEWER_REMASTER_END");
                    unRegisterBackKeyListener();
                    if (this.mState == STATE.CANCELING) {
                        quit();
                        return true;
                    }
                    onDemandRemastered((RemasterHelper.Result) eventMessage.obj);
                    return true;
                case 3036:
                    Log.d(this.TAG, "onHandleEvent EVENT_VIEWER_REMASTER_CANCEL");
                    this.mState = STATE.CANCELING;
                    this.mActionInvoker.invoke(ViewerAction.ON_DEMAND_REMASTER_CANCEL, eventMessage.obj);
                    this.mActionInvoker.invoke(ViewerAction.ON_DEMAND_REMASTER_ANALYTIC_LOGGING_FOR_ERROR, eventMessage.obj);
                    return true;
                case 3037:
                    if (eventMessage.obj != null) {
                        BlackboardUtils.cancelAndEraseViewerBitmap(this.mModel.getBlackboard(), (FileItemInterface) this.mModel.getOriginMediaItem());
                        this.mModel.setOriginMediaItem((MediaItem) eventMessage.obj);
                    }
                    return false;
                default:
                    return false;
            }
        } else {
            this.mActionInvoker.invoke(ViewerAction.ON_DEMAND_REMASTER_PROCESSING, eventMessage.obj);
            return true;
        }
    }

    public void onViewConfirm() {
        MediaItem mediaItem;
        super.onViewConfirm();
        if (this.mState == STATE.READY && (mediaItem = this.mModel.getMediaItem()) != null) {
            new OnDemandRemasterCmd().execute(this.mModel.getContainerModel().getEventContext(), mediaItem.clone());
            registerBackKeyListener();
            this.mState = STATE.STARTED;
        }
    }

    public void onViewRecycled() {
        super.onViewRecycled();
        STATE state = this.mState;
        if (state == STATE.ENDED) {
            if (!TextUtils.isEmpty(this.mRemasteredPath)) {
                removeRemasterImage(this.mRemasteredPath);
            }
        } else if (state == STATE.STARTED) {
            stopRemasterService();
        }
        this.mRemasteredPath = "";
        this.mRemasteredMediaItem = null;
        this.mState = STATE.READY;
    }

    public void registerBackKeyListener() {
        d dVar = new d(9, this);
        this.mBackKeyListener = dVar;
        this.mActionInvoker.addExclusive(ViewerAction.BACK_KEY_PRESSED, dVar, this.TAG);
    }

    public void removeRemasterImage(String str) {
        Log.d(this.TAG, "remove temp file");
        SimpleThreadPool.getInstance().execute(new c(2, this, str));
    }
}
