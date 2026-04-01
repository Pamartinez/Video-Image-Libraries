package com.samsung.android.app.sdk.deepsky.textextraction.translate.engine;

import Vf.A;
import Vf.C;
import Vf.C0867e0;
import Vf.C0886x;
import Vf.D;
import Vf.M;
import Vf.g0;
import android.content.Context;
import android.graphics.Bitmap;
import com.samsung.android.app.sdk.deepsky.textextraction.languagepack.LanguageUtil;
import com.samsung.android.app.sdk.deepsky.textextraction.logger.LibLogger;
import com.samsung.android.app.sdk.deepsky.textextraction.logger.LogUtil;
import com.samsung.android.app.sdk.deepsky.textextraction.result.OcrData;
import com.samsung.android.app.sdk.deepsky.textextraction.translate.ImageTranslateListener;
import com.samsung.android.app.sdk.deepsky.textextraction.translate.TextTranslator;
import com.samsung.android.app.sdk.deepsky.textextraction.translate.data.ImageTranslationResult;
import com.samsung.android.vexfwk.imagetranslation.VexFwkLanguagePackInfo;
import com.samsung.android.vexfwk.param.VexFwkLanguageDirection;
import com.samsung.android.vexfwk.param.VexFwkOcrResult;
import com.samsung.android.vexfwk.sdk.imagetranslator.VexFwkImageTranslator;
import fg.h;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import ne.C1194l;
import ne.C1196n;
import qe.C1227c;
import vd.d;

@Metadata(d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u0000 ;2\u00020\u0001:\u0001;B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u001f\u0010\r\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\nH\u0016¢\u0006\u0004\b\r\u0010\u000eJ\u0017\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u0011\u0010\u0012J\u000f\u0010\u0014\u001a\u00020\u0013H\u0016¢\u0006\u0004\b\u0014\u0010\u0015J'\u0010\u001a\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u0010\u001a\u00020\u000fH\u0002¢\u0006\u0004\b\u001a\u0010\u001bJ0\u0010\u001c\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u000fH@¢\u0006\u0004\b\u001c\u0010\u001dJ6\u0010\"\u001a\u0010\u0012\f\u0012\n \u001f*\u0004\u0018\u00010\u00180\u00180\u001e2\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\nH@¢\u0006\u0004\b \u0010!J\u0017\u0010$\u001a\u00020\f2\u0006\u0010\u0019\u001a\u00020#H\u0002¢\u0006\u0004\b$\u0010%J\u000f\u0010&\u001a\u00020\u0013H\u0002¢\u0006\u0004\b&\u0010\u0015R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010'R\u0014\u0010)\u001a\u00020(8\u0002X\u0004¢\u0006\u0006\n\u0004\b)\u0010*R\u0014\u0010,\u001a\u00020+8\u0002X\u0004¢\u0006\u0006\n\u0004\b,\u0010-R\u001a\u00100\u001a\b\u0012\u0004\u0012\u00020/0.8\u0002X\u0004¢\u0006\u0006\n\u0004\b0\u00101R\u0014\u00103\u001a\u0002028\u0002X\u0004¢\u0006\u0006\n\u0004\b3\u00104R \u00107\u001a\u000e\u0012\u0004\u0012\u000206\u0012\u0004\u0012\u00020/058\u0002X\u0004¢\u0006\u0006\n\u0004\b7\u00108R \u0010:\u001a\u000e\u0012\u0004\u0012\u000206\u0012\u0004\u0012\u000209058\u0002X\u0004¢\u0006\u0006\n\u0004\b:\u00108¨\u0006<"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/translate/engine/VexImageTranslationEngine;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/translate/engine/ImageTranslationEngine;", "Landroid/content/Context;", "context", "Lcom/samsung/android/app/sdk/deepsky/textextraction/translate/TextTranslator;", "textTranslator", "<init>", "(Landroid/content/Context;Lcom/samsung/android/app/sdk/deepsky/textextraction/translate/TextTranslator;)V", "Landroid/graphics/Bitmap;", "bitmap", "Lcom/samsung/android/app/sdk/deepsky/textextraction/result/OcrData;", "ocrData", "Lme/x;", "init", "(Landroid/graphics/Bitmap;Lcom/samsung/android/app/sdk/deepsky/textextraction/result/OcrData;)V", "Lcom/samsung/android/app/sdk/deepsky/textextraction/translate/ImageTranslateListener;", "listener", "doImageTranslation", "(Lcom/samsung/android/app/sdk/deepsky/textextraction/translate/ImageTranslateListener;)V", "", "isLangPackDownloadNeeded", "()Z", "Lcom/samsung/android/vexfwk/sdk/imagetranslator/VexFwkImageTranslator;", "translator", "Lcom/samsung/android/vexfwk/sdk/imagetranslator/VexFwkImageTranslator$TranslationResult;", "result", "onImageTranslationComplete", "(Lcom/samsung/android/vexfwk/sdk/imagetranslator/VexFwkImageTranslator;Lcom/samsung/android/vexfwk/sdk/imagetranslator/VexFwkImageTranslator$TranslationResult;Lcom/samsung/android/app/sdk/deepsky/textextraction/translate/ImageTranslateListener;)V", "doImageTranslationInternal", "(Lcom/samsung/android/vexfwk/sdk/imagetranslator/VexFwkImageTranslator;Landroid/graphics/Bitmap;Lcom/samsung/android/app/sdk/deepsky/textextraction/result/OcrData;Lcom/samsung/android/app/sdk/deepsky/textextraction/translate/ImageTranslateListener;Lqe/c;)Ljava/lang/Object;", "Lme/k;", "kotlin.jvm.PlatformType", "translateImage-BWLJW6A", "(Lcom/samsung/android/vexfwk/sdk/imagetranslator/VexFwkImageTranslator;Landroid/graphics/Bitmap;Lcom/samsung/android/app/sdk/deepsky/textextraction/result/OcrData;Lqe/c;)Ljava/lang/Object;", "translateImage", "Lcom/samsung/android/vexfwk/sdk/imagetranslator/VexFwkImageTranslator$TranslationResult$Success;", "logSuccessResult", "(Lcom/samsung/android/vexfwk/sdk/imagetranslator/VexFwkImageTranslator$TranslationResult$Success;)V", "isTargetLanguageModified", "Landroid/content/Context;", "LVf/A;", "engineScope", "LVf/A;", "Lfg/e;", "translationSemaphore", "Lfg/e;", "", "", "translationRequestList", "Ljava/util/List;", "Ljava/util/concurrent/atomic/AtomicBoolean;", "isSessionCreated", "Ljava/util/concurrent/atomic/AtomicBoolean;", "", "", "sessionTranslationRequestMap", "Ljava/util/Map;", "LVf/e0;", "sessionTranslationTaskMap", "Companion", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class VexImageTranslationEngine extends ImageTranslationEngine {
    public static final Companion Companion = new Companion((e) null);
    private final Context context;
    private final A engineScope = D.a(M.f3843a.plus(new g0()));
    private final AtomicBoolean isSessionCreated;
    private final Map<Integer, Object> sessionTranslationRequestMap;
    private final Map<Integer, C0867e0> sessionTranslationTaskMap;
    private final List<Object> translationRequestList;
    private final fg.e translationSemaphore;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/translate/engine/VexImageTranslationEngine$Companion;", "", "<init>", "()V", "TAG", "", "MAX_CONCURRENT_TRANSLATION", "", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX WARNING: type inference failed for: r2v5, types: [fg.h, fg.e] */
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public VexImageTranslationEngine(Context context2, TextTranslator textTranslator) {
        super(textTranslator);
        j.e(context2, "context");
        j.e(textTranslator, "textTranslator");
        this.context = context2;
        int i2 = fg.j.f4377a;
        this.translationSemaphore = new h(4);
        this.translationRequestList = new ArrayList();
        this.isSessionCreated = new AtomicBoolean(false);
        this.sessionTranslationRequestMap = new LinkedHashMap();
        this.sessionTranslationTaskMap = new LinkedHashMap();
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v4, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v2, resolved type: com.samsung.android.app.sdk.deepsky.textextraction.translate.ImageTranslateListener} */
    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0053, code lost:
        if (r12 == r1) goto L_0x006e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x006c, code lost:
        if (Vf.D.w(r11, r4, r0) != r1) goto L_0x006f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x006e, code lost:
        return r1;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0046  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0022  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object doImageTranslationInternal(com.samsung.android.vexfwk.sdk.imagetranslator.VexFwkImageTranslator r11, android.graphics.Bitmap r12, com.samsung.android.app.sdk.deepsky.textextraction.result.OcrData r13, com.samsung.android.app.sdk.deepsky.textextraction.translate.ImageTranslateListener r14, qe.C1227c r15) {
        /*
            r10 = this;
            boolean r0 = r15 instanceof com.samsung.android.app.sdk.deepsky.textextraction.translate.engine.VexImageTranslationEngine$doImageTranslationInternal$1
            if (r0 == 0) goto L_0x0013
            r0 = r15
            com.samsung.android.app.sdk.deepsky.textextraction.translate.engine.VexImageTranslationEngine$doImageTranslationInternal$1 r0 = (com.samsung.android.app.sdk.deepsky.textextraction.translate.engine.VexImageTranslationEngine$doImageTranslationInternal$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            com.samsung.android.app.sdk.deepsky.textextraction.translate.engine.VexImageTranslationEngine$doImageTranslationInternal$1 r0 = new com.samsung.android.app.sdk.deepsky.textextraction.translate.engine.VexImageTranslationEngine$doImageTranslationInternal$1
            r0.<init>(r10, r15)
        L_0x0018:
            java.lang.Object r15 = r0.result
            re.a r1 = re.C1245a.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L_0x0046
            if (r2 == r4) goto L_0x0032
            if (r2 != r3) goto L_0x002a
            L2.a.A(r15)
            goto L_0x006f
        L_0x002a:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L_0x0032:
            java.lang.Object r11 = r0.L$1
            r14 = r11
            com.samsung.android.app.sdk.deepsky.textextraction.translate.ImageTranslateListener r14 = (com.samsung.android.app.sdk.deepsky.textextraction.translate.ImageTranslateListener) r14
            java.lang.Object r11 = r0.L$0
            com.samsung.android.vexfwk.sdk.imagetranslator.VexFwkImageTranslator r11 = (com.samsung.android.vexfwk.sdk.imagetranslator.VexFwkImageTranslator) r11
            L2.a.A(r15)
            me.k r15 = (me.k) r15
            java.lang.Object r12 = r15.d
        L_0x0042:
            r7 = r11
            r5 = r12
            r8 = r14
            goto L_0x0056
        L_0x0046:
            L2.a.A(r15)
            r0.L$0 = r11
            r0.L$1 = r14
            r0.label = r4
            java.lang.Object r12 = r10.m37translateImageBWLJW6A(r11, r12, r13, r0)
            if (r12 != r1) goto L_0x0042
            goto L_0x006e
        L_0x0056:
            eg.f r11 = Vf.M.f3843a
            Wf.e r11 = cg.n.f4030a
            com.samsung.android.app.sdk.deepsky.textextraction.translate.engine.VexImageTranslationEngine$doImageTranslationInternal$2 r4 = new com.samsung.android.app.sdk.deepsky.textextraction.translate.engine.VexImageTranslationEngine$doImageTranslationInternal$2
            r9 = 0
            r6 = r10
            r4.<init>(r5, r6, r7, r8, r9)
            r10 = 0
            r0.L$0 = r10
            r0.L$1 = r10
            r0.label = r3
            java.lang.Object r10 = Vf.D.w(r11, r4, r0)
            if (r10 != r1) goto L_0x006f
        L_0x006e:
            return r1
        L_0x006f:
            me.x r10 = me.x.f4917a
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.app.sdk.deepsky.textextraction.translate.engine.VexImageTranslationEngine.doImageTranslationInternal(com.samsung.android.vexfwk.sdk.imagetranslator.VexFwkImageTranslator, android.graphics.Bitmap, com.samsung.android.app.sdk.deepsky.textextraction.result.OcrData, com.samsung.android.app.sdk.deepsky.textextraction.translate.ImageTranslateListener, qe.c):java.lang.Object");
    }

    private final boolean isTargetLanguageModified() {
        return !j.a(getTextTranslator().getTargetLanguage(), LanguageUtil.INSTANCE.createTargetLanguage());
    }

    private final void logSuccessResult(VexFwkImageTranslator.TranslationResult.Success success) {
        for (VexFwkLanguageDirection vexFwkLanguageDirection : success.getTranslatedDirections()) {
            LibLogger.i("VexImageTranslationEngine", "translatedDirection: " + vexFwkLanguageDirection);
        }
        String logString = LogUtil.INSTANCE.logString(success.getImage());
        LibLogger.i("VexImageTranslationEngine", "VexFwkImageTranslator.translate success: " + logString);
    }

    /* access modifiers changed from: private */
    public final void onImageTranslationComplete(VexFwkImageTranslator vexFwkImageTranslator, VexFwkImageTranslator.TranslationResult translationResult, ImageTranslateListener imageTranslateListener) {
        String str;
        if (translationResult instanceof VexFwkImageTranslator.TranslationResult.Success) {
            VexFwkImageTranslator.TranslationResult.Success success = (VexFwkImageTranslator.TranslationResult.Success) translationResult;
            VexFwkOcrResult vexFwkOcrResult = success.getVexFwkOcrResult();
            if (vexFwkOcrResult == null || (str = C1194l.R0(vexFwkOcrResult.getBlockInfoList(), "\n", (String) null, (String) null, new d(10), 30)) == null) {
                str = "";
            }
            setLastTranslatedText(str);
            setRenderedBitmap(success.getImage());
            logSuccessResult(success);
            imageTranslateListener.onImageTranslateSuccess();
        } else if (translationResult instanceof VexFwkImageTranslator.TranslationResult.Error.InvalidDirection) {
            String message = ((VexFwkImageTranslator.TranslationResult.Error.InvalidDirection) translationResult).getMessage();
            LibLogger.e("VexImageTranslationEngine", "vexFwkImageTranslator failed: " + message);
            setRenderedBitmap(getOriginalBitmap());
            imageTranslateListener.onImageTranslateSuccess();
        } else if (translationResult instanceof VexFwkImageTranslator.TranslationResult.Error.LanguagePackRequired) {
            if (isLangPackDownloadNeeded()) {
                VexFwkImageTranslator.TranslationResult.Error.LanguagePackRequired languagePackRequired = (VexFwkImageTranslator.TranslationResult.Error.LanguagePackRequired) translationResult;
                Iterable<VexFwkLanguagePackInfo> requiredLanguages = languagePackRequired.getRequiredLanguages();
                ArrayList arrayList = new ArrayList(C1196n.w0(requiredLanguages, 10));
                for (VexFwkLanguagePackInfo languagePackCode : requiredLanguages) {
                    arrayList.add(languagePackCode.getLanguagePackCode());
                }
                LibLogger.i("VexImageTranslationEngine", "required languages: " + arrayList);
                vexFwkImageTranslator.showInstallPopup(this.context, languagePackRequired.getRequiredLanguages());
            }
            imageTranslateListener.onImageTranslateFail(new ImageTranslationResult.Error.RequestLanguagePackDownload(0, 1, (e) null));
        } else if (translationResult instanceof VexFwkImageTranslator.TranslationResult.Error.UnsupportedImageSize) {
            String message2 = ((VexFwkImageTranslator.TranslationResult.Error.UnsupportedImageSize) translationResult).getMessage();
            LibLogger.e("VexImageTranslationEngine", "vexFwkImageTranslator failed: " + message2);
            setRenderedBitmap(getOriginalBitmap());
            imageTranslateListener.onImageTranslateSuccess();
        } else if (translationResult instanceof VexFwkImageTranslator.TranslationResult.Error.Others) {
            String message3 = ((VexFwkImageTranslator.TranslationResult.Error.Others) translationResult).getMessage();
            LibLogger.e("VexImageTranslationEngine", "vexFwkImageTranslator failed: " + message3);
            setRenderedBitmap(getOriginalBitmap());
            imageTranslateListener.onImageTranslateSuccess();
        } else {
            throw new RuntimeException();
        }
    }

    /* access modifiers changed from: private */
    public static final CharSequence onImageTranslationComplete$lambda$1$lambda$0(VexFwkOcrResult.BlockInfo blockInfo) {
        j.e(blockInfo, "it");
        return blockInfo.getTranslatedText();
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0033  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* renamed from: translateImage-BWLJW6A  reason: not valid java name */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object m37translateImageBWLJW6A(com.samsung.android.vexfwk.sdk.imagetranslator.VexFwkImageTranslator r9, android.graphics.Bitmap r10, com.samsung.android.app.sdk.deepsky.textextraction.result.OcrData r11, qe.C1227c r12) {
        /*
            r8 = this;
            java.lang.String r0 = "translate with "
            boolean r1 = r12 instanceof com.samsung.android.app.sdk.deepsky.textextraction.translate.engine.VexImageTranslationEngine$translateImage$1
            if (r1 == 0) goto L_0x0016
            r1 = r12
            com.samsung.android.app.sdk.deepsky.textextraction.translate.engine.VexImageTranslationEngine$translateImage$1 r1 = (com.samsung.android.app.sdk.deepsky.textextraction.translate.engine.VexImageTranslationEngine$translateImage$1) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r4 = r2 & r3
            if (r4 == 0) goto L_0x0016
            int r2 = r2 - r3
            r1.label = r2
            goto L_0x001b
        L_0x0016:
            com.samsung.android.app.sdk.deepsky.textextraction.translate.engine.VexImageTranslationEngine$translateImage$1 r1 = new com.samsung.android.app.sdk.deepsky.textextraction.translate.engine.VexImageTranslationEngine$translateImage$1
            r1.<init>(r8, r12)
        L_0x001b:
            java.lang.Object r12 = r1.result
            re.a r2 = re.C1245a.COROUTINE_SUSPENDED
            int r3 = r1.label
            r4 = 1
            if (r3 == 0) goto L_0x0033
            if (r3 != r4) goto L_0x002b
            L2.a.A(r12)     // Catch:{ all -> 0x00ae }
            goto L_0x00ab
        L_0x002b:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L_0x0033:
            L2.a.A(r12)
            com.samsung.android.vexfwk.sdk.imagetranslator.VexFwkImageTranslator r12 = r9.configure()     // Catch:{ all -> 0x00ae }
            r12.awaitConfiguration()     // Catch:{ all -> 0x00ae }
            boolean r12 = r8.isAutoTranslationMode()     // Catch:{ all -> 0x00ae }
            r3 = 0
            if (r12 != 0) goto L_0x004d
            com.samsung.android.app.sdk.deepsky.textextraction.translate.TextTranslator r12 = r8.getTextTranslator()     // Catch:{ all -> 0x00ae }
            java.lang.String r12 = r12.getSourceLanguage()     // Catch:{ all -> 0x00ae }
            goto L_0x004e
        L_0x004d:
            r12 = r3
        L_0x004e:
            boolean r5 = r8.isTargetLanguageModified()     // Catch:{ all -> 0x00ae }
            if (r5 == 0) goto L_0x005c
            com.samsung.android.app.sdk.deepsky.textextraction.translate.TextTranslator r8 = r8.getTextTranslator()     // Catch:{ all -> 0x00ae }
            java.lang.String r3 = r8.getTargetLanguage()     // Catch:{ all -> 0x00ae }
        L_0x005c:
            com.samsung.android.app.sdk.deepsky.textextraction.ocrwrapper.OcrDataConverter r8 = com.samsung.android.app.sdk.deepsky.textextraction.ocrwrapper.OcrDataConverter.INSTANCE     // Catch:{ all -> 0x00ae }
            com.samsung.android.vexfwk.param.VexFwkOcrResultV2 r8 = r8.toVexFwkOcrResultV2(r11)     // Catch:{ all -> 0x00ae }
            java.util.List r11 = r8.getBlockInfoList()     // Catch:{ all -> 0x00ae }
            int r11 = r11.size()     // Catch:{ all -> 0x00ae }
            java.lang.String r5 = "VexImageTranslationEngine"
            com.samsung.android.app.sdk.deepsky.textextraction.logger.LogUtil r6 = com.samsung.android.app.sdk.deepsky.textextraction.logger.LogUtil.INSTANCE     // Catch:{ all -> 0x00ae }
            java.lang.String r6 = r6.logString(r10)     // Catch:{ all -> 0x00ae }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ all -> 0x00ae }
            r7.<init>(r0)     // Catch:{ all -> 0x00ae }
            r7.append(r6)     // Catch:{ all -> 0x00ae }
            java.lang.String r0 = " from:"
            r7.append(r0)     // Catch:{ all -> 0x00ae }
            r7.append(r12)     // Catch:{ all -> 0x00ae }
            java.lang.String r0 = " to:"
            r7.append(r0)     // Catch:{ all -> 0x00ae }
            r7.append(r3)     // Catch:{ all -> 0x00ae }
            java.lang.String r0 = " "
            r7.append(r0)     // Catch:{ all -> 0x00ae }
            r7.append(r11)     // Catch:{ all -> 0x00ae }
            java.lang.String r11 = " blocks"
            r7.append(r11)     // Catch:{ all -> 0x00ae }
            java.lang.String r11 = r7.toString()     // Catch:{ all -> 0x00ae }
            com.samsung.android.app.sdk.deepsky.textextraction.logger.LibLogger.i(r5, r11)     // Catch:{ all -> 0x00ae }
            java.util.concurrent.CompletableFuture r8 = r9.translate((android.graphics.Bitmap) r10, (java.lang.String) r12, (java.lang.String) r3, (com.samsung.android.vexfwk.param.VexFwkOcrResultV2) r8)     // Catch:{ all -> 0x00ae }
            r1.label = r4     // Catch:{ all -> 0x00ae }
            java.lang.Object r12 = o1.C0246a.J(r8, r1)     // Catch:{ all -> 0x00ae }
            if (r12 != r2) goto L_0x00ab
            return r2
        L_0x00ab:
            com.samsung.android.vexfwk.sdk.imagetranslator.VexFwkImageTranslator$TranslationResult r12 = (com.samsung.android.vexfwk.sdk.imagetranslator.VexFwkImageTranslator.TranslationResult) r12     // Catch:{ all -> 0x00ae }
            return r12
        L_0x00ae:
            r8 = move-exception
            me.j r8 = L2.a.l(r8)
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.app.sdk.deepsky.textextraction.translate.engine.VexImageTranslationEngine.m37translateImageBWLJW6A(com.samsung.android.vexfwk.sdk.imagetranslator.VexFwkImageTranslator, android.graphics.Bitmap, com.samsung.android.app.sdk.deepsky.textextraction.result.OcrData, qe.c):java.lang.Object");
    }

    public void doImageTranslation(ImageTranslateListener imageTranslateListener) {
        j.e(imageTranslateListener, "listener");
        if (isProcessing()) {
            LibLogger.w("VexImageTranslationEngine", "The previous translate job wasn't complete");
        } else if (getRenderedBitmap() != null) {
            LibLogger.w("VexImageTranslationEngine", "The same rendered bitmap exists");
            imageTranslateListener.onImageTranslateSuccess();
        } else {
            Bitmap originalBitmap = getOriginalBitmap();
            OcrData ocrData = getOcrData();
            if (originalBitmap == null || ocrData == null) {
                ImageTranslateListener imageTranslateListener2 = imageTranslateListener;
                LibLogger.e("VexImageTranslationEngine", "bitmap and ocrData are not set");
                imageTranslateListener2.onImageTranslateFail(new ImageTranslationResult.Error.Unknown(0, 1, (e) null));
                return;
            }
            setLastTranslatedText("");
            setImageTranslateTask(D.n(this.engineScope, (C0886x) null, (C) null, new VexImageTranslationEngine$doImageTranslation$1(this, originalBitmap, ocrData, imageTranslateListener, (C1227c) null), 3));
        }
    }

    public void init(Bitmap bitmap, OcrData ocrData) {
        j.e(bitmap, "bitmap");
        j.e(ocrData, "ocrData");
        super.init(bitmap, ocrData);
        setRenderedBitmap((Bitmap) null);
    }

    public boolean isLangPackDownloadNeeded() {
        return getNeedToDownloadLangPack();
    }
}
