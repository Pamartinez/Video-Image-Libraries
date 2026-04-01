package com.samsung.android.app.sdk.deepsky.barcode.parser.adapter;

import X2.r;
import android.content.Context;
import android.content.res.Resources;
import android.telephony.TelephonyManager;
import com.samsung.android.app.sdk.deepsky.barcode.R$string;
import com.samsung.android.app.sdk.deepsky.barcode.action.SendMatterAction;
import com.samsung.android.app.sdk.deepsky.barcode.action.abstraction.Action;
import com.samsung.android.app.sdk.deepsky.barcode.action.di.Injector;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import ne.C1195m;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0017\u0010\t\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u0002H\u0002¢\u0006\u0004\b\t\u0010\nJ\u0017\u0010\f\u001a\u00020\u000b2\u0006\u0010\u0003\u001a\u00020\u0002H\u0002¢\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u000e\u001a\u00020\bH\u0016¢\u0006\u0004\b\u000e\u0010\u000fJ\u000f\u0010\u0010\u001a\u00020\bH\u0016¢\u0006\u0004\b\u0010\u0010\u000fJ\u000f\u0010\u0011\u001a\u00020\bH\u0016¢\u0006\u0004\b\u0011\u0010\u000fJ\u0015\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012H\u0016¢\u0006\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u0016R\u0014\u0010\u0017\u001a\u00020\u00028\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0017\u0010\u0018¨\u0006\u0019"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/barcode/parser/adapter/MatterBarcodeAdapter;", "Lcom/samsung/android/app/sdk/deepsky/barcode/parser/adapter/BarcodeParsedResult;", "Landroid/content/Context;", "context", "LX2/r;", "parsedResult", "<init>", "(Landroid/content/Context;LX2/r;)V", "", "getAppName", "(Landroid/content/Context;)Ljava/lang/String;", "", "isSmartThingsInChina", "(Landroid/content/Context;)Z", "getTitle", "()Ljava/lang/String;", "getBody", "getBodyTts", "", "Lcom/samsung/android/app/sdk/deepsky/barcode/action/abstraction/Action;", "getActions", "()Ljava/util/List;", "LX2/r;", "appContext", "Landroid/content/Context;", "deepsky-sdk-barcode-1.0.12_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class MatterBarcodeAdapter implements BarcodeParsedResult {
    private final Context appContext;
    private final r parsedResult;

    public MatterBarcodeAdapter(Context context, r rVar) {
        j.e(context, "context");
        j.e(rVar, "parsedResult");
        this.parsedResult = rVar;
        Context applicationContext = context.getApplicationContext();
        j.d(applicationContext, "getApplicationContext(...)");
        this.appContext = applicationContext;
    }

    private final String getAppName(Context context) {
        if (isSmartThingsInChina(context)) {
            String string = context.getString(R$string.barcode_samsung_connect);
            j.d(string, "getString(...)");
            return string;
        }
        String string2 = context.getString(R$string.barcode_smartthings);
        j.d(string2, "getString(...)");
        return string2;
    }

    private final boolean isSmartThingsInChina(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        String str = "";
        if (telephonyManager != null) {
            String simCountryIso = telephonyManager.getSimCountryIso();
            if (simCountryIso == null || str.equals(simCountryIso)) {
                String networkCountryIso = telephonyManager.getNetworkCountryIso();
                if (networkCountryIso == null || str.equals(networkCountryIso)) {
                    str = Resources.getSystem().getConfiguration().getLocales().get(0).getCountry();
                } else {
                    str = networkCountryIso;
                }
            } else {
                str = simCountryIso;
            }
        }
        j.b(str);
        Locale locale = Locale.getDefault();
        j.d(locale, "getDefault(...)");
        String upperCase = str.toUpperCase(locale);
        j.d(upperCase, "toUpperCase(...)");
        return "CN".equals(upperCase);
    }

    public List<Action> getActions() {
        return C1195m.o0(new SendMatterAction(this.appContext, this.parsedResult, (Injector) null, 4, (e) null));
    }

    public String getBody() {
        Context context = this.appContext;
        String string = context.getString(R$string.barcode_body_matter, new Object[]{getAppName(context)});
        j.d(string, "getString(...)");
        return string;
    }

    public String getBodyTts() {
        return getBody();
    }

    public String getTitle() {
        String string = this.appContext.getString(R$string.barcode_title_matter);
        j.d(string, "getString(...)");
        return string;
    }
}
