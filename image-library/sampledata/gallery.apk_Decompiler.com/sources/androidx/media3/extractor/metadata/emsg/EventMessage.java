package androidx.media3.extractor.metadata.emsg;

import androidx.media3.common.Format;
import androidx.media3.common.Metadata;
import java.util.Arrays;
import java.util.Objects;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class EventMessage implements Metadata.Entry {
    private static final Format ID3_FORMAT = new Format.Builder().setSampleMimeType("application/id3").build();
    private static final Format SCTE35_FORMAT = new Format.Builder().setSampleMimeType("application/x-scte35").build();
    public final long durationMs;
    private int hashCode;
    public final long id;
    public final byte[] messageData;
    public final String schemeIdUri;
    public final String value;

    public EventMessage(String str, String str2, long j2, long j3, byte[] bArr) {
        this.schemeIdUri = str;
        this.value = str2;
        this.durationMs = j2;
        this.id = j3;
        this.messageData = bArr;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && EventMessage.class == obj.getClass()) {
            EventMessage eventMessage = (EventMessage) obj;
            if (this.durationMs != eventMessage.durationMs || this.id != eventMessage.id || !Objects.equals(this.schemeIdUri, eventMessage.schemeIdUri) || !Objects.equals(this.value, eventMessage.value) || !Arrays.equals(this.messageData, eventMessage.messageData)) {
                return false;
            }
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i2;
        if (this.hashCode == 0) {
            String str = this.schemeIdUri;
            int i7 = 0;
            if (str != null) {
                i2 = str.hashCode();
            } else {
                i2 = 0;
            }
            int i8 = (527 + i2) * 31;
            String str2 = this.value;
            if (str2 != null) {
                i7 = str2.hashCode();
            }
            long j2 = this.durationMs;
            long j3 = this.id;
            this.hashCode = Arrays.hashCode(this.messageData) + ((((((i8 + i7) * 31) + ((int) (j2 ^ (j2 >>> 32)))) * 31) + ((int) (j3 ^ (j3 >>> 32)))) * 31);
        }
        return this.hashCode;
    }

    public String toString() {
        return "EMSG: scheme=" + this.schemeIdUri + ", id=" + this.id + ", durationMs=" + this.durationMs + ", value=" + this.value;
    }
}
