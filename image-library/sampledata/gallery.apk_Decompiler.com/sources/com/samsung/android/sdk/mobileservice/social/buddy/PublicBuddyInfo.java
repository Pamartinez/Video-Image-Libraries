package com.samsung.android.sdk.mobileservice.social.buddy;

import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PublicBuddyInfo {
    private ArrayList<Capability> mCapabilityList;
    private byte[] mImage;
    private String mImageType;
    private String mName;
    private String mPhoneNumber;
    private String mStatusMessage;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Capability {
        private String mAppId;
        private String mServiceId;

        public Capability(String str, String str2) {
            this.mAppId = str;
            this.mServiceId = str2;
        }

        public String getAppId() {
            return this.mAppId;
        }

        public String getServiceId() {
            return this.mServiceId;
        }
    }

    public PublicBuddyInfo(String str, String str2, String str3, byte[] bArr, String str4, ArrayList<Capability> arrayList) {
        this.mPhoneNumber = str;
        this.mName = str2;
        this.mStatusMessage = str3;
        this.mImage = bArr;
        this.mImageType = str4;
        this.mCapabilityList = arrayList;
    }

    public ArrayList<Capability> getCapabilityList() {
        return this.mCapabilityList;
    }

    public byte[] getImage() {
        return this.mImage;
    }

    public String getImageType() {
        return this.mImageType;
    }

    public String getName() {
        return this.mName;
    }

    public String getPhoneNumber() {
        return this.mPhoneNumber;
    }

    public String getStatusMessage() {
        return this.mStatusMessage;
    }
}
