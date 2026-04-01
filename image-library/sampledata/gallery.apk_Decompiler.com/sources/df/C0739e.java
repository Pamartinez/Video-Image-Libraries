package Df;

/* renamed from: Df.e  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract /* synthetic */ class C0739e {

    /* renamed from: a  reason: collision with root package name */
    public static final /* synthetic */ int[] f3342a;

    /* JADX WARNING: Can't wrap try/catch for region: R(28:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|(2:25|26)|27|29) */
    /* JADX WARNING: Can't wrap try/catch for region: R(29:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|29) */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0034 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x003d */
    /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0046 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0050 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x005a */
    /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x0064 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x006e */
    /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x0078 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0022 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x002b */
    static {
        /*
            lf.c[] r0 = lf.C1150c.values()
            int r0 = r0.length
            int[] r0 = new int[r0]
            lf.c r1 = lf.C1150c.BYTE     // Catch:{ NoSuchFieldError -> 0x0010 }
            int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
            r2 = 1
            r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
        L_0x0010:
            lf.c r1 = lf.C1150c.CHAR     // Catch:{ NoSuchFieldError -> 0x0019 }
            int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
            r2 = 2
            r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
        L_0x0019:
            lf.c r1 = lf.C1150c.SHORT     // Catch:{ NoSuchFieldError -> 0x0022 }
            int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
            r2 = 3
            r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
        L_0x0022:
            lf.c r1 = lf.C1150c.INT     // Catch:{ NoSuchFieldError -> 0x002b }
            int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002b }
            r2 = 4
            r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002b }
        L_0x002b:
            lf.c r1 = lf.C1150c.LONG     // Catch:{ NoSuchFieldError -> 0x0034 }
            int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0034 }
            r2 = 5
            r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0034 }
        L_0x0034:
            lf.c r1 = lf.C1150c.FLOAT     // Catch:{ NoSuchFieldError -> 0x003d }
            int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003d }
            r2 = 6
            r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003d }
        L_0x003d:
            lf.c r1 = lf.C1150c.DOUBLE     // Catch:{ NoSuchFieldError -> 0x0046 }
            int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0046 }
            r2 = 7
            r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0046 }
        L_0x0046:
            lf.c r1 = lf.C1150c.BOOLEAN     // Catch:{ NoSuchFieldError -> 0x0050 }
            int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0050 }
            r2 = 8
            r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0050 }
        L_0x0050:
            lf.c r1 = lf.C1150c.STRING     // Catch:{ NoSuchFieldError -> 0x005a }
            int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x005a }
            r2 = 9
            r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x005a }
        L_0x005a:
            lf.c r1 = lf.C1150c.CLASS     // Catch:{ NoSuchFieldError -> 0x0064 }
            int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0064 }
            r2 = 10
            r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0064 }
        L_0x0064:
            lf.c r1 = lf.C1150c.ENUM     // Catch:{ NoSuchFieldError -> 0x006e }
            int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x006e }
            r2 = 11
            r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x006e }
        L_0x006e:
            lf.c r1 = lf.C1150c.ANNOTATION     // Catch:{ NoSuchFieldError -> 0x0078 }
            int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0078 }
            r2 = 12
            r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0078 }
        L_0x0078:
            lf.c r1 = lf.C1150c.ARRAY     // Catch:{ NoSuchFieldError -> 0x0082 }
            int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0082 }
            r2 = 13
            r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0082 }
        L_0x0082:
            f3342a = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: Df.C0739e.<clinit>():void");
    }
}
