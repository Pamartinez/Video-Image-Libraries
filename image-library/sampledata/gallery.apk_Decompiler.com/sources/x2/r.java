package X2;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class r {

    /* renamed from: a  reason: collision with root package name */
    public final s f937a;

    public r(s sVar) {
        this.f937a = sVar;
    }

    public static void b(StringBuilder sb2, String str) {
        if (str != null && !str.isEmpty()) {
            if (sb2.length() > 0) {
                sb2.append(10);
            }
            sb2.append(str);
        }
    }

    public static void c(StringBuilder sb2, String[] strArr) {
        if (strArr != null) {
            for (String b : strArr) {
                b(sb2, b);
            }
        }
    }

    public abstract String a();

    public final String toString() {
        return a();
    }
}
