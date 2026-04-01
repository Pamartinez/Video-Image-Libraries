package com.samsung.android.app.sdk.deepsky.textextraction.translate.engine;

import android.graphics.Bitmap;
import com.samsung.android.app.sdk.deepsky.textextraction.logger.LibLogger;
import com.samsung.android.app.sdk.deepsky.textextraction.translate.ImageTranslateListener;
import com.samsung.android.app.sdk.deepsky.textextraction.translate.data.ImageTranslationResult;
import com.samsung.android.app.sdk.deepsky.textextraction.util.SingleThreadCoroutineSwitcher;
import com.samsung.android.imagetranslation.LttEngineClient;
import com.samsung.android.imagetranslation.LttEngineListener;
import com.samsung.android.imagetranslation.inpainting.InpainterResult;
import java.util.concurrent.CountDownLatch;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import me.x;
import vd.c;
import y3.b;

@Metadata(d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J!\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0016¢\u0006\u0004\b\u0007\u0010\bJ!\u0010\n\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\b\u0010\u0005\u001a\u0004\u0018\u00010\tH\u0016¢\u0006\u0004\b\n\u0010\u000bJ\u001f\u0010\r\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"com/samsung/android/app/sdk/deepsky/textextraction/translate/engine/LttV5ImageTranslationEngine$initLttEngineClient$1", "Lcom/samsung/android/imagetranslation/LttEngineListener;", "", "requestId", "Lcom/samsung/android/imagetranslation/inpainting/InpainterResult;", "result", "Lme/x;", "onInPaintingSuccess", "(ILcom/samsung/android/imagetranslation/inpainting/InpainterResult;)V", "Landroid/graphics/Bitmap;", "onRenderSuccess", "(ILandroid/graphics/Bitmap;)V", "errorCode", "onFailure", "(II)V", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class LttV5ImageTranslationEngine$initLttEngineClient$1 implements LttEngineListener {
    final /* synthetic */ CountDownLatch $inPaintCountDownLatch;
    final /* synthetic */ ImageTranslateListener $listener;
    final /* synthetic */ LttEngineClient $lttEngineClient;
    final /* synthetic */ CountDownLatch $renderCountDownLatch;
    final /* synthetic */ LttV5ImageTranslationEngine this$0;

    public LttV5ImageTranslationEngine$initLttEngineClient$1(LttV5ImageTranslationEngine lttV5ImageTranslationEngine, CountDownLatch countDownLatch, LttEngineClient lttEngineClient, ImageTranslateListener imageTranslateListener, CountDownLatch countDownLatch2) {
        this.this$0 = lttV5ImageTranslationEngine;
        this.$inPaintCountDownLatch = countDownLatch;
        this.$lttEngineClient = lttEngineClient;
        this.$listener = imageTranslateListener;
        this.$renderCountDownLatch = countDownLatch2;
    }

    /* access modifiers changed from: private */
    public static final Object onFailure$lambda$1(int i2, LttEngineClient lttEngineClient, ImageTranslateListener imageTranslateListener, CountDownLatch countDownLatch, CountDownLatch countDownLatch2, LttV5ImageTranslationEngine lttV5ImageTranslationEngine, x xVar) {
        j.e(xVar, "it");
        x xVar2 = x.f4917a;
        if (i2 == -400) {
            LibLogger.e("LttV5ImageTranslationEngine", "LttEngineClient - ERROR_HARD_ERROR");
            lttEngineClient.release();
            imageTranslateListener.onImageTranslateFail(new ImageTranslationResult.Error.RendererFail(0, 1, (e) null));
            countDownLatch.countDown();
            countDownLatch2.countDown();
            return xVar2;
        } else if (i2 == -300) {
            return Integer.valueOf(LibLogger.w("LttV5ImageTranslationEngine", "LttEngineClient - ERROR_RENDERER_SOFT_ERROR"));
        } else {
            if (i2 != -200) {
                LibLogger.e("LttV5ImageTranslationEngine", "LttEngineClient - ERROR_UNKNOWN_ERROR");
                lttEngineClient.release();
                imageTranslateListener.onImageTranslateFail(new ImageTranslationResult.Error.RendererFail(0, 1, (e) null));
                countDownLatch.countDown();
                countDownLatch2.countDown();
                return xVar2;
            }
            LibLogger.w("LttV5ImageTranslationEngine", "LttEngineClient - ERROR_INPAINTER_SOFT_ERROR");
            lttV5ImageTranslationEngine.inPaintedBitmap = null;
            countDownLatch.countDown();
            return xVar2;
        }
    }

    /* access modifiers changed from: private */
    public static final x onRenderSuccess$lambda$0(LttEngineClient lttEngineClient, LttV5ImageTranslationEngine lttV5ImageTranslationEngine, Bitmap bitmap, ImageTranslateListener imageTranslateListener, CountDownLatch countDownLatch, x xVar) {
        j.e(xVar, "it");
        lttEngineClient.release();
        lttV5ImageTranslationEngine.setRenderedBitmap(bitmap);
        LibLogger.i("LttV5ImageTranslationEngine", "Render Success from LTT");
        imageTranslateListener.onImageTranslateSuccess();
        countDownLatch.countDown();
        return x.f4917a;
    }

    public void onFailure(int i2, int i7) {
        SingleThreadCoroutineSwitcher.Chain.start$default(SingleThreadCoroutineSwitcher.INSTANCE.newChain().onMain(new b(i7, this.$lttEngineClient, this.$listener, this.$inPaintCountDownLatch, this.$renderCountDownLatch, this.this$0)), (Ae.b) null, (Ae.b) null, 3, (Object) null);
    }

    public void onInPaintingSuccess(int i2, InpainterResult inpainterResult) {
        Bitmap bitmap;
        LibLogger.i("LttV5ImageTranslationEngine", "InPainting Success from LTT");
        LttV5ImageTranslationEngine lttV5ImageTranslationEngine = this.this$0;
        if (inpainterResult != null) {
            bitmap = inpainterResult.getInpaintedBitmap();
        } else {
            bitmap = null;
        }
        lttV5ImageTranslationEngine.inPaintedBitmap = bitmap;
        this.$inPaintCountDownLatch.countDown();
    }

    public void onRenderSuccess(int i2, Bitmap bitmap) {
        SingleThreadCoroutineSwitcher.Chain.start$default(SingleThreadCoroutineSwitcher.INSTANCE.newChain().onMain(new c(this.$lttEngineClient, this.this$0, bitmap, this.$listener, this.$renderCountDownLatch, 2)), (Ae.b) null, (Ae.b) null, 3, (Object) null);
    }
}
