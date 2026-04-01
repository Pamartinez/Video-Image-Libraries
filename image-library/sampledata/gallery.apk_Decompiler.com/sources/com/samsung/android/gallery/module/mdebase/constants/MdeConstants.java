package com.samsung.android.gallery.module.mdebase.constants;

import android.net.Uri;
import c0.C0086a;
import com.samsung.android.sdk.mobileservice.social.group.provider.GroupContract;
import com.samsung.android.sdk.mobileservice.social.share.provider.SpaceContract;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class MdeConstants {
    public static final Uri GALLERY_SETTING_CONTENT_URI = Uri.parse("content://com.samsung.android.mobileservice.datasync.gallery_setting");
    public static final Uri GROUP_CONTENT_URI = GroupContract.Group.CONTENT_URI;
    public static final Uri INVITATION_CONTENT_URI = Uri.parse("content://com.samsung.android.mobileservice.social.group.invitation/");
    public static final Uri MDE_QUOTA_URI;
    public static final Uri SPACE_CONTENT_URI;

    static {
        Uri uri = SpaceContract.Space.CONTENT_URI;
        SPACE_CONTENT_URI = uri;
        MDE_QUOTA_URI = uri.buildUpon().appendPath("usage").build();
    }

    public static String getReqServiceName(int i2) {
        if (i2 == 1) {
            return "Auth";
        }
        if (i2 == 2) {
            return "Social";
        }
        return C0086a.i(i2, "ReqService#");
    }

    public static boolean isValidMember(int i2) {
        if (i2 == 2) {
            return true;
        }
        return false;
    }
}
