package com.samsung.android.gallery.app.ui.list.timeline;

import android.os.Bundle;
import com.samsung.android.gallery.app.ui.list.pictures.IPicturesView;
import com.samsung.android.gallery.app.ui.list.pictures.TimelineLayoutManager;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface ITimelineView extends IPicturesView {
    void checkAndUpdateTipCard(boolean z);

    TimelineLayoutManager getLayoutManager();

    void onCloudMediaSyncStatusChanged();

    void onCloudSyncOnOffChanged();

    void operateSimilarPhoto();

    void startScrollAndShrink(Object obj, Bundle bundle);

    void updateSmartSwitchView();
}
