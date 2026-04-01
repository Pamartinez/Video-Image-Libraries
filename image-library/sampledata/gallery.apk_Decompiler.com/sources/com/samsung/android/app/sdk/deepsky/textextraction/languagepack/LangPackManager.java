package com.samsung.android.app.sdk.deepsky.textextraction.languagepack;

import Tf.v;
import Vf.D;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.google.gson.GsonBuilder;
import com.samsung.android.app.sdk.deepsky.textextraction.R$string;
import com.samsung.android.app.sdk.deepsky.textextraction.languagepack.ScsLanguageInfo;
import com.samsung.android.app.sdk.deepsky.textextraction.logger.LibLogger;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import ne.C1194l;
import ne.C1195m;
import ne.C1196n;
import ne.C1202t;
import qe.C1227c;
import qe.C1233i;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u0000 12\u00020\u0001:\u00011B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u001d\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\u0003\u001a\u00020\u0002H\u0002¢\u0006\u0004\b\b\u0010\tJ\u001d\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\u000b\u001a\u00020\nH\u0002¢\u0006\u0004\b\f\u0010\rJ#\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\n0\u00062\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\n0\u0006H\u0002¢\u0006\u0004\b\u000f\u0010\u0010J\u001d\u0010\u0012\u001a\u00020\u00112\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\n0\u0006H\u0002¢\u0006\u0004\b\u0012\u0010\u0013J\u001d\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\u0014\u001a\u00020\nH\u0002¢\u0006\u0004\b\u0015\u0010\rJ\u0017\u0010\u0017\u001a\u00020\u00112\u0006\u0010\u0016\u001a\u00020\nH\u0002¢\u0006\u0004\b\u0017\u0010\u0018J\u0017\u0010\u0019\u001a\u00020\u00112\u0006\u0010\u0016\u001a\u00020\nH\u0002¢\u0006\u0004\b\u0019\u0010\u0018J\u0017\u0010\u001b\u001a\u00020\u00112\u0006\u0010\u001a\u001a\u00020\nH\u0002¢\u0006\u0004\b\u001b\u0010\u0018J\u0017\u0010\u001c\u001a\u00020\n2\u0006\u0010\u0016\u001a\u00020\nH\u0002¢\u0006\u0004\b\u001c\u0010\u001dJ\u0015\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0002¢\u0006\u0004\b\u001e\u0010\u001fJ\u000f\u0010!\u001a\u00020 H\u0007¢\u0006\u0004\b!\u0010\"J\u001b\u0010#\u001a\u00020\u00112\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\n0\u0006¢\u0006\u0004\b#\u0010\u0013J\u001b\u0010$\u001a\u00020 2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\n0\u0006¢\u0006\u0004\b$\u0010%J\u0015\u0010'\u001a\u00020\u00112\u0006\u0010&\u001a\u00020\n¢\u0006\u0004\b'\u0010\u0018J\u001b\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\u0016\u001a\u00020\n¢\u0006\u0004\b(\u0010\rJ\u0013\u0010)\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0004\b)\u0010\u001fJ\u0015\u0010*\u001a\u00020\n2\u0006\u0010\u0016\u001a\u00020\n¢\u0006\u0004\b*\u0010\u001dR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010+R\u001c\u0010,\u001a\b\u0012\u0004\u0012\u00020\u00070\u00068\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b,\u0010-R\u0014\u0010/\u001a\u00020.8\u0002X\u0004¢\u0006\u0006\n\u0004\b/\u00100¨\u00062"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/languagepack/LangPackManager;", "", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;)V", "", "Lcom/samsung/android/app/sdk/deepsky/textextraction/languagepack/OnDeviceLanguage;", "initTranslateSupportLangList", "(Landroid/content/Context;)Ljava/util/List;", "", "json", "initSupportLanguages", "(Ljava/lang/String;)Ljava/util/List;", "onDeviceLangCodes", "filterDownloadNeededLangCodes", "(Ljava/util/List;)Ljava/util/List;", "", "isUnsupportedLangPackExist", "(Ljava/util/List;)Z", "langCode", "getSupportOnDeviceLangList", "onDeviceLangCode", "isSupportedOnDeviceLang", "(Ljava/lang/String;)Z", "isInstalledOnDeviceLangPack", "packageName", "isInstalledPackage", "convertOnDeviceLangCodeToLangPackCode", "(Ljava/lang/String;)Ljava/lang/String;", "getDefaultLanguageList", "()Ljava/util/List;", "Lme/x;", "init", "()V", "isLangPackDownloadNeeded", "requestLangPackDownload", "(Ljava/util/List;)V", "localeLanguage", "checkSupportOnDevice", "getInstalledOnDeviceLangList", "getAllInstalledOnDeviceLangList", "convertOnDeviceLangCodeToDisplayLangCode", "Landroid/content/Context;", "translateSupportLangList", "Ljava/util/List;", "Ljava/util/concurrent/atomic/AtomicBoolean;", "isInitialized", "Ljava/util/concurrent/atomic/AtomicBoolean;", "Companion", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class LangPackManager {
    public static final Companion Companion = new Companion((e) null);
    private final Context context;
    private final AtomicBoolean isInitialized = new AtomicBoolean(false);
    private List<OnDeviceLanguage> translateSupportLangList = getDefaultLanguageList();

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/languagepack/LangPackManager$Companion;", "", "<init>", "()V", "TAG", "", "GET_TRANSLATE_SUPPORT_LANGUAGE_TIMEOUT_MILLISECONDS", "", "LANGUAGE_PACK_DOWNLOAD_ACTION", "LANGUAGE_PACK_SETTINGS_ACTION", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    public LangPackManager(Context context2) {
        j.e(context2, "context");
        this.context = context2;
    }

    private final String convertOnDeviceLangCodeToLangPackCode(String str) {
        List<OnDeviceLanguage> supportOnDeviceLangList = getSupportOnDeviceLangList(str);
        if (!supportOnDeviceLangList.isEmpty()) {
            OnDeviceLanguage onDeviceLanguage = (OnDeviceLanguage) C1194l.L0(supportOnDeviceLangList);
            String code = onDeviceLanguage.getCode();
            String langPackCode = onDeviceLanguage.getLangPackCode();
            LibLogger.d("LangPackManager", "convert from " + code + " to " + langPackCode);
            return onDeviceLanguage.getLangPackCode();
        }
        LibLogger.w("LangPackManager", "no matching LangPackCode for " + str);
        return "";
    }

    private final List<String> filterDownloadNeededLangCodes(List<String> list) {
        ArrayList arrayList = new ArrayList();
        for (Object next : list) {
            String str = (String) next;
            if (str.length() > 0 && !j.a(str, "en") && !isInstalledOnDeviceLangPack(str)) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }

    private final List<OnDeviceLanguage> getDefaultLanguageList() {
        return C1195m.q0(OnDeviceLanguage.Companion.getEN(), new OnDeviceLanguage("de", "de-DE", "de", (String) null, false, "com.samsung.android.nmt.apps.t2t.languagepack.ende", 24, (e) null), new OnDeviceLanguage("es", "es-ES", (String) null, (String) null, false, "com.samsung.android.nmt.apps.t2t.languagepack.enes", 28, (e) null), new OnDeviceLanguage("esmx", "es-MX", (String) null, (String) null, false, "com.samsung.android.nmt.apps.t2t.languagepack.enesmx", 28, (e) null), new OnDeviceLanguage("esus", "es-US", (String) null, (String) null, false, "com.samsung.android.nmt.apps.t2t.languagepack.enesus", 28, (e) null), new OnDeviceLanguage("fr", "fr-FR", "fr", (String) null, false, "com.samsung.android.nmt.apps.t2t.languagepack.enfr", 24, (e) null), new OnDeviceLanguage("frca", "fr-CA", "fr-CA", (String) null, false, "com.samsung.android.nmt.apps.t2t.languagepack.enfrca", 24, (e) null), new OnDeviceLanguage("hi", "hi-IN", "hi", (String) null, false, "com.samsung.android.nmt.apps.t2t.languagepack.enhi", 24, (e) null), new OnDeviceLanguage("id", "id-ID", "id", (String) null, false, "com.samsung.android.nmt.apps.t2t.languagepack.enid", 24, (e) null), new OnDeviceLanguage("it", "it-IT", "it", (String) null, false, "com.samsung.android.nmt.apps.t2t.languagepack.enit", 24, (e) null), new OnDeviceLanguage("ja", "ja-JP", "ja", (String) null, false, "com.samsung.android.nmt.apps.t2t.languagepack.enja", 24, (e) null), new OnDeviceLanguage("ko", "ko-KR", "ko", (String) null, false, "com.samsung.android.nmt.apps.t2t.languagepack.enko", 24, (e) null), new OnDeviceLanguage("pl", "pl-PL", "pl", (String) null, false, "com.samsung.android.nmt.apps.t2t.languagepack.enpl", 24, (e) null), new OnDeviceLanguage("ptbr", "pt-BR", "pt", (String) null, false, "com.samsung.android.nmt.apps.t2t.languagepack.enptbr", 24, (e) null), new OnDeviceLanguage("ru", "ru-RU", "ru", (String) null, false, "com.samsung.android.nmt.apps.t2t.languagepack.enru", 24, (e) null), new OnDeviceLanguage("th", "th-TH", "th", (String) null, false, "com.samsung.android.nmt.apps.t2t.languagepack.enth", 24, (e) null), new OnDeviceLanguage("vi", "vi-VN", "vi", (String) null, false, "com.samsung.android.nmt.apps.t2t.languagepack.envi", 24, (e) null), new OnDeviceLanguage("zh", "zh-CN", "zh", (String) null, false, "com.samsung.android.nmt.apps.t2t.languagepack.enzh", 24, (e) null), new OnDeviceLanguage("ar", "ar-AE", "ar", (String) null, false, "com.samsung.android.nmt.apps.t2t.languagepack.enar", 24, (e) null), new OnDeviceLanguage("zhhk", "zh-HK", "zh-HK", (String) null, false, "com.samsung.android.nmt.apps.t2t.languagepack.enzhhk", 24, (e) null));
    }

    private final List<OnDeviceLanguage> getSupportOnDeviceLangList(String str) {
        if (this.translateSupportLangList.isEmpty()) {
            LibLogger.w("LangPackManager", "translateSupportLangList is empty");
            return C1202t.d;
        }
        ArrayList arrayList = new ArrayList();
        if (v.t0(str, "zh")) {
            for (OnDeviceLanguage next : this.translateSupportLangList) {
                if (j.a(next.getCode(), str)) {
                    arrayList.add(next);
                }
            }
        } else if (str.length() >= 2) {
            String substring = str.substring(0, 2);
            j.d(substring, "substring(...)");
            for (OnDeviceLanguage next2 : this.translateSupportLangList) {
                if (v.t0(next2.getCode(), substring)) {
                    arrayList.add(next2);
                }
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: private */
    public final List<OnDeviceLanguage> initSupportLanguages(String str) {
        if (str.length() == 0) {
            LibLogger.w("LangPackManager", "SCS returns empty list, so use default list");
            return getDefaultLanguageList();
        }
        ArrayList arrayList = new ArrayList();
        for (Object next : ((ScsLanguageInfo) new GsonBuilder().create().fromJson(str, ScsLanguageInfo.class)).getLanguageInfo().getSupportLanguages()) {
            if (((ScsLanguageInfo.SupportLanguage) next).getSupportOnDevice()) {
                arrayList.add(next);
            }
        }
        HashSet hashSet = new HashSet();
        ArrayList<ScsLanguageInfo.SupportLanguage> arrayList2 = new ArrayList<>();
        for (Object next2 : arrayList) {
            if (hashSet.add(((ScsLanguageInfo.SupportLanguage) next2).getOnDeviceLangCode())) {
                arrayList2.add(next2);
            }
        }
        int i2 = 0;
        int i7 = 0;
        for (Object next3 : arrayList2) {
            int i8 = i7 + 1;
            if (i7 >= 0) {
                LibLogger.d("LangPackManager", "supportLanguages[SCS " + i7 + "]: " + ((ScsLanguageInfo.SupportLanguage) next3));
                i7 = i8;
            } else {
                C1195m.v0();
                throw null;
            }
        }
        ArrayList arrayList3 = new ArrayList(C1196n.w0(arrayList2, 10));
        for (ScsLanguageInfo.SupportLanguage supportLanguage : arrayList2) {
            arrayList3.add(new OnDeviceLanguage(supportLanguage.getOnDeviceLangCode(), supportLanguage.getLangPackCode(), supportLanguage.getDisplayLangCode(), (String) null, false, supportLanguage.getPackageName(), 24, (e) null));
        }
        LibLogger.i("LangPackManager", "translate support language size: " + arrayList3.size());
        for (Object next4 : arrayList3) {
            int i10 = i2 + 1;
            if (i2 >= 0) {
                LibLogger.i("LangPackManager", "supportLanguages[" + i2 + "]: " + ((OnDeviceLanguage) next4));
                i2 = i10;
            } else {
                C1195m.v0();
                throw null;
            }
        }
        return arrayList3;
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [kotlin.jvm.internal.u, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r1v0, types: [kotlin.jvm.internal.u, java.lang.Object] */
    private final List<OnDeviceLanguage> initTranslateSupportLangList(Context context2) {
        ? obj = new Object();
        obj.d = getDefaultLanguageList();
        Object unused = D.r(C1233i.d, new LangPackManager$initTranslateSupportLangList$1(new Object(), context2, obj, this, (C1227c) null));
        return (List) obj.d;
    }

    private final boolean isInstalledOnDeviceLangPack(String str) {
        Iterable<OnDeviceLanguage> supportOnDeviceLangList = getSupportOnDeviceLangList(str);
        ArrayList<String> arrayList = new ArrayList<>(C1196n.w0(supportOnDeviceLangList, 10));
        for (OnDeviceLanguage packageName : supportOnDeviceLangList) {
            arrayList.add(packageName.getPackageName());
        }
        if (arrayList.isEmpty()) {
            return false;
        }
        for (String isInstalledPackage : arrayList) {
            if (isInstalledPackage(isInstalledPackage)) {
                return true;
            }
        }
        return false;
    }

    private final boolean isInstalledPackage(String str) {
        try {
            this.context.getPackageManager().getPackageInfo(str, 0);
            LibLogger.d("LangPackManager", "isInstalledPackage already installed package: " + str);
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            LibLogger.d("LangPackManager", "isInstalledPackage not installed package: " + str);
            return false;
        }
    }

    private final boolean isSupportedOnDeviceLang(String str) {
        if (!getSupportOnDeviceLangList(str).isEmpty()) {
            return true;
        }
        LibLogger.w("LangPackManager", "no matching onDeviceLangCode for " + str);
        return false;
    }

    private final boolean isUnsupportedLangPackExist(List<String> list) {
        for (String next : list) {
            if (!isSupportedOnDeviceLang(next)) {
                LibLogger.d("LangPackManager", "unsupported : " + next);
                return true;
            }
        }
        return false;
    }

    public final boolean checkSupportOnDevice(String str) {
        j.e(str, "localeLanguage");
        boolean isSupportedOnDeviceLang = isSupportedOnDeviceLang(str);
        LibLogger.d("LangPackManager", "checkSupportOnDevice " + str + " " + isSupportedOnDeviceLang);
        return isSupportedOnDeviceLang;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v9, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v5, resolved type: com.samsung.android.app.sdk.deepsky.textextraction.languagepack.OnDeviceLanguage} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String convertOnDeviceLangCodeToDisplayLangCode(java.lang.String r5) {
        /*
            r4 = this;
            java.lang.String r0 = "onDeviceLangCode"
            kotlin.jvm.internal.j.e(r5, r0)
            java.util.List r4 = r4.getSupportOnDeviceLangList(r5)
            r0 = r4
            java.util.Collection r0 = (java.util.Collection) r0
            boolean r0 = r0.isEmpty()
            java.lang.String r1 = "LangPackManager"
            if (r0 != 0) goto L_0x0065
            r0 = r4
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            java.util.Iterator r0 = r0.iterator()
        L_0x001b:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x0033
            java.lang.Object r2 = r0.next()
            r3 = r2
            com.samsung.android.app.sdk.deepsky.textextraction.languagepack.OnDeviceLanguage r3 = (com.samsung.android.app.sdk.deepsky.textextraction.languagepack.OnDeviceLanguage) r3
            java.lang.String r3 = r3.getCode()
            boolean r3 = kotlin.jvm.internal.j.a(r3, r5)
            if (r3 == 0) goto L_0x001b
            goto L_0x0034
        L_0x0033:
            r2 = 0
        L_0x0034:
            com.samsung.android.app.sdk.deepsky.textextraction.languagepack.OnDeviceLanguage r2 = (com.samsung.android.app.sdk.deepsky.textextraction.languagepack.OnDeviceLanguage) r2
            if (r2 != 0) goto L_0x003f
            java.lang.Object r4 = ne.C1194l.L0(r4)
            r2 = r4
            com.samsung.android.app.sdk.deepsky.textextraction.languagepack.OnDeviceLanguage r2 = (com.samsung.android.app.sdk.deepsky.textextraction.languagepack.OnDeviceLanguage) r2
        L_0x003f:
            java.lang.String r4 = r2.getCode()
            java.lang.String r5 = r2.getLangPackCode()
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r3 = "convert from "
            r0.<init>(r3)
            r0.append(r4)
            java.lang.String r4 = " to "
            r0.append(r4)
            r0.append(r5)
            java.lang.String r4 = r0.toString()
            com.samsung.android.app.sdk.deepsky.textextraction.logger.LibLogger.d(r1, r4)
            java.lang.String r4 = r2.getDisplayLangCode()
            return r4
        L_0x0065:
            java.lang.String r4 = "no matching LangPackCode for "
            java.lang.String r4 = r4.concat(r5)
            com.samsung.android.app.sdk.deepsky.textextraction.logger.LibLogger.w(r1, r4)
            java.lang.String r4 = ""
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.app.sdk.deepsky.textextraction.languagepack.LangPackManager.convertOnDeviceLangCodeToDisplayLangCode(java.lang.String):java.lang.String");
    }

    public final List<OnDeviceLanguage> getAllInstalledOnDeviceLangList() {
        ArrayList arrayList = new ArrayList();
        for (OnDeviceLanguage next : this.translateSupportLangList) {
            String packageName = next.getPackageName();
            if (packageName.length() == 0) {
                arrayList.add(next);
            } else if (isInstalledPackage(packageName)) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }

    public final List<OnDeviceLanguage> getInstalledOnDeviceLangList(String str) {
        j.e(str, "onDeviceLangCode");
        ArrayList arrayList = new ArrayList();
        for (OnDeviceLanguage next : getSupportOnDeviceLangList(str)) {
            String packageName = next.getPackageName();
            if (packageName.length() > 0 && isInstalledPackage(packageName)) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }

    public final void init() {
        if (!this.isInitialized.get()) {
            LibLogger.i("LangPackManager", "initialize LangPackManager");
            this.translateSupportLangList = initTranslateSupportLangList(this.context);
            this.isInitialized.compareAndSet(false, true);
        }
    }

    public final boolean isLangPackDownloadNeeded(List<String> list) {
        j.e(list, "onDeviceLangCodes");
        LibLogger.i("LangPackManager", "lang pack check = " + list);
        List<String> filterDownloadNeededLangCodes = filterDownloadNeededLangCodes(list);
        if (isUnsupportedLangPackExist(filterDownloadNeededLangCodes)) {
            LibLogger.w("LangPackManager", "unsupported lang pack exists, request download skip");
            return false;
        }
        for (String str : filterDownloadNeededLangCodes) {
            LibLogger.d("LangPackManager", "(valid) installable : " + str);
        }
        return !filterDownloadNeededLangCodes.isEmpty();
    }

    public final void requestLangPackDownload(List<String> list) {
        j.e(list, "onDeviceLangCodes");
        Iterable<String> filterDownloadNeededLangCodes = filterDownloadNeededLangCodes(list);
        ArrayList arrayList = new ArrayList(C1196n.w0(filterDownloadNeededLangCodes, 10));
        for (String convertOnDeviceLangCodeToLangPackCode : filterDownloadNeededLangCodes) {
            arrayList.add(convertOnDeviceLangCodeToLangPackCode(convertOnDeviceLangCodeToLangPackCode));
        }
        ArrayList arrayList2 = new ArrayList();
        for (Object next : arrayList) {
            if (!j.a((String) next, "")) {
                arrayList2.add(next);
            }
        }
        if (arrayList2.isEmpty()) {
            LibLogger.w("LangPackManager", "downloadList is empty, startActivity skipped");
            return;
        }
        String join = TextUtils.join(GlobalPostProcInternalPPInterface.SPLIT_REGEX, arrayList2);
        LibLogger.i("LangPackManager", "downloadList " + join);
        Context context2 = this.context;
        Intent intent = new Intent("com.samsung.android.settings.action.REQUEST_LANGUAGE_PACK_DOWNLOAD");
        intent.putExtra("package", this.context.getPackageName());
        intent.putExtra("function", this.context.getString(R$string.translate));
        intent.putExtra("locale", join);
        context2.startActivity(intent);
        LibLogger.i("LangPackManager", "requested LanguagePackDownload");
    }
}
