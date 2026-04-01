package com.samsung.android.gallery.app.remote;

import A.a;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.util.Log;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.app.remote.v2.GalleryPresentation;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.graphics.BitmapOptionsFactory;
import com.samsung.android.gallery.module.graphics.BitmapSizeHolder;
import com.samsung.android.gallery.module.graphics.BitmapUtils;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.threadpool.ThreadPool;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.photoview.PhotoViewMotionControl;
import com.samsung.android.gallery.widget.utils.ResourceUtil;
import com.samsung.android.sivs.ai.sdkcommon.tts.TextToSpeechConst;
import java.io.FileInputStream;
import java.util.Timer;
import java.util.TimerTask;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class Slideshow {
    private Bitmap mBitmap;
    private int mDisplayPosition;
    private boolean mIsRunning;
    private final MediaData mMediaData;
    private MediaItem mMediaItem;
    private int mPosition;
    private final GalleryPresentation mPresentation;
    private Timer mTimer;
    private final int mTimerInterval = TextToSpeechConst.MAX_SPEECH_INPUT;
    private TimerTask mTimerTask;

    public Slideshow(GalleryPresentation galleryPresentation, MediaData mediaData) {
        this.mPresentation = galleryPresentation;
        this.mMediaData = mediaData;
    }

    private Bitmap getBitmap(MediaItem mediaItem) {
        if (this.mMediaItem == mediaItem) {
            return this.mBitmap;
        }
        return null;
    }

    private Bitmap getVideoThumbnail(String str) {
        FileInputStream fileInputStream;
        MediaMetadataRetriever mediaMetadataRetriever;
        try {
            fileInputStream = new FileInputStream(str);
            mediaMetadataRetriever = new MediaMetadataRetriever();
            mediaMetadataRetriever.setDataSource(fileInputStream.getFD());
            Bitmap videoFrameAtTime = SeApiCompat.getVideoFrameAtTime(mediaMetadataRetriever, 0, 2);
            mediaMetadataRetriever.close();
            fileInputStream.close();
            return videoFrameAtTime;
            throw th;
            throw th;
        } catch (Exception e) {
            a.s(e, new StringBuilder("fail to get video thumbnail="), "SlideshowServiceOnRemoteV2");
            return null;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onSlideShowTimer$0() {
        GalleryPresentation galleryPresentation;
        boolean z;
        MediaItem mediaItem;
        MediaData mediaData = this.mMediaData;
        if (mediaData == null || mediaData.getCount() == 0 || (galleryPresentation = this.mPresentation) == null) {
            Log.e("SlideshowServiceOnRemoteV2", "onSlideShowTimer skip. null media data or count is zero or no presentation");
            return;
        }
        galleryPresentation.clearController();
        MediaItem read = this.mMediaData.read(this.mPosition);
        if (this.mMediaData.getCount() > this.mDisplayPosition) {
            this.mDisplayPosition = this.mPosition;
        }
        StringBuilder sb2 = new StringBuilder("onSlideShowTimer [");
        sb2.append(this.mPosition);
        sb2.append("]  [");
        sb2.append(this.mDisplayPosition);
        sb2.append("]");
        sb2.append(MediaItemUtil.getSimpleLog(read));
        sb2.append(" > ");
        boolean z3 = false;
        if (read == this.mMediaItem) {
            z = true;
        } else {
            z = false;
        }
        sb2.append(z);
        sb2.append(ArcCommonLog.TAG_COMMA);
        if (this.mBitmap != null) {
            z3 = true;
        }
        sb2.append(z3);
        Log.d("SlideshowServiceOnRemoteV2", sb2.toString());
        Bitmap bitmap = getBitmap(read);
        if (!(bitmap == null || (mediaItem = this.mMediaItem) == null)) {
            this.mPresentation.updateData(bitmap, (PhotoViewMotionControl) null, mediaItem, this.mDisplayPosition, useInternalPlay(read));
        }
        int i2 = this.mPosition + 1;
        this.mPosition = i2;
        this.mDisplayPosition++;
        this.mPosition = i2 % this.mMediaData.getCount();
        prepareBitmap();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Boolean lambda$prepareBitmap$1(MediaItem mediaItem, ThreadPool.JobContext jobContext) {
        if (!mediaItem.isBroken()) {
            if (mediaItem.isImage()) {
                this.mBitmap = BitmapUtils.getDecodedBitmap(mediaItem.getPath(), BitmapOptionsFactory.of(mediaItem).adjustInSampleSize(BitmapSizeHolder.size()));
            } else if (mediaItem.isVideo()) {
                this.mBitmap = resizeBitmap(getVideoThumbnail(mediaItem.getPath()), 1920);
            }
            if (this.mBitmap == null) {
                this.mBitmap = ThumbnailLoader.getInstance().loadThumbnailSync(mediaItem, ThumbKind.MEDIUM_KIND);
            }
            if (this.mBitmap == null) {
                com.samsung.android.gallery.support.utils.Log.e("SlideshowServiceOnRemoteV2", "prepareBitmap [" + this.mPosition + "] broken " + MediaItemUtil.getSimpleLog(mediaItem));
                return Boolean.FALSE;
            }
        } else {
            this.mBitmap = ThumbnailLoader.getInstance().getReplacedThumbnail(Blackboard.getContext(), ResourceUtil.getBrokenDrawable(this.mMediaItem), ResourceUtil.getBrokenBgColor(this.mMediaItem));
        }
        this.mMediaItem = mediaItem;
        return Boolean.TRUE;
    }

    /* access modifiers changed from: private */
    public void onSlideShowTimer() {
        ThreadUtil.postOnUiThread(new b(2, this));
    }

    private void prepareBitmap() {
        MediaItem mediaItem = null;
        this.mBitmap = null;
        this.mMediaItem = null;
        MediaData mediaData = this.mMediaData;
        if (mediaData != null) {
            mediaItem = mediaData.read(this.mPosition);
        }
        if (mediaItem != null) {
            ThreadPool.getInstance().submit(new a(this, mediaItem));
        }
    }

    private static Bitmap resizeBitmap(Bitmap bitmap, int i2) {
        if (bitmap == null) {
            return bitmap;
        }
        float max = ((float) i2) / ((float) Math.max(bitmap.getWidth(), bitmap.getHeight()));
        if (max < 0.9f) {
            return BitmapUtils.resize(bitmap, max);
        }
        return bitmap;
    }

    private boolean useInternalPlay(MediaItem mediaItem) {
        if (PreferenceFeatures.Remote.PLAY_VIDEO_IN_SLIDESHOW_PRESENTATION || !mediaItem.isVideo()) {
            return true;
        }
        return false;
    }

    public void init(int i2) {
        this.mPosition = i2;
    }

    public boolean isRunning() {
        return this.mIsRunning;
    }

    public void start() {
        this.mIsRunning = true;
        prepareBitmap();
        this.mTimer = new Timer(true);
        AnonymousClass1 r32 = new TimerTask() {
            public void run() {
                Slideshow.this.checkSlideshowService();
                Slideshow.this.onSlideShowTimer();
            }
        };
        this.mTimerTask = r32;
        Timer timer = this.mTimer;
        int i2 = this.mTimerInterval;
        timer.schedule(r32, (long) (i2 / 3), (long) i2);
    }

    public void stop() {
        if (this.mIsRunning) {
            this.mIsRunning = false;
            TimerTask timerTask = this.mTimerTask;
            if (timerTask != null) {
                timerTask.cancel();
                this.mTimerTask = null;
            }
            Timer timer = this.mTimer;
            if (timer != null) {
                timer.cancel();
                this.mTimer = null;
            }
        }
    }

    /* access modifiers changed from: private */
    public void checkSlideshowService() {
    }
}
