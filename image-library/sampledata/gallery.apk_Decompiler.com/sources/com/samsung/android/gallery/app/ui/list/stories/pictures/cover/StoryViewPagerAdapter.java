package com.samsung.android.gallery.app.ui.list.stories.pictures.cover;

import android.view.View;
import com.samsung.android.gallery.app.ui.list.stories.pictures.viewholder.StoryViewPagerHolder;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.widget.simpleslideshow.SimpleSlideShowAdapter;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StoryViewPagerAdapter extends SimpleSlideShowAdapter<StoryViewPagerHolder> {
    private MediaItem mCoverItem;

    private boolean isHeaderItem(int i2) {
        if (i2 == getEntryDataPosition()) {
            return true;
        }
        return false;
    }

    public MediaItem getCoverItem() {
        return this.mCoverItem;
    }

    public MediaItem getMediaItem(int i2) {
        MediaItem mediaItem;
        MediaItem mediaItem2 = super.getMediaItem(i2);
        if (!isHeaderItem(i2) || (mediaItem = this.mCoverItem) == null) {
            return mediaItem2;
        }
        return mediaItem;
    }

    public ThumbKind getThumbKind(MediaItem mediaItem) {
        if (!mediaItem.isHeif()) {
            return ThumbKind.STORY_COVER;
        }
        return ThumbKind.MEDIUM_KIND;
    }

    public void setCoverItem(MediaItem mediaItem) {
        this.mCoverItem = mediaItem;
    }

    public boolean supportFaceCircle() {
        return true;
    }

    public StoryViewPagerHolder createViewHolder(View view, int i2) {
        return new StoryViewPagerHolder(view, i2);
    }

    public void onPreBindViewHolder(StoryViewPagerHolder storyViewPagerHolder, MediaItem mediaItem, int i2) {
        storyViewPagerHolder.setUseCustomCoverRatio(isHeaderItem(getDataPosition(i2)));
    }
}
