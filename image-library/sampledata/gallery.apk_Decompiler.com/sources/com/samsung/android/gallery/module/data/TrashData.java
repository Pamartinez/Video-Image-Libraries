package com.samsung.android.gallery.module.data;

import com.samsung.android.gallery.database.dbtype.ExtrasID;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.support.utils.Copyable;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class TrashData implements Copyable<TrashData> {
    private static final String TAG = "TrashData";
    static final Function<String, Object> constructor = new j(11);
    public long deleteTime;
    public boolean drm;
    public int expiredPeriod;
    public long expiryDate;
    public long mediaId;
    public String originalTitle;
    public String restoreData;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class FeatureHolder {
        static final boolean FAMILY_SHARED_TRASH = Features.isEnabled(Features.SUPPORT_FAMILY_SHARED_TRASH);
    }

    public static String getOriginalPath(FileItemInterface fileItemInterface) {
        if (fileItemInterface == null) {
            return null;
        }
        return (String) fileItemInterface.getExtra(ExtrasID.ORIGIN_PATH);
    }

    public static boolean isTrash(FileItemInterface fileItemInterface) {
        if (fileItemInterface == null) {
            return false;
        }
        if (fileItemInterface.isTrash()) {
            return true;
        }
        TrashData of2 = of(fileItemInterface);
        if (of2.restoreData != null || of2.deleteTime > 0 || (FeatureHolder.FAMILY_SHARED_TRASH && fileItemInterface.isSharing() && of2.expiryDate > 0)) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Object lambda$static$0(String str) {
        return new TrashData();
    }

    public static TrashData of(FileItemInterface fileItemInterface) {
        return (TrashData) fileItemInterface.tags().computeIfAbsent(TAG, constructor);
    }

    public TrashData copyOf() {
        try {
            return (TrashData) super.clone();
        } catch (CloneNotSupportedException e) {
            Log.e((CharSequence) TAG, "clone failed", (Throwable) e);
            return this;
        }
    }
}
