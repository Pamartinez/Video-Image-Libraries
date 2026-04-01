package androidx.media3.transformer;

import F2.Q;
import android.graphics.Bitmap;
import androidx.media3.common.Format;
import androidx.media3.transformer.ImageAssetLoader;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;
    public final /* synthetic */ Object f;
    public final /* synthetic */ Object g;

    public /* synthetic */ e(Object obj, Object obj2, Object obj3, int i2) {
        this.d = i2;
        this.e = obj;
        this.f = obj2;
        this.g = obj3;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                ((ImageAssetLoader.AnonymousClass1) this.e).lambda$onSuccess$0((Bitmap) this.f, (Format) this.g);
                return;
            default:
                ((TransformerInternal) this.e).lambda$endInternal$0((Q) this.f, (ExportException) this.g);
                return;
        }
    }
}
