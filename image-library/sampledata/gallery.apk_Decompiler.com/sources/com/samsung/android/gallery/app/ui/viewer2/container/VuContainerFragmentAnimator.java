package com.samsung.android.gallery.app.ui.viewer2.container;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import androidx.fragment.app.FragmentTransaction;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.widget.abstraction.SimpleAnimationListener;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class VuContainerFragmentAnimator {
    private Animation loadRemasterDepthInAnim(Context context, final View view, int i2) {
        Animation loadAnimation = AnimationUtils.loadAnimation(context, i2);
        if (loadAnimation != null) {
            loadAnimation.setAnimationListener(new SimpleAnimationListener() {
                public void onAnimationEnd(Animation animation) {
                    View view = view;
                    ViewUtils.postDelayed(view, new b(view), 250);
                }

                public void onAnimationStart(Animation animation) {
                    ViewUtils.setTranslationZ(view, 1.0f);
                }
            });
        }
        return loadAnimation;
    }

    public Animation onCreateAnimation(Context context, View view, int i2) {
        if (i2 == R.anim.remaster_depth_in_enter) {
            return loadRemasterDepthInAnim(context, view, i2);
        }
        return null;
    }

    public void setCustomAnimations(FragmentTransaction fragmentTransaction, String str) {
        if (LocationKey.isAllDayTimeLapse(str) || LocationKey.isLongExposure(str)) {
            fragmentTransaction.setCustomAnimations(R.anim.remaster_depth_in_enter, R.anim.remaster_depth_in_exit, 0, R.anim.remaster_pop_exit);
        }
    }
}
