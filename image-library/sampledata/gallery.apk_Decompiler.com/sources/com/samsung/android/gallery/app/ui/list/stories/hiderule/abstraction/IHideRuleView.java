package com.samsung.android.gallery.app.ui.list.stories.hiderule.abstraction;

import com.samsung.android.gallery.app.ui.list.abstraction.IBaseListView;
import com.samsung.android.gallery.module.data.MediaItem;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface IHideRuleView extends IBaseListView {
    void launchDatePicker();

    void launchHideSceneSelection(int i2, MediaItem mediaItem);

    void removeDate(int i2, MediaItem mediaItem);
}
