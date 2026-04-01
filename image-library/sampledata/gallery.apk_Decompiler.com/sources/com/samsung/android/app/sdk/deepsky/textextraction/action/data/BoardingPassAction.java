package com.samsung.android.app.sdk.deepsky.textextraction.action.data;

import Tf.n;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.SemSystemProperties;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.samsung.android.app.sdk.deepsky.textextraction.R$drawable;
import com.samsung.android.app.sdk.deepsky.textextraction.action.ActionConstants;
import com.samsung.android.app.sdk.deepsky.textextraction.logger.LibLogger;
import java.util.Iterator;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import me.i;
import ne.C1194l;
import ne.z;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0000\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\b\u0010\u0010\u001a\u00020\u000eH\u0016J\b\u0010\u0011\u001a\u00020\u000eH\u0002J\b\u0010\u0012\u001a\u00020\u000eH\u0002J\b\u0010\u0013\u001a\u00020\u000eH\u0002J\b\u0010\u0014\u001a\u00020\u000eH\u0002J\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0016H\u0002R\u0014\u0010\n\u001a\u00020\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\r\u001a\u00020\u000eX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000f¨\u0006\u0019"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/action/data/BoardingPassAction;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/action/data/WalletAction;", "context", "Landroid/content/Context;", "imageUri", "Landroid/net/Uri;", "info", "Lcom/google/gson/JsonObject;", "<init>", "(Landroid/content/Context;Landroid/net/Uri;Lcom/google/gson/JsonObject;)V", "iconUri", "getIconUri", "()Landroid/net/Uri;", "isSupportAction", "", "()Z", "runAction", "isSupportOldBoardingPass", "isSupportBoardingPass", "isSupportFormat", "isChineseModel", "getReferrerId", "", "packageName", "Companion", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class BoardingPassAction extends WalletAction {
    public static final Companion Companion = new Companion((e) null);
    private static final Map<String, String> SERVICE_NAME_TO_REFERRER_ID = z.b0(new i("com.samsung.android.app.smartcapture", "smart_capture"), new i("com.samsung.android.app.smartcapture.aiselect", "ai_select"), new i("com.sec.android.gallery3d", "gallery"), new i("com.sec.android.app.camera", "camera"));
    private final Uri iconUri;
    private final boolean isSupportAction;

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010$\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u001a\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0010X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/action/data/BoardingPassAction$Companion;", "", "<init>", "()V", "TAG", "", "WALLET_PACKAGE_NAME", "WALLET_CLASS_NAME", "WALLET_URI", "JSON_KEY_QR_CODE_VALUE", "JSON_KEY_QR_CODE_TYPE", "INTENT_KEY_QR_DATA", "INTENT_KEY_QR_FORMAT", "INTENT_KEY_REFERRER", "INTENT_KEY_ADD_FROM", "SERVICE_NAME_TO_REFERRER_ID", "", "SUPPORTED_FEATURE_BOARDING_PASS", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public BoardingPassAction(Context context, Uri uri, JsonObject jsonObject) {
        super(context, uri, jsonObject);
        boolean z;
        j.e(context, "context");
        j.e(uri, "imageUri");
        j.e(jsonObject, "info");
        this.iconUri = getResourceUri(R$drawable.capsule_traveling, context);
        if ((isSupportBoardingPass() || isSupportOldBoardingPass()) && isSupportFormat()) {
            z = true;
        } else {
            z = false;
        }
        if (!z) {
            LibLogger.i("BoardingPassAction", "Wallet doesn't support boarding pass.");
        }
        this.isSupportAction = z;
    }

    private final String getReferrerId(String str) {
        T t;
        String str2;
        Iterator<T> it = SERVICE_NAME_TO_REFERRER_ID.entrySet().iterator();
        while (true) {
            if (!it.hasNext()) {
                t = null;
                break;
            }
            t = it.next();
            if (n.u0(str, (CharSequence) ((Map.Entry) t).getKey())) {
                break;
            }
        }
        Map.Entry entry = (Map.Entry) t;
        if (entry == null || (str2 = (String) entry.getValue()) == null) {
            return "";
        }
        return str2;
    }

    private final boolean isChineseModel() {
        return j.a(SemSystemProperties.getCountryIso(), "CN");
    }

    private final boolean isSupportBoardingPass() {
        return isSupportFeature("BOARDING_PASS");
    }

    private final boolean isSupportFormat() {
        String str;
        if (!isChineseModel()) {
            return true;
        }
        JsonElement jsonElement = getInfo().get("QrCodeType");
        if (jsonElement != null) {
            str = jsonElement.getAsString();
        } else {
            str = null;
        }
        return C1194l.G0(ActionConstants.INSTANCE.getSUPPORTED_BARCODE_FORMATS_IN_CN(), str);
    }

    private final boolean isSupportOldBoardingPass() {
        try {
            PackageManager packageManager = getContext().getPackageManager();
            if (packageManager == null || packageManager.getComponentEnabledSetting(new ComponentName("com.samsung.android.spay", "com.samsung.android.spay.BoardingPassReceiver")) != 1) {
                return false;
            }
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            LibLogger.e("BoardingPassAction", "Samsung Wallet is not installed");
            return false;
        } catch (Exception e) {
            LibLogger.e("BoardingPassAction", "Exception occurred while check wallet app : " + e);
            return false;
        }
    }

    public Uri getIconUri() {
        return this.iconUri;
    }

    public boolean isSupportAction() {
        return this.isSupportAction;
    }

    public boolean runAction() {
        String str;
        int i2;
        boolean z;
        LibLogger.i("BoardingPassAction", "runAction");
        if (isSupportAction()) {
            Bundle bundle = new Bundle();
            JsonElement jsonElement = getInfo().get("QrCodeValue");
            String str2 = null;
            if (jsonElement != null) {
                str = jsonElement.getAsString();
            } else {
                str = null;
            }
            JsonElement jsonElement2 = getInfo().get("QrCodeType");
            if (jsonElement2 != null) {
                str2 = jsonElement2.getAsString();
            }
            bundle.putString("extra_key_qr_data", str);
            Integer num = ActionConstants.INSTANCE.getBARCODE_LOOKUP_TABLE().get(str2);
            if (num != null) {
                i2 = num.intValue();
            } else {
                i2 = -1;
            }
            bundle.putInt("extra_key_qr_format", i2);
            String sourceName = getSourceName(getContext());
            if (isChineseModel()) {
                bundle.putString("extra_key_referrer", "DEEP_SKY");
            } else {
                bundle.putString("extra_key_referrer", getReferrerId(sourceName));
            }
            bundle.putString("extra_key_add_from", sourceName);
            if (str == null || str.length() == 0) {
                z = true;
            } else {
                z = false;
            }
            StringBuilder sb2 = new StringBuilder("Send boarding pass intent : ");
            sb2.append(!z);
            LibLogger.i("BoardingPassAction", sb2.toString());
            Intent intent = new Intent();
            intent.setData(Uri.parse("samsungpay://launch?action=usbpc_add"));
            intent.setFlags(268435456);
            intent.putExtras(bundle);
            getContext().startActivity(intent);
        }
        return true;
    }
}
