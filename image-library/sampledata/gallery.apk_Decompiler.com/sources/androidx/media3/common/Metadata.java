package androidx.media3.common;

import androidx.media3.common.MediaMetadata;
import androidx.media3.common.util.Util;
import java.util.Arrays;
import java.util.List;
import og.k;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class Metadata {
    private final Entry[] entries;
    public final long presentationTimeUs;

    public Metadata(Entry... entryArr) {
        this(-9223372036854775807L, entryArr);
    }

    public Metadata copyWithAppendedEntries(Entry... entryArr) {
        if (entryArr.length == 0) {
            return this;
        }
        return new Metadata(this.presentationTimeUs, (Entry[]) Util.nullSafeArrayConcatenation(this.entries, entryArr));
    }

    public Metadata copyWithAppendedEntriesFrom(Metadata metadata) {
        if (metadata == null) {
            return this;
        }
        return copyWithAppendedEntries(metadata.entries);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && Metadata.class == obj.getClass()) {
            Metadata metadata = (Metadata) obj;
            if (!Arrays.equals(this.entries, metadata.entries) || this.presentationTimeUs != metadata.presentationTimeUs) {
                return false;
            }
            return true;
        }
        return false;
    }

    public Entry get(int i2) {
        return this.entries[i2];
    }

    public int hashCode() {
        return k.D(this.presentationTimeUs) + (Arrays.hashCode(this.entries) * 31);
    }

    public int length() {
        return this.entries.length;
    }

    public String toString() {
        String str;
        StringBuilder sb2 = new StringBuilder("entries=");
        sb2.append(Arrays.toString(this.entries));
        if (this.presentationTimeUs == -9223372036854775807L) {
            str = "";
        } else {
            str = ", presentationTimeUs=" + this.presentationTimeUs;
        }
        sb2.append(str);
        return sb2.toString();
    }

    public Metadata(long j2, Entry... entryArr) {
        this.presentationTimeUs = j2;
        this.entries = entryArr;
    }

    public Metadata(List<? extends Entry> list) {
        this((Entry[]) list.toArray(new Entry[0]));
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface Entry {
        void populateMediaMetadata(MediaMetadata.Builder builder) {
        }
    }
}
