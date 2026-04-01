package com.samsung.android.gallery.app.ui.list.stories.pictures.cover;

import com.samsung.android.gallery.app.ui.list.stories.pictures.abstraction.IStoryPicturesView;
import com.samsung.android.gallery.app.ui.list.stories.pictures.abstraction.IViewCache;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class CoverFactory {
    public static StoryCover<IStoryPicturesView> create(IStoryPicturesView iStoryPicturesView, IViewCache iViewCache) {
        if (PreferenceFeatures.OneUi40.SUPPORT_STORY_COVER_SLIDESHOW_V2) {
            return new StoryViewPagerCover(iStoryPicturesView, iViewCache);
        }
        if (PreferenceFeatures.OneUi40.SUPPORT_STORY_COVER_SLIDESHOW) {
            return new StorySlideShowCover(iStoryPicturesView, iViewCache);
        }
        return new StoryCover<>(iStoryPicturesView, iViewCache);
    }
}
