package androidx.media3.exoplayer;

import androidx.media3.common.util.BackgroundThreadStateHandler;
import androidx.media3.exoplayer.SuitableOutputChecker;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements BackgroundThreadStateHandler.StateChangeListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ SuitableOutputChecker.Callback e;

    public /* synthetic */ e(SuitableOutputChecker.Callback callback, int i2) {
        this.d = i2;
        this.e = callback;
    }

    public final void onStateChanged(Object obj, Object obj2) {
        int i2 = this.d;
        Boolean bool = (Boolean) obj;
        Boolean bool2 = (Boolean) obj2;
        SuitableOutputChecker.Callback callback = this.e;
        switch (i2) {
            case 0:
                ((k) callback).a(bool2.booleanValue());
                return;
            default:
                ((k) callback).a(bool2.booleanValue());
                return;
        }
    }
}
