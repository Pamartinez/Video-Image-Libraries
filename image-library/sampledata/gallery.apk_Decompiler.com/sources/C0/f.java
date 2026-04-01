package C0;

import c0.C0086a;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class f {

    /* renamed from: c  reason: collision with root package name */
    public static final f f96c = new f("COMPOSITION");

    /* renamed from: a  reason: collision with root package name */
    public final List f97a;
    public g b;

    public f(String... strArr) {
        this.f97a = Arrays.asList(strArr);
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x007e A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean a(int r7, java.lang.String r8) {
        /*
            r6 = this;
            java.util.List r6 = r6.f97a
            int r0 = r6.size()
            r1 = 0
            if (r7 < r0) goto L_0x000b
            goto L_0x0087
        L_0x000b:
            int r0 = r6.size()
            r2 = 1
            int r0 = r0 - r2
            if (r7 != r0) goto L_0x0015
            r0 = r2
            goto L_0x0016
        L_0x0015:
            r0 = r1
        L_0x0016:
            java.lang.Object r3 = r6.get(r7)
            java.lang.String r3 = (java.lang.String) r3
            java.lang.String r4 = "**"
            boolean r5 = r3.equals(r4)
            if (r5 != 0) goto L_0x004f
            boolean r8 = r3.equals(r8)
            if (r8 != 0) goto L_0x0035
            java.lang.String r8 = "*"
            boolean r8 = r3.equals(r8)
            if (r8 == 0) goto L_0x0033
            goto L_0x0035
        L_0x0033:
            r8 = r1
            goto L_0x0036
        L_0x0035:
            r8 = r2
        L_0x0036:
            if (r0 != 0) goto L_0x004c
            int r0 = r6.size()
            int r0 = r0 + -2
            if (r7 != r0) goto L_0x0087
            java.lang.Object r6 = c0.C0086a.f(r2, r6)
            java.lang.String r6 = (java.lang.String) r6
            boolean r6 = r6.equals(r4)
            if (r6 == 0) goto L_0x0087
        L_0x004c:
            if (r8 == 0) goto L_0x0087
            goto L_0x007e
        L_0x004f:
            if (r0 != 0) goto L_0x007c
            int r3 = r7 + 1
            java.lang.Object r3 = r6.get(r3)
            java.lang.String r3 = (java.lang.String) r3
            boolean r3 = r3.equals(r8)
            if (r3 == 0) goto L_0x007c
            int r8 = r6.size()
            int r8 = r8 + -2
            if (r7 == r8) goto L_0x007e
            int r8 = r6.size()
            int r8 = r8 + -3
            if (r7 != r8) goto L_0x0087
            java.lang.Object r6 = c0.C0086a.f(r2, r6)
            java.lang.String r6 = (java.lang.String) r6
            boolean r6 = r6.equals(r4)
            if (r6 == 0) goto L_0x0087
            goto L_0x007e
        L_0x007c:
            if (r0 == 0) goto L_0x007f
        L_0x007e:
            return r2
        L_0x007f:
            int r7 = r7 + r2
            int r0 = r6.size()
            int r0 = r0 - r2
            if (r7 >= r0) goto L_0x0088
        L_0x0087:
            return r1
        L_0x0088:
            java.lang.Object r6 = r6.get(r7)
            java.lang.String r6 = (java.lang.String) r6
            boolean r6 = r6.equals(r8)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: C0.f.a(int, java.lang.String):boolean");
    }

    public final int b(int i2, String str) {
        if ("__container".equals(str)) {
            return 0;
        }
        List list = this.f97a;
        if (!((String) list.get(i2)).equals("**")) {
            return 1;
        }
        if (i2 != list.size() - 1 && ((String) list.get(i2 + 1)).equals(str)) {
            return 2;
        }
        return 0;
    }

    public final boolean c(int i2, String str) {
        if ("__container".equals(str)) {
            return true;
        }
        List list = this.f97a;
        if (i2 >= list.size()) {
            return false;
        }
        if (((String) list.get(i2)).equals(str) || ((String) list.get(i2)).equals("**") || ((String) list.get(i2)).equals("*")) {
            return true;
        }
        return false;
    }

    public final boolean d(int i2, String str) {
        if ("__container".equals(str)) {
            return true;
        }
        List list = this.f97a;
        if (i2 < list.size() - 1 || ((String) list.get(i2)).equals("**")) {
            return true;
        }
        return false;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && f.class == obj.getClass()) {
            f fVar = (f) obj;
            if (!this.f97a.equals(fVar.f97a)) {
                return false;
            }
            g gVar = this.b;
            if (gVar != null) {
                return gVar.equals(fVar.b);
            }
            if (fVar.b == null) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        int i2;
        int hashCode = this.f97a.hashCode() * 31;
        g gVar = this.b;
        if (gVar != null) {
            i2 = gVar.hashCode();
        } else {
            i2 = 0;
        }
        return hashCode + i2;
    }

    public final String toString() {
        boolean z;
        StringBuilder sb2 = new StringBuilder("KeyPath{keys=");
        sb2.append(this.f97a);
        sb2.append(",resolved=");
        if (this.b != null) {
            z = true;
        } else {
            z = false;
        }
        return C0086a.n(sb2, z, '}');
    }

    public f(f fVar) {
        this.f97a = new ArrayList(fVar.f97a);
        this.b = fVar.b;
    }
}
