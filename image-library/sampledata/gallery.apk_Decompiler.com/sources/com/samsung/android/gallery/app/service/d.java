package com.samsung.android.gallery.app.service;

import com.samsung.android.gallery.app.service.RemasterService;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    public /* synthetic */ d(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    public final void run() {
        int i2 = this.d;
        Object obj = this.e;
        switch (i2) {
            case 0:
                ((FileOpData) obj).lambda$stop$0();
                return;
            default:
                ((RemasterService.RemasterWorker) obj).interruptRemaster();
                return;
        }
    }
}
