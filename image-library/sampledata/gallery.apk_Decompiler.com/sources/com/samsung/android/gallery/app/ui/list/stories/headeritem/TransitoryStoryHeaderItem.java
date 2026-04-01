package com.samsung.android.gallery.app.ui.list.stories.headeritem;

import com.samsung.android.gallery.app.ui.abstraction.IMvpBaseView;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class TransitoryStoryHeaderItem extends StoryHeaderItem2 {
    public TransitoryStoryHeaderItem(IMvpBaseView iMvpBaseView) {
        super(iMvpBaseView);
    }

    public String getLocationKey() {
        return "location://stories/category/transitory";
    }

    public MediaData openMediaData(MediaData mediaData) {
        if (PreferenceFeatures.OneUi7x.STORY_ONE_UI_70 || mediaData.getChildMediaData((String) null) == null) {
            return super.openMediaData(mediaData);
        }
        return mediaData.getChildMediaData((String) null).open("location://stories/category/transitory");
    }
}
