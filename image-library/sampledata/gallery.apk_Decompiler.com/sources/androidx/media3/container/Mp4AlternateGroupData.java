package androidx.media3.container;

import androidx.media3.common.Metadata;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class Mp4AlternateGroupData implements Metadata.Entry {
    public final int alternateGroup;

    public Mp4AlternateGroupData(int i2) {
        this.alternateGroup = i2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj instanceof Mp4AlternateGroupData) && this.alternateGroup == ((Mp4AlternateGroupData) obj).alternateGroup) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return this.alternateGroup;
    }

    public String toString() {
        return "Mp4AlternateGroup: " + this.alternateGroup;
    }
}
