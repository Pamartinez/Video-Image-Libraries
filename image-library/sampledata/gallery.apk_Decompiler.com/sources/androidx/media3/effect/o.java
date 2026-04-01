package androidx.media3.effect;

import androidx.media3.effect.MultipleInputVideoGraph;
import androidx.media3.effect.SingleInputVideoGraph;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class o implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    public /* synthetic */ o(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    public final void run() {
        int i2 = this.d;
        Object obj = this.e;
        switch (i2) {
            case 0:
                ((ExternalTextureManager) obj).lambda$restartForceSignalEndOfStreamTimer$8();
                return;
            case 1:
                ((MultipleInputVideoGraph.AnonymousClass1) obj).lambda$onEnded$3();
                return;
            default:
                ((SingleInputVideoGraph.AnonymousClass1) obj).lambda$onEnded$4();
                return;
        }
    }
}
