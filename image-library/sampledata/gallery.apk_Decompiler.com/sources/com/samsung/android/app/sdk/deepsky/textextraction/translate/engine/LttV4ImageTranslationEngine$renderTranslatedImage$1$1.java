package com.samsung.android.app.sdk.deepsky.textextraction.translate.engine;

import Ae.b;
import android.graphics.Bitmap;
import com.samsung.android.app.sdk.deepsky.textextraction.logger.LibLogger;
import com.samsung.android.app.sdk.deepsky.textextraction.translate.ImageTranslateListener;
import com.samsung.android.app.sdk.deepsky.textextraction.translate.data.ImageTranslationResult;
import com.samsung.android.app.sdk.deepsky.textextraction.util.SingleThreadCoroutineSwitcher;
import com.samsung.android.imagetranslation.LttEngineClient;
import com.samsung.android.imagetranslation.LttEngineListener;
import com.samsung.android.imagetranslation.inpainting.InpainterResult;
import com.samsung.android.motionphoto.utils.v2.n;
import java.util.concurrent.CountDownLatch;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import me.x;
import vd.c;

@Metadata(d1 = {"\u0000-\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J!\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0016¢\u0006\u0004\b\u0007\u0010\bJ!\u0010\n\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\b\u0010\u0005\u001a\u0004\u0018\u00010\tH\u0016¢\u0006\u0004\b\n\u0010\u000bJ\u001f\u0010\f\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\f\u0010\rJ\u0019\u0010\n\u001a\u00020\u00062\b\u0010\u000e\u001a\u0004\u0018\u00010\tH\u0016¢\u0006\u0004\b\n\u0010\u000fJ\u0019\u0010\u0012\u001a\u00020\u00062\b\u0010\u0011\u001a\u0004\u0018\u00010\u0010H\u0016¢\u0006\u0004\b\u0012\u0010\u0013¨\u0006\u0014"}, d2 = {"com/samsung/android/app/sdk/deepsky/textextraction/translate/engine/LttV4ImageTranslationEngine$renderTranslatedImage$1$1", "Lcom/samsung/android/imagetranslation/LttEngineListener;", "", "p0", "Lcom/samsung/android/imagetranslation/inpainting/InpainterResult;", "p1", "Lme/x;", "onInPaintingSuccess", "(ILcom/samsung/android/imagetranslation/inpainting/InpainterResult;)V", "Landroid/graphics/Bitmap;", "onRenderSuccess", "(ILandroid/graphics/Bitmap;)V", "onFailure", "(II)V", "bitmap", "(Landroid/graphics/Bitmap;)V", "", "message", "onRenderFailure", "(Ljava/lang/String;)V", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class LttV4ImageTranslationEngine$renderTranslatedImage$1$1 implements LttEngineListener {
    final /* synthetic */ ImageTranslateListener $listener;
    final /* synthetic */ LttEngineClient $lttEngineClient;
    final /* synthetic */ CountDownLatch $renderCountDownLatch;
    final /* synthetic */ LttV4ImageTranslationEngine this$0;

    public LttV4ImageTranslationEngine$renderTranslatedImage$1$1(LttEngineClient lttEngineClient, LttV4ImageTranslationEngine lttV4ImageTranslationEngine, ImageTranslateListener imageTranslateListener, CountDownLatch countDownLatch) {
        this.$lttEngineClient = lttEngineClient;
        this.this$0 = lttV4ImageTranslationEngine;
        this.$listener = imageTranslateListener;
        this.$renderCountDownLatch = countDownLatch;
    }

    /* access modifiers changed from: private */
    public static final x onRenderFailure$lambda$1(LttEngineClient lttEngineClient, String str, ImageTranslateListener imageTranslateListener, CountDownLatch countDownLatch, x xVar) {
        j.e(xVar, "it");
        lttEngineClient.release4x();
        LibLogger.e("LttV4ImageTranslationEngine", "Render Failure from LTT : " + str);
        imageTranslateListener.onImageTranslateFail(new ImageTranslationResult.Error.RendererFail(0, 1, (e) null));
        countDownLatch.countDown();
        return x.f4917a;
    }

    /* access modifiers changed from: private */
    public static final x onRenderSuccess$lambda$0(LttEngineClient lttEngineClient, LttV4ImageTranslationEngine lttV4ImageTranslationEngine, Bitmap bitmap, ImageTranslateListener imageTranslateListener, CountDownLatch countDownLatch, x xVar) {
        j.e(xVar, "it");
        lttEngineClient.release4x();
        LibLogger.i("LttV4ImageTranslationEngine", "Render Success from LTT");
        lttV4ImageTranslationEngine.setRenderedBitmap(bitmap);
        imageTranslateListener.onImageTranslateSuccess();
        countDownLatch.countDown();
        return x.f4917a;
    }

    public void onRenderFailure(String str) {
        SingleThreadCoroutineSwitcher.Chain.start$default(SingleThreadCoroutineSwitcher.INSTANCE.newChain().onMain(new n(this.$lttEngineClient, str, this.$listener, this.$renderCountDownLatch, 2)), (b) null, (b) null, 3, (Object) null);
    }

    public void onRenderSuccess(int i2, Bitmap bitmap) {
    }

    public void onRenderSuccess(Bitmap bitmap) {
        SingleThreadCoroutineSwitcher.Chain.start$default(SingleThreadCoroutineSwitcher.INSTANCE.newChain().onMain(new c(this.$lttEngineClient, this.this$0, bitmap, this.$listener, this.$renderCountDownLatch, 1)), (b) null, (b) null, 3, (Object) null);
    }

    public void onFailure(int i2, int i7) {
    }

    public void onInPaintingSuccess(int i2, InpainterResult inpainterResult) {
    }
}
