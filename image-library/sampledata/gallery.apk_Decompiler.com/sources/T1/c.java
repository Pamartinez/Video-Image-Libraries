package T1;

import android.animation.Animator;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.appbar.model.view.BasicViewPagerAppBarView;
import kotlin.jvm.internal.j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class c implements Animator.AnimatorListener {
    public final /* synthetic */ BasicViewPagerAppBarView d;
    public final /* synthetic */ ViewPager2 e;
    public final /* synthetic */ int f;

    public c(BasicViewPagerAppBarView basicViewPagerAppBarView, ViewPager2 viewPager2, int i2) {
        this.d = basicViewPagerAppBarView;
        this.e = viewPager2;
        this.f = i2;
    }

    public final void onAnimationCancel(Animator animator) {
        j.e(animator, "animation");
    }

    public final void onAnimationEnd(Animator animator) {
        j.e(animator, "animation");
        BasicViewPagerAppBarView basicViewPagerAppBarView = this.d;
        ViewPager2 viewPager2 = this.e;
        int i2 = this.f;
        basicViewPagerAppBarView.moveNextAndRemove(viewPager2, i2);
        basicViewPagerAppBarView.removeIndicator(i2);
    }

    public final void onAnimationRepeat(Animator animator) {
        j.e(animator, "animation");
    }

    public final void onAnimationStart(Animator animator) {
        j.e(animator, "animation");
    }
}
