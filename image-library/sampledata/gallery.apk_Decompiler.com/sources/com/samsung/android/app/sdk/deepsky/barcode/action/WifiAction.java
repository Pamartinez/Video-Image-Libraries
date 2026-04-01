package com.samsung.android.app.sdk.deepsky.barcode.action;

import X2.F;
import X2.G;
import android.content.Context;
import android.content.Intent;
import com.samsung.android.app.sdk.deepsky.barcode.R$string;
import com.samsung.android.app.sdk.deepsky.barcode.action.abstraction.Action;
import com.samsung.android.app.sdk.deepsky.barcode.action.di.Injector;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\f\u0018\u0000 \u001b2\u00020\u0001:\u0001\u001bB!\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\u0013\u0010\f\u001a\u00020\u000b*\u00020\nH\u0002¢\u0006\u0004\b\f\u0010\rJ\u0017\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u000eH\u0002¢\u0006\u0004\b\u0011\u0010\u0012J\u000f\u0010\u0013\u001a\u00020\u0010H\u0016¢\u0006\u0004\b\u0013\u0010\u0014J\u000f\u0010\u0015\u001a\u00020\u000eH\u0016¢\u0006\u0004\b\u0015\u0010\u0016J\u000f\u0010\u0017\u001a\u00020\nH\u0016¢\u0006\u0004\b\u0017\u0010\u0018R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u0019R\u0014\u0010\u0007\u001a\u00020\u00068\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010\u001a¨\u0006\u001c"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/barcode/action/WifiAction;", "Lcom/samsung/android/app/sdk/deepsky/barcode/action/abstraction/Action;", "Landroid/content/Context;", "context", "LX2/F;", "wifiParsedResult", "Lcom/samsung/android/app/sdk/deepsky/barcode/action/di/Injector;", "injector", "<init>", "(Landroid/content/Context;LX2/F;Lcom/samsung/android/app/sdk/deepsky/barcode/action/di/Injector;)V", "Landroid/content/Intent;", "Lme/x;", "putExtras", "(Landroid/content/Intent;)V", "", "networkEncryption", "", "getWifiAuthType", "(Ljava/lang/String;)I", "getTitleId", "()I", "getActionId", "()Ljava/lang/String;", "getIntent", "()Landroid/content/Intent;", "LX2/F;", "Lcom/samsung/android/app/sdk/deepsky/barcode/action/di/Injector;", "Companion", "deepsky-sdk-barcode-1.0.12_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class WifiAction extends Action {
    public static final Companion Companion = new Companion((e) null);
    private final Injector injector;
    private final F wifiParsedResult;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/barcode/action/WifiAction$Companion;", "", "<init>", "()V", "INTENT_WIFI_SETTINGS", "", "deepsky-sdk-barcode-1.0.12_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ WifiAction(Context context, F f, Injector injector2, int i2, e eVar) {
        this(context, f, (i2 & 4) != 0 ? new Injector() {
            public Intent getIntent() {
                return new Intent();
            }
        } : injector2);
    }

    private final int getWifiAuthType(String str) {
        switch (str.hashCode()) {
            case 68404:
                if (!str.equals("EAP")) {
                    return 0;
                }
                return 3;
            case 81847:
                if (!str.equals("SAE")) {
                    return 0;
                }
                return 4;
            case 85826:
                if (!str.equals("WEP")) {
                    return 0;
                }
                return 1;
            case 86152:
                if (!str.equals("WPA")) {
                    return 0;
                }
                return 2;
            case 2670762:
                if (!str.equals("WPA2")) {
                    return 0;
                }
                return 2;
            case 1219499692:
                if (!str.equals("WAPI_PSK")) {
                    return 0;
                }
                return 5;
            default:
                return 0;
        }
    }

    private final void putExtras(Intent intent) {
        F f = this.wifiParsedResult;
        if (f.f == G.DPP) {
            intent.putExtra("DPP", f.g);
            return;
        }
        intent.putExtra("SSID", f.b);
        intent.putExtra("PASSWORD", this.wifiParsedResult.d);
        intent.putExtra("HIDDEN", this.wifiParsedResult.e);
        String str = this.wifiParsedResult.f910c;
        if (str == null) {
            Locale locale = Locale.getDefault();
            j.d(locale, "getDefault(...)");
            str = "".toUpperCase(locale);
            j.d(str, "toUpperCase(...)");
        }
        intent.putExtra("AUTH_TYPE", getWifiAuthType(str));
    }

    public String getActionId() {
        return "Wifi";
    }

    public Intent getIntent() {
        Intent intent = this.injector.getIntent();
        intent.setAction("android.settings.WIFI_SETTINGS");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(268468224);
        putExtras(intent);
        return intent;
    }

    public int getTitleId() {
        return R$string.barcode_dialog_action_connect_to_network;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public WifiAction(Context context, F f, Injector injector2) {
        super(context);
        j.e(context, "context");
        j.e(f, "wifiParsedResult");
        j.e(injector2, "injector");
        this.wifiParsedResult = f;
        this.injector = injector2;
    }
}
