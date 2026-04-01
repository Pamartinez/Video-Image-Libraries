package com.samsung.android.gallery.module.media;

import Fb.h;
import android.content.Context;
import android.media.MediaPlayer;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.shotmode.ShotModeAudio;
import com.samsung.android.gallery.module.media.AudioManagerHelper;
import com.samsung.android.gallery.support.utils.Log;
import java.io.FileDescriptor;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class AudioPlayer implements AudioManagerHelper.OnAudioFocusManagerListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener {
    private final Object PLAYER_LOCK = new Object();
    private final Context mAppContext;
    protected AudioManagerHelper mAudioManagerHelper;
    private int mCurrentPosition;
    private final String mFileName;
    private OnAudioPlayerChangedListener mListener;
    protected MediaPlayer mMediaPlayer;
    protected int mPlayerStatus;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnAudioPlayerChangedListener {
    }

    public AudioPlayer(Context context, String str, OnAudioPlayerChangedListener onAudioPlayerChangedListener) {
        this.mAppContext = context.getApplicationContext();
        this.mFileName = str;
        this.mListener = onAudioPlayerChangedListener;
    }

    private void notifyStatus(int i2) {
        this.mPlayerStatus = i2;
        OnAudioPlayerChangedListener onAudioPlayerChangedListener = this.mListener;
        if (onAudioPlayerChangedListener != null) {
            ((ShotModeAudio) ((h) onAudioPlayerChangedListener).e).onAudioPlayerChanged(this, 1, i2);
        }
    }

    private void resetInternal() {
        synchronized (this.PLAYER_LOCK) {
            try {
                MediaPlayer mediaPlayer = this.mMediaPlayer;
                if (mediaPlayer != null) {
                    mediaPlayer.setLooping(false);
                    this.mMediaPlayer.reset();
                    this.mMediaPlayer.release();
                    this.mMediaPlayer = null;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private void setAudioEnabled(boolean z) {
        if (this.mAudioManagerHelper == null) {
            this.mAudioManagerHelper = new AudioManagerHelper(this.mAppContext, this);
        }
        if (this.mAudioManagerHelper.setAudioFocusEnabled(z)) {
            setAudioMuteInternal(!z);
        }
    }

    private void setAudioMuteInternal(boolean z) {
        float f;
        synchronized (this.PLAYER_LOCK) {
            try {
                if (this.mMediaPlayer != null) {
                    if (!z) {
                        f = this.mAudioManagerHelper.getVolume();
                    } else {
                        f = 0.0f;
                    }
                    this.mMediaPlayer.setVolume(f, f);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public int getDuration() {
        int i2;
        synchronized (this.PLAYER_LOCK) {
            try {
                MediaPlayer mediaPlayer = this.mMediaPlayer;
                if (mediaPlayer != null) {
                    i2 = mediaPlayer.getDuration();
                } else {
                    i2 = 0;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return i2;
    }

    public boolean isLooping() {
        boolean z;
        synchronized (this.PLAYER_LOCK) {
            try {
                MediaPlayer mediaPlayer = this.mMediaPlayer;
                if (mediaPlayer == null || !mediaPlayer.isLooping()) {
                    z = false;
                } else {
                    z = true;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return z;
    }

    public void onAudioDucked(boolean z) {
        if (this.mMediaPlayer != null) {
            setAudioMuteInternal(z);
        }
    }

    public void onAudioEnabled(boolean z) {
        if (this.mMediaPlayer != null && !z) {
            release();
        }
    }

    public void onCompletion(MediaPlayer mediaPlayer) {
        notifyStatus(8);
        if (this.mListener == null) {
            release();
        }
    }

    public boolean onError(MediaPlayer mediaPlayer, int i2, int i7) {
        notifyStatus(9);
        release();
        return true;
    }

    public boolean pause() {
        synchronized (this.PLAYER_LOCK) {
            MediaPlayer mediaPlayer = this.mMediaPlayer;
            if (mediaPlayer != null) {
                try {
                    if (mediaPlayer.isPlaying()) {
                        this.mMediaPlayer.pause();
                        this.mCurrentPosition = this.mMediaPlayer.getCurrentPosition();
                        notifyStatus(6);
                    }
                    return true;
                } catch (IllegalStateException e) {
                    Log.mpe("AudioPlayer", "pause failed e=" + e.getMessage());
                    return false;
                }
            }
        }
    }

    public boolean release() {
        stop();
        resetInternal();
        setAudioEnabled(false);
        notifyStatus(0);
        return true;
    }

    public boolean restart() {
        synchronized (this.PLAYER_LOCK) {
            try {
                if (this.mMediaPlayer != null) {
                    Log.mpv("AudioPlayer", "restart " + this.mPlayerStatus);
                    int i2 = this.mPlayerStatus;
                    if (i2 == 8) {
                        this.mMediaPlayer.start();
                        notifyStatus(5);
                    } else if (i2 == 6) {
                        this.mMediaPlayer.seekTo(0);
                        this.mMediaPlayer.start();
                        notifyStatus(5);
                    } else {
                        this.mMediaPlayer.seekTo(0);
                        notifyStatus(5);
                    }
                    setAudioEnabled(true);
                    return true;
                }
            } catch (Exception e) {
                Log.mpe("AudioPlayer", "restart failed e=" + e.toString());
                this.mMediaPlayer.release();
                this.mMediaPlayer = null;
                notifyStatus(0);
            } catch (Throwable th) {
                throw th;
            }
        }
        return false;
    }

    public boolean setDataSource(FileDescriptor fileDescriptor, long j2, long j3) {
        resetInternal();
        synchronized (this.PLAYER_LOCK) {
            try {
                MediaPlayer mediaPlayer = new MediaPlayer();
                this.mMediaPlayer = mediaPlayer;
                mediaPlayer.setDataSource(fileDescriptor, j2, j3);
            } catch (Exception e) {
                Log.mpe("AudioPlayer", "setDataSource failed e=" + e.getMessage());
                this.mMediaPlayer.release();
                this.mMediaPlayer = null;
                notifyStatus(0);
                return false;
            } catch (Throwable th) {
                throw th;
            }
        }
        return true;
    }

    public void setLooping(boolean z) {
        synchronized (this.PLAYER_LOCK) {
            try {
                MediaPlayer mediaPlayer = this.mMediaPlayer;
                if (mediaPlayer != null) {
                    mediaPlayer.setLooping(z);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public boolean start() {
        synchronized (this.PLAYER_LOCK) {
            try {
                MediaPlayer mediaPlayer = this.mMediaPlayer;
                if (mediaPlayer != null) {
                    mediaPlayer.setOnCompletionListener(this);
                    this.mMediaPlayer.setOnErrorListener(this);
                    this.mMediaPlayer.prepare();
                    this.mMediaPlayer.start();
                    setAudioEnabled(true);
                    notifyStatus(4);
                    return true;
                }
            } catch (Exception e) {
                Log.mpe("AudioPlayer", "playAudioInternal failed");
                e.printStackTrace();
                this.mMediaPlayer.release();
                this.mMediaPlayer = null;
                notifyStatus(0);
                return false;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public boolean stop() {
        synchronized (this.PLAYER_LOCK) {
            try {
                MediaPlayer mediaPlayer = this.mMediaPlayer;
                if (mediaPlayer != null) {
                    mediaPlayer.setLooping(false);
                    this.mMediaPlayer.stop();
                    notifyStatus(2);
                    return true;
                }
            } catch (IllegalStateException e) {
                Log.mpe("AudioPlayer", "stop failed e=" + e.getMessage());
                return false;
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
