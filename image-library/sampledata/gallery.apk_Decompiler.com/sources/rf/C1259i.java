package rf;

/* renamed from: rf.i  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract /* synthetic */ class C1259i {

    /* renamed from: a  reason: collision with root package name */
    public static final /* synthetic */ int[] f5064a;
    public static final /* synthetic */ int[] b;

    /* JADX WARNING: Can't wrap try/catch for region: R(55:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|(2:13|14)|15|(2:17|18)|19|21|22|23|(2:25|26)|27|(2:29|30)|31|33|34|35|36|37|38|39|40|41|42|43|44|45|46|47|48|49|50|(2:51|52)|53|55|56|57|58|59|60|61|62|63|64|65|66|67|68|69|70|(3:71|72|74)) */
    /* JADX WARNING: Can't wrap try/catch for region: R(56:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|(2:13|14)|15|17|18|19|21|22|23|(2:25|26)|27|(2:29|30)|31|33|34|35|36|37|38|39|40|41|42|43|44|45|46|47|48|49|50|(2:51|52)|53|55|56|57|58|59|60|61|62|63|64|65|66|67|68|69|70|(3:71|72|74)) */
    /* JADX WARNING: Can't wrap try/catch for region: R(58:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|(2:13|14)|15|17|18|19|21|22|23|(2:25|26)|27|(2:29|30)|31|33|34|35|36|37|38|39|40|41|42|43|44|45|46|47|48|49|50|(2:51|52)|53|55|56|57|58|59|60|61|62|63|64|65|66|67|68|69|70|71|72|74) */
    /* JADX WARNING: Can't wrap try/catch for region: R(59:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|13|14|15|17|18|19|21|22|23|(2:25|26)|27|(2:29|30)|31|33|34|35|36|37|38|39|40|41|42|43|44|45|46|47|48|49|50|(2:51|52)|53|55|56|57|58|59|60|61|62|63|64|65|66|67|68|69|70|71|72|74) */
    /* JADX WARNING: Can't wrap try/catch for region: R(60:0|(2:1|2)|3|(2:5|6)|7|9|10|11|13|14|15|17|18|19|21|22|23|(2:25|26)|27|(2:29|30)|31|33|34|35|36|37|38|39|40|41|42|43|44|45|46|47|48|49|50|(2:51|52)|53|55|56|57|58|59|60|61|62|63|64|65|66|67|68|69|70|71|72|74) */
    /* JADX WARNING: Can't wrap try/catch for region: R(62:0|(2:1|2)|3|5|6|7|9|10|11|13|14|15|17|18|19|21|22|23|(2:25|26)|27|29|30|31|33|34|35|36|37|38|39|40|41|42|43|44|45|46|47|48|49|50|(2:51|52)|53|55|56|57|58|59|60|61|62|63|64|65|66|67|68|69|70|71|72|74) */
    /* JADX WARNING: Can't wrap try/catch for region: R(65:0|1|2|3|5|6|7|9|10|11|13|14|15|17|18|19|21|22|23|25|26|27|29|30|31|33|34|35|36|37|38|39|40|41|42|43|44|45|46|47|48|49|50|51|52|53|55|56|57|58|59|60|61|62|63|64|65|66|67|68|69|70|71|72|74) */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:35:0x006c */
    /* JADX WARNING: Missing exception handler attribute for start block: B:37:0x0078 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:39:0x0084 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:41:0x0090 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:43:0x009c */
    /* JADX WARNING: Missing exception handler attribute for start block: B:45:0x00a8 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:47:0x00b4 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:49:0x00c0 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:51:0x00cc */
    /* JADX WARNING: Missing exception handler attribute for start block: B:57:0x00e9 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:59:0x00f3 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:61:0x00fd */
    /* JADX WARNING: Missing exception handler attribute for start block: B:63:0x0107 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:65:0x0111 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:67:0x011b */
    /* JADX WARNING: Missing exception handler attribute for start block: B:69:0x0125 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:71:0x012f */
    static {
        /*
            rf.Q[] r0 = rf.Q.values()
            int r0 = r0.length
            int[] r0 = new int[r0]
            b = r0
            r1 = 1
            rf.Q r2 = rf.Q.DOUBLE     // Catch:{ NoSuchFieldError -> 0x0012 }
            int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
            r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x0012 }
        L_0x0012:
            r0 = 2
            int[] r2 = b     // Catch:{ NoSuchFieldError -> 0x001d }
            rf.Q r3 = rf.Q.FLOAT     // Catch:{ NoSuchFieldError -> 0x001d }
            int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
            r2[r3] = r0     // Catch:{ NoSuchFieldError -> 0x001d }
        L_0x001d:
            r2 = 3
            int[] r3 = b     // Catch:{ NoSuchFieldError -> 0x0028 }
            rf.Q r4 = rf.Q.INT64     // Catch:{ NoSuchFieldError -> 0x0028 }
            int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
            r3[r4] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
        L_0x0028:
            r3 = 4
            int[] r4 = b     // Catch:{ NoSuchFieldError -> 0x0033 }
            rf.Q r5 = rf.Q.UINT64     // Catch:{ NoSuchFieldError -> 0x0033 }
            int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
            r4[r5] = r3     // Catch:{ NoSuchFieldError -> 0x0033 }
        L_0x0033:
            r4 = 5
            int[] r5 = b     // Catch:{ NoSuchFieldError -> 0x003e }
            rf.Q r6 = rf.Q.INT32     // Catch:{ NoSuchFieldError -> 0x003e }
            int r6 = r6.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
            r5[r6] = r4     // Catch:{ NoSuchFieldError -> 0x003e }
        L_0x003e:
            r5 = 6
            int[] r6 = b     // Catch:{ NoSuchFieldError -> 0x0049 }
            rf.Q r7 = rf.Q.FIXED64     // Catch:{ NoSuchFieldError -> 0x0049 }
            int r7 = r7.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
            r6[r7] = r5     // Catch:{ NoSuchFieldError -> 0x0049 }
        L_0x0049:
            r6 = 7
            int[] r7 = b     // Catch:{ NoSuchFieldError -> 0x0054 }
            rf.Q r8 = rf.Q.FIXED32     // Catch:{ NoSuchFieldError -> 0x0054 }
            int r8 = r8.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
            r7[r8] = r6     // Catch:{ NoSuchFieldError -> 0x0054 }
        L_0x0054:
            r7 = 8
            int[] r8 = b     // Catch:{ NoSuchFieldError -> 0x0060 }
            rf.Q r9 = rf.Q.BOOL     // Catch:{ NoSuchFieldError -> 0x0060 }
            int r9 = r9.ordinal()     // Catch:{ NoSuchFieldError -> 0x0060 }
            r8[r9] = r7     // Catch:{ NoSuchFieldError -> 0x0060 }
        L_0x0060:
            r8 = 9
            int[] r9 = b     // Catch:{ NoSuchFieldError -> 0x006c }
            rf.Q r10 = rf.Q.STRING     // Catch:{ NoSuchFieldError -> 0x006c }
            int r10 = r10.ordinal()     // Catch:{ NoSuchFieldError -> 0x006c }
            r9[r10] = r8     // Catch:{ NoSuchFieldError -> 0x006c }
        L_0x006c:
            int[] r9 = b     // Catch:{ NoSuchFieldError -> 0x0078 }
            rf.Q r10 = rf.Q.BYTES     // Catch:{ NoSuchFieldError -> 0x0078 }
            int r10 = r10.ordinal()     // Catch:{ NoSuchFieldError -> 0x0078 }
            r11 = 10
            r9[r10] = r11     // Catch:{ NoSuchFieldError -> 0x0078 }
        L_0x0078:
            int[] r9 = b     // Catch:{ NoSuchFieldError -> 0x0084 }
            rf.Q r10 = rf.Q.UINT32     // Catch:{ NoSuchFieldError -> 0x0084 }
            int r10 = r10.ordinal()     // Catch:{ NoSuchFieldError -> 0x0084 }
            r11 = 11
            r9[r10] = r11     // Catch:{ NoSuchFieldError -> 0x0084 }
        L_0x0084:
            int[] r9 = b     // Catch:{ NoSuchFieldError -> 0x0090 }
            rf.Q r10 = rf.Q.SFIXED32     // Catch:{ NoSuchFieldError -> 0x0090 }
            int r10 = r10.ordinal()     // Catch:{ NoSuchFieldError -> 0x0090 }
            r11 = 12
            r9[r10] = r11     // Catch:{ NoSuchFieldError -> 0x0090 }
        L_0x0090:
            int[] r9 = b     // Catch:{ NoSuchFieldError -> 0x009c }
            rf.Q r10 = rf.Q.SFIXED64     // Catch:{ NoSuchFieldError -> 0x009c }
            int r10 = r10.ordinal()     // Catch:{ NoSuchFieldError -> 0x009c }
            r11 = 13
            r9[r10] = r11     // Catch:{ NoSuchFieldError -> 0x009c }
        L_0x009c:
            int[] r9 = b     // Catch:{ NoSuchFieldError -> 0x00a8 }
            rf.Q r10 = rf.Q.SINT32     // Catch:{ NoSuchFieldError -> 0x00a8 }
            int r10 = r10.ordinal()     // Catch:{ NoSuchFieldError -> 0x00a8 }
            r11 = 14
            r9[r10] = r11     // Catch:{ NoSuchFieldError -> 0x00a8 }
        L_0x00a8:
            int[] r9 = b     // Catch:{ NoSuchFieldError -> 0x00b4 }
            rf.Q r10 = rf.Q.SINT64     // Catch:{ NoSuchFieldError -> 0x00b4 }
            int r10 = r10.ordinal()     // Catch:{ NoSuchFieldError -> 0x00b4 }
            r11 = 15
            r9[r10] = r11     // Catch:{ NoSuchFieldError -> 0x00b4 }
        L_0x00b4:
            int[] r9 = b     // Catch:{ NoSuchFieldError -> 0x00c0 }
            rf.Q r10 = rf.Q.GROUP     // Catch:{ NoSuchFieldError -> 0x00c0 }
            int r10 = r10.ordinal()     // Catch:{ NoSuchFieldError -> 0x00c0 }
            r11 = 16
            r9[r10] = r11     // Catch:{ NoSuchFieldError -> 0x00c0 }
        L_0x00c0:
            int[] r9 = b     // Catch:{ NoSuchFieldError -> 0x00cc }
            rf.Q r10 = rf.Q.MESSAGE     // Catch:{ NoSuchFieldError -> 0x00cc }
            int r10 = r10.ordinal()     // Catch:{ NoSuchFieldError -> 0x00cc }
            r11 = 17
            r9[r10] = r11     // Catch:{ NoSuchFieldError -> 0x00cc }
        L_0x00cc:
            int[] r9 = b     // Catch:{ NoSuchFieldError -> 0x00d8 }
            rf.Q r10 = rf.Q.ENUM     // Catch:{ NoSuchFieldError -> 0x00d8 }
            int r10 = r10.ordinal()     // Catch:{ NoSuchFieldError -> 0x00d8 }
            r11 = 18
            r9[r10] = r11     // Catch:{ NoSuchFieldError -> 0x00d8 }
        L_0x00d8:
            rf.S[] r9 = rf.S.values()
            int r9 = r9.length
            int[] r9 = new int[r9]
            f5064a = r9
            rf.S r10 = rf.S.INT     // Catch:{ NoSuchFieldError -> 0x00e9 }
            int r10 = r10.ordinal()     // Catch:{ NoSuchFieldError -> 0x00e9 }
            r9[r10] = r1     // Catch:{ NoSuchFieldError -> 0x00e9 }
        L_0x00e9:
            int[] r1 = f5064a     // Catch:{ NoSuchFieldError -> 0x00f3 }
            rf.S r9 = rf.S.LONG     // Catch:{ NoSuchFieldError -> 0x00f3 }
            int r9 = r9.ordinal()     // Catch:{ NoSuchFieldError -> 0x00f3 }
            r1[r9] = r0     // Catch:{ NoSuchFieldError -> 0x00f3 }
        L_0x00f3:
            int[] r0 = f5064a     // Catch:{ NoSuchFieldError -> 0x00fd }
            rf.S r1 = rf.S.FLOAT     // Catch:{ NoSuchFieldError -> 0x00fd }
            int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00fd }
            r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00fd }
        L_0x00fd:
            int[] r0 = f5064a     // Catch:{ NoSuchFieldError -> 0x0107 }
            rf.S r1 = rf.S.DOUBLE     // Catch:{ NoSuchFieldError -> 0x0107 }
            int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0107 }
            r0[r1] = r3     // Catch:{ NoSuchFieldError -> 0x0107 }
        L_0x0107:
            int[] r0 = f5064a     // Catch:{ NoSuchFieldError -> 0x0111 }
            rf.S r1 = rf.S.BOOLEAN     // Catch:{ NoSuchFieldError -> 0x0111 }
            int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0111 }
            r0[r1] = r4     // Catch:{ NoSuchFieldError -> 0x0111 }
        L_0x0111:
            int[] r0 = f5064a     // Catch:{ NoSuchFieldError -> 0x011b }
            rf.S r1 = rf.S.STRING     // Catch:{ NoSuchFieldError -> 0x011b }
            int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x011b }
            r0[r1] = r5     // Catch:{ NoSuchFieldError -> 0x011b }
        L_0x011b:
            int[] r0 = f5064a     // Catch:{ NoSuchFieldError -> 0x0125 }
            rf.S r1 = rf.S.BYTE_STRING     // Catch:{ NoSuchFieldError -> 0x0125 }
            int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0125 }
            r0[r1] = r6     // Catch:{ NoSuchFieldError -> 0x0125 }
        L_0x0125:
            int[] r0 = f5064a     // Catch:{ NoSuchFieldError -> 0x012f }
            rf.S r1 = rf.S.ENUM     // Catch:{ NoSuchFieldError -> 0x012f }
            int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x012f }
            r0[r1] = r7     // Catch:{ NoSuchFieldError -> 0x012f }
        L_0x012f:
            int[] r0 = f5064a     // Catch:{ NoSuchFieldError -> 0x0139 }
            rf.S r1 = rf.S.MESSAGE     // Catch:{ NoSuchFieldError -> 0x0139 }
            int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0139 }
            r0[r1] = r8     // Catch:{ NoSuchFieldError -> 0x0139 }
        L_0x0139:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: rf.C1259i.<clinit>():void");
    }
}
