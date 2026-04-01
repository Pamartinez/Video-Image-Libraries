package com.samsung.android.gallery.app.ui.viewer2.aiedit.items;

import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.aiedit.AiEditType;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RemasterMoireAiEdit extends AbsRemasterAiEdit {
    public RemasterMoireAiEdit(EventContext eventContext, ActionInvoker actionInvoker) {
        super(eventContext, actionInvoker, AiEditType.RemoveMoire);
    }

    public void execute(MediaItem mediaItem, boolean z) {
        super.lambda$executeItem$0(mediaItem, z);
        executeOnDemandRemaster(mediaItem, 9, z);
    }

    public int getDetectionType() {
        return 9;
    }

    public String getEstimatorText() {
        return "MOIRE";
    }

    public boolean support(MediaItem mediaItem) {
        if (!supportImageSize(mediaItem) || !supportFormat(mediaItem)) {
            return false;
        }
        return true;
    }
}
