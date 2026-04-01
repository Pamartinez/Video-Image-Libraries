package Tf;

import D1.f;
import c0.C0086a;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class d {

    /* renamed from: a  reason: collision with root package name */
    public static final d f3818a;

    /* JADX WARNING: type inference failed for: r0v0, types: [java.lang.Object, Tf.d] */
    static {
        ? obj = new Object();
        if (!f.g("  ") && !f.g("") && !f.g("")) {
            f.g("");
        }
        f3818a = obj;
    }

    public final void a(StringBuilder sb2, String str) {
        sb2.append(str);
        sb2.append("bytesPerLine = ");
        sb2.append(Integer.MAX_VALUE);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(10);
        sb2.append(str);
        sb2.append("bytesPerGroup = ");
        sb2.append(Integer.MAX_VALUE);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(10);
        sb2.append(str);
        sb2.append("groupSeparator = \"");
        sb2.append("  ");
        sb2.append("\",");
        sb2.append(10);
        sb2.append(str);
        sb2.append("byteSeparator = \"");
        sb2.append("");
        sb2.append("\",");
        sb2.append(10);
        C0086a.z(sb2, str, "bytePrefix = \"", "", "\",");
        sb2.append(10);
        sb2.append(str);
        sb2.append("byteSuffix = \"");
        sb2.append("");
        sb2.append("\"");
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder();
        sb2.append("BytesHexFormat(\n");
        a(sb2, "    ");
        sb2.append(10);
        sb2.append(")");
        return sb2.toString();
    }
}
