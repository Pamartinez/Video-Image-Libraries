package com.samsung.android.sdk.mobileservice.profile.result;

import com.samsung.android.sdk.mobileservice.common.result.CommonResultStatus;
import com.samsung.android.sdk.mobileservice.common.result.Result;
import com.samsung.android.sdk.mobileservice.profile.ProfileApi;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ProfileBirthdayTypeResult implements Result {
    private ProfileApi.ProfileBirthdayType mProfileBirthdayType;
    private CommonResultStatus mStatus;

    public ProfileBirthdayTypeResult(CommonResultStatus commonResultStatus, String str) {
        this.mStatus = commonResultStatus;
        this.mProfileBirthdayType = convertStringToProfileBirthdayType(str);
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.samsung.android.sdk.mobileservice.profile.ProfileApi.ProfileBirthdayType convertStringToProfileBirthdayType(java.lang.String r3) {
        /*
            r2 = this;
            int r2 = r3.hashCode()
            r0 = 1
            r1 = 2
            switch(r2) {
                case 48: goto L_0x001e;
                case 49: goto L_0x0014;
                case 50: goto L_0x000a;
                default: goto L_0x0009;
            }
        L_0x0009:
            goto L_0x0028
        L_0x000a:
            java.lang.String r2 = "2"
            boolean r2 = r3.equals(r2)
            if (r2 == 0) goto L_0x0028
            r2 = r1
            goto L_0x0029
        L_0x0014:
            java.lang.String r2 = "1"
            boolean r2 = r3.equals(r2)
            if (r2 == 0) goto L_0x0028
            r2 = r0
            goto L_0x0029
        L_0x001e:
            java.lang.String r2 = "0"
            boolean r2 = r3.equals(r2)
            if (r2 == 0) goto L_0x0028
            r2 = 0
            goto L_0x0029
        L_0x0028:
            r2 = -1
        L_0x0029:
            if (r2 == 0) goto L_0x0038
            if (r2 == r0) goto L_0x0035
            if (r2 == r1) goto L_0x0032
            com.samsung.android.sdk.mobileservice.profile.ProfileApi$ProfileBirthdayType r2 = com.samsung.android.sdk.mobileservice.profile.ProfileApi.ProfileBirthdayType.INVALID
            return r2
        L_0x0032:
            com.samsung.android.sdk.mobileservice.profile.ProfileApi$ProfileBirthdayType r2 = com.samsung.android.sdk.mobileservice.profile.ProfileApi.ProfileBirthdayType.LEAP_BIRTHDAY
            return r2
        L_0x0035:
            com.samsung.android.sdk.mobileservice.profile.ProfileApi$ProfileBirthdayType r2 = com.samsung.android.sdk.mobileservice.profile.ProfileApi.ProfileBirthdayType.LUNAR_BIRTHDAY
            return r2
        L_0x0038:
            com.samsung.android.sdk.mobileservice.profile.ProfileApi$ProfileBirthdayType r2 = com.samsung.android.sdk.mobileservice.profile.ProfileApi.ProfileBirthdayType.SOLAR_BIRTHDAY
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.mobileservice.profile.result.ProfileBirthdayTypeResult.convertStringToProfileBirthdayType(java.lang.String):com.samsung.android.sdk.mobileservice.profile.ProfileApi$ProfileBirthdayType");
    }

    public ProfileApi.ProfileBirthdayType getResult() {
        return this.mProfileBirthdayType;
    }

    public CommonResultStatus getStatus() {
        return this.mStatus;
    }
}
