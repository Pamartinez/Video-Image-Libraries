package androidx.media3.extractor.metadata.id3;

import java.util.Objects;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class CommentFrame extends Id3Frame {
    public final String description;
    public final String language;
    public final String text;

    public CommentFrame(String str, String str2, String str3) {
        super("COMM");
        this.language = str;
        this.description = str2;
        this.text = str3;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && CommentFrame.class == obj.getClass()) {
            CommentFrame commentFrame = (CommentFrame) obj;
            if (!Objects.equals(this.description, commentFrame.description) || !Objects.equals(this.language, commentFrame.language) || !Objects.equals(this.text, commentFrame.text)) {
                return false;
            }
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i2;
        int i7;
        String str = this.language;
        int i8 = 0;
        if (str != null) {
            i2 = str.hashCode();
        } else {
            i2 = 0;
        }
        int i10 = (527 + i2) * 31;
        String str2 = this.description;
        if (str2 != null) {
            i7 = str2.hashCode();
        } else {
            i7 = 0;
        }
        int i11 = (i10 + i7) * 31;
        String str3 = this.text;
        if (str3 != null) {
            i8 = str3.hashCode();
        }
        return i11 + i8;
    }

    public String toString() {
        return this.id + ": language=" + this.language + ", description=" + this.description + ", text=" + this.text;
    }
}
