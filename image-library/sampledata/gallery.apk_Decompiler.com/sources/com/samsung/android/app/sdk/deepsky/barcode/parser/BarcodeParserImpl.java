package com.samsung.android.app.sdk.deepsky.barcode.parser;

import com.samsung.android.app.sdk.deepsky.barcode.parser.adapter.BarcodeParsedResult;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/barcode/parser/BarcodeParserImpl;", "Lcom/samsung/android/app/sdk/deepsky/barcode/parser/BarcodeParser;", "legacyParserAdapter", "Lcom/samsung/android/app/sdk/deepsky/barcode/parser/LegacyParserAdapter;", "<init>", "(Lcom/samsung/android/app/sdk/deepsky/barcode/parser/LegacyParserAdapter;)V", "getParsedBarcodeResult", "Lcom/samsung/android/app/sdk/deepsky/barcode/parser/adapter/BarcodeParsedResult;", "rawData", "", "deepsky-sdk-barcode-1.0.12_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class BarcodeParserImpl implements BarcodeParser {
    private final LegacyParserAdapter legacyParserAdapter;

    public BarcodeParserImpl(LegacyParserAdapter legacyParserAdapter2) {
        j.e(legacyParserAdapter2, "legacyParserAdapter");
        this.legacyParserAdapter = legacyParserAdapter2;
    }

    public BarcodeParsedResult getParsedBarcodeResult(String str) {
        j.e(str, "rawData");
        return this.legacyParserAdapter.getParsedBarcodeResult(str);
    }
}
