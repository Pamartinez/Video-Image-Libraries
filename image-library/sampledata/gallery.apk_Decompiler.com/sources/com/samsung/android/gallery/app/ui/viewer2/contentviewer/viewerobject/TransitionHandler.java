package com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject;

import android.graphics.Bitmap;
import android.graphics.RectF;
import android.view.View;
import android.view.ViewParent;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObject;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.blackboard.key.DataKey;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.widget.abstraction.SharedTransition;
import com.samsung.android.gallery.widget.abstraction.TransitionInfo;
import com.samsung.android.gallery.widget.abstraction.ViewTag;
import com.samsung.android.gallery.widget.photoview.PhotoViewCompat;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.videoview.mediaplayer.IMediaPlayerView;
import com.samsung.android.sum.core.message.Message;
import java.util.Optional;
import o4.a;
import v7.u;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class TransitionHandler extends ViewerObject {
    private IMediaPlayerView mMediaPlayerView;
    private PhotoViewCompat mPhotoView;

    private RectF getDisplayRectForTransition(boolean z) {
        RectF rectF;
        if (z) {
            rectF = this.mPhotoView.getDisplayRect();
        } else {
            IMediaPlayerView iMediaPlayerView = this.mMediaPlayerView;
            if (iMediaPlayerView != null) {
                View view = iMediaPlayerView.getView();
                ViewParent parent = view.getParent();
                if (parent instanceof View) {
                    View view2 = (View) parent;
                    if (view2.getTag() == ViewTag.MEDIA_COVER_ROUND_VIEW) {
                        view = view2;
                    }
                }
                rectF = getVideoDisplayRect(view, this.mModel.getBitmap());
            } else {
                Log.w(this.TAG, "setDisplayRect fail for transition");
                return null;
            }
        }
        if (rectF != null && this.mModel.isInMultiWindowMode()) {
            rectF.left += (float) ViewMarginUtils.getLeftMargin(this.mModel.getContainerModel().getView());
            rectF.top += (float) ViewMarginUtils.getTopMargin(this.mModel.getContainerModel().getView());
            rectF.right += (float) ViewMarginUtils.getLeftMargin(this.mModel.getContainerModel().getView());
            rectF.bottom += (float) ViewMarginUtils.getTopMargin(this.mModel.getContainerModel().getView());
        }
        return rectF;
    }

    private int getReturnPosition(String str) {
        if (LocationKey.isSuggests(str)) {
            return ArgumentsUtil.getArgValue(str, Message.KEY_POSITION, 0);
        }
        int argValue = ArgumentsUtil.getArgValue(str, "fixed_return_position_hover", -1);
        if (argValue != -1) {
            return argValue;
        }
        return this.mModel.getContainerModel().getPosition();
    }

    private RectF getVideoDisplayRect(View view, Bitmap bitmap) {
        RectF rectF = new RectF(view.getX(), view.getY(), view.getX() + ((float) view.getWidth()), view.getY() + ((float) view.getHeight()));
        if (bitmap != null && bitmap.getHeight() > 0) {
            int width = (int) ((((float) bitmap.getWidth()) * ((float) view.getHeight())) / ((float) bitmap.getHeight()));
            float width2 = (float) (view.getWidth() - width);
            if (width2 > 0.0f) {
                float f = (width2 * 0.5f) + rectF.left;
                rectF.left = f;
                rectF.right = f + ((float) width);
            }
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
        this.mMediaPlayerView = objArr[0];
    }

    /* access modifiers changed from: private */
    public void onBackKeyPressed(Object... objArr) {
        boolean z;
        String str;
        String locationKey = this.mModel.getContainerModel().getLocationKey();
        boolean z3 = true;
        if (LocationKey.isTempList(locationKey) || ArgumentsUtil.getArgValue(locationKey, "is_temp", false)) {
            z = true;
        } else {
            z = false;
        }
        if (this.mModel.getStateHelper().isQuickView() || z || this.mModel.getStateHelper().isQuickCropQuickView() || ArgumentsUtil.getArgValue(locationKey, "from_viewer", false) || ArgumentsUtil.getArgValue(locationKey, "forceDisableReturnTransition", false)) {
            z3 = false;
        }
        boolean argValue = ArgumentsUtil.getArgValue(locationKey, "returnAutoScrollTransition", false);
        if (z3 || argValue) {
            prepareAutoScrollerTransition();
        } else if (z && ArgumentsUtil.getArgValue(locationKey, "returnTransition", false)) {
            MediaItem mediaItem = this.mModel.getMediaItem();
            if (!locationKey.startsWith("location://dynamicViewList") || mediaItem == null) {
                str = null;
            } else {
                str = mediaItem.getTitle();
            }
            this.mActionInvoker.invoke(ViewerAction.PREPARE_RETURN_TRANSITION, mediaItem, Integer.valueOf(getReturnPosition(locationKey)), str);
        }
        if (this.mPhotoView != null && BlackboardUtils.isViewerShrink(getBlackboard())) {
            this.mPhotoView.setTransitionName((String) null);
        }
    }

    private void prepareAutoScrollerTransition() {
        int returnPosition = getReturnPosition(this.mModel.getContainerModel().getLocationKey());
        SharedTransition.setReturnPosition(getBlackboard(), returnPosition);
        MediaItem mediaItem = this.mModel.getMediaItem();
        TransitionInfo transitionInfo = new TransitionInfo(mediaItem, this.mModel.getBitmap(), returnPosition);
        boolean z = false;
        this.mActionInvoker.invoke(ViewerAction.PREPARE_LAST_VIDEO_FRAME, new Object[0]);
        this.mActionInvoker.invoke(ViewerAction.PREPARE_EXIT_TRANSITION, transitionInfo);
        if (transitionInfo.hasValidData()) {
            if (!BlackboardUtils.isViewerShrink(getBlackboard()) && !LocationKey.isFromHoverView(this.mModel.getContainerModel().getLocationKey())) {
                getBlackboard().publish("data://shrink_active", DataKey.ShrinkType.BACK_PRESSED);
            }
            if (this.mPhotoView.isShown() && ((mediaItem != null && mediaItem.isImage()) || !MediaItemUtil.supportPreviewPlay(mediaItem))) {
                z = true;
            }
            if (mediaItem != null && !mediaItem.isSharing() && !mediaItem.isCloudOnly() && z) {
                transitionInfo.setScale(this.mPhotoView.getCurrentScale(), this.mPhotoView.getScaledPosition());
            }
            Optional.ofNullable(getDisplayRectForTransition(z)).ifPresent(new a(24, transitionInfo));
            transitionInfo.publish(getBlackboard());
            StringCompat stringCompat = this.TAG;
            Log.st(stringCompat, "onBackPressed. " + transitionInfo);
            return;
        }
        StringCompat stringCompat2 = this.TAG;
        Log.e(stringCompat2, "onBackPressed. invalid transition data " + transitionInfo);
    }

    public void addActionInvokeListener() {
        this.mActionInvoker.add(ViewerAction.IMAGE_PHOTO_VIEW, new u(this, 0));
        this.mActionInvoker.add(ViewerAction.MEDIA_VIEW, new u(this, 1));
        this.mActionInvoker.add(ViewerAction.BACK_KEY_PRESSED, new u(this, 2));
    }

    public void invalidate(MediaItem mediaItem, int i2, MediaItem mediaItem2, int i7) {
        super.invalidate(mediaItem, i2, mediaItem2, i7);
        MediaItemUtil.updateVideoResumePosition(this.mModel.getBlackboard(), mediaItem);
    }

    public void onBind(MediaItem mediaItem, int i2) {
        super.onBind(mediaItem, i2);
        MediaItemUtil.updateVideoResumePosition(this.mModel.getBlackboard(), mediaItem);
    }
}
