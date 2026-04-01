package F0;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract /* synthetic */ class b {

    /* renamed from: a  reason: collision with root package name */
    public static final /* synthetic */ int[] f180a;
    public static final /* synthetic */ int[] b;

    /* JADX WARNING: Can't wrap try/catch for region: R(23:0|(2:1|2)|3|(2:5|6)|7|9|10|11|(2:13|14)|15|17|18|19|20|21|22|23|24|25|26|27|28|(3:29|30|32)) */
    /* JADX WARNING: Can't wrap try/catch for region: R(27:0|1|2|3|(2:5|6)|7|9|10|11|13|14|15|17|18|19|20|21|22|23|24|25|26|27|28|29|30|32) */
    /* JADX WARNING: Can't wrap try/catch for region: R(28:0|1|2|3|5|6|7|9|10|11|13|14|15|17|18|19|20|21|22|23|24|25|26|27|28|29|30|32) */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x0044 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x004e */
    /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x0058 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x0062 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x006d */
    /* JADX WARNING: Missing exception handler attribute for start block: B:29:0x0078 */
    static {
        /*
            E0.i[] r0 = E0.i.values()
            int r0 = r0.length
            int[] r0 = new int[r0]
            b = r0
            r1 = 1
            E0.i r2 = E0.i.MASK_MODE_NONE     // Catch:{ NoSuchFieldError -> 0x0012 }
            int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
            r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x0012 }
        L_0x0012:
            r0 = 2
            int[] r2 = b     // Catch:{ NoSuchFieldError -> 0x001d }
            E0.i r3 = E0.i.MASK_MODE_SUBTRACT     // Catch:{ NoSuchFieldError -> 0x001d }
            int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
            r2[r3] = r0     // Catch:{ NoSuchFieldError -> 0x001d }
        L_0x001d:
            r2 = 3
            int[] r3 = b     // Catch:{ NoSuchFieldError -> 0x0028 }
            E0.i r4 = E0.i.MASK_MODE_INTERSECT     // Catch:{ NoSuchFieldError -> 0x0028 }
            int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
            r3[r4] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
        L_0x0028:
            r3 = 4
            int[] r4 = b     // Catch:{ NoSuchFieldError -> 0x0033 }
            E0.i r5 = E0.i.MASK_MODE_ADD     // Catch:{ NoSuchFieldError -> 0x0033 }
            int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
            r4[r5] = r3     // Catch:{ NoSuchFieldError -> 0x0033 }
        L_0x0033:
            F0.g[] r4 = F0.g.values()
            int r4 = r4.length
            int[] r4 = new int[r4]
            f180a = r4
            F0.g r5 = F0.g.SHAPE     // Catch:{ NoSuchFieldError -> 0x0044 }
            int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x0044 }
            r4[r5] = r1     // Catch:{ NoSuchFieldError -> 0x0044 }
        L_0x0044:
            int[] r1 = f180a     // Catch:{ NoSuchFieldError -> 0x004e }
            F0.g r4 = F0.g.PRE_COMP     // Catch:{ NoSuchFieldError -> 0x004e }
            int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x004e }
            r1[r4] = r0     // Catch:{ NoSuchFieldError -> 0x004e }
        L_0x004e:
            int[] r0 = f180a     // Catch:{ NoSuchFieldError -> 0x0058 }
            F0.g r1 = F0.g.SOLID     // Catch:{ NoSuchFieldError -> 0x0058 }
            int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0058 }
            r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0058 }
        L_0x0058:
            int[] r0 = f180a     // Catch:{ NoSuchFieldError -> 0x0062 }
            F0.g r1 = F0.g.IMAGE     // Catch:{ NoSuchFieldError -> 0x0062 }
            int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0062 }
            r0[r1] = r3     // Catch:{ NoSuchFieldError -> 0x0062 }
        L_0x0062:
            int[] r0 = f180a     // Catch:{ NoSuchFieldError -> 0x006d }
            F0.g r1 = F0.g.NULL     // Catch:{ NoSuchFieldError -> 0x006d }
            int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x006d }
            r2 = 5
            r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x006d }
        L_0x006d:
            int[] r0 = f180a     // Catch:{ NoSuchFieldError -> 0x0078 }
            F0.g r1 = F0.g.TEXT     // Catch:{ NoSuchFieldError -> 0x0078 }
            int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0078 }
            r2 = 6
            r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0078 }
        L_0x0078:
            int[] r0 = f180a     // Catch:{ NoSuchFieldError -> 0x0083 }
            F0.g r1 = F0.g.UNKNOWN     // Catch:{ NoSuchFieldError -> 0x0083 }
            int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0083 }
            r2 = 7
            r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0083 }
        L_0x0083:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: F0.b.<clinit>():void");
    }
}
