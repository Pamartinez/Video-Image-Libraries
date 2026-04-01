package com.samsung.android.gallery.app.ui.viewer2.aiedit.items;

import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemSuggest;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.utils.PreferenceCache;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RemasterGifAiEdit extends AbsRemasterAiEdit {
    public RemasterGifAiEdit(EventContext eventContext, ActionInvoker actionInvoker) {
        super(eventContext, actionInvoker);
    }

    private void updatePreferenceForBadge() {
        if (PreferenceCache.RemasterGifNewBadge.compareAndSet(true, false)) {
            this.mActionInvoker.invoke(ViewerAction.UPDATE_MORE_OPTION_NEW_BADGE, new Object[0]);
        }
    }

    public void execute(MediaItem mediaItem, boolean z) {
        super.lambda$executeItem$0(mediaItem, z);
        updatePreferenceForBadge();
        executeOnDemandRemaster(mediaItem, MediaItemSuggest.getRevitalizedType(mediaItem), z);
    }

    public int getDetectionType() {
        return 0;
    }

    public String getEstimatorText() {
        return "GIF";
    }

    public AnalyticsEventId getEventId() {
        return AnalyticsEventId.EVENT_AI_EDIT_REMASTER_GIF;
    }

    public boolean support(MediaItem mediaItem) {
        if (mediaItem.isCloudOnly() || mediaItem.isRemasterSaved() || !mediaItem.isGif()) {
            return false;
        }
        return true;
    }
}
