package com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.page;

import android.graphics.RectF;
import android.view.View;
import android.widget.TextView;
import com.samsung.android.gallery.app.ui.viewholders.ImageViewHolder;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.module.story.FontTypefaceUtils;
import com.samsung.android.gallery.module.story.StoryHelper;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.RectUtils;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RelatedViewHolder extends ImageViewHolder {
    private View mImageContainer;
    private TextView mSubTitleView;
    private TextView mTitleView;

    public RelatedViewHolder(View view, int i2) {
        super(view, i2);
    }

    private RectF getCropRect(MediaItem mediaItem) {
        if (!StoryHelper.isVideoItem(mediaItem)) {
            return RectUtils.stringToRectF(MediaItemStory.getStoryCoverRectRatio(mediaItem));
        }
        return null;
    }

    public void bind(MediaItem mediaItem) {
        super.bind(mediaItem);
        this.mTitleView.setText(mediaItem.getTitle());
        this.mSubTitleView.setText(MediaItemStory.getStoryTimeDuration(mediaItem));
        if (PreferenceFeatures.OneUi5x.SUPPORT_STORIES_TITLE_ALIGN) {
            this.mTitleView.setTypeface(FontTypefaceUtils.getTextFont(MediaItemStory.getStorySaType(mediaItem), mediaItem.getAlbumID(), StringCompat.isKorean(mediaItem.getTitle())));
        }
    }

    public void bindView(View view) {
        super.bindView(view);
        this.mTitleView = (TextView) view.findViewById(R.id.title);
        this.mSubTitleView = (TextView) view.findViewById(R.id.duration);
        this.mImageContainer = view.findViewById(R.id.thumbnail_container);
    }

    public TextView getSubTitleView() {
        return this.mSubTitleView;
    }

    public TextView getTitleView() {
        return this.mTitleView;
    }

    public ListViewHolder setThumbnailShape(int i2, float f) {
        ViewUtils.setViewShape(this.mImageContainer, i2, f);
        return this;
    }

    public void setViewMatrix() {
        int i2;
        MediaItem mediaItem = this.mMediaItem;
        if (mediaItem != null) {
            RectF cropRect = getCropRect(mediaItem);
            if (this.mMediaItem.isVideo() || this.mMediaItem.isBroken()) {
                i2 = 0;
            } else {
                i2 = this.mMediaItem.getOrientation();
            }
            setViewMatrix(cropRect, i2, RectUtils.isStretchable(cropRect, this.mMediaItem.getWidth(), this.mMediaItem.getHeight(), this.mMediaItem.getOrientation()));
        }
    }
}
