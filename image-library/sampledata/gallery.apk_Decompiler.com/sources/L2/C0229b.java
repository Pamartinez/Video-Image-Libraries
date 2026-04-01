package l2;

import android.content.res.Resources;
import com.sec.android.gallery3d.R;
import kotlin.jvm.internal.j;

/* renamed from: l2.b  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0229b extends C0230c {
    public final int c() {
        return R.dimen.sesl_navigation_bar_floating_icon_only_mode_inner_padding_horizontal;
    }

    public final int d(int i2, Resources resources) {
        int i7;
        j.e(resources, "resources");
        if (i2 == 1) {
            i7 = R.dimen.sesl_navigation_bar_floating_icon_only_min_width_count_1;
        } else if (i2 != 2) {
            i7 = R.dimen.sesl_navigation_bar_floating_icon_only_min_width_count_over_3;
        } else {
            i7 = R.dimen.sesl_navigation_bar_floating_icon_only_min_width_count_2;
        }
        return resources.getDimensionPixelSize(i7);
    }

    public final int f() {
        return -1;
    }

    public final int g() {
        return R.dimen.sesl_bottom_navigation_floating_height;
    }

    public final int h() {
        return R.dimen.sesl_bottom_navigation_floating_icon_only_selected_side_padding;
    }

    public final boolean i() {
        return true;
    }
}
