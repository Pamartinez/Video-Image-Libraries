package com.samsung.android.app.sdk.deepsky.barcode.action;

import X2.r;
import Y2.a;
import Y2.c;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.samsung.android.app.sdk.deepsky.barcode.R$string;
import com.samsung.android.app.sdk.deepsky.barcode.action.abstraction.Action;
import com.samsung.android.app.sdk.deepsky.barcode.action.common.ActionUtil;
import com.samsung.android.app.sdk.deepsky.barcode.logger.LibLogger;
import i.C0212a;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\t\u001a\u00020\bH\u0016¢\u0006\u0004\b\t\u0010\nJ\u000f\u0010\f\u001a\u00020\u000bH\u0016¢\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u000f\u001a\u00020\u000eH\u0016¢\u0006\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u0011R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u0012¨\u0006\u0014"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/barcode/action/ConnectSmartThingsAction;", "Lcom/samsung/android/app/sdk/deepsky/barcode/action/abstraction/Action;", "Landroid/content/Context;", "context", "LX2/r;", "parsedResult", "<init>", "(Landroid/content/Context;LX2/r;)V", "", "getTitleId", "()I", "", "getActionId", "()Ljava/lang/String;", "Landroid/content/Intent;", "getIntent", "()Landroid/content/Intent;", "Landroid/content/Context;", "LX2/r;", "Companion", "deepsky-sdk-barcode-1.0.12_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ConnectSmartThingsAction extends Action {
    public static final Companion Companion = new Companion((e) null);
    private final Context context;
    private final r parsedResult;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/barcode/action/ConnectSmartThingsAction$Companion;", "", "<init>", "()V", "TAG", "", "SMART_THINGS_DEEP_LINK_PREFIX", "SMART_THINGS_GLOBAL_URL", "deepsky-sdk-barcode-1.0.12_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ConnectSmartThingsAction(Context context2, r rVar) {
        super(context2);
        j.e(context2, "context");
        j.e(rVar, "parsedResult");
        this.context = context2;
        this.parsedResult = rVar;
    }

    public String getActionId() {
        return "ConnectSmartThings";
    }

    public Intent getIntent() {
        r rVar = this.parsedResult;
        j.c(rVar, "null cannot be cast to non-null type com.google.zxing.client.result.iot.IoTParsedResult");
        a aVar = (a) rVar;
        String str = aVar.b;
        c cVar = aVar.f954c;
        if (cVar == c.SMART_TAG_QR) {
            ActionUtil actionUtil = ActionUtil.INSTANCE;
            j.b(str);
            return actionUtil.getLaunchBrowserIntent(str);
        }
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(C0212a.l("scapp_qronboarding://", str)));
        intent.addFlags(268435456);
        ActionUtil actionUtil2 = ActionUtil.INSTANCE;
        if (actionUtil2.isLauncherActivityAvailable(this.context, intent)) {
            return intent;
        }
        LibLogger.w("ConnectSmartThingsAction", "getConnectSmartThingsIntent : Activity cannot found");
        if (cVar != c.ON_BOARDING_STANDARD_QR) {
            return actionUtil2.getLaunchBrowserIntent("https://qr.samsungiots.com");
        }
        j.b(str);
        return actionUtil2.getLaunchBrowserIntent(str);
    }

    public int getTitleId() {
        return R$string.barcode_dialog_action_add_now;
    }
}
