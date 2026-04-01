package E0;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract /* synthetic */ class g {

    /* renamed from: a  reason: collision with root package name */
    public static final /* synthetic */ int[] f135a;

    /* JADX WARNING: Can't wrap try/catch for region: R(36:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|33|34|(3:35|36|38)) */
    /* JADX WARNING: Can't wrap try/catch for region: R(38:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|33|34|35|36|38) */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
    /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0049 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0054 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0060 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x006c */
    /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x0078 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x0084 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x0090 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x009c */
    /* JADX WARNING: Missing exception handler attribute for start block: B:29:0x00a8 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:31:0x00b4 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:33:0x00c0 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:35:0x00cc */
    /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
    /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
    static {
        /*
            E0.h[] r0 = E0.h.values()
            int r0 = r0.length
            int[] r0 = new int[r0]
            f135a = r0
            E0.h r1 = E0.h.NORMAL     // Catch:{ NoSuchFieldError -> 0x0012 }
            int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
            r2 = 1
            r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
        L_0x0012:
            int[] r0 = f135a     // Catch:{ NoSuchFieldError -> 0x001d }
            E0.h r1 = E0.h.MULTIPLY     // Catch:{ NoSuchFieldError -> 0x001d }
            int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
            r2 = 2
            r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
        L_0x001d:
            int[] r0 = f135a     // Catch:{ NoSuchFieldError -> 0x0028 }
            E0.h r1 = E0.h.SCREEN     // Catch:{ NoSuchFieldError -> 0x0028 }
            int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
            r2 = 3
            r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
        L_0x0028:
            int[] r0 = f135a     // Catch:{ NoSuchFieldError -> 0x0033 }
            E0.h r1 = E0.h.OVERLAY     // Catch:{ NoSuchFieldError -> 0x0033 }
            int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
            r2 = 4
            r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
        L_0x0033:
            int[] r0 = f135a     // Catch:{ NoSuchFieldError -> 0x003e }
            E0.h r1 = E0.h.DARKEN     // Catch:{ NoSuchFieldError -> 0x003e }
            int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
            r2 = 5
            r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
        L_0x003e:
            int[] r0 = f135a     // Catch:{ NoSuchFieldError -> 0x0049 }
            E0.h r1 = E0.h.LIGHTEN     // Catch:{ NoSuchFieldError -> 0x0049 }
            int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
            r2 = 6
            r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
        L_0x0049:
            int[] r0 = f135a     // Catch:{ NoSuchFieldError -> 0x0054 }
            E0.h r1 = E0.h.ADD     // Catch:{ NoSuchFieldError -> 0x0054 }
            int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
            r2 = 7
            r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
        L_0x0054:
            int[] r0 = f135a     // Catch:{ NoSuchFieldError -> 0x0060 }
            E0.h r1 = E0.h.COLOR_DODGE     // Catch:{ NoSuchFieldError -> 0x0060 }
            int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0060 }
            r2 = 8
            r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0060 }
        L_0x0060:
            int[] r0 = f135a     // Catch:{ NoSuchFieldError -> 0x006c }
            E0.h r1 = E0.h.COLOR_BURN     // Catch:{ NoSuchFieldError -> 0x006c }
            int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x006c }
            r2 = 9
            r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x006c }
        L_0x006c:
            int[] r0 = f135a     // Catch:{ NoSuchFieldError -> 0x0078 }
            E0.h r1 = E0.h.HARD_LIGHT     // Catch:{ NoSuchFieldError -> 0x0078 }
            int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0078 }
            r2 = 10
            r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0078 }
        L_0x0078:
            int[] r0 = f135a     // Catch:{ NoSuchFieldError -> 0x0084 }
            E0.h r1 = E0.h.SOFT_LIGHT     // Catch:{ NoSuchFieldError -> 0x0084 }
            int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0084 }
            r2 = 11
            r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0084 }
        L_0x0084:
            int[] r0 = f135a     // Catch:{ NoSuchFieldError -> 0x0090 }
            E0.h r1 = E0.h.DIFFERENCE     // Catch:{ NoSuchFieldError -> 0x0090 }
            int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0090 }
            r2 = 12
            r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0090 }
        L_0x0090:
            int[] r0 = f135a     // Catch:{ NoSuchFieldError -> 0x009c }
            E0.h r1 = E0.h.EXCLUSION     // Catch:{ NoSuchFieldError -> 0x009c }
            int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x009c }
            r2 = 13
            r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x009c }
        L_0x009c:
            int[] r0 = f135a     // Catch:{ NoSuchFieldError -> 0x00a8 }
            E0.h r1 = E0.h.HUE     // Catch:{ NoSuchFieldError -> 0x00a8 }
            int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00a8 }
            r2 = 14
            r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00a8 }
        L_0x00a8:
            int[] r0 = f135a     // Catch:{ NoSuchFieldError -> 0x00b4 }
            E0.h r1 = E0.h.SATURATION     // Catch:{ NoSuchFieldError -> 0x00b4 }
            int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00b4 }
            r2 = 15
            r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00b4 }
        L_0x00b4:
            int[] r0 = f135a     // Catch:{ NoSuchFieldError -> 0x00c0 }
            E0.h r1 = E0.h.COLOR     // Catch:{ NoSuchFieldError -> 0x00c0 }
            int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00c0 }
            r2 = 16
            r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00c0 }
        L_0x00c0:
            int[] r0 = f135a     // Catch:{ NoSuchFieldError -> 0x00cc }
            E0.h r1 = E0.h.LUMINOSITY     // Catch:{ NoSuchFieldError -> 0x00cc }
            int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00cc }
            r2 = 17
            r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00cc }
        L_0x00cc:
            int[] r0 = f135a     // Catch:{ NoSuchFieldError -> 0x00d8 }
            E0.h r1 = E0.h.HARD_MIX     // Catch:{ NoSuchFieldError -> 0x00d8 }
            int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00d8 }
            r2 = 18
            r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00d8 }
        L_0x00d8:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: E0.g.<clinit>():void");
    }
}
