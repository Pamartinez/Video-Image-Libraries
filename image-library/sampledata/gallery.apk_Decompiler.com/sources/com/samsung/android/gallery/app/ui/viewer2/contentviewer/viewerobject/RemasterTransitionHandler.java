package com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.View;
import android.widget.ImageView;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObject;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.blackboard.key.DataKey;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.ExifUtils;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.Utils;
import com.samsung.android.gallery.widget.abstraction.SharedTransition;
import com.samsung.android.gallery.widget.abstraction.TransitionInfo;
import com.samsung.android.gallery.widget.photoview.PhotoPreView;
import com.samsung.android.gallery.widget.photoview.PhotoView;
import com.samsung.android.gallery.widget.photoview.PhotoViewCompat;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.sum.core.message.Message;
import com.sec.android.gallery3d.R;
import t8.e;
import v7.r;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RemasterTransitionHandler extends ViewerObject {
    private PhotoViewCompat mPhotoView;
    private PhotoViewCompat mRemasterPhotoView;

    private RectF getDisplayRect(PhotoView photoView) {
        Rect rect = new Rect();
        if (!photoView.getGlobalVisibleRect(rect)) {
            return photoView.getDisplayRect();
        }
        PointF scaledPosition = photoView.getScaledPosition();
        RectF displayRect = photoView.getDisplayRect();
        if (scaledPosition == null || displayRect == null) {
            return null;
        }
        RectF rectF = new RectF();
        int i2 = rect.top;
        rectF.top = Math.max(((float) i2) + scaledPosition.y, (float) i2);
        rectF.bottom = Math.min(((float) rect.bottom) - scaledPosition.y, (float) (rect.height() + rect.top));
        if (Features.isEnabled(Features.IS_RTL)) {
            rectF.left = (scaledPosition.x + ((float) photoView.getRootView().getWidth())) - ((float) rect.right);
        } else {
            rectF.left = scaledPosition.x + ((float) rect.left);
        }
        rectF.right = displayRect.width() + rectF.left;
        MediaItem mediaItem = this.mModel.getMediaItem();
        if (mediaItem != null && ExifUtils.isHorizontalMirror(mediaItem.getOrientationTag())) {
            float f = rectF.left;
            rectF.left = ((float) rect.width()) - rectF.right;
            rectF.right = ((float) rect.width()) - f;
        }
        return rectF;
    }

    private Matrix getPhotoViewMatrix(PhotoView photoView) {
        Matrix displayMatrix = photoView.getDisplayMatrix();
        MediaItem mediaItem = this.mModel.getMediaItem();
        if (mediaItem != null && ExifUtils.isHorizontalMirror(mediaItem.getOrientationTag())) {
            displayMatrix.postScale(-1.0f, 1.0f);
            displayMatrix.postTranslate((float) this.mPhotoView.getWidth(), 0.0f);
        }
        return displayMatrix;
    }

    private int getReturnPosition(String str) {
        if (LocationKey.isSuggests(str)) {
            return ArgumentsUtil.getArgValue(str, Message.KEY_POSITION, 0);
        }
        int argValue = ArgumentsUtil.getArgValue(str, "fixed_return_position_hover", -1);
        if (argValue != -1) {
            return argValue;
        }
        return this.mModel.getPosition();
    }

    /* access modifiers changed from: private */
    public void hideContainerPreview() {
        View view = this.mModel.getContainerModel().getView();
        if (view != null) {
            ViewUtils.setVisibleOrGone((PhotoPreView) view.findViewById(R.id.viewer_container_preview), false);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$0(Object[] objArr) {
        this.mPhotoView = objArr[0];
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$1(Object[] objArr) {
        this.mRemasterPhotoView = objArr[0];
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$2(Object[] objArr) {
        ThreadUtil.postOnUiThread(new e(17, this));
    }

    /* access modifiers changed from: private */
    public void onBackKeyPressed(Object... objArr) {
        if (this.mPhotoView == null) {
            Log.w(this.TAG, "failed to prepare shared transition. null PhotoView");
        } else if (Utils.isAnimationDuration0x(this.mModel.getContext())) {
            Log.d(this.TAG, "failed to prepare shared transition. animation off");
        } else {
            String locationKey = this.mModel.getContainerModel().getLocationKey();
            if (LocationKey.isRemasterSingle(locationKey)) {
                Bitmap bitmap = this.mPhotoView.getBitmap();
                View view = this.mModel.getContainerModel().getView();
                if (bitmap == null || view == null) {
                    Log.w((CharSequence) this.TAG, "failed to prepare shared transition -> ", bitmap, view);
                    return;
                }
                TransitionInfo transitionInfo = new TransitionInfo(this.mModel.getOriginMediaItem(), bitmap, "remaster/");
                this.mPhotoView.resetRegionDecodingInfoDirectly();
                this.mPhotoView.setScaleType(ImageView.ScaleType.MATRIX);
                this.mPhotoView.setImageBitmap(bitmap);
                this.mPhotoView.updateMatrixForBitmap(bitmap);
                PhotoViewCompat photoViewCompat = this.mPhotoView;
                photoViewCompat.setImageMatrix(getPhotoViewMatrix(photoViewCompat));
                SharedTransition.setTransitionName((PhotoPreView) view.findViewById(R.id.viewer_container_preview), (String) null);
                SharedTransition.setTransitionName(this.mPhotoView, SharedTransition.getTransitionName("remaster/", this.mModel.getOriginMediaItem()));
                SharedTransition.setReturnPosition(getBlackboard(), 0, transitionInfo);
            } else if (this.mRemasterPhotoView == null) {
                Log.w(this.TAG, "failed to prepare shared transition. null RemasterPhotoView");
            } else {
                SharedTransition.setReturnPosition(getBlackboard(), getReturnPosition(locationKey));
                this.mActionInvoker.invoke(ViewerAction.PREPARE_LAST_VIDEO_FRAME, new Object[0]);
                TransitionInfo transitionInfo2 = new TransitionInfo(this.mModel.getMediaItem(), this.mRemasterPhotoView.getBitmap(), this.mModel.getPosition());
                RectF displayRect = getDisplayRect(this.mRemasterPhotoView);
                if (!transitionInfo2.hasValidData() || displayRect == null) {
                    StringCompat stringCompat = this.TAG;
                    Log.e(stringCompat, "onBackPressed. invalid transition data " + transitionInfo2 + displayRect);
                } else {
                    if (!BlackboardUtils.isViewerShrink(getBlackboard())) {
                        getBlackboard().publish("data://shrink_active", DataKey.ShrinkType.BACK_PRESSED);
                    }
                    transitionInfo2.setDisplayRect(displayRect);
                    transitionInfo2.publish(getBlackboard());
                    StringCompat stringCompat2 = this.TAG;
                    Log.st(stringCompat2, "onBackPressed. " + transitionInfo2);
                }
                if (BlackboardUtils.isViewerShrink(getBlackboard())) {
                    this.mPhotoView.setTransitionName((String) null);
                }
            }
        }
    }

    public void addActionInvokeListener() {
        this.mActionInvoker.add(ViewerAction.IMAGE_PHOTO_VIEW, new r(this, 0));
        this.mActionInvoker.add(ViewerAction.REMASTER_PHOTO_VIEW, new r(this, 1));
        this.mActionInvoker.add(ViewerAction.BACK_KEY_PRESSED, new r(this, 2));
        this.mActionInvoker.add(ViewerAction.SET_REMASTER_IMAGE, new r(this, 3));
    }
}
