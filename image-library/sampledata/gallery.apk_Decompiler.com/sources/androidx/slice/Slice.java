package androidx.slice;

import androidx.versionedparcelable.VersionedParcelable;
import c0.C0086a;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import i.C0212a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class Slice implements VersionedParcelable {
    String[] mHints = new String[0];
    SliceItem[] mItems = new SliceItem[0];
    SliceSpec mSpec;
    String mUri;

    public static void appendHints(StringBuilder sb2, String[] strArr) {
        if (strArr != null && strArr.length != 0) {
            sb2.append('(');
            int length = strArr.length - 1;
            for (int i2 = 0; i2 < length; i2++) {
                sb2.append(strArr[i2]);
                sb2.append(ArcCommonLog.TAG_COMMA);
            }
            sb2.append(strArr[length]);
            sb2.append(")");
        }
    }

    public String toString() {
        return toString("");
    }

    public String toString(String str) {
        StringBuilder t = C0212a.t(str, "Slice ");
        String[] strArr = this.mHints;
        if (strArr.length > 0) {
            appendHints(t, strArr);
            t.append(' ');
        }
        t.append('[');
        t.append(this.mUri);
        t.append("] {\n");
        String str2 = str + "  ";
        int i2 = 0;
        while (true) {
            SliceItem[] sliceItemArr = this.mItems;
            if (i2 >= sliceItemArr.length) {
                return C0086a.m(t, str, '}');
            }
            t.append(sliceItemArr[i2].toString(str2));
            i2++;
        }
    }
}
