package androidx.media3.exoplayer.source;

import F2.C0040v;
import F2.U;
import K.a;
import androidx.media3.common.TrackGroup;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Util;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class TrackGroupArray {
    public static final TrackGroupArray EMPTY = new TrackGroupArray(new TrackGroup[0]);
    private static final String FIELD_TRACK_GROUPS = Util.intToStringMaxRadix(0);
    private int hashCode;
    public final int length;
    private final U trackGroups;

    public TrackGroupArray(TrackGroup... trackGroupArr) {
        this.trackGroups = U.z(trackGroupArr);
        this.length = trackGroupArr.length;
        verifyCorrectness();
    }

    private void verifyCorrectness() {
        int i2 = 0;
        while (i2 < this.trackGroups.size()) {
            int i7 = i2 + 1;
            for (int i8 = i7; i8 < this.trackGroups.size(); i8++) {
                if (((TrackGroup) this.trackGroups.get(i2)).equals(this.trackGroups.get(i8))) {
                    Log.e("TrackGroupArray", "", new IllegalArgumentException("Multiple identical TrackGroups added to one TrackGroupArray."));
                }
            }
            i2 = i7;
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && TrackGroupArray.class == obj.getClass()) {
            TrackGroupArray trackGroupArray = (TrackGroupArray) obj;
            if (this.length != trackGroupArray.length || !this.trackGroups.equals(trackGroupArray.trackGroups)) {
                return false;
            }
            return true;
        }
        return false;
    }

    public TrackGroup get(int i2) {
        return (TrackGroup) this.trackGroups.get(i2);
    }

    public U getTrackTypes() {
        return U.y(C0040v.t(this.trackGroups, new a(6)));
    }

    public int hashCode() {
        if (this.hashCode == 0) {
            this.hashCode = this.trackGroups.hashCode();
        }
        return this.hashCode;
    }

    public int indexOf(TrackGroup trackGroup) {
        int indexOf = this.trackGroups.indexOf(trackGroup);
        if (indexOf >= 0) {
            return indexOf;
        }
        return -1;
    }

    public String toString() {
        return this.trackGroups.toString();
    }
}
