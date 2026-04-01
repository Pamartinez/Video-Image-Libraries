package androidx.media3.extractor.metadata.mp4;

import androidx.media3.common.Metadata;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class SmtaMetadataEntry implements Metadata.Entry {
    public final float captureFrameRate;
    public final int svcTemporalLayerCount;

    public SmtaMetadataEntry(float f, int i2) {
        this.captureFrameRate = f;
        this.svcTemporalLayerCount = i2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && SmtaMetadataEntry.class == obj.getClass()) {
            SmtaMetadataEntry smtaMetadataEntry = (SmtaMetadataEntry) obj;
            if (this.captureFrameRate == smtaMetadataEntry.captureFrameRate && this.svcTemporalLayerCount == smtaMetadataEntry.svcTemporalLayerCount) {
                return true;
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        return ((Float.valueOf(this.captureFrameRate).hashCode() + 527) * 31) + this.svcTemporalLayerCount;
    }

    public String toString() {
        return "smta: captureFrameRate=" + this.captureFrameRate + ", svcTemporalLayerCount=" + this.svcTemporalLayerCount;
    }
}
