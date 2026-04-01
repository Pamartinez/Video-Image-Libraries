package com.samsung.android.gallery.support.blackboard.key;

import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.sdk.pen.ocr.SpenRecogConfig;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class CommandKey {
    public static String DATA_REQUEST(String str) {
        return replaceHeader(str, "command://DataRequest/");
    }

    public static String DATA_REQUEST_CANCEL(String str) {
        return replaceHeader(str, "command://DataRequestCancel/");
    }

    public static String DATA_REQUEST_REVERT(String str) {
        return str.replaceFirst("command://DataRequest/", "data://");
    }

    public static String DATA_REQUEST_TO_LOCATION(String str) {
        return str.replaceFirst("command://DataRequest/", "location://");
    }

    public static String DATA_REQUEST_URGENT(String str) {
        return ArgumentsUtil.appendArgs(replaceHeader(str, "command://DataRequest/"), "bbArg_NotifyDirect", SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_TRUE);
    }

    public static String SEGMENT(String str) {
        return str.substring(22);
    }

    private static String replaceHeader(String str, String str2) {
        int indexOf = str.indexOf("://");
        if (indexOf == -1) {
            return str;
        }
        StringBuilder sb2 = new StringBuilder(str.length());
        sb2.append(str2);
        sb2.append(str, indexOf + 3, str.length());
        return sb2.toString();
    }
}
