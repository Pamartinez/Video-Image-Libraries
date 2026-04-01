package androidx.media3.transformer;

import F2.C0040v;
import F2.N;
import F2.U;
import androidx.media3.common.VideoCompositorSettings;
import androidx.media3.common.util.Assertions;
import java.util.Arrays;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class Composition {
    public final Effects effects;
    @Deprecated
    public final boolean forceAudioTrack;
    public final int hdrMode;
    public final boolean retainHdrFromUltraHdrImage;
    public final U sequences;
    public final boolean transmuxAudio;
    public final boolean transmuxVideo;
    public final VideoCompositorSettings videoCompositorSettings;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Builder {
        private Effects effects;
        private boolean forceAudioTrack;
        private int hdrMode;
        private boolean retainHdrFromUltraHdrImage;
        private U sequences;
        private boolean transmuxAudio;
        private boolean transmuxVideo;
        private VideoCompositorSettings videoCompositorSettings;

        public Composition build() {
            List list;
            boolean z = false;
            if (this.forceAudioTrack) {
                C0040v.c(4, "initialCapacity");
                Object[] objArr = new Object[4];
                int i2 = 0;
                int i7 = 0;
                while (i2 < this.sequences.size()) {
                    EditedMediaItemSequence build = ((EditedMediaItemSequence) this.sequences.get(i2)).buildUpon().experimentalSetForceAudioTrack(this.forceAudioTrack).build();
                    build.getClass();
                    int i8 = i7 + 1;
                    int e = N.e(objArr.length, i8);
                    if (e > objArr.length) {
                        objArr = Arrays.copyOf(objArr, e);
                    }
                    objArr[i7] = build;
                    i2++;
                    i7 = i8;
                }
                list = U.w(i7, objArr);
            } else {
                list = this.sequences;
            }
            List list2 = list;
            VideoCompositorSettings videoCompositorSettings2 = this.videoCompositorSettings;
            Effects effects2 = this.effects;
            boolean z3 = this.forceAudioTrack;
            boolean z7 = this.transmuxAudio;
            boolean z9 = this.transmuxVideo;
            int i10 = this.hdrMode;
            if (this.retainHdrFromUltraHdrImage && i10 == 0) {
                z = true;
            }
            return new Composition(list2, videoCompositorSettings2, effects2, z3, z7, z9, i10, z);
        }

        public Builder setSequences(List<EditedMediaItemSequence> list) {
            Assertions.checkArgument(!list.isEmpty(), "The composition must contain at least one EditedMediaItemSequence.");
            this.sequences = U.y(list);
            return this;
        }

        public Builder setTransmuxVideo(boolean z) {
            this.transmuxVideo = z;
            return this;
        }

        public Builder(List<EditedMediaItemSequence> list) {
            Assertions.checkArgument(!list.isEmpty(), "The composition must contain at least one EditedMediaItemSequence.");
            this.sequences = U.y(list);
            this.videoCompositorSettings = VideoCompositorSettings.DEFAULT;
            this.effects = Effects.EMPTY;
        }

        private Builder(Composition composition) {
            this.sequences = composition.sequences;
            this.videoCompositorSettings = composition.videoCompositorSettings;
            this.effects = composition.effects;
            this.forceAudioTrack = composition.forceAudioTrack;
            this.transmuxAudio = composition.transmuxAudio;
            this.transmuxVideo = composition.transmuxVideo;
            this.hdrMode = composition.hdrMode;
            this.retainHdrFromUltraHdrImage = composition.retainHdrFromUltraHdrImage;
        }
    }

    private static boolean hasNonLoopingSequence(List<EditedMediaItemSequence> list) {
        for (EditedMediaItemSequence editedMediaItemSequence : list) {
            if (!editedMediaItemSequence.isLooping) {
                return true;
            }
        }
        return false;
    }

    public Builder buildUpon() {
        return new Builder();
    }

    public boolean hasGaps() {
        for (int i2 = 0; i2 < this.sequences.size(); i2++) {
            if (((EditedMediaItemSequence) this.sequences.get(i2)).hasGaps()) {
                return true;
            }
        }
        return false;
    }

    private Composition(List<EditedMediaItemSequence> list, VideoCompositorSettings videoCompositorSettings2, Effects effects2, boolean z, boolean z3, boolean z7, int i2, boolean z9) {
        Assertions.checkArgument(!z3 || !z, "Audio transmuxing and audio track forcing are not allowed together.");
        Assertions.checkArgument(hasNonLoopingSequence(list), "Composition must have at least one non-looping sequence.");
        this.sequences = U.y(list);
        this.videoCompositorSettings = videoCompositorSettings2;
        this.effects = effects2;
        this.transmuxAudio = z3;
        this.transmuxVideo = z7;
        this.forceAudioTrack = z;
        this.hdrMode = i2;
        this.retainHdrFromUltraHdrImage = z9;
    }
}
