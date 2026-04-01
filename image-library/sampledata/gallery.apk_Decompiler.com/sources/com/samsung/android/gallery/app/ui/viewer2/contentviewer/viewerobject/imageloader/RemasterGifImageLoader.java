package com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.imageloader;

import A.a;
import D7.j;
import D7.k;
import D7.l;
import android.graphics.Bitmap;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.FragmentLifeCycle;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObject;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.media.GifAnimation;
import com.samsung.android.gallery.module.media.GifAnimationFactory;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.sum.core.Def;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RemasterGifImageLoader extends ViewerObject implements FragmentLifeCycle {
    private GifAnimation mAfterGifAnimation;
    private GifAnimation mBeforeGifAnimation;
    private RemasterGifPair mGifPair;
    private boolean mIsRemastered;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class RemasterGifPair {
        private Bitmap mAfter;
        private int mAfterIndex = 0;
        private Bitmap mBefore;
        private int mBeforeIndex = 0;

        private void waitAnother() {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        private boolean withInTimeOut(long j2) {
            if (System.currentTimeMillis() - j2 < Def.MEDIAFILTER_MESSAGE_TIMEOUT_MILLIS) {
                return true;
            }
            Log.w("RemasterGifImageLoader", "failed to sync gif bitmap pair by time out");
            return false;
        }

        public void clear() {
            this.mBeforeIndex = 0;
            this.mAfterIndex = 0;
            this.mBefore = null;
            this.mAfter = null;
        }

        public Bitmap getAfterImage() {
            return this.mAfter;
        }

        public Bitmap getBeforeImage() {
            return this.mBefore;
        }

        public boolean isReady() {
            if (this.mBeforeIndex == this.mAfterIndex) {
                return true;
            }
            return false;
        }

        public void setAfter(Bitmap bitmap) {
            long currentTimeMillis = System.currentTimeMillis();
            while (this.mAfterIndex > this.mBeforeIndex && withInTimeOut(currentTimeMillis)) {
                waitAnother();
            }
            try {
                this.mAfter = bitmap.copy(bitmap.getConfig(), true);
                this.mAfterIndex++;
            } catch (Exception e) {
                a.s(e, new StringBuilder("failed to set after image, e="), "RemasterGifImageLoader");
            }
        }

        public void setBefore(Bitmap bitmap) {
            long currentTimeMillis = System.currentTimeMillis();
            while (this.mBeforeIndex > this.mAfterIndex && withInTimeOut(currentTimeMillis)) {
                waitAnother();
            }
            try {
                this.mBefore = bitmap.copy(bitmap.getConfig(), true);
                this.mBeforeIndex++;
            } catch (Exception e) {
                a.s(e, new StringBuilder("failed to set before image, e="), "RemasterGifImageLoader");
            }
        }
    }

    private GifAnimation createGifAnimation(MediaItem mediaItem, boolean z) {
        return GifAnimationFactory.getDecodedRemasterAnimation(this.mModel.getContext(), mediaItem).setAnimationFrameUpdateListener(new l(this, z));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$0(Object[] objArr) {
        stopGif();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$playGif$2() {
        MediaItem originMediaItem = this.mModel.getOriginMediaItem();
        if (originMediaItem != null) {
            if (this.mBeforeGifAnimation == null) {
                this.mBeforeGifAnimation = createGifAnimation(originMediaItem, false);
            }
            playGif(this.mBeforeGifAnimation);
        }
        MediaItem remasteredMediaItem = this.mModel.getRemasteredMediaItem();
        if (remasteredMediaItem != null) {
            if (this.mAfterGifAnimation == null) {
                this.mAfterGifAnimation = createGifAnimation(remasteredMediaItem, true);
            }
            playGif(this.mAfterGifAnimation);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$releaseGif$4() {
        release(this.mBeforeGifAnimation);
        release(this.mAfterGifAnimation);
        this.mBeforeGifAnimation = null;
        this.mAfterGifAnimation = null;
        RemasterGifPair remasterGifPair = this.mGifPair;
        if (remasterGifPair != null) {
            remasterGifPair.clear();
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$stopGif$3() {
        stopGif(this.mBeforeGifAnimation);
        stopGif(this.mAfterGifAnimation);
    }

    private void playGif() {
        this.mThread.runOnBgThread(new j(this, 1));
    }

    private void release(GifAnimation gifAnimation) {
        if (gifAnimation != null) {
            gifAnimation.release();
        }
    }

    private void releaseGif() {
        this.mThread.cancelBgThread((Runnable) null);
        this.mThread.runOnBgThread(new j(this, 0));
    }

    public void addActionInvokeListener() {
        this.mActionInvoker.add(ViewerAction.ON_READY_REMASTER_VIEW, new k(this, 0));
        this.mActionInvoker.add(ViewerAction.STOP_GIF, new k(this, 1));
    }

    /* renamed from: onAnimationFrameUpdated */
    public void lambda$createGifAnimation$1(Bitmap bitmap, boolean z) {
        RemasterGifPair remasterGifPair = this.mGifPair;
        if (remasterGifPair != null) {
            if (z) {
                remasterGifPair.setAfter(bitmap);
            } else {
                remasterGifPair.setBefore(bitmap);
            }
            if (this.mGifPair.isReady()) {
                this.mActionInvoker.invoke(ViewerAction.UPDATE_REMASTER_GIF_BITMAP, this.mGifPair.getBeforeImage(), this.mGifPair.getAfterImage());
            }
        }
    }

    public void onDemandRemastered(Object... objArr) {
        this.mIsRemastered = true;
        playGif();
    }

    public void onInitialized() {
        super.onInitialized();
        this.mGifPair = new RemasterGifPair();
    }

    public void onPause() {
        stopGif();
    }

    public void onResume() {
        if (this.mIsRemastered) {
            playGif();
        }
    }

    public void onViewConfirm() {
        super.onViewConfirm();
        if (this.mIsRemastered) {
            playGif();
        }
    }

    public void onViewDetached() {
        super.onViewDetached();
        stopGif();
    }

    public void onViewRecycled() {
        super.onViewRecycled();
        releaseGif();
    }

    public void stopGif() {
        this.mThread.runOnBgThread(new j(this, 2));
    }

    private void playGif(GifAnimation gifAnimation) {
        gifAnimation.start();
    }

    private void stopGif(GifAnimation gifAnimation) {
        if (gifAnimation != null) {
            gifAnimation.stop();
        }
    }
}
