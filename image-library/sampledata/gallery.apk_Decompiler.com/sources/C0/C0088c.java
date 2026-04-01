package c0;

import android.graphics.Bitmap;
import androidx.media3.common.Format;
import androidx.media3.transformer.ImageAssetLoader;

/* renamed from: c0.c  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0088c implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ ImageAssetLoader e;
    public final /* synthetic */ Bitmap f;
    public final /* synthetic */ Format g;

    public /* synthetic */ C0088c(ImageAssetLoader imageAssetLoader, Bitmap bitmap, Format format, int i2) {
        this.d = i2;
        this.e = imageAssetLoader;
        this.f = bitmap;
        this.g = format;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.e.lambda$queueBitmapInternal$0(this.f, this.g);
                return;
            default:
                this.e.lambda$queueBitmapInternal$1(this.f, this.g);
                return;
        }
    }
}
