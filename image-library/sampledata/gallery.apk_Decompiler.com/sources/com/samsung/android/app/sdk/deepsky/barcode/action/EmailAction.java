package com.samsung.android.app.sdk.deepsky.barcode.action;

import X2.h;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.samsung.android.app.sdk.deepsky.barcode.R$string;
import com.samsung.android.app.sdk.deepsky.barcode.action.abstraction.Action;
import com.samsung.android.app.sdk.deepsky.barcode.action.di.Injector;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B!\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\u000f\u0010\u000b\u001a\u00020\nH\u0016¢\u0006\u0004\b\u000b\u0010\fJ\u000f\u0010\u000e\u001a\u00020\rH\u0016¢\u0006\u0004\b\u000e\u0010\u000fJ\u000f\u0010\u0011\u001a\u00020\u0010H\u0016¢\u0006\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u0013R\u0014\u0010\u0007\u001a\u00020\u00068\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010\u0014¨\u0006\u0016"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/barcode/action/EmailAction;", "Lcom/samsung/android/app/sdk/deepsky/barcode/action/abstraction/Action;", "Landroid/content/Context;", "context", "LX2/h;", "emailAddressParsedResult", "Lcom/samsung/android/app/sdk/deepsky/barcode/action/di/Injector;", "injector", "<init>", "(Landroid/content/Context;LX2/h;Lcom/samsung/android/app/sdk/deepsky/barcode/action/di/Injector;)V", "", "getTitleId", "()I", "", "getActionId", "()Ljava/lang/String;", "Landroid/content/Intent;", "getIntent", "()Landroid/content/Intent;", "LX2/h;", "Lcom/samsung/android/app/sdk/deepsky/barcode/action/di/Injector;", "Companion", "deepsky-sdk-barcode-1.0.12_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class EmailAction extends Action {
    public static final Companion Companion = new Companion((e) null);
    private final h emailAddressParsedResult;
    private final Injector injector;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/barcode/action/EmailAction$Companion;", "", "<init>", "()V", "URI_SCHEME", "", "deepsky-sdk-barcode-1.0.12_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ EmailAction(Context context, h hVar, Injector injector2, int i2, e eVar) {
        this(context, hVar, (i2 & 4) != 0 ? new Injector() {
            public Intent getIntent() {
                return new Intent();
            }
        } : injector2);
    }

    public String getActionId() {
        return "Email";
    }

    public Intent getIntent() {
        boolean z;
        boolean z3;
        Intent intent = this.injector.getIntent();
        intent.setAction("android.intent.action.SENDTO");
        intent.setData(Uri.parse("mailto:"));
        intent.addFlags(268435456);
        String[] strArr = this.emailAddressParsedResult.b;
        String[] strArr2 = null;
        boolean z7 = false;
        if (strArr != null) {
            if (strArr.length == 0) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (z3) {
                strArr = null;
            }
            if (strArr != null) {
                intent.putExtra("android.intent.extra.EMAIL", strArr);
            }
        }
        String[] strArr3 = this.emailAddressParsedResult.f925c;
        if (strArr3 != null) {
            if (strArr3.length == 0) {
                z = true;
            } else {
                z = false;
            }
            if (z) {
                strArr3 = null;
            }
            if (strArr3 != null) {
                intent.putExtra("android.intent.extra.CC", strArr3);
            }
        }
        String[] strArr4 = this.emailAddressParsedResult.d;
        if (strArr4 != null) {
            if (strArr4.length == 0) {
                z7 = true;
            }
            if (!z7) {
                strArr2 = strArr4;
            }
            if (strArr2 != null) {
                intent.putExtra("android.intent.extra.BCC", strArr2);
            }
        }
        intent.putExtra("android.intent.extra.SUBJECT", this.emailAddressParsedResult.e);
        intent.putExtra("android.intent.extra.TEXT", this.emailAddressParsedResult.f);
        return intent;
    }

    public int getTitleId() {
        return R$string.barcode_dialog_action_send_email;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public EmailAction(Context context, h hVar, Injector injector2) {
        super(context);
        j.e(context, "context");
        j.e(hVar, "emailAddressParsedResult");
        j.e(injector2, "injector");
        this.emailAddressParsedResult = hVar;
        this.injector = injector2;
    }
}
