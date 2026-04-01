package com.samsung.android.gallery.app.ui.viewer2.aiedit.items;

import android.graphics.Bitmap;
import android.util.Size;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.internals.ViewLiveEffectCmd;
import com.samsung.android.gallery.module.aiedit.AiEditType;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.graphics.BitmapOptions;
import com.samsung.android.gallery.module.graphics.BitmapSizeHolder;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.samsung.android.gallery.support.utils.Features;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class LiveEffectAiEdit extends AbsRemasterAiEdit {
    public static final boolean SUPPORT = Features.isEnabled(Features.SUPPORT_LIVE_EFFECT);

    public LiveEffectAiEdit(EventContext eventContext, ActionInvoker actionInvoker) {
        super(eventContext, actionInvoker, AiEditType.LiveEffect);
    }

    private void executeInternal(MediaItem mediaItem, boolean z, boolean z3) {
        ViewLiveEffectCmd viewLiveEffectCmd = new ViewLiveEffectCmd();
        if (z3) {
            viewLiveEffectCmd.addParameter("app_transition_view", this.mEventContext.getEventContextData("live_effect_shared_view"));
        }
        viewLiveEffectCmd.execute(this.mEventContext, mediaItem, Boolean.valueOf(z), Boolean.valueOf(z3));
    }

    private Size getBitmapSize(MediaItem mediaItem) {
        Bitmap bitmap = (Bitmap) this.mEventContext.getBlackboard().read(MediaItemUtil.getViewerBitmapKey(mediaItem));
        if (bitmap != null) {
            return new Size(bitmap.getWidth(), bitmap.getHeight());
        }
        int computeInSampleSize = BitmapOptions.computeInSampleSize(mediaItem.getWidth(), mediaItem.getHeight(), BitmapSizeHolder.size());
        return new Size(Math.floorDiv(mediaItem.getWidth(), computeInSampleSize), Math.floorDiv(mediaItem.getHeight(), computeInSampleSize));
    }

    private boolean isUnsupportedShotType(MediaItem mediaItem) {
        if (mediaItem.is360Image() || mediaItem.isPanoramaShot() || mediaItem.isBurstShot() || mediaItem.isSingleTakenShot() || mediaItem.isLiveEffect() || mediaItem.isDrm() || mediaItem.isMotionPhoto() || mediaItem.isScreenShot()) {
            return true;
        }
        return false;
    }

    private boolean supportBitmapSize(MediaItem mediaItem) {
        Size bitmapSize = getBitmapSize(mediaItem);
        if (Math.min(bitmapSize.getWidth(), bitmapSize.getHeight()) >= 500) {
            return true;
        }
        return false;
    }

    private boolean supportSize(MediaItem mediaItem) {
        if (Math.min(mediaItem.getWidth(), mediaItem.getHeight()) < 500 || !supportBitmapSize(mediaItem)) {
            return false;
        }
        return true;
    }

    public void execute(MediaItem mediaItem, boolean z) {
        super.lambda$executeItem$0(mediaItem, z);
        executeInternal(mediaItem, z, true);
    }

    public int getDetectionType() {
        return 25;
    }

    public String getEstimatorText() {
        return "O3DP";
    }

    public ExecuteTriggerType getExecuteTriggerType() {
        return ExecuteTriggerType.COLLAPSE_WITH_TRANSPARENT_PROGRESS;
    }

    public boolean isNeedCloudOnlyDownload() {
        return true;
    }

    public boolean support(MediaItem mediaItem) {
        if (!SUPPORT || mediaItem.isCloudOnly() || isUnsupportedShotType(mediaItem) || !supportFormat(mediaItem) || !supportSize(mediaItem)) {
            return false;
        }
        return true;
    }

    public boolean supportFormat(MediaItem mediaItem) {
        if (mediaItem.isHeif() || mediaItem.isJpeg()) {
            return true;
        }
        return false;
    }
}
