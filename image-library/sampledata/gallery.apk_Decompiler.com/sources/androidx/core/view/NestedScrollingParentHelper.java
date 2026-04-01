package androidx.core.view;

import android.view.View;
import android.view.ViewGroup;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class NestedScrollingParentHelper {
    private int mNestedScrollAxesNonTouch;
    private int mNestedScrollAxesTouch;

    public NestedScrollingParentHelper(ViewGroup viewGroup) {
    }

    public int getNestedScrollAxes() {
        return this.mNestedScrollAxesNonTouch | this.mNestedScrollAxesTouch;
    }

    public void onNestedScrollAccepted(View view, View view2, int i2) {
        onNestedScrollAccepted(view, view2, i2, 0);
    }

    public void onStopNestedScroll(View view, int i2) {
        if (i2 == 1) {
            this.mNestedScrollAxesNonTouch = 0;
        } else {
            this.mNestedScrollAxesTouch = 0;
        }
    }

    public void onNestedScrollAccepted(View view, View view2, int i2, int i7) {
        if (i7 == 1) {
            this.mNestedScrollAxesNonTouch = i2;
        } else {
            this.mNestedScrollAxesTouch = i2;
        }
    }
}
