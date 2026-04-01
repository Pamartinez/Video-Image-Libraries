package L5;

import android.view.View;
import com.samsung.android.gallery.app.ui.list.sharings.SharingsFragment;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements View.OnClickListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ SharingsFragment e;

    public /* synthetic */ a(SharingsFragment sharingsFragment, int i2) {
        this.d = i2;
        this.e = sharingsFragment;
    }

    public final void onClick(View view) {
        int i2 = this.d;
        SharingsFragment sharingsFragment = this.e;
        switch (i2) {
            case 0:
                sharingsFragment.lambda$onEmptyViewVisibilityChanged$4(view);
                return;
            default:
                sharingsFragment.lambda$updateViewDensity$5(view);
                return;
        }
    }
}
