package androidx.media3.exoplayer;

import A.a;
import E2.r;
import E2.s;
import E2.u;
import android.content.Context;
import android.media.AudioManager;
import android.os.Handler;
import android.os.Looper;
import androidx.media3.common.AudioAttributes;
import androidx.media3.common.audio.AudioFocusRequestCompat;
import androidx.media3.common.audio.AudioManagerCompat;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import java.io.Serializable;
import java.util.Objects;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class AudioFocusManager {
    private AudioAttributes audioAttributes;
    private AudioFocusRequestCompat audioFocusRequest;
    private int audioFocusState;
    private final r audioManager;
    private final Handler eventHandler;
    private int focusGainToRequest;
    private PlayerControl playerControl;
    private boolean rebuildAudioFocusRequest;
    private float volumeMultiplier = 1.0f;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface PlayerControl {
        void executePlayerCommand(int i2);

        void setVolumeMultiplier(float f);
    }

    public AudioFocusManager(Context context, Looper looper, PlayerControl playerControl2) {
        r rVar;
        d dVar = new d(context);
        if (dVar instanceof Serializable) {
            rVar = new s(dVar);
        } else {
            rVar = new u(dVar);
        }
        this.audioManager = rVar;
        this.playerControl = playerControl2;
        this.eventHandler = new Handler(looper);
        this.audioFocusState = 0;
    }

    private void abandonAudioFocusIfHeld() {
        int i2 = this.audioFocusState;
        if (i2 != 1 && i2 != 0 && this.audioFocusRequest != null) {
            AudioManagerCompat.abandonAudioFocusRequest((AudioManager) this.audioManager.get(), this.audioFocusRequest);
        }
    }

    private static int convertAudioAttributesToFocusGain(AudioAttributes audioAttributes2) {
        if (audioAttributes2 == null) {
            return 0;
        }
        switch (audioAttributes2.usage) {
            case 0:
                Log.w("AudioFocusManager", "Specify a proper usage in the audio attributes for audio focus handling. Using AUDIOFOCUS_GAIN by default.");
                return 1;
            case 1:
            case 14:
                return 1;
            case 2:
            case 4:
                return 2;
            case 3:
                return 0;
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 12:
            case 13:
                break;
            case 11:
                if (audioAttributes2.contentType == 1) {
                    return 2;
                }
                break;
            case 16:
                return 4;
            default:
                Log.w("AudioFocusManager", "Unidentified audio usage: " + audioAttributes2.usage);
                return 0;
        }
        return 3;
    }

    private void executePlayerCommand(int i2) {
        PlayerControl playerControl2 = this.playerControl;
        if (playerControl2 != null) {
            playerControl2.executePlayerCommand(i2);
        }
    }

    /* access modifiers changed from: private */
    public void handlePlatformAudioFocusChange(int i2) {
        if (i2 == -3 || i2 == -2) {
            if (i2 == -2 || willPauseWhenDucked()) {
                executePlayerCommand(0);
                setAudioFocusState(3);
                return;
            }
            setAudioFocusState(4);
        } else if (i2 == -1) {
            executePlayerCommand(-1);
            abandonAudioFocusIfHeld();
            setAudioFocusState(1);
        } else if (i2 != 1) {
            a.D(i2, "Unknown focus change type: ", "AudioFocusManager");
        } else {
            setAudioFocusState(2);
            executePlayerCommand(1);
        }
    }

    private int requestAudioFocus() {
        if (this.audioFocusState == 2) {
            return 1;
        }
        if (requestAudioFocusInternal() == 1) {
            setAudioFocusState(2);
            return 1;
        }
        setAudioFocusState(1);
        return -1;
    }

    private int requestAudioFocusInternal() {
        AudioFocusRequestCompat.Builder builder;
        AudioFocusRequestCompat audioFocusRequestCompat = this.audioFocusRequest;
        if (audioFocusRequestCompat == null || this.rebuildAudioFocusRequest) {
            if (audioFocusRequestCompat == null) {
                builder = new AudioFocusRequestCompat.Builder(this.focusGainToRequest);
            } else {
                builder = audioFocusRequestCompat.buildUpon();
            }
            this.audioFocusRequest = builder.setAudioAttributes((AudioAttributes) Assertions.checkNotNull(this.audioAttributes)).setWillPauseWhenDucked(willPauseWhenDucked()).setOnAudioFocusChangeListener(new c(this), this.eventHandler).build();
            this.rebuildAudioFocusRequest = false;
        }
        return AudioManagerCompat.requestAudioFocus((AudioManager) this.audioManager.get(), this.audioFocusRequest);
    }

    private void setAudioFocusState(int i2) {
        float f;
        if (this.audioFocusState != i2) {
            this.audioFocusState = i2;
            if (i2 == 4) {
                f = 0.2f;
            } else {
                f = 1.0f;
            }
            if (this.volumeMultiplier != f) {
                this.volumeMultiplier = f;
                PlayerControl playerControl2 = this.playerControl;
                if (playerControl2 != null) {
                    playerControl2.setVolumeMultiplier(f);
                }
            }
        }
    }

    private boolean shouldHandleAudioFocus(int i2) {
        if (i2 == 1 || this.focusGainToRequest != 1) {
            return false;
        }
        return true;
    }

    private boolean willPauseWhenDucked() {
        AudioAttributes audioAttributes2 = this.audioAttributes;
        if (audioAttributes2 == null || audioAttributes2.contentType != 1) {
            return false;
        }
        return true;
    }

    public float getVolumeMultiplier() {
        return this.volumeMultiplier;
    }

    public void release() {
        this.playerControl = null;
        abandonAudioFocusIfHeld();
        setAudioFocusState(0);
    }

    public void setAudioAttributes(AudioAttributes audioAttributes2) {
        if (!Objects.equals(this.audioAttributes, audioAttributes2)) {
            this.audioAttributes = audioAttributes2;
            int convertAudioAttributesToFocusGain = convertAudioAttributesToFocusGain(audioAttributes2);
            this.focusGainToRequest = convertAudioAttributesToFocusGain;
            boolean z = true;
            if (!(convertAudioAttributesToFocusGain == 1 || convertAudioAttributesToFocusGain == 0)) {
                z = false;
            }
            Assertions.checkArgument(z, "Automatic handling of audio focus is only available for USAGE_MEDIA and USAGE_GAME.");
        }
    }

    public int updateAudioFocus(boolean z, int i2) {
        if (!shouldHandleAudioFocus(i2)) {
            abandonAudioFocusIfHeld();
            setAudioFocusState(0);
            return 1;
        } else if (z) {
            return requestAudioFocus();
        } else {
            int i7 = this.audioFocusState;
            if (i7 == 1) {
                return -1;
            }
            if (i7 != 3) {
                return 1;
            }
            return 0;
        }
    }
}
