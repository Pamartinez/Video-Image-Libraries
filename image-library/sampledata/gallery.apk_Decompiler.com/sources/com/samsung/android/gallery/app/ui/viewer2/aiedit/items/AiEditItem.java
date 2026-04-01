package com.samsung.android.gallery.app.ui.viewer2.aiedit.items;

import a8.d;
import android.graphics.Bitmap;
import android.view.View;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.abstraction.DownloadType;
import com.samsung.android.gallery.app.controller.viewer.DownloadForViewerCmd;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.module.account.AwesomeIntelligenceServiceManager;
import com.samsung.android.gallery.module.account.SamsungAccountManager;
import com.samsung.android.gallery.module.aiedit.AiEditExecutor;
import com.samsung.android.gallery.module.aiedit.AiEditType;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.logger.AnalyticsLogger;
import com.samsung.android.gallery.module.service.download.DownloadSyncMgr;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.StorageUtil;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.Utils;
import com.samsung.android.gallery.widget.animations.textexpand.TextExpandAnimView;
import com.samsung.android.gallery.widget.awesome.IAwesomeItem;
import com.sec.android.gallery3d.R;
import h7.C0466b;
import h7.C0467c;
import java.util.List;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class AiEditItem implements IAwesomeItem {
    public final String TAG = getClass().getSimpleName();
    protected final ActionInvoker mActionInvoker;
    protected boolean mCheckLowStorage;
    protected final EventContext mEventContext;
    private AiEditExecutor mExecutor = AiEditExecutor.EditSuggestion;
    private final AiEditType mType;

    public AiEditItem(EventContext eventContext, ActionInvoker actionInvoker, AiEditType aiEditType) {
        this.mEventContext = eventContext;
        this.mActionInvoker = actionInvoker;
        this.mType = aiEditType;
    }

    private void executeAfterDetailsCollapsed(Runnable runnable) {
        this.mActionInvoker.invoke(ViewerAction.EXECUTE_AI_EDIT_AFTER_COLLAPSED, Integer.valueOf(getExecuteTriggerType().ordinal()), runnable);
    }

    /* access modifiers changed from: private */
    /* renamed from: executeAfterDownload */
    public void lambda$executeCloudOnly$3(MediaItem mediaItem, boolean z) {
        if (mediaItem != null) {
            lambda$executeItem$0(mediaItem, z);
        } else {
            Log.e(this.TAG, "execute failed: download item is null");
        }
    }

    private void executeCloudOnly(MediaItem mediaItem, boolean z) {
        if (getExecuteTriggerType().isNeedCollapse()) {
            executeAfterDetailsCollapsed(new C0466b(this, mediaItem, z, 1));
        } else {
            requestDownload(mediaItem, DownloadType.EDIT, new C0467c(this, z, 0));
        }
    }

    private void executeItem(boolean z, MediaItem mediaItem) {
        postAnalyticsLog(mediaItem.isImage());
        if (isNeedCloudOnlyDownload() && mediaItem.isCloudOnly()) {
            executeCloudOnly(mediaItem, z);
        } else if (getExecuteTriggerType().isNeedCollapse()) {
            executeAfterDetailsCollapsed(new C0466b(this, mediaItem, z, 0));
        } else {
            lambda$executeItem$0(mediaItem, z);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$executeCloudOnly$2(MediaItem mediaItem, boolean z) {
        requestDownload(mediaItem, DownloadType.EDIT, new C0467c(this, z, 1));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$requestDownload$4(MediaItem mediaItem, Consumer consumer, DownloadType downloadType) {
        DownloadSyncMgr downloadSyncMgr = new DownloadSyncMgr();
        if (downloadSyncMgr.valid(mediaItem.getFileId())) {
            new DownloadForViewerCmd().execute(this.mEventContext, mediaItem, consumer, downloadType, downloadSyncMgr);
            return;
        }
        String str = this.TAG;
        Log.w(str, "skip downloading. [" + mediaItem.getFileId() + "] is not valid");
    }

    private void requestDownload(MediaItem mediaItem, DownloadType downloadType, Consumer<Object> consumer) {
        ThreadUtil.postOnBgThread(new d((Object) this, (Object) mediaItem, (Object) consumer, (Object) downloadType, 13));
    }

    /* renamed from: execute */
    public void lambda$executeItem$0(MediaItem mediaItem, boolean z) {
        Object obj;
        String str = this.TAG;
        if (mediaItem != null) {
            obj = Long.valueOf(mediaItem.getFileId());
        } else {
            obj = "";
        }
        Log.d(str, "onAiEditExecute", obj, Boolean.valueOf(z));
    }

    public String getAnalyticsDetail() {
        return null;
    }

    public AnalyticsEventId getEventId() {
        return this.mType.eventId;
    }

    public ExecuteTriggerType getExecuteTriggerType() {
        return ExecuteTriggerType.DEFAULT;
    }

    public AiEditItem getLoadableItem(AiEditType aiEditType) {
        if (getType().equals(aiEditType)) {
            return this;
        }
        return null;
    }

    public final AiEditType getType() {
        return this.mType;
    }

    public boolean isNeedCloudOnlyDownload() {
        return false;
    }

    public abstract List<AiEditItem> load(MediaItem mediaItem, Bitmap bitmap);

    public final void onMenuSelect(View view, boolean z) {
        if (!(view instanceof TextExpandAnimView) || !((TextExpandAnimView) view).isAnimationRunning()) {
            onMenuSelect(z);
        } else {
            Log.w(this.TAG, "ai edits selected failed: animation is running");
        }
    }

    public void postAnalyticsLog(boolean z) {
        String str;
        if (getEventId() == null) {
            str = null;
        } else {
            str = getEventId().toString();
        }
        if (str != null) {
            String analyticsDetail = getAnalyticsDetail();
            String screenId = this.mExecutor.getScreenId(z);
            if (analyticsDetail != null) {
                AnalyticsLogger.getInstance().postLog(screenId, str, analyticsDetail);
            } else {
                AnalyticsLogger.getInstance().postLog(screenId, str);
            }
        }
    }

    public void setExecutor(AiEditExecutor aiEditExecutor) {
        this.mExecutor = aiEditExecutor;
    }

    public boolean supportAutoSave() {
        return false;
    }

    public String toString() {
        return this.TAG;
    }

    public final void onMenuSelect(boolean z) {
        Boolean bool = (Boolean) this.mEventContext.getEventContextData("is_input_blocked");
        if (bool == null || !bool.booleanValue()) {
            MediaItem currentItem = this.mEventContext.getCurrentItem();
            if (currentItem == null) {
                Log.w(this.TAG, "ai edits selected failed: null item");
            } else if (!this.mCheckLowStorage || !StorageUtil.isLowStorage()) {
                this.mActionInvoker.invoke(ViewerAction.SET_INPUT_BLOCK, this.TAG, 1000);
                if (Features.isEnabled(Features.SUPPORT_AWESOME_INTELLIGENT_FEATURES) && !PreferenceFeatures.isEnabled(PreferenceFeatures.RetailMode)) {
                    if (AwesomeIntelligenceServiceManager.getInstance().needToShowAiIntroduction(this.mEventContext.getContext())) {
                        this.mEventContext.getBlackboard().publish("data://viewer_executed_ai_edit", new Object[]{Long.valueOf(currentItem.getFileId()), this.mType, Boolean.valueOf(z)});
                        this.mEventContext.getBlackboard().post("data://bixby_command_done", new Object[]{"ai_confirm"});
                        AwesomeIntelligenceServiceManager.getInstance().startActivity(this.mEventContext.getActivity());
                        return;
                    } else if (!SamsungAccountManager.isSamsungAiSupportAccount(this.mEventContext.getContext())) {
                        this.mEventContext.getBlackboard().post("data://bixby_command_done", new Object[]{"kids_account"});
                        AwesomeIntelligenceServiceManager.getInstance().showRestrictedAccountToast(this.mEventContext.getContext());
                        return;
                    }
                }
                executeItem(z, currentItem);
            } else {
                Utils.showToast(this.mEventContext.getContext(), (int) R.string.operation_failed_low_storage, 1);
                Log.w(this.TAG, "Couldn't execute by low storage");
            }
        } else {
            Log.w(this.TAG, "ai edits selected failed: input blocked");
        }
    }

    public void recycle() {
    }
}
