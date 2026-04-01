package com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.imageloader;

import D7.c;
import D7.d;
import D7.e;
import android.graphics.Bitmap;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.FragmentLifeCycle;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObject;
import com.samsung.android.gallery.app.ui.viewer2.model.ContentModel;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.media.GifAnimation;
import com.samsung.android.gallery.module.media.GifAnimationFactory;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.StringCompat;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class GifImageLoader extends ViewerObject implements FragmentLifeCycle {
    private GifAnimation mGifAnimation;
    private boolean mIsAudioEnabled;

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$0(Object[] objArr) {
        this.mIsAudioEnabled = objArr[0].booleanValue();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$1(Object[] objArr) {
        stopGif();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onAnimationFrameStart$5() {
        if (this.mIsAudioEnabled) {
            this.mActionInvoker.invoke(ViewerAction.AUDIO_RESTART, new Object[0]);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$playGif$2() {
        MediaItem mediaItem = this.mModel.getMediaItem();
        if (mediaItem == null) {
            Log.w(this.TAG, "fail to play Gif : null item");
            return;
        }
        if (this.mGifAnimation == null) {
            long currentTimeMillis = System.currentTimeMillis();
            this.mGifAnimation = createGifAnimation(mediaItem);
            StringCompat stringCompat = this.TAG;
            Log.d(stringCompat, "createGifAnimation" + Logger.vt(currentTimeMillis));
        }
        if (this.mGifAnimation.isAnimation()) {
            this.mActionInvoker.invoke(ViewerAction.GIF_ANIMATION_MODE, new Object[0]);
            this.mGifAnimation.start();
            return;
        }
        mediaItem.setSingleFrameMovie();
        if (mediaItem.isWebp()) {
            this.mActionInvoker.invoke(ViewerAction.WEBP_SET_DONE, new Object[0]);
        }
        Log.w(this.TAG, "fail to play : not animated image");
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$releaseGif$4() {
        GifAnimation gifAnimation = this.mGifAnimation;
        if (gifAnimation != null) {
            gifAnimation.release();
            this.mGifAnimation = null;
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$stopGif$3() {
        GifAnimation gifAnimation = this.mGifAnimation;
        if (gifAnimation != null) {
            gifAnimation.stop();
        }
    }

    /* access modifiers changed from: private */
    public void onUpdatedRemasterStatus(Object... objArr) {
        boolean z = false;
        if (objArr[0].arg1 == 0) {
            z = true;
        }
        releaseGif();
        if (z) {
            playGif();
        }
    }

    public void addActionInvokeListener() {
        this.mActionInvoker.add(ViewerAction.AUDIO_ENABLED, new d(this, 0));
        this.mActionInvoker.add(ViewerAction.STOP_GIF, new d(this, 1));
        this.mActionInvoker.add(ViewerAction.UPDATE_REMASTER_STATUS, new d(this, 2));
    }

    public GifAnimation createGifAnimation(MediaItem mediaItem) {
        return GifAnimationFactory.getDecodedAnimation(this.mModel.getContext(), mediaItem).setAnimationFrameStartListener(new e(this)).setAnimationFrameUpdateListener(new e(this));
    }

    public boolean handleBlackboardEvent(EventMessage eventMessage) {
        int i2 = eventMessage.what;
        if (i2 == 3050) {
            releaseGif();
            playGif();
            return true;
        } else if (i2 == 3051) {
            onUpdatedRemasterStatus(eventMessage);
            return false;
        } else if (i2 != 6016) {
            return false;
        } else {
            releaseGif();
            return false;
        }
    }

    public void onAnimationFrameStart() {
        this.mThread.runOnUiThread(new c(this, 3));
    }

    public void onAnimationFrameUpdated(Bitmap bitmap) {
        ContentModel contentModel = this.mModel;
        if (contentModel.setBitmap(bitmap, contentModel.getMediaItem())) {
            this.mActionInvoker.invoke(ViewerAction.UPDATE_BITMAP_ANIM, bitmap);
        }
    }

    public void onPause() {
        stopGif();
    }

    public void onResume() {
        playGif();
    }

    public void onViewConfirm() {
        super.onViewConfirm();
        playGif();
    }

    public void onViewDetached() {
        super.onViewDetached();
        stopGif();
    }

    public void onViewRecycled() {
        super.onViewRecycled();
        releaseGif();
        this.mIsAudioEnabled = false;
    }

    public void playGif() {
        this.mThread.runOnBgThread(new c(this, 1));
    }

    public void releaseGif() {
        this.mThread.cancelBgThread((Runnable) null);
        this.mThread.runOnBgThread(new c(this, 0));
    }

    public void stopGif() {
        this.mThread.runOnBgThread(new c(this, 2));
    }
}
