package com.samsung.android.gallery.support.exception;

import com.samsung.android.gallery.support.utils.GalleryPreference;
import com.samsung.android.gallery.support.utils.RandomNumber;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class InternalAnrBg extends InternalAnr {
    StackTraceElement[] mElements;

    public InternalAnrBg(Object obj) {
        super(obj);
    }

    private static boolean canPost(String str) {
        return GalleryPreference.getInstanceDebug().loadBoolean(str, true);
    }

    public String[] getHeader(StackTraceElement[] stackTraceElementArr) {
        String[] header = super.getHeader(stackTraceElementArr);
        header[0] = "[BG2] " + header[0];
        return header;
    }

    public StackTraceElement[] getStackTraceElements() {
        StackTraceElement[] stackTraceElementArr = this.mElements;
        if (stackTraceElementArr != null) {
            return stackTraceElementArr;
        }
        return super.getStackTraceElements();
    }

    @Deprecated
    public void post() {
    }

    public String tag() {
        return "InternalAnrBG:";
    }

    public void post(StackTraceElement[] stackTraceElementArr) {
        this.mElements = stackTraceElementArr;
        String[] header = getHeader(stackTraceElementArr);
        if (canPost(header[0])) {
            GalleryPreference.getInstanceDebug().saveState(header[0], false);
            if (RandomNumber.nextInt(1000) == 99) {
                super.post();
            }
        }
    }
}
