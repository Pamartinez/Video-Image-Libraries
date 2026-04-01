package com.samsung.android.sdk.mobileservice.social.activity;

import android.net.Uri;

@Deprecated
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ActivityImage {
    private String mActivityId;
    private Uri mActivityImageContentUri;
    private String mGuid;
    private Uri mProfileImageContentUri;

    public ActivityImage(String str, String str2, Uri uri, Uri uri2) {
        this.mGuid = str;
        this.mActivityId = str2;
        this.mActivityImageContentUri = uri2;
        this.mProfileImageContentUri = uri;
    }

    public String getActivityId() {
        return this.mActivityId;
    }

    public Uri getActivityImageUri() {
        return this.mActivityImageContentUri;
    }

    public String getGuid() {
        return this.mGuid;
    }

    public Uri getProfileImageUri() {
        return this.mProfileImageContentUri;
    }
}
