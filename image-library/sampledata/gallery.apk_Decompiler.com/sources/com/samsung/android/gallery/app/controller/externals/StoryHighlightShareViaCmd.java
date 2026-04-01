package com.samsung.android.gallery.app.controller.externals;

import N3.c;
import android.content.Intent;
import android.text.TextUtils;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.data.ContentUri;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.support.utils.GsonTool;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import java.util.HashMap;
import java.util.Optional;
import java.util.StringJoiner;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StoryHighlightShareViaCmd extends ShareViaCmd {
    private String getCoverUriString(EventContext eventContext) {
        MediaItem headerItem = eventContext.getHeaderItem();
        if (headerItem == null || MediaItemStory.getStoryCoverId(headerItem) <= 0) {
            return "";
        }
        return ContentUri.getUriString(headerItem);
    }

    private String getOrDefault(String str, String str2) {
        if (!TextUtils.isEmpty(str)) {
            return str;
        }
        return str2;
    }

    private String getTitle(EventContext eventContext) {
        MediaItem headerItem = eventContext.getHeaderItem();
        if (headerItem != null) {
            return headerItem.getTitle();
        }
        return "";
    }

    /* access modifiers changed from: private */
    /* renamed from: loadQuickShareExtra */
    public void lambda$loadAdditionalExtraData$0(EventContext eventContext, Intent intent) {
        intent.putExtra("share-version", 1);
        intent.putExtra("share-from", "gallery");
        intent.putExtra("share-category", "story");
        intent.putExtra("story_title", getTitle(eventContext));
        intent.putExtra("story_extra", getExtraJsonString(eventContext));
        printLogMetaData(intent);
    }

    private void printLogMetaData(Intent intent) {
        Log.d(this.TAG, "[TO QS] story_title", intent.getStringExtra("story_title"));
        Log.d(this.TAG, "[TO QS] story_extra", Logger.getEncodedString(intent.getStringExtra("story_extra")));
    }

    public String getExtraJsonString(EventContext eventContext) {
        StringJoiner stringJoiner = new StringJoiner(";");
        StringJoiner stringJoiner2 = new StringJoiner(";");
        StringJoiner stringJoiner3 = new StringJoiner(";");
        StringJoiner stringJoiner4 = new StringJoiner(";");
        for (MediaItem mediaItem : eventContext.getAllItems()) {
            stringJoiner.add(ContentUri.getUriString(mediaItem));
            stringJoiner2.add(getOrDefault(MediaItemStory.getTotalSmartCropRatio(mediaItem), " "));
            stringJoiner3.add(getOrDefault(MediaItemStory.getTotalSmartCropDeviceRatio(mediaItem), " "));
            stringJoiner4.add(String.valueOf(MediaItemStory.getDisplayOrder(mediaItem)));
        }
        HashMap hashMap = new HashMap();
        hashMap.put("story_cover", getCoverUriString(eventContext));
        hashMap.put("story_theme", (String) getParameter("story_theme"));
        hashMap.put("story_filter", (String) getParameter("story_filter"));
        hashMap.put("story_bgm", (String) getParameter("story_bgm"));
        hashMap.put("story_title", getTitle(eventContext));
        hashMap.put("story_uris", stringJoiner.toString());
        hashMap.put("total_smartcrop_ratio", stringJoiner2.toString());
        hashMap.put("total_smartcrop_device_ratio", stringJoiner3.toString());
        hashMap.put("story_display_order", stringJoiner4.toString());
        return GsonTool.toJsonString(hashMap);
    }

    public void loadAdditionalExtraData(Intent intent) {
        Optional.ofNullable(getHandler()).ifPresent(new c(1, this, intent));
    }
}
