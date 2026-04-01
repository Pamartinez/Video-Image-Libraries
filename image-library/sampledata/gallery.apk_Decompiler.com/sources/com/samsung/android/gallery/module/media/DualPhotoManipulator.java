package com.samsung.android.gallery.module.media;

import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.library.sef.SefInfo;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.StringCompat;
import i.C0212a;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DualPhotoManipulator {
    private boolean isWide;
    private final HashMap<String, byte[]> mDataMap = new HashMap<>();
    private final HashMap<String, Integer> mDataTypeMap = new HashMap<>();
    private String mFilePath;

    private String getKeyName(String str) {
        if (SefInfo.DUAL_SHOT_IMAGE_LIVE_FOCUS.equals(str)) {
            return C0212a.A(str, "(LiveFocus)");
        }
        if (SefInfo.DUAL_SHOT_IMAGE_WIDE.equals(str)) {
            return C0212a.A(str, "(Wide)");
        }
        if (SefInfo.DUAL_SHOT_IMAGE_CLOSEUP.equals(str)) {
            return C0212a.A(str, "(CloseUp)");
        }
        return str;
    }

    public boolean hasCloseUp() {
        return this.mDataMap.containsKey(SefInfo.DUAL_SHOT_IMAGE_CLOSEUP.keyName);
    }

    public boolean hasLiveFocus() {
        return this.mDataMap.containsKey(SefInfo.DUAL_SHOT_IMAGE_LIVE_FOCUS.keyName);
    }

    public boolean hasWide() {
        return this.mDataMap.containsKey(SefInfo.DUAL_SHOT_IMAGE_WIDE.keyName);
    }

    public boolean init(String str) {
        boolean z;
        char c5;
        char c6;
        long currentTimeMillis = System.currentTimeMillis();
        this.mFilePath = str;
        File file = new File(str);
        try {
            String[] keyNameArray = SeApiCompat.getSefFileCompat().getKeyNameArray(file);
            if (keyNameArray != null) {
                if (keyNameArray.length != 0) {
                    for (String str2 : keyNameArray) {
                        this.mDataMap.put(str2, SeApiCompat.getSefFileCompat().getData(file, str2));
                        this.mDataTypeMap.put(str2, Integer.valueOf(SeApiCompat.getSefFileCompat().getDataType(file, str2)));
                    }
                    if (this.mDataMap.get(SefInfo.DUAL_SHOT_IMAGE_LIVE_FOCUS.keyName) != null) {
                        z = true;
                    } else {
                        z = false;
                    }
                    this.isWide = z;
                    StringBuilder sb2 = new StringBuilder("init done {");
                    char c8 = "";
                    if (hasLiveFocus()) {
                        c5 = 'L';
                    } else {
                        c5 = c8;
                    }
                    sb2.append(c5);
                    if (hasCloseUp()) {
                        c6 = 'C';
                    } else {
                        c6 = c8;
                    }
                    sb2.append(c6);
                    if (hasWide()) {
                        c8 = 'W';
                    }
                    sb2.append(c8);
                    sb2.append("} +");
                    sb2.append(System.currentTimeMillis() - currentTimeMillis);
                    Log.d("DualPhotoManipulator", sb2.toString());
                    return true;
                }
            }
            Log.e("DualPhotoManipulator", "fail get keyNames : " + str);
            return false;
        } catch (IOException e) {
            Log.e("DualPhotoManipulator", e.toString());
            return false;
        }
    }

    public String toString() {
        String str;
        StringBuilder sb2 = new StringBuilder();
        for (Map.Entry next : this.mDataMap.entrySet()) {
            sb2.append("   ");
            sb2.append(getKeyName((String) next.getKey()));
            sb2.append(":={");
            sb2.append(StringCompat.valueOf((byte[]) next.getValue(), 8));
            sb2.append("}");
            sb2.append(System.lineSeparator());
        }
        StringBuilder sb3 = new StringBuilder("DualPhoto{");
        if (this.isWide) {
            str = "wide";
        } else {
            str = "close-up";
        }
        sb3.append(str);
        sb3.append(",\n");
        sb3.append(sb2);
        sb3.append('}');
        return sb3.toString();
    }
}
