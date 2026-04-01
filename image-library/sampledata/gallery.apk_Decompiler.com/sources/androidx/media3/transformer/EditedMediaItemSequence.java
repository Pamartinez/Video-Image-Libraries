package androidx.media3.transformer;

import F2.C0040v;
import F2.N;
import F2.Q;
import F2.U;
import F2.y0;
import androidx.media3.common.MediaItem;
import androidx.media3.common.util.Assertions;
import androidx.media3.transformer.EditedMediaItem;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class EditedMediaItemSequence {
    public final U editedMediaItems;
    public final boolean forceAudioTrack;
    public final boolean forceVideoTrack;
    public final boolean isLooping;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Builder {
        /* access modifiers changed from: private */
        public boolean forceAudioTrack;
        /* access modifiers changed from: private */
        public boolean forceVideoTrack;
        /* access modifiers changed from: private */
        public boolean isLooping;
        /* access modifiers changed from: private */
        public final Q items;

        public Builder addGap(long j2) {
            this.items.a(new EditedMediaItem.Builder(new MediaItem.Builder().setMediaId("androidx-media3-GapMediaItem").build()).setDurationUs(j2).build());
            return this;
        }

        public EditedMediaItemSequence build() {
            return new EditedMediaItemSequence(this);
        }

        public Builder experimentalSetForceAudioTrack(boolean z) {
            this.forceAudioTrack = z;
            return this;
        }

        public Builder setIsLooping(boolean z) {
            this.isLooping = z;
            return this;
        }

        /* JADX WARNING: type inference failed for: r0v0, types: [F2.N, F2.Q] */
        public Builder(EditedMediaItem... editedMediaItemArr) {
            ? n = new N(4);
            int length = editedMediaItemArr.length;
            C0040v.a(length, editedMediaItemArr);
            n.d(length);
            System.arraycopy(editedMediaItemArr, 0, n.f247a, n.b, length);
            n.b += length;
            this.items = n;
        }

        /* JADX WARNING: type inference failed for: r0v0, types: [F2.N, F2.Q] */
        public Builder(List<EditedMediaItem> list) {
            ? n = new N(4);
            n.c(list);
            this.items = n;
        }

        /* JADX WARNING: type inference failed for: r0v0, types: [F2.N, F2.Q] */
        private Builder(EditedMediaItemSequence editedMediaItemSequence) {
            ? n = new N(4);
            n.c(editedMediaItemSequence.editedMediaItems);
            this.items = n;
            this.isLooping = editedMediaItemSequence.isLooping;
            this.forceAudioTrack = editedMediaItemSequence.forceAudioTrack;
            this.forceVideoTrack = editedMediaItemSequence.forceVideoTrack;
        }
    }

    public Builder buildUpon() {
        return new Builder();
    }

    public boolean hasGaps() {
        for (int i2 = 0; i2 < this.editedMediaItems.size(); i2++) {
            if (((EditedMediaItem) this.editedMediaItems.get(i2)).isGap()) {
                return true;
            }
        }
        return false;
    }

    private EditedMediaItemSequence(Builder builder) {
        y0 f = builder.items.f();
        this.editedMediaItems = f;
        boolean z = true;
        Assertions.checkArgument(!f.isEmpty(), "The sequence must contain at least one EditedMediaItem.");
        if (((EditedMediaItem) f.get(0)).isGap() && !builder.forceAudioTrack && !builder.forceVideoTrack) {
            z = false;
        }
        Assertions.checkArgument(z, "If the first item in the sequence is a Gap, then forceAudioTrack or forceVideoTrack flag must be set");
        this.isLooping = builder.isLooping;
        this.forceAudioTrack = builder.forceAudioTrack;
        this.forceVideoTrack = builder.forceVideoTrack;
    }
}
