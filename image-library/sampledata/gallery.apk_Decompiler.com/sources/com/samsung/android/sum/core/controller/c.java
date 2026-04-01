package com.samsung.android.sum.core.controller;

import com.samsung.android.sum.core.buffer.MediaBuffer;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ MediaFilterController e;

    public /* synthetic */ c(MediaFilterController mediaFilterController, int i2) {
        this.d = i2;
        this.e = mediaFilterController;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        MediaFilterController mediaFilterController = this.e;
        switch (i2) {
            case 0:
                mediaFilterController.lambda$setMediaFilterGraph$9((Consumer) obj);
                return;
            default:
                mediaFilterController.lambda$run$0((MediaBuffer) obj);
                return;
        }
    }
}
