package com.samsung.android.sum.core.buffer;

import java.util.HashMap;
import java.util.function.Supplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class u implements Supplier {
    public final /* synthetic */ int d;

    public /* synthetic */ u(int i2) {
        this.d = i2;
    }

    public final Object get() {
        switch (this.d) {
            case 0:
                return new HashMap();
            case 1:
                return MediaBufferFileReader.lambda$read$4();
            case 2:
                return MediaBufferFileReader.lambda$read$6();
            case 3:
                return MediaBufferFileWriter.lambda$writeSingle$1();
            default:
                return new IllegalStateException();
        }
    }
}
