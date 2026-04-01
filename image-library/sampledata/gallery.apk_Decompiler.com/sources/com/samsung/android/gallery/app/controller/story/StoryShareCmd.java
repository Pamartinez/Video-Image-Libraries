package com.samsung.android.gallery.app.controller.story;

import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.o3dp.lib3dphotography.common.O3DPConstant;
import java.io.File;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StoryShareCmd extends StorySaveCmd {
    public String getEventId() {
        return AnalyticsEventId.EVENT_MENU_SHARE_COVER_STORY.toString();
    }

    public String getResultFilePath(MediaItem mediaItem, boolean z, boolean z3) {
        String str;
        File externalFilesDir = getContext().getExternalFilesDir(".share");
        if (externalFilesDir == null || !externalFilesDir.exists()) {
            return null;
        }
        String str2 = MediaItemStory.getStoryId(mediaItem) + "_" + (z3 ? 1 : 0) + "_" + mediaItem.getPath() + "_" + mediaItem.getDateModified() + "_" + ((String) Optional.ofNullable(MediaItemStory.getStoryCollagePath(mediaItem)).orElse("_")) + "_" + MediaItemStory.getStoryCoverRectRatio(mediaItem);
        StringBuilder sb2 = new StringBuilder();
        sb2.append(externalFilesDir.getAbsolutePath());
        sb2.append(File.separator);
        sb2.append(str2.hashCode());
        if (z) {
            str = O3DPConstant.MP4_EXTENSION;
        } else {
            str = ".jpg";
        }
        sb2.append(str);
        return sb2.toString();
    }

    public String getServiceName() {
        return "com.samsung.android.gallery.app.service.StoryShareService";
    }

    public boolean isWithShare() {
        return true;
    }
}
