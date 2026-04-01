package com.samsung.android.gallery.module.publisher;

import android.database.Cursor;
import android.graphics.Bitmap;
import com.samsung.android.gallery.module.graphics.BitmapCache;
import com.samsung.android.gallery.module.trash.abstraction.ITrashProvider;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class A implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;
    public final /* synthetic */ Object f;
    public final /* synthetic */ boolean g;

    public /* synthetic */ A(Object obj, Object obj2, boolean z, int i2) {
        this.d = i2;
        this.e = obj;
        this.f = obj2;
        this.g = z;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                ListDataPublisher.lambda$publishTrashData$10((Cursor[]) this.e, (ITrashProvider) this.f, this.g);
                return;
            case 1:
                ListDataPublisher.lambda$publishTrashData$11((Cursor[]) this.e, (ITrashProvider) this.f, this.g);
                return;
            default:
                BitmapCache.putDiskCache(8, (byte[]) this.e, (Bitmap) this.f, this.g);
                return;
        }
    }
}
