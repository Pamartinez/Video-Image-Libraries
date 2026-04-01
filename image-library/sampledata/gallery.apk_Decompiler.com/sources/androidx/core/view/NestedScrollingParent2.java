package androidx.core.view;

import android.view.View;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface NestedScrollingParent2 {
    void onNestedPreScroll(View view, int i2, int i7, int[] iArr, int i8);

    void onNestedScroll(View view, int i2, int i7, int i8, int i10, int i11);

    void onNestedScrollAccepted(View view, View view2, int i2, int i7);

    boolean onStartNestedScroll(View view, View view2, int i2, int i7);

    void onStopNestedScroll(View view, int i2);
}
