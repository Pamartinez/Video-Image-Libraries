package com.samsung.android.app.sdk.deepsky.textextraction.action.data;

import L1.d;
import Sf.q;
import Tf.n;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import com.google.gson.JsonObject;
import com.samsung.android.app.sdk.deepsky.textextraction.R$string;
import com.samsung.android.app.sdk.deepsky.textextraction.logger.LibLogger;
import i.C0212a;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import me.f;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\n\b \u0018\u0000 \u00172\u00020\u0001:\u0001\u0017B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\u000f\u0010\u000b\u001a\u00020\nH\u0002¢\u0006\u0004\b\u000b\u0010\fJ\u0017\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\nH\u0004¢\u0006\u0004\b\u000f\u0010\u0010J\u0017\u0010\u0011\u001a\u00020\n2\u0006\u0010\u0003\u001a\u00020\u0002H\u0004¢\u0006\u0004\b\u0011\u0010\u0012R\u001b\u0010\u0016\u001a\u00020\n8VX\u0002¢\u0006\f\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0015\u0010\f¨\u0006\u0018"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/action/data/WalletAction;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/action/data/Action;", "Landroid/content/Context;", "context", "Landroid/net/Uri;", "imageUri", "Lcom/google/gson/JsonObject;", "info", "<init>", "(Landroid/content/Context;Landroid/net/Uri;Lcom/google/gson/JsonObject;)V", "", "initTitle", "()Ljava/lang/String;", "feature", "", "isSupportFeature", "(Ljava/lang/String;)Z", "getSourceName", "(Landroid/content/Context;)Ljava/lang/String;", "title$delegate", "Lme/f;", "getTitle", "title", "Companion", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class WalletAction extends Action {
    public static final Companion Companion = new Companion((e) null);
    private final f title$delegate = d.q(new q(13, this));

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\t\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/action/data/WalletAction$Companion;", "", "<init>", "()V", "TAG", "", "WALLET_SHARE_GLOBAL_URI", "WALLET_SHARE_GLOBAL_METHOD", "WALLET_SHARE_GLOBAL_ARG_LABEL", "WALLET_SHARE_GLOBAL_ARG_SUPPORT", "WALLET_LABEL_KEY", "WALLET_LABEL_DEFAULT", "WALLET_GET_SUPPORTED_FEATURE_LIST", "AI_SELECT_ACTIVITY_NAME", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public WalletAction(Context context, Uri uri, JsonObject jsonObject) {
        super(context, uri, jsonObject);
        j.e(context, "context");
        j.e(uri, "imageUri");
        j.e(jsonObject, "info");
    }

    private final String initTitle() {
        String string;
        String str = "Wallet";
        try {
            ContentResolver contentResolver = getContext().getContentResolver();
            Bundle bundle = null;
            if (contentResolver != null) {
                bundle = contentResolver.call(Uri.parse("content://com.samsung.android.spay.common.share/global"), "GET_global", "wallet_app_label_info", (Bundle) null);
            }
            if (!(bundle == null || (string = bundle.getString("app_label", "")) == null)) {
                str = string;
            }
        } catch (Exception e) {
            LibLogger.e("WalletAction", "Exception occurred while get app name from wallet : " + e);
        }
        String string2 = getContext().getString(R$string.add_to);
        j.d(string2, "getString(...)");
        return String.format(string2, Arrays.copyOf(new Object[]{str}, 1));
    }

    /* access modifiers changed from: private */
    public static final String title_delegate$lambda$0(WalletAction walletAction) {
        return walletAction.initTitle();
    }

    public final String getSourceName(Context context) {
        j.e(context, "context");
        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            String localClassName = activity.getLocalClassName();
            j.d(localClassName, "getLocalClassName(...)");
            if (n.u0(localClassName, "AiAssistSmartSelectActivity")) {
                return C0212a.A(activity.getPackageName(), ".aiselect");
            }
        }
        String packageName = context.getPackageName();
        j.b(packageName);
        return packageName;
    }

    public String getTitle() {
        return (String) this.title$delegate.getValue();
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0038 A[Catch:{ Exception -> 0x0029 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean isSupportFeature(java.lang.String r9) {
        /*
            r8 = this;
            java.lang.String r0 = "WalletAction"
            java.lang.String r1 = "Wallet does not support "
            java.lang.String r2 = "Wallet supports "
            java.lang.String r3 = "feature"
            kotlin.jvm.internal.j.e(r9, r3)
            r3 = 0
            android.content.Context r8 = r8.getContext()     // Catch:{ Exception -> 0x0029 }
            android.content.ContentResolver r8 = r8.getContentResolver()     // Catch:{ Exception -> 0x0029 }
            if (r8 == 0) goto L_0x002b
            java.lang.String r4 = "content://com.samsung.android.spay.common.share/global"
            android.net.Uri r4 = android.net.Uri.parse(r4)     // Catch:{ Exception -> 0x0029 }
            java.lang.String r5 = "GET_global"
            java.lang.String r6 = "support_add_item_from_external_app"
            r7 = 0
            android.os.Bundle r8 = r8.call(r4, r5, r6, r7)     // Catch:{ Exception -> 0x0029 }
            if (r8 != 0) goto L_0x0030
            goto L_0x002b
        L_0x0029:
            r8 = move-exception
            goto L_0x005c
        L_0x002b:
            android.os.Bundle r8 = new android.os.Bundle     // Catch:{ Exception -> 0x0029 }
            r8.<init>()     // Catch:{ Exception -> 0x0029 }
        L_0x0030:
            java.lang.String r4 = "get_supported_feature_list"
            java.util.ArrayList r8 = r8.getStringArrayList(r4)     // Catch:{ Exception -> 0x0029 }
            if (r8 == 0) goto L_0x0056
            boolean r4 = r8.isEmpty()     // Catch:{ Exception -> 0x0029 }
            if (r4 == 0) goto L_0x003f
            goto L_0x0056
        L_0x003f:
            boolean r4 = r8.contains(r9)     // Catch:{ Exception -> 0x0029 }
            if (r4 == 0) goto L_0x004a
            java.lang.String r1 = r2.concat(r9)     // Catch:{ Exception -> 0x0029 }
            goto L_0x004e
        L_0x004a:
            java.lang.String r1 = r1.concat(r9)     // Catch:{ Exception -> 0x0029 }
        L_0x004e:
            com.samsung.android.app.sdk.deepsky.textextraction.logger.LibLogger.i(r0, r1)     // Catch:{ Exception -> 0x0029 }
            boolean r8 = r8.contains(r9)     // Catch:{ Exception -> 0x0029 }
            return r8
        L_0x0056:
            java.lang.String r8 = "Wallet is not supported version"
            com.samsung.android.app.sdk.deepsky.textextraction.logger.LibLogger.i(r0, r8)     // Catch:{ Exception -> 0x0029 }
            return r3
        L_0x005c:
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            java.lang.String r1 = "Exception during check wallet app : "
            r9.<init>(r1)
            r9.append(r8)
            java.lang.String r8 = r9.toString()
            com.samsung.android.app.sdk.deepsky.textextraction.logger.LibLogger.i(r0, r8)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.app.sdk.deepsky.textextraction.action.data.WalletAction.isSupportFeature(java.lang.String):boolean");
    }
}
