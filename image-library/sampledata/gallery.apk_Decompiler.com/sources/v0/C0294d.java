package v0;

/* renamed from: v0.d  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract /* synthetic */ class C0294d {

    /* renamed from: a  reason: collision with root package name */
    public static final /* synthetic */ int[] f1951a;
    public static final /* synthetic */ int[] b;

    /* JADX WARNING: Can't wrap try/catch for region: R(27:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|(2:13|14)|15|(2:17|18)|19|21|22|23|24|25|27|28|29|30|31|32|33|34|35|36|(3:37|38|40)) */
    /* JADX WARNING: Can't wrap try/catch for region: R(31:0|1|2|3|(2:5|6)|7|9|10|11|(2:13|14)|15|(2:17|18)|19|21|22|23|24|25|27|28|29|30|31|32|33|34|35|36|37|38|40) */
    /* JADX WARNING: Can't wrap try/catch for region: R(32:0|1|2|3|(2:5|6)|7|9|10|11|13|14|15|(2:17|18)|19|21|22|23|24|25|27|28|29|30|31|32|33|34|35|36|37|38|40) */
    /* JADX WARNING: Can't wrap try/catch for region: R(33:0|1|2|3|(2:5|6)|7|9|10|11|13|14|15|17|18|19|21|22|23|24|25|27|28|29|30|31|32|33|34|35|36|37|38|40) */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x0049 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:29:0x0065 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:31:0x006f */
    /* JADX WARNING: Missing exception handler attribute for start block: B:33:0x0079 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:35:0x0083 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:37:0x008d */
    static {
        /*
            w0.o[] r0 = w0.o.values()
            int r0 = r0.length
            int[] r0 = new int[r0]
            b = r0
            r1 = 1
            w0.o r2 = w0.o.BREAK     // Catch:{ NoSuchFieldError -> 0x0012 }
            int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
            r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x0012 }
        L_0x0012:
            r0 = 2
            int[] r2 = b     // Catch:{ NoSuchFieldError -> 0x001d }
            w0.o r3 = w0.o.SIMPLE_VALUE     // Catch:{ NoSuchFieldError -> 0x001d }
            int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
            r2[r3] = r0     // Catch:{ NoSuchFieldError -> 0x001d }
        L_0x001d:
            r2 = 3
            int[] r3 = b     // Catch:{ NoSuchFieldError -> 0x0028 }
            w0.o r4 = w0.o.IEEE_754_HALF_PRECISION_FLOAT     // Catch:{ NoSuchFieldError -> 0x0028 }
            int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
            r3[r4] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
        L_0x0028:
            r3 = 4
            int[] r4 = b     // Catch:{ NoSuchFieldError -> 0x0033 }
            w0.o r5 = w0.o.IEEE_754_SINGLE_PRECISION_FLOAT     // Catch:{ NoSuchFieldError -> 0x0033 }
            int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
            r4[r5] = r3     // Catch:{ NoSuchFieldError -> 0x0033 }
        L_0x0033:
            r4 = 5
            int[] r5 = b     // Catch:{ NoSuchFieldError -> 0x003e }
            w0.o r6 = w0.o.IEEE_754_DOUBLE_PRECISION_FLOAT     // Catch:{ NoSuchFieldError -> 0x003e }
            int r6 = r6.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
            r5[r6] = r4     // Catch:{ NoSuchFieldError -> 0x003e }
        L_0x003e:
            r5 = 6
            int[] r6 = b     // Catch:{ NoSuchFieldError -> 0x0049 }
            w0.o r7 = w0.o.SIMPLE_VALUE_NEXT_BYTE     // Catch:{ NoSuchFieldError -> 0x0049 }
            int r7 = r7.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
            r6[r7] = r5     // Catch:{ NoSuchFieldError -> 0x0049 }
        L_0x0049:
            int[] r6 = b     // Catch:{ NoSuchFieldError -> 0x0054 }
            w0.o r7 = w0.o.UNALLOCATED     // Catch:{ NoSuchFieldError -> 0x0054 }
            int r7 = r7.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
            r8 = 7
            r6[r7] = r8     // Catch:{ NoSuchFieldError -> 0x0054 }
        L_0x0054:
            w0.m[] r6 = w0.m.values()
            int r6 = r6.length
            int[] r6 = new int[r6]
            f1951a = r6
            w0.m r7 = w0.m.FALSE     // Catch:{ NoSuchFieldError -> 0x0065 }
            int r7 = r7.ordinal()     // Catch:{ NoSuchFieldError -> 0x0065 }
            r6[r7] = r1     // Catch:{ NoSuchFieldError -> 0x0065 }
        L_0x0065:
            int[] r1 = f1951a     // Catch:{ NoSuchFieldError -> 0x006f }
            w0.m r6 = w0.m.TRUE     // Catch:{ NoSuchFieldError -> 0x006f }
            int r6 = r6.ordinal()     // Catch:{ NoSuchFieldError -> 0x006f }
            r1[r6] = r0     // Catch:{ NoSuchFieldError -> 0x006f }
        L_0x006f:
            int[] r0 = f1951a     // Catch:{ NoSuchFieldError -> 0x0079 }
            w0.m r1 = w0.m.NULL     // Catch:{ NoSuchFieldError -> 0x0079 }
            int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0079 }
            r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0079 }
        L_0x0079:
            int[] r0 = f1951a     // Catch:{ NoSuchFieldError -> 0x0083 }
            w0.m r1 = w0.m.UNDEFINED     // Catch:{ NoSuchFieldError -> 0x0083 }
            int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0083 }
            r0[r1] = r3     // Catch:{ NoSuchFieldError -> 0x0083 }
        L_0x0083:
            int[] r0 = f1951a     // Catch:{ NoSuchFieldError -> 0x008d }
            w0.m r1 = w0.m.UNALLOCATED     // Catch:{ NoSuchFieldError -> 0x008d }
            int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x008d }
            r0[r1] = r4     // Catch:{ NoSuchFieldError -> 0x008d }
        L_0x008d:
            int[] r0 = f1951a     // Catch:{ NoSuchFieldError -> 0x0097 }
            w0.m r1 = w0.m.RESERVED     // Catch:{ NoSuchFieldError -> 0x0097 }
            int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0097 }
            r0[r1] = r5     // Catch:{ NoSuchFieldError -> 0x0097 }
        L_0x0097:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: v0.C0294d.<clinit>():void");
    }
}
