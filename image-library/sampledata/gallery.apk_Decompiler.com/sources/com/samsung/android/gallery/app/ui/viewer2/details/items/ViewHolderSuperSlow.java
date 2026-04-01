package com.samsung.android.gallery.app.ui.viewer2.details.items;

import android.content.res.Resources;
import android.util.Size;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.widget.TextView;
import c0.C0086a;
import com.samsung.android.gallery.app.ui.viewholders.ImageViewHolder;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.superslow.SuperSlowUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.animator.SimpleAnimator;
import com.samsung.android.gallery.widget.details.ViewHolderPlayer;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.gallery.widget.videoview.mediaplayer.IMediaPlayerView;
import com.samsung.android.gallery.widget.videoview.mediaplayer.MediaPlayerListener;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class ViewHolderSuperSlow extends ImageViewHolder implements MediaPlayerListener, ViewHolderPlayer {
    private final View.AccessibilityDelegate mAccessibilityDelegate = new View.AccessibilityDelegate() {
        public void onPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            super.onPopulateAccessibilityEvent(view, accessibilityEvent);
            if (accessibilityEvent.getEventType() == 32768) {
                Log.v(ViewHolderSuperSlow.this.TAG, "Populating accessibility event");
                ViewHolderSuperSlow.this.setContentDescription();
            }
        }
    };
    float mCornerRadius;
    private IMediaPlayerView mMediaView;
    protected TextView mTitle;
    public VideoCompleteCallback mVideoCompleteCallback;

    public ViewHolderSuperSlow(View view, int i2) {
        super(view, i2);
    }

    private void bindTitleView(MediaItem mediaItem) {
        ViewUtils.setText(this.mTitle, mediaItem.getTitle());
    }

    private void bindVideoView(MediaItem mediaItem) {
        Size size = getSize(this.mMediaItem);
        setLayout(this.mImageView, size);
        setLayoutVideo((View) this.mMediaView, mediaItem.getSourceSize(), size);
        this.mMediaView.addMediaPlayerListener(this);
        openSlowMoVideo(mediaItem);
        this.mMediaView.setAccessibilityDelegate(this.mAccessibilityDelegate);
    }

    private void clearVideoView() {
        IMediaPlayerView iMediaPlayerView = this.mMediaView;
        if (iMediaPlayerView != null) {
            iMediaPlayerView.close(true);
        }
    }

    private Size getSize(MediaItem mediaItem) {
        Resources resources = getRootView().getResources();
        int dimension = (int) resources.getDimension(R.dimen.moreinfo_oneui5x_item_suggested_edit_long_side);
        int dimension2 = (int) resources.getDimension(R.dimen.moreinfo_oneui5x_item_suggested_edit_short_side);
        if (MediaItemUtil.isLandscapeItem(mediaItem)) {
            return new Size(dimension, dimension2);
        }
        return new Size(dimension2, dimension);
    }

    private boolean openSlowMoVideo(MediaItem mediaItem) {
        if (!this.mMediaView.open(mediaItem)) {
            return false;
        }
        int playType = getPlayType();
        this.mMediaView.setSlowMo(playType, SuperSlowUtils.getSuperSlowOffset(playType), true);
        this.mMediaView.setPlaybackLoop(false);
        return true;
    }

    private void setImageViewVisibility(int i2) {
        if (this.mImageView.getVisibility() != i2) {
            SimpleAnimator.setVisibility((View) this.mImageView, i2, 120);
        }
    }

    private void setLayout(View view, Size size) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.width = size.getWidth();
        layoutParams.height = size.getHeight();
        view.setLayoutParams(layoutParams);
    }

    private void setLayoutVideo(View view, Size size, Size size2) {
        setLayout(view, size2);
        float width = ((float) size.getWidth()) / ((float) size.getHeight());
        float width2 = ((float) size2.getWidth()) / ((float) size2.getHeight());
        view.setScaleX(Math.max(1.0f, width / width2));
        view.setScaleY(Math.max(1.0f, width2 / width));
        ViewUtils.setViewShape((View) view.getParent(), 1, this.mCornerRadius);
    }

    public void bind(MediaItem mediaItem) {
        super.bind(mediaItem);
        if (this.mMediaView != null) {
            clearVideoView();
        }
        bindVideoView(mediaItem);
        bindTitleView(mediaItem);
    }

    public void bindView(View view) {
        super.bindView(view);
        this.mMediaView = (IMediaPlayerView) view.findViewById(R.id.video);
        this.mTitle = (TextView) view.findViewById(R.id.title);
        float dimension = view.getResources().getDimension(R.dimen.moreinfo_item_image_corner_radius_oneui30);
        this.mCornerRadius = dimension;
        setThumbnailShape(1, dimension);
        ViewUtils.setViewShape((View) this.mMediaView, 1, this.mCornerRadius);
        IMediaPlayerView iMediaPlayerView = this.mMediaView;
        iMediaPlayerView.setLogTag("[" + getPlayType() + "]");
    }

    public int getPlayType() {
        return getViewType() + 1;
    }

    public boolean isPlaying() {
        IMediaPlayerView iMediaPlayerView = this.mMediaView;
        if (iMediaPlayerView == null || !iMediaPlayerView.isPlaying()) {
            return false;
        }
        return true;
    }

    public void onVideoCompleted() {
        setImageViewVisibility(0);
        VideoCompleteCallback videoCompleteCallback = this.mVideoCompleteCallback;
        if (videoCompleteCallback != null) {
            videoCompleteCallback.onCompleted(getMediaItem());
        }
    }

    public void onVideoPlay(int i2) {
        setImageViewVisibility(8);
    }

    public void play() {
        if (this.mMediaView.isPlaying()) {
            Log.d(this.TAG, "play skip. keep playing");
            return;
        }
        if (!this.mMediaView.isOpened()) {
            openSlowMoVideo(this.mMediaItem);
        }
        this.mMediaView.play();
    }

    public void recycle() {
        stop();
        clearVideoView();
        super.recycle();
    }

    public void setContentDescription() {
        if (this.mMediaItem != null) {
            this.mMediaView.setContentDescription(getContentDescription());
        }
    }

    public void setVideoCompleteListener(VideoCompleteCallback videoCompleteCallback) {
        this.mVideoCompleteCallback = videoCompleteCallback;
    }

    public void stop() {
        this.mMediaView.close();
        setImageViewVisibility(0);
    }

    public String toString() {
        MediaItem mediaItem = this.mMediaItem;
        if (mediaItem == null) {
            return C0086a.l(new StringBuilder("ViewHolderSuperSlow{"), getPlayType(), "}");
        }
        return "ViewHolderSuperSlow{" + getPlayType() + GlobalPostProcInternalPPInterface.SPLIT_REGEX + mediaItem.getOrientation() + GlobalPostProcInternalPPInterface.SPLIT_REGEX + mediaItem.getVideoThumbStartTime() + "}";
    }
}
