package A2;

import android.view.View;
import com.google.android.material.tabs.d;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class k implements View.OnLayoutChangeListener {
    public final /* synthetic */ View d;
    public final /* synthetic */ d e;

    public k(d dVar, View view) {
        this.e = dVar;
        this.d = view;
    }

    public final void onLayoutChange(View view, int i2, int i7, int i8, int i10, int i11, int i12, int i13, int i14) {
        View view2 = this.d;
        if (view2.getVisibility() == 0) {
            this.e.c(view2);
        }
    }
}
