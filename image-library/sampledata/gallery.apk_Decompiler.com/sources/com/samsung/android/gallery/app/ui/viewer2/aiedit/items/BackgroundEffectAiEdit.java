package com.samsung.android.gallery.app.ui.viewer2.aiedit.items;

import android.graphics.Bitmap;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.externals.PlayDualShotCmd;
import com.samsung.android.gallery.app.controller.externals.StartSimplePhotoEditorCmd;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.module.aiedit.AiEditType;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.media.DualPhotoManipulator2;
import com.samsung.android.gallery.module.nondestruction.NonDestructionManager;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.FileUtils;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class BackgroundEffectAiEdit extends AiEditItem {
    private static final boolean SUPPORT_EDITOR_BOKEH = Features.isEnabled(Features.SUPPORT_PHOTO_EDITOR_PORTRAIT_BACKGROUND_EFFECT);

    public BackgroundEffectAiEdit(EventContext eventContext, ActionInvoker actionInvoker) {
        super(eventContext, actionInvoker, AiEditType.BackgroundEffect);
    }

    public void execute(MediaItem mediaItem, boolean z) {
        super.lambda$executeItem$0(mediaItem, z);
        if (SUPPORT_EDITOR_BOKEH) {
            this.mActionInvoker.invoke(ViewerAction.PREPARE_EDITOR, mediaItem);
            new StartSimplePhotoEditorCmd().execute(this.mEventContext, mediaItem, StartSimplePhotoEditorCmd.PhotoEditorMode.spe_portrait_bg);
            return;
        }
        new PlayDualShotCmd().execute(this.mEventContext, mediaItem, Integer.valueOf(PlayDualShotCmd.SEF_VIEWER));
    }

    public ExecuteTriggerType getExecuteTriggerType() {
        return ExecuteTriggerType.COLLAPSE_WITH_TRANSPARENT_PROGRESS;
    }

    public boolean isNeedCloudOnlyDownload() {
        return true;
    }

    public List<AiEditItem> load(MediaItem mediaItem, Bitmap bitmap) {
        boolean z;
        Features features = Features.IS_JDM;
        if (Features.isEnabled(features) || mediaItem.hasSefFileTypes(3040)) {
            if (mediaItem.hasSefFileTypes(3040) || mediaItem.hasSefFileTypes(2880)) {
                z = true;
            } else {
                z = false;
            }
            if (!Features.isEnabled(features) || !z) {
                return null;
            }
            return List.of(this);
        } else if (!SUPPORT_EDITOR_BOKEH || !mediaItem.hasSefFileTypes(3552)) {
            String shotMode = mediaItem.getShotMode();
            if (shotMode == null) {
                return null;
            }
            if ("live_focus".equals(shotMode) || "Selfie focus".equals(shotMode) || ("Dual capture".equals(shotMode) && !DualPhotoManipulator2.isWideImage(mediaItem.getPath()))) {
                return List.of(this);
            }
            return null;
        } else {
            String path = NonDestructionManager.getInstance().getPath(mediaItem.getOriginalFileHash());
            if (path == null || !FileUtils.exists(path)) {
                return null;
            }
            return List.of(this);
        }
    }
}
