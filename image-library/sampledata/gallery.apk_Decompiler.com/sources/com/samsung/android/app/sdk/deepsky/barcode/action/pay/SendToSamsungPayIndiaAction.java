package com.samsung.android.app.sdk.deepsky.barcode.action.pay;

import Z2.a;
import android.content.Context;
import android.content.Intent;
import com.samsung.android.app.sdk.deepsky.barcode.R$string;
import com.samsung.android.app.sdk.deepsky.barcode.action.abstraction.Action;
import com.samsung.android.app.sdk.deepsky.barcode.action.di.Injector;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B!\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\u000f\u0010\u000b\u001a\u00020\nH\u0016¢\u0006\u0004\b\u000b\u0010\fJ\u000f\u0010\u000e\u001a\u00020\rH\u0016¢\u0006\u0004\b\u000e\u0010\u000fJ\u000f\u0010\u0011\u001a\u00020\u0010H\u0016¢\u0006\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u0013R\u0014\u0010\u0007\u001a\u00020\u00068\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010\u0014¨\u0006\u0016"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/barcode/action/pay/SendToSamsungPayIndiaAction;", "Lcom/samsung/android/app/sdk/deepsky/barcode/action/abstraction/Action;", "Landroid/content/Context;", "context", "LZ2/a;", "payParsedResult", "Lcom/samsung/android/app/sdk/deepsky/barcode/action/di/Injector;", "injector", "<init>", "(Landroid/content/Context;LZ2/a;Lcom/samsung/android/app/sdk/deepsky/barcode/action/di/Injector;)V", "", "getTitleId", "()I", "", "getActionId", "()Ljava/lang/String;", "Landroid/content/Intent;", "getIntent", "()Landroid/content/Intent;", "LZ2/a;", "Lcom/samsung/android/app/sdk/deepsky/barcode/action/di/Injector;", "Companion", "deepsky-sdk-barcode-1.0.12_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class SendToSamsungPayIndiaAction extends Action {
    public static final Companion Companion = new Companion((e) null);
    private final Injector injector;
    private final a payParsedResult;

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/barcode/action/pay/SendToSamsungPayIndiaAction$Companion;", "", "<init>", "()V", "deepsky-sdk-barcode-1.0.12_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ SendToSamsungPayIndiaAction(Context context, a aVar, Injector injector2, int i2, e eVar) {
        this(context, aVar, (i2 & 4) != 0 ? new Injector() {
            public Intent getIntent() {
                return new Intent();
            }
        } : injector2);
    }

    public String getActionId() {
        return "SendToSamsungPayIndia";
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0035, code lost:
        r2 = r5.payParsedResult;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.content.Intent getIntent() {
        /*
            r5 = this;
            com.samsung.android.app.sdk.deepsky.barcode.action.di.Injector r0 = r5.injector
            android.content.Intent r0 = r0.getIntent()
            java.lang.String r1 = "android.intent.action.VIEW"
            r0.setAction(r1)
            Z2.a r1 = r5.payParsedResult
            java.lang.String r1 = r1.b
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "samsungpay://cameraqrscan?action=qr_scan&qrData="
            r2.<init>(r3)
            r2.append(r1)
            java.lang.String r1 = r2.toString()
            android.net.Uri r1 = android.net.Uri.parse(r1)
            r0.setData(r1)
            r1 = 268435456(0x10000000, float:2.5243549E-29)
            r0.addFlags(r1)
            com.samsung.android.app.sdk.deepsky.barcode.action.common.ActionUtil r1 = com.samsung.android.app.sdk.deepsky.barcode.action.common.ActionUtil.INSTANCE
            android.content.Context r2 = r5.getAppContext()
            boolean r2 = r1.isLauncherActivityAvailable(r2, r0)
            if (r2 != 0) goto L_0x0069
            Z2.a r2 = r5.payParsedResult
            Z2.d r3 = r2.f969c
            Z2.d r4 = Z2.d.UPI
            if (r3 == r4) goto L_0x0041
            Z2.d r4 = Z2.d.UPI_PAYTM
            if (r3 != r4) goto L_0x0069
        L_0x0041:
            java.lang.String r2 = r2.b
            android.net.Uri r2 = android.net.Uri.parse(r2)
            r0.setData(r2)
            android.content.Context r2 = r5.getAppContext()
            java.lang.String r3 = "com.samsung.android.spay"
            boolean r2 = r1.isPackageInstalled(r2, r3)
            if (r2 == 0) goto L_0x005a
            r0.setPackage(r3)
            return r0
        L_0x005a:
            android.content.Context r5 = r5.getAppContext()
            java.lang.String r2 = "com.samsung.android.spaymini"
            boolean r5 = r1.isPackageInstalled(r5, r2)
            if (r5 == 0) goto L_0x0069
            r0.setPackage(r2)
        L_0x0069:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.app.sdk.deepsky.barcode.action.pay.SendToSamsungPayIndiaAction.getIntent():android.content.Intent");
    }

    public int getTitleId() {
        return R$string.barcode_dialog_action_open_samsung_pay;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SendToSamsungPayIndiaAction(Context context, a aVar, Injector injector2) {
        super(context);
        j.e(context, "context");
        j.e(aVar, "payParsedResult");
        j.e(injector2, "injector");
        this.payParsedResult = aVar;
        this.injector = injector2;
    }
}
