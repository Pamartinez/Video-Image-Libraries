package androidx.slice;

import androidx.versionedparcelable.VersionedParcelable;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class SliceSpec implements VersionedParcelable {
    int mRevision;
    String mType;

    public boolean equals(Object obj) {
        if (!(obj instanceof SliceSpec)) {
            return false;
        }
        SliceSpec sliceSpec = (SliceSpec) obj;
        if (!this.mType.equals(sliceSpec.mType) || this.mRevision != sliceSpec.mRevision) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return this.mType.hashCode() + this.mRevision;
    }

    public String toString() {
        return String.format("SliceSpec{%s,%d}", new Object[]{this.mType, Integer.valueOf(this.mRevision)});
    }
}
