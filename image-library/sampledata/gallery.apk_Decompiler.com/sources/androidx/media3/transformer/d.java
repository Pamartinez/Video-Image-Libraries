package androidx.media3.transformer;

import F2.Q;
import android.graphics.Bitmap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;
    public final /* synthetic */ Object f;

    public /* synthetic */ d(int i2, Object obj, Object obj2) {
        this.d = i2;
        this.e = obj;
        this.f = obj2;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                ((FallbackListener) this.e).lambda$onTransformationRequestFinalized$1((TransformationRequest) this.f);
                return;
            case 1:
                ((SequenceAssetLoader) this.e).lambda$insertBlankFrames$1((Bitmap) this.f);
                return;
            default:
                ((TransformerInternal) this.e).lambda$endInternal$1((Q) this.f);
                return;
        }
    }
}
