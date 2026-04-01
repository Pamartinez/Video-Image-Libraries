package com.samsung.android.gallery.app.controller.internals;

import c0.C0086a;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.BucketUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sdk.pen.ocr.SpenRecogConfig;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class DeleteBaseCmd extends BaseCommand {
    public String getEventIdOfCancel(boolean z) {
        return null;
    }

    public String getEventIdOfDelete(boolean z) {
        return null;
    }

    public final int getLastOption(ArrayList<Object> arrayList) {
        if (arrayList != null) {
            try {
                if (!arrayList.isEmpty()) {
                    return ((Integer) arrayList.get(arrayList.size() - 1)).intValue();
                }
            } catch (ClassCastException e) {
                String str = this.TAG;
                Log.e(str, "getLastOption failed. e=" + e.getMessage());
            }
        }
        return -1;
    }

    public boolean isVirtualAlbum(String str) {
        String argValue;
        if (str == null) {
            return false;
        }
        if (str.startsWith("location://search/fileList/Category/Location")) {
            return SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_TRUE.equals(ArgumentsUtil.getArgValue(str, "fromVirtualAlbum"));
        }
        if (!str.startsWith("location://albums/fileList") || (argValue = ArgumentsUtil.getArgValue(str, "id")) == null || !BucketUtils.isVirtualAlbum(Integer.valueOf(argValue).intValue())) {
            return str.startsWith("location://virtual/album");
        }
        return true;
    }

    public void onConfirmed(EventContext eventContext, ArrayList<Object> arrayList) {
        Object obj;
        int lastOption = getLastOption(arrayList);
        if (lastOption == 1) {
            Log.d(this.TAG, "confirmed {1}");
            operateDelete();
            return;
        }
        String str = this.TAG;
        StringBuilder o2 = C0086a.o(lastOption, "confirmed skip {", GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        if (arrayList != null) {
            obj = Integer.valueOf(arrayList.size());
        } else {
            obj = "null";
        }
        o2.append(obj);
        o2.append("}");
        Log.w(str, o2.toString());
    }

    public abstract void operateDelete();
}
