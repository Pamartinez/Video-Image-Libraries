package com.samsung.android.gallery.support.blackboard.key;

import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.util.HashMap;
import java.util.StringJoiner;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class DataKey {
    private static final HashMap<String, String> sDataCursorKeyCache = new HashMap<>();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum ShrinkType {
        NONE,
        DRAG,
        DRAG_CAMERA,
        PINCH,
        PINCH_CAMERA,
        BACK_PRESSED,
        BACK_PRESSED_CAMERA,
        HIGHLIGHT,
        HIGHLIGHT_JUMP
    }

    public static String CACHED_DATA_CURSOR(String str) {
        return ArgumentsUtil.appendUriKey(str.replace("location://", "data://"), "/CachedDataCursor", false);
    }

    public static String DATA(String str) {
        return str.replace("location://", "data://");
    }

    public static String DATA_CURSOR(String str) {
        HashMap<String, String> hashMap = sDataCursorKeyCache;
        String orDefault = hashMap.getOrDefault(str, (Object) null);
        if (orDefault != null) {
            return orDefault;
        }
        String appendUriKey = ArgumentsUtil.appendUriKey(str.replace("location://", "data://"), "/DataCursor", false);
        hashMap.put(str, appendUriKey);
        return appendUriKey;
    }

    public static String getQuickViewDataKey(int[] iArr) {
        StringJoiner stringJoiner = new StringJoiner(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        for (int valueOf : iArr) {
            stringJoiner.add(String.valueOf(valueOf));
        }
        return new UriBuilder("location://quickView/fileList").appendArg("ids", stringJoiner.toString()).appendArg("disableTimeline", true).appendArg("disableRealRatio", true).appendArg("fromNow", true).appendArg("quick_view", true).build();
    }

    public static String getQuickViewDataKeyLegacy() {
        return new UriBuilder("location://quickView/fileList").appendArg("disableTimeline", true).appendArg("disableRealRatio", true).appendArg("fromNow", true).appendArg("quick_view", true).build();
    }

    public static String getSlideshowDataKey(String str) {
        return getViewerDataKey(str).replace("/slideshow", "");
    }

    public static String getStorySlideshowDataKey(String str) {
        return str.replace("/spotify", "/fileList");
    }

    public static String getViewerDataKey(String str) {
        return str.replace("/viewer", "");
    }
}
