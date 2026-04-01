package com.samsung.android.gallery.app.ui.viewer2.aiedit.items;

import android.graphics.Bitmap;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.internals.LongExposureCmd;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.module.aiedit.AiEditType;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.longexposure.LongExposureHelper;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.utils.Features;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class LongExposureAiEdit extends AiEditItem {
    public static final boolean SUPPORT = Features.isEnabled(Features.SUPPORT_LONG_EXPOSURE_EFFECT);
    private boolean mIsMotionPhoto;

    public LongExposureAiEdit(EventContext eventContext, ActionInvoker actionInvoker) {
        super(eventContext, actionInvoker, AiEditType.LongExposure);
    }

    public void execute(MediaItem mediaItem, boolean z) {
        super.lambda$executeItem$0(mediaItem, z);
        this.mActionInvoker.invoke(ViewerAction.REQUEST_VIDEO_STOP, new Object[0]);
        new LongExposureCmd().execute(this.mEventContext, mediaItem, Boolean.valueOf(z));
    }

    public AnalyticsEventId getEventId() {
        if (this.mIsMotionPhoto) {
            return AnalyticsEventId.EVENT_AI_EDIT_LONG_EXPOSURE_MOTION_PHOTO;
        }
        return AnalyticsEventId.EVENT_AI_EDIT_LONG_EXPOSURE_VIDEO;
    }

    public ExecuteTriggerType getExecuteTriggerType() {
        return ExecuteTriggerType.COLLAPSE_ONLY;
    }

    public boolean isNeedCloudOnlyDownload() {
        return true;
    }

    public List<AiEditItem> load(MediaItem mediaItem, Bitmap bitmap) {
        if (!SUPPORT || !LongExposureHelper.supportLongExposure(mediaItem)) {
            return null;
        }
        this.mIsMotionPhoto = mediaItem.isMotionPhoto();
        return List.of(this);
    }

    public void recycle() {
        this.mIsMotionPhoto = false;
    }

    public boolean supportAutoSave() {
        return true;
    }
}
