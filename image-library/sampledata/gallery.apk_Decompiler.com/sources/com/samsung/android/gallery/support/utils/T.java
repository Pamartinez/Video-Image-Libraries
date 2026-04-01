package com.samsung.android.gallery.support.utils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class T implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ ThreadBuilder e;
    public final /* synthetic */ Runnable f;

    public /* synthetic */ T(ThreadBuilder threadBuilder, Runnable runnable, int i2) {
        this.d = i2;
        this.e = threadBuilder;
        this.f = runnable;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.e.lambda$execute$0(this.f);
                return;
            default:
                this.e.lambda$executeWorker$1(this.f);
                return;
        }
    }
}
