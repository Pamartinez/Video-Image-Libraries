package com.samsung.android.sdk.scs.ai.extension.lts;

import android.util.Log;
import java.util.ArrayList;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class TextChunkUtils {
    private static final String TAG = "TextChunkUtils";

    private TextChunkUtils() {
    }

    public static void printLogs(String str, List<String> list) {
        for (int i2 = 0; i2 < list.size(); i2++) {
            String str2 = list.get(i2);
            Log.i(TAG, str + " " + i2 + " (" + str2.length() + "): " + str2);
        }
    }

    public static List<String> splitWordsIntoChunks(String[] strArr, int i2) {
        ArrayList arrayList = new ArrayList();
        StringBuilder sb2 = new StringBuilder();
        for (String str : strArr) {
            if (str.length() + sb2.length() <= i2) {
                sb2.append(str);
                sb2.append(" ");
            } else {
                if (sb2.length() > 0) {
                    arrayList.add(sb2.toString().trim());
                    sb2.setLength(0);
                }
                sb2.append(str);
                sb2.append(" ");
            }
        }
        if (sb2.length() > 0) {
            arrayList.add(sb2.toString().trim());
        }
        return arrayList;
    }
}
