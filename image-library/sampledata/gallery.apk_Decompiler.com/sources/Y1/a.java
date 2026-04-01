package Y1;

import android.view.View;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class a implements Runnable {
    public final /* synthetic */ View d;
    public final /* synthetic */ int e;
    public final /* synthetic */ BottomSheetBehavior f;

    public a(BottomSheetBehavior bottomSheetBehavior, View view, int i2) {
        this.f = bottomSheetBehavior;
        this.d = view;
        this.e = i2;
    }

    public final void run() {
        this.f.startSettling(this.d, this.e, false);
    }
}
