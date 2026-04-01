package com.samsung.android.sdk.mobileservice.social.activity;

import android.net.Uri;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ContentInfo {
    private Uri mContentUri;
    private String mHash;
    private String mMime;
    private String mName;
    private long mSize;

    public ContentInfo(String str, String str2, long j2, String str3, Uri uri) {
        this.mName = str;
        this.mHash = str2;
        this.mSize = j2;
        this.mMime = str3;
        this.mContentUri = uri;
    }

    public Uri getContentUri() {
        return this.mContentUri;
    }

    public String getHash() {
        return this.mHash;
    }

    public String getMime() {
        return this.mMime;
    }

    public String getName() {
        return this.mName;
    }

    public long getSize() {
        return this.mSize;
    }
}
