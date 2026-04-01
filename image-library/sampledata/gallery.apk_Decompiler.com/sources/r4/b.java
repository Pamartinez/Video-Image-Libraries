package r4;

import android.view.View;
import com.samsung.android.gallery.app.ui.dialog.abstraction.EditBaseDialog;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ EditBaseDialog e;
    public final /* synthetic */ View f;

    public /* synthetic */ b(EditBaseDialog editBaseDialog, View view, int i2) {
        this.d = i2;
        this.e = editBaseDialog;
        this.f = view;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.e.lambda$initInputMethodManager$0(this.f);
                return;
            default:
                this.e.lambda$onSipResume$1(this.f);
                return;
        }
    }
}
