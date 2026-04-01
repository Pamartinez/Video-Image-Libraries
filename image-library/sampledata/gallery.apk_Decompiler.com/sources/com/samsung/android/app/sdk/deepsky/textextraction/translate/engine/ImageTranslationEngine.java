package com.samsung.android.app.sdk.deepsky.textextraction.translate.engine;

import Vf.C0861b0;
import Vf.C0867e0;
import Vf.D;
import Vf.n0;
import android.graphics.Bitmap;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.app.sdk.deepsky.textextraction.logger.LibLogger;
import com.samsung.android.app.sdk.deepsky.textextraction.logger.LogUtil;
import com.samsung.android.app.sdk.deepsky.textextraction.result.OcrData;
import com.samsung.android.app.sdk.deepsky.textextraction.translate.ImageTranslateListener;
import com.samsung.android.app.sdk.deepsky.textextraction.translate.TextTranslator;
import com.samsung.android.app.sdk.deepsky.textextraction.util.Rune;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import ne.C1194l;
import ne.C1202t;
import ne.C1203u;
import qe.C1227c;
import qe.C1233i;

@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b%\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010$\n\u0002\u0010\b\n\u0002\b\u0004\b \u0018\u0000 P2\u00020\u0001:\u0001PB\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u001f\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\bH\u0016¢\u0006\u0004\b\u000b\u0010\fJ\u001f\u0010\u000e\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\u000e\u0010\u000fJ\u0015\u0010\u0012\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\u0010¢\u0006\u0004\b\u0012\u0010\u0013J\u0015\u0010\u0014\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\u0010¢\u0006\u0004\b\u0014\u0010\u0013J\r\u0010\u0015\u001a\u00020\r¢\u0006\u0004\b\u0015\u0010\u0016J\r\u0010\u0017\u001a\u00020\n¢\u0006\u0004\b\u0017\u0010\u0018J\r\u0010\u0019\u001a\u00020\u0010¢\u0006\u0004\b\u0019\u0010\u001aJ\u0013\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00100\u001b¢\u0006\u0004\b\u001c\u0010\u001dJ\u000f\u0010\u001e\u001a\u00020\rH\u0004¢\u0006\u0004\b\u001e\u0010\u0016J\u0017\u0010!\u001a\u00020\n2\u0006\u0010 \u001a\u00020\u001fH&¢\u0006\u0004\b!\u0010\"R\u001a\u0010\u0003\u001a\u00020\u00028\u0004X\u0004¢\u0006\f\n\u0004\b\u0003\u0010#\u001a\u0004\b$\u0010%R\"\u0010&\u001a\u00020\r8\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b&\u0010'\u001a\u0004\b(\u0010\u0016\"\u0004\b)\u0010*R\"\u0010+\u001a\u00020\r8\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b+\u0010'\u001a\u0004\b+\u0010\u0016\"\u0004\b,\u0010*R(\u0010-\u001a\b\u0012\u0004\u0012\u00020\u00100\u001b8\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b-\u0010.\u001a\u0004\b/\u0010\u001d\"\u0004\b0\u00101R.\u0010\t\u001a\u0004\u0018\u00010\b2\b\u00102\u001a\u0004\u0018\u00010\b8\u0006@DX\u000e¢\u0006\u0012\n\u0004\b\t\u00103\u001a\u0004\b4\u00105\"\u0004\b6\u00107R.\u00108\u001a\u0004\u0018\u00010\u00062\b\u00102\u001a\u0004\u0018\u00010\u00068\u0006@DX\u000e¢\u0006\u0012\n\u0004\b8\u00109\u001a\u0004\b:\u0010;\"\u0004\b<\u0010=R.\u0010>\u001a\u0004\u0018\u00010\u00062\b\u00102\u001a\u0004\u0018\u00010\u00068\u0006@DX\u000e¢\u0006\u0012\n\u0004\b>\u00109\u001a\u0004\b?\u0010;\"\u0004\b@\u0010=R*\u0010A\u001a\u00020\u00102\u0006\u00102\u001a\u00020\u00108\u0006@DX\u000e¢\u0006\u0012\n\u0004\bA\u0010B\u001a\u0004\bC\u0010\u001a\"\u0004\bD\u0010\u0013R$\u0010F\u001a\u0004\u0018\u00010E8\u0004@\u0004X\u000e¢\u0006\u0012\n\u0004\bF\u0010G\u001a\u0004\bH\u0010I\"\u0004\bJ\u0010KR\"\u0010N\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020M0L8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\bN\u0010O¨\u0006Q"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/translate/engine/ImageTranslationEngine;", "", "Lcom/samsung/android/app/sdk/deepsky/textextraction/translate/TextTranslator;", "textTranslator", "<init>", "(Lcom/samsung/android/app/sdk/deepsky/textextraction/translate/TextTranslator;)V", "Landroid/graphics/Bitmap;", "bitmap", "Lcom/samsung/android/app/sdk/deepsky/textextraction/result/OcrData;", "ocrData", "Lme/x;", "init", "(Landroid/graphics/Bitmap;Lcom/samsung/android/app/sdk/deepsky/textextraction/result/OcrData;)V", "", "isTranslationAvailable", "(Lcom/samsung/android/app/sdk/deepsky/textextraction/result/OcrData;Landroid/graphics/Bitmap;)Z", "", "language", "setSourceLanguage", "(Ljava/lang/String;)V", "setTargetLanguage", "isProcessing", "()Z", "removeCache", "()V", "getMostDetectedLanguage", "()Ljava/lang/String;", "", "getDetectedLanguageList", "()Ljava/util/List;", "isAutoTranslationMode", "Lcom/samsung/android/app/sdk/deepsky/textextraction/translate/ImageTranslateListener;", "listener", "doImageTranslation", "(Lcom/samsung/android/app/sdk/deepsky/textextraction/translate/ImageTranslateListener;)V", "Lcom/samsung/android/app/sdk/deepsky/textextraction/translate/TextTranslator;", "getTextTranslator", "()Lcom/samsung/android/app/sdk/deepsky/textextraction/translate/TextTranslator;", "needToDownloadLangPack", "Z", "getNeedToDownloadLangPack", "setNeedToDownloadLangPack", "(Z)V", "isCheckImageMinimumSize", "setCheckImageMinimumSize", "supportedSourceLangList", "Ljava/util/List;", "getSupportedSourceLangList", "setSupportedSourceLangList", "(Ljava/util/List;)V", "value", "Lcom/samsung/android/app/sdk/deepsky/textextraction/result/OcrData;", "getOcrData", "()Lcom/samsung/android/app/sdk/deepsky/textextraction/result/OcrData;", "setOcrData", "(Lcom/samsung/android/app/sdk/deepsky/textextraction/result/OcrData;)V", "originalBitmap", "Landroid/graphics/Bitmap;", "getOriginalBitmap", "()Landroid/graphics/Bitmap;", "setOriginalBitmap", "(Landroid/graphics/Bitmap;)V", "renderedBitmap", "getRenderedBitmap", "setRenderedBitmap", "lastTranslatedText", "Ljava/lang/String;", "getLastTranslatedText", "setLastTranslatedText", "LVf/e0;", "imageTranslateTask", "LVf/e0;", "getImageTranslateTask", "()LVf/e0;", "setImageTranslateTask", "(LVf/e0;)V", "", "", "detectedLangCountMap", "Ljava/util/Map;", "Companion", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class ImageTranslationEngine {
    public static final Companion Companion = new Companion((e) null);
    /* access modifiers changed from: private */
    public Map<String, Integer> detectedLangCountMap = C1203u.d;
    private C0867e0 imageTranslateTask;
    private boolean isCheckImageMinimumSize = true;
    private String lastTranslatedText = "";
    private boolean needToDownloadLangPack = true;
    private OcrData ocrData;
    private Bitmap originalBitmap;
    private Bitmap renderedBitmap;
    private List<String> supportedSourceLangList = C1202t.d;
    private final TextTranslator textTranslator;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/translate/engine/ImageTranslationEngine$Companion;", "", "<init>", "()V", "TAG", "", "MINIMUM_IMAGE_SIZE_FOR_TRANSLATION", "", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    public ImageTranslationEngine(TextTranslator textTranslator2) {
        j.e(textTranslator2, "textTranslator");
        this.textTranslator = textTranslator2;
    }

    public abstract void doImageTranslation(ImageTranslateListener imageTranslateListener);

    public final List<String> getDetectedLanguageList() {
        return C1194l.k1(this.detectedLangCountMap.keySet());
    }

    public final String getLastTranslatedText() {
        return this.lastTranslatedText;
    }

    public final String getMostDetectedLanguage() {
        T t;
        String str;
        Iterator<T> it = this.detectedLangCountMap.entrySet().iterator();
        if (!it.hasNext()) {
            t = null;
        } else {
            T next = it.next();
            if (!it.hasNext()) {
                t = next;
            } else {
                int intValue = ((Number) ((Map.Entry) next).getValue()).intValue();
                do {
                    T next2 = it.next();
                    int intValue2 = ((Number) ((Map.Entry) next2).getValue()).intValue();
                    if (intValue < intValue2) {
                        next = next2;
                        intValue = intValue2;
                    }
                } while (it.hasNext());
            }
            t = next;
        }
        Map.Entry entry = (Map.Entry) t;
        if (entry == null || (str = (String) entry.getKey()) == null) {
            return "";
        }
        return str;
    }

    public final boolean getNeedToDownloadLangPack() {
        return this.needToDownloadLangPack;
    }

    public final OcrData getOcrData() {
        return this.ocrData;
    }

    public final Bitmap getOriginalBitmap() {
        return this.originalBitmap;
    }

    public final Bitmap getRenderedBitmap() {
        return this.renderedBitmap;
    }

    public final List<String> getSupportedSourceLangList() {
        return this.supportedSourceLangList;
    }

    public final TextTranslator getTextTranslator() {
        return this.textTranslator;
    }

    public void init(Bitmap bitmap, OcrData ocrData2) {
        j.e(bitmap, "bitmap");
        j.e(ocrData2, "ocrData");
        String logString = LogUtil.INSTANCE.logString(bitmap);
        int size = ocrData2.getBlockList().size();
        LibLogger.i("ImageTranslationEngine", "initialize engine with " + logString + ArcCommonLog.TAG_COMMA + size + " blocks");
        this.originalBitmap = bitmap.copy(Bitmap.Config.ARGB_8888, false);
        this.ocrData = ocrData2;
        try {
            Object unused = D.r(C1233i.d, new ImageTranslationEngine$init$1(this, ocrData2, (C1227c) null));
        } catch (InterruptedException e) {
            String message = e.getMessage();
            LibLogger.w("ImageTranslationEngine", "InterruptedException in creating lang count map: " + message);
        } catch (Exception e7) {
            String message2 = e7.getMessage();
            LibLogger.w("ImageTranslationEngine", "Exception in creating lang count map: " + message2);
        }
    }

    public final boolean isAutoTranslationMode() {
        return j.a(this.textTranslator.getSourceLanguage(), "Auto");
    }

    public final boolean isProcessing() {
        C0867e0 e0Var = this.imageTranslateTask;
        if (e0Var == null) {
            return false;
        }
        return n0.d.get((n0) e0Var) instanceof C0861b0;
    }

    public final boolean isTranslationAvailable(OcrData ocrData2, Bitmap bitmap) {
        j.e(ocrData2, "ocrData");
        if (!Rune.INSTANCE.isSupportTranslation()) {
            LibLogger.w("ImageTranslationEngine", "[Available] Not support native AI feature");
            return false;
        } else if (ocrData2.getBlockList().isEmpty()) {
            LibLogger.w("ImageTranslationEngine", "[Available] OcrResult empty");
            return false;
        } else {
            if (bitmap != null) {
                int min = Math.min(bitmap.getWidth(), bitmap.getHeight());
                if (this.isCheckImageMinimumSize && min < 350) {
                    LibLogger.w("ImageTranslationEngine", "[Available] Image size too small");
                    return false;
                }
            }
            if (this.textTranslator.isTranslationNeeded(OcrData.toBlockStringList$deepsky_sdk_textextraction_8_5_30_release$default(ocrData2, (String) null, (String) null, (String) null, 7, (Object) null))) {
                return true;
            }
            LibLogger.i("ImageTranslationEngine", "[Available] Translation not needed");
            return false;
        }
    }

    public final void removeCache() {
        this.renderedBitmap = null;
    }

    public final void setImageTranslateTask(C0867e0 e0Var) {
        this.imageTranslateTask = e0Var;
    }

    public final void setLastTranslatedText(String str) {
        j.e(str, "<set-?>");
        this.lastTranslatedText = str;
    }

    public final void setRenderedBitmap(Bitmap bitmap) {
        this.renderedBitmap = bitmap;
    }

    public final void setSourceLanguage(String str) {
        j.e(str, "language");
        this.textTranslator.setSourceLanguage(str);
    }

    public final void setTargetLanguage(String str) {
        j.e(str, "language");
        this.textTranslator.setTargetLanguage(str);
    }
}
