package com.samsung.android.sdk.mobileservice.social.share.result;

import android.net.Uri;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class OriginalFile {
    private final Uri mContentUri;
    private final String mFilePath;
    private final String mHash;

    public OriginalFile(String str, String str2, Uri uri) {
        this.mHash = str;
        this.mFilePath = str2;
        this.mContentUri = uri;
    }

    public Uri getContentUri() {
        return this.mContentUri;
    }

    public String getFilePath() {
        return this.mFilePath;
    }

    public String getHash() {
        return this.mHash;
    }
}
