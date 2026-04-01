package com.samsung.android.gallery.settings.ui;

import android.content.Context;
import com.samsung.android.gallery.settings.ui.LabsFragment;
import java.util.function.Consumer;

/* renamed from: com.samsung.android.gallery.settings.ui.h  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0653h implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;
    public final /* synthetic */ Object f;

    public /* synthetic */ C0653h(int i2, Object obj, Object obj2) {
        this.d = i2;
        this.e = obj;
        this.f = obj2;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                ((FixFaceTable) this.e).lambda$fix$1((Context) this.f);
                return;
            case 1:
                ((LabsFragment.PrivateRecoveryWorker) this.e).lambda$onExecute$0((Float) this.f);
                return;
            case 2:
                ((SettingCloud) this.e).lambda$loadAccount$17((Consumer) this.f);
                return;
            default:
                ((SettingCloud) this.e).lambda$update2FaStatus$23((String) this.f);
                return;
        }
    }
}
