package com.google.android.material.search;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.appbar.AppBarLayout;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SearchBar$ScrollingViewBehavior extends AppBarLayout.ScrollingViewBehavior {

    /* renamed from: j  reason: collision with root package name */
    public boolean f1488j = false;

    public SearchBar$ScrollingViewBehavior() {
    }

    public final boolean onDependentViewChanged(CoordinatorLayout coordinatorLayout, View view, View view2) {
        super.onDependentViewChanged(coordinatorLayout, view, view2);
        if (!this.f1488j && (view2 instanceof AppBarLayout)) {
            this.f1488j = true;
            AppBarLayout appBarLayout = (AppBarLayout) view2;
            appBarLayout.setBackgroundColor(0);
            appBarLayout.setTargetElevation(0.0f);
        }
        return false;
    }

    public SearchBar$ScrollingViewBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }
}
