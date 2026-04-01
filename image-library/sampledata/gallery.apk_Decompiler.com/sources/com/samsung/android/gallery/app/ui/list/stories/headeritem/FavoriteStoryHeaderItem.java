package com.samsung.android.gallery.app.ui.list.stories.headeritem;

import com.samsung.android.gallery.app.ui.abstraction.IMvpBaseView;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FavoriteStoryHeaderItem extends StoryHeaderItem2 {
    public FavoriteStoryHeaderItem(IMvpBaseView iMvpBaseView) {
        super(iMvpBaseView);
    }

    public String getLocationKey() {
        return "location://stories/favorite";
    }
}
