package com.samsung.scsp.framework.core.network.base;

import com.samsung.scsp.error.FaultBarrier;
import java.net.HttpURLConnection;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class f implements FaultBarrier.ThrowableRunnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ HttpURLConnection e;

    public /* synthetic */ f(int i2, HttpURLConnection httpURLConnection) {
        this.d = i2;
        this.e = httpURLConnection;
    }

    public final void run() {
        int i2 = this.d;
        HttpURLConnection httpURLConnection = this.e;
        switch (i2) {
            case 0:
                NetworkImpl.closeSilently(httpURLConnection.getInputStream());
                return;
            case 1:
                NetworkImpl.closeSilently(httpURLConnection.getErrorStream());
                return;
            default:
                NetworkImpl.closeSilently(httpURLConnection.getOutputStream());
                return;
        }
    }
}
