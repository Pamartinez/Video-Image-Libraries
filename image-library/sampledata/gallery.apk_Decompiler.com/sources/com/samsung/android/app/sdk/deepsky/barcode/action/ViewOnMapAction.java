package com.samsung.android.app.sdk.deepsky.barcode.action;

import X2.n;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.samsung.android.app.sdk.deepsky.barcode.R$string;
import com.samsung.android.app.sdk.deepsky.barcode.action.abstraction.Action;
import com.samsung.android.app.sdk.deepsky.barcode.action.di.Injector;
import com.samsung.android.gallery.support.utils.MapUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\u000f\u0010\u000b\u001a\u00020\nH\u0016¢\u0006\u0004\b\u000b\u0010\fJ\u000f\u0010\u000e\u001a\u00020\rH\u0016¢\u0006\u0004\b\u000e\u0010\u000fJ\u000f\u0010\u0011\u001a\u00020\u0010H\u0016¢\u0006\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u0013R\u0014\u0010\u0007\u001a\u00020\u00068\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010\u0014¨\u0006\u0015"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/barcode/action/ViewOnMapAction;", "Lcom/samsung/android/app/sdk/deepsky/barcode/action/abstraction/Action;", "Landroid/content/Context;", "context", "LX2/n;", "parsedResult", "Lcom/samsung/android/app/sdk/deepsky/barcode/action/di/Injector;", "injector", "<init>", "(Landroid/content/Context;LX2/n;Lcom/samsung/android/app/sdk/deepsky/barcode/action/di/Injector;)V", "", "getTitleId", "()I", "", "getActionId", "()Ljava/lang/String;", "Landroid/content/Intent;", "getIntent", "()Landroid/content/Intent;", "LX2/n;", "Lcom/samsung/android/app/sdk/deepsky/barcode/action/di/Injector;", "deepsky-sdk-barcode-1.0.12_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ViewOnMapAction extends Action {
    private final Injector injector;
    private final n parsedResult;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ ViewOnMapAction(Context context, n nVar, Injector injector2, int i2, e eVar) {
        this(context, nVar, (i2 & 4) != 0 ? new Injector() {
            public Intent getIntent() {
                return new Intent();
            }
        } : injector2);
    }

    public String getActionId() {
        return "ViewOnMap";
    }

    public Intent getIntent() {
        Intent intent = this.injector.getIntent();
        intent.setAction("android.intent.action.VIEW");
        n nVar = this.parsedResult;
        String str = nVar.e;
        StringBuilder sb2 = new StringBuilder("geo:");
        sb2.append(nVar.b);
        sb2.append(',');
        sb2.append(nVar.f932c);
        double d = nVar.d;
        if (d > MapUtil.INVALID_LOCATION) {
            sb2.append(',');
            sb2.append(d);
        }
        if (str != null) {
            sb2.append('?');
            sb2.append(str);
        }
        intent.setData(Uri.parse(sb2.toString()));
        intent.addFlags(268435456);
        return intent;
    }

    public int getTitleId() {
        return R$string.barcode_dialog_action_view_on_map;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ViewOnMapAction(Context context, n nVar, Injector injector2) {
        super(context);
        j.e(context, "context");
        j.e(nVar, "parsedResult");
        j.e(injector2, "injector");
        this.parsedResult = nVar;
        this.injector = injector2;
    }
}
