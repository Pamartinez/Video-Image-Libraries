package com.samsung.android.sdk.mobileservice.social.feedback;

import android.net.Uri;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class UserProfile {
    private String mGuid;
    private Uri mImageContentUri;
    private String mName;

    public UserProfile(String str, String str2, Uri uri) {
        this.mGuid = str;
        this.mName = str2;
        this.mImageContentUri = uri;
    }

    public String getGuid() {
        return this.mGuid;
    }

    public Uri getImageContentUri() {
        return this.mImageContentUri;
    }

    public String getName() {
        return this.mName;
    }

    public UserProfile(String str, Uri uri) {
        this.mGuid = str;
        this.mImageContentUri = uri;
    }
}
