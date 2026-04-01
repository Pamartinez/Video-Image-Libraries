package androidx.media3.extractor.metadata.mp4;

import D6.a;
import androidx.media3.common.Metadata;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class SlowMotionData implements Metadata.Entry {
    public final List<Segment> segments;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Segment {
        public static final Comparator<Segment> BY_START_THEN_END_THEN_DIVISOR = new a(9);
        public final long endTimeMs;
        public final int speedDivisor;
        public final long startTimeMs;

        public Segment(long j2, long j3, int i2) {
            boolean z;
            if (j2 < j3) {
                z = true;
            } else {
                z = false;
            }
            Assertions.checkArgument(z);
            this.startTimeMs = j2;
            this.endTimeMs = j3;
            this.speedDivisor = i2;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj != null && Segment.class == obj.getClass()) {
                Segment segment = (Segment) obj;
                if (this.startTimeMs == segment.startTimeMs && this.endTimeMs == segment.endTimeMs && this.speedDivisor == segment.speedDivisor) {
                    return true;
                }
                return false;
            }
            return false;
        }

        public int hashCode() {
            return Objects.hash(new Object[]{Long.valueOf(this.startTimeMs), Long.valueOf(this.endTimeMs), Integer.valueOf(this.speedDivisor)});
        }

        public String toString() {
            return Util.formatInvariant("Segment: startTimeMs=%d, endTimeMs=%d, speedDivisor=%d", Long.valueOf(this.startTimeMs), Long.valueOf(this.endTimeMs), Integer.valueOf(this.speedDivisor));
        }
    }

    public SlowMotionData(List<Segment> list) {
        this.segments = list;
        Assertions.checkArgument(!doSegmentsOverlap(list));
    }

    private static boolean doSegmentsOverlap(List<Segment> list) {
        if (list.isEmpty()) {
            return false;
        }
        long j2 = list.get(0).endTimeMs;
        for (int i2 = 1; i2 < list.size(); i2++) {
            if (list.get(i2).startTimeMs < j2) {
                return true;
            }
            j2 = list.get(i2).endTimeMs;
        }
        return false;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || SlowMotionData.class != obj.getClass()) {
            return false;
        }
        return this.segments.equals(((SlowMotionData) obj).segments);
    }

    public int hashCode() {
        return this.segments.hashCode();
    }

    public String toString() {
        return "SlowMotion: segments=" + this.segments;
    }
}
