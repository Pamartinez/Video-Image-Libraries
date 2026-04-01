package r3;

import android.view.View;
import com.samsung.android.app.sdk.deepsky.textextraction.selectionui.barcode.BarcodeDialogFragment;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements View.OnClickListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ BarcodeDialogFragment e;

    public /* synthetic */ a(BarcodeDialogFragment barcodeDialogFragment, int i2) {
        this.d = i2;
        this.e = barcodeDialogFragment;
    }

    public final void onClick(View view) {
        int i2 = this.d;
        BarcodeDialogFragment barcodeDialogFragment = this.e;
        switch (i2) {
            case 0:
                BarcodeDialogFragment.setContent$lambda$5$lambda$4(barcodeDialogFragment, view);
                return;
            default:
                BarcodeDialogFragment.setContent$lambda$10$lambda$9(barcodeDialogFragment, view);
                return;
        }
    }
}
