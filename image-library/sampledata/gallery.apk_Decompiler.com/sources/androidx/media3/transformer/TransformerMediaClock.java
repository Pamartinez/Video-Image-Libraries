package androidx.media3.transformer;

import android.util.SparseLongArray;
import androidx.media3.common.PlaybackParameters;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.MediaClock;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class TransformerMediaClock implements MediaClock {
    private long minTrackTimeUs;
    private final SparseLongArray trackTypeToTimeUs = new SparseLongArray();

    public PlaybackParameters getPlaybackParameters() {
        return PlaybackParameters.DEFAULT;
    }

    public long getPositionUs() {
        return this.minTrackTimeUs;
    }

    public void updateTimeForTrackType(int i2, long j2) {
        long j3 = this.trackTypeToTimeUs.get(i2, -9223372036854775807L);
        int i7 = (j3 > -9223372036854775807L ? 1 : (j3 == -9223372036854775807L ? 0 : -1));
        if (i7 == 0 || j2 > j3) {
            this.trackTypeToTimeUs.put(i2, j2);
            if (i7 == 0 || j3 == this.minTrackTimeUs) {
                this.minTrackTimeUs = Util.minValue(this.trackTypeToTimeUs);
            }
        }
    }

    public void setPlaybackParameters(PlaybackParameters playbackParameters) {
    }
}
