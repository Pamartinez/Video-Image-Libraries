package g0;

import androidx.room.AutoCloser;

/* renamed from: g0.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0188a implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ AutoCloser e;

    public /* synthetic */ C0188a(AutoCloser autoCloser, int i2) {
        this.d = i2;
        this.e = autoCloser;
    }

    public final void run() {
        int i2 = this.d;
        AutoCloser autoCloser = this.e;
        switch (i2) {
            case 0:
                AutoCloser.executeAutoCloser$lambda$0(autoCloser);
                return;
            default:
                AutoCloser.autoCloser$lambda$3(autoCloser);
                return;
        }
    }
}
