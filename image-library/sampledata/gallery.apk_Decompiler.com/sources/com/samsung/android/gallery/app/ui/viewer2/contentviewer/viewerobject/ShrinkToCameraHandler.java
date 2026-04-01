package com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject;

import android.graphics.Rect;
import android.graphics.RectF;
import android.view.View;
import android.view.ViewGroup;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObject;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.abstraction.TransitionInfo;
import com.samsung.android.gallery.widget.photoview.PhotoView;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.gallery.widget.videoview.mediaplayer.IMediaPlayerView;
import java.util.Optional;
import o4.a;
import r6.e;
import v7.s;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ShrinkToCameraHandler extends ViewerObject {
    private IMediaPlayerView mMediaView;
    private PhotoView mPhotoView;
    final Rect mVideoRectBackup = new Rect();
    int[] mVisibilityBackup;
    boolean mZoomEnabledBackup;

    private RectF getDisplayRect(MediaItem mediaItem) {
        IMediaPlayerView iMediaPlayerView;
        RectF rectF = null;
        if (!(mediaItem == null || !mediaItem.isVideo() || (iMediaPlayerView = this.mMediaView) == null)) {
            rectF = iMediaPlayerView.getDisplayRect();
        }
        if (isInvalidRect(rectF)) {
            rectF = this.mPhotoView.getDisplayRect();
        }
        if (isInvalidRect(rectF)) {
            return this.mPhotoView.getDisplayMinRect();
        }
        return rectF;
    }

    private boolean isInvalidRect(RectF rectF) {
        if (rectF == null || rectF.width() == 0.0f || rectF.height() == 0.0f) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$0(Object[] objArr) {
        this.mPhotoView = objArr[0];
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$1(Object[] objArr) {
        this.mMediaView = objArr[0];
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onHideMainView$2(IMediaPlayerView iMediaPlayerView) {
        iMediaPlayerView.getView().getLocalVisibleRect(this.mVideoRectBackup);
        iMediaPlayerView.setVisibility(8);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$onHideMainView$3(IMediaPlayerView iMediaPlayerView) {
        View view = iMediaPlayerView.getView();
        iMediaPlayerView.setVisibility(8);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.width = -1;
        layoutParams.height = -1;
        view.setLayoutParams(layoutParams);
    }

    /* access modifiers changed from: private */
    public void onHideMainView(Object... objArr) {
        int i2;
        int i7;
        if (objArr[0].booleanValue()) {
            PhotoView photoView = this.mPhotoView;
            if (photoView != null) {
                i2 = photoView.getVisibility();
            } else {
                i2 = 8;
            }
            IMediaPlayerView iMediaPlayerView = this.mMediaView;
            if (iMediaPlayerView != null) {
                i7 = iMediaPlayerView.getVisibility();
            } else {
                i7 = 8;
            }
            this.mVisibilityBackup = new int[]{i2, i7};
            ViewUtils.setAlpha(this.mPhotoView, 0.0f);
            ViewUtils.setVisibility(this.mPhotoView, 8);
            PhotoView photoView2 = this.mPhotoView;
            if (photoView2 != null) {
                this.mZoomEnabledBackup = photoView2.isZoomEnabled();
                this.mPhotoView.setZoomEnabled(false);
            }
            Optional.ofNullable(this.mMediaView).ifPresent(new a(22, this));
        } else if (this.mVisibilityBackup == null) {
            Log.e(this.TAG, "onHideMainView : mVisibilityBackup is null");
        } else {
            ViewUtils.setAlpha(this.mPhotoView, 1.0f);
            ViewUtils.setVisibility(this.mPhotoView, 0);
            PhotoView photoView3 = this.mPhotoView;
            if (photoView3 != null) {
                photoView3.setZoomEnabled(this.mZoomEnabledBackup);
            }
            Optional.ofNullable(this.mMediaView).ifPresent(new e(29));
        }
    }

    /* access modifiers changed from: private */
    public void onPublishTransitionInfo(Object... objArr) {
        Blackboard blackboard = this.mModel.getBlackboard();
        MediaItem mediaItem = this.mModel.getMediaItem();
        if (blackboard != null && mediaItem != null) {
            TransitionInfo displayRect = new TransitionInfo(mediaItem, this.mModel.getBitmap()).setDisplayRect(getDisplayRect(mediaItem));
            if (mediaItem.isVideo()) {
                this.mActionInvoker.invoke(ViewerAction.PREPARE_LAST_VIDEO_FRAME, new Object[0]);
                displayRect.bitmap = this.mModel.getBitmap();
            }
            this.mActionInvoker.invoke(ViewerAction.PREPARE_EXIT_TRANSITION, displayRect);
            displayRect.publish(blackboard);
        }
    }

    public void addActionInvokeListener() {
        super.addActionInvokeListener();
        this.mActionInvoker.add(ViewerAction.IMAGE_PHOTO_VIEW, new s(this, 0));
        this.mActionInvoker.add(ViewerAction.MEDIA_VIEW, new s(this, 1));
        this.mActionInvoker.add(ViewerAction.HIDE_MAIN_VIEW, new s(this, 2));
        this.mActionInvoker.add(ViewerAction.PUBLISH_TRANSITION_INFO, new s(this, 3));
    }
}
