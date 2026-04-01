package com.google.protobuf;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class f0 {

    /* renamed from: a  reason: collision with root package name */
    public final MessageLite f1603a;
    public final String b;

    /* renamed from: c  reason: collision with root package name */
    public final Object[] f1604c;
    public final int d;

    public f0(MessageLite messageLite, String str, Object[] objArr) {
        this.f1603a = messageLite;
        this.b = str;
        this.f1604c = objArr;
        char charAt = str.charAt(0);
        if (charAt < 55296) {
            this.d = charAt;
            return;
        }
        char c5 = charAt & 8191;
        int i2 = 13;
        int i7 = 1;
        while (true) {
            int i8 = i7 + 1;
            char charAt2 = str.charAt(i7);
            if (charAt2 >= 55296) {
                c5 |= (charAt2 & 8191) << i2;
                i2 += 13;
                i7 = i8;
            } else {
                this.d = c5 | (charAt2 << i2);
                return;
            }
        }
    }

    public final c0 a() {
        int i2 = this.d;
        if ((i2 & 1) != 0) {
            return c0.PROTO2;
        }
        if ((i2 & 4) == 4) {
            return c0.EDITIONS;
        }
        return c0.PROTO3;
    }
}
