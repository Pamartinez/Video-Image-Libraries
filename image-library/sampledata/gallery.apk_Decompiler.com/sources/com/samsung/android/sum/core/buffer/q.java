package com.samsung.android.sum.core.buffer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class q implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ MediaBufferBase e;

    public /* synthetic */ q(MediaBufferBase mediaBufferBase, int i2) {
        this.d = i2;
        this.e = mediaBufferBase;
    }

    public final void run() {
        int i2 = this.d;
        MediaBufferBase mediaBufferBase = this.e;
        switch (i2) {
            case 0:
                ((DeriveBufferGroup) mediaBufferBase).lambda$release$1();
                return;
            case 1:
                ((GenericMediaBuffer) mediaBufferBase).lambda$release$0();
                return;
            case 2:
                ((MediaBufferGroup) mediaBufferBase).lambda$release$3();
                return;
            default:
                ((MutableMediaBuffer) mediaBufferBase).lambda$release$0();
                return;
        }
    }
}
