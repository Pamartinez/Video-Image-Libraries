package androidx.media3.common.audio;

import android.media.AudioFocusRequest;
import android.media.AudioManager;
import android.os.Handler;
import androidx.media3.common.AudioAttributes;
import androidx.media3.common.util.Assertions;
import java.util.Objects;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class AudioFocusRequestCompat {
    private final AudioAttributes audioAttributes;
    private final Handler focusChangeHandler;
    private final int focusGain;
    private final Object frameworkAudioFocusRequest;
    private final AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener;
    private final boolean pauseOnDuck;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Builder {
        private AudioAttributes audioAttributes;
        private Handler focusChangeHandler;
        private int focusGain;
        private AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener;
        private boolean pauseOnDuck;

        public AudioFocusRequestCompat build() {
            AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener2 = this.onAudioFocusChangeListener;
            if (onAudioFocusChangeListener2 != null) {
                return new AudioFocusRequestCompat(this.focusGain, onAudioFocusChangeListener2, (Handler) Assertions.checkNotNull(this.focusChangeHandler), this.audioAttributes, this.pauseOnDuck);
            }
            throw new IllegalStateException("Can't build an AudioFocusRequestCompat instance without a listener");
        }

        public Builder setAudioAttributes(AudioAttributes audioAttributes2) {
            Assertions.checkNotNull(audioAttributes2);
            this.audioAttributes = audioAttributes2;
            return this;
        }

        public Builder setOnAudioFocusChangeListener(AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener2, Handler handler) {
            Assertions.checkNotNull(onAudioFocusChangeListener2);
            Assertions.checkNotNull(handler);
            this.onAudioFocusChangeListener = onAudioFocusChangeListener2;
            this.focusChangeHandler = handler;
            return this;
        }

        public Builder setWillPauseWhenDucked(boolean z) {
            this.pauseOnDuck = z;
            return this;
        }

        public Builder(int i2) {
            this.audioAttributes = AudioAttributes.DEFAULT;
            this.focusGain = i2;
        }

        private Builder(AudioFocusRequestCompat audioFocusRequestCompat) {
            this.focusGain = audioFocusRequestCompat.getFocusGain();
            this.onAudioFocusChangeListener = audioFocusRequestCompat.getOnAudioFocusChangeListener();
            this.focusChangeHandler = audioFocusRequestCompat.getFocusChangeHandler();
            this.audioAttributes = audioFocusRequestCompat.getAudioAttributes();
            this.pauseOnDuck = audioFocusRequestCompat.willPauseWhenDucked();
        }
    }

    public AudioFocusRequestCompat(int i2, AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener2, Handler handler, AudioAttributes audioAttributes2, boolean z) {
        this.focusGain = i2;
        this.focusChangeHandler = handler;
        this.audioAttributes = audioAttributes2;
        this.pauseOnDuck = z;
        this.onAudioFocusChangeListener = onAudioFocusChangeListener2;
        this.frameworkAudioFocusRequest = new AudioFocusRequest.Builder(i2).setAudioAttributes(audioAttributes2.getAudioAttributesV21().audioAttributes).setWillPauseWhenDucked(z).setOnAudioFocusChangeListener(onAudioFocusChangeListener2, handler).build();
    }

    public Builder buildUpon() {
        return new Builder();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AudioFocusRequestCompat)) {
            return false;
        }
        AudioFocusRequestCompat audioFocusRequestCompat = (AudioFocusRequestCompat) obj;
        if (this.focusGain != audioFocusRequestCompat.focusGain || this.pauseOnDuck != audioFocusRequestCompat.pauseOnDuck || !Objects.equals(this.onAudioFocusChangeListener, audioFocusRequestCompat.onAudioFocusChangeListener) || !Objects.equals(this.focusChangeHandler, audioFocusRequestCompat.focusChangeHandler) || !Objects.equals(this.audioAttributes, audioFocusRequestCompat.audioAttributes)) {
            return false;
        }
        return true;
    }

    public AudioAttributes getAudioAttributes() {
        return this.audioAttributes;
    }

    public AudioFocusRequest getAudioFocusRequest() {
        return (AudioFocusRequest) Assertions.checkNotNull(this.frameworkAudioFocusRequest);
    }

    public Handler getFocusChangeHandler() {
        return this.focusChangeHandler;
    }

    public int getFocusGain() {
        return this.focusGain;
    }

    public AudioManager.OnAudioFocusChangeListener getOnAudioFocusChangeListener() {
        return this.onAudioFocusChangeListener;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{Integer.valueOf(this.focusGain), this.onAudioFocusChangeListener, this.focusChangeHandler, this.audioAttributes, Boolean.valueOf(this.pauseOnDuck)});
    }

    public boolean willPauseWhenDucked() {
        return this.pauseOnDuck;
    }
}
