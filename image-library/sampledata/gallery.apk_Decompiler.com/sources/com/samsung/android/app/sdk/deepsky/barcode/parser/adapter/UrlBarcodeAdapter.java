package com.samsung.android.app.sdk.deepsky.barcode.parser.adapter;

import X2.r;
import X2.y;
import android.content.Context;
import com.samsung.android.app.sdk.deepsky.barcode.R$string;
import com.samsung.android.app.sdk.deepsky.barcode.action.CopyTextAction;
import com.samsung.android.app.sdk.deepsky.barcode.action.abstraction.Action;
import com.samsung.android.app.sdk.deepsky.barcode.action.common.ActionUtil;
import com.samsung.android.app.sdk.deepsky.barcode.action.uri.LinkOpenAction;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J#\u0010\f\u001a\u00020\u000b*\u0012\u0012\u0004\u0012\u00020\t0\bj\b\u0012\u0004\u0012\u00020\t`\nH\u0002¢\u0006\u0004\b\f\u0010\rJ#\u0010\u000e\u001a\u00020\u000b*\u0012\u0012\u0004\u0012\u00020\t0\bj\b\u0012\u0004\u0012\u00020\t`\nH\u0002¢\u0006\u0004\b\u000e\u0010\rJ\u000f\u0010\u0010\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u0010\u0010\u0011J\u000f\u0010\u0012\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u0012\u0010\u0011J\u000f\u0010\u0013\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u0013\u0010\u0011J\u0015\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\t0\u0014H\u0016¢\u0006\u0004\b\u0015\u0010\u0016R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u0017R\u0014\u0010\u0018\u001a\u00020\u00028\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0018\u0010\u0019¨\u0006\u001a"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/barcode/parser/adapter/UrlBarcodeAdapter;", "Lcom/samsung/android/app/sdk/deepsky/barcode/parser/adapter/BarcodeParsedResult;", "Landroid/content/Context;", "context", "LX2/r;", "parsedResult", "<init>", "(Landroid/content/Context;LX2/r;)V", "Ljava/util/ArrayList;", "Lcom/samsung/android/app/sdk/deepsky/barcode/action/abstraction/Action;", "Lkotlin/collections/ArrayList;", "Lme/x;", "addUriOpenActionIfPossible", "(Ljava/util/ArrayList;)V", "addUriCopyAction", "", "getTitle", "()Ljava/lang/String;", "getBody", "getBodyTts", "", "getActions", "()Ljava/util/List;", "LX2/r;", "appContext", "Landroid/content/Context;", "deepsky-sdk-barcode-1.0.12_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class UrlBarcodeAdapter implements BarcodeParsedResult {
    private final Context appContext;
    private final r parsedResult;

    public UrlBarcodeAdapter(Context context, r rVar) {
        j.e(context, "context");
        j.e(rVar, "parsedResult");
        this.parsedResult = rVar;
        Context applicationContext = context.getApplicationContext();
        j.d(applicationContext, "getApplicationContext(...)");
        this.appContext = applicationContext;
    }

    private final void addUriCopyAction(ArrayList<Action> arrayList) {
        Context context = this.appContext;
        String a7 = this.parsedResult.a();
        j.d(a7, "getDisplayResult(...)");
        arrayList.add(new CopyTextAction(context, a7));
    }

    private final void addUriOpenActionIfPossible(ArrayList<Action> arrayList) {
        LinkOpenAction linkOpenAction = new LinkOpenAction(this.appContext, this.parsedResult);
        if (!ActionUtil.INSTANCE.isLauncherActivityAvailable(this.appContext, linkOpenAction.getIntent())) {
            linkOpenAction = null;
        }
        if (linkOpenAction != null) {
            arrayList.add(linkOpenAction);
        }
    }

    public List<Action> getActions() {
        ArrayList arrayList = new ArrayList();
        addUriOpenActionIfPossible(arrayList);
        addUriCopyAction(arrayList);
        return arrayList;
    }

    public String getBody() {
        String a7;
        String str;
        r rVar = this.parsedResult;
        if (rVar instanceof y) {
            a7 = ((y) rVar).b;
            str = "getURI(...)";
        } else {
            a7 = rVar.a();
            str = "getDisplayResult(...)";
        }
        j.d(a7, str);
        return a7;
    }

    public String getBodyTts() {
        return getBody();
    }

    public String getTitle() {
        String string = this.appContext.getString(R$string.barcode_dialog_title_url);
        j.d(string, "getString(...)");
        return string;
    }
}
