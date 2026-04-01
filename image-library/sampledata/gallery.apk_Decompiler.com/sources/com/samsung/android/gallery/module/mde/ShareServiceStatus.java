package com.samsung.android.gallery.module.mde;

import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.GalleryPreference;
import com.samsung.android.gallery.support.utils.PreferenceName;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
abstract class ShareServiceStatus {
    private static Integer sServiceStatus;

    /* JADX WARNING: type inference failed for: r2v0, types: [java.lang.Object, java.util.function.IntSupplier] */
    public static int get() {
        if (sServiceStatus == null) {
            sServiceStatus = Integer.valueOf(GalleryPreference.getInstance().computeIntIfAbsent(PreferenceName.SHARE_SERVICE_STATUS.key(), new Object()));
        }
        return sServiceStatus.intValue();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ int lambda$get$0() {
        return Features.isEnabled(Features.SUPPORT_SHARING_SERVICE) ? 1 : 0;
    }

    public static int update(boolean z) {
        if (get() != z) {
            sServiceStatus = Integer.valueOf(z);
            GalleryPreference.getInstance().saveState(PreferenceName.SHARE_SERVICE_STATUS.key(), (int) z);
        }
        return z ? 1 : 0;
    }
}
