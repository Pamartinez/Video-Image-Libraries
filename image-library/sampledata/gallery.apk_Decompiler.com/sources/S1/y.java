package S1;

import android.animation.ObjectAnimator;
import android.animation.StateListAnimator;
import com.google.android.material.appbar.AppBarLayout;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class y {

    /* renamed from: a  reason: collision with root package name */
    public static final int[] f794a = {16843848};

    public static void a(AppBarLayout appBarLayout, float f) {
        int integer = appBarLayout.getResources().getInteger(R.integer.app_bar_elevation_anim_duration);
        StateListAnimator stateListAnimator = new StateListAnimator();
        long j2 = (long) integer;
        stateListAnimator.addState(new int[]{16842910, R.attr.state_liftable, -2130969844}, ObjectAnimator.ofFloat(appBarLayout, "elevation", new float[]{0.0f}).setDuration(j2));
        stateListAnimator.addState(new int[]{16842910}, ObjectAnimator.ofFloat(appBarLayout, "elevation", new float[]{f}).setDuration(j2));
        stateListAnimator.addState(new int[0], ObjectAnimator.ofFloat(appBarLayout, "elevation", new float[]{0.0f}).setDuration(0));
        appBarLayout.setStateListAnimator(stateListAnimator);
    }
}
