package com.samsung.android.app.sdk.deepsky.textextraction.translate.engine;

import android.content.Context;
import android.graphics.Bitmap;
import com.samsung.android.app.sdk.deepsky.textextraction.logger.LibLogger;
import com.samsung.android.app.sdk.deepsky.textextraction.result.OcrData;
import com.samsung.android.app.sdk.deepsky.textextraction.translate.ImageTranslateListener;
import com.samsung.android.app.sdk.deepsky.textextraction.translate.TextTranslator;
import com.samsung.android.imagetranslation.LttEngineClient;
import com.samsung.android.imagetranslation.data.RenderParam;
import com.samsung.android.imagetranslation.data.Session;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0000\u0018\u0000 \u001f2\u00020\u0001:\u0001\u001fB\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u001f\u0010\r\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\nH\u0016¢\u0006\u0004\b\r\u0010\u000eJ/\u0010\u0016\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u0013H\u0016¢\u0006\u0004\b\u0016\u0010\u0017J5\u0010\u001b\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u000f2\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00190\u00182\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u0013H\u0016¢\u0006\u0004\b\u001b\u0010\u001cR\u0018\u0010\u001d\u001a\u0004\u0018\u00010\b8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\u001d\u0010\u001e¨\u0006 "}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/translate/engine/LttV5ImageTranslationEngine;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/translate/engine/LttImageTranslationEngine;", "Landroid/content/Context;", "context", "Lcom/samsung/android/app/sdk/deepsky/textextraction/translate/TextTranslator;", "textTranslator", "<init>", "(Landroid/content/Context;Lcom/samsung/android/app/sdk/deepsky/textextraction/translate/TextTranslator;)V", "Landroid/graphics/Bitmap;", "bitmap", "Lcom/samsung/android/app/sdk/deepsky/textextraction/result/OcrData;", "ocrData", "Lme/x;", "init", "(Landroid/graphics/Bitmap;Lcom/samsung/android/app/sdk/deepsky/textextraction/result/OcrData;)V", "Lcom/samsung/android/imagetranslation/LttEngineClient;", "lttEngineClient", "Lcom/samsung/android/app/sdk/deepsky/textextraction/translate/ImageTranslateListener;", "listener", "Ljava/util/concurrent/CountDownLatch;", "inPaintCountDownLatch", "renderCountDownLatch", "initLttEngineClient", "(Lcom/samsung/android/imagetranslation/LttEngineClient;Lcom/samsung/android/app/sdk/deepsky/textextraction/translate/ImageTranslateListener;Ljava/util/concurrent/CountDownLatch;Ljava/util/concurrent/CountDownLatch;)V", "", "", "translatedTextList", "renderTranslatedImage", "(Lcom/samsung/android/imagetranslation/LttEngineClient;Ljava/util/List;Lcom/samsung/android/app/sdk/deepsky/textextraction/translate/ImageTranslateListener;Ljava/util/concurrent/CountDownLatch;)V", "inPaintedBitmap", "Landroid/graphics/Bitmap;", "Companion", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class LttV5ImageTranslationEngine extends LttImageTranslationEngine {
    public static final Companion Companion = new Companion((e) null);
    /* access modifiers changed from: private */
    public Bitmap inPaintedBitmap;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/translate/engine/LttV5ImageTranslationEngine$Companion;", "", "<init>", "()V", "TAG", "", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public LttV5ImageTranslationEngine(Context context, TextTranslator textTranslator) {
        super(context, textTranslator);
        j.e(context, "context");
        j.e(textTranslator, "textTranslator");
    }

    public void init(Bitmap bitmap, OcrData ocrData) {
        j.e(bitmap, "bitmap");
        j.e(ocrData, "ocrData");
        super.init(bitmap, ocrData);
        this.inPaintedBitmap = null;
    }

    public void initLttEngineClient(LttEngineClient lttEngineClient, ImageTranslateListener imageTranslateListener, CountDownLatch countDownLatch, CountDownLatch countDownLatch2) {
        j.e(lttEngineClient, "lttEngineClient");
        j.e(imageTranslateListener, "listener");
        j.e(countDownLatch, "inPaintCountDownLatch");
        j.e(countDownLatch2, "renderCountDownLatch");
        LibLogger.i("LttV5ImageTranslationEngine", "initialize LttEngineClient version 5.x");
        LttEngineClient lttEngineClient2 = lttEngineClient;
        lttEngineClient2.initialize(new Session(getContext(), new LttV5ImageTranslationEngine$initLttEngineClient$1(this, countDownLatch, lttEngineClient2, imageTranslateListener, countDownLatch2)));
    }

    public void renderTranslatedImage(LttEngineClient lttEngineClient, List<String> list, ImageTranslateListener imageTranslateListener, CountDownLatch countDownLatch) {
        j.e(lttEngineClient, "lttEngineClient");
        j.e(list, "translatedTextList");
        j.e(imageTranslateListener, "listener");
        j.e(countDownLatch, "renderCountDownLatch");
        RenderParam renderParam = new RenderParam();
        renderParam.setInputBitmap(getOriginalBitmap());
        renderParam.setInPaintedBitmap(this.inPaintedBitmap);
        renderParam.setDestLanguage(getTextTranslator().getTargetLanguage());
        renderParam.setLttOcrResult(getLttOcrResult());
        renderParam.setTrlResult(list);
        lttEngineClient.renderImage(0, renderParam);
    }
}
