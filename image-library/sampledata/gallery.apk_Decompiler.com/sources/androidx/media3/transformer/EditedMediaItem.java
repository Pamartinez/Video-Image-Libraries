package androidx.media3.transformer;

import F2.G;
import androidx.media3.common.Effect;
import androidx.media3.common.MediaItem;
import androidx.media3.common.audio.AudioProcessor;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import java.util.Objects;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class EditedMediaItem {
    public final long durationUs;
    public final Effects effects;
    public final boolean flattenForSlowMotion;
    public final int frameRate;
    public final MediaItem mediaItem;
    private long presentationDurationUs;
    public final boolean removeAudio;
    public final boolean removeVideo;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Builder {
        private long durationUs;
        private Effects effects;
        private boolean flattenForSlowMotion;
        private int frameRate;
        private MediaItem mediaItem;
        private boolean removeAudio;
        private boolean removeVideo;

        public EditedMediaItem build() {
            return new EditedMediaItem(this.mediaItem, this.removeAudio, this.removeVideo, this.flattenForSlowMotion, this.durationUs, this.frameRate, this.effects);
        }

        public Builder setDurationUs(long j2) {
            boolean z;
            if (j2 > 0) {
                z = true;
            } else {
                z = false;
            }
            Assertions.checkArgument(z);
            this.durationUs = j2;
            return this;
        }

        public Builder setEffects(Effects effects2) {
            this.effects = effects2;
            return this;
        }

        public Builder setMediaItem(MediaItem mediaItem2) {
            this.mediaItem = mediaItem2;
            return this;
        }

        public Builder setRemoveAudio(boolean z) {
            this.removeAudio = z;
            return this;
        }

        public Builder setRemoveVideo(boolean z) {
            this.removeVideo = z;
            return this;
        }

        public Builder(MediaItem mediaItem2) {
            long j2;
            this.mediaItem = mediaItem2;
            MediaItem.LocalConfiguration localConfiguration = mediaItem2.localConfiguration;
            if (localConfiguration == null) {
                j2 = -9223372036854775807L;
            } else {
                j2 = Util.msToUs(localConfiguration.imageDurationMs);
            }
            this.durationUs = j2;
            this.frameRate = -2147483647;
            this.effects = Effects.EMPTY;
        }

        private Builder(EditedMediaItem editedMediaItem) {
            this.mediaItem = editedMediaItem.mediaItem;
            this.removeAudio = editedMediaItem.removeAudio;
            this.removeVideo = editedMediaItem.removeVideo;
            this.flattenForSlowMotion = editedMediaItem.flattenForSlowMotion;
            this.durationUs = editedMediaItem.durationUs;
            this.frameRate = editedMediaItem.frameRate;
            this.effects = editedMediaItem.effects;
        }
    }

    public Builder buildUpon() {
        return new Builder();
    }

    public long getDurationAfterEffectsApplied(long j2) {
        long j3;
        long j8 = -9223372036854775807L;
        if (this.removeAudio) {
            j3 = -9223372036854775807L;
        } else {
            G A10 = this.effects.audioProcessors.listIterator(0);
            j3 = j2;
            while (A10.hasNext()) {
                j3 = ((AudioProcessor) A10.next()).getDurationAfterProcessorApplied(j3);
            }
        }
        if (!this.removeVideo) {
            G A11 = this.effects.videoEffects.listIterator(0);
            while (A11.hasNext()) {
                j2 = ((Effect) A11.next()).getDurationAfterEffectApplied(j2);
            }
            j8 = j2;
        }
        return Math.max(j3, j8);
    }

    public boolean isGap() {
        return isGap(this.mediaItem);
    }

    private EditedMediaItem(MediaItem mediaItem2, boolean z, boolean z3, boolean z7, long j2, int i2, Effects effects2) {
        boolean z9 = true;
        Assertions.checkState(!z || !z3, "Audio and video cannot both be removed");
        if (isGap(mediaItem2)) {
            Assertions.checkArgument(j2 != -9223372036854775807L);
            Assertions.checkArgument((z || z7 || !effects2.audioProcessors.isEmpty()) ? false : z9);
        }
        this.mediaItem = mediaItem2;
        this.removeAudio = z;
        this.removeVideo = z3;
        this.flattenForSlowMotion = z7;
        this.durationUs = j2;
        this.frameRate = i2;
        this.effects = effects2;
        this.presentationDurationUs = -9223372036854775807L;
    }

    private static boolean isGap(MediaItem mediaItem2) {
        return Objects.equals(mediaItem2.mediaId, "androidx-media3-GapMediaItem");
    }
}
