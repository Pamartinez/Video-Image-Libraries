package yd;

import android.graphics.Bitmap;
import com.samsung.android.vexfwk.sdk.objeraser.VexFwkObjectExtractor;
import java.util.function.Supplier;

/* renamed from: yd.c  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C1356c implements Supplier {
    public final /* synthetic */ float d;
    public final /* synthetic */ float e;
    public final /* synthetic */ Bitmap f;
    public final /* synthetic */ VexFwkObjectExtractor g;

    public /* synthetic */ C1356c(float f5, float f8, Bitmap bitmap, VexFwkObjectExtractor vexFwkObjectExtractor) {
        this.d = f5;
        this.e = f8;
        this.f = bitmap;
        this.g = vexFwkObjectExtractor;
    }

    public final Object get() {
        return VexFwkObjectExtractor.extractObject$lambda$7(this.d, this.e, this.f, this.g);
    }
}
