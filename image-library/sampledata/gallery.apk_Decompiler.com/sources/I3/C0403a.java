package I3;

import android.content.DialogInterface;

/* renamed from: I3.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0403a implements DialogInterface.OnClickListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ Runnable e;

    public /* synthetic */ C0403a(Runnable runnable, int i2) {
        this.d = i2;
        this.e = runnable;
    }

    public final void onClick(DialogInterface dialogInterface, int i2) {
        int i7 = this.d;
        Runnable runnable = this.e;
        switch (i7) {
            case 0:
                runnable.run();
                return;
            default:
                runnable.run();
                return;
        }
    }
}
