package com.samsung.android.app.sdk.deepsky.textextraction.translate;

import He.t;
import Wf.c;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.MotionEvent;
import bd.h;
import com.samsung.android.app.sdk.deepsky.textextraction.logger.LibLogger;
import com.samsung.android.app.sdk.deepsky.textextraction.result.OcrData;
import com.samsung.android.app.sdk.deepsky.textextraction.translate.engine.ImageTranslationEngine;
import com.samsung.android.app.sdk.deepsky.textextraction.translate.engine.ImageTranslationEngineFactory;
import com.samsung.android.app.sdk.deepsky.textextraction.util.SingleThreadCoroutineSwitcher;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import kotlin.jvm.internal.p;
import kotlin.jvm.internal.u;
import kotlin.jvm.internal.v;
import kotlin.jvm.internal.w;
import me.x;
import vd.d;
import w3.a;

@Metadata(d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u001a\b\u0000\u0018\u0000 V2\u00020\u0001:\u0001VB\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u001d\u0010\r\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\r\u0010\u000eJ\u0015\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u000f¢\u0006\u0004\b\u0011\u0010\u0012J\u0015\u0010\u0013\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u000f¢\u0006\u0004\b\u0013\u0010\u0012J\u0015\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\u0015\u0010\u0016J\u001f\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u000b\u001a\u00020\n2\b\u0010\t\u001a\u0004\u0018\u00010\b¢\u0006\u0004\b\u0015\u0010\u0017J\u0015\u0010\u001a\u001a\u00020\f2\u0006\u0010\u0019\u001a\u00020\u0018¢\u0006\u0004\b\u001a\u0010\u001bJ\r\u0010\u001c\u001a\u00020\u000f¢\u0006\u0004\b\u001c\u0010\u001dJ\u0013\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u000f0\u001e¢\u0006\u0004\b\u001f\u0010 J%\u0010'\u001a\u00020\u00142\u0006\u0010\"\u001a\u00020!2\u0006\u0010$\u001a\u00020#2\u0006\u0010&\u001a\u00020%¢\u0006\u0004\b'\u0010(J\u001d\u0010-\u001a\u00020\f2\u0006\u0010*\u001a\u00020)2\u0006\u0010,\u001a\u00020+¢\u0006\u0004\b-\u0010.J\r\u0010/\u001a\u00020\f¢\u0006\u0004\b/\u00100J1\u00106\u001a\u0004\u0018\u0001052\u0006\u00102\u001a\u0002012\u0006\u00103\u001a\u0002012\u0006\u0010$\u001a\u00020#2\u0006\u00104\u001a\u00020%H\u0002¢\u0006\u0004\b6\u00107J\u0017\u00109\u001a\u00020\f2\u0006\u00108\u001a\u000205H\u0002¢\u0006\u0004\b9\u0010:R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010;R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010<R\u0014\u0010>\u001a\u00020=8\u0002X\u0004¢\u0006\u0006\n\u0004\b>\u0010?R\u001d\u0010\u000b\u001a\u0004\u0018\u00010\n8BX\u0002¢\u0006\f\n\u0004\b@\u0010A\u001a\u0004\bB\u0010CR\u001d\u0010G\u001a\u0004\u0018\u00010\b8BX\u0002¢\u0006\f\n\u0004\bD\u0010A\u001a\u0004\bE\u0010FR\u001d\u0010J\u001a\u0004\u0018\u00010\b8FX\u0002¢\u0006\f\n\u0004\bH\u0010A\u001a\u0004\bI\u0010FR\u001b\u0010M\u001a\u00020\u000f8FX\u0002¢\u0006\f\n\u0004\bK\u0010A\u001a\u0004\bL\u0010\u001dR\"\u0010N\u001a\u00020\u00148\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\bN\u0010O\u001a\u0004\bN\u0010P\"\u0004\bQ\u0010RR\"\u0010S\u001a\u00020\u00148\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\bS\u0010O\u001a\u0004\bT\u0010P\"\u0004\bU\u0010R¨\u0006W"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/translate/ImageTranslator;", "", "Landroid/content/Context;", "context", "Lcom/samsung/android/app/sdk/deepsky/textextraction/translate/TextTranslator;", "textTranslator", "<init>", "(Landroid/content/Context;Lcom/samsung/android/app/sdk/deepsky/textextraction/translate/TextTranslator;)V", "Landroid/graphics/Bitmap;", "bitmap", "Lcom/samsung/android/app/sdk/deepsky/textextraction/result/OcrData;", "ocrData", "Lme/x;", "init", "(Landroid/graphics/Bitmap;Lcom/samsung/android/app/sdk/deepsky/textextraction/result/OcrData;)V", "", "language", "setSourceLanguage", "(Ljava/lang/String;)V", "setTargetLanguage", "", "isTranslationAvailable", "(Lcom/samsung/android/app/sdk/deepsky/textextraction/result/OcrData;)Z", "(Lcom/samsung/android/app/sdk/deepsky/textextraction/result/OcrData;Landroid/graphics/Bitmap;)Z", "Lcom/samsung/android/app/sdk/deepsky/textextraction/translate/ImageTranslateListener;", "listener", "doImageTranslate", "(Lcom/samsung/android/app/sdk/deepsky/textextraction/translate/ImageTranslateListener;)V", "getMostDetectedLanguage", "()Ljava/lang/String;", "", "getDetectedLanguageList", "()Ljava/util/List;", "Landroid/view/MotionEvent;", "event", "", "imageRatio", "Landroid/graphics/Point;", "center", "handleTouchEvent", "(Landroid/view/MotionEvent;FLandroid/graphics/Point;)Z", "Landroid/graphics/Canvas;", "canvas", "Landroid/graphics/RectF;", "bitmapRect", "drawTranslation", "(Landroid/graphics/Canvas;Landroid/graphics/RectF;)V", "removeCache", "()V", "", "x", "y", "centerOffset", "Lcom/samsung/android/app/sdk/deepsky/textextraction/result/OcrData$BlockInfo;", "findSelectedBlock", "(IIFLandroid/graphics/Point;)Lcom/samsung/android/app/sdk/deepsky/textextraction/result/OcrData$BlockInfo;", "block", "showTranslationDialog", "(Lcom/samsung/android/app/sdk/deepsky/textextraction/result/OcrData$BlockInfo;)V", "Landroid/content/Context;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/translate/TextTranslator;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/translate/engine/ImageTranslationEngine;", "imageTranslationEngine", "Lcom/samsung/android/app/sdk/deepsky/textextraction/translate/engine/ImageTranslationEngine;", "ocrData$delegate", "Lcom/samsung/android/app/sdk/deepsky/textextraction/translate/EngineProperty;", "getOcrData", "()Lcom/samsung/android/app/sdk/deepsky/textextraction/result/OcrData;", "originalBitmap$delegate", "getOriginalBitmap", "()Landroid/graphics/Bitmap;", "originalBitmap", "renderedBitmap$delegate", "getRenderedBitmap", "renderedBitmap", "lastTranslatedText$delegate", "getLastTranslatedText", "lastTranslatedText", "isLongPress", "Z", "()Z", "setLongPress", "(Z)V", "ignoreLastTouchUpEvent", "getIgnoreLastTouchUpEvent", "setIgnoreLastTouchUpEvent", "Companion", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ImageTranslator {
    static final /* synthetic */ t[] $$delegatedProperties;
    public static final Companion Companion = new Companion((e) null);
    private final Context context;
    private boolean ignoreLastTouchUpEvent;
    private final ImageTranslationEngine imageTranslationEngine;
    private boolean isLongPress;
    private final EngineProperty lastTranslatedText$delegate = new EngineProperty(new a(this, 3));
    private final EngineProperty ocrData$delegate = new EngineProperty(new a(this, 0));
    private final EngineProperty originalBitmap$delegate = new EngineProperty(new a(this, 1));
    private final EngineProperty renderedBitmap$delegate = new EngineProperty(new a(this, 2));
    private final TextTranslator textTranslator;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/translate/ImageTranslator$Companion;", "", "<init>", "()V", "TAG", "", "TRANSLATE_PACKAGE_NAME", "TRANSLATE_ACTIVITY", "KEY_SOURCE_LANG_CODE", "KEY_TARGET_LANG_CODE", "VALUE_SOURCE_LANG_CODE", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    static {
        p pVar = new p("ocrData", "getOcrData()Lcom/samsung/android/app/sdk/deepsky/textextraction/result/OcrData;");
        w wVar = v.f4727a;
        $$delegatedProperties = new t[]{wVar.f(pVar), wVar.f(new p("originalBitmap", "getOriginalBitmap()Landroid/graphics/Bitmap;")), wVar.f(new p("renderedBitmap", "getRenderedBitmap()Landroid/graphics/Bitmap;")), wVar.f(new p("lastTranslatedText", "getLastTranslatedText()Ljava/lang/String;"))};
    }

    public ImageTranslator(Context context2, TextTranslator textTranslator2) {
        j.e(context2, "context");
        j.e(textTranslator2, "textTranslator");
        this.context = context2;
        this.textTranslator = textTranslator2;
        this.imageTranslationEngine = ImageTranslationEngineFactory.INSTANCE.create(context2, textTranslator2);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v0, resolved type: com.samsung.android.app.sdk.deepsky.textextraction.result.OcrData$BlockInfo} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v1, resolved type: com.samsung.android.app.sdk.deepsky.textextraction.result.OcrData$BlockInfo} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v1, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v0, resolved type: com.samsung.android.app.sdk.deepsky.textextraction.result.OcrData$BlockInfo} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v3, resolved type: com.samsung.android.app.sdk.deepsky.textextraction.result.OcrData$BlockInfo} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final com.samsung.android.app.sdk.deepsky.textextraction.result.OcrData.BlockInfo findSelectedBlock(int r6, int r7, float r8, android.graphics.Point r9) {
        /*
            r5 = this;
            com.samsung.android.app.sdk.deepsky.textextraction.result.OcrData r5 = r5.getOcrData()
            r0 = 0
            if (r5 == 0) goto L_0x0038
            java.util.List r5 = r5.getBlockList()
            if (r5 == 0) goto L_0x0038
            java.lang.Iterable r5 = (java.lang.Iterable) r5
            java.util.Iterator r5 = r5.iterator()
        L_0x0013:
            boolean r1 = r5.hasNext()
            if (r1 == 0) goto L_0x0036
            java.lang.Object r1 = r5.next()
            r2 = r1
            com.samsung.android.app.sdk.deepsky.textextraction.result.OcrData$BlockInfo r2 = (com.samsung.android.app.sdk.deepsky.textextraction.result.OcrData.BlockInfo) r2
            com.samsung.android.app.sdk.deepsky.textextraction.util.PointUtil r3 = com.samsung.android.app.sdk.deepsky.textextraction.util.PointUtil.INSTANCE
            android.graphics.Point[] r2 = r2.getPoly()
            android.graphics.Point[] r2 = r3.getTransformedPoly(r2, r8, r9)
            android.graphics.Point r4 = new android.graphics.Point
            r4.<init>(r6, r7)
            boolean r2 = r3.isPointInsidePoly(r4, r2)
            if (r2 == 0) goto L_0x0013
            r0 = r1
        L_0x0036:
            com.samsung.android.app.sdk.deepsky.textextraction.result.OcrData$BlockInfo r0 = (com.samsung.android.app.sdk.deepsky.textextraction.result.OcrData.BlockInfo) r0
        L_0x0038:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.app.sdk.deepsky.textextraction.translate.ImageTranslator.findSelectedBlock(int, int, float, android.graphics.Point):com.samsung.android.app.sdk.deepsky.textextraction.result.OcrData$BlockInfo");
    }

    private final OcrData getOcrData() {
        return (OcrData) this.ocrData$delegate.getValue(this, $$delegatedProperties[0]);
    }

    private final Bitmap getOriginalBitmap() {
        return (Bitmap) this.originalBitmap$delegate.getValue(this, $$delegatedProperties[1]);
    }

    /* access modifiers changed from: private */
    public static final String lastTranslatedText_delegate$lambda$3(ImageTranslator imageTranslator) {
        return imageTranslator.imageTranslationEngine.getLastTranslatedText();
    }

    /* access modifiers changed from: private */
    public static final OcrData ocrData_delegate$lambda$0(ImageTranslator imageTranslator) {
        return imageTranslator.imageTranslationEngine.getOcrData();
    }

    /* access modifiers changed from: private */
    public static final Bitmap originalBitmap_delegate$lambda$1(ImageTranslator imageTranslator) {
        return imageTranslator.imageTranslationEngine.getOriginalBitmap();
    }

    /* access modifiers changed from: private */
    public static final Bitmap renderedBitmap_delegate$lambda$2(ImageTranslator imageTranslator) {
        return imageTranslator.imageTranslationEngine.getRenderedBitmap();
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [kotlin.jvm.internal.u, java.lang.Object] */
    private final void showTranslationDialog(OcrData.BlockInfo blockInfo) {
        ? obj = new Object();
        obj.d = "";
        SingleThreadCoroutineSwitcher.INSTANCE.newChain().onBackground(new c(15, obj, this)).start(new h(this, blockInfo, obj, 6), new d(4));
    }

    /* access modifiers changed from: private */
    public static final x showTranslationDialog$lambda$10(Throwable th) {
        j.e(th, "it");
        LibLogger.d("ImageTranslator", "lang pack convert fail");
        return x.f4917a;
    }

    /* access modifiers changed from: private */
    public static final x showTranslationDialog$lambda$7(u uVar, ImageTranslator imageTranslator, x xVar) {
        j.e(xVar, "it");
        TextTranslator textTranslator2 = imageTranslator.textTranslator;
        uVar.d = textTranslator2.convertOnDeviceLangCodeToDisplayLangCode(textTranslator2.getTargetLanguage());
        return x.f4917a;
    }

    /* access modifiers changed from: private */
    public static final x showTranslationDialog$lambda$9(ImageTranslator imageTranslator, OcrData.BlockInfo blockInfo, u uVar, x xVar) {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.samsung.android.app.interpreter", "com.samsung.android.app.interpreter.translation.view.TranslationActivity"));
        intent.putExtra("android.intent.extra.TEXT", blockInfo.toString());
        intent.putExtra("source_language_code", "auto");
        intent.putExtra("target_language_code", (String) uVar.d);
        imageTranslator.context.startActivity(intent);
        return x.f4917a;
    }

    public final void doImageTranslate(ImageTranslateListener imageTranslateListener) {
        j.e(imageTranslateListener, "listener");
        this.imageTranslationEngine.doImageTranslation(imageTranslateListener);
    }

    public final void drawTranslation(Canvas canvas, RectF rectF) {
        j.e(canvas, "canvas");
        j.e(rectF, "bitmapRect");
        if (getRenderedBitmap() == null) {
            LibLogger.w("ImageTranslator", "drawBackgroundBitmap called with renderedBitmap set to null");
            return;
        }
        Bitmap renderedBitmap = getRenderedBitmap();
        if (renderedBitmap != null) {
            canvas.drawBitmap(renderedBitmap, new Rect(0, 0, renderedBitmap.getWidth(), renderedBitmap.getHeight()), rectF, new Paint(1));
        }
    }

    public final List<String> getDetectedLanguageList() {
        return this.imageTranslationEngine.getDetectedLanguageList();
    }

    public final String getLastTranslatedText() {
        return (String) this.lastTranslatedText$delegate.getValue(this, $$delegatedProperties[3]);
    }

    public final String getMostDetectedLanguage() {
        return this.imageTranslationEngine.getMostDetectedLanguage();
    }

    public final Bitmap getRenderedBitmap() {
        return (Bitmap) this.renderedBitmap$delegate.getValue(this, $$delegatedProperties[2]);
    }

    public final boolean handleTouchEvent(MotionEvent motionEvent, float f, Point point) {
        j.e(motionEvent, "event");
        j.e(point, "center");
        int x9 = (int) (motionEvent.getX() + 0.5f);
        int y = (int) (motionEvent.getY() + 0.5f);
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 1 || actionMasked == 3) {
            if (this.isLongPress || this.ignoreLastTouchUpEvent) {
                this.ignoreLastTouchUpEvent = false;
                return false;
            }
            OcrData.BlockInfo findSelectedBlock = findSelectedBlock(x9, y, f, point);
            if (findSelectedBlock != null) {
                showTranslationDialog(findSelectedBlock);
                return true;
            }
        }
        return false;
    }

    public final void init(Bitmap bitmap, OcrData ocrData) {
        j.e(bitmap, "bitmap");
        j.e(ocrData, "ocrData");
        this.imageTranslationEngine.init(bitmap, ocrData);
    }

    public final boolean isLongPress() {
        return this.isLongPress;
    }

    public final boolean isTranslationAvailable(OcrData ocrData) {
        j.e(ocrData, "ocrData");
        return isTranslationAvailable(ocrData, getOriginalBitmap());
    }

    public final void removeCache() {
        this.imageTranslationEngine.removeCache();
    }

    public final void setIgnoreLastTouchUpEvent(boolean z) {
        this.ignoreLastTouchUpEvent = z;
    }

    public final void setLongPress(boolean z) {
        this.isLongPress = z;
    }

    public final void setSourceLanguage(String str) {
        j.e(str, "language");
        this.imageTranslationEngine.setSourceLanguage(str);
    }

    public final void setTargetLanguage(String str) {
        j.e(str, "language");
        this.imageTranslationEngine.setTargetLanguage(str);
    }

    public final boolean isTranslationAvailable(OcrData ocrData, Bitmap bitmap) {
        j.e(ocrData, "ocrData");
        return this.imageTranslationEngine.isTranslationAvailable(ocrData, bitmap);
    }
}
