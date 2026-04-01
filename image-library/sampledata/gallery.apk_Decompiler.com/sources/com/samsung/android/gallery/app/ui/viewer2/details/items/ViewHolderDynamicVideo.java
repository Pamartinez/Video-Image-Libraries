package com.samsung.android.gallery.app.ui.viewer2.details.items;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.util.Size;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.widget.TextView;
import com.samsung.android.gallery.app.ui.viewholders.ImageViewHolder;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.animator.SimpleAnimator;
import com.samsung.android.gallery.widget.details.ViewHolderPlayer;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.gallery.widget.videoview.mediaplayer.IMediaPlayerView;
import com.samsung.android.gallery.widget.videoview.mediaplayer.MediaPlayerListener;
import com.samsung.android.gallery.widget.videoview.mediaplayer.MediaTextureViewCompat;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class ViewHolderDynamicVideo extends ImageViewHolder implements MediaPlayerListener, ViewHolderPlayer {
    private final View.AccessibilityDelegate mAccessibilityDelegate = new View.AccessibilityDelegate() {
        public void onPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            super.onPopulateAccessibilityEvent(view, accessibilityEvent);
            if (accessibilityEvent.getEventType() == 32768) {
                Log.v(ViewHolderDynamicVideo.this.TAG, "Populating accessibility event");
                ViewHolderDynamicVideo.this.setContentDescription();
            }
        }
    };
    float mCornerRadius;
    private IMediaPlayerView mMediaView;
    protected TextView mTitle;
    public VideoCompleteCallback mVideoCompleteCallback;

    public ViewHolderDynamicVideo(View view, int i2) {
        super(view, i2);
        setThumbnailShape(1, this.mCornerRadius);
    }

    private void bindTitleView(MediaItem mediaItem) {
        ViewUtils.setText(this.mTitle, mediaItem.getTitle());
    }

    private IMediaPlayerView bindVideoView(MediaItem mediaItem) {
        IMediaPlayerView createTextureView = createTextureView();
        View view = (View) createTextureView;
        ViewUtils.setViewShape(view, 1, this.mCornerRadius);
        ((ViewGroup) getImage().getParent()).addView(view, 0);
        createTextureView.addMediaPlayerListener(this);
        createTextureView.setLogTag(Integer.valueOf(getBindingAdapterPosition()));
        Size size = getSize(MediaItemUtil.isLandscapeItem(mediaItem));
        setLayout(this.mImageView, size);
        setLayoutVideo((View) createTextureView, mediaItem.getSourceSize(), size);
        createTextureView.open(mediaItem);
        createTextureView.setPlaybackLoop(false);
        createTextureView.setAccessibilityDelegate(this.mAccessibilityDelegate);
        return createTextureView;
    }

    private void clearVideoView() {
        IMediaPlayerView iMediaPlayerView = this.mMediaView;
        if (iMediaPlayerView != null) {
            iMediaPlayerView.close(true);
            ViewUtils.removeSelf((View) this.mMediaView);
            this.mMediaView = null;
        }
    }

    private IMediaPlayerView createTextureView() {
        return new MediaTextureViewCompat(getContext(), (AttributeSet) null, 0, R.style.DynamicViewPreviewStyle);
    }

    private Size getSize(boolean z) {
        Resources resources = getRootView().getResources();
        int dimension = (int) resources.getDimension(R.dimen.moreinfo_oneui5x_item_suggested_edit_long_side);
        int dimension2 = (int) resources.getDimension(R.dimen.moreinfo_oneui5x_item_suggested_edit_short_side);
        if (z) {
            return new Size(dimension, dimension2);
        }
        return new Size(dimension2, dimension);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$bindView$0(View view, View view2) {
        onClick(view);
    }

    private void setImageViewVisibility(int i2) {
        if (this.mImageView.getVisibility() != i2) {
            SimpleAnimator.setVisibility((View) this.mImageView, i2, 0);
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

    private void updateViewSize(Bitmap bitmap) {
        boolean z;
        if (bitmap != null && this.mMediaView != null) {
            if (bitmap.getWidth() > bitmap.getHeight()) {
                z = true;
            } else {
                z = false;
            }
            Size size = getSize(z);
            setLayout(this.mImageView, size);
            setLayoutVideo((View) this.mMediaView, new Size(bitmap.getWidth(), bitmap.getHeight()), size);
        }
    }

    public void bind(MediaItem mediaItem) {
        super.bind(mediaItem);
        if (this.mMediaView != null) {
            clearVideoView();
        }
        bindTitleView(mediaItem);
        this.mMediaView = bindVideoView(mediaItem);
    }

    public void bindImage(Bitmap bitmap) {
        updateViewSize(bitmap);
        super.bindImage(bitmap);
    }

    public void bindView(View view) {
        super.bindView(view);
        this.mTitle = (TextView) view.findViewById(R.id.title);
        this.mCornerRadius = view.getResources().getDimension(R.dimen.moreinfo_item_image_corner_radius_oneui30);
        this.mTitle.setOnClickListener(new C(this, view));
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

    public void onVideoReleased() {
        setImageViewVisibility(0);
    }

    public void play() {
        if (this.mMediaView.isPlaying()) {
            Log.d(this.TAG, "play skip. keep playing");
            return;
        }
        String str = this.TAG;
        Log.d(str, "play() pos=" + getBindingAdapterPosition());
        if (!this.mMediaView.isOpened()) {
            this.mMediaView.open(this.mMediaItem);
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
            return "ViewHolderDynamicVideo{" + getBindingAdapterPosition() + "}";
        }
        return "ViewHolderDynamicVideo{" + getBindingAdapterPosition() + GlobalPostProcInternalPPInterface.SPLIT_REGEX + mediaItem.getOrientation() + GlobalPostProcInternalPPInterface.SPLIT_REGEX + mediaItem.getVideoThumbStartTime() + "}";
    }
}
