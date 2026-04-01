package com.samsung.android.gallery.support.exception;

import com.samsung.android.gallery.support.utils.GalleryPreference;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.RandomNumber;
import i.C0212a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class InternalException extends AssertionError {
    String mTag;

    public InternalException(String str) {
        super(str);
        this.mTag = C0212a.l("InternalException:", str);
    }

    public static boolean canPost(String str) {
        return GalleryPreference.getInstanceDebug().loadBoolean(str, true);
    }

    public void post() {
        post(1);
    }

    public void post(int i2) {
        Log.e("InternalException", this.mTag);
        if (canPost(this.mTag)) {
            GalleryPreference.getInstanceDebug().saveState(this.mTag, false);
            if (i2 == 1 || RandomNumber.nextInt(i2) == 1) {
                GalleryUncaughtExceptionHandler.getInstance().reportInternalException(this);
            }
        }
    }

    public InternalException(String str, String str2) {
        super(C0212a.B(str, " : ", str2));
        this.mTag = C0212a.l("InternalException:", str);
    }
}
