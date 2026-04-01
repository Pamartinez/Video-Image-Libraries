package com.samsung.android.gallery.module.media;

import A.a;
import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioFocusRequest;
import android.media.AudioManager;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.ThreadUtil;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AudioManagerHelper implements AudioManager.OnAudioFocusChangeListener {
    private final Context mAppContext;
    private boolean mAudioFocusDucked;
    protected boolean mAudioFocusEnabled;
    protected AudioFocusRequest mAudioFocusRequest;
    private final OnAudioFocusManagerListener mListener;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnAudioFocusManagerListener {
        void onAudioDucked(boolean z);

        void onAudioEnabled(boolean z);
    }

    public AudioManagerHelper(Context context, OnAudioFocusManagerListener onAudioFocusManagerListener) {
        this.mAppContext = context.getApplicationContext();
        this.mListener = onAudioFocusManagerListener;
    }

    private int abandonAudioFocus() {
        AudioManager audioManager = (AudioManager) this.mAppContext.getSystemService("audio");
        if (audioManager != null) {
            return audioManager.abandonAudioFocusRequest(getAudioFocusRequest());
        }
        return 0;
    }

    private AudioFocusRequest getAudioFocusRequest() {
        if (this.mAudioFocusRequest == null) {
            this.mAudioFocusRequest = new AudioFocusRequest.Builder(1).setAudioAttributes(new AudioAttributes.Builder().setUsage(1).setContentType(3).build()).setAcceptsDelayedFocusGain(false).setOnAudioFocusChangeListener(this, ThreadUtil.createMainThreadHandler()).setWillPauseWhenDucked(true).build();
        }
        return this.mAudioFocusRequest;
    }

    private int requestAudioFocus() {
        AudioManager audioManager = (AudioManager) this.mAppContext.getSystemService("audio");
        if (audioManager != null) {
            return audioManager.requestAudioFocus(getAudioFocusRequest());
        }
        return 0;
    }

    public void destroy() {
        if (this.mAudioFocusRequest != null) {
            this.mAudioFocusRequest = null;
        }
    }

    public String getAudioFocusedPackageName() {
        return SeApiCompat.getAudioFocusedPackageName(this.mAppContext);
    }

    public float getVolume() {
        AudioManager audioManager = (AudioManager) this.mAppContext.getSystemService("audio");
        if (audioManager == null) {
            return 0.0f;
        }
        return ((float) audioManager.getStreamVolume(3)) / ((float) audioManager.getStreamMaxVolume(3));
    }

    public void onAudioFocusChange(int i2) {
        a.B(i2, "onAudioFocusChange ", "AudioManagerHelper");
        if (i2 != -3) {
            if (i2 == -2 || i2 == -1) {
                if (this.mAudioFocusEnabled) {
                    this.mListener.onAudioEnabled(false);
                }
            } else if (i2 == 1) {
                if (this.mAudioFocusDucked) {
                    this.mAudioFocusDucked = false;
                    this.mListener.onAudioDucked(false);
                } else if (this.mAudioFocusEnabled) {
                    this.mListener.onAudioEnabled(true);
                }
            }
        } else if (this.mAudioFocusEnabled) {
            this.mAudioFocusDucked = true;
            this.mListener.onAudioDucked(true);
        }
    }

    public boolean setAudioFocusEnabled(boolean z) {
        int i2;
        if (z) {
            i2 = requestAudioFocus();
        } else {
            i2 = abandonAudioFocus();
        }
        boolean z3 = true;
        if (i2 != 1) {
            z3 = false;
        }
        this.mAudioFocusEnabled = z3;
        return z3;
    }
}
