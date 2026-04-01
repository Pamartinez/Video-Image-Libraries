package l2;

import android.content.res.Resources;
import androidx.appcompat.view.menu.MenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import k2.h;
import kotlin.jvm.internal.j;

/* renamed from: l2.d  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class C0231d {
    public static int b(int i2, Resources resources) {
        j.e(resources, "resources");
        if (i2 == -1) {
            return 0;
        }
        return resources.getDimensionPixelSize(i2);
    }

    public void a(BottomNavigationView bottomNavigationView) {
        h hVar;
        MenuView menuView = bottomNavigationView.getMenuView();
        if (menuView instanceof h) {
            hVar = (h) menuView;
        } else {
            hVar = null;
        }
        if (hVar != null) {
            Resources resources = hVar.getResources();
            j.d(resources, "resources");
            int b = b(g(), resources);
            Resources resources2 = hVar.getResources();
            j.d(resources2, "resources");
            int b5 = b(c(), resources2);
            hVar.setMinimumHeight(b);
            bottomNavigationView.setPadding(b5, bottomNavigationView.getPaddingTop(), b5, bottomNavigationView.getPaddingBottom());
            bottomNavigationView.setMinimumHeight(b);
        }
    }

    public abstract int c();

    public int d(int i2, Resources resources) {
        j.e(resources, "resources");
        return 0;
    }

    public int e(Resources resources, boolean z) {
        j.e(resources, "resources");
        return b(f(), resources);
    }

    public int f() {
        return -1;
    }

    public abstract int g();

    public int h() {
        return -1;
    }

    public boolean i() {
        return false;
    }
}
