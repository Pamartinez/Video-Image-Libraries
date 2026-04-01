package com.samsung.android.gallery.app.ui.viewer2.aiedit.items;

import android.graphics.Bitmap;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.externals.StartSimplePhotoEditorCmd;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.module.aiedit.AiEditType;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.samsung.android.gallery.support.utils.Features;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ObjectEraserAiEdit extends AiEditItem {
    public ObjectEraserAiEdit(EventContext eventContext, ActionInvoker actionInvoker) {
        super(eventContext, actionInvoker, AiEditType.ObjectEraser);
    }

    private boolean supportedObjectEraser(MediaItem mediaItem) {
        if (!Features.isEnabled(Features.SUPPORT_OBJECT_ERASER) || Features.isEnabled(Features.IS_REPAIR_MODE) || !supportImageSize(mediaItem)) {
            return false;
        }
        if (mediaItem.isJpeg() || mediaItem.isDng() || mediaItem.isPng() || mediaItem.isHeif() || mediaItem.isBmp()) {
            return true;
        }
        if (!mediaItem.isWebp() || !mediaItem.isSingleFrameMovie()) {
            return false;
        }
        return true;
    }

    public void execute(MediaItem mediaItem, boolean z) {
        super.lambda$executeItem$0(mediaItem, z);
        this.mActionInvoker.invoke(ViewerAction.PREPARE_EDITOR, mediaItem);
        new StartSimplePhotoEditorCmd().execute(this.mEventContext, mediaItem, StartSimplePhotoEditorCmd.PhotoEditorMode.spe_objecteraser, null, Boolean.TRUE);
    }

    public ExecuteTriggerType getExecuteTriggerType() {
        return ExecuteTriggerType.COLLAPSE_WITH_TRANSPARENT_PROGRESS;
    }

    public boolean isNeedCloudOnlyDownload() {
        return true;
    }

    public List<AiEditItem> load(MediaItem mediaItem, Bitmap bitmap) {
        if (supportedObjectEraser(mediaItem)) {
            return List.of(this);
        }
        return null;
    }

    public boolean supportImageSize(MediaItem mediaItem) {
        if (mediaItem.getHeight() * mediaItem.getWidth() <= 16000000) {
            return true;
        }
        return false;
    }
}
