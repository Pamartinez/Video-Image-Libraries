package com.samsung.android.sum.core.buffer;

import com.samsung.android.sum.core.buffer.BufferExtension;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class o implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    public /* synthetic */ o(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        Object obj2 = this.e;
        switch (i2) {
            case 0:
                ((BufferExtension.TransformFunction) obj2).lambda$new$0((BufferExtension.TransformFunction) obj);
                return;
            default:
                ((MediaBufferBase) obj2).lambda$transformDataTo$8(obj);
                return;
        }
    }
}
