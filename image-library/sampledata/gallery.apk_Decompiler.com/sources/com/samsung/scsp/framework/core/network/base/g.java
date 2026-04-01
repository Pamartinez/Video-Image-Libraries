package com.samsung.scsp.framework.core.network.base;

import com.samsung.scsp.error.FaultBarrier;
import com.samsung.scsp.framework.core.network.base.NetworkImpl;
import java.io.Closeable;
import java.io.FileInputStream;
import java.net.HttpURLConnection;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class g implements NetworkImpl.ConnectionSetter, FaultBarrier.ThrowableRunnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    public /* synthetic */ g(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    public void run() {
        int i2 = this.d;
        Object obj = this.e;
        switch (i2) {
            case 1:
                ((Closeable) obj).close();
                return;
            default:
                ((FileInputStream) obj).close();
                return;
        }
    }

    public void setup(HttpURLConnection httpURLConnection) {
        httpURLConnection.setRequestMethod((String) this.e);
    }
}
