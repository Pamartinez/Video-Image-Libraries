package com.samsung.android.gallery.database.dal.cmh.table;

import com.samsung.android.gallery.support.config.SdkConfig;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class CmhStoryColumns {
    public static String getStoryContentId() {
        if (SdkConfig.atLeast(SdkConfig.GED.Q)) {
            return "sec_media_id";
        }
        if (SdkConfig.atLeast(SdkConfig.GED.P)) {
            return "media_id";
        }
        return "fileid";
    }
}
