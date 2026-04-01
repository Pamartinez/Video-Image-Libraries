package com.samsung.android.sdk.mobileservice.auth;

import java.util.HashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DisclaimerAgreementInfo {
    public static final String ACCOUNT = "agreement_samsung_account";
    public static final String SOCIAL = "agreement_social_service";
    private HashMap<String, Boolean> mInfo = new HashMap<>();

    public void add(String str, boolean z) {
        this.mInfo.put(str, Boolean.valueOf(z));
    }

    public boolean contain(String str) {
        return this.mInfo.containsKey(str);
    }

    public boolean getValue(String str, boolean z) {
        if (this.mInfo.containsKey(str)) {
            return this.mInfo.get(str).booleanValue();
        }
        return z;
    }
}
