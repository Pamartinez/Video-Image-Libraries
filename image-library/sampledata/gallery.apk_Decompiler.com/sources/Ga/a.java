package Ga;

import android.content.DialogInterface;
import com.samsung.android.gallery.settings.ui.delegate.DialogDelegate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements DialogInterface.OnClickListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ DialogDelegate.OnDataUsageDialogButtonClickListener e;

    public /* synthetic */ a(DialogDelegate.OnDataUsageDialogButtonClickListener onDataUsageDialogButtonClickListener, int i2) {
        this.d = i2;
        this.e = onDataUsageDialogButtonClickListener;
    }

    public final void onClick(DialogInterface dialogInterface, int i2) {
        int i7 = this.d;
        DialogDelegate.OnDataUsageDialogButtonClickListener onDataUsageDialogButtonClickListener = this.e;
        switch (i7) {
            case 0:
                onDataUsageDialogButtonClickListener.onNegativeClicked();
                return;
            default:
                DialogDelegate.lambda$showDataUsageDialog$1(onDataUsageDialogButtonClickListener, dialogInterface, i2);
                return;
        }
    }
}
