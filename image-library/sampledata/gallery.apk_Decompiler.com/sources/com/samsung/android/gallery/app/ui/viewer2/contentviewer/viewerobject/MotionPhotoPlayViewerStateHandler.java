package com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject;

import android.content.res.Configuration;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.FragmentLifeCycle;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObject;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.motionphoto.MotionPhotoViewMode;
import com.samsung.android.gallery.module.motionphoto.MotionPlayViewer;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import q6.e;
import v7.o;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MotionPhotoPlayViewerStateHandler extends ViewerObject implements FragmentLifeCycle {
    private void changeMotionPlayViewer(MotionPlayViewer motionPlayViewer, int i2) {
        MotionPlayViewer motionPlayViewer2 = this.mModel.getMotionPlayViewer();
        if (i2 == this.mModel.getPosition()) {
            changeMotionPlayViewerInternal(motionPlayViewer2, motionPlayViewer);
        }
    }

    private void changeMotionPlayViewerInternal(MotionPlayViewer motionPlayViewer, MotionPlayViewer motionPlayViewer2) {
        if (motionPlayViewer != motionPlayViewer2) {
            StringCompat stringCompat = this.TAG;
            Log.d(stringCompat, "Motion play viewer changed : " + motionPlayViewer + " -> " + motionPlayViewer2);
            this.mModel.setMotionPlayViewer(motionPlayViewer2);
            this.mActionInvoker.invoke(ViewerAction.MOTION_PLAY_VIEWER_CHANGED, motionPlayViewer, motionPlayViewer2);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: changeToImageViewer */
    public void lambda$invalidate$3(MediaItem mediaItem) {
        if (this.mModel.getMotionPlayViewer().isViewMode) {
            this.mActionInvoker.invoke(ViewerAction.SET_TO_MOTION_PHOTO_ORIGINAL_IMAGE, mediaItem);
        } else {
            this.mActionInvoker.invoke(ViewerAction.CHANGE_MOTION_PLAY_VIEWER, MotionPlayViewer.PHOTO, Integer.valueOf(this.mModel.getPosition()));
        }
    }

    private MotionPlayViewer getMotionPlayViewerByFilmState(boolean z) {
        if (z) {
            return MotionPlayViewer.VIDEO_FILM;
        }
        return MotionPlayViewer.PHOTO;
    }

    private boolean isMotionPhotoViewModeOn(MediaItem mediaItem) {
        if (mediaItem == null || !mediaItem.isMotionPhoto() || MediaItemUtil.getMotionPhotoViewMode(mediaItem) != MotionPhotoViewMode.ON) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$0(Object[] objArr) {
        if (isMotionPhotoViewModeOn(this.mModel.getMediaItem())) {
            changeMotionPlayViewer(getMotionPlayViewerByFilmState(objArr[0].booleanValue()), objArr[1].intValue());
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$1(Object[] objArr) {
        changeMotionPlayViewer(objArr[0], objArr[1].intValue());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$2(Object[] objArr) {
        lambda$invalidate$3(this.mModel.getMediaItem());
    }

    public void addActionInvokeListener() {
        super.addActionInvokeListener();
        this.mActionInvoker.add(ViewerAction.FILM_STRIP_STATE_CHANGED, new o(this, 0));
        this.mActionInvoker.add(ViewerAction.CHANGE_MOTION_PLAY_VIEWER, new o(this, 1));
        this.mActionInvoker.add(ViewerAction.CLOSE_MOTION_PHOTO, new o(this, 2));
    }

    public void invalidate(MediaItem mediaItem, int i2, MediaItem mediaItem2, int i7) {
        super.invalidate(mediaItem, i2, mediaItem2, i7);
        if (!MediaItemUtil.equals(mediaItem, mediaItem2)) {
            ThreadUtil.postOnUiThread(new e(23, this, mediaItem));
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        lambda$invalidate$3(this.mModel.getMediaItem());
    }

    public void onPause() {
        if (!this.mModel.getStateHelper().isQuickViewShrink()) {
            lambda$invalidate$3(this.mModel.getMediaItem());
        }
    }

    public void onViewAttached() {
        super.onViewAttached();
        if (this.mModel.getStateHelper().isFilmExpanded(this.mModel.getPosition())) {
            changeMotionPlayViewerInternal(this.mModel.getMotionPlayViewer(), MotionPlayViewer.VIDEO_FILM);
        }
    }

    public void onViewDetached() {
        super.onViewDetached();
        changeMotionPlayViewerInternal(this.mModel.getMotionPlayViewer(), MotionPlayViewer.PHOTO);
    }
}
