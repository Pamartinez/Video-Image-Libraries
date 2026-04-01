package com.google.android.material.datepicker;

import ge.C1003D;
import k2.d;
import v1.k;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class g implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ int e;
    public final /* synthetic */ Object f;

    public /* synthetic */ g(Object obj, int i2, int i7) {
        this.d = i7;
        this.f = obj;
        this.e = i2;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                ((p) this.f).k.smoothScrollToPosition(this.e);
                return;
            case 1:
                ((C1003D) this.f).f4388i.h(this.e);
                return;
            case 2:
                ((d) this.f).i(this.e);
                return;
            default:
                ((k) this.f).h(this.e);
                return;
        }
    }
}
