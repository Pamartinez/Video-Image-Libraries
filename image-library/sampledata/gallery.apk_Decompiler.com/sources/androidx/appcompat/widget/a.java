package androidx.appcompat.widget;

import android.content.res.Resources;
import android.view.View;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements View.OnLayoutChangeListener {
    public final /* synthetic */ TooltipCompatHandler d;
    public final /* synthetic */ Resources e;

    public /* synthetic */ a(TooltipCompatHandler tooltipCompatHandler, Resources resources) {
        this.d = tooltipCompatHandler;
        this.e = resources;
    }

    public final void onLayoutChange(View view, int i2, int i7, int i8, int i10, int i11, int i12, int i13, int i14) {
        this.d.lambda$show$1(this.e, view, i2, i7, i8, i10, i11, i12, i13, i14);
    }
}
