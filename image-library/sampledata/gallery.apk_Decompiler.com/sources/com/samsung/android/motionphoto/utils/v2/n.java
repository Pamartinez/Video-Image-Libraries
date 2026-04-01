package com.samsung.android.motionphoto.utils.v2;

import Ae.b;
import com.samsung.android.app.sdk.deepsky.textextraction.translate.ImageTranslateListener;
import com.samsung.android.app.sdk.deepsky.textextraction.translate.engine.LttV4ImageTranslationEngine$renderTranslatedImage$1$1;
import com.samsung.android.imagetranslation.LttEngineClient;
import com.samsung.android.vexfwk.metadata.VexFwkMetadataKey;
import com.samsung.android.vexfwk.metadata.VexFwkMetadataNative;
import com.samsung.android.vexfwk.param.VexFwkOcrResult;
import com.samsung.android.vexfwk.param.VexFwkOcrResultKt;
import com.samsung.android.vexfwk.param.VexFwkOcrResultV2;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import me.x;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class n implements b {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;
    public final /* synthetic */ Object f;
    public final /* synthetic */ Object g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ Object f3253h;

    public /* synthetic */ n(Object obj, Object obj2, Object obj3, Object obj4, int i2) {
        this.d = i2;
        this.e = obj;
        this.f = obj2;
        this.g = obj3;
        this.f3253h = obj4;
    }

    public final Object invoke(Object obj) {
        switch (this.d) {
            case 0:
                return SEFEditImpl.commit$lambda$35((SEFEditImpl) this.e, (SEFInfoImpl) this.f, (List) this.g, (ByteBuffer) this.f3253h, (FileChannel) obj);
            case 1:
                String str = (String) this.e;
                String str2 = (String) this.f;
                VexFwkOcrResult vexFwkOcrResult = (VexFwkOcrResult) this.g;
                VexFwkOcrResultV2 vexFwkOcrResultV2 = (VexFwkOcrResultV2) this.f3253h;
                VexFwkMetadataNative vexFwkMetadataNative = (VexFwkMetadataNative) obj;
                if (str != null) {
                    vexFwkMetadataNative.set(VexFwkMetadataKey.INPUT_PATH.INSTANCE, str);
                }
                if (str2 != null) {
                    vexFwkMetadataNative.set(VexFwkMetadataKey.OUTPUT_PATH.INSTANCE, str2);
                }
                if (vexFwkOcrResult != null) {
                    vexFwkMetadataNative.set(VexFwkMetadataKey.OCR_RESULT.INSTANCE, VexFwkOcrResultKt.toResultMeta(vexFwkOcrResult));
                    vexFwkMetadataNative.set(VexFwkMetadataKey.OCR_ADDITIONAL_RESULT.INSTANCE, VexFwkOcrResultKt.toAdditionalMeta(vexFwkOcrResult));
                }
                if (vexFwkOcrResultV2 != null) {
                    vexFwkMetadataNative.set(VexFwkMetadataKey.OCR_RESULT_V2.INSTANCE, vexFwkOcrResultV2.toResultMeta());
                }
                return x.f4917a;
            default:
                return LttV4ImageTranslationEngine$renderTranslatedImage$1$1.onRenderFailure$lambda$1((LttEngineClient) this.e, (String) this.f, (ImageTranslateListener) this.g, (CountDownLatch) this.f3253h, (x) obj);
        }
    }
}
