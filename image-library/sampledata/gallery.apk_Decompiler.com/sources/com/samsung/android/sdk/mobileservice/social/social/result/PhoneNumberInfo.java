package com.samsung.android.sdk.mobileservice.social.social.result;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PhoneNumberInfo {
    private final String hash;
    private final String number;
    private final Type type;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum Type {
        IMS,
        SIM,
        SERVICE_NUMBER,
        UNKNOWN
    }

    public PhoneNumberInfo(String str, String str2, Type type2) {
        this.hash = str;
        this.number = str2;
        this.type = type2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0039  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0046  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.samsung.android.sdk.mobileservice.social.social.result.PhoneNumberInfo.Type mapToType(java.lang.String r4) {
        /*
            int r0 = r4.hashCode()
            r1 = -2029057005(0xffffffff870f0c13, float:-1.0761673E-34)
            r2 = 2
            r3 = 1
            if (r0 == r1) goto L_0x002b
            r1 = 104399(0x197cf, float:1.46294E-40)
            if (r0 == r1) goto L_0x0021
            r1 = 113879(0x1bcd7, float:1.59578E-40)
            if (r0 == r1) goto L_0x0016
            goto L_0x0036
        L_0x0016:
            java.lang.String r0 = "sim"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x0036
            r4 = r3
            goto L_0x0037
        L_0x0021:
            java.lang.String r0 = "ims"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x0036
            r4 = 0
            goto L_0x0037
        L_0x002b:
            java.lang.String r0 = "service_number"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x0036
            r4 = r2
            goto L_0x0037
        L_0x0036:
            r4 = -1
        L_0x0037:
            if (r4 == 0) goto L_0x0046
            if (r4 == r3) goto L_0x0043
            if (r4 == r2) goto L_0x0040
            com.samsung.android.sdk.mobileservice.social.social.result.PhoneNumberInfo$Type r4 = com.samsung.android.sdk.mobileservice.social.social.result.PhoneNumberInfo.Type.UNKNOWN
            return r4
        L_0x0040:
            com.samsung.android.sdk.mobileservice.social.social.result.PhoneNumberInfo$Type r4 = com.samsung.android.sdk.mobileservice.social.social.result.PhoneNumberInfo.Type.SERVICE_NUMBER
            return r4
        L_0x0043:
            com.samsung.android.sdk.mobileservice.social.social.result.PhoneNumberInfo$Type r4 = com.samsung.android.sdk.mobileservice.social.social.result.PhoneNumberInfo.Type.SIM
            return r4
        L_0x0046:
            com.samsung.android.sdk.mobileservice.social.social.result.PhoneNumberInfo$Type r4 = com.samsung.android.sdk.mobileservice.social.social.result.PhoneNumberInfo.Type.IMS
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.mobileservice.social.social.result.PhoneNumberInfo.mapToType(java.lang.String):com.samsung.android.sdk.mobileservice.social.social.result.PhoneNumberInfo$Type");
    }

    public String getHash() {
        return this.hash;
    }

    public String getNumber() {
        return this.number;
    }

    public Type getType() {
        return this.type;
    }
}
