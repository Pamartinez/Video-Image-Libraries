package com.samsung.android.sdk.mobileservice.social.buddy.result;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Buddy {
    private final String mBuddyId;
    private final Certificate mCertificate;
    private final SyncedContact mSyncedContact;

    public Buddy(String str, Certificate certificate, SyncedContact syncedContact) {
        this.mBuddyId = str;
        this.mCertificate = certificate;
        this.mSyncedContact = syncedContact;
    }

    public String getBuddyId() {
        return this.mBuddyId;
    }

    public Certificate getCertificate() {
        return this.mCertificate;
    }

    public SyncedContact getSyncedContact() {
        return this.mSyncedContact;
    }
}
