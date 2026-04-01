package p0;

import androidx.work.impl.background.systemalarm.DelayMetCommandHandler;

/* renamed from: p0.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0252a implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ DelayMetCommandHandler e;

    public /* synthetic */ C0252a(DelayMetCommandHandler delayMetCommandHandler, int i2) {
        this.d = i2;
        this.e = delayMetCommandHandler;
    }

    public final void run() {
        int i2 = this.d;
        DelayMetCommandHandler delayMetCommandHandler = this.e;
        switch (i2) {
            case 0:
                delayMetCommandHandler.stopWork();
                return;
            default:
                delayMetCommandHandler.startWork();
                return;
        }
    }
}
