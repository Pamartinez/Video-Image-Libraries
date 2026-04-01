package androidx.media3.exoplayer.trackselection;

import androidx.media3.common.Format;
import androidx.media3.common.TrackGroup;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface TrackSelection {
    Format getFormat(int i2);

    int getIndexInTrackGroup(int i2);

    TrackGroup getTrackGroup();

    int indexOf(int i2);

    int length();
}
