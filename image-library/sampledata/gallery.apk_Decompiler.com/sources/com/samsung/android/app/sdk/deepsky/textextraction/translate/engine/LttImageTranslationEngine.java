package com.samsung.android.app.sdk.deepsky.textextraction.translate.engine;

import Ae.b;
import Wf.c;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import com.samsung.android.app.sdk.deepsky.textextraction.languagepack.LanguageUtil;
import com.samsung.android.app.sdk.deepsky.textextraction.logger.LibLogger;
import com.samsung.android.app.sdk.deepsky.textextraction.ocrwrapper.OcrDataConverter;
import com.samsung.android.app.sdk.deepsky.textextraction.result.OcrData;
import com.samsung.android.app.sdk.deepsky.textextraction.translate.ImageTranslateListener;
import com.samsung.android.app.sdk.deepsky.textextraction.translate.TextTranslator;
import com.samsung.android.app.sdk.deepsky.textextraction.translate.data.ImageTranslationData;
import com.samsung.android.app.sdk.deepsky.textextraction.translate.data.ImageTranslationResult;
import com.samsung.android.app.sdk.deepsky.textextraction.util.Rune;
import com.samsung.android.app.sdk.deepsky.textextraction.util.SingleThreadCoroutineSwitcher;
import com.samsung.android.imagetranslation.LttEngineClient;
import com.samsung.android.imagetranslation.data.LttOcrResult;
import com.samsung.android.imagetranslation.inpainting.InpainterParam;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import me.i;
import me.x;
import ne.C1194l;
import ne.C1196n;
import vd.d;
import y3.a;

@Metadata(d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b \u0018\u0000 82\u00020\u0001:\u00018B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0017\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\bH\u0002¢\u0006\u0004\b\u000b\u0010\fJ'\u0010\u0012\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u000fH\u0002¢\u0006\u0004\b\u0012\u0010\u0013J\u000f\u0010\u0014\u001a\u00020\nH\u0002¢\u0006\u0004\b\u0014\u0010\u0015J\u000f\u0010\u0016\u001a\u00020\nH\u0002¢\u0006\u0004\b\u0016\u0010\u0015J\u000f\u0010\u0017\u001a\u00020\nH\u0002¢\u0006\u0004\b\u0017\u0010\u0015J\u000f\u0010\u0018\u001a\u00020\nH\u0002¢\u0006\u0004\b\u0018\u0010\u0015J\u000f\u0010\u0019\u001a\u00020\nH\u0002¢\u0006\u0004\b\u0019\u0010\u0015J\u001f\u0010\u001e\u001a\u00020\n2\u0006\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u001d\u001a\u00020\u001cH\u0016¢\u0006\u0004\b\u001e\u0010\u001fJ\u0017\u0010 \u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\rH\u0016¢\u0006\u0004\b \u0010!J\u000f\u0010#\u001a\u00020\"H\u0004¢\u0006\u0004\b#\u0010$J\u000f\u0010&\u001a\u00020%H\u0016¢\u0006\u0004\b&\u0010'J/\u0010(\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u000fH&¢\u0006\u0004\b(\u0010)J5\u0010-\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\b2\f\u0010,\u001a\b\u0012\u0004\u0012\u00020+0*2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u000fH&¢\u0006\u0004\b-\u0010.R\u001a\u0010\u0003\u001a\u00020\u00028\u0004X\u0004¢\u0006\f\n\u0004\b\u0003\u0010/\u001a\u0004\b0\u00101R\u001c\u00104\u001a\b\u0012\u0004\u0012\u000203028\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b4\u00105R\u001c\u00107\u001a\b\u0012\u0004\u0012\u000206028\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b7\u00105¨\u00069"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/translate/engine/LttImageTranslationEngine;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/translate/engine/ImageTranslationEngine;", "Landroid/content/Context;", "context", "Lcom/samsung/android/app/sdk/deepsky/textextraction/translate/TextTranslator;", "textTranslator", "<init>", "(Landroid/content/Context;Lcom/samsung/android/app/sdk/deepsky/textextraction/translate/TextTranslator;)V", "Lcom/samsung/android/imagetranslation/LttEngineClient;", "lttEngineClient", "Lme/x;", "initImageTranslationData", "(Lcom/samsung/android/imagetranslation/LttEngineClient;)V", "Lcom/samsung/android/app/sdk/deepsky/textextraction/translate/ImageTranslateListener;", "listener", "Ljava/util/concurrent/CountDownLatch;", "inPaintCountDownLatch", "renderCountDownLatch", "createLttEngineClient", "(Lcom/samsung/android/app/sdk/deepsky/textextraction/translate/ImageTranslateListener;Ljava/util/concurrent/CountDownLatch;Ljava/util/concurrent/CountDownLatch;)Lcom/samsung/android/imagetranslation/LttEngineClient;", "trimSkippingCases", "()V", "trimSameSourceAndTargetLanguage", "trimInvalidSourceLanguage", "trimUnsupportedSourceLanguage", "trimOnDeviceTranslationUnavailable", "Landroid/graphics/Bitmap;", "bitmap", "Lcom/samsung/android/app/sdk/deepsky/textextraction/result/OcrData;", "ocrData", "init", "(Landroid/graphics/Bitmap;Lcom/samsung/android/app/sdk/deepsky/textextraction/result/OcrData;)V", "doImageTranslation", "(Lcom/samsung/android/app/sdk/deepsky/textextraction/translate/ImageTranslateListener;)V", "Lcom/samsung/android/imagetranslation/data/LttOcrResult;", "getLttOcrResult", "()Lcom/samsung/android/imagetranslation/data/LttOcrResult;", "", "isLangPackDownloadNeeded", "()Z", "initLttEngineClient", "(Lcom/samsung/android/imagetranslation/LttEngineClient;Lcom/samsung/android/app/sdk/deepsky/textextraction/translate/ImageTranslateListener;Ljava/util/concurrent/CountDownLatch;Ljava/util/concurrent/CountDownLatch;)V", "", "", "translatedTextList", "renderTranslatedImage", "(Lcom/samsung/android/imagetranslation/LttEngineClient;Ljava/util/List;Lcom/samsung/android/app/sdk/deepsky/textextraction/translate/ImageTranslateListener;Ljava/util/concurrent/CountDownLatch;)V", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "", "Lcom/samsung/android/app/sdk/deepsky/textextraction/translate/data/ImageTranslationData;", "imageTranslationDataList", "Ljava/util/List;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/result/OcrData$BlockInfo;", "targetBlockInfoList", "Companion", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class LttImageTranslationEngine extends ImageTranslationEngine {
    public static final Companion Companion = new Companion((e) null);
    private final Context context;
    private List<ImageTranslationData> imageTranslationDataList = new ArrayList();
    private List<OcrData.BlockInfo> targetBlockInfoList = new ArrayList();

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/translate/engine/LttImageTranslationEngine$Companion;", "", "<init>", "()V", "TAG", "", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public LttImageTranslationEngine(Context context2, TextTranslator textTranslator) {
        super(textTranslator);
        j.e(context2, "context");
        j.e(textTranslator, "textTranslator");
        this.context = context2;
    }

    private final LttEngineClient createLttEngineClient(ImageTranslateListener imageTranslateListener, CountDownLatch countDownLatch, CountDownLatch countDownLatch2) {
        LttEngineClient createInstance = LttEngineClient.createInstance();
        j.b(createInstance);
        initImageTranslationData(createInstance);
        initLttEngineClient(createInstance, imageTranslateListener, countDownLatch, countDownLatch2);
        return createInstance;
    }

    /* access modifiers changed from: private */
    public static final x doImageTranslation$lambda$3(LttImageTranslationEngine lttImageTranslationEngine, ImageTranslateListener imageTranslateListener, x xVar) {
        LttImageTranslationEngine lttImageTranslationEngine2 = lttImageTranslationEngine;
        ImageTranslateListener imageTranslateListener2 = imageTranslateListener;
        j.e(xVar, "it");
        lttImageTranslationEngine2.setLastTranslatedText("");
        CountDownLatch countDownLatch = new CountDownLatch(1);
        CountDownLatch countDownLatch2 = new CountDownLatch(1);
        LttEngineClient createLttEngineClient = lttImageTranslationEngine2.createLttEngineClient(imageTranslateListener2, countDownLatch, countDownLatch2);
        lttImageTranslationEngine2.getTextTranslator().initSourceLangInfo(lttImageTranslationEngine2.imageTranslationDataList);
        boolean isLangPackDownloadNeeded = lttImageTranslationEngine2.isLangPackDownloadNeeded();
        x xVar2 = x.f4917a;
        if (isLangPackDownloadNeeded) {
            LibLogger.w("LttImageTranslationEngine", "Lang pack download needed");
            TextTranslator.showLangPackDownloadDialog$default(lttImageTranslationEngine2.getTextTranslator(), (List) null, 1, (Object) null);
            SingleThreadCoroutineSwitcher.Chain.start$default(SingleThreadCoroutineSwitcher.INSTANCE.newChain().onMain(new a(imageTranslateListener2, 0)), (b) null, (b) null, 3, (Object) null);
            createLttEngineClient.release();
            return xVar2;
        }
        LibLogger.i("LttImageTranslationEngine", "LangPack Done, Translate starts");
        lttImageTranslationEngine2.trimSkippingCases();
        if (lttImageTranslationEngine2.targetBlockInfoList.isEmpty()) {
            SingleThreadCoroutineSwitcher.Chain.start$default(SingleThreadCoroutineSwitcher.INSTANCE.newChain().onMain(new a(imageTranslateListener2, 1)), (b) null, (b) null, 3, (Object) null);
            createLttEngineClient.release();
            return xVar2;
        }
        if (Rune.INSTANCE.isSupportImageInPainting()) {
            LibLogger.i("LttImageTranslationEngine", "LttEngineClient - starts image in-painting");
            createLttEngineClient.inPaintImage(0, new InpainterParam(0, lttImageTranslationEngine2.getOriginalBitmap(), lttImageTranslationEngine2.getLttOcrResult()));
        }
        TextTranslator.translateAll$default(lttImageTranslationEngine2.getTextTranslator(), 0, lttImageTranslationEngine2.imageTranslationDataList, false, 5, (Object) null);
        Iterable<ImageTranslationData> iterable = lttImageTranslationEngine2.imageTranslationDataList;
        ArrayList arrayList = new ArrayList(C1196n.w0(iterable, 10));
        for (ImageTranslationData targetText : iterable) {
            arrayList.add(targetText.getTargetText());
        }
        List k12 = C1194l.k1(arrayList);
        lttImageTranslationEngine2.setLastTranslatedText(C1194l.R0(k12, "\n", (String) null, (String) null, (b) null, 62));
        if (Rune.INSTANCE.isSupportImageInPainting()) {
            LibLogger.i("LttImageTranslationEngine", "Waits for finishing inPainting");
            countDownLatch.await();
        }
        LibLogger.i("LttImageTranslationEngine", "Translate Done, Overlay starts");
        lttImageTranslationEngine2.renderTranslatedImage(createLttEngineClient, k12, imageTranslateListener2, countDownLatch2);
        countDownLatch2.await();
        return xVar2;
    }

    /* access modifiers changed from: private */
    public static final x doImageTranslation$lambda$3$lambda$0(ImageTranslateListener imageTranslateListener, x xVar) {
        j.e(xVar, "it");
        imageTranslateListener.onImageTranslateFail(new ImageTranslationResult.Error.RequestLanguagePackDownload(0, 1, (e) null));
        return x.f4917a;
    }

    /* access modifiers changed from: private */
    public static final x doImageTranslation$lambda$3$lambda$1(ImageTranslateListener imageTranslateListener, x xVar) {
        j.e(xVar, "it");
        LibLogger.w("LttImageTranslationEngine", "targetBlockInfoList empty after trimming");
        imageTranslateListener.onImageTranslateFail(new ImageTranslationResult.Error.EmptyBlockList(0, 1, (e) null));
        return x.f4917a;
    }

    /* access modifiers changed from: private */
    public static final x doImageTranslation$lambda$4(x xVar) {
        LibLogger.i("LttImageTranslationEngine", "imageTranslateTask done successfully");
        return x.f4917a;
    }

    /* access modifiers changed from: private */
    public static final x doImageTranslation$lambda$5(ImageTranslateListener imageTranslateListener, Throwable th) {
        j.e(th, "it");
        LibLogger.e("LttImageTranslationEngine", "image translation failed", th);
        imageTranslateListener.onImageTranslateFail(new ImageTranslationResult.Error.Unknown(0, 1, (e) null));
        return x.f4917a;
    }

    private final void initImageTranslationData(LttEngineClient lttEngineClient) {
        ArrayList arrayList;
        List<OcrData.BlockInfo> blockList;
        this.imageTranslationDataList = new ArrayList();
        OcrData ocrData = getOcrData();
        if (ocrData == null || (blockList = ocrData.getBlockList()) == null) {
            arrayList = new ArrayList();
        } else {
            arrayList = C1194l.m1(blockList);
        }
        this.targetBlockInfoList = arrayList;
        List<String> resultsWithLineBreak = lttEngineClient.getResultsWithLineBreak(this.context, getLttOcrResult());
        j.d(resultsWithLineBreak, "getResultsWithLineBreak(...)");
        Iterable iterable = this.targetBlockInfoList;
        Iterator it = iterable.iterator();
        Iterable iterable2 = resultsWithLineBreak;
        Iterator it2 = iterable2.iterator();
        ArrayList arrayList2 = new ArrayList(Math.min(C1196n.w0(iterable, 10), C1196n.w0(iterable2, 10)));
        while (it.hasNext() && it2.hasNext()) {
            Object next = it.next();
            arrayList2.add(Boolean.valueOf(this.imageTranslationDataList.add(new ImageTranslationData(((OcrData.BlockInfo) next).getPoly(), (String) it2.next(), "", "", ""))));
        }
    }

    private final void trimInvalidSourceLanguage() {
        int size = this.imageTranslationDataList.size();
        ArrayList r1 = C1194l.r1(this.targetBlockInfoList, this.imageTranslationDataList);
        ArrayList arrayList = new ArrayList();
        Iterator it = r1.iterator();
        while (it.hasNext()) {
            Object next = it.next();
            if (LanguageUtil.INSTANCE.isNormalLanguage(((ImageTranslationData) ((i) next).e).getSourceLang())) {
                arrayList.add(next);
            }
        }
        i y0 = C1196n.y0(arrayList);
        this.targetBlockInfoList = C1194l.m1((Collection) y0.d);
        ArrayList m12 = C1194l.m1((Collection) y0.e);
        this.imageTranslationDataList = m12;
        LibLogger.i("LttImageTranslationEngine", A.a.d(size - m12.size(), this.imageTranslationDataList.size(), "trimInvalidSourceLanguage: ", " results trimmed, ", " left"));
    }

    private final void trimOnDeviceTranslationUnavailable() {
        int size = this.imageTranslationDataList.size();
        if (isAutoTranslationMode()) {
            ArrayList r1 = C1194l.r1(this.targetBlockInfoList, this.imageTranslationDataList);
            ArrayList arrayList = new ArrayList();
            Iterator it = r1.iterator();
            while (it.hasNext()) {
                Object next = it.next();
                ImageTranslationData imageTranslationData = (ImageTranslationData) ((i) next).e;
                if (getTextTranslator().isOnDeviceTranslationAvailable(imageTranslationData.getSourceLang(), imageTranslationData.getTargetLang())) {
                    arrayList.add(next);
                }
            }
            i y0 = C1196n.y0(arrayList);
            this.targetBlockInfoList = C1194l.m1((Collection) y0.d);
            this.imageTranslationDataList = C1194l.m1((Collection) y0.e);
        }
        LibLogger.i("LttImageTranslationEngine", A.a.d(size - this.imageTranslationDataList.size(), this.imageTranslationDataList.size(), "trimOnDeviceTranslationUnavailable: ", " results trimmed, ", " left"));
    }

    private final void trimSameSourceAndTargetLanguage() {
        int size = this.imageTranslationDataList.size();
        if (isAutoTranslationMode()) {
            ArrayList r1 = C1194l.r1(this.targetBlockInfoList, this.imageTranslationDataList);
            ArrayList arrayList = new ArrayList();
            Iterator it = r1.iterator();
            while (it.hasNext()) {
                Object next = it.next();
                ImageTranslationData imageTranslationData = (ImageTranslationData) ((i) next).e;
                if (!j.a(imageTranslationData.getSourceLang(), imageTranslationData.getTargetLang())) {
                    arrayList.add(next);
                }
            }
            i y0 = C1196n.y0(arrayList);
            this.targetBlockInfoList = C1194l.m1((Collection) y0.d);
            this.imageTranslationDataList = C1194l.m1((Collection) y0.e);
        }
        LibLogger.i("LttImageTranslationEngine", A.a.d(size - this.imageTranslationDataList.size(), this.imageTranslationDataList.size(), "trimSameSourceAndTargetLanguage: ", " results trimmed, ", " left"));
    }

    private final void trimSkippingCases() {
        trimSameSourceAndTargetLanguage();
        trimInvalidSourceLanguage();
        trimUnsupportedSourceLanguage();
        trimOnDeviceTranslationUnavailable();
    }

    private final void trimUnsupportedSourceLanguage() {
        int size = this.imageTranslationDataList.size();
        if (!getSupportedSourceLangList().isEmpty()) {
            ArrayList r1 = C1194l.r1(this.targetBlockInfoList, this.imageTranslationDataList);
            ArrayList arrayList = new ArrayList();
            Iterator it = r1.iterator();
            while (it.hasNext()) {
                Object next = it.next();
                if (getSupportedSourceLangList().contains(((ImageTranslationData) ((i) next).e).getSourceLang())) {
                    arrayList.add(next);
                }
            }
            i y0 = C1196n.y0(arrayList);
            this.targetBlockInfoList = C1194l.m1((Collection) y0.d);
            this.imageTranslationDataList = C1194l.m1((Collection) y0.e);
        }
        LibLogger.i("LttImageTranslationEngine", A.a.d(size - this.imageTranslationDataList.size(), this.imageTranslationDataList.size(), "trimUnsupportedSourceLanguage: ", " results trimmed, ", " left"));
    }

    public void doImageTranslation(ImageTranslateListener imageTranslateListener) {
        j.e(imageTranslateListener, "listener");
        if (isProcessing()) {
            LibLogger.w("LttImageTranslationEngine", "The previous translate job wasn't complete");
        } else if (getRenderedBitmap() != null) {
            LibLogger.w("LttImageTranslationEngine", "The same rendered bitmap exists");
            imageTranslateListener.onImageTranslateSuccess();
        } else {
            setImageTranslateTask(SingleThreadCoroutineSwitcher.INSTANCE.newChain().onBackground(new c(16, this, imageTranslateListener)).start(new d(9), new a(imageTranslateListener, 2)));
        }
    }

    public final Context getContext() {
        return this.context;
    }

    public final LttOcrResult getLttOcrResult() {
        Rect rect;
        OcrDataConverter ocrDataConverter = OcrDataConverter.INSTANCE;
        List<OcrData.BlockInfo> list = this.targetBlockInfoList;
        OcrData ocrData = getOcrData();
        if (ocrData == null || (rect = ocrData.getValidRect()) == null) {
            rect = new Rect();
        }
        return ocrDataConverter.toLttOcrResult(new OcrData((String) null, list, rect, 1, (e) null));
    }

    public void init(Bitmap bitmap, OcrData ocrData) {
        j.e(bitmap, "bitmap");
        j.e(ocrData, "ocrData");
        super.init(bitmap, ocrData);
        this.targetBlockInfoList = C1194l.m1(ocrData.getBlockList());
        this.imageTranslationDataList = new ArrayList();
        setRenderedBitmap((Bitmap) null);
    }

    public abstract void initLttEngineClient(LttEngineClient lttEngineClient, ImageTranslateListener imageTranslateListener, CountDownLatch countDownLatch, CountDownLatch countDownLatch2);

    public boolean isLangPackDownloadNeeded() {
        if (!getNeedToDownloadLangPack() || !getTextTranslator().isLangPackDownloadNeeded()) {
            return false;
        }
        return true;
    }

    public abstract void renderTranslatedImage(LttEngineClient lttEngineClient, List<String> list, ImageTranslateListener imageTranslateListener, CountDownLatch countDownLatch);
}
