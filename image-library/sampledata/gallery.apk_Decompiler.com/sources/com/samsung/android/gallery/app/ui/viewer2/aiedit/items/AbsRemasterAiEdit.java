package com.samsung.android.gallery.app.ui.viewer2.aiedit.items;

import android.graphics.Bitmap;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.internals.OnDemandRemasterViewerCmd;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.module.aiedit.AiEditType;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import h7.C0465a;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class AbsRemasterAiEdit extends AiEditItem {
    static final int SUPPORT_MAX_PIXELS;

    static {
        int i2;
        if (PreferenceFeatures.OneUi8x.IS_ONE_UI_85) {
            i2 = 24000000;
        } else {
            i2 = 16000000;
        }
        SUPPORT_MAX_PIXELS = i2;
    }

    public AbsRemasterAiEdit(EventContext eventContext, ActionInvoker actionInvoker) {
        super(eventContext, actionInvoker, AiEditType.Remaster);
        this.mCheckLowStorage = true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$invokeRemasterSelected$0() {
        this.mActionInvoker.invoke(ViewerAction.REMASTER_VIEW_SELECTED, Boolean.TRUE);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$invokeRemasterSelected$1() {
        ActionInvoker actionInvoker = this.mActionInvoker;
        ViewerAction viewerAction = ViewerAction.REQUEST_DETAILS_SHOW_OR_HIDE;
        Boolean bool = Boolean.FALSE;
        actionInvoker.invoke(viewerAction, bool, bool);
    }

    public final void executeOnDemandRemaster(MediaItem mediaItem, long j2, boolean z) {
        new OnDemandRemasterViewerCmd().execute(this.mEventContext, mediaItem, Long.valueOf(j2), new C0465a(this, 0), Boolean.valueOf(z));
    }

    public int getDetectionType() {
        return 0;
    }

    public abstract String getEstimatorText();

    public AiEditItem getLoadableItem(AiEditType aiEditType) {
        return null;
    }

    public final void invokeRemasterSelected() {
        ThreadUtil.runOnUiThread(new C0465a(this, 1));
        ThreadUtil.postOnUiThreadDelayed(new C0465a(this, 2), 150);
    }

    public List<AiEditItem> load(MediaItem mediaItem, Bitmap bitmap) {
        return null;
    }

    public abstract boolean support(MediaItem mediaItem);

    public boolean supportAutoSave() {
        return true;
    }

    public boolean supportFormat(MediaItem mediaItem) {
        if (mediaItem.isCloudOnly()) {
            return false;
        }
        if (mediaItem.isJpeg() || mediaItem.isPng() || mediaItem.isHeif() || mediaItem.isBmp()) {
            return true;
        }
        if (!mediaItem.isWebp() || !mediaItem.isSingleFrameMovie()) {
            return false;
        }
        return true;
    }

    public boolean supportImageSize(MediaItem mediaItem) {
        if (mediaItem.getHeight() * mediaItem.getWidth() <= SUPPORT_MAX_PIXELS) {
            return true;
        }
        return false;
    }

    public AbsRemasterAiEdit(EventContext eventContext, ActionInvoker actionInvoker, AiEditType aiEditType) {
        super(eventContext, actionInvoker, aiEditType);
        this.mCheckLowStorage = true;
    }
}
