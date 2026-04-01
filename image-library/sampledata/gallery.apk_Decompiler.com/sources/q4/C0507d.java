package q4;

import com.samsung.android.gallery.app.ui.dialog.CreateNameDialog;

/* renamed from: q4.d  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0507d implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ CreateNameDialog e;

    public /* synthetic */ C0507d(CreateNameDialog createNameDialog, int i2) {
        this.d = i2;
        this.e = createNameDialog;
    }

    public final void run() {
        int i2 = this.d;
        CreateNameDialog createNameDialog = this.e;
        switch (i2) {
            case 0:
                createNameDialog.closeDialog();
                return;
            case 1:
                createNameDialog.lambda$onClickPositive$2();
                return;
            case 2:
                createNameDialog.lambda$postInitDialog$0();
                return;
            default:
                createNameDialog.lambda$clearError$6();
                return;
        }
    }
}
