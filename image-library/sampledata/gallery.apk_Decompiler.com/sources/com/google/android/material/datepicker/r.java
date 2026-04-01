package com.google.android.material.datepicker;

import android.view.View;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.WindowInsetsCompat;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class r implements OnApplyWindowInsetsListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ View e;
    public final /* synthetic */ int f;

    public r(int i2, int i7, View view) {
        this.d = i2;
        this.e = view;
        this.f = i7;
    }

    public final WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
        int i2 = windowInsetsCompat.getInsets(WindowInsetsCompat.Type.systemBars()).top;
        View view2 = this.e;
        int i7 = this.d;
        if (i7 >= 0) {
            view2.getLayoutParams().height = i7 + i2;
            view2.setLayoutParams(view2.getLayoutParams());
        }
        view2.setPadding(view2.getPaddingLeft(), this.f + i2, view2.getPaddingRight(), view2.getPaddingBottom());
        return windowInsetsCompat;
    }
}
