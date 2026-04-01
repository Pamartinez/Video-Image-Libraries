package com.sec.sshutter;

import android.graphics.YuvImage;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface ISlowShutterCallback {
    void onError(int i2);

    void onFinish(int i2);

    void onLastImage(YuvImage yuvImage, int i2);

    void onProgress(int i2);
}
