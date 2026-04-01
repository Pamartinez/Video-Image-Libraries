package com.samsung.android.gallery.app.ui.list.dragdrop;

import android.view.DragEvent;
import com.samsung.android.gallery.app.controller.creature.MergeCreatureMultipleCmd;
import com.samsung.android.gallery.app.ui.list.abstraction.IBaseListView;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.logger.AnalyticsLogger;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CreatureDragAndDrop extends TwoHandedDragAndDrop {
    private boolean isEmptySpaceDrop(MediaItem mediaItem) {
        if (mediaItem == null) {
            return true;
        }
        return false;
    }

    public boolean handleDrop(IBaseListView iBaseListView, DragEvent dragEvent, MediaItem mediaItem) {
        if (isEmptySpaceDrop(mediaItem)) {
            return false;
        }
        new MergeCreatureMultipleCmd().execute(iBaseListView.getPresenter(), mediaItem);
        AnalyticsLogger.getInstance().postLog(AnalyticsScreenId.SCREEN_VISUAL_SEARCH_VIEW_PEOPLE_CATEGORY.toString(), AnalyticsEventId.EVENT_SEARCH_PEOPLE_AND_PETS_TOP5_INTERACTION_FOR_MERGE.toString());
        return true;
    }

    public boolean isValidDropEvent(DragEvent dragEvent) {
        return isDragStartedFromGallery(dragEvent);
    }
}
