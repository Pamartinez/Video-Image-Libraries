package com.samsung.android.app.sdk.deepsky.barcode.parser.adapter;

import X2.r;
import android.content.Context;
import com.samsung.android.app.sdk.deepsky.barcode.R$string;
import com.samsung.android.app.sdk.deepsky.barcode.action.abstraction.Action;
import com.samsung.android.app.sdk.deepsky.barcode.action.uri.SamsungPassLinkOpenAction;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import ne.C1195m;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0019\u0010\t\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0003\u001a\u00020\u0002H\u0002¢\u0006\u0004\b\t\u0010\nJ\u000f\u0010\u000b\u001a\u00020\bH\u0016¢\u0006\u0004\b\u000b\u0010\fJ\u000f\u0010\r\u001a\u00020\bH\u0016¢\u0006\u0004\b\r\u0010\fJ\u000f\u0010\u000e\u001a\u00020\bH\u0016¢\u0006\u0004\b\u000e\u0010\fJ\u0015\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fH\u0016¢\u0006\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u0013R\u0014\u0010\u0014\u001a\u00020\u00028\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0014\u0010\u0015¨\u0006\u0016"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/barcode/parser/adapter/SamsungPassBarcodeAdapter;", "Lcom/samsung/android/app/sdk/deepsky/barcode/parser/adapter/BarcodeParsedResult;", "Landroid/content/Context;", "context", "LX2/r;", "parsedResult", "<init>", "(Landroid/content/Context;LX2/r;)V", "", "getAppName", "(Landroid/content/Context;)Ljava/lang/String;", "getTitle", "()Ljava/lang/String;", "getBody", "getBodyTts", "", "Lcom/samsung/android/app/sdk/deepsky/barcode/action/abstraction/Action;", "getActions", "()Ljava/util/List;", "LX2/r;", "appContext", "Landroid/content/Context;", "deepsky-sdk-barcode-1.0.12_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class SamsungPassBarcodeAdapter implements BarcodeParsedResult {
    private final Context appContext;
    private final r parsedResult;

    public SamsungPassBarcodeAdapter(Context context, r rVar) {
        j.e(context, "context");
        j.e(rVar, "parsedResult");
        this.parsedResult = rVar;
        Context applicationContext = context.getApplicationContext();
        j.d(applicationContext, "getApplicationContext(...)");
        this.appContext = applicationContext;
    }

    private final String getAppName(Context context) {
        return context.getString(R$string.barcode_samsung_pass);
    }

    public List<Action> getActions() {
        return C1195m.o0(new SamsungPassLinkOpenAction(this.appContext, this.parsedResult));
    }

    public String getBody() {
        Context context = this.appContext;
        String string = context.getString(R$string.barcode_body_samsung_pass, new Object[]{getAppName(context)});
        j.d(string, "getString(...)");
        return string;
    }

    public String getBodyTts() {
        return getBody();
    }

    public String getTitle() {
        String string = this.appContext.getString(R$string.barcode_title_samsung_pass);
        j.d(string, "getString(...)");
        return string;
    }
}
