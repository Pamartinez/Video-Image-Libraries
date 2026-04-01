package com.samsung.android.gallery.support.utils;

/* renamed from: com.samsung.android.gallery.support.utils.k  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0673k implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ String e;

    public /* synthetic */ C0673k(String str, int i2) {
        this.d = i2;
        this.e = str;
    }

    public final void run() {
        int i2 = this.d;
        String str = this.e;
        switch (i2) {
            case 0:
                FileLogger.dumpToFileAsync(str);
                return;
            default:
                Log.sendLog(str);
                return;
        }
    }
}
