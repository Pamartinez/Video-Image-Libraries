package tb;

import android.content.Context;
import com.samsung.android.gallery.widget.dialog.CustomPinPrompt;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ CustomPinPrompt e;
    public final /* synthetic */ Context f;

    public /* synthetic */ e(CustomPinPrompt customPinPrompt, Context context, int i2) {
        this.d = i2;
        this.e = customPinPrompt;
        this.f = context;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.e.lambda$authenticate$7(this.f);
                return;
            case 1:
                this.e.lambda$createNew$5(this.f);
                return;
            default:
                this.e.lambda$showDialog$4(this.f);
                return;
        }
    }
}
