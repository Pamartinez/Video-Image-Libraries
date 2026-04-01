package com.samsung.scsp.common;

import com.samsung.android.motionphoto.utils.v2.h;
import com.samsung.scsp.error.FaultBarrier;
import com.samsung.scsp.error.Logger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DeviceHealthCheckPushExecutorImpl implements Consumer<PushVo> {
    public static final String IDENTIFIER = "identity.deviceHealthCheck";
    private static final Logger logger = Logger.get("DeviceHealthCheckPushExecutorImpl");

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$accept$1(PushVo pushVo) {
        String str = pushVo.callbackUrl;
        Logger logger2 = logger;
        logger2.d(new h(str, 2));
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
        if (httpURLConnection != null) {
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setConnectTimeout(30000);
            httpURLConnection.setReadTimeout(30000);
            int responseCode = httpURLConnection.getResponseCode();
            logger2.i("callback response :" + responseCode);
        }
    }

    public void accept(PushVo pushVo) {
        FaultBarrier.run(new b(pushVo));
    }
}
