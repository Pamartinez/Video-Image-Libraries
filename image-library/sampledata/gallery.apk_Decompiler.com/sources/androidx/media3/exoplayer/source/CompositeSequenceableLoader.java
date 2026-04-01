package androidx.media3.exoplayer.source;

import F2.Q;
import F2.U;
import androidx.media3.common.util.Assertions;
import androidx.media3.exoplayer.LoadingInfo;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class CompositeSequenceableLoader implements SequenceableLoader {
    private long lastAudioVideoBufferedPositionUs;
    private final U loadersWithTrackTypes;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class SequenceableLoaderWithTrackTypes implements SequenceableLoader {
        private final SequenceableLoader loader;
        private final U trackTypes;

        public SequenceableLoaderWithTrackTypes(SequenceableLoader sequenceableLoader, List<Integer> list) {
            this.loader = sequenceableLoader;
            this.trackTypes = U.y(list);
        }

        public boolean continueLoading(LoadingInfo loadingInfo) {
            return this.loader.continueLoading(loadingInfo);
        }

        public long getBufferedPositionUs() {
            return this.loader.getBufferedPositionUs();
        }

        public long getNextLoadPositionUs() {
            return this.loader.getNextLoadPositionUs();
        }

        public U getTrackTypes() {
            return this.trackTypes;
        }

        public boolean isLoading() {
            return this.loader.isLoading();
        }

        public void reevaluateBuffer(long j2) {
            this.loader.reevaluateBuffer(j2);
        }
    }

    public CompositeSequenceableLoader(List<? extends SequenceableLoader> list, List<List<Integer>> list2) {
        boolean z;
        Q x9 = U.x();
        if (list.size() == list2.size()) {
            z = true;
        } else {
            z = false;
        }
        Assertions.checkArgument(z);
        for (int i2 = 0; i2 < list.size(); i2++) {
            x9.a(new SequenceableLoaderWithTrackTypes((SequenceableLoader) list.get(i2), list2.get(i2)));
        }
        this.loadersWithTrackTypes = x9.f();
        this.lastAudioVideoBufferedPositionUs = -9223372036854775807L;
    }

    public boolean continueLoading(LoadingInfo loadingInfo) {
        boolean z;
        boolean z3;
        boolean z7 = false;
        do {
            long nextLoadPositionUs = getNextLoadPositionUs();
            if (nextLoadPositionUs == Long.MIN_VALUE) {
                return z7;
            }
            z = false;
            for (int i2 = 0; i2 < this.loadersWithTrackTypes.size(); i2++) {
                long nextLoadPositionUs2 = ((SequenceableLoaderWithTrackTypes) this.loadersWithTrackTypes.get(i2)).getNextLoadPositionUs();
                if (nextLoadPositionUs2 == Long.MIN_VALUE || nextLoadPositionUs2 > loadingInfo.playbackPositionUs) {
                    z3 = false;
                } else {
                    z3 = true;
                }
                if (nextLoadPositionUs2 == nextLoadPositionUs || z3) {
                    z |= ((SequenceableLoaderWithTrackTypes) this.loadersWithTrackTypes.get(i2)).continueLoading(loadingInfo);
                }
            }
            z7 |= z;
        } while (z);
        return z7;
    }

    public long getBufferedPositionUs() {
        long j2 = Long.MAX_VALUE;
        long j3 = Long.MAX_VALUE;
        for (int i2 = 0; i2 < this.loadersWithTrackTypes.size(); i2++) {
            SequenceableLoaderWithTrackTypes sequenceableLoaderWithTrackTypes = (SequenceableLoaderWithTrackTypes) this.loadersWithTrackTypes.get(i2);
            long bufferedPositionUs = sequenceableLoaderWithTrackTypes.getBufferedPositionUs();
            if ((sequenceableLoaderWithTrackTypes.getTrackTypes().contains(1) || sequenceableLoaderWithTrackTypes.getTrackTypes().contains(2) || sequenceableLoaderWithTrackTypes.getTrackTypes().contains(4)) && bufferedPositionUs != Long.MIN_VALUE) {
                j2 = Math.min(j2, bufferedPositionUs);
            }
            if (bufferedPositionUs != Long.MIN_VALUE) {
                j3 = Math.min(j3, bufferedPositionUs);
            }
        }
        if (j2 != Long.MAX_VALUE) {
            this.lastAudioVideoBufferedPositionUs = j2;
            return j2;
        } else if (j3 == Long.MAX_VALUE) {
            return Long.MIN_VALUE;
        } else {
            long j8 = this.lastAudioVideoBufferedPositionUs;
            if (j8 != -9223372036854775807L) {
                return j8;
            }
            return j3;
        }
    }

    public long getNextLoadPositionUs() {
        long j2 = Long.MAX_VALUE;
        for (int i2 = 0; i2 < this.loadersWithTrackTypes.size(); i2++) {
            long nextLoadPositionUs = ((SequenceableLoaderWithTrackTypes) this.loadersWithTrackTypes.get(i2)).getNextLoadPositionUs();
            if (nextLoadPositionUs != Long.MIN_VALUE) {
                j2 = Math.min(j2, nextLoadPositionUs);
            }
        }
        if (j2 == Long.MAX_VALUE) {
            return Long.MIN_VALUE;
        }
        return j2;
    }

    public boolean isLoading() {
        for (int i2 = 0; i2 < this.loadersWithTrackTypes.size(); i2++) {
            if (((SequenceableLoaderWithTrackTypes) this.loadersWithTrackTypes.get(i2)).isLoading()) {
                return true;
            }
        }
        return false;
    }

    public void reevaluateBuffer(long j2) {
        for (int i2 = 0; i2 < this.loadersWithTrackTypes.size(); i2++) {
            ((SequenceableLoaderWithTrackTypes) this.loadersWithTrackTypes.get(i2)).reevaluateBuffer(j2);
        }
    }
}
