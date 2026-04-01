package com.samsung.android.gallery.app.ui.viewer2.aiedit.items;

import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.externals.PlayDualShotCmd;
import com.samsung.android.gallery.app.controller.externals.StartSimplePhotoEditorCmd;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.module.aiedit.AiEditType;
import com.samsung.android.gallery.module.aiedit.PortraitEffectUtil;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.utils.Features;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class RemasterPortraitAiEdit extends AbsRemasterAiEdit {
    public RemasterPortraitAiEdit(EventContext eventContext, ActionInvoker actionInvoker) {
        super(eventContext, actionInvoker, AiEditType.PortraitEffect);
    }

    public void execute(MediaItem mediaItem, boolean z) {
        super.lambda$executeItem$0(mediaItem, z);
        if (Features.isEnabled(Features.SUPPORT_PHOTO_EDITOR_PORTRAIT_EFFECT)) {
            this.mActionInvoker.invoke(ViewerAction.PREPARE_EDITOR, mediaItem);
            new StartSimplePhotoEditorCmd().execute(this.mEventContext, mediaItem, StartSimplePhotoEditorCmd.PhotoEditorMode.spe_bgblur);
            return;
        }
        new PlayDualShotCmd().execute(this.mEventContext, mediaItem, Integer.valueOf(PlayDualShotCmd.MORE_OPTION));
    }

    public int getDetectionType() {
        return 30;
    }

    public String getEstimatorText() {
        return "PORTRAIT";
    }

    public AnalyticsEventId getEventId() {
        return AnalyticsEventId.EVENT_AI_EDIT_PORTRAIT;
    }

    public ExecuteTriggerType getExecuteTriggerType() {
        return ExecuteTriggerType.COLLAPSE_WITH_TRANSPARENT_PROGRESS;
    }

    public boolean isNeedCloudOnlyDownload() {
        return true;
    }

    public boolean support(MediaItem mediaItem) {
        return PortraitEffectUtil.supportPortraitEffect(mediaItem);
    }
}
