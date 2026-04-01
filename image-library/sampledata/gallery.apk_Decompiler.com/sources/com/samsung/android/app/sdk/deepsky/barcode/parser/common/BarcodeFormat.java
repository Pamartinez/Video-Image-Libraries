package com.samsung.android.app.sdk.deepsky.barcode.parser.common;

import W2.a;
import com.samsung.context.sdk.samsunganalytics.internal.sender.c;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0018\u0002\n\u0002\b\u0011\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0011\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0006\u001a\u0004\b\u0007\u0010\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012¨\u0006\u0013"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/barcode/parser/common/BarcodeFormat;", "", "LW2/a;", "zxingBarcodeFormat", "<init>", "(Ljava/lang/String;ILW2/a;)V", "LW2/a;", "getZxingBarcodeFormat", "()LW2/a;", "QR", "EAN8", "EAN13", "UPCA", "UPCE", "CODE39", "CODE128", "DM", "PDF_417", "AZTEC", "deepsky-sdk-barcode-1.0.12_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum BarcodeFormat {
    QR(a.QR_CODE),
    EAN8(a.EAN_8),
    EAN13(a.EAN_13),
    UPCA(a.UPC_A),
    UPCE(a.UPC_E),
    CODE39(a.CODE_39),
    CODE128(a.CODE_128),
    DM(a.DATA_MATRIX),
    PDF_417(a.PDF_417),
    AZTEC(a.AZTEC);
    
    private final a zxingBarcodeFormat;

    static {
        BarcodeFormat[] $values;
        $ENTRIES = c.t($values);
    }

    private BarcodeFormat(a aVar) {
        this.zxingBarcodeFormat = aVar;
    }

    public final a getZxingBarcodeFormat() {
        return this.zxingBarcodeFormat;
    }
}
