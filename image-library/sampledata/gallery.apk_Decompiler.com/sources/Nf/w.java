package Nf;

import bf.g;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class w extends m {

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ int f3606c = 1;
    public final int d;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public w(int r3) {
        /*
            r2 = this;
            r0 = 0
            r2.f3606c = r0
            java.lang.String r0 = "must have at least "
            java.lang.String r1 = " value parameter"
            java.lang.StringBuilder r0 = c0.C0086a.o(r3, r0, r1)
            r1 = 1
            if (r3 <= r1) goto L_0x0011
            java.lang.String r1 = "s"
            goto L_0x0013
        L_0x0011:
            java.lang.String r1 = ""
        L_0x0013:
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r1 = 1
            r2.<init>(r0, r1)
            r2.d = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: Nf.w.<init>(int):void");
    }

    public final boolean a(g gVar) {
        switch (this.f3606c) {
            case 0:
                if (gVar.B().size() >= this.d) {
                    return true;
                }
                return false;
            default:
                if (gVar.B().size() == this.d) {
                    return true;
                }
                return false;
        }
    }

    public w() {
        super("must have exactly 2 value parameters", 1);
        this.d = 2;
    }
}
