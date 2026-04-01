package C0;

import i.C0212a;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class e {

    /* renamed from: a  reason: collision with root package name */
    public final ArrayList f94a;
    public final char b;

    /* renamed from: c  reason: collision with root package name */
    public final double f95c;
    public final String d;
    public final String e;

    public e(ArrayList arrayList, char c5, double d2, String str, String str2) {
        this.f94a = arrayList;
        this.b = c5;
        this.f95c = d2;
        this.d = str;
        this.e = str2;
    }

    public static int a(char c5, String str, String str2) {
        return str2.hashCode() + C0212a.d(c5 * 31, 31, str);
    }

    public final int hashCode() {
        return a(this.b, this.e, this.d);
    }
}
