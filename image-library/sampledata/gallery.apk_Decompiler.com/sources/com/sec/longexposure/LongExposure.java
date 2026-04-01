package com.sec.longexposure;

import android.content.Context;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class LongExposure {
    LongExposureImpl longExposureImpl = new LongExposureImpl();

    public synchronized int cancel() {
        return this.longExposureImpl.cancel();
    }

    public synchronized int start(Context context, String str, String str2, ILongExposureCallback iLongExposureCallback) {
        return this.longExposureImpl.start(context, str, str2, iLongExposureCallback);
    }
}
