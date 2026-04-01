package com.samsung.android.gallery.app.ui.viewholders;

import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.samsung.android.gallery.app.ui.viewholders.PreviewViewHolder;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.support.library.sef.SingleTakeType;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.utils.ResourceUtil;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SingleTakenImageViewHolder extends PreviewViewHolder {
    float mCornerRadius;
    protected ImageView mImageViewBorder;
    protected TextView mShotTypeTextView;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class SingleTakenPreviewListener extends PreviewViewHolder.PreviewListener {
        public /* synthetic */ SingleTakenPreviewListener(SingleTakenImageViewHolder singleTakenImageViewHolder, int i2) {
            this();
        }

        private void setPlaybackRange() {
            MediaItem mediaItem = SingleTakenImageViewHolder.this.getMediaItem();
            if (SingleTakenImageViewHolder.this.mPreview != null && PreferenceFeatures.OneUi6x.SUPPORT_AI_EDIT_GROUP_PANEL && MediaItemUtil.isHighLightClip(mediaItem)) {
                SingleTakenImageViewHolder.this.mPreview.setPlaybackRange((int) (mediaItem.getVideoThumbStartTime() / 1000), (int) (MediaItemUtil.getHighLightClipEndTime(mediaItem) / 1000));
            }
        }

        public void onPreviewReady() {
            super.onPreviewReady();
            setPlaybackRange();
        }

        public boolean useSecMediaPlayer() {
            if (SingleTakenImageViewHolder.this.getViewType() == 2) {
                return true;
            }
            return super.useSecMediaPlayer();
        }

        private SingleTakenPreviewListener() {
            super();
        }
    }

    public SingleTakenImageViewHolder(View view, int i2) {
        super(view, i2);
        float dimension = view.getResources().getDimension(R.dimen.single_taken_thumbnail_corner_radius);
        this.mCornerRadius = dimension;
        setThumbnailShape(1, dimension);
    }

    private void setShotTypeTextView(MediaItem mediaItem) {
        if (mediaItem != null) {
            int viewType = getViewType();
            if (viewType == 0) {
                int stringResId = SingleTakeType.toStringResId(mediaItem.getSefFileTypes());
                if (stringResId > 0) {
                    ViewUtils.setText(this.mShotTypeTextView, stringResId);
                }
            } else if (viewType == 1) {
                String title = mediaItem.getTitle();
                if (!TextUtils.isEmpty(title)) {
                    ViewUtils.setText(this.mShotTypeTextView, title);
                }
            }
        }
    }

    public void addThumbnailBorder(Drawable drawable) {
        ImageView imageView = this.mImageViewBorder;
        if (imageView != null) {
            imageView.setForeground(drawable);
        }
    }

    public void bind(MediaItem mediaItem) {
        super.bind(mediaItem);
        if (isSlot1Enabled()) {
            inflateStorageFavoriteSlot(true).setPaddingRelative(0, 0, getContext().getResources().getDimensionPixelSize(R.dimen.single_taken_thumbnail_icon_end_padding), 0);
        }
        setDecoViewVisibility(mediaItem);
        setShotTypeTextView(mediaItem);
    }

    public void bindView(View view) {
        super.bindView(view);
        this.mShotTypeTextView = (TextView) view.findViewById(R.id.shot_type_text);
        this.mImageViewBorder = (ImageView) view.findViewById(R.id.thumbnail_border);
    }

    public PreviewViewHolder.PreviewListener createPreviewListener() {
        return new SingleTakenPreviewListener(this, 0);
    }

    public void drawFocusedBorder() {
        addThumbnailBorder(getContext().getDrawable(R.drawable.single_taken_border));
    }

    public boolean durationAvailableItem(MediaItem mediaItem) {
        if (mediaItem == null || MediaItemUtil.isSuperSlowClip(mediaItem)) {
            return false;
        }
        return true;
    }

    public int getIconResourceId(MediaItem mediaItem) {
        return ResourceUtil.getIconResourceIdForSingleTake(mediaItem);
    }

    public int getSlot1ResId() {
        return R.drawable.gallery_ic_thumbnail_best;
    }

    public int getSlot2ResId() {
        return R.drawable.gallery_ic_thumbnail_favorite;
    }

    public TextView getTextView() {
        return this.mShotTypeTextView;
    }

    public boolean isSelectionMode() {
        int i2 = this.mSupportDecoItemType;
        if (i2 == -1 || (i2 & 1) == 0) {
            return false;
        }
        return true;
    }

    public boolean isSlot1Enabled() {
        MediaItem mediaItem = this.mMediaItem;
        if (mediaItem == null || mediaItem.getBestImage() != 1) {
            return false;
        }
        return true;
    }

    public boolean isSlot2Enabled(boolean z) {
        MediaItem mediaItem = this.mMediaItem;
        if (mediaItem == null || !mediaItem.isFavourite()) {
            return false;
        }
        return true;
    }

    public void recycle() {
        super.recycle();
        restoreThumbnailBorder();
        ViewUtils.setText(this.mShotTypeTextView, (String) null);
    }

    public void restoreThumbnailBorder() {
        ImageView imageView = this.mImageViewBorder;
        if (imageView != null) {
            imageView.setForeground(this.mForegroundDrawable);
        }
    }

    public void setDecoViewVisibilityInSelectionMode() {
        setStorageFavoriteVisibility();
    }

    public ListViewHolder setThumbnailShape(int i2, float f) {
        ImageView imageView = this.mImageViewBorder;
        if (imageView != null) {
            ViewUtils.setViewShape(imageView, i2, f);
        }
        return super.setThumbnailShape(i2, f);
    }
}
