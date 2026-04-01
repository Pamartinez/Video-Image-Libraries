package com.samsung.android.gallery.app.ui.viewer2.aiedit.items;

import android.graphics.Bitmap;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.externals.PlayDualShotCmd;
import com.samsung.android.gallery.app.controller.externals.StartSimplePhotoEditorCmd;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.module.abstraction.PortraitType;
import com.samsung.android.gallery.module.aiedit.AiEditType;
import com.samsung.android.gallery.module.aiedit.PortraitDetector;
import com.samsung.android.gallery.module.aiedit.PortraitEffectUtil;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.samsung.android.gallery.support.utils.Features;
import java.util.ArrayList;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PortraitEffectAiEdit extends AiEditItem {
    private static final boolean SUPPORT_PORTRAIT_DETECTION = PortraitDetector.SUPPORT;

    public PortraitEffectAiEdit(EventContext eventContext, ActionInvoker actionInvoker) {
        super(eventContext, actionInvoker, AiEditType.PortraitEffect);
    }

    private List<PortraitType> detect(MediaItem mediaItem, Bitmap bitmap) {
        return PortraitDetector.getInstance().detect(mediaItem, bitmap);
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

    public ExecuteTriggerType getExecuteTriggerType() {
        return ExecuteTriggerType.COLLAPSE_WITH_TRANSPARENT_PROGRESS;
    }

    public boolean isNeedCloudOnlyDownload() {
        return true;
    }

    public List<AiEditItem> load(MediaItem mediaItem, Bitmap bitmap) {
        List<PortraitType> detect;
        boolean supportPortraitEffect = PortraitEffectUtil.supportPortraitEffect(mediaItem);
        ArrayList arrayList = new ArrayList();
        if (SUPPORT_PORTRAIT_DETECTION) {
            if (supportPortraitEffect && bitmap != null && (detect = detect(mediaItem, bitmap)) != null && detect.contains(PortraitType.PORTRAIT)) {
                arrayList.add(this);
                return arrayList;
            }
        } else if (supportPortraitEffect) {
            arrayList.add(this);
        }
        return arrayList;
    }
}
