package com.samsung.android.sum.core.channel;

import com.samsung.android.sum.core.buffer.MediaBuffer;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class k implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ SendChannelRouter e;

    public /* synthetic */ k(SendChannelRouter sendChannelRouter, int i2) {
        this.d = i2;
        this.e = sendChannelRouter;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        SendChannelRouter sendChannelRouter = this.e;
        MediaBuffer mediaBuffer = (MediaBuffer) obj;
        switch (i2) {
            case 0:
                sendChannelRouter.evaluate(mediaBuffer);
                return;
            case 1:
                sendChannelRouter.broadcast(mediaBuffer);
                return;
            case 2:
                sendChannelRouter.sendAny(mediaBuffer);
                return;
            default:
                sendChannelRouter.sendAll(mediaBuffer);
                return;
        }
    }
}
