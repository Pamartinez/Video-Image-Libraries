package com.samsung.android.gallery.app.ui.list.stories.highlight.viewpager;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AIEditImageBinder implements ILargeImage {
    private final ImageView mImage;
    private boolean mLoaded;
    private final MediaItem mMediaItem;
    private final MediaItem mOriginMediaItem;
    private final ILargeImage mOriginVh;

    public AIEditImageBinder(ILargeImage iLargeImage) {
        this.mImage = (ImageView) iLargeImage.getRootView().findViewById(R.id.ai_edit_thumbnail);
        this.mOriginVh = iLargeImage;
        MediaItem mediaItem = iLargeImage.getMediaItem();
        this.mOriginMediaItem = mediaItem;
        this.mMediaItem = (MediaItem) MediaItemStory.getEffectItem(mediaItem);
    }

    public void bindOriginImage(Bitmap bitmap) {
        this.mOriginVh.onAiEditImageLoaded(this.mOriginMediaItem, bitmap);
        this.mLoaded = true;
    }

    public MediaItem getMediaItem() {
        return this.mMediaItem;
    }

    public View getRootView() {
        return this.mOriginVh.getRootView();
    }

    public boolean isOriginImageLoaded() {
        return this.mLoaded;
    }
}
