package z2;

import android.graphics.Outline;
import android.view.View;
import android.view.ViewOutlineProvider;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class g extends ViewOutlineProvider {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2208a;
    public final /* synthetic */ int b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ float f2209c;

    public g(int i2, int i7, float f) {
        this.f2208a = i2;
        this.b = i7;
        this.f2209c = f;
    }

    public final void getOutline(View view, Outline outline) {
        int measuredWidth = view.getMeasuredWidth();
        int measuredHeight = view.getMeasuredHeight();
        int i2 = this.f2208a;
        int i7 = (measuredWidth - i2) / 2;
        int i8 = this.b;
        int i10 = (measuredHeight - i8) / 2;
        Outline outline2 = outline;
        outline2.setRoundRect(Math.max(0, i7), Math.max(0, i10), i7 + i2, i10 + i8, this.f2209c);
    }
}
