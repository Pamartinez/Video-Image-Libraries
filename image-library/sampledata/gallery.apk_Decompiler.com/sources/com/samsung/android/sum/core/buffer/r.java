package com.samsung.android.sum.core.buffer;

import android.graphics.Bitmap;
import android.os.ParcelFileDescriptor;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class r implements Consumer {
    public final /* synthetic */ int d;

    public /* synthetic */ r(int i2) {
        this.d = i2;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                MediaBufferBase.lambda$release$9(obj);
                return;
            case 1:
                BufferExtension.lambda$new$18((ParcelFileDescriptor) obj);
                return;
            default:
                BufferExtension.lambda$new$23((Bitmap) obj);
                return;
        }
    }
}
