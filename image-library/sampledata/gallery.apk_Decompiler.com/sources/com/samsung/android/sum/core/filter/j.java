package com.samsung.android.sum.core.filter;

import com.samsung.android.sum.core.message.Message;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class j implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ MediaMuxerFilter e;
    public final /* synthetic */ Message f;

    public /* synthetic */ j(MediaMuxerFilter mediaMuxerFilter, Message message, int i2) {
        this.d = i2;
        this.e = mediaMuxerFilter;
        this.f = message;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.e.lambda$onReceiveConfigureData$10(this.f);
                return;
            default:
                this.e.lambda$onReceiveCodecFormatData$8(this.f);
                return;
        }
    }
}
