package com.samsung.android.imagetranslation;

import android.graphics.Bitmap;
import com.samsung.android.imagetranslation.LttEngine;
import com.samsung.android.imagetranslation.data.MaskResponse;
import com.samsung.android.imagetranslation.inpainting.InpainterResult;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ LttEngine.AnonymousClass1 e;
    public final /* synthetic */ int f;
    public final /* synthetic */ Object g;

    public /* synthetic */ b(LttEngine.AnonymousClass1 r1, int i2, Object obj, int i7) {
        this.d = i7;
        this.e = r1;
        this.f = i2;
        this.g = obj;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.e.lambda$onRenderSuccess$0(this.f, (Bitmap) this.g);
                return;
            case 1:
                this.e.lambda$onInpaintingSuccess$2(this.f, (InpainterResult) this.g);
                return;
            default:
                this.e.lambda$onMaskAvailable$3(this.f, (MaskResponse) this.g);
                return;
        }
    }
}
