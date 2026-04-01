package com.samsung.android.sdk.mobileservice.profile;

import android.os.Bundle;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Privacy {
    public static final String KEY_BIRTHDAYS = "birthdays";
    public static final String KEY_EMAIL_ADDRESSES = "emailAddresses";
    public static final String KEY_EVENTS = "events";
    public static final String KEY_GENDERS = "genders";
    public static final String KEY_HEALTHS = "healths";
    public static final String KEY_MESSENGER_ACCOUNTS = "messengerAccounts";
    public static final String KEY_NAMES = "names";
    public static final String KEY_NICKNAMES = "nicknames";
    public static final String KEY_NOTES = "notes";
    public static final String KEY_ORGANIZATIONS = "organizations";
    public static final String KEY_PHONE_NUMBERS = "phoneNumbers";
    public static final String KEY_PHOTOS = "photos";
    public static final String KEY_PLACES = "places";
    public static final String KEY_STATUS_MESSAGE = "statusMessages";
    public static final String KEY_WEB_ADDRESSES = "webAddresses";
    public static final int TYPE_CONTACT_OR_GROUP = 2;
    public static final int TYPE_EVERYONE = 0;
    public static final int TYPE_INVALID = -1;
    public static final int TYPE_SELF = 1;
    private Bundle mPrivacyBundle = new Bundle();

    public Privacy(Bundle bundle) {
        if (bundle != null) {
            this.mPrivacyBundle = bundle;
        }
    }

    public boolean contains(String str) {
        if (this.mPrivacyBundle.containsKey(str)) {
            return true;
        }
        return false;
    }

    public int get(String str) {
        if (!contains(str)) {
            return -1;
        }
        return this.mPrivacyBundle.getInt(str);
    }

    public Bundle read() {
        return this.mPrivacyBundle;
    }

    public void set(String str, int i2) {
        Bundle bundle = this.mPrivacyBundle;
        if (bundle != null) {
            bundle.putInt(str, i2);
        }
    }
}
