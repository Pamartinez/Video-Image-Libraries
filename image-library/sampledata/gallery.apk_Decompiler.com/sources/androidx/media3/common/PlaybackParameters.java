package androidx.media3.common;

import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class PlaybackParameters {
    public static final PlaybackParameters DEFAULT = new PlaybackParameters(1.0f);
    private static final String FIELD_PITCH = Util.intToStringMaxRadix(1);
    private static final String FIELD_SPEED = Util.intToStringMaxRadix(0);
    public final float pitch;
    private final int scaledUsPerMs;
    public final float speed;

    public PlaybackParameters(float f) {
        this(f, 1.0f);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && PlaybackParameters.class == obj.getClass()) {
            PlaybackParameters playbackParameters = (PlaybackParameters) obj;
            if (this.speed == playbackParameters.speed && this.pitch == playbackParameters.pitch) {
                return true;
            }
            return false;
        }
        return false;
    }

    public long getMediaTimeUsForPlayoutTimeMs(long j2) {
        return j2 * ((long) this.scaledUsPerMs);
    }

    public int hashCode() {
        return Float.floatToRawIntBits(this.pitch) + ((Float.floatToRawIntBits(this.speed) + 527) * 31);
    }

    public String toString() {
        return Util.formatInvariant("PlaybackParameters(speed=%.2f, pitch=%.2f)", Float.valueOf(this.speed), Float.valueOf(this.pitch));
    }

    public PlaybackParameters withSpeed(float f) {
        return new PlaybackParameters(f, this.pitch);
    }

    public PlaybackParameters(float f, float f5) {
        boolean z = false;
        Assertions.checkArgument(f > 0.0f);
        Assertions.checkArgument(f5 > 0.0f ? true : z);
        this.speed = f;
        this.pitch = f5;
        this.scaledUsPerMs = Math.round(f * 1000.0f);
    }
}
