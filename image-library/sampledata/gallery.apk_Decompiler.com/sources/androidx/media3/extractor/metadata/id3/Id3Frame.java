package androidx.media3.extractor.metadata.id3;

import androidx.media3.common.Metadata;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class Id3Frame implements Metadata.Entry {
    public final String id;

    public Id3Frame(String str) {
        this.id = str;
    }

    public String toString() {
        return this.id;
    }
}
