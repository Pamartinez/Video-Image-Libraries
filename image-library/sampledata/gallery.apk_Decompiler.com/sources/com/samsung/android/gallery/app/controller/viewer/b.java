package com.samsung.android.gallery.app.controller.viewer;

import com.samsung.android.gallery.app.controller.viewer.DeleteUndoSingleCmd;
import com.samsung.android.gallery.module.dataset.MediaData;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ MediaData.SimpleDataChangeListener e;

    public /* synthetic */ b(MediaData.SimpleDataChangeListener simpleDataChangeListener, int i2) {
        this.d = i2;
        this.e = simpleDataChangeListener;
    }

    public final void run() {
        int i2 = this.d;
        MediaData.SimpleDataChangeListener simpleDataChangeListener = this.e;
        switch (i2) {
            case 0:
                ((DeleteUndoSingleCmd.AnonymousClass1) simpleDataChangeListener).lambda$onDataChanged$0();
                return;
            default:
                ((DeleteUndoSingleCmd.AnonymousClass4) simpleDataChangeListener).lambda$onDataChanged$0();
                return;
        }
    }
}
