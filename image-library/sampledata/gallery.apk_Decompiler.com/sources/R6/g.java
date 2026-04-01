package r6;

import android.widget.LinearLayout;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class g implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ LinearLayout.LayoutParams e;

    public /* synthetic */ g(LinearLayout.LayoutParams layoutParams, int i2) {
        this.d = i2;
        this.e = layoutParams;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        LinearLayout.LayoutParams layoutParams = this.e;
        switch (i2) {
            case 0:
                layoutParams.width = ((Integer) obj).intValue();
                return;
            case 1:
                layoutParams.height = ((Integer) obj).intValue();
                return;
            default:
                layoutParams.weight = ((Float) obj).floatValue();
                return;
        }
    }
}
