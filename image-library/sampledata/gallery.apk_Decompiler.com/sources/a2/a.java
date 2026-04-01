package A2;

import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.view.View;
import com.google.android.material.tabs.TabLayout;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class a extends c {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f28a;

    public /* synthetic */ a(int i2) {
        this.f28a = i2;
    }

    public final void b(TabLayout tabLayout, View view, View view2, float f, Drawable drawable) {
        float f5;
        float f8;
        float f10;
        switch (this.f28a) {
            case 0:
                RectF a7 = c.a(tabLayout, view);
                RectF a10 = c.a(tabLayout, view2);
                if (a7.left < a10.left) {
                    double d = (((double) f) * 3.141592653589793d) / 2.0d;
                    f5 = (float) (1.0d - Math.cos(d));
                    f8 = (float) Math.sin(d);
                } else {
                    double d2 = (((double) f) * 3.141592653589793d) / 2.0d;
                    f5 = (float) Math.sin(d2);
                    f8 = (float) (1.0d - Math.cos(d2));
                }
                drawable.setBounds(R1.a.c(f5, (int) a7.left, (int) a10.left), drawable.getBounds().top, R1.a.c(f8, (int) a7.right, (int) a10.right), drawable.getBounds().bottom);
                return;
            default:
                int i2 = (f > 0.5f ? 1 : (f == 0.5f ? 0 : -1));
                if (i2 >= 0) {
                    view = view2;
                }
                RectF a11 = c.a(tabLayout, view);
                if (i2 < 0) {
                    f10 = R1.a.b(1.0f, 0.0f, 0.0f, 0.5f, f);
                } else {
                    f10 = R1.a.b(0.0f, 1.0f, 0.5f, 1.0f, f);
                }
                drawable.setBounds((int) a11.left, drawable.getBounds().top, (int) a11.right, drawable.getBounds().bottom);
                drawable.setAlpha((int) (f10 * 255.0f));
                return;
        }
    }
}
