package O3;

import com.samsung.android.gallery.app.controller.internals.DeleteCmd;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class j implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ DeleteCmd e;

    public /* synthetic */ j(DeleteCmd deleteCmd, int i2) {
        this.d = i2;
        this.e = deleteCmd;
    }

    public final void run() {
        int i2 = this.d;
        DeleteCmd deleteCmd = this.e;
        switch (i2) {
            case 0:
                deleteCmd.startConfirmDialog();
                return;
            case 1:
                deleteCmd.lambda$startConfirmDialog$2();
                return;
            case 2:
                deleteCmd.lambda$onConfirmed$3();
                return;
            case 3:
                deleteCmd.lambda$operateDelete$0();
                return;
            default:
                deleteCmd.lambda$operateDeleteInternal$1();
                return;
        }
    }
}
