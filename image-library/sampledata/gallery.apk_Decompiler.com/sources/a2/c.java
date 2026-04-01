package A2;

import R1.a;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.view.View;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.d;
import h2.u;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class c {
    public static RectF a(TabLayout tabLayout, View view) {
        if (view == null) {
            return new RectF();
        }
        if (tabLayout.isTabIndicatorFullWidth() || !(view instanceof d)) {
            return new RectF((float) view.getLeft(), (float) view.getTop(), (float) view.getRight(), (float) view.getBottom());
        }
        d dVar = (d) view;
        int contentWidth = dVar.getContentWidth();
        int contentHeight = dVar.getContentHeight();
        int b = (int) u.b(dVar.getContext(), 24);
        if (contentWidth < b) {
            contentWidth = b;
        }
        int right = (dVar.getRight() + dVar.getLeft()) / 2;
        int bottom = (dVar.getBottom() + dVar.getTop()) / 2;
        int i2 = contentWidth / 2;
        return new RectF((float) (right - i2), (float) (bottom - (contentHeight / 2)), (float) (i2 + right), (float) ((right / 2) + bottom));
    }

    public void b(TabLayout tabLayout, View view, View view2, float f, Drawable drawable) {
        RectF a7 = a(tabLayout, view);
        RectF a10 = a(tabLayout, view2);
        drawable.setBounds(a.c(f, (int) a7.left, (int) a10.left), drawable.getBounds().top, a.c(f, (int) a7.right, (int) a10.right), drawable.getBounds().bottom);
    }
}
