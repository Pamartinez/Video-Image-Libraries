package F;

import androidx.media3.common.util.BackgroundThreadStateHandler;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ BackgroundThreadStateHandler e;
    public final /* synthetic */ Object f;

    public /* synthetic */ a(BackgroundThreadStateHandler backgroundThreadStateHandler, Object obj, int i2) {
        this.d = i2;
        this.e = backgroundThreadStateHandler;
        this.f = obj;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.e.lambda$setStateInBackground$2(this.f);
                return;
            default:
                this.e.lambda$updateStateAsync$0(this.f);
                return;
        }
    }
}
