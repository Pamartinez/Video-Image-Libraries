package com.samsung.android.gallery.app.remote.v2;

import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.window.embedding.c;
import b4.C0425e;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.photoview.OnViewerExitGestureListener;
import com.samsung.android.gallery.widget.photoview.PhotoViewMotionControl;
import com.samsung.android.gallery.widget.photoview.RemotePhotoView;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PresentationViewPagerHolder extends RecyclerView.ViewHolder {
    private View mMediaPlayerView;
    private ImageView mPlayIcon;
    private final RemotePhotoView mRemotePhotoView;
    private final int mType;
    private final View mView;

    public PresentationViewPagerHolder(View view, int i2) {
        super(view);
        this.mView = view;
        this.mType = i2;
        this.mRemotePhotoView = (RemotePhotoView) view.findViewById(R.id.remote_photo);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$hideMediaView$2() {
        ViewUtils.setVisibility(this.mRemotePhotoView, 0);
        if (this.mType == 21) {
            ViewUtils.setVisibility(this.mPlayIcon, 0);
        }
        ViewUtils.setVisibility(this.mMediaPlayerView, 4);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$showMediaView$1() {
        ViewUtils.setVisibility(this.mMediaPlayerView, 0);
        ViewUtils.setVisibility(this.mPlayIcon, 4);
        ViewUtils.setVisibility(this.mRemotePhotoView, 4);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateImage$0(Bitmap bitmap) {
        this.mRemotePhotoView.setImage(bitmap, bitmap.getWidth(), bitmap.getHeight());
    }

    public void bindImage(Bitmap bitmap, MediaItem mediaItem) {
        this.mRemotePhotoView.setImage(mediaItem, bitmap);
        ViewUtils.setVisibility(this.mRemotePhotoView, 0);
        if (this.mType == 21) {
            ImageView imageView = (ImageView) this.mView.findViewById(R.id.mirror_play_icon);
            this.mPlayIcon = imageView;
            ViewUtils.setVisibility(imageView, 0);
        }
    }

    public View getMediaView() {
        ViewStub viewStub;
        if (this.mMediaPlayerView == null && (viewStub = (ViewStub) this.mView.findViewById(R.id.media_view_stub)) != null) {
            this.mMediaPlayerView = viewStub.inflate();
        }
        return this.mMediaPlayerView;
    }

    public ImageView getPlayView() {
        return this.mPlayIcon;
    }

    public void hideMediaView() {
        ThreadUtil.postOnUiThread(new C0425e(this, 0));
    }

    public void recycle() {
        ViewUtils.setVisibility(this.mRemotePhotoView, 8);
        ViewUtils.setVisibility(this.mMediaPlayerView, 8);
        this.mRemotePhotoView.recycle();
    }

    public void setMotionControl(PhotoViewMotionControl photoViewMotionControl) {
        this.mRemotePhotoView.createDefaultMotionControl(photoViewMotionControl);
        RemotePhotoView remotePhotoView = this.mRemotePhotoView;
        remotePhotoView.setMotionControl(remotePhotoView.getMotionControl(), (OnViewerExitGestureListener) null);
    }

    public void showMediaView() {
        ThreadUtil.postOnUiThread(new C0425e(this, 1));
    }

    public void updateImage(MediaItem mediaItem, Bitmap bitmap) {
        this.mRemotePhotoView.setImage(mediaItem, bitmap);
        this.mRemotePhotoView.setVisibility(0);
    }

    public void updateImage(Bitmap bitmap) {
        RemotePhotoView remotePhotoView = this.mRemotePhotoView;
        if (remotePhotoView != null && bitmap != null) {
            remotePhotoView.post(new c(2, this, bitmap));
        }
    }
}
