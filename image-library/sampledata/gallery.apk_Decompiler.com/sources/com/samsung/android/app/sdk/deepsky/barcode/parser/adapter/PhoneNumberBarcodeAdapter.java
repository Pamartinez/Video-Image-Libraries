package com.samsung.android.app.sdk.deepsky.barcode.parser.adapter;

import X2.x;
import android.content.Context;
import com.samsung.android.app.sdk.deepsky.barcode.R$string;
import com.samsung.android.app.sdk.deepsky.barcode.action.PhoneNumberCallAction;
import com.samsung.android.app.sdk.deepsky.barcode.action.abstraction.Action;
import com.samsung.android.app.sdk.deepsky.barcode.action.di.Injector;
import com.samsung.android.app.sdk.deepsky.barcode.parser.common.ParserUtil;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import ne.C1195m;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\t\u001a\u00020\bH\u0016¢\u0006\u0004\b\t\u0010\nJ\u000f\u0010\u000b\u001a\u00020\bH\u0016¢\u0006\u0004\b\u000b\u0010\nJ\u000f\u0010\f\u001a\u00020\bH\u0016¢\u0006\u0004\b\f\u0010\nJ\u0015\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rH\u0016¢\u0006\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u0011R\u0014\u0010\u0012\u001a\u00020\u00028\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0012\u0010\u0013¨\u0006\u0014"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/barcode/parser/adapter/PhoneNumberBarcodeAdapter;", "Lcom/samsung/android/app/sdk/deepsky/barcode/parser/adapter/BarcodeParsedResult;", "Landroid/content/Context;", "context", "LX2/x;", "telParsedResult", "<init>", "(Landroid/content/Context;LX2/x;)V", "", "getTitle", "()Ljava/lang/String;", "getBody", "getBodyTts", "", "Lcom/samsung/android/app/sdk/deepsky/barcode/action/abstraction/Action;", "getActions", "()Ljava/util/List;", "LX2/x;", "appContext", "Landroid/content/Context;", "deepsky-sdk-barcode-1.0.12_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class PhoneNumberBarcodeAdapter implements BarcodeParsedResult {
    private final Context appContext;
    private final x telParsedResult;

    public PhoneNumberBarcodeAdapter(Context context, x xVar) {
        j.e(context, "context");
        j.e(xVar, "telParsedResult");
        this.telParsedResult = xVar;
        Context applicationContext = context.getApplicationContext();
        j.d(applicationContext, "getApplicationContext(...)");
        this.appContext = applicationContext;
    }

    public List<Action> getActions() {
        return C1195m.o0(new PhoneNumberCallAction(this.appContext, this.telParsedResult, (Injector) null, 4, (e) null));
    }

    public String getBody() {
        String str = this.telParsedResult.b;
        j.d(str, "getNumber(...)");
        return str;
    }

    public String getBodyTts() {
        ParserUtil parserUtil = ParserUtil.INSTANCE;
        String str = this.telParsedResult.b;
        j.d(str, "getNumber(...)");
        return parserUtil.getTtsOneDigitNumber(str);
    }

    public String getTitle() {
        String string = this.appContext.getString(R$string.barcode_title_phone_number);
        j.d(string, "getString(...)");
        return string;
    }
}
