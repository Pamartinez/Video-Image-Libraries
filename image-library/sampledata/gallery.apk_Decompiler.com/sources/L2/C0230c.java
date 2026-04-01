package l2;

import android.content.res.Resources;
import androidx.appcompat.view.menu.MenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.sec.android.gallery3d.R;
import k2.h;
import kotlin.jvm.internal.j;

/* renamed from: l2.c  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class C0230c extends C0231d {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f1837a;

    public /* synthetic */ C0230c(int i2) {
        this.f1837a = i2;
    }

    public void a(BottomNavigationView bottomNavigationView) {
        h hVar;
        int i2;
        switch (this.f1837a) {
            case 0:
                MenuView menuView = bottomNavigationView.getMenuView();
                if (menuView instanceof h) {
                    hVar = (h) menuView;
                } else {
                    hVar = null;
                }
                if (hVar != null) {
                    Resources resources = hVar.getResources();
                    j.d(resources, "resources");
                    int b = C0231d.b(g(), resources);
                    Resources resources2 = hVar.getResources();
                    j.d(resources2, "resources");
                    if (hVar.getVisibleItemCount() == bottomNavigationView.getMaxItemCount()) {
                        i2 = j();
                    } else {
                        i2 = c();
                    }
                    int b5 = C0231d.b(i2, resources2);
                    hVar.setMinimumHeight(b);
                    bottomNavigationView.setPadding(b5, bottomNavigationView.getPaddingTop(), b5, bottomNavigationView.getPaddingBottom());
                    bottomNavigationView.setMinimumHeight(b);
                    return;
                }
                return;
            default:
                super.a(bottomNavigationView);
                return;
        }
    }

    public int c() {
        switch (this.f1837a) {
            case 0:
                return R.dimen.sesl_navigation_bar_icon_text_mode_padding_horizontal;
            case 1:
                return R.dimen.sesl_navigation_bar_floating_icon_only_mode_inner_padding_horizontal;
            default:
                return R.dimen.sesl_navigation_bar_text_mode_padding_horizontal;
        }
    }

    public int e(Resources resources, boolean z) {
        int i2;
        switch (this.f1837a) {
            case 0:
                j.e(resources, "resources");
                if (z) {
                    i2 = k();
                } else {
                    i2 = f();
                }
                return C0231d.b(i2, resources);
            default:
                return super.e(resources, z);
        }
    }

    public int f() {
        switch (this.f1837a) {
            case 0:
                return R.dimen.sesl_bottom_navigation_icon_mode_padding_horizontal;
            default:
                return super.f();
        }
    }

    public int g() {
        switch (this.f1837a) {
            case 0:
                return R.dimen.sesl_bottom_navigation_icon_mode_height;
            case 1:
                return R.dimen.sesl_bottom_navigation_icon_only_mode_height;
            default:
                return R.dimen.sesl_bottom_navigation_text_mode_height;
        }
    }

    public int j() {
        return R.dimen.sesl_navigation_bar_icon_text_mode_min_padding_horizontal;
    }

    public int k() {
        return R.dimen.sesl_bottom_navigation_icon_mode_min_padding_horizontal;
    }
}
