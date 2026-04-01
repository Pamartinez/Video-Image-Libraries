package androidx.media3.exoplayer.trackselection;

import androidx.media3.common.TrackGroup;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class FixedTrackSelection extends BaseTrackSelection {
    private final Object data;
    private final int reason;

    public FixedTrackSelection(TrackGroup trackGroup, int i2, int i7) {
        this(trackGroup, i2, i7, 0, (Object) null);
    }

    public int getSelectedIndex() {
        return 0;
    }

    public FixedTrackSelection(TrackGroup trackGroup, int i2, int i7, int i8, Object obj) {
        super(trackGroup, new int[]{i2}, i7);
        this.reason = i8;
        this.data = obj;
    }
}
