package com.samsung.android.sdk.mobileservice.social.social;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ContactInfo {
    private final String hash;
    private final String phoneNumber;
    private final long rawContactId;
    private final int result;

    public ContactInfo(int i2, long j2, String str, String str2) {
        this.result = i2;
        this.rawContactId = j2;
        this.phoneNumber = str;
        this.hash = str2;
    }

    public String getHash() {
        return this.hash;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public long getRawContactId() {
        return this.rawContactId;
    }

    public int getResult() {
        return this.result;
    }
}
