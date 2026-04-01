package com.samsung.android.app.sdk.deepsky.barcode.parser.adapter;

import X2.v;
import android.content.Context;
import com.samsung.android.app.sdk.deepsky.barcode.R$string;
import com.samsung.android.app.sdk.deepsky.barcode.action.SmsAction;
import com.samsung.android.app.sdk.deepsky.barcode.action.abstraction.Action;
import com.samsung.android.app.sdk.deepsky.barcode.action.di.Injector;
import com.samsung.android.app.sdk.deepsky.barcode.parser.common.ParserUtil;
import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import ne.C1195m;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0017\u0010\n\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0002¢\u0006\u0004\b\n\u0010\u000bJ\u000f\u0010\f\u001a\u00020\bH\u0002¢\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u000e\u001a\u00020\bH\u0016¢\u0006\u0004\b\u000e\u0010\rJ\u000f\u0010\u000f\u001a\u00020\bH\u0016¢\u0006\u0004\b\u000f\u0010\rJ\u000f\u0010\u0010\u001a\u00020\bH\u0016¢\u0006\u0004\b\u0010\u0010\rJ\u0015\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011H\u0016¢\u0006\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u0015R\u0014\u0010\u0016\u001a\u00020\u00028\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0016\u0010\u0017¨\u0006\u0018"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/barcode/parser/adapter/SmsBarcodeAdapter;", "Lcom/samsung/android/app/sdk/deepsky/barcode/parser/adapter/BarcodeParsedResult;", "Landroid/content/Context;", "context", "LX2/v;", "smsParsedResult", "<init>", "(Landroid/content/Context;LX2/v;)V", "", "number", "getFormattedText", "(Ljava/lang/String;)Ljava/lang/String;", "getNumber", "()Ljava/lang/String;", "getTitle", "getBody", "getBodyTts", "", "Lcom/samsung/android/app/sdk/deepsky/barcode/action/abstraction/Action;", "getActions", "()Ljava/util/List;", "LX2/v;", "appContext", "Landroid/content/Context;", "deepsky-sdk-barcode-1.0.12_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class SmsBarcodeAdapter implements BarcodeParsedResult {
    private final Context appContext;
    private final v smsParsedResult;

    public SmsBarcodeAdapter(Context context, v vVar) {
        j.e(context, "context");
        j.e(vVar, "smsParsedResult");
        this.smsParsedResult = vVar;
        Context applicationContext = context.getApplicationContext();
        j.d(applicationContext, "getApplicationContext(...)");
        this.appContext = applicationContext;
    }

    private final String getFormattedText(String str) {
        String str2 = this.smsParsedResult.e;
        if (str2 != null) {
            return String.format("%s, %s", Arrays.copyOf(new Object[]{str, str2}, 2));
        }
        return str;
    }

    private final String getNumber() {
        String str = this.smsParsedResult.b[0];
        if (str == null) {
            return "";
        }
        if (str.length() <= 0) {
            str = null;
        }
        if (str == null) {
            return "";
        }
        return str;
    }

    public List<Action> getActions() {
        return C1195m.o0(new SmsAction(this.appContext, this.smsParsedResult, (Injector) null, 4, (e) null));
    }

    public String getBody() {
        return getFormattedText(getNumber());
    }

    public String getBodyTts() {
        return getFormattedText(ParserUtil.INSTANCE.getTtsOneDigitNumber(getNumber()));
    }

    public String getTitle() {
        String string = this.appContext.getString(R$string.barcode_title_message_recipient);
        j.d(string, "getString(...)");
        return string;
    }
}
