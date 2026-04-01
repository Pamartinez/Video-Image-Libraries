package com.samsung.android.app.sdk.deepsky.barcode;

import android.graphics.Bitmap;
import com.samsung.android.app.sdk.deepsky.barcode.result.Barcode;
import java.util.List;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u0006H&¨\u0006\u0007"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/barcode/BarcodeScanner;", "", "process", "", "Lcom/samsung/android/app/sdk/deepsky/barcode/result/Barcode;", "bitmap", "Landroid/graphics/Bitmap;", "deepsky-sdk-barcode-1.0.12_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface BarcodeScanner {
    List<Barcode> process(Bitmap bitmap);
}
