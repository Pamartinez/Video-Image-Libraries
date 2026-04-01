package androidx.media3.extractor.metadata.id3;

import i.C0212a;
import java.util.Objects;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class UrlLinkFrame extends Id3Frame {
    public final String description;
    public final String url;

    public UrlLinkFrame(String str, String str2, String str3) {
        super(str);
        this.description = str2;
        this.url = str3;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && UrlLinkFrame.class == obj.getClass()) {
            UrlLinkFrame urlLinkFrame = (UrlLinkFrame) obj;
            if (!this.id.equals(urlLinkFrame.id) || !Objects.equals(this.description, urlLinkFrame.description) || !Objects.equals(this.url, urlLinkFrame.url)) {
                return false;
            }
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i2;
        int d = C0212a.d(527, 31, this.id);
        String str = this.description;
        int i7 = 0;
        if (str != null) {
            i2 = str.hashCode();
        } else {
            i2 = 0;
        }
        int i8 = (d + i2) * 31;
        String str2 = this.url;
        if (str2 != null) {
            i7 = str2.hashCode();
        }
        return i8 + i7;
    }

    public String toString() {
        return this.id + ": url=" + this.url;
    }
}
