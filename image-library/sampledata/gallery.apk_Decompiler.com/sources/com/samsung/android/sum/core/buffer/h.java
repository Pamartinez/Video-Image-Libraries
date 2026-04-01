package com.samsung.android.sum.core.buffer;

import android.hardware.HardwareBuffer;
import android.media.Image;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class h implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ BufferExtension e;

    public /* synthetic */ h(BufferExtension bufferExtension, int i2) {
        this.d = i2;
        this.e = bufferExtension;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        BufferExtension bufferExtension = this.e;
        switch (i2) {
            case 0:
                bufferExtension.lambda$new$17((HardwareBuffer) obj);
                return;
            default:
                bufferExtension.lambda$new$22((Image) obj);
                return;
        }
    }
}
