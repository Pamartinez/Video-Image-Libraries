package androidx.media3.extractor.text;

import F2.U;
import androidx.media3.common.text.Cue;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CuesWithTiming {
    public final U cues;
    public final long durationUs;
    public final long endTimeUs;
    public final long startTimeUs;

    public CuesWithTiming(List<Cue> list, long j2, long j3) {
        this.cues = U.y(list);
        this.startTimeUs = j2;
        this.durationUs = j3;
        long j8 = -9223372036854775807L;
        if (!(j2 == -9223372036854775807L || j3 == -9223372036854775807L)) {
            j8 = j2 + j3;
        }
        this.endTimeUs = j8;
    }
}
