package q4;

import com.samsung.android.gallery.app.ui.dialog.CreateNameDialog;

/* renamed from: q4.c  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0506c implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ CreateNameDialog e;
    public final /* synthetic */ String f;

    public /* synthetic */ C0506c(CreateNameDialog createNameDialog, String str, int i2) {
        this.d = i2;
        this.e = createNameDialog;
        this.f = str;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.e.lambda$onClickPositive$3(this.f);
                return;
            case 1:
                this.e.lambda$postInitDialog$1(this.f);
                return;
            default:
                this.e.lambda$setError$5(this.f);
                return;
        }
    }
}
