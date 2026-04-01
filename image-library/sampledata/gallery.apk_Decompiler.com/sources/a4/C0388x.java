package A4;

import com.samsung.android.gallery.app.ui.list.abstraction.BaseListKeyHandler;

/* renamed from: A4.x  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0388x implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ BaseListKeyHandler e;

    public /* synthetic */ C0388x(BaseListKeyHandler baseListKeyHandler, int i2) {
        this.d = i2;
        this.e = baseListKeyHandler;
    }

    public final void run() {
        int i2 = this.d;
        BaseListKeyHandler baseListKeyHandler = this.e;
        switch (i2) {
            case 0:
                baseListKeyHandler.lambda$onMouseEvent$6();
                return;
            default:
                baseListKeyHandler.lambda$onMouseEvent$7();
                return;
        }
    }
}
