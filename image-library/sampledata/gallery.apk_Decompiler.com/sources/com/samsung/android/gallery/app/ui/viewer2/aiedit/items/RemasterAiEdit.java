package com.samsung.android.gallery.app.ui.viewer2.aiedit.items;

import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.aiedit.RemasterDetector;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemSuggest;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.library.sef.SefInfo;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RemasterAiEdit extends AbsRemasterAiEdit {
    public RemasterAiEdit(EventContext eventContext, ActionInvoker actionInvoker) {
        super(eventContext, actionInvoker);
    }

    private boolean isRemasterSaved(MediaItem mediaItem) {
        if (RemasterDetector.SUPPORT) {
            return SeApiCompat.getSefString(mediaItem.getPath(), SefInfo.REMASTER_INFO.keyName).contains("RemasterDirector");
        }
        return mediaItem.isRemasterSaved();
    }

    public void execute(MediaItem mediaItem, boolean z) {
        super.lambda$executeItem$0(mediaItem, z);
        executeOnDemandRemaster(mediaItem, MediaItemSuggest.getRevitalizedType(mediaItem), z);
    }

    public int getDetectionType() {
        return 0;
    }

    public String getEstimatorText() {
        return "END_TO_END";
    }

    public boolean support(MediaItem mediaItem) {
        if (!supportFormat(mediaItem) || isRemasterSaved(mediaItem)) {
            return false;
        }
        return true;
    }
}
