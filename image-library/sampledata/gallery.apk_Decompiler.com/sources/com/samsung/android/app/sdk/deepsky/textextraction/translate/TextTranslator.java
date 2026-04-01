package com.samsung.android.app.sdk.deepsky.textextraction.translate;

import A.a;
import Ae.b;
import Vf.D;
import android.content.Context;
import c0.C0086a;
import com.samsung.android.app.sdk.deepsky.textextraction.languagepack.LangPackManager;
import com.samsung.android.app.sdk.deepsky.textextraction.languagepack.LanguageUtil;
import com.samsung.android.app.sdk.deepsky.textextraction.languagepack.OnDeviceLanguage;
import com.samsung.android.app.sdk.deepsky.textextraction.logger.LibLogger;
import com.samsung.android.app.sdk.deepsky.textextraction.translate.data.ImageTranslationData;
import com.samsung.android.sdk.scs.ai.translation.NeuralTranslationErrorCode;
import com.samsung.android.sdk.scs.ai.translation.NeuralTranslationRequest;
import com.samsung.android.sdk.scs.ai.translation.NeuralTranslationResult;
import com.samsung.android.sdk.scs.ai.translation.NeuralTranslationSource;
import com.samsung.android.sdk.scs.ai.translation.NeuralTranslator;
import com.samsung.android.sivs.ai.sdkcommon.translation.LanguageDirection;
import com.samsung.android.sum.core.functional.g;
import com.samsung.android.sum.core.types.NumericEnum;
import i.C0212a;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import me.i;
import me.x;
import n4.C0491c;
import ne.C1194l;
import ne.C1196n;
import ne.C1202t;
import qe.C1227c;
import qe.C1233i;

@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\b\n\u0002\b\u001b\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\u0007\n\u0002\b\b\b\u0000\u0018\u0000 ]2\u00020\u0001:\u0001]B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u001b\u0010\f\u001a\u00020\u000b2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\b¢\u0006\u0004\b\f\u0010\rJ\r\u0010\u000e\u001a\u00020\u000b¢\u0006\u0004\b\u000e\u0010\u000fJ\u001b\u0010\u0013\u001a\u00020\u00122\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00100\b¢\u0006\u0004\b\u0013\u0010\u0014J'\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00160\u00152\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00100\b¢\u0006\u0004\b\u0017\u0010\u0018J/\u0010\u001c\u001a\u00020\u000b2\b\b\u0002\u0010\u0019\u001a\u00020\u00162\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\t0\b2\b\b\u0002\u0010\u001b\u001a\u00020\u0012¢\u0006\u0004\b\u001c\u0010\u001dJ5\u0010\"\u001a\u00020\u00102\u0006\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u001e\u001a\u00020\u00162\u0006\u0010\u001f\u001a\u00020\u00102\u0006\u0010 \u001a\u00020\u00102\u0006\u0010!\u001a\u00020\u0010¢\u0006\u0004\b\"\u0010#J\u001d\u0010$\u001a\u00020\u00122\u0006\u0010 \u001a\u00020\u00102\u0006\u0010!\u001a\u00020\u0010¢\u0006\u0004\b$\u0010%J\r\u0010&\u001a\u00020\u0012¢\u0006\u0004\b&\u0010'J\u001f\u0010)\u001a\u00020\u000b2\u0010\b\u0002\u0010(\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\b¢\u0006\u0004\b)\u0010\rJ\u0015\u0010+\u001a\u00020\u00102\u0006\u0010*\u001a\u00020\u0010¢\u0006\u0004\b+\u0010,J+\u0010.\u001a\u00020\u00122\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00100\b2\f\u0010-\u001a\b\u0012\u0004\u0012\u00020\u00100\bH\u0002¢\u0006\u0004\b.\u0010/J\u000f\u00100\u001a\u00020\u0012H\u0002¢\u0006\u0004\b0\u0010'J+\u00101\u001a\u00020\u00122\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00100\b2\f\u0010-\u001a\b\u0012\u0004\u0012\u00020\u00100\bH\u0002¢\u0006\u0004\b1\u0010/J9\u00103\u001a\u0010\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u0016\u0018\u0001022\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00100\b2\f\u0010-\u001a\b\u0012\u0004\u0012\u00020\u00100\bH\u0002¢\u0006\u0004\b3\u00104J7\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00160\u00152\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00100\b2\f\u0010-\u001a\b\u0012\u0004\u0012\u00020\u00100\bH\u0002¢\u0006\u0004\b\u0017\u00105J\u0017\u00107\u001a\u00020\u00122\u0006\u00106\u001a\u00020\u0010H\u0002¢\u0006\u0004\b7\u00108J7\u0010;\u001a\u00020\u00102\u0006\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u001e\u001a\u00020\u00162\u0006\u0010 \u001a\u00020\u00102\u0006\u00109\u001a\u00020\u00102\u0006\u0010:\u001a\u00020\u0010H\u0002¢\u0006\u0004\b;\u0010#J7\u0010<\u001a\u00020\u00102\u0006\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u001e\u001a\u00020\u00162\u0006\u0010\u001f\u001a\u00020\u00102\u0006\u0010 \u001a\u00020\u00102\u0006\u0010!\u001a\u00020\u0010H\u0002¢\u0006\u0004\b<\u0010#J\u0017\u0010>\u001a\u00020\u00102\u0006\u0010=\u001a\u00020\u0010H\u0002¢\u0006\u0004\b>\u0010,J#\u0010?\u001a\b\u0012\u0004\u0012\u00020\u00100\b2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00100\bH\u0002¢\u0006\u0004\b?\u0010@R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010AR\u0016\u0010C\u001a\u00020B8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\bC\u0010DR\u001c\u0010E\u001a\b\u0012\u0004\u0012\u00020\u00100\b8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\bE\u0010FR\"\u0010G\u001a\u00020\u00108\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\bG\u0010H\u001a\u0004\bI\u0010J\"\u0004\bK\u0010LR\"\u0010M\u001a\u00020\u00108\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\bM\u0010H\u001a\u0004\bN\u0010J\"\u0004\bO\u0010LR\"\u0010P\u001a\u00020\u00168\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\bP\u0010Q\u001a\u0004\bR\u0010S\"\u0004\bT\u0010UR\"\u0010W\u001a\u00020V8\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\bW\u0010X\u001a\u0004\bY\u0010Z\"\u0004\b[\u0010\\¨\u0006^"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/translate/TextTranslator;", "", "Landroid/content/Context;", "context", "Lcom/samsung/android/app/sdk/deepsky/textextraction/languagepack/LangPackManager;", "langPackManager", "<init>", "(Landroid/content/Context;Lcom/samsung/android/app/sdk/deepsky/textextraction/languagepack/LangPackManager;)V", "", "Lcom/samsung/android/app/sdk/deepsky/textextraction/translate/data/ImageTranslationData;", "resultList", "Lme/x;", "initSourceLangInfo", "(Ljava/util/List;)V", "close", "()V", "", "sourceTextList", "", "isTranslationNeeded", "(Ljava/util/List;)Z", "", "", "getDetectedLanguageAndCount", "(Ljava/util/List;)Ljava/util/Map;", "requestId", "dataList", "isConcurrentMode", "translateAll", "(ILjava/util/List;Z)V", "index", "sourceText", "sourceLang", "targetLang", "translate", "(IILjava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;", "isOnDeviceTranslationAvailable", "(Ljava/lang/String;Ljava/lang/String;)Z", "isLangPackDownloadNeeded", "()Z", "requestLangPackList", "showLangPackDownloadDialog", "onDeviceLangCode", "convertOnDeviceLangCodeToDisplayLangCode", "(Ljava/lang/String;)Ljava/lang/String;", "detectedLangList", "isSourceLanguageTranslationNeeded", "(Ljava/util/List;Ljava/util/List;)Z", "canSupportTranslateToTargetLanguage", "canSupportTranslateToSourceLanguage", "Lme/i;", "getMostDetectedLanguageAndCount", "(Ljava/util/List;Ljava/util/List;)Lme/i;", "(Ljava/util/List;Ljava/util/List;)Ljava/util/Map;", "detectedLang", "isSkippableDetectedLanguage", "(Ljava/lang/String;)Z", "destLang", "text", "translateText", "translateOnDevice", "language", "getAvailableOnDeviceLangCode", "detectLanguage", "(Ljava/util/List;)Ljava/util/List;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/languagepack/LangPackManager;", "Lcom/samsung/android/sdk/scs/ai/translation/NeuralTranslator;", "translator", "Lcom/samsung/android/sdk/scs/ai/translation/NeuralTranslator;", "languagePackRequestLanguageList", "Ljava/util/List;", "sourceLanguage", "Ljava/lang/String;", "getSourceLanguage", "()Ljava/lang/String;", "setSourceLanguage", "(Ljava/lang/String;)V", "targetLanguage", "getTargetLanguage", "setTargetLanguage", "minCharCountForTranslation", "I", "getMinCharCountForTranslation", "()I", "setMinCharCountForTranslation", "(I)V", "", "foreignLanguageRatioThreshold", "F", "getForeignLanguageRatioThreshold", "()F", "setForeignLanguageRatioThreshold", "(F)V", "Companion", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class TextTranslator {
    public static final Companion Companion = new Companion((e) null);
    private float foreignLanguageRatioThreshold = 0.1f;
    private final LangPackManager langPackManager;
    /* access modifiers changed from: private */
    public List<String> languagePackRequestLanguageList = C1202t.d;
    private int minCharCountForTranslation;
    private String sourceLanguage = "Auto";
    private String targetLanguage = LanguageUtil.INSTANCE.createTargetLanguage();
    /* access modifiers changed from: private */
    public NeuralTranslator translator;

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tXT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/translate/TextTranslator$Companion;", "", "<init>", "()V", "TAG", "", "DEFAULT_MIN_CHARACTER_COUNT_FOR_TRANSLATION", "", "DEFAULT_THRESHOLD_RATIO_FOR_TRANSLATION", "", "SOURCE_ID", "MAX_SOURCE_ID", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    public TextTranslator(Context context, LangPackManager langPackManager2) {
        j.e(context, "context");
        j.e(langPackManager2, "langPackManager");
        this.langPackManager = langPackManager2;
        this.translator = new NeuralTranslator(context.getApplicationContext());
    }

    private final boolean canSupportTranslateToSourceLanguage(List<String> list, List<String> list2) {
        String str;
        int i2;
        i mostDetectedLanguageAndCount = getMostDetectedLanguageAndCount(list, list2);
        if (mostDetectedLanguageAndCount == null || (str = (String) mostDetectedLanguageAndCount.d) == null) {
            str = "";
        }
        if (mostDetectedLanguageAndCount != null) {
            i2 = ((Number) mostDetectedLanguageAndCount.e).intValue();
        } else {
            i2 = 0;
        }
        boolean checkSupportOnDevice = this.langPackManager.checkSupportOnDevice(str);
        LibLogger.i("TextTranslator", "canSupportTranslateToSourceLanguage: " + checkSupportOnDevice + " (" + str + NumericEnum.SEP + i2 + ")");
        return checkSupportOnDevice;
    }

    private final boolean canSupportTranslateToTargetLanguage() {
        boolean checkSupportOnDevice = this.langPackManager.checkSupportOnDevice(this.targetLanguage);
        String str = this.targetLanguage;
        LibLogger.i("TextTranslator", "canSupportTranslateToTargetLanguage: " + checkSupportOnDevice + " (" + str + ")");
        return checkSupportOnDevice;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v1, resolved type: me.j} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v9, resolved type: java.util.ArrayList} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v11, resolved type: me.j} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v12, resolved type: me.j} */
    /* access modifiers changed from: private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.List<java.lang.String> detectLanguage(java.util.List<java.lang.String> r3) {
        /*
            r2 = this;
            com.samsung.android.sdk.scs.ai.translation.NeuralTranslator r2 = r2.translator     // Catch:{ all -> 0x0042 }
            java.lang.String r0 = "null cannot be cast to non-null type java.util.ArrayList<kotlin.String>"
            kotlin.jvm.internal.j.c(r3, r0)     // Catch:{ all -> 0x0042 }
            java.util.ArrayList r3 = (java.util.ArrayList) r3     // Catch:{ all -> 0x0042 }
            java.lang.String r0 = "Unknown"
            com.samsung.android.sdk.scs.base.tasks.Task r2 = r2.identifyLanguageWithList(r3, r0)     // Catch:{ all -> 0x0042 }
            java.lang.Object r2 = com.samsung.android.sdk.scs.base.tasks.Tasks.await(r2)     // Catch:{ all -> 0x0042 }
            java.lang.String r3 = "await(...)"
            kotlin.jvm.internal.j.d(r2, r3)     // Catch:{ all -> 0x0042 }
            java.lang.Iterable r2 = (java.lang.Iterable) r2     // Catch:{ all -> 0x0042 }
            java.util.ArrayList r3 = new java.util.ArrayList     // Catch:{ all -> 0x0042 }
            r0 = 10
            int r0 = ne.C1196n.w0(r2, r0)     // Catch:{ all -> 0x0042 }
            r3.<init>(r0)     // Catch:{ all -> 0x0042 }
            java.util.Iterator r2 = r2.iterator()     // Catch:{ all -> 0x0042 }
        L_0x0029:
            boolean r0 = r2.hasNext()     // Catch:{ all -> 0x0042 }
            if (r0 == 0) goto L_0x0047
            java.lang.Object r0 = r2.next()     // Catch:{ all -> 0x0042 }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ all -> 0x0042 }
            com.samsung.android.app.sdk.deepsky.textextraction.languagepack.LanguageUtil r1 = com.samsung.android.app.sdk.deepsky.textextraction.languagepack.LanguageUtil.INSTANCE     // Catch:{ all -> 0x0042 }
            kotlin.jvm.internal.j.b(r0)     // Catch:{ all -> 0x0042 }
            java.lang.String r0 = r1.createSourceLanguage(r0)     // Catch:{ all -> 0x0042 }
            r3.add(r0)     // Catch:{ all -> 0x0042 }
            goto L_0x0029
        L_0x0042:
            r2 = move-exception
            me.j r3 = L2.a.l(r2)
        L_0x0047:
            java.lang.Throwable r2 = me.k.a(r3)
            if (r2 == 0) goto L_0x0054
            java.lang.String r0 = "TextTranslator"
            java.lang.String r1 = "detectLanguage failed"
            com.samsung.android.app.sdk.deepsky.textextraction.logger.LibLogger.e(r0, r1, r2)
        L_0x0054:
            boolean r2 = r3 instanceof me.j
            if (r2 == 0) goto L_0x0059
            r3 = 0
        L_0x0059:
            java.util.List r3 = (java.util.List) r3
            if (r3 != 0) goto L_0x005f
            ne.t r3 = ne.C1202t.d
        L_0x005f:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.app.sdk.deepsky.textextraction.translate.TextTranslator.detectLanguage(java.util.List):java.util.List");
    }

    private final String getAvailableOnDeviceLangCode(String str) {
        Object obj;
        String code;
        List<OnDeviceLanguage> installedOnDeviceLangList = this.langPackManager.getInstalledOnDeviceLangList(str);
        if (installedOnDeviceLangList.isEmpty()) {
            return str;
        }
        Iterator it = installedOnDeviceLangList.iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            if (j.a(((OnDeviceLanguage) obj).getCode(), str)) {
                break;
            }
        }
        OnDeviceLanguage onDeviceLanguage = (OnDeviceLanguage) obj;
        if (onDeviceLanguage == null || (code = onDeviceLanguage.getCode()) == null) {
            return ((OnDeviceLanguage) C1194l.L0(installedOnDeviceLangList)).getCode();
        }
        return code;
    }

    /* access modifiers changed from: private */
    public final i getMostDetectedLanguageAndCount(List<String> list, List<String> list2) {
        T t;
        Iterator<T> it = getDetectedLanguageAndCount(list, list2).entrySet().iterator();
        if (!it.hasNext()) {
            t = null;
        } else {
            t = it.next();
            if (it.hasNext()) {
                int intValue = ((Number) ((Map.Entry) t).getValue()).intValue();
                do {
                    T next = it.next();
                    int intValue2 = ((Number) ((Map.Entry) next).getValue()).intValue();
                    if (intValue < intValue2) {
                        t = next;
                        intValue = intValue2;
                    }
                } while (it.hasNext());
            }
        }
        Map.Entry entry = (Map.Entry) t;
        if (entry != null) {
            return new i(entry.getKey(), entry.getValue());
        }
        return null;
    }

    private final boolean isSkippableDetectedLanguage(String str) {
        if (!LanguageUtil.INSTANCE.isNormalLanguage(str) || j.a(str, this.targetLanguage)) {
            return true;
        }
        return false;
    }

    private final boolean isSourceLanguageTranslationNeeded(List<String> list, List<String> list2) {
        Iterable iterable = list;
        int length = C1194l.R0(iterable, "", (String) null, (String) null, (b) null, 62).length();
        LibLogger.i("TextTranslator", a.d(length, this.minCharCountForTranslation, "(sourceTextLength/minCharCount)(", "/", ")"));
        if (length >= this.minCharCountForTranslation) {
            String str = this.targetLanguage;
            Iterator it = iterable.iterator();
            Iterable iterable2 = list2;
            Iterator it2 = iterable2.iterator();
            ArrayList arrayList = new ArrayList(Math.min(C1196n.w0(iterable, 10), C1196n.w0(iterable2, 10)));
            int i2 = 0;
            int i7 = 0;
            while (it.hasNext() && it2.hasNext()) {
                Object next = it.next();
                String str2 = (String) it2.next();
                String str3 = (String) next;
                if (this.langPackManager.checkSupportOnDevice(str2) && LanguageUtil.INSTANCE.isNormalLanguage(str2)) {
                    i2 += str3.length();
                    if (!j.a(str2, str)) {
                        i7 += str3.length();
                    }
                }
                arrayList.add(x.f4917a);
            }
            float f = this.foreignLanguageRatioThreshold;
            StringBuilder u = C0212a.u("sl=", str, ", st=", i2, ", ft=");
            u.append(i7);
            u.append(" (");
            u.append(f);
            u.append(")");
            LibLogger.i("TextTranslator", u.toString());
            if (((float) i7) >= ((float) i2) * this.foreignLanguageRatioThreshold) {
                return true;
            }
        }
        return false;
    }

    public static /* synthetic */ void showLangPackDownloadDialog$default(TextTranslator textTranslator, List list, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            list = null;
        }
        textTranslator.showLangPackDownloadDialog(list);
    }

    public static /* synthetic */ void translateAll$default(TextTranslator textTranslator, int i2, List list, boolean z, int i7, Object obj) {
        if ((i7 & 1) != 0) {
            i2 = 0;
        }
        if ((i7 & 4) != 0) {
            z = false;
        }
        textTranslator.translateAll(i2, list, z);
    }

    private final String translateOnDevice(int i2, int i7, String str, String str2, String str3) {
        System.currentTimeMillis();
        return translateText(i2, i7, getAvailableOnDeviceLangCode(str2), getAvailableOnDeviceLangCode(str3), str);
    }

    private final String translateText(int i2, int i7, String str, String str2, String str3) {
        StringBuilder sb2 = new StringBuilder();
        CountDownLatch countDownLatch = new CountDownLatch(1);
        String str4 = i2 + "DeepSky" + (i7 % 3);
        LibLogger.d("TextTranslator", "translateText with index(" + i7 + ") sourceId(" + str4 + ")");
        try {
            this.translator.translate(NeuralTranslationRequest.builder().source(NeuralTranslationSource.builder(str3, str, str2).requestingSourceId(str4).verbose(Boolean.TRUE).build()).onSuccess(new C0491c(20, sb2, countDownLatch)).onFailure(new g(sb2, str3, countDownLatch, 12)).build());
            countDownLatch.await();
            String sb3 = sb2.toString();
            j.d(sb3, "toString(...)");
            return sb3;
        } catch (Exception e) {
            e.printStackTrace();
            LibLogger.w("TextTranslator", "translateText failed");
            return str3;
        }
    }

    /* access modifiers changed from: private */
    public static final void translateText$lambda$4(StringBuilder sb2, CountDownLatch countDownLatch, NeuralTranslationResult neuralTranslationResult) {
        sb2.append(neuralTranslationResult.getTargetText());
        countDownLatch.countDown();
    }

    /* access modifiers changed from: private */
    public static final void translateText$lambda$5(StringBuilder sb2, String str, CountDownLatch countDownLatch, NeuralTranslationErrorCode neuralTranslationErrorCode) {
        LibLogger.e("TextTranslator", "translation failed (ErrorCode=" + neuralTranslationErrorCode + ")");
        sb2.append(str);
        countDownLatch.countDown();
    }

    public final void close() {
        this.translator.close();
    }

    public final String convertOnDeviceLangCodeToDisplayLangCode(String str) {
        j.e(str, "onDeviceLangCode");
        return this.langPackManager.convertOnDeviceLangCodeToDisplayLangCode(getAvailableOnDeviceLangCode(str));
    }

    public final Map<String, Integer> getDetectedLanguageAndCount(List<String> list) {
        j.e(list, "sourceTextList");
        return getDetectedLanguageAndCount(list, detectLanguage(list));
    }

    public final String getSourceLanguage() {
        return this.sourceLanguage;
    }

    public final String getTargetLanguage() {
        return this.targetLanguage;
    }

    public final void initSourceLangInfo(List<ImageTranslationData> list) {
        j.e(list, "resultList");
        Object unused = D.r(C1233i.d, new TextTranslator$initSourceLangInfo$1(this, list, (C1227c) null));
    }

    public final boolean isLangPackDownloadNeeded() {
        return this.langPackManager.isLangPackDownloadNeeded(this.languagePackRequestLanguageList);
    }

    public final boolean isOnDeviceTranslationAvailable(String str, String str2) {
        j.e(str, "sourceLang");
        j.e(str2, "targetLang");
        String availableOnDeviceLangCode = getAvailableOnDeviceLangCode(str);
        String availableOnDeviceLangCode2 = getAvailableOnDeviceLangCode(str2);
        boolean isAvailableDirection = this.translator.isAvailableDirection(new LanguageDirection(availableOnDeviceLangCode, availableOnDeviceLangCode2));
        StringBuilder q = C0086a.q("isOnDeviceTranslationAvailable from ", availableOnDeviceLangCode, " to ", availableOnDeviceLangCode2, ": ");
        q.append(isAvailableDirection);
        LibLogger.i("TextTranslator", q.toString());
        return isAvailableDirection;
    }

    public final boolean isTranslationNeeded(List<String> list) {
        j.e(list, "sourceTextList");
        List<String> detectLanguage = detectLanguage(list);
        if (!isSourceLanguageTranslationNeeded(list, detectLanguage) || !canSupportTranslateToTargetLanguage() || !canSupportTranslateToSourceLanguage(list, detectLanguage)) {
            return false;
        }
        return true;
    }

    public final void setSourceLanguage(String str) {
        j.e(str, "<set-?>");
        this.sourceLanguage = str;
    }

    public final void setTargetLanguage(String str) {
        j.e(str, "<set-?>");
        this.targetLanguage = str;
    }

    public final void showLangPackDownloadDialog(List<String> list) {
        if (list == null) {
            list = this.languagePackRequestLanguageList;
        }
        this.langPackManager.requestLangPackDownload(list);
    }

    public final String translate(int i2, int i7, String str, String str2, String str3) {
        j.e(str, "sourceText");
        j.e(str2, "sourceLang");
        j.e(str3, "targetLang");
        int length = str.length();
        LibLogger.i("TextTranslator", "[" + i7 + "] translate from source[" + str2 + "] to target[" + str3 + "] (" + length + ")");
        if (isOnDeviceTranslationAvailable(str2, str3)) {
            return translateOnDevice(i2, i7, str, str2, str3);
        }
        LibLogger.e("TextTranslator", "[" + i7 + "] OnDeviceOnly - lang pack not available " + str2 + " -> " + str3);
        return str;
    }

    public final void translateAll(int i2, List<ImageTranslationData> list, boolean z) {
        j.e(list, "dataList");
        Object unused = D.r(C1233i.d, new TextTranslator$translateAll$1(this, i2, list, z, (C1227c) null));
    }

    private final Map<String, Integer> getDetectedLanguageAndCount(List<String> list, List<String> list2) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        Iterable iterable = list;
        Iterator it = iterable.iterator();
        Iterable iterable2 = list2;
        Iterator it2 = iterable2.iterator();
        ArrayList arrayList = new ArrayList(Math.min(C1196n.w0(iterable, 10), C1196n.w0(iterable2, 10)));
        while (it.hasNext() && it2.hasNext()) {
            Object next = it.next();
            String str = (String) it2.next();
            String str2 = (String) next;
            if (!isSkippableDetectedLanguage(str) && this.langPackManager.checkSupportOnDevice(str)) {
                linkedHashMap.put(str, Integer.valueOf(str2.length() + ((Number) linkedHashMap.getOrDefault(str, 0)).intValue()));
            }
            arrayList.add(x.f4917a);
        }
        return linkedHashMap;
    }
}
