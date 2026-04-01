package com.samsung.android.gallery.support.analytics;

import com.samsung.android.sdk.moneta.lifestyle.entertainment.entity.MediaType;
import com.samsung.android.sdk.moneta.memory.entity.content.KeywordInfo;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum AnalyticsDetailKey {
    DEFAULT("det"),
    ITEM_COUNT("ItemCount"),
    ALBUM_COUNT("AlbumCount"),
    GROUP_COUNT("GroupCount"),
    IMAGE_COUNT("ImageCount"),
    VIDEO_COUNT("VideoCount"),
    MEDIA_TYPE(MediaType.TAG),
    SEF_TYPE("SefType"),
    SEF_SUB_TYPE("SefSubType"),
    SEF_TYPES("SefTypes"),
    RECORDING_MODE("RecordingMode"),
    RESOLUTION("Resolution"),
    ALBUM("Album"),
    REMASTER_TYPE("Remaster_type"),
    STORAGE("Storage"),
    REASON(KeywordInfo.EXTRA_BUNDLE_KEY_REASON),
    EXTENSION("extension"),
    USB_NUM("usb_num");
    
    private final String value;

    private AnalyticsDetailKey(String str) {
        this.value = str;
    }

    public String toString() {
        return this.value;
    }
}
