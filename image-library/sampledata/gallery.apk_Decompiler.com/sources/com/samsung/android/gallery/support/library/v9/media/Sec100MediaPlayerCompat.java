package com.samsung.android.gallery.support.library.v9.media;

import A.a;
import N2.j;
import V3.b;
import Xa.c;
import Xa.d;
import android.content.Context;
import android.media.PlaybackParams;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.text.TextUtils;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import c0.C0086a;
import com.samsung.android.gallery.support.library.abstraction.FileDataSource;
import com.samsung.android.gallery.support.library.abstraction.MediaPlayerCompat;
import com.samsung.android.gallery.support.library.v0.media.MediaTimeTick;
import com.samsung.android.media.SemMediaPlayer;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import i.C0212a;
import ic.e;
import java.io.File;
import java.io.FileDescriptor;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Sec100MediaPlayerCompat implements MediaPlayerCompat {
    protected final String TAG = getClass().getSimpleName();
    private Context mAppContext;
    private MediaPlayerCompat.OnCompletionListener mCompletionListenerCompat;
    protected final int mConfig;
    private String mDataSource;
    private Uri mDataSourceUri;
    private int mDuration;
    private MediaPlayerCompat.OnErrorListener mErrorListenerCompat;
    private FileDataSource mFileDataSource;
    private FileDescriptor mFileDescriptor;
    private int mFrameRate;
    private Boolean mHasAudioTrack;
    private MediaPlayerCompat.OnInfoListener mInfoListenerCompat;
    private final AtomicBoolean mLastPlayStateForSeek = new AtomicBoolean(false);
    private long mLength;
    private long mOffset;
    private MediaPlayerCompat.OnVideoSizeChangedListener mOnVideoSizeChangedListenerCompat;
    private int mPlayerState = 0;
    private MediaPlayerCompat.OnPreparedListener mPreparedListenerCompat;
    protected final SemMediaPlayer mSemMediaPlayer;
    private Object mTag;
    private final ConcurrentHashMap<String, Object> mTagMap = new ConcurrentHashMap<>();
    private final MediaTimeTick mTimeTick = new MediaTimeTick(new b(14, this));
    private int mVideoRotation = -1;
    private int[] mVideoSize;
    private int mVisualSeekTime;

    public Sec100MediaPlayerCompat(int i2) {
        this.mConfig = i2;
        SemMediaPlayer semMediaPlayer = new SemMediaPlayer();
        this.mSemMediaPlayer = semMediaPlayer;
        semMediaPlayer.setOnErrorListener(new Xa.b(this));
        semMediaPlayer.setOnPlaybackCompleteListener(new c(this));
    }

    private int getFrameRate(SemMediaPlayer.TrackInfo[] trackInfoArr) {
        try {
            for (SemMediaPlayer.TrackInfo trackInfo : trackInfoArr) {
                if (trackInfo.getTrackType() == 1) {
                    return trackInfo.getFrameRate();
                }
            }
        } catch (Error | Exception e) {
            C0212a.y(e, new StringBuilder("getFrameRate failed e="), this.TAG);
        }
        return 0;
    }

    private int getVideoRotation(SemMediaPlayer.TrackInfo[] trackInfoArr) {
        try {
            for (SemMediaPlayer.TrackInfo trackInfo : trackInfoArr) {
                if (trackInfo.getTrackType() == 1) {
                    return trackInfo.getVideoRotation();
                }
            }
        } catch (Error | Exception e) {
            C0212a.y(e, new StringBuilder("getVideoRotation failed e="), this.TAG);
        }
        return 0;
    }

    private int[] getVideoSize(SemMediaPlayer.TrackInfo[] trackInfoArr) {
        try {
            for (SemMediaPlayer.TrackInfo trackInfo : trackInfoArr) {
                if (trackInfo.getTrackType() == 1) {
                    return new int[]{trackInfo.getVideoWidth(), trackInfo.getVideoHeight()};
                }
            }
            return null;
        } catch (Error | Exception e) {
            C0212a.y(e, new StringBuilder("getVideoSize failed e="), this.TAG);
            return null;
        }
    }

    /* access modifiers changed from: private */
    public void onCompletion(SemMediaPlayer semMediaPlayer) {
        this.mPlayerState = 7;
        if (this.mTimeTick.isEnabled()) {
            this.mTimeTick.stop();
            onInfo(semMediaPlayer, 9000001, getDurationMs());
        }
        MediaPlayerCompat.OnCompletionListener onCompletionListener = this.mCompletionListenerCompat;
        if (onCompletionListener != null) {
            onCompletionListener.onCompletion(this);
        } else {
            onInfo(semMediaPlayer, 9000005, getDurationMs());
        }
    }

    /* access modifiers changed from: private */
    public boolean onError(SemMediaPlayer semMediaPlayer, int i2, int i7) {
        this.mPlayerState = -2;
        Log.e(this.TAG, a.d(i2, i7, "onError {", GlobalPostProcInternalPPInterface.SPLIT_REGEX, "}"));
        MediaPlayerCompat.OnErrorListener onErrorListener = this.mErrorListenerCompat;
        if (onErrorListener != null) {
            onErrorListener.onError(this, i2, i7);
            return true;
        }
        onInfo(semMediaPlayer, 9000007, i7);
        return true;
    }

    /* access modifiers changed from: private */
    public boolean onInfo(SemMediaPlayer semMediaPlayer, int i2, int i7) {
        MediaPlayerCompat.OnInfoListener onInfoListener = this.mInfoListenerCompat;
        if (onInfoListener == null || !onInfoListener.onInfo(this, i2, i7)) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public void onTimeTickUpdated() {
        MediaPlayerCompat.OnInfoListener onInfoListener = this.mInfoListenerCompat;
        if (onInfoListener != null && this.mPlayerState == 4) {
            onInfoListener.onInfo(this, 9000001, getCurrentPositionMs());
        }
    }

    /* access modifiers changed from: private */
    public void onVideoSizeChangedListener(SemMediaPlayer semMediaPlayer, int i2, int i7) {
        MediaPlayerCompat.OnVideoSizeChangedListener onVideoSizeChangedListener = this.mOnVideoSizeChangedListenerCompat;
        if (onVideoSizeChangedListener != null) {
            ((e) onVideoSizeChangedListener).a(this, i2, i7);
        }
    }

    private Boolean retrieveAudioTrack() {
        try {
            for (SemMediaPlayer.TrackInfo trackType : this.mSemMediaPlayer.getTrackInfo()) {
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

    public void completeSeekTo(boolean z) {
        this.mSemMediaPlayer.seekTo(this.mVisualSeekTime, 5);
        if (!this.mLastPlayStateForSeek.getAndSet(false)) {
            return;
        }
        if (this.mSemMediaPlayer.getCurrentPosition() + 100 < this.mSemMediaPlayer.getDuration()) {
            Log.d(this.TAG, "completeSeekTo start video");
            start();
            return;
        }
        Log.d(this.TAG, "completeSeekTo stop video");
        stop();
    }

    public final int getCurrentPositionMs() {
        try {
            int i2 = this.mPlayerState;
            if (i2 == 7) {
                return getDurationMs();
            }
            if (i2 >= 3) {
                return this.mSemMediaPlayer.getCurrentPosition();
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
                int duration = this.mSemMediaPlayer.getDuration();
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

    public PlaybackParams getPlaybackParam() {
        return this.mSemMediaPlayer.getPlaybackParams();
    }

    public int getPlaybackState() {
        return this.mPlayerState;
    }

    public int[] getSuperSlowTitleRegion() {
        SemMediaPlayer.SuperSlowRegion[] superSlowRegions = this.mSemMediaPlayer.getSuperSlowRegions();
        if (superSlowRegions == null || superSlowRegions.length <= 0) {
            Log.w(this.TAG, "no super-slow region");
        } else {
            for (SemMediaPlayer.SuperSlowRegion superSlowRegion : superSlowRegions) {
                if (superSlowRegion != null && superSlowRegion.getRegionType() == 1) {
                    return new int[]{superSlowRegion.getStartTime(), superSlowRegion.getEndTime()};
                }
            }
            SemMediaPlayer.SuperSlowRegion superSlowRegion2 = superSlowRegions[0];
            if (superSlowRegion2 != null) {
                return new int[]{superSlowRegion2.getStartTime(), superSlowRegions[0].getEndTime()};
            }
        }
        return new int[]{0, 0};
    }

    public Object getTag() {
        return this.mTag;
    }

    public int getVideoHeight() {
        int[] iArr = this.mVideoSize;
        if (iArr != null) {
            return iArr[1];
        }
        return 0;
    }

    public int getVideoWidth() {
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

    public final boolean initMediaPlayer(SemMediaPlayer semMediaPlayer) {
        ParcelFileDescriptor open;
        Throwable th;
        Uri uri;
        try {
            Context context = this.mAppContext;
            if (context != null && (uri = this.mDataSourceUri) != null) {
                semMediaPlayer.init(context, uri);
                return true;
            } else if (!TextUtils.isEmpty(this.mDataSource)) {
                open = ParcelFileDescriptor.open(new File(this.mDataSource), 268435456);
                semMediaPlayer.init(open.getFileDescriptor());
                open.close();
                return true;
            } else {
                FileDataSource fileDataSource = this.mFileDataSource;
                if (fileDataSource != null) {
                    FileDescriptor fd2 = fileDataSource.getFD();
                    FileDataSource fileDataSource2 = this.mFileDataSource;
                    semMediaPlayer.init(fd2, fileDataSource2.offset, fileDataSource2.length);
                    return true;
                }
                SemMediaPlayer semMediaPlayer2 = semMediaPlayer;
                FileDescriptor fileDescriptor = this.mFileDescriptor;
                if (fileDescriptor == null) {
                    return false;
                }
                semMediaPlayer2.init(fileDescriptor, this.mOffset, this.mLength);
                return true;
            }
        } catch (IOException | IllegalArgumentException | IllegalStateException e) {
            Throwable th2 = e;
            Log.e(this.TAG, "prepareAsync failed e", th2);
            throw new IllegalStateException("prepareAsync failed " + th2.getMessage());
        } catch (Throwable th3) {
            th.addSuppressed(th3);
        }
        throw th;
    }

    public boolean isPlaying() {
        try {
            return this.mSemMediaPlayer.isPlaying();
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

    public void notifyPrepared() {
        MediaPlayerCompat.OnPreparedListener onPreparedListener = this.mPreparedListenerCompat;
        if (onPreparedListener != null) {
            onPreparedListener.onPrepared(this);
        }
    }

    public void onPrepared(SemMediaPlayer semMediaPlayer, SemMediaPlayer.TrackInfo[] trackInfoArr) {
        this.mVideoSize = getVideoSize(trackInfoArr);
        this.mFrameRate = getFrameRate(trackInfoArr);
        this.mVideoRotation = getVideoRotation(trackInfoArr);
        String str = this.TAG;
        Log.v(str, "onPrepared {" + getVideoWidth() + GlobalPostProcInternalPPInterface.SPLIT_REGEX + getVideoHeight() + "}");
        this.mPlayerState = 3;
        notifyPrepared();
    }

    public boolean pause() {
        try {
            this.mTimeTick.stop();
            this.mPlayerState = 6;
            if (this.mSemMediaPlayer.isPlaying()) {
                this.mSemMediaPlayer.pause();
            }
            int renderingPosition = getRenderingPosition();
            if (renderingPosition < 0) {
                return true;
            }
            onInfo(this.mSemMediaPlayer, 9000004, renderingPosition);
            return true;
        } catch (IllegalStateException e) {
            j.t(e, new StringBuilder("pause failed e="), this.TAG);
            onInfo(this.mSemMediaPlayer, 9000007, 0);
            return false;
        }
    }

    public void prepareAsync() {
        this.mPlayerState = 2;
        if (initMediaPlayer(this.mSemMediaPlayer)) {
            this.mPlayerState = 1;
        }
    }

    public void prepareSeekTo() {
        this.mVisualSeekTime = 0;
        if (isPlaying()) {
            this.mLastPlayStateForSeek.set(true);
            pause();
        }
    }

    public void release() {
        try {
            this.mPlayerState = -1;
            this.mTimeTick.stop();
            this.mSemMediaPlayer.release();
            this.mLastPlayStateForSeek.set(false);
        } catch (Exception e) {
            j.D(e, new StringBuilder("release failed e="), this.TAG);
        }
        onInfo(this.mSemMediaPlayer, 9000006, 0);
        this.mPreparedListenerCompat = null;
        this.mTagMap.clear();
        this.mTag = null;
        FileDataSource fileDataSource = this.mFileDataSource;
        if (fileDataSource != null) {
            fileDataSource.release();
        }
    }

    public void reset() {
        try {
            String str = this.TAG;
            Log.i(str, "Reset " + this.mPlayerState + " @" + Integer.toHexString(this.mSemMediaPlayer.hashCode()));
            this.mPlayerState = 0;
            this.mSemMediaPlayer.reset();
        } catch (IllegalStateException e) {
            j.t(e, new StringBuilder("reset failed e="), this.TAG);
        }
    }

    public final void seekTo(int i2) {
        seekTo(i2, 5);
    }

    public void setAudioMute(boolean z) {
        this.mSemMediaPlayer.setParameter(35004, z ? 1 : 0);
    }

    public void setDataSource(Context context, Uri uri) {
        this.mAppContext = context;
        this.mDataSourceUri = uri;
    }

    public void setDisplay(SurfaceHolder surfaceHolder) {
        this.mSemMediaPlayer.setDisplay(surfaceHolder);
    }

    public void setLooping(boolean z) {
        this.mSemMediaPlayer.setLooping(z);
    }

    public void setOnCompletionListener(MediaPlayerCompat.OnCompletionListener onCompletionListener) {
        this.mCompletionListenerCompat = onCompletionListener;
    }

    public void setOnErrorListener(MediaPlayerCompat.OnErrorListener onErrorListener) {
        this.mErrorListenerCompat = onErrorListener;
    }

    public void setOnInfoListener(MediaPlayerCompat.OnInfoListener onInfoListener) {
        this.mInfoListenerCompat = onInfoListener;
        this.mSemMediaPlayer.setOnInfoListener(new d(this));
    }

    public void setOnPreparedListener(MediaPlayerCompat.OnPreparedListener onPreparedListener) {
        this.mPreparedListenerCompat = onPreparedListener;
        this.mSemMediaPlayer.setOnInitCompleteListener(new Xa.a(this));
    }

    public void setOnVideoSizeChangedListener(MediaPlayerCompat.OnVideoSizeChangedListener onVideoSizeChangedListener) {
        this.mOnVideoSizeChangedListenerCompat = onVideoSizeChangedListener;
        this.mSemMediaPlayer.setOnVideoSizeChangedListener(new Xa.e(this));
    }

    public void setPlaybackParam(PlaybackParams playbackParams) {
        this.mSemMediaPlayer.setPlaybackParams(playbackParams);
    }

    public void setSuperSlowAllRegionEnabled() {
        this.mSemMediaPlayer.setParameter(36000, 1);
    }

    public void setSuperSlowPlaybackEffect(int i2, int i7) {
        this.mSemMediaPlayer.setPlaybackEffect(i2, i7);
    }

    public void setSurface(Surface surface) {
        this.mSemMediaPlayer.setSurface(surface);
    }

    public void setTag(Object obj) {
        this.mTag = obj;
    }

    public void setTimeTickEnabled(boolean z) {
        this.mTimeTick.setEnabled(z);
    }

    public void setVolume(float f, float f5) {
        this.mSemMediaPlayer.setVolume(f, f5);
    }

    public void setWfdTcpDisable() {
        this.mSemMediaPlayer.setParameter(2500, 1);
    }

    public void start() {
        try {
            String str = this.TAG;
            Log.i(str, "Start " + this.mPlayerState + " @" + Integer.toHexString(this.mSemMediaPlayer.hashCode()));
            this.mSemMediaPlayer.start();
            this.mPlayerState = 4;
            this.mTimeTick.start(getDurationMs() / 100);
            onInfo(this.mSemMediaPlayer, 9000002, getCurrentPositionMs());
        } catch (IllegalStateException e) {
            j.t(e, new StringBuilder("start failed e="), this.TAG);
            onInfo(this.mSemMediaPlayer, 9000007, 0);
        }
    }

    public void stop() {
        this.mTimeTick.stop();
        onInfo(this.mSemMediaPlayer, 9000003, getCurrentPositionMs());
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
        return C0086a.l(sb2, this.mDuration, "}");
    }

    public void visualSeekTo(int i2) {
        this.mVisualSeekTime = i2;
        seekTo(i2, 0);
    }

    private void seekTo(int i2, int i7) {
        try {
            int i8 = this.mPlayerState;
            if (i8 != 1) {
                if (i8 == 7 && i2 < getDurationMs()) {
                    this.mPlayerState = 6;
                }
                this.mSemMediaPlayer.seekTo(i2, i7);
            }
        } catch (IllegalStateException e) {
            j.t(e, C0086a.o(i2, "seekTo ", " failed e="), this.TAG);
        }
    }

    public void setDataSource(String str) {
        this.mDataSource = str;
    }

    public void setDataSource(String str, long j2, long j3) {
        this.mFileDataSource = new FileDataSource(str, j2, j3);
    }

    public int getFrameRate() {
        return this.mFrameRate;
    }

    public int getVideoRotation() {
        return this.mVideoRotation;
    }
}
