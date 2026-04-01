package com.samsung.android.gallery.module.story.smartrect;

import N2.j;
import android.text.TextUtils;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DeviceRatio {
    private ArrayList<Float> mDeviceRatio;

    private synchronized ArrayList<Float> computeIfAbsent(FileItemInterface fileItemInterface) {
        try {
            ArrayList<Float> arrayList = this.mDeviceRatio;
            if (arrayList != null) {
                if (arrayList.isEmpty()) {
                }
            }
            this.mDeviceRatio = parse(MediaItemStory.getTotalSmartCropDeviceRatio(fileItemInterface));
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
        return this.mDeviceRatio;
    }

    private ArrayList<Float> parse(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return new ArrayList<>();
            }
            String[] split = str.split(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            ArrayList<Float> arrayList = new ArrayList<>();
            if (split.length >= 10) {
                arrayList.add(Float.valueOf(Float.parseFloat(split[0]) / Float.parseFloat(split[1])));
                arrayList.add(Float.valueOf(Float.parseFloat(split[1]) / Float.parseFloat(split[0])));
                Log.d("DeviceRatio", "parseDeviceRatio main = ".concat(str));
            }
            if (split.length >= 20) {
                arrayList.add(Float.valueOf(Float.parseFloat(split[10]) / Float.parseFloat(split[11])));
                arrayList.add(Float.valueOf(Float.parseFloat(split[11]) / Float.parseFloat(split[10])));
                Log.d("DeviceRatio", "parseDeviceRatio sub = ".concat(str));
            }
            return arrayList;
        } catch (Exception e) {
            StringBuilder k = j.k("parseDeviceRatio fail = ", str, GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            k.append(e.getMessage());
            Log.e("DeviceRatio", k.toString());
            return new ArrayList<>();
        }
    }

    public ArrayList<Float> getDeviceRatio(FileItemInterface fileItemInterface) {
        return computeIfAbsent(fileItemInterface);
    }
}
