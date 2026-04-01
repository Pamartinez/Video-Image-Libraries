package com.samsung.android.gallery.module.data;

import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.module.dynamicview.DynamicViewInfo;
import com.samsung.android.gallery.module.dynamicview.DynamicViewPlayInfo;
import com.samsung.android.gallery.support.utils.Copyable;
import com.samsung.android.gallery.support.utils.Log;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DynamicViewData implements Copyable<DynamicViewData> {
    private static final String TAG = "DynamicViewData";
    static final Function<String, Object> constructor = new j(3);
    public byte[] dynamicViewBlob;
    public DynamicViewInfo dynamicViewInfo;
    public DynamicViewPlayInfo dynamicViewPlayInfo;

    public static boolean hasPlayInfo(FileItemInterface fileItemInterface) {
        if (!DynamicViewPlayInfo.SUPPORT || fileItemInterface == null || of(fileItemInterface).dynamicViewPlayInfo == null) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Object lambda$static$0(String str) {
        return new DynamicViewData();
    }

    public static DynamicViewData of(FileItemInterface fileItemInterface) {
        return (DynamicViewData) fileItemInterface.tags().computeIfAbsent(TAG, constructor);
    }

    public DynamicViewData copyOf() {
        try {
            return (DynamicViewData) super.clone();
        } catch (CloneNotSupportedException e) {
            Log.e((CharSequence) TAG, "clone failed", (Throwable) e);
            return this;
        }
    }
}
