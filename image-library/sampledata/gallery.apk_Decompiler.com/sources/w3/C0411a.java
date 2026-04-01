package W3;

import com.samsung.android.gallery.app.controller.viewer.DeleteUndoSingleCmd;

/* renamed from: W3.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0411a implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ DeleteUndoSingleCmd e;

    public /* synthetic */ C0411a(DeleteUndoSingleCmd deleteUndoSingleCmd, int i2) {
        this.d = i2;
        this.e = deleteUndoSingleCmd;
    }

    public final void run() {
        int i2 = this.d;
        DeleteUndoSingleCmd deleteUndoSingleCmd = this.e;
        switch (i2) {
            case 0:
                deleteUndoSingleCmd.lambda$deleteWithCmh$0();
                return;
            default:
                deleteUndoSingleCmd.operateDeleteInternal();
                return;
        }
    }
}
