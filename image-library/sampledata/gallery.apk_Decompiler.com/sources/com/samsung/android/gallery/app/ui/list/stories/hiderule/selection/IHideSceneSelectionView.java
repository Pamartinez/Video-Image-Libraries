package com.samsung.android.gallery.app.ui.list.stories.hiderule.selection;

import com.samsung.android.gallery.app.ui.list.pictures.IPicturesView;
import com.samsung.android.gallery.module.data.MediaItem;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface IHideSceneSelectionView extends IPicturesView {
    void finishSelf();

    MediaItem[] getHideItems();

    int getHideRuleId(String str);

    MediaItem[] getUnHideItems();
}
