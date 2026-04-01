package com.samsung.android.gallery.settings.ui;

import com.samsung.android.gallery.settings.ui.LabsBaseFragment;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class x implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ LabsBaseFragment.WorkerTask e;
    public final /* synthetic */ Object[] f;

    public /* synthetic */ x(LabsBaseFragment.WorkerTask workerTask, Object[] objArr, int i2) {
        this.d = i2;
        this.e = workerTask;
        this.f = objArr;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.e.lambda$execute$0(this.f);
                return;
            case 1:
                this.e.lambda$execute$1(this.f);
                return;
            default:
                this.e.lambda$execute$2(this.f);
                return;
        }
    }
}
