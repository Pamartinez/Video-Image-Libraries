package com.samsung.android.gallery.app.ui.list.stories.highlight.viewpager;

import android.view.View;
import android.view.ViewGroup;
import c0.C0086a;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.module.story.StoryHelper;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class ViewPagerHolderFactory {
    public static ViewPagerHolder createViewPagerHolder(ViewGroup viewGroup, int i2) {
        View d = C0086a.d(viewGroup, R.layout.recycler_item_story_highlight_layout, viewGroup, false);
        if (i2 == 1) {
            return new ViewPagerVideoHolder(d, i2);
        }
        if (i2 == 2) {
            return new ViewPagerAiEditHolder(d, i2);
        }
        return new ViewPagerHolder(d, i2);
    }

    public static int getType(MediaItem mediaItem, boolean z) {
        if (z) {
            return StoryHelper.isGeneralSlideshowVideoFormat(mediaItem) ? 1 : 0;
        }
        if (StoryHelper.isVideoItem(mediaItem)) {
            return 1;
        }
        if (MediaItemStory.isAiEditEffect(mediaItem)) {
            return 2;
        }
        return 0;
    }
}
