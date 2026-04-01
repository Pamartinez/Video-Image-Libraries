package com.samsung.android.app.sdk.deepsky.barcode.parser.adapter;

import X2.r;
import android.content.Context;
import com.samsung.android.app.sdk.deepsky.barcode.R$string;
import com.samsung.android.app.sdk.deepsky.barcode.action.CopyTextAction;
import com.samsung.android.app.sdk.deepsky.barcode.action.abstraction.Action;
import com.samsung.android.app.sdk.deepsky.barcode.action.common.ActionUtil;
import com.samsung.android.app.sdk.deepsky.barcode.action.di.Injector;
import com.samsung.android.app.sdk.deepsky.barcode.action.text.ViewTextAction;
import com.samsung.android.app.sdk.deepsky.barcode.action.text.WebSearchAction;
import com.samsung.android.app.sdk.deepsky.barcode.logger.LibLogger;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0007\u0018\u0000 \u001b2\u00020\u0001:\u0001\u001bB\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J#\u0010\f\u001a\u00020\u000b*\u0012\u0012\u0004\u0012\u00020\t0\bj\b\u0012\u0004\u0012\u00020\t`\nH\u0002¢\u0006\u0004\b\f\u0010\rJ#\u0010\u000e\u001a\u00020\u000b*\u0012\u0012\u0004\u0012\u00020\t0\bj\b\u0012\u0004\u0012\u00020\t`\nH\u0002¢\u0006\u0004\b\u000e\u0010\rJ#\u0010\u000f\u001a\u00020\u000b*\u0012\u0012\u0004\u0012\u00020\t0\bj\b\u0012\u0004\u0012\u00020\t`\nH\u0002¢\u0006\u0004\b\u000f\u0010\rJ\u000f\u0010\u0011\u001a\u00020\u0010H\u0016¢\u0006\u0004\b\u0011\u0010\u0012J\u000f\u0010\u0013\u001a\u00020\u0010H\u0016¢\u0006\u0004\b\u0013\u0010\u0012J\u000f\u0010\u0014\u001a\u00020\u0010H\u0016¢\u0006\u0004\b\u0014\u0010\u0012J\u0015\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\t0\u0015H\u0016¢\u0006\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u0018R\u0014\u0010\u0019\u001a\u00020\u00028\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0019\u0010\u001a¨\u0006\u001c"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/barcode/parser/adapter/TextBarcodeAdapter;", "Lcom/samsung/android/app/sdk/deepsky/barcode/parser/adapter/BarcodeParsedResult;", "Landroid/content/Context;", "context", "LX2/r;", "parsedResult", "<init>", "(Landroid/content/Context;LX2/r;)V", "Ljava/util/ArrayList;", "Lcom/samsung/android/app/sdk/deepsky/barcode/action/abstraction/Action;", "Lkotlin/collections/ArrayList;", "Lme/x;", "addViewTextActionIfPossible", "(Ljava/util/ArrayList;)V", "addWebSearchActionIfPossible", "addCopyTextAction", "", "getTitle", "()Ljava/lang/String;", "getBody", "getBodyTts", "", "getActions", "()Ljava/util/List;", "LX2/r;", "appContext", "Landroid/content/Context;", "Companion", "deepsky-sdk-barcode-1.0.12_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class TextBarcodeAdapter implements BarcodeParsedResult {
    public static final Companion Companion = new Companion((e) null);
    private final Context appContext;
    private final r parsedResult;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/barcode/parser/adapter/TextBarcodeAdapter$Companion;", "", "<init>", "()V", "TAG", "", "deepsky-sdk-barcode-1.0.12_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    public TextBarcodeAdapter(Context context, r rVar) {
        j.e(context, "context");
        j.e(rVar, "parsedResult");
        this.parsedResult = rVar;
        Context applicationContext = context.getApplicationContext();
        j.d(applicationContext, "getApplicationContext(...)");
        this.appContext = applicationContext;
    }

    private final void addCopyTextAction(ArrayList<Action> arrayList) {
        Context context = this.appContext;
        String a7 = this.parsedResult.a();
        j.d(a7, "getDisplayResult(...)");
        arrayList.add(new CopyTextAction(context, a7));
    }

    private final void addViewTextActionIfPossible(ArrayList<Action> arrayList) {
        ViewTextAction viewTextAction = new ViewTextAction(this.appContext, this.parsedResult, (Injector) null, 4, (e) null);
        boolean isActionAddible = viewTextAction.isActionAddible();
        if (!isActionAddible) {
            LibLogger.w("TextBarcodeAdapter", "ViewTextAction is not supported: Apps not installed");
        }
        if (!isActionAddible) {
            viewTextAction = null;
        }
        if (viewTextAction != null) {
            arrayList.add(viewTextAction);
        }
    }

    private final void addWebSearchActionIfPossible(ArrayList<Action> arrayList) {
        WebSearchAction webSearchAction = new WebSearchAction(this.appContext, this.parsedResult, (Injector) null, 4, (e) null);
        if (!ActionUtil.INSTANCE.isLauncherActivityAvailable(this.appContext, webSearchAction.getIntent())) {
            webSearchAction = null;
        }
        if (webSearchAction != null) {
            arrayList.add(webSearchAction);
        }
    }

    public List<Action> getActions() {
        ArrayList arrayList = new ArrayList();
        addViewTextActionIfPossible(arrayList);
        addWebSearchActionIfPossible(arrayList);
        addCopyTextAction(arrayList);
        return arrayList;
    }

    public String getBody() {
        String a7 = this.parsedResult.a();
        if (a7 == null) {
            return "";
        }
        return a7;
    }

    public String getBodyTts() {
        return getBody();
    }

    public String getTitle() {
        String string = this.appContext.getString(R$string.barcode_title_note);
        j.d(string, "getString(...)");
        return string;
    }
}
