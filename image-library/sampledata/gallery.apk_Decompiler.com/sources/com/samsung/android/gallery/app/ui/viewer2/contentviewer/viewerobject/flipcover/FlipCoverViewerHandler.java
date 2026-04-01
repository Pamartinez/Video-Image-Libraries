package com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.flipcover;

import A4.C0366a;
import A4.C0367b;
import B7.g;
import B7.h;
import android.graphics.Rect;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.FragmentLifeCycle;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObject;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.helper.DeviceInfo;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ResourceCompat;
import com.samsung.android.gallery.widget.photoview.PhotoView;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.videoview.mediaplayer.IMediaPlayerView;
import com.sec.android.gallery3d.R;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FlipCoverViewerHandler extends ViewerObject implements FragmentLifeCycle {
    private IMediaPlayerView mMediaView;
    private PhotoView mPhotoView;

    private Rect getDisplayCutoutRect() {
        return this.mModel.getContainerModel().getCutouts();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onViewAttached$0(PhotoView photoView) {
        photoView.setRoundedCorner(ResourceCompat.getDimensionPixelOffset(this.mModel.getContext(), R.dimen.cover_rounded_corner_radius, 0));
    }

    /* access modifiers changed from: private */
    public void setMediaView(Object... objArr) {
        this.mMediaView = objArr[0];
        setVideoPadding();
    }

    private void setPhotoPadding() {
        MediaItem mediaItem;
        int i2;
        int i7;
        if (this.mModel.getMediaItem() != null) {
            mediaItem = this.mModel.getMediaItem();
        } else {
            mediaItem = this.mModel.getContainerModel().getCurrentMediaItem();
        }
        if (mediaItem != null && this.mPhotoView != null) {
            boolean z = true;
            if (mediaItem.getWidth() <= 0 || mediaItem.getHeight() <= 0) {
                Log.e(this.TAG, "set broken : " + mediaItem);
                mediaItem.setBroken(true);
            }
            if (this.mModel.getStateHelper().isFullScreenInFlipCover(mediaItem, DeviceInfo.getDisplaySize(this.mModel.getContext()))) {
                ViewMarginUtils.setPadding(this.mPhotoView, 0, 0, 0, 0);
                return;
            }
            int width = DeviceInfo.getDisplaySize(this.mModel.getContext()).getWidth();
            int height = DeviceInfo.getDisplaySize(this.mModel.getContext()).getHeight();
            if (!(mediaItem.getOrientation() == 90 || mediaItem.getOrientation() == 270)) {
                z = false;
            }
            if (z) {
                i2 = mediaItem.getWidth();
            } else {
                i2 = mediaItem.getHeight();
            }
            if (z) {
                i7 = mediaItem.getHeight();
            } else {
                i7 = mediaItem.getWidth();
            }
            float f = (float) i7;
            float f5 = (float) i2;
            float min = Math.min(Math.min(((float) width) / f, ((float) height) / f5), this.mPhotoView.getMaxScale());
            int i8 = (int) (f5 * min);
            Rect displayCutoutRect = getDisplayCutoutRect();
            int i10 = (height - i8) - (displayCutoutRect.top + displayCutoutRect.bottom);
            int max = (Math.max(0, i10) / 2) + displayCutoutRect.top;
            int max2 = (Math.max(0, i10) / 2) + displayCutoutRect.bottom;
            int i11 = (width - ((int) (f * min))) - (displayCutoutRect.left + displayCutoutRect.right);
            ViewMarginUtils.setPadding(this.mPhotoView, (Math.max(0, i11) / 2) + displayCutoutRect.left, max, (Math.max(0, i11) / 2) + displayCutoutRect.right, max2);
        }
    }

    /* access modifiers changed from: private */
    public void setPhotoView(Object... objArr) {
        this.mPhotoView = objArr[0];
        setPhotoPadding();
    }

    private void setVideoPadding() {
        if (this.mModel.getStateHelper().isFullScreenInFlipCover(this.mModel.getMediaItem(), DeviceInfo.getDisplaySize(this.mModel.getContext()))) {
            Optional.ofNullable(this.mMediaView).ifPresent(new C0366a(28));
            return;
        }
        Optional.ofNullable(this.mMediaView).ifPresent(new h(getDisplayCutoutRect(), 0));
    }

    private void setViewPadding() {
        setPhotoPadding();
        setVideoPadding();
    }

    public void addActionInvokeListener() {
        this.mActionInvoker.add(ViewerAction.IMAGE_PHOTO_VIEW, new g(this, 0));
        this.mActionInvoker.add(ViewerAction.MEDIA_VIEW, new g(this, 1));
    }

    public void onApplyWindowInsets() {
        setViewPadding();
    }

    public void onViewAttached() {
        super.onViewAttached();
        setViewPadding();
        Optional.ofNullable(this.mPhotoView).ifPresent(new C0367b(9, this));
    }
}
