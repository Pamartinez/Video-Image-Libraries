package com.samsung.android.app.sdk.deepsky.barcode.action.text;

import X2.r;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.samsung.android.app.sdk.deepsky.barcode.R$string;
import com.samsung.android.app.sdk.deepsky.barcode.action.abstraction.Action;
import com.samsung.android.app.sdk.deepsky.barcode.action.common.ActionUtil;
import com.samsung.android.app.sdk.deepsky.barcode.action.di.Injector;
import com.samsung.android.app.sdk.deepsky.contract.search.Contract;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017B!\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\u000f\u0010\u000b\u001a\u00020\nH\u0002¢\u0006\u0004\b\u000b\u0010\fJ\u000f\u0010\r\u001a\u00020\nH\u0002¢\u0006\u0004\b\r\u0010\fJ\u000f\u0010\u000f\u001a\u00020\u000eH\u0016¢\u0006\u0004\b\u000f\u0010\u0010J\u000f\u0010\u0012\u001a\u00020\u0011H\u0016¢\u0006\u0004\b\u0012\u0010\u0013J\u000f\u0010\u0014\u001a\u00020\nH\u0016¢\u0006\u0004\b\u0014\u0010\fR\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u0015R\u0014\u0010\u0007\u001a\u00020\u00068\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010\u0016¨\u0006\u0018"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/barcode/action/text/WebSearchAction;", "Lcom/samsung/android/app/sdk/deepsky/barcode/action/abstraction/Action;", "Landroid/content/Context;", "context", "LX2/r;", "parsedResult", "Lcom/samsung/android/app/sdk/deepsky/barcode/action/di/Injector;", "injector", "<init>", "(Landroid/content/Context;LX2/r;Lcom/samsung/android/app/sdk/deepsky/barcode/action/di/Injector;)V", "Landroid/content/Intent;", "getSearchWebIntentBySamsungInternet", "()Landroid/content/Intent;", "getSearchWebIntentByGoogle", "", "getTitleId", "()I", "", "getActionId", "()Ljava/lang/String;", "getIntent", "LX2/r;", "Lcom/samsung/android/app/sdk/deepsky/barcode/action/di/Injector;", "Companion", "deepsky-sdk-barcode-1.0.12_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class WebSearchAction extends Action {
    public static final Companion Companion = new Companion((e) null);
    private final Injector injector;
    private final r parsedResult;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/barcode/action/text/WebSearchAction$Companion;", "", "<init>", "()V", "SAMSUNG_INTERNET_SEARCH_DEEP_LINK", "", "deepsky-sdk-barcode-1.0.12_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ WebSearchAction(Context context, r rVar, Injector injector2, int i2, e eVar) {
        this(context, rVar, (i2 & 4) != 0 ? new Injector() {
            public Intent getIntent() {
                return new Intent();
            }
        } : injector2);
    }

    private final Intent getSearchWebIntentByGoogle() {
        Intent intent = this.injector.getIntent();
        intent.setAction("android.intent.action.WEB_SEARCH");
        intent.putExtra(Contract.QUERY, this.parsedResult.a());
        intent.addFlags(268435456);
        return intent;
    }

    private final Intent getSearchWebIntentBySamsungInternet() {
        Intent intent = this.injector.getIntent();
        intent.setAction("android.intent.action.VIEW");
        String a7 = this.parsedResult.a();
        intent.setData(Uri.parse("samsunginternet://search?keyword=" + a7));
        intent.addFlags(268435456);
        return intent;
    }

    public String getActionId() {
        return "WebSearch";
    }

    public Intent getIntent() {
        Intent searchWebIntentBySamsungInternet = getSearchWebIntentBySamsungInternet();
        if (!ActionUtil.INSTANCE.isLauncherActivityAvailable(getAppContext(), searchWebIntentBySamsungInternet)) {
            searchWebIntentBySamsungInternet = null;
        }
        if (searchWebIntentBySamsungInternet == null) {
            return getSearchWebIntentByGoogle();
        }
        return searchWebIntentBySamsungInternet;
    }

    public int getTitleId() {
        return R$string.barcode_dialog_action_search_web;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public WebSearchAction(Context context, r rVar, Injector injector2) {
        super(context);
        j.e(context, "context");
        j.e(rVar, "parsedResult");
        j.e(injector2, "injector");
        this.parsedResult = rVar;
        this.injector = injector2;
    }
}
