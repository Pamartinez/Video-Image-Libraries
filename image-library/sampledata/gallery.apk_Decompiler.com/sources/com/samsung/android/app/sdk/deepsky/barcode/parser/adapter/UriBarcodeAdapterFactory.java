package com.samsung.android.app.sdk.deepsky.barcode.parser.adapter;

import A4.I;
import Ae.b;
import Tf.v;
import X2.r;
import android.content.Context;
import h3.a;
import java.util.ArrayList;
import java.util.regex.Pattern;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import ne.C1195m;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 \u001a2\u00020\u0001:\u0001\u001aB\u0011\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0017\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\t\u0010\nJ\u0017\u0010\u000b\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\u000b\u0010\nJ\u0017\u0010\f\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\f\u0010\nJ\u0017\u0010\r\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\r\u0010\nJ\u0017\u0010\u000e\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\u000e\u0010\nJ\u0017\u0010\u000f\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\u000f\u0010\nJ\u0017\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\u0010\u0010\nJ\u0017\u0010\u0011\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\u0011\u0010\nJ\u0017\u0010\u0012\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\u0012\u0010\nJ\u0015\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0014\u001a\u00020\u0013¢\u0006\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0018\u001a\u00020\u00028\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0018\u0010\u0019¨\u0006\u001b"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/barcode/parser/adapter/UriBarcodeAdapterFactory;", "", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;)V", "", "displayResult", "", "isEsimUri", "(Ljava/lang/String;)Z", "isFacebookUri", "isInstagramUri", "isPlayStoreUri", "isQuickShareUri", "isSamsungAccountUri", "isSamsungCmcUri", "isSamsungHealthUri", "isSamsungPassUri", "LX2/r;", "parsedResult", "Lcom/samsung/android/app/sdk/deepsky/barcode/parser/adapter/BarcodeParsedResult;", "create", "(LX2/r;)Lcom/samsung/android/app/sdk/deepsky/barcode/parser/adapter/BarcodeParsedResult;", "appContext", "Landroid/content/Context;", "Companion", "deepsky-sdk-barcode-1.0.12_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class UriBarcodeAdapterFactory {
    public static final Companion Companion = new Companion((e) null);
    private static final ArrayList<String> QUICK_SHARE_URLS = C1195m.o0("https://linksharing.samsungcloud.com");
    private static final ArrayList<String> SAMSUNG_ACCOUNT_URLS = C1195m.o0("https://signin.samsung.com/key/", "https://signins.samsungospdev.com/key/", "https://signine.samsungosp.com/key/");
    /* access modifiers changed from: private */
    public static volatile UriBarcodeAdapterFactory instance;
    private final Context appContext;

    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u0014R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u001e\u0010\f\u001a\u0012\u0012\u0004\u0012\u00020\u00050\rj\b\u0012\u0004\u0012\u00020\u0005`\u000eX\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u000f\u001a\u0012\u0012\u0004\u0012\u00020\u00050\rj\b\u0012\u0004\u0012\u00020\u0005`\u000eX\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u000e¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/barcode/parser/adapter/UriBarcodeAdapterFactory$Companion;", "", "<init>", "()V", "ESIM_QR_CODE_PREFIX", "", "FACEBOOK_QR_PATTERN", "INSTAGRAM_QR_PATTERN", "PLAY_STORE_URL_PREFIX", "SAMSUNG_CMC_PREFIX", "SAMSUNG_HEALTH_PREFIX", "SAMSUNG_PASS_DEEP_LINK_PREFIX", "QUICK_SHARE_URLS", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "SAMSUNG_ACCOUNT_URLS", "instance", "Lcom/samsung/android/app/sdk/deepsky/barcode/parser/adapter/UriBarcodeAdapterFactory;", "getInstance", "context", "Landroid/content/Context;", "deepsky-sdk-barcode-1.0.12_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        public final UriBarcodeAdapterFactory getInstance(Context context) {
            UriBarcodeAdapterFactory access$getInstance$cp;
            j.e(context, "context");
            UriBarcodeAdapterFactory access$getInstance$cp2 = UriBarcodeAdapterFactory.instance;
            if (access$getInstance$cp2 != null) {
                return access$getInstance$cp2;
            }
            synchronized (this) {
                access$getInstance$cp = UriBarcodeAdapterFactory.instance;
                if (access$getInstance$cp == null) {
                    Context applicationContext = context.getApplicationContext();
                    j.d(applicationContext, "getApplicationContext(...)");
                    access$getInstance$cp = new UriBarcodeAdapterFactory(applicationContext, (e) null);
                    UriBarcodeAdapterFactory.instance = access$getInstance$cp;
                }
            }
            return access$getInstance$cp;
        }

        private Companion() {
        }
    }

    public /* synthetic */ UriBarcodeAdapterFactory(Context context, e eVar) {
        this(context);
    }

    private final boolean isEsimUri(String str) {
        return v.t0(str, "LPA:");
    }

    private final boolean isFacebookUri(String str) {
        Pattern compile = Pattern.compile("((http(s)?)://)?(www[.])?(facebook.com)/.*");
        j.d(compile, "compile(...)");
        j.e(str, "input");
        return compile.matcher(str).matches();
    }

    private final boolean isInstagramUri(String str) {
        Pattern compile = Pattern.compile("((http(s)?)://)?(www[.])?(facebook.com)/.*");
        j.d(compile, "compile(...)");
        j.e(str, "input");
        return compile.matcher(str).matches();
    }

    private final boolean isPlayStoreUri(String str) {
        return v.t0(str, "https://play.google.com/store/apps/details?id=");
    }

    private final boolean isQuickShareUri(String str) {
        return QUICK_SHARE_URLS.stream().anyMatch(new I(29, new a(str, 0)));
    }

    /* access modifiers changed from: private */
    public static final boolean isQuickShareUri$lambda$0(String str, String str2) {
        j.b(str2);
        return v.t0(str, str2);
    }

    /* access modifiers changed from: private */
    public static final boolean isQuickShareUri$lambda$1(b bVar, Object obj) {
        return ((Boolean) bVar.invoke(obj)).booleanValue();
    }

    private final boolean isSamsungAccountUri(String str) {
        return SAMSUNG_ACCOUNT_URLS.stream().anyMatch(new h3.b(0, new a(str, 1)));
    }

    /* access modifiers changed from: private */
    public static final boolean isSamsungAccountUri$lambda$2(String str, String str2) {
        j.b(str2);
        return v.t0(str, str2);
    }

    /* access modifiers changed from: private */
    public static final boolean isSamsungAccountUri$lambda$3(b bVar, Object obj) {
        return ((Boolean) bVar.invoke(obj)).booleanValue();
    }

    private final boolean isSamsungCmcUri(String str) {
        return v.t0(str, "cmc://setting?");
    }

    private final boolean isSamsungHealthUri(String str) {
        return v.t0(str, "https://shealth.samsung.com/deepLink?sc_id=");
    }

    private final boolean isSamsungPassUri(String str) {
        return v.t0(str, "samsungpass://launch?action=main&verify=");
    }

    public final BarcodeParsedResult create(r rVar) {
        j.e(rVar, "parsedResult");
        String a7 = rVar.a();
        j.d(a7, "getDisplayResult(...)");
        if (isEsimUri(a7)) {
            return new EsimBarcodeAdapter(this.appContext, rVar);
        }
        String a10 = rVar.a();
        j.d(a10, "getDisplayResult(...)");
        if (isFacebookUri(a10)) {
            return new FacebookBarcodeAdapter(this.appContext, rVar);
        }
        String a11 = rVar.a();
        j.d(a11, "getDisplayResult(...)");
        if (isInstagramUri(a11)) {
            return new InstagramBarcodeAdapter(this.appContext, rVar);
        }
        String a12 = rVar.a();
        j.d(a12, "getDisplayResult(...)");
        if (isPlayStoreUri(a12)) {
            return new PlayStoreBarcodeAdapter(this.appContext, rVar);
        }
        String a13 = rVar.a();
        j.d(a13, "getDisplayResult(...)");
        if (isQuickShareUri(a13)) {
            return new QuickShareBarcodeAdapter(this.appContext, rVar);
        }
        String a14 = rVar.a();
        j.d(a14, "getDisplayResult(...)");
        if (isSamsungAccountUri(a14)) {
            return new SamsungAccountBarcodeAdapter(this.appContext, rVar);
        }
        String a15 = rVar.a();
        j.d(a15, "getDisplayResult(...)");
        if (isSamsungCmcUri(a15)) {
            return new SamsungCmcBarcodeAdapter(this.appContext, rVar);
        }
        String a16 = rVar.a();
        j.d(a16, "getDisplayResult(...)");
        if (isSamsungHealthUri(a16)) {
            return new SamsungHealthBarcodeAdapter(this.appContext, rVar);
        }
        String a17 = rVar.a();
        j.d(a17, "getDisplayResult(...)");
        if (isSamsungPassUri(a17)) {
            return new SamsungPassBarcodeAdapter(this.appContext, rVar);
        }
        return new UrlBarcodeAdapter(this.appContext, rVar);
    }

    private UriBarcodeAdapterFactory(Context context) {
        Context applicationContext = context.getApplicationContext();
        j.d(applicationContext, "getApplicationContext(...)");
        this.appContext = applicationContext;
    }
}
