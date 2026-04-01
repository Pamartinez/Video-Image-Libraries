package vb;

import android.view.View;
import com.samsung.android.gallery.widget.dragdrop.clipdata.ClipDataManager;

/* renamed from: vb.c  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0714c implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ ClipDataManager e;
    public final /* synthetic */ View f;

    public /* synthetic */ C0714c(ClipDataManager clipDataManager, View view, int i2) {
        this.d = i2;
        this.e = clipDataManager;
        this.f = view;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.e.lambda$addItemAndUpdate$2(this.f);
                return;
            default:
                this.e.lambda$removeItemAndUpdate$4(this.f);
                return;
        }
    }
}
