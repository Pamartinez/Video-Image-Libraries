package com.samsung.android.gallery.support.library.v0.media;

import A.a;
import N2.j;
import Qb.e;
import Ra.b;
import Ra.c;
import Ra.d;
import android.content.Context;
import android.media.MediaPlayer;
import android.media.PlaybackParams;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import c0.C0086a;
import com.samsung.android.gallery.support.library.abstraction.MediaPlayerCompat;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import i.C0212a;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class GedMediaPlayerCompat implements MediaPlayerCompat {
    protected final String TAG = getClass().getSimpleName();
    private MediaPlayerCompat.OnCompletionListener mCompletionListenerCompat;
    protected final int mConfig;
    private int mDuration;
    private MediaPlayerCompat.OnErrorListener mErrorListenerCompat;
    private Boolean mHasAudioTrack;
    private MediaPlayerCompat.OnInfoListener mInfoListenerCompat;
    protected final MediaPlayer mMediaPlayer;
    private MediaPlayerCompat.OnVideoSizeChangedListener mOnVideoSizeChangedListenerCompat;
    protected int mPlayerState = 0;
    private MediaPlayerCompat.OnPreparedListener mPreparedListenerCompat;
    private Object mTag;
    private final ConcurrentHashMap<String, Object> mTagMap = new ConcurrentHashMap<>();
    private final MediaTimeTick mTimeTick = new MediaTimeTick(new e(5, this));
    private int[] mVideoSize;

    public GedMediaPlayerCompat(int i2) {
        this.mConfig = i2;
        MediaPlayer mediaPlayer = new MediaPlayer();
        this.mMediaPlayer = mediaPlayer;
        mediaPlayer.setOnErrorListener(new b(this));
        mediaPlayer.setOnCompletionListener(new c(this));
    }

    private int[] getVideoSize() {
        try {
            int videoWidth = this.mMediaPlayer.getVideoWidth();
            int videoHeight = this.mMediaPlayer.getVideoHeight();
            if (videoWidth <= 0 || videoHeight <= 0) {
                return null;
            }
            return new int[]{videoWidth, videoHeight};
        } catch (Error | Exception e) {
            C0212a.y(e, new StringBuilder("getVideoSize failed e="), this.TAG);
            return null;
        }
    }

    /* access modifiers changed from: private */
    public void onCompletion(MediaPlayer mediaPlayer) {
        this.mPlayerState = 7;
        if (this.mTimeTick.isEnabled()) {
            this.mTimeTick.stop();
            if (computeTimeTickOnCompletion()) {
                onInfo(mediaPlayer, 9000001, getDurationMs());
            }
        }
        MediaPlayerCompat.OnCompletionListener onCompletionListener = this.mCompletionListenerCompat;
        if (onCompletionListener != null) {
            onCompletionListener.onCompletion(this);
        } else {
            onInfo(mediaPlayer, 9000005, getDurationMs());
        }
    }

    /* access modifiers changed from: private */
    public boolean onError(MediaPlayer mediaPlayer, int i2, int i7) {
        this.mPlayerState = -2;
        Log.e(this.TAG, a.d(i2, i7, "onError {", GlobalPostProcInternalPPInterface.SPLIT_REGEX, "}"));
        MediaPlayerCompat.OnErrorListener onErrorListener = this.mErrorListenerCompat;
        if (onErrorListener != null) {
            onErrorListener.onError(this, i2, i7);
            return true;
        }
        onInfo(mediaPlayer, 9000007, i7);
        return true;
    }

    /* access modifiers changed from: private */
    public boolean onInfo(MediaPlayer mediaPlayer, int i2, int i7) {
        MediaPlayerCompat.OnInfoListener onInfoListener = this.mInfoListenerCompat;
        if (onInfoListener == null || !onInfoListener.onInfo(this, i2, i7)) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public void onTimeTickUpdated() {
        MediaPlayerCompat.OnInfoListener onInfoListener = this.mInfoListenerCompat;
        if (onInfoListener != null && this.mMediaPlayer != null && this.mPlayerState == 4) {
            onInfoListener.onInfo(this, 9000001, getCurrentPositionMs());
        }
    }

    /* access modifiers changed from: private */
    public void onVideoSizeChangedListener(MediaPlayer mediaPlayer, int i2, int i7) {
        MediaPlayerCompat.OnVideoSizeChangedListener onVideoSizeChangedListener = this.mOnVideoSizeChangedListenerCompat;
        if (onVideoSizeChangedListener != null) {
            ((ic.e) onVideoSizeChangedListener).a(this, i2, i7);
        }
    }

    private Boolean retrieveAudioTrack() {
        try {
            for (MediaPlayer.TrackInfo trackType : this.mMediaPlayer.getTrackInfo()) {
                if (trackType.getTrackType() == 2) {
                    return Boolean.TRUE;
                }
            }
            return Boolean.FALSE;
        } catch (Error | Exception e) {
            Log.w(this.TAG, "hasAudioTrack failed. e=" + e.getMessage());
            return null;
        }
    }

    public boolean computeTimeTickOnCompletion() {
        return true;
    }

    public final int getCurrentPositionMs() {
        try {
            int i2 = this.mPlayerState;
            if (i2 == 7) {
                return getDurationMs();
            }
            if (i2 >= 3) {
                return this.mMediaPlayer.getCurrentPosition();
            }
            return 0;
        } catch (IllegalStateException e) {
            j.t(e, new StringBuilder("getCurrentPosition failed e="), this.TAG);
            return 0;
        }
    }

    public int getDurationMs() {
        if (this.mDuration == 0) {
            try {
                int duration = this.mMediaPlayer.getDuration();
                this.mDuration = duration;
                if (duration < 0) {
                    this.mDuration = 0;
                }
            } catch (IllegalStateException e) {
                j.t(e, new StringBuilder("getDuration failed e="), this.TAG);
                return 0;
            }
        }
        return this.mDuration;
    }

    public int getFrameRate() {
        return 0;
    }

    public PlaybackParams getPlaybackParam() {
        return this.mMediaPlayer.getPlaybackParams();
    }

    public int getPlaybackState() {
        return this.mPlayerState;
    }

    public Object getTag() {
        return this.mTag;
    }

    public int getVideoHeight() {
        if (this.mVideoSize == null) {
            this.mVideoSize = getVideoSize();
        }
        int[] iArr = this.mVideoSize;
        if (iArr != null) {
            return iArr[1];
        }
        return 0;
    }

    public int getVideoRotation() {
        return -1;
    }

    public int getVideoWidth() {
        if (this.mVideoSize == null) {
            this.mVideoSize = getVideoSize();
        }
        int[] iArr = this.mVideoSize;
        if (iArr != null) {
            return iArr[0];
        }
        return 0;
    }

    public boolean hasAudioTrack() {
        if (this.mHasAudioTrack == null) {
            this.mHasAudioTrack = retrieveAudioTrack();
        }
        Boolean bool = this.mHasAudioTrack;
        if (bool == null || bool.booleanValue()) {
            return true;
        }
        return false;
    }

    public boolean isPlaying() {
        try {
            return this.mMediaPlayer.isPlaying();
        } catch (Exception e) {
            j.D(e, new StringBuilder("isPlaying failed e="), this.TAG);
            return false;
        }
    }

    public boolean isPrepared() {
        if (this.mPlayerState > 2) {
            return true;
        }
        return false;
    }

    public boolean isPreparing() {
        if (this.mPlayerState == 2) {
            return true;
        }
        return false;
    }

    public void onPrepared(MediaPlayer mediaPlayer) {
        this.mVideoSize = getVideoSize();
        String str = this.TAG;
        Log.v(str, "onPrepared {" + getVideoWidth() + GlobalPostProcInternalPPInterface.SPLIT_REGEX + getVideoHeight() + "}");
        this.mPlayerState = 3;
        MediaPlayerCompat.OnPreparedListener onPreparedListener = this.mPreparedListenerCompat;
        if (onPreparedListener != null) {
            onPreparedListener.onPrepared(this);
        }
    }

    public boolean pause() {
        try {
            this.mTimeTick.stop();
            this.mPlayerState = 6;
            if (this.mMediaPlayer.isPlaying()) {
                this.mMediaPlayer.pause();
            }
            int renderingPosition = getRenderingPosition();
            if (renderingPosition < 0) {
                return true;
            }
            onInfo(this.mMediaPlayer, 9000004, renderingPosition);
            return true;
        } catch (IllegalStateException e) {
            j.t(e, new StringBuilder("pause failed e="), this.TAG);
            onInfo(this.mMediaPlayer, 9000007, 0);
            return false;
        }
    }

    public void prepareAsync() {
        try {
            this.mMediaPlayer.prepareAsync();
            this.mPlayerState = 2;
        } catch (IllegalStateException e) {
            Log.e(this.TAG, "prepareAsync failed", e);
            throw e;
        }
    }

    public void release() {
        try {
            this.mPlayerState = -1;
            this.mTimeTick.stop();
            this.mMediaPlayer.release();
        } catch (Exception e) {
            j.D(e, new StringBuilder("release failed e="), this.TAG);
        }
        onInfo(this.mMediaPlayer, 9000006, 0);
        this.mPreparedListenerCompat = null;
        this.mTagMap.clear();
        this.mTag = null;
    }

    public void reset() {
        try {
            String str = this.TAG;
            Log.i(str, "Reset " + this.mPlayerState + " @" + Integer.toHexString(this.mMediaPlayer.hashCode()));
            this.mPlayerState = 0;
            this.mMediaPlayer.reset();
            this.mPreparedListenerCompat = null;
        } catch (IllegalStateException e) {
            j.t(e, new StringBuilder("reset failed e="), this.TAG);
        }
    }

    public void seekTo(int i2) {
        try {
            if (this.mPlayerState == 7 && i2 < getDurationMs()) {
                this.mPlayerState = 6;
            }
            this.mMediaPlayer.seekTo((long) i2, 2);
        } catch (IllegalStateException e) {
            j.t(e, new StringBuilder("seekTo failed e="), this.TAG);
        }
    }

    public void seekToAdaptively(int i2) {
        seekTo(i2);
        this.mInfoListenerCompat.onInfo(this, 3, 0);
    }

    public void setAudioMute(boolean z) {
        float f;
        if (z) {
            f = 0.0f;
        } else {
            f = 1.0f;
        }
        this.mMediaPlayer.setVolume(f, f);
    }

    public void setDataSource(Context context, Uri uri) {
        ParcelFileDescriptor openFileDescriptor;
        try {
            this.mMediaPlayer.setDataSource(context, uri);
            this.mPlayerState = 1;
        } catch (Exception e) {
            j.D(e, new StringBuilder("setDataSource failed e="), this.TAG);
            try {
                openFileDescriptor = context.getContentResolver().openFileDescriptor(uri, "r");
                if (openFileDescriptor != null) {
                    this.mMediaPlayer.setDataSource(openFileDescriptor.getFileDescriptor());
                    this.mPlayerState = 1;
                } else {
                    String str = this.TAG;
                    Log.e(str, "setDataSource retry  fd is null : " + uri);
                }
                if (openFileDescriptor != null) {
                    openFileDescriptor.close();
                }
            } catch (Exception e7) {
                j.D(e7, new StringBuilder("setDataSource retry failed e2="), this.TAG);
                throw e7;
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
        } catch (Throwable th2) {
            if (openFileDescriptor != null) {
                openFileDescriptor.close();
            }
            throw th2;
        }
    }

    public void setDisplay(SurfaceHolder surfaceHolder) {
        this.mMediaPlayer.setDisplay(surfaceHolder);
    }

    public void setLooping(boolean z) {
        this.mMediaPlayer.setLooping(z);
    }

    public void setOnCompletionListener(MediaPlayerCompat.OnCompletionListener onCompletionListener) {
        this.mCompletionListenerCompat = onCompletionListener;
    }

    public void setOnErrorListener(MediaPlayerCompat.OnErrorListener onErrorListener) {
        this.mErrorListenerCompat = onErrorListener;
    }

    public void setOnInfoListener(MediaPlayerCompat.OnInfoListener onInfoListener) {
        this.mInfoListenerCompat = onInfoListener;
        this.mMediaPlayer.setOnInfoListener(new d(this));
    }

    public void setOnPreparedListener(MediaPlayerCompat.OnPreparedListener onPreparedListener) {
        this.mPreparedListenerCompat = onPreparedListener;
        this.mMediaPlayer.setOnPreparedListener(new Ra.a(this));
    }

    public void setOnVideoSizeChangedListener(MediaPlayerCompat.OnVideoSizeChangedListener onVideoSizeChangedListener) {
        this.mOnVideoSizeChangedListenerCompat = onVideoSizeChangedListener;
        this.mMediaPlayer.setOnVideoSizeChangedListener(new Ra.e(this));
    }

    public void setPlaybackParam(PlaybackParams playbackParams) {
        this.mMediaPlayer.setPlaybackParams(playbackParams);
    }

    public void setSurface(Surface surface) {
        this.mMediaPlayer.setSurface(surface);
    }

    public void setTag(Object obj) {
        this.mTag = obj;
    }

    public void setTimeTickEnabled(boolean z) {
        this.mTimeTick.setEnabled(z);
    }

    public void setVolume(float f, float f5) {
        this.mMediaPlayer.setVolume(f, f5);
    }

    public void start() {
        try {
            String str = this.TAG;
            Log.i(str, "Start " + this.mPlayerState + " @" + Integer.toHexString(this.mMediaPlayer.hashCode()));
            this.mMediaPlayer.start();
            this.mPlayerState = 4;
            this.mTimeTick.start(getDurationMs() / 100);
            onInfo(this.mMediaPlayer, 9000002, getCurrentPositionMs());
        } catch (IllegalStateException e) {
            j.t(e, new StringBuilder("start failed e="), this.TAG);
            throw e;
        }
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder();
        sb2.append(this.TAG);
        sb2.append(com.samsung.android.sdk.scs.base.utils.Log.TAG_SEPARATOR);
        sb2.append(Integer.toHexString(hashCode()));
        sb2.append("{");
        sb2.append(this.mPlayerState);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(isPlaying());
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(getCurrentPositionMs());
        sb2.append("|");
        sb2.append(getVideoWidth());
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(getVideoHeight());
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        return C0086a.l(sb2, this.mDuration, "}");
    }

    public void setDataSource(String str) {
        try {
            this.mMediaPlayer.setDataSource(str);
            this.mPlayerState = 1;
        } catch (Exception e) {
            j.D(e, new StringBuilder("setDataSource failed e="), this.TAG);
            throw e;
        }
    }

    @Deprecated
    public void setDataSource(FileDescriptor fileDescriptor, long j2, long j3) {
        try {
            this.mMediaPlayer.setDataSource(fileDescriptor, j2, j3);
            this.mPlayerState = 1;
        } catch (Exception e) {
            Exception exc = e;
            j.D(exc, new StringBuilder("setDataSource failed e="), this.TAG);
            throw exc;
        }
    }

    public void setDataSource(String str, long j2, long j3) {
        Throwable th;
        FileInputStream fileInputStream = new FileInputStream(str);
        try {
            setDataSource(fileInputStream.getFD(), j2, j3);
            fileInputStream.close();
            return;
        } catch (Throwable th2) {
            th.addSuppressed(th2);
        }
        throw th;
    }
}
