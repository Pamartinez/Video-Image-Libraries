package com.samsung.android.sdk.mobileservice.social.feedback;

import android.os.Bundle;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class ContentId {
    public static final int TYPE_ACTIVITY = 1;
    private int mContentIdType;

    public ContentId(int i2) {
        this.mContentIdType = i2;
    }

    public int getContentIdType() {
        return this.mContentIdType;
    }

    public abstract Bundle toBundle();
}
