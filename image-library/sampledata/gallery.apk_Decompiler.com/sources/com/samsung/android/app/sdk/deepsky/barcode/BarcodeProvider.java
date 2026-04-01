package com.samsung.android.app.sdk.deepsky.barcode;

import android.content.Context;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0007¨\u0006\b"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/barcode/BarcodeProvider;", "", "<init>", "()V", "getBarcodeScanner", "Lcom/samsung/android/app/sdk/deepsky/barcode/BarcodeScanner;", "context", "Landroid/content/Context;", "deepsky-sdk-barcode-1.0.12_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class BarcodeProvider {
    public static final BarcodeProvider INSTANCE = new BarcodeProvider();

    private BarcodeProvider() {
    }

    public static final BarcodeScanner getBarcodeScanner(Context context) {
        j.e(context, "context");
        Context applicationContext = context.getApplicationContext();
        j.d(applicationContext, "getApplicationContext(...)");
        return new BarcodeScannerImpl(applicationContext);
    }
}
