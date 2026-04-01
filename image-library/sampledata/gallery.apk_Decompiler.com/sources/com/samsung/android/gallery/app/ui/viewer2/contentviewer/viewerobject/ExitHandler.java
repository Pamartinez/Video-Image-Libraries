package com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject;

import android.view.View;
import android.view.ViewParent;
import androidx.fragment.app.Fragment;
import c0.C0086a;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.FragmentLifeCycle;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObject;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.widget.DragExitHandler;
import com.samsung.android.gallery.widget.photoview.PhotoView;
import com.samsung.android.gallery.widget.videoview.mediaplayer.IMediaPlayerView;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import o6.p;
import v7.m;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ExitHandler extends ViewerObject implements FragmentLifeCycle {
    private View mContentContainerForTouch;
    private DragExitHandler mDragExitHandler;
    private final DragExitHandler.TouchListener mDragExitTouchListener = new DragExitHandler.TouchListener() {
        public boolean isMovable() {
            MediaItem mediaItem;
            if (ExitHandler.this.mMediaView == null || (mediaItem = ExitHandler.this.mModel.getMediaItem()) == null || (!mediaItem.isVideo() && (!mediaItem.isMotionPhoto() || !ExitHandler.this.mMediaView.isVisible()))) {
                if (!ExitHandler.this.mPhotoView.isMovable() || ExitHandler.this.mPhotoView.isZoomed()) {
                    return false;
                }
                return true;
            } else if (!ExitHandler.this.mMediaView.isMovable() || ExitHandler.this.mMediaView.isZoomed()) {
                return false;
            } else {
                return true;
            }
        }

        public boolean isSupportedDragExit() {
            return isMovable();
        }

        public void onDetectedDragExit() {
            ExitHandler.this.mActionInvoker.invoke(ViewerAction.EXIT_GESTURE, Boolean.FALSE, Boolean.TRUE);
        }
    };
    /* access modifiers changed from: private */
    public IMediaPlayerView mMediaView;
    /* access modifiers changed from: private */
    public PhotoView mPhotoView;

    private boolean canBackToCamera() {
        Fragment containerFragment = this.mModel.getContainerModel().getContainerFragment();
        if (containerFragment == null || containerFragment.getParentFragmentManager().getBackStackEntryCount() != 0) {
            return false;
        }
        return true;
    }

    private int getEventKey(boolean z, boolean z3) {
        if (!PreferenceFeatures.OneUi5x.SUPPORT_SHRINK_TO_CAMERA || !canBackToCamera()) {
            if (supportPinchShrink()) {
                if (z3) {
                    return 3020;
                }
                if (z) {
                    return 3019;
                }
            }
            return 3014;
        } else if (z3) {
            return 3046;
        } else {
            if (z) {
                return 3047;
            }
            return 3014;
        }
    }

    private ViewParent getPhotoAndVideoViewParent() {
        return this.mContentContainerForTouch.getParent();
    }

    /* access modifiers changed from: private */
    public void handleContentViewTouch(Object... objArr) {
        DragExitHandler dragExitHandler = this.mDragExitHandler;
        if (dragExitHandler != null) {
            dragExitHandler.onTouch(objArr[0]);
        }
    }

    /* access modifiers changed from: private */
    public void initMediaView(Object... objArr) {
        IMediaPlayerView iMediaPlayerView = objArr[0];
        this.mMediaView = iMediaPlayerView;
        iMediaPlayerView.setPinchShrinkSupport(new p(20, this));
    }

    /* access modifiers changed from: private */
    public void initPhotoView(Object... objArr) {
        PhotoView photoView = objArr[0];
        this.mPhotoView = photoView;
        photoView.setMotionControl(photoView.createDefaultMotionControl(getPhotoAndVideoViewParent()), new p(20, this));
    }

    private boolean isUnsupportedExitGesture(boolean z) {
        if (!PreferenceFeatures.OneUi5x.SUPPORT_SHRINK_TO_CAMERA || !canBackToCamera()) {
            if ((!z || !this.mModel.getStateHelper().isQuickView()) && !this.mModel.isTextExtractionFullState() && !this.mModel.isObjectCaptureState() && !this.mModel.isInstantSlowMoPlayEnabled() && !this.mModel.isVideoSpeedChangeOnGoing()) {
                return false;
            }
            return true;
        } else if (this.mModel.isTextExtractionFullState() || this.mModel.isObjectCaptureState() || this.mModel.getActivity() == null || !this.mModel.getActivity().hasWindowFocus()) {
            return true;
        } else {
            return false;
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$0(Object[] objArr) {
        this.mContentContainerForTouch = objArr[0];
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$1(Object[] objArr) {
        onExitGesture(objArr[0].booleanValue(), objArr[1].booleanValue());
    }

    /* access modifiers changed from: private */
    public void onObjectCaptureDone(Object... objArr) {
        DragExitHandler dragExitHandler = this.mDragExitHandler;
        if (dragExitHandler != null) {
            dragExitHandler.cancel();
        }
    }

    /* access modifiers changed from: private */
    public void onResetExitGesture(Object... objArr) {
        this.mDragExitHandler = new DragExitHandler(this.mModel.getContext(), this.mDragExitTouchListener);
    }

    private boolean supportPinchShrink() {
        return this.mModel.getContainerModel().getStateHelper().supportPinchShrink();
    }

    public void addActionInvokeListener() {
        super.addActionInvokeListener();
        this.mActionInvoker.add(ViewerAction.IMAGE_PHOTO_VIEW, new m(this, 0));
        this.mActionInvoker.add(ViewerAction.MEDIA_VIEW, new m(this, 1));
        this.mActionInvoker.add(ViewerAction.CONTENT_CONTAINER_FOR_TOUCH, new m(this, 2));
        this.mActionInvoker.add(ViewerAction.EXIT_GESTURE, new m(this, 3));
        this.mActionInvoker.add(ViewerAction.RESET_EXIT_GESTURE, new m(this, 4));
        this.mActionInvoker.add(ViewerAction.ON_CONTENT_VIEW_TOUCH, new m(this, 5));
        this.mActionInvoker.add(ViewerAction.OBJECT_CAPTURE_DONE, new m(this, 6));
    }

    public void onBind(MediaItem mediaItem, int i2) {
        super.onBind(mediaItem, i2);
        this.mDragExitHandler = new DragExitHandler(this.mModel.getContext(), this.mDragExitTouchListener);
    }

    public void onExitGesture(boolean z, boolean z3) {
        boolean z7;
        if (isUnsupportedExitGesture(z)) {
            StringCompat stringCompat = this.TAG;
            Boolean valueOf = Boolean.valueOf(z);
            Boolean valueOf2 = Boolean.valueOf(this.mModel.isTextExtractionFullState());
            Boolean valueOf3 = Boolean.valueOf(this.mModel.isObjectCaptureState());
            Boolean valueOf4 = Boolean.valueOf(this.mModel.isInstantSlowMoPlayEnabled());
            Boolean valueOf5 = Boolean.valueOf(this.mModel.isVideoSpeedChangeOnGoing());
            if (this.mModel.getActivity() == null || !this.mModel.getActivity().hasWindowFocus()) {
                z7 = false;
            } else {
                z7 = true;
            }
            Log.w((CharSequence) stringCompat, "onExitGesture Fail", valueOf, valueOf2, valueOf3, valueOf4, valueOf5, Boolean.valueOf(z7));
            return;
        }
        int eventKey = getEventKey(z, z3);
        StringBuilder sb2 = new StringBuilder("ViewerOnExitGesture {");
        sb2.append(z);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(z3);
        sb2.append(",k=");
        Log.d(this.TAG, C0086a.l(sb2, eventKey, "}"));
        this.mActionInvoker.invoke(ViewerAction.HANDLE_EXIT_GESTURE, Integer.valueOf(eventKey));
    }

    public void onResume() {
        this.mModel.getBlackboard().erase("data://image_viewer_return_info");
        this.mModel.getBlackboard().erase("data://video_viewer_return_info");
    }
}
