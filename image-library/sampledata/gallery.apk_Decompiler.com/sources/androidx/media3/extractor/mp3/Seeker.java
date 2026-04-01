package androidx.media3.extractor.mp3;

import androidx.media3.extractor.SeekMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
interface Seeker extends SeekMap {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class UnseekableSeeker extends SeekMap.Unseekable implements Seeker {
        public UnseekableSeeker() {
            super(-9223372036854775807L);
        }

        public int getAverageBitrate() {
            return -2147483647;
        }

        public long getDataEndPosition() {
            return -1;
        }

        public long getDataStartPosition() {
            return 0;
        }

        public long getTimeUs(long j2) {
            return 0;
        }
    }

    int getAverageBitrate();

    long getDataEndPosition();

    long getDataStartPosition();

    long getTimeUs(long j2);
}
