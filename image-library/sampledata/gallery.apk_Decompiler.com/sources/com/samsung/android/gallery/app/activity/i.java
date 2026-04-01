package com.samsung.android.gallery.app.activity;

import com.samsung.android.gallery.app.activity.DefaultGestureDetector;
import com.samsung.android.gallery.app.activity.GalleryPrivateActivity;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class i implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    public /* synthetic */ i(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    public final void run() {
        int i2 = this.d;
        Object obj = this.e;
        switch (i2) {
            case 0:
                ((GalleryPrivateActivity.KeepStorageDelegate) obj).close();
                return;
            case 1:
                ((GalleryPrivateActivity.KeepStorageDelegate) obj).openSession();
                return;
            case 2:
                ((GalleryPrivateActivity.KeepStorageDelegate) obj).open();
                return;
            default:
                ((DefaultGestureDetector.DefaultGestureListener) obj).lambda$onDoubleTap$0();
                return;
        }
    }
}
