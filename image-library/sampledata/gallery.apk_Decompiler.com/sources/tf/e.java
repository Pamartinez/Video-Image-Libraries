package Tf;

import D1.f;
import c0.C0086a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class e {
    public static final e b = new e();

    /* renamed from: a  reason: collision with root package name */
    public final boolean f3819a = true;

    public e() {
        if (!f.g("")) {
            f.g("");
        }
    }

    public final void a(StringBuilder sb2, String str) {
        C0086a.z(sb2, str, "prefix = \"", "", "\",");
        sb2.append(10);
        sb2.append(str);
        sb2.append("suffix = \"");
        sb2.append("");
        sb2.append("\",");
        sb2.append(10);
        sb2.append(str);
        sb2.append("removeLeadingZeros = ");
        sb2.append(false);
        sb2.append(',');
        sb2.append(10);
        sb2.append(str);
        sb2.append("minLength = ");
        sb2.append(1);
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder();
        sb2.append("NumberHexFormat(\n");
        a(sb2, "    ");
        sb2.append(10);
        sb2.append(")");
        return sb2.toString();
    }
}
