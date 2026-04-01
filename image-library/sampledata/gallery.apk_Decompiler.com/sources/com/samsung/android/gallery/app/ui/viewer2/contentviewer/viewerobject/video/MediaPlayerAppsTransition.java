package com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.video;

import H7.r;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.core.content.ContextCompat;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.FragmentLifeCycle;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObject;
import com.samsung.android.gallery.module.abstraction.VideoPropData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.graphics.BitmapUtils;
import com.samsung.android.gallery.module.transition.SharedMemoryUtil;
import com.samsung.android.gallery.module.utils.ShareList;
import com.samsung.android.gallery.support.utils.AndroidCompat;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.UnsafeCast;
import com.samsung.android.gallery.widget.photoview.PhotoViewCompat;
import com.samsung.android.gallery.widget.videoview.mediaplayer.IMediaPlayerView;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MediaPlayerAppsTransition extends ViewerObject implements FragmentLifeCycle {
    private final boolean SUPPORT_VIDEO_CONTROL = PreferenceFeatures.VideoPlayerFeature.isOpenInGalleryVideoPlayer();
    private IMediaPlayerView mMediaPlayerView;
    private BroadcastReceiver mPlayerReceiver;
    private PhotoViewCompat mPreview;
    protected Bundle mVideoAppBundle;

    private Bitmap getBitmapForAppTransition() {
        Bitmap bitmap;
        IMediaPlayerView iMediaPlayerView = this.mMediaPlayerView;
        if (iMediaPlayerView != null) {
            bitmap = iMediaPlayerView.pauseAndCapture();
            PhotoViewCompat photoViewCompat = this.mPreview;
            if (!(photoViewCompat == null || bitmap == null)) {
                photoViewCompat.clearBitmap();
                this.mPreview.setImage(bitmap, bitmap.getWidth(), bitmap.getHeight());
                this.mPreview.setVisibility(0);
            }
        } else {
            bitmap = null;
        }
        if (bitmap == null) {
            return setPreviewWithThumbnail();
        }
        return bitmap;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$0(Object[] objArr) {
        this.mPreview = objArr[0];
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$1(Object[] objArr) {
        this.mMediaPlayerView = objArr[0];
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$2(Object[] objArr) {
        restorePreviewFromCaptured();
    }

    /* access modifiers changed from: private */
    public void onPrepareVideoAppTransition(Object... objArr) {
        IMediaPlayerView iMediaPlayerView = this.mMediaPlayerView;
        if (iMediaPlayerView != null) {
            if (iMediaPlayerView.isPlaying()) {
                this.mMediaPlayerView.pauseVideo();
            }
            this.mMediaPlayerView.setDefaultPosition(false);
        }
        registerFinishCallback(this.mModel.getContext());
    }

    /* access modifiers changed from: private */
    public void onPrepareVideoEditorTransition(Object... objArr) {
        IMediaPlayerView iMediaPlayerView = this.mMediaPlayerView;
        if (iMediaPlayerView != null) {
            iMediaPlayerView.setDefaultPosition(false);
            this.mMediaPlayerView.setBackgroundColor(ContextCompat.getColor(this.mModel.getContext(), R.color.daynight_default_background));
            saveBitmapToSharedMemory(getBitmapForAppTransition());
            this.mMediaPlayerView.close();
        }
        registerFinishCallback(this.mModel.getContext());
    }

    /* access modifiers changed from: private */
    public void onReenterFromVideoEditor(Object... objArr) {
        Bundle bundle = objArr[0];
        boolean booleanValue = objArr[1].booleanValue();
        MediaItem mediaItem = this.mModel.getMediaItem();
        if (bundle == null || mediaItem == null) {
            Log.ate(this.TAG, "onReenterFromVideoEditingApp data is null.");
            return;
        }
        int i2 = bundle.getInt("video_seek_position");
        StringCompat stringCompat = this.TAG;
        Log.at(stringCompat, "10. onReenterFromVideoEditingApp {" + i2 + GlobalPostProcInternalPPInterface.SPLIT_REGEX + booleanValue + "}");
        this.mModel.setVideoSeekPosition(Math.max(i2, 0));
        updatePreviewForAppTransition(bundle);
        IMediaPlayerView iMediaPlayerView = this.mMediaPlayerView;
        if (iMediaPlayerView != null) {
            iMediaPlayerView.setRenderingPosition(i2);
            if (booleanValue) {
                this.mMediaPlayerView.setVideoCaptured(-1, (Bitmap) null);
            }
        }
        if (supportAdvancedVideoPreview()) {
            this.mActionInvoker.invoke(ViewerAction.REQUEST_PLAY_TIME_UPDATED, Integer.valueOf(VideoPropData.getVideoDuration(mediaItem)), Integer.valueOf(i2), mediaItem);
        }
    }

    private void onResumeFromVideoPlayer() {
        MediaItem mediaItem;
        Bundle bundle = this.mVideoAppBundle;
        if (bundle != null) {
            String string = bundle.getString("currentUri");
            if (isViewingItemEqual(string)) {
                int i2 = this.mVideoAppBundle.getInt("resumePos");
                StringCompat stringCompat = this.TAG;
                Log.at(stringCompat, "onResumedFromVideoPlayer {" + i2 + "}");
                int max = Math.max(i2, 0);
                this.mModel.setVideoSeekPosition(max);
                IMediaPlayerView iMediaPlayerView = this.mMediaPlayerView;
                if (iMediaPlayerView != null) {
                    iMediaPlayerView.setRenderingPosition(max);
                }
                updatePreviewForAppTransition(this.mVideoAppBundle);
                IMediaPlayerView iMediaPlayerView2 = this.mMediaPlayerView;
                if (iMediaPlayerView2 != null) {
                    iMediaPlayerView2.setBackgroundColor(0);
                }
                if (supportAdvancedVideoPreview() && (mediaItem = this.mModel.getMediaItem()) != null) {
                    this.mActionInvoker.invoke(ViewerAction.REQUEST_PLAY_TIME_UPDATED, Integer.valueOf(VideoPropData.getVideoDuration(mediaItem)), Integer.valueOf(i2), this.mModel.getMediaItem());
                }
                this.mActionInvoker.invoke(ViewerAction.RESUME_FROM_VIDEO_PLAYER, new Object[0]);
                return;
            }
            StringCompat stringCompat2 = this.TAG;
            Log.atw(stringCompat2, "onResumeFromVideoPlayer not matched {" + string + "}");
            setPreviewWithThumbnail();
        }
    }

    /* access modifiers changed from: private */
    public void prepareLastFrame(Object... objArr) {
        Bitmap pauseAndCapture;
        IMediaPlayerView iMediaPlayerView = this.mMediaPlayerView;
        if (iMediaPlayerView != null && (pauseAndCapture = iMediaPlayerView.pauseAndCapture()) != null) {
            this.mModel.setBitmap(pauseAndCapture, this.mModel.getMediaItem());
            this.mPreview.setImage(pauseAndCapture, pauseAndCapture.getWidth(), pauseAndCapture.getHeight());
        }
    }

    private void restorePreviewFromCaptured() {
        Bitmap bitmap = this.mModel.getBitmap();
        if (bitmap != null) {
            this.mPreview.setImage(bitmap, bitmap.getWidth(), bitmap.getHeight());
        } else {
            Log.i(this.TAG, "restorePreviewFromCaptured fail");
        }
    }

    private void saveBitmapToSharedMemory(Bitmap bitmap) {
        byte[] bArr;
        boolean z;
        if (bitmap == null) {
            bArr = null;
        } else {
            bArr = BitmapUtils.getJpegFromBitmap(bitmap, 1920);
        }
        ShareList.setSharedMemory(bArr);
        StringCompat stringCompat = this.TAG;
        StringBuilder sb2 = new StringBuilder("VideoEditingApp#SharedMemory {");
        if (bitmap != null) {
            z = true;
        } else {
            z = false;
        }
        sb2.append(z);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(ShareList.getSharedMemoryHash());
        sb2.append("}");
        Log.at(stringCompat, sb2.toString());
    }

    private Bitmap setPreviewWithThumbnail() {
        Bitmap bitmap = this.mModel.getBitmap();
        if (this.mPreview == null || bitmap == null || bitmap.isRecycled()) {
            StringCompat stringCompat = this.TAG;
            Log.d(stringCompat, "setPreviewWithThumbnail failed, " + Logger.toSimpleString(bitmap));
            return bitmap;
        }
        Log.d(this.TAG, "setPreviewWithThumbnail", bitmap);
        this.mPreview.setImageBitmap(bitmap);
        this.mPreview.setVisibility(0);
        return bitmap;
    }

    private boolean supportAdvancedVideoPreview() {
        if (!this.SUPPORT_VIDEO_CONTROL || this.mModel.isSingleTakenShot()) {
            return false;
        }
        return true;
    }

    private void updatePreviewForAppTransition(Bundle bundle) {
        Bitmap fetchBitmap = SharedMemoryUtil.fetchBitmap(bundle);
        if (this.mPreview == null || fetchBitmap == null) {
            setPreviewWithThumbnail();
            return;
        }
        MediaItem mediaItem = this.mModel.getMediaItem();
        this.mPreview.clearBitmap();
        if (mediaItem != null) {
            this.mPreview.setImage(mediaItem, fetchBitmap);
            this.mModel.setBitmap(fetchBitmap, mediaItem);
            this.mPreview.setVisibility(0);
            return;
        }
        Log.e(this.TAG, "updatePreviewForAppTransition fail");
    }

    public void addActionInvokeListener() {
        this.mActionInvoker.add(ViewerAction.IMAGE_PHOTO_VIEW, new r(this, 0));
        this.mActionInvoker.add(ViewerAction.MEDIA_VIEW, new r(this, 1));
        this.mActionInvoker.add(ViewerAction.PREPARE_VIDEO_APP_TRANSITION, new r(this, 2));
        this.mActionInvoker.add(ViewerAction.PREPARE_VIDEO_EDITOR_TRANSITION, new r(this, 3));
        this.mActionInvoker.add(ViewerAction.REENTER_FROM_VIDEO_EDITOR, new r(this, 4));
        this.mActionInvoker.add(ViewerAction.RESTORE_VIDEO_DEFAULT_PREVIEW, new r(this, 5));
        this.mActionInvoker.add(ViewerAction.PREPARE_LAST_VIDEO_FRAME, new r(this, 6));
    }

    public boolean isViewingItemEqual(String str) {
        long j2;
        MediaItem mediaItem = this.mModel.getMediaItem();
        long j3 = -1;
        if (TextUtils.isEmpty(str)) {
            j2 = -1;
        } else {
            j2 = UnsafeCast.toLong(str.substring(str.lastIndexOf("/") + 1), -1);
        }
        if (mediaItem != null) {
            j3 = mediaItem.getMediaId();
        }
        if (j2 == j3) {
            return true;
        }
        return false;
    }

    public void onResume() {
        if (this.mVideoAppBundle != null) {
            onResumeFromVideoPlayer();
            this.mVideoAppBundle = null;
        }
        unregisterPlayerReceiver(this.mModel.getContext());
    }

    public void onViewDetached() {
        super.onViewDetached();
        unregisterPlayerReceiver(this.mModel.getContext());
    }

    public void onViewRecycled() {
        super.onViewRecycled();
        unregisterPlayerReceiver(this.mModel.getContext());
    }

    public void registerFinishCallback(Context context) {
        if (Features.isEnabled(Features.SUPPORT_APP_TRANSITION) && this.mPlayerReceiver == null) {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("com.samsung.android.video.action_share_current_frame_info");
            AnonymousClass1 r1 = new BroadcastReceiver() {
                public void onReceive(Context context, Intent intent) {
                    if (intent != null && "com.samsung.android.video.action_share_current_frame_info".equals(intent.getAction())) {
                        MediaPlayerAppsTransition.this.mVideoAppBundle = intent.getExtras();
                        Bundle bundle = MediaPlayerAppsTransition.this.mVideoAppBundle;
                        if (bundle != null) {
                            bundle.putString("launch_from", "com.samsung.android.video");
                        }
                    }
                }
            };
            this.mPlayerReceiver = r1;
            AndroidCompat.registerReceiver(context, r1, intentFilter);
        }
    }

    public synchronized void unregisterPlayerReceiver(Context context) {
        BroadcastReceiver broadcastReceiver;
        try {
            if (Features.isEnabled(Features.SUPPORT_APP_TRANSITION) && (broadcastReceiver = this.mPlayerReceiver) != null) {
                AndroidCompat.unregisterReceiver(context, broadcastReceiver);
                this.mPlayerReceiver = null;
            }
            this.mVideoAppBundle = null;
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
    }
}
