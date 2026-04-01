package androidx.media3.common;

import F2.G;
import F2.U;
import F2.y0;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import java.util.Arrays;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class Tracks {
    public static final Tracks EMPTY = new Tracks(y0.f278h);
    private static final String FIELD_TRACK_GROUPS = Util.intToStringMaxRadix(0);
    private final U groups;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Group {
        private static final String FIELD_ADAPTIVE_SUPPORTED = Util.intToStringMaxRadix(4);
        private static final String FIELD_TRACK_GROUP = Util.intToStringMaxRadix(0);
        private static final String FIELD_TRACK_SELECTED = Util.intToStringMaxRadix(3);
        private static final String FIELD_TRACK_SUPPORT = Util.intToStringMaxRadix(1);
        private final boolean adaptiveSupported;
        public final int length;
        private final TrackGroup mediaTrackGroup;
        private final boolean[] trackSelected;
        private final int[] trackSupport;

        public Group(TrackGroup trackGroup, boolean z, int[] iArr, boolean[] zArr) {
            boolean z3;
            int i2 = trackGroup.length;
            this.length = i2;
            boolean z7 = false;
            if (i2 == iArr.length && i2 == zArr.length) {
                z3 = true;
            } else {
                z3 = false;
            }
            Assertions.checkArgument(z3);
            this.mediaTrackGroup = trackGroup;
            if (z && i2 > 1) {
                z7 = true;
            }
            this.adaptiveSupported = z7;
            this.trackSupport = (int[]) iArr.clone();
            this.trackSelected = (boolean[]) zArr.clone();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj != null && Group.class == obj.getClass()) {
                Group group = (Group) obj;
                if (this.adaptiveSupported != group.adaptiveSupported || !this.mediaTrackGroup.equals(group.mediaTrackGroup) || !Arrays.equals(this.trackSupport, group.trackSupport) || !Arrays.equals(this.trackSelected, group.trackSelected)) {
                    return false;
                }
                return true;
            }
            return false;
        }

        public Format getTrackFormat(int i2) {
            return this.mediaTrackGroup.getFormat(i2);
        }

        public int getType() {
            return this.mediaTrackGroup.type;
        }

        public int hashCode() {
            int hashCode = Arrays.hashCode(this.trackSupport);
            return Arrays.hashCode(this.trackSelected) + ((hashCode + (((this.mediaTrackGroup.hashCode() * 31) + (this.adaptiveSupported ? 1 : 0)) * 31)) * 31);
        }

        public boolean isSelected() {
            for (boolean z : this.trackSelected) {
                if (z) {
                    return true;
                }
            }
            return false;
        }

        public boolean isTrackSelected(int i2) {
            return this.trackSelected[i2];
        }
    }

    static {
        G g = U.e;
    }

    public Tracks(List<Group> list) {
        this.groups = U.y(list);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || Tracks.class != obj.getClass()) {
            return false;
        }
        return this.groups.equals(((Tracks) obj).groups);
    }

    public U getGroups() {
        return this.groups;
    }

    public int hashCode() {
        return this.groups.hashCode();
    }

    public boolean isTypeSelected(int i2) {
        for (int i7 = 0; i7 < this.groups.size(); i7++) {
            Group group = (Group) this.groups.get(i7);
            if (group.isSelected() && group.getType() == i2) {
                return true;
            }
        }
        return false;
    }
}
