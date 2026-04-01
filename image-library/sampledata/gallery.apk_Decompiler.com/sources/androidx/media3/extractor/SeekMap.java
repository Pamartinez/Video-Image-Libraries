package androidx.media3.extractor;

import androidx.media3.common.util.Assertions;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import i.C0212a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface SeekMap {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class SeekPoints {
        public final SeekPoint first;
        public final SeekPoint second;

        public SeekPoints(SeekPoint seekPoint) {
            this(seekPoint, seekPoint);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj != null && SeekPoints.class == obj.getClass()) {
                SeekPoints seekPoints = (SeekPoints) obj;
                if (!this.first.equals(seekPoints.first) || !this.second.equals(seekPoints.second)) {
                    return false;
                }
                return true;
            }
            return false;
        }

        public int hashCode() {
            return this.second.hashCode() + (this.first.hashCode() * 31);
        }

        public String toString() {
            String str;
            StringBuilder sb2 = new StringBuilder("[");
            sb2.append(this.first);
            if (this.first.equals(this.second)) {
                str = "";
            } else {
                str = ArcCommonLog.TAG_COMMA + this.second;
            }
            return C0212a.p(sb2, str, "]");
        }

        public SeekPoints(SeekPoint seekPoint, SeekPoint seekPoint2) {
            this.first = (SeekPoint) Assertions.checkNotNull(seekPoint);
            this.second = (SeekPoint) Assertions.checkNotNull(seekPoint2);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Unseekable implements SeekMap {
        private final long durationUs;
        private final SeekPoints startSeekPoints;

        public Unseekable(long j2) {
            this(j2, 0);
        }

        public long getDurationUs() {
            return this.durationUs;
        }

        public SeekPoints getSeekPoints(long j2) {
            return this.startSeekPoints;
        }

        public boolean isSeekable() {
            return false;
        }

        public Unseekable(long j2, long j3) {
            this.durationUs = j2;
            this.startSeekPoints = new SeekPoints(j3 == 0 ? SeekPoint.START : new SeekPoint(0, j3));
        }
    }

    long getDurationUs();

    SeekPoints getSeekPoints(long j2);

    boolean isSeekable();
}
