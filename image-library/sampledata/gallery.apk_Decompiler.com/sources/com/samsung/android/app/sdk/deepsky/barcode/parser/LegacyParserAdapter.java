package com.samsung.android.app.sdk.deepsky.barcode.parser;

import D0.e;
import Tf.n;
import X2.p;
import X2.r;
import X2.u;
import android.content.Context;
import com.samsung.android.app.sdk.deepsky.barcode.parser.adapter.BarcodeParsedResult;
import com.samsung.android.app.sdk.deepsky.barcode.parser.adapter.ParsedResultType;
import com.samsung.android.app.sdk.deepsky.barcode.parser.common.BarcodeFormat;
import com.samsung.android.sdk.bixby2.state.StateHandler;
import com.samsung.android.sum.core.types.NumericEnum;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0017\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\t\u0010\nJ\u0017\u0010\r\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\bH\u0002¢\u0006\u0004\b\r\u0010\u000eJ\u0017\u0010\u000f\u001a\u00020\f2\u0006\u0010\u0007\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\u000f\u0010\u0010R\u001c\u0010\u0012\u001a\n \u0011*\u0004\u0018\u00010\u00020\u00028\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0012\u0010\u0013¨\u0006\u0014"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/barcode/parser/LegacyParserAdapter;", "Lcom/samsung/android/app/sdk/deepsky/barcode/parser/BarcodeParser;", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;)V", "", "rawData", "LX2/r;", "getParsedResultFromZxingParser", "(Ljava/lang/String;)LX2/r;", "parsedResult", "Lcom/samsung/android/app/sdk/deepsky/barcode/parser/adapter/BarcodeParsedResult;", "getBarcodeResult", "(LX2/r;)Lcom/samsung/android/app/sdk/deepsky/barcode/parser/adapter/BarcodeParsedResult;", "getParsedBarcodeResult", "(Ljava/lang/String;)Lcom/samsung/android/app/sdk/deepsky/barcode/parser/adapter/BarcodeParsedResult;", "kotlin.jvm.PlatformType", "appContext", "Landroid/content/Context;", "deepsky-sdk-barcode-1.0.12_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class LegacyParserAdapter implements BarcodeParser {
    private final Context appContext;

    public LegacyParserAdapter(Context context) {
        j.e(context, "context");
        this.appContext = context.getApplicationContext();
    }

    private final BarcodeParsedResult getBarcodeResult(r rVar) {
        ParsedResultType valueOf = ParsedResultType.valueOf(rVar.f937a.name());
        Context context = this.appContext;
        j.d(context, StateHandler.KEY_APP_STATE);
        return valueOf.getBarcodeResult(context, rVar);
    }

    private final r getParsedResultFromZxingParser(String str) {
        String substring = str.substring(n.B0(str, NumericEnum.SEP, 0, 6) + 1);
        j.d(substring, "substring(...)");
        e eVar = new e(substring, BarcodeFormat.valueOf((String) n.K0(str, new String[]{NumericEnum.SEP}).get(0)).getZxingBarcodeFormat());
        for (u e : u.f940a) {
            r e7 = e.e(eVar);
            if (e7 != null) {
                return e7;
            }
        }
        return new p((String) eVar.e, 3);
    }

    public BarcodeParsedResult getParsedBarcodeResult(String str) {
        j.e(str, "rawData");
        return getBarcodeResult(getParsedResultFromZxingParser(str));
    }
}
