package com.samsung.android.gallery.module.longexposure;

import com.samsung.android.gallery.module.longexposure.LongExposureHelper;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Runnable {
    public final /* synthetic */ LongExposureHelper.AnonymousClass1 d;
    public final /* synthetic */ Consumer e;

    public /* synthetic */ a(LongExposureHelper.AnonymousClass1 r1, Consumer consumer) {
        this.d = r1;
        this.e = consumer;
    }

    public final void run() {
        this.d.lambda$onFinish$0(this.e);
    }
}
