package com.samsung.android.app.sdk.deepsky.barcode.action.uri;

import X2.r;
import X2.s;
import X2.y;
import android.content.Context;
import android.content.Intent;
import com.samsung.android.app.sdk.deepsky.barcode.R$string;
import com.samsung.android.app.sdk.deepsky.barcode.action.abstraction.Action;
import com.samsung.android.app.sdk.deepsky.barcode.action.common.ActionUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\t\u001a\u00020\bH\u0016¢\u0006\u0004\b\t\u0010\nJ\u000f\u0010\f\u001a\u00020\u000bH\u0016¢\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u000f\u001a\u00020\u000eH\u0016¢\u0006\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u0011¨\u0006\u0012"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/barcode/action/uri/PlayStoreLinkOpenAction;", "Lcom/samsung/android/app/sdk/deepsky/barcode/action/abstraction/Action;", "Landroid/content/Context;", "context", "LX2/r;", "parsedResult", "<init>", "(Landroid/content/Context;LX2/r;)V", "", "getTitleId", "()I", "", "getActionId", "()Ljava/lang/String;", "Landroid/content/Intent;", "getIntent", "()Landroid/content/Intent;", "LX2/r;", "deepsky-sdk-barcode-1.0.12_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class PlayStoreLinkOpenAction extends Action {
    private final r parsedResult;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public PlayStoreLinkOpenAction(Context context, r rVar) {
        super(context);
        j.e(context, "context");
        j.e(rVar, "parsedResult");
        this.parsedResult = rVar;
    }

    public String getActionId() {
        return "PlayStoreLinkOpen";
    }

    public Intent getIntent() {
        String str;
        r rVar = this.parsedResult;
        if (rVar.f937a == s.URI) {
            j.c(rVar, "null cannot be cast to non-null type com.google.zxing.client.result.URIParsedResult");
            str = ((y) rVar).b;
            j.b(str);
        } else {
            str = rVar.a();
            j.b(str);
        }
        return ActionUtil.INSTANCE.getLaunchBrowserIntent(str);
    }

    public int getTitleId() {
        return R$string.barcode_dialog_action_view_in_play_store;
    }
}
