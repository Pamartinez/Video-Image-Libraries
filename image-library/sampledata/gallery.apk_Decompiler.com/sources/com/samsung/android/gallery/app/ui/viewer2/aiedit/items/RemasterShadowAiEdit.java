package com.samsung.android.gallery.app.ui.viewer2.aiedit.items;

import android.net.Uri;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.externals.StartSimplePhotoEditorCmd;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.module.aiedit.AiEditType;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.editor.ModeInfo;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.samsung.android.gallery.support.utils.Features;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RemasterShadowAiEdit extends AbsRemasterAiEdit {
    public RemasterShadowAiEdit(EventContext eventContext, ActionInvoker actionInvoker) {
        super(eventContext, actionInvoker, AiEditType.EraseShadows);
    }

    public void execute(MediaItem mediaItem, boolean z) {
        super.lambda$executeItem$0(mediaItem, z);
        this.mActionInvoker.invoke(ViewerAction.PREPARE_EDITOR, mediaItem);
        new StartSimplePhotoEditorCmd().execute(this.mEventContext, mediaItem, StartSimplePhotoEditorCmd.PhotoEditorMode.spe_objecteraser, null, Boolean.FALSE, new ModeInfo(2, (Uri) null, z));
    }

    public int getDetectionType() {
        return 13;
    }

    public String getEstimatorText() {
        return "SHADOW";
    }

    public ExecuteTriggerType getExecuteTriggerType() {
        return ExecuteTriggerType.COLLAPSE_WITH_TRANSPARENT_PROGRESS;
    }

    public boolean support(MediaItem mediaItem) {
        if (!Features.isEnabled(Features.SUPPORT_OBJECT_ERASER) || !supportImageSize(mediaItem) || !supportFormat(mediaItem)) {
            return false;
        }
        return true;
    }
}
