package g0;

import androidx.room.MultiInstanceInvalidationClient;

/* renamed from: g0.b  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0189b implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ MultiInstanceInvalidationClient e;

    public /* synthetic */ C0189b(MultiInstanceInvalidationClient multiInstanceInvalidationClient, int i2) {
        this.d = i2;
        this.e = multiInstanceInvalidationClient;
    }

    public final void run() {
        int i2 = this.d;
        MultiInstanceInvalidationClient multiInstanceInvalidationClient = this.e;
        switch (i2) {
            case 0:
                MultiInstanceInvalidationClient.setUpRunnable$lambda$1(multiInstanceInvalidationClient);
                return;
            default:
                MultiInstanceInvalidationClient.removeObserverRunnable$lambda$2(multiInstanceInvalidationClient);
                return;
        }
    }
}
