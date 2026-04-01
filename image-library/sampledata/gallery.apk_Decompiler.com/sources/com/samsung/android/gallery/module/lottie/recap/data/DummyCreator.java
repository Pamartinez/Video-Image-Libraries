package com.samsung.android.gallery.module.lottie.recap.data;

import android.content.ClipData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.SafeClipboard;
import com.samsung.android.gallery.support.utils.SecureFile;
import com.samsung.android.gallery.support.utils.StorageInfo;
import i.C0212a;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class DummyCreator {
    static String DUMMY_JSON = "{\n    \"type\": 999,\n    \"contents\": [\n        {\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},        {\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},        {\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},        {\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},{\"contents\": __FILE_ID__},    ],\n    \"faceContents\": [\n        {\n            \"contents\": __FILE_ID__,\n            \"name\": \"100008\"\n        },\n        {\n            \"contents\": __FILE_ID__,\n            \"name\": \"100037\"\n        },\n        {\n            \"contents\": __FILE_ID__,\n            \"isNew\": true,\n            \"name\": \"100018\"\n        },\n        {\n            \"contents\": __FILE_ID__,\n            \"name\": \"100039\"\n        }\n    ],\"locationContents\": [\n        {\n            \"contents\": __FILE_ID__,\n            \"city\": \"수원시\",\n            \"uniqueDays\": 118\n        },\n        {\n            \"contents\": __FILE_ID__,\n            \"city\": \"Honolulu\",\n            \"uniqueDays\": 5,\n            \"isNew\": true\n        },\n        {\n            \"contents\": __FILE_ID__,\n            \"city\": \"Waimea\",\n            \"uniqueDays\": 3,\n            \"isNew\": true\n        },\n        {\n            \"contents\": __FILE_ID__,\n            \"city\": \"하남시\",\n            \"uniqueDays\": 3\n        }\n    ],\n\"totalImages\": 5877,\n    \"totalVideos\": 178,\n    \"totalFaces\": 36,\n    \"totalLocations\": 88,\n    \"totalDays\": 210,\n    \"mostActiveDay\": 1751328000000,\n    \"mostActiveDayCount\": 346,\n    \"mostActiveDayContents\": [\n        {\n            \"contents\": __FILE_ID__,\n            \"value\": \"Grand Canyon Village\"\n        },\n        {\n            \"contents\": __FILE_ID__,\n            \"value\": \"Grand Canyon Village\"\n        },\n        {\n            \"contents\": __FILE_ID__,\n            \"value\": \"Grand Canyon Village\"\n        }\n    ],\n    \"newLocations\": 68\n}\n";
    public static CharSequence[] TYPE_NAMES = {"QuarterBrief", "YearBrief", "YearMoments", "YearPlace", "YearPeople", "MonthlyBrief", "MonthlyMoments", "StoryMoments"};
    public static int[] TYPE_VALUES = {3286, 3287, 3288, 3292, 3289, 3294, 3295, 90001};

    public static String create(ArrayList<MediaItem> arrayList) {
        String str = DUMMY_JSON;
        ArrayList arrayList2 = new ArrayList();
        if (arrayList.size() > 99) {
            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                MediaItem mediaItem = arrayList.get(i2);
                if (arrayList2.isEmpty()) {
                    arrayList2.add(mediaItem);
                } else if (Math.abs(((MediaItem) C0212a.i(arrayList2, 1)).getDateLocal() - mediaItem.getDateLocal()) > 60000) {
                    arrayList2.add(mediaItem);
                }
            }
        } else {
            arrayList2.addAll(arrayList);
        }
        Collections.shuffle(arrayList2, new Random(System.currentTimeMillis()));
        try {
            return new DummyAnalyzer().createDataJson(arrayList2);
        } catch (JSONException unused) {
            int i7 = 0;
            while (i7 < arrayList2.size()) {
                MediaItem mediaItem2 = (MediaItem) arrayList2.get(i7);
                if (!mediaItem2.isGif()) {
                    str = str.replaceFirst("__FILE_ID__", mediaItem2.getFileId() + "");
                    if (i7 == arrayList2.size() - 1 && str.contains("__FILE_ID__")) {
                        i7 = 0;
                    }
                }
                i7++;
            }
            return str;
        }
    }

    public static String loadFromClipboard() {
        String str;
        ClipData primaryClip = new SafeClipboard(AppResources.getAppContext()).getPrimaryClip();
        if (primaryClip == null || primaryClip.getItemCount() <= 0 || (str = (String) primaryClip.getItemAt(0).getText()) == null || !str.contains("type") || !str.contains("contents")) {
            return null;
        }
        try {
            FileUtils.writeFile((File) new SecureFile(StorageInfo.getDefault().getMediaPath("") + "/" + ("recap_soda" + new JSONObject(str).optInt("type") + ".json")), str.getBytes(StandardCharsets.UTF_8));
            return str;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
