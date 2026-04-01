package com.google.android.appfunctions.schema.internal.dependencies;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class L {

    /* renamed from: a  reason: collision with root package name */
    public final A f1209a;
    public final String b;

    /* renamed from: c  reason: collision with root package name */
    public final Object[] f1210c;
    public final int d;

    public L(C0102l lVar, String str, Object[] objArr) {
        this.f1209a = lVar;
        this.b = str;
        this.f1210c = objArr;
        char charAt = str.charAt(0);
        if (charAt < 55296) {
            this.d = charAt;
            return;
        }
        char c5 = charAt & 8191;
        int i2 = 1;
        int i7 = 13;
        while (true) {
            int i8 = i2 + 1;
            char charAt2 = str.charAt(i2);
            if (charAt2 >= 55296) {
                c5 |= (charAt2 & 8191) << i7;
                i7 += 13;
                i2 = i8;
            } else {
                this.d = c5 | (charAt2 << i7);
                return;
            }
        }
    }

    public final int a() {
        int i2 = this.d;
        if ((i2 & 1) != 0) {
            return 1;
        }
        if ((i2 & 4) == 4) {
            return 3;
        }
        return 2;
    }
}
