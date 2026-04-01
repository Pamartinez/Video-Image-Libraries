package com.samsung.android.gallery.app.ui.list.stories.pictures.viewholder;

import android.graphics.RectF;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.app.ui.viewholders.PreviewViewHolder;
import com.samsung.android.gallery.module.abstraction.VideoPropData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.support.utils.RectUtils;
import com.samsung.android.gallery.support.utils.TimeUtil;
import com.samsung.android.gallery.widget.previewable.PreviewFactory;
import com.samsung.android.gallery.widget.utils.ViewMatrixUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StoryViewPagerHolder extends PreviewViewHolder {
    ViewGroup mCoverView;
    public boolean mIsCoverRatio;

    public StoryViewPagerHolder(View view, int i2) {
        super(view, i2);
    }

    public void bind(MediaItem mediaItem) {
        int i2;
        Object obj;
        super.bind(mediaItem);
        if (mediaItem != null) {
            ImageView image = getImage();
            StringBuilder sb2 = new StringBuilder();
            sb2.append(TimeUtil.getLocalizedDateTime(mediaItem.getDateTaken()));
            sb2.append(ArcCommonLog.TAG_COMMA);
            if (mediaItem.isImage()) {
                i2 = R.string.image;
            } else {
                i2 = R.string.video;
            }
            sb2.append(getString(i2));
            if (mediaItem.isImage()) {
                obj = "";
            } else {
                obj = Integer.valueOf(VideoPropData.getVideoDuration(mediaItem));
            }
            sb2.append(obj);
            image.setContentDescription(sb2.toString());
        }
    }

    public void bindPreviewView(View view) {
        this.mCoverView.setVisibility(0);
        this.mCoverView.addView(view);
    }

    public void bindView(View view) {
        super.bindView(view);
        this.mCoverView = (ViewGroup) view.findViewById(R.id.thumbnail_preview_layout);
    }

    public boolean canLooping(int i2) {
        return true;
    }

    public RectF getCropRect(MediaItem mediaItem) {
        if (PreviewFactory.isPreviewableFormat(mediaItem) || !this.mIsCoverRatio) {
            return null;
        }
        return RectUtils.stringToRectF(MediaItemStory.getStoryCoverRectRatio(mediaItem));
    }

    public int getPreviewDuration() {
        return 5000;
    }

    public int getPreviewThumbnailOffsetMs() {
        return 0;
    }

    public void recycle() {
        super.recycle();
        this.mIsCoverRatio = false;
        ViewUtils.setVisibility(this.mCoverView, 8);
    }

    public void removePreviewView(View view) {
        super.removePreviewView(view);
        ViewUtils.setVisibility(this.mCoverView, 8);
    }

    public void setUseCustomCoverRatio(boolean z) {
        this.mIsCoverRatio = z;
    }

    public void setViewMatrix() {
        setViewMatrix(this.mMediaItem, getImage());
    }

    private void setViewMatrix(MediaItem mediaItem, ImageView imageView) {
        if (mediaItem != null) {
            RectF cropRect = getCropRect(mediaItem);
            ViewMatrixUtils.setViewMatrix(imageView, mediaItem, cropRect, (mediaItem.isVideo() || mediaItem.isBroken()) ? 0 : mediaItem.getOrientation(), RectUtils.isStretchable(cropRect, mediaItem.getWidth(), mediaItem.getHeight(), mediaItem.getOrientation()));
        }
    }
}
