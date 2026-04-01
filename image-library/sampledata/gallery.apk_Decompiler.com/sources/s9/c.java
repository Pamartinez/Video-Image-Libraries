package S9;

import com.samsung.android.gallery.module.remote.v2.RemoteDisplayState;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ RemoteDisplayState e;

    public /* synthetic */ c(RemoteDisplayState remoteDisplayState, int i2) {
        this.d = i2;
        this.e = remoteDisplayState;
    }

    public final void run() {
        int i2 = this.d;
        RemoteDisplayState remoteDisplayState = this.e;
        switch (i2) {
            case 0:
                remoteDisplayState.updateState();
                return;
            case 1:
                remoteDisplayState.lambda$unregisterObserver$1();
                return;
            case 2:
                remoteDisplayState.lambda$onResume$4();
                return;
            default:
                remoteDisplayState.lambda$onResume$5();
                return;
        }
    }
}
