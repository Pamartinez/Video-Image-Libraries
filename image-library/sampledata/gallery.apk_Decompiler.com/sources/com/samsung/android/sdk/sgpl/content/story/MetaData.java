package com.samsung.android.sdk.sgpl.content.story;

import android.text.TextUtils;
import c0.C0086a;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MetaData {
    private static final String DELIMITER = ";";
    static final String KEY_BGM = "story_bgm";
    static final String KEY_CONTENT_URIS = "story_uris";
    static final String KEY_COVER = "story_cover";
    static final String KEY_DISPLAY_ORDER = "story_display_order";
    static final String KEY_FILTER = "story_filter";
    static final String KEY_THEME = "story_theme";
    static final String KEY_TITLE = "story_title";
    static final String KEY_TOTAL_SMART_CROP_DIViCE_LIST = "total_smartcrop_device_ratio";
    static final String KEY_TOTAL_SMART_CROP_LIST = "total_smartcrop_ratio";
    String bgm;
    String coverUri;
    List<String> displayOrders;
    String filter;
    List<String> sendUris;
    List<String> smartCropDeviceRatioList;
    List<String> smartCropRatioList;
    String theme;
    String title;

    public static MetaData from(String str) {
        MetaData metaData = new MetaData();
        HashMap hashMap = (HashMap) fromJsonString(HashMap.class, str);
        metaData.coverUri = (String) hashMap.getOrDefault(KEY_COVER, "");
        metaData.title = (String) hashMap.getOrDefault(KEY_TITLE, "");
        metaData.theme = (String) hashMap.getOrDefault(KEY_THEME, "");
        metaData.filter = (String) hashMap.getOrDefault(KEY_FILTER, "");
        metaData.bgm = (String) hashMap.getOrDefault(KEY_BGM, "");
        metaData.sendUris = parseString((String) hashMap.getOrDefault(KEY_CONTENT_URIS, ""));
        metaData.displayOrders = parseString((String) hashMap.getOrDefault(KEY_DISPLAY_ORDER, ""));
        metaData.smartCropRatioList = parseString((String) hashMap.getOrDefault(KEY_TOTAL_SMART_CROP_LIST, ""));
        metaData.smartCropDeviceRatioList = parseString((String) hashMap.getOrDefault(KEY_TOTAL_SMART_CROP_DIViCE_LIST, ""));
        return metaData;
    }

    private static <T> T fromJsonString(Class<T> cls, String str) {
        return new GsonBuilder().serializeNulls().setPrettyPrinting().create().fromJson(str, cls);
    }

    private static List<String> parseString(String str) {
        if (!TextUtils.isEmpty(str)) {
            return (List) Arrays.stream(str.split(DELIMITER)).collect(Collectors.toList());
        }
        return new ArrayList();
    }

    public String toString() {
        int i2;
        int i7;
        int i8;
        StringBuilder sb2 = new StringBuilder("MetaData@");
        sb2.append(Integer.toHexString(hashCode()));
        sb2.append(" cover:");
        sb2.append(this.coverUri);
        sb2.append(" ,title:");
        sb2.append(this.title);
        sb2.append(" ,theme:");
        sb2.append(this.theme);
        sb2.append(" ,filter:");
        sb2.append(this.filter);
        sb2.append(" ,bgm:");
        sb2.append(this.bgm);
        sb2.append(" ,sendUris[");
        List<String> list = this.sendUris;
        int i10 = -1;
        if (list != null) {
            i2 = list.size();
        } else {
            i2 = -1;
        }
        sb2.append(i2);
        sb2.append("] ,order[");
        List<String> list2 = this.displayOrders;
        if (list2 != null) {
            i7 = list2.size();
        } else {
            i7 = -1;
        }
        sb2.append(i7);
        sb2.append("] ,smartCropRatio[");
        List<String> list3 = this.smartCropRatioList;
        if (list3 != null) {
            i8 = list3.size();
        } else {
            i8 = -1;
        }
        sb2.append(i8);
        sb2.append("] ,smartCropDeviceRatio[");
        List<String> list4 = this.smartCropDeviceRatioList;
        if (list4 != null) {
            i10 = list4.size();
        }
        return C0086a.l(sb2, i10, "]");
    }
}
