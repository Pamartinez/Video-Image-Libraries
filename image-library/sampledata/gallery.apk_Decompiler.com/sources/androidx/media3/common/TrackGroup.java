package androidx.media3.common;

import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Util;
import c0.C0086a;
import i.C0212a;
import java.util.Arrays;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class TrackGroup {
    private static final String FIELD_FORMATS = Util.intToStringMaxRadix(0);
    private static final String FIELD_ID = Util.intToStringMaxRadix(1);
    private final Format[] formats;
    private int hashCode;
    public final String id;
    public final int length;
    public final int type;

    public TrackGroup(Format... formatArr) {
        this("", formatArr);
    }

    private static void logErrorMessage(String str, String str2, String str3, int i2) {
        StringBuilder q = C0086a.q("Different ", str, " combined in one TrackGroup: '", str2, "' (track 0) and '");
        q.append(str3);
        q.append("' (track ");
        q.append(i2);
        q.append(")");
        Log.e("TrackGroup", "", new IllegalStateException(q.toString()));
    }

    private static String normalizeLanguage(String str) {
        if (str == null || str.equals("und")) {
            return "";
        }
        return str;
    }

    private static int normalizeRoleFlags(int i2) {
        return i2 | 16384;
    }

    private void verifyCorrectness() {
        String normalizeLanguage = normalizeLanguage(this.formats[0].language);
        int normalizeRoleFlags = normalizeRoleFlags(this.formats[0].roleFlags);
        int i2 = 1;
        while (true) {
            Format[] formatArr = this.formats;
            if (i2 >= formatArr.length) {
                return;
            }
            if (!normalizeLanguage.equals(normalizeLanguage(formatArr[i2].language))) {
                Format[] formatArr2 = this.formats;
                logErrorMessage("languages", formatArr2[0].language, formatArr2[i2].language, i2);
                return;
            } else if (normalizeRoleFlags != normalizeRoleFlags(this.formats[i2].roleFlags)) {
                logErrorMessage("role flags", Integer.toBinaryString(this.formats[0].roleFlags), Integer.toBinaryString(this.formats[i2].roleFlags), i2);
                return;
            } else {
                i2++;
            }
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && TrackGroup.class == obj.getClass()) {
            TrackGroup trackGroup = (TrackGroup) obj;
            if (!this.id.equals(trackGroup.id) || !Arrays.equals(this.formats, trackGroup.formats)) {
                return false;
            }
            return true;
        }
        return false;
    }

    public Format getFormat(int i2) {
        return this.formats[i2];
    }

    public int hashCode() {
        if (this.hashCode == 0) {
            this.hashCode = Arrays.hashCode(this.formats) + C0212a.d(527, 31, this.id);
        }
        return this.hashCode;
    }

    public int indexOf(Format format) {
        int i2 = 0;
        while (true) {
            Format[] formatArr = this.formats;
            if (i2 >= formatArr.length) {
                return -1;
            }
            if (format == formatArr[i2]) {
                return i2;
            }
            i2++;
        }
    }

    public String toString() {
        return this.id + ": " + Arrays.toString(this.formats);
    }

    public TrackGroup(String str, Format... formatArr) {
        Assertions.checkArgument(formatArr.length > 0);
        this.id = str;
        this.formats = formatArr;
        this.length = formatArr.length;
        int trackType = MimeTypes.getTrackType(formatArr[0].sampleMimeType);
        this.type = trackType == -1 ? MimeTypes.getTrackType(formatArr[0].containerMimeType) : trackType;
        verifyCorrectness();
    }
}
