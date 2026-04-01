package C3;

import android.app.AlertDialog;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class h implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ AlertDialog.Builder e;

    public /* synthetic */ h(AlertDialog.Builder builder, int i2) {
        this.d = i2;
        this.e = builder;
    }

    public final void run() {
        int i2 = this.d;
        AlertDialog.Builder builder = this.e;
        switch (i2) {
            case 0:
                builder.create().show();
                return;
            default:
                builder.create().show();
                return;
        }
    }
}
