package com.samsung.android.app.sdk.deepsky.barcode.parser;

import com.samsung.android.app.sdk.deepsky.barcode.parser.adapter.BarcodeParsedResult;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/barcode/parser/BarcodeParser;", "", "getParsedBarcodeResult", "Lcom/samsung/android/app/sdk/deepsky/barcode/parser/adapter/BarcodeParsedResult;", "rawData", "", "deepsky-sdk-barcode-1.0.12_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface BarcodeParser {
    BarcodeParsedResult getParsedBarcodeResult(String str);
}
