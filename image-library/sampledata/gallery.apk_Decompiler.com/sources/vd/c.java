package vd;

import Ae.b;
import android.graphics.Bitmap;
import com.samsung.android.app.sdk.deepsky.textextraction.translate.ImageTranslateListener;
import com.samsung.android.app.sdk.deepsky.textextraction.translate.engine.LttV4ImageTranslationEngine;
import com.samsung.android.app.sdk.deepsky.textextraction.translate.engine.LttV4ImageTranslationEngine$renderTranslatedImage$1$1;
import com.samsung.android.app.sdk.deepsky.textextraction.translate.engine.LttV5ImageTranslationEngine;
import com.samsung.android.app.sdk.deepsky.textextraction.translate.engine.LttV5ImageTranslationEngine$initLttEngineClient$1;
import com.samsung.android.imagetranslation.LttEngineClient;
import com.samsung.android.vexfwk.metadata.VexFwkMetadataNative;
import com.samsung.android.vexfwk.param.VexFwkOcrResult;
import com.samsung.android.vexfwk.param.VexFwkOcrResultV2;
import com.samsung.android.vexfwk.sdk.imagetranslator.VexFwkImageTranslator;
import java.util.concurrent.CountDownLatch;
import me.x;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements b {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;
    public final /* synthetic */ Object f;
    public final /* synthetic */ Object g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ Object f5148h;

    /* renamed from: i  reason: collision with root package name */
    public final /* synthetic */ Object f5149i;

    public /* synthetic */ c(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, int i2) {
        this.d = i2;
        this.e = obj;
        this.f = obj2;
        this.g = obj3;
        this.f5148h = obj4;
        this.f5149i = obj5;
    }

    public final Object invoke(Object obj) {
        switch (this.d) {
            case 0:
                return VexFwkImageTranslator.translateImpl$lambda$16$lambda$8((String) this.e, (String) this.f, (VexFwkOcrResult) this.g, (VexFwkOcrResultV2) this.f5148h, (VexFwkImageTranslator) this.f5149i, (VexFwkMetadataNative) obj);
            case 1:
                return LttV4ImageTranslationEngine$renderTranslatedImage$1$1.onRenderSuccess$lambda$0((LttEngineClient) this.e, (LttV4ImageTranslationEngine) this.f, (Bitmap) this.g, (ImageTranslateListener) this.f5148h, (CountDownLatch) this.f5149i, (x) obj);
            default:
                return LttV5ImageTranslationEngine$initLttEngineClient$1.onRenderSuccess$lambda$0((LttEngineClient) this.e, (LttV5ImageTranslationEngine) this.f, (Bitmap) this.g, (ImageTranslateListener) this.f5148h, (CountDownLatch) this.f5149i, (x) obj);
        }
    }
}
