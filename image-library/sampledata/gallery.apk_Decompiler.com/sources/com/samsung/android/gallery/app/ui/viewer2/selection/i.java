package com.samsung.android.gallery.app.ui.viewer2.selection;

import com.samsung.android.gallery.app.ui.viewer2.selection.SelectionViewAdapter;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class i implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ SelectionViewAdapter.AnonymousClass1 e;

    public /* synthetic */ i(SelectionViewAdapter.AnonymousClass1 r1, int i2) {
        this.d = i2;
        this.e = r1;
    }

    public final void run() {
        int i2 = this.d;
        SelectionViewAdapter.AnonymousClass1 r1 = this.e;
        switch (i2) {
            case 0:
                r1.lambda$onDataChanged$0();
                return;
            default:
                r1.lambda$onDataChanged$1();
                return;
        }
    }
}
