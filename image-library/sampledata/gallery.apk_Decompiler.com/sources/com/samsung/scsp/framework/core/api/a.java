package com.samsung.scsp.framework.core.api;

import com.samsung.scsp.error.FaultBarrier;
import java.io.OutputStream;
import java.lang.reflect.Constructor;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements FaultBarrier.ThrowableRunnable, FaultBarrier.ThrowableSupplier {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    public /* synthetic */ a(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    public Object get() {
        int i2 = this.d;
        Object obj = this.e;
        switch (i2) {
            case 1:
                return AbstractApiControl.lambda$new$0((Constructor) obj);
            default:
                return ResponsiveJob.lambda$onStream$1((Map) obj);
        }
    }

    public void run() {
        int i2 = this.d;
        Object obj = this.e;
        switch (i2) {
            case 0:
                ((AbstractApi) obj).lambda$new$0();
                return;
            case 2:
                ((OutputStream) obj).close();
                return;
            default:
                ((Response) obj).lambda$toString$0();
                return;
        }
    }
}
