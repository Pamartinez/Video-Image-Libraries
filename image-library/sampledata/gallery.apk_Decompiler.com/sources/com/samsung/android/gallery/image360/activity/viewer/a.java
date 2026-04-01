package com.samsung.android.gallery.image360.activity.viewer;

import android.graphics.Bitmap;
import com.samsung.android.gallery.image360.activity.viewer.Image360Fragment;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Runnable {
    public final /* synthetic */ Image360Fragment.AnonymousClass1 d;
    public final /* synthetic */ Bitmap e;

    public /* synthetic */ a(Image360Fragment.AnonymousClass1 r1, Bitmap bitmap) {
        this.d = r1;
        this.e = bitmap;
    }

    public final void run() {
        this.d.lambda$accept$0(this.e);
    }
}
