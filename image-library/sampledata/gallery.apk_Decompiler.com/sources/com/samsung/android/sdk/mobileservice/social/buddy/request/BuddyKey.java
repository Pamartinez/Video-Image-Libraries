package com.samsung.android.sdk.mobileservice.social.buddy.request;

import android.text.TextUtils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class BuddyKey {
    private String mKeyword;

    public BuddyKey(String str) {
        this.mKeyword = str;
    }

    public String getKeyword() {
        if (TextUtils.isEmpty(this.mKeyword)) {
            return "";
        }
        return this.mKeyword;
    }

    public abstract int getType();
}
