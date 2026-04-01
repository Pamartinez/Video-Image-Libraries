package com.samsung.android.gallery.app.ui.list.stories.viewholder;

import android.graphics.RectF;
import android.view.View;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.RectUtils;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.previewable.PreviewFactory;
import com.samsung.android.gallery.widget.story.DebugHelper;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PinDataViewHolder extends PinViewHolder {
    private View mGradientView;
    private float mRoundRadius;
    private View mThumbnailContainer;
    private View mTitleContainer;

    public PinDataViewHolder(View view, int i2, float f) {
        super(view, i2);
        this.mRoundRadius = f;
    }

    private RectF getCropRect(MediaItem mediaItem) {
        if (!PreviewFactory.isPreviewableFormat(mediaItem)) {
            return RectUtils.stringToRectF(MediaItemStory.getStoryCoverRectRatio(mediaItem));
        }
        return null;
    }

    public void bind(MediaItem mediaItem) {
        super.bind(mediaItem);
        float f = this.mRoundRadius;
        if (f > 0.0f) {
            setThumbnailShape(1, f);
        }
        DebugHelper.showInfoView((ListViewHolder) this, (int) R.id.thumbnail_container, false);
    }

    public void bindView(View view) {
        super.bindView(view);
        this.mThumbnailContainer = view.findViewById(R.id.thumbnail_container);
        this.mTitleContainer = view.findViewById(R.id.title_container);
        this.mGradientView = view.findViewById(R.id.gradient_view);
    }

    public View getDecoView(int i2) {
        if (i2 == 57) {
            return this.mTitleContainer;
        }
        if (i2 == 32) {
            return this.mGradientView;
        }
        return super.getDecoView(i2);
    }

    public void preparePinItemAnim() {
        if (!this.mPinAnimPrepared) {
            this.mPinAnimPrepared = true;
            ViewUtils.setAlpha(getRootView(), 0.0f);
            ViewUtils.setScale(getRootView(), 0.5f, 0.5f);
        }
    }

    public void recycle() {
        super.recycle();
    }

    public void resetPinItemAnim() {
        if (this.mPinAnimPrepared) {
            this.mPinAnimPrepared = false;
            ViewUtils.setAlpha(getRootView(), 1.0f);
            ViewUtils.setScale(getRootView(), 1.0f, 1.0f);
        }
    }

    public ListViewHolder setThumbnailShape(int i2, float f) {
        ViewUtils.setViewShape(this.mThumbnailContainer, i2, f);
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

    public void startPinItemAnim() {
        if (this.mPinAnimPrepared) {
            getRootView().animate().alpha(1.0f).scaleY(1.0f).scaleX(1.0f).setDuration(200).start();
            String str = this.TAG;
            Log.d(str, "startPinItemAnim=Data#" + getAdapterPosition());
        }
    }
}
