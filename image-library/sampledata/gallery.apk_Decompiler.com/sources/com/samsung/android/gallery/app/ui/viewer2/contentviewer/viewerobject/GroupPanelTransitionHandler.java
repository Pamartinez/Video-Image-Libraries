package com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.view.View;
import android.widget.ImageView;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObject;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.utils.ExifUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Utils;
import com.samsung.android.gallery.widget.abstraction.SharedTransition;
import com.samsung.android.gallery.widget.abstraction.TransitionInfo;
import com.samsung.android.gallery.widget.photoview.PhotoPreView;
import com.samsung.android.gallery.widget.photoview.PhotoView;
import com.samsung.android.gallery.widget.photoview.PhotoViewCompat;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import t8.e;
import v7.n;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class GroupPanelTransitionHandler extends ViewerObject {
    private PhotoViewCompat mPhotoView;

    private Matrix getPhotoViewMatrix(PhotoView photoView) {
        Matrix displayMatrix = photoView.getDisplayMatrix();
        MediaItem mediaItem = this.mModel.getMediaItem();
        if (mediaItem != null && ExifUtils.isHorizontalMirror(mediaItem.getOrientationTag())) {
            displayMatrix.postScale(-1.0f, 1.0f);
            displayMatrix.postTranslate((float) this.mPhotoView.getWidth(), 0.0f);
        }
        return displayMatrix;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$0(Object[] objArr) {
        this.mPhotoView = objArr[0];
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onBackKeyPressed$1() {
        Bitmap bitmap = this.mPhotoView.getBitmap();
        View view = this.mModel.getContainerModel().getView();
        if (bitmap == null || view == null) {
            Log.w((CharSequence) this.TAG, "failed to prepare shared transition -> ", bitmap, view);
            return;
        }
        TransitionInfo transitionInfo = new TransitionInfo(this.mModel.getOriginMediaItem(), bitmap, "groupPanel/");
        this.mPhotoView.resetRegionDecodingInfoDirectly();
        this.mPhotoView.setScaleType(ImageView.ScaleType.MATRIX);
        this.mPhotoView.setImageBitmap(bitmap);
        this.mPhotoView.updateMatrixForBitmap(bitmap);
        PhotoViewCompat photoViewCompat = this.mPhotoView;
        photoViewCompat.setImageMatrix(getPhotoViewMatrix(photoViewCompat));
        SharedTransition.setTransitionName((PhotoPreView) view.findViewById(R.id.viewer_container_preview), (String) null);
        SharedTransition.setTransitionName(this.mPhotoView, "groupPanel/");
        SharedTransition.setReturnPosition(getBlackboard(), 0, transitionInfo);
    }

    /* access modifiers changed from: private */
    public void onBackKeyPressed(Object... objArr) {
        if (this.mPhotoView == null) {
            Log.w(this.TAG, "failed to prepare shared transition. null PhotoView");
        } else if (Utils.isAnimationDuration0x(this.mModel.getContext())) {
            Log.d(this.TAG, "failed to prepare shared transition. animation off");
        } else {
            ViewUtils.post(this.mPhotoView, new e(14, this));
        }
    }

    public void addActionInvokeListener() {
        this.mActionInvoker.add(ViewerAction.IMAGE_PHOTO_VIEW, new n(this, 0));
        this.mActionInvoker.add(ViewerAction.BACK_KEY_PRESSED, new n(this, 1));
    }
}
