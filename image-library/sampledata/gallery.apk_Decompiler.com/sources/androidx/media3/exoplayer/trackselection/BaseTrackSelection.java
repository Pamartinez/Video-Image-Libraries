package androidx.media3.exoplayer.trackselection;

import D6.a;
import androidx.media3.common.Format;
import androidx.media3.common.TrackGroup;
import androidx.media3.common.util.Assertions;
import java.util.Arrays;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class BaseTrackSelection implements ExoTrackSelection {
    private final long[] excludeUntilTimes;
    private final Format[] formats;
    protected final TrackGroup group;
    private int hashCode;
    protected final int length;
    private boolean playWhenReady;
    protected final int[] tracks;
    private final int type;

    public BaseTrackSelection(TrackGroup trackGroup, int[] iArr, int i2) {
        boolean z;
        if (iArr.length > 0) {
            z = true;
        } else {
            z = false;
        }
        Assertions.checkState(z);
        this.type = i2;
        this.group = (TrackGroup) Assertions.checkNotNull(trackGroup);
        int length2 = iArr.length;
        this.length = length2;
        this.formats = new Format[length2];
        for (int i7 = 0; i7 < iArr.length; i7++) {
            this.formats[i7] = trackGroup.getFormat(iArr[i7]);
        }
        Arrays.sort(this.formats, new a(3));
        this.tracks = new int[this.length];
        int i8 = 0;
        while (true) {
            int i10 = this.length;
            if (i8 < i10) {
                this.tracks[i8] = trackGroup.indexOf(this.formats[i8]);
                i8++;
            } else {
                this.excludeUntilTimes = new long[i10];
                this.playWhenReady = false;
                return;
            }
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ int lambda$new$0(Format format, Format format2) {
        return format2.bitrate - format.bitrate;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            BaseTrackSelection baseTrackSelection = (BaseTrackSelection) obj;
            if (!this.group.equals(baseTrackSelection.group) || !Arrays.equals(this.tracks, baseTrackSelection.tracks)) {
                return false;
            }
            return true;
        }
        return false;
    }

    public final Format getFormat(int i2) {
        return this.formats[i2];
    }

    public final int getIndexInTrackGroup(int i2) {
        return this.tracks[i2];
    }

    public final Format getSelectedFormat() {
        return this.formats[getSelectedIndex()];
    }

    public final int getSelectedIndexInTrackGroup() {
        return this.tracks[getSelectedIndex()];
    }

    public final TrackGroup getTrackGroup() {
        return this.group;
    }

    public int hashCode() {
        if (this.hashCode == 0) {
            this.hashCode = Arrays.hashCode(this.tracks) + (System.identityHashCode(this.group) * 31);
        }
        return this.hashCode;
    }

    public final int indexOf(int i2) {
        for (int i7 = 0; i7 < this.length; i7++) {
            if (this.tracks[i7] == i2) {
                return i7;
            }
        }
        return -1;
    }

    public final int length() {
        return this.tracks.length;
    }

    public void onPlayWhenReadyChanged(boolean z) {
        this.playWhenReady = z;
    }

    public void disable() {
    }

    public void enable() {
    }

    public void onPlaybackSpeed(float f) {
    }
}
