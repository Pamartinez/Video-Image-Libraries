package com.samsung.android.gallery.bixby.bixbycard;

import java.io.Serializable;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class GalleryCardData implements Serializable {
    private final String mContentUri;
    private final String mMimeType;
    private int mStoryId;
    private String mTitle;

    public GalleryCardData(String str, String str2) {
        this.mContentUri = str;
        this.mMimeType = str2;
    }

    public String getContentUri() {
        return this.mContentUri;
    }

    public String getMimeType() {
        return this.mMimeType;
    }

    public int getStoryId() {
        return this.mStoryId;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public void setStoryId(int i2) {
        this.mStoryId = i2;
    }

    public void setTitle(String str) {
        this.mTitle = str;
    }
}
