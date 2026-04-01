package E7;

import android.animation.ValueAnimator;
import android.view.View;
import com.samsung.android.app.sdk.deepsky.textextraction.capsule.util.CapsuleAnimationUtil;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.liveeffectvideo.LiveEffectVideoController;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.motionphoto.MotionVideoController;
import com.samsung.android.gallery.widget.utils.PickerViewUtil;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ View e;

    public /* synthetic */ b(View view, int i2) {
        this.d = i2;
        this.e = view;
    }

    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        int i2 = this.d;
        View view = this.e;
        switch (i2) {
            case 0:
                LiveEffectVideoController.lambda$createAnimator$3(view, valueAnimator);
                return;
            case 1:
                MotionVideoController.lambda$createAnimator$3(view, valueAnimator);
                return;
            case 2:
                PickerViewUtil.setContentViewTopPadding(view, ((Integer) valueAnimator.getAnimatedValue()).intValue());
                return;
            case 3:
                CapsuleAnimationUtil.getValueAnimatorAlongHeight$lambda$3$lambda$2(view, valueAnimator);
                return;
            default:
                CapsuleAnimationUtil.getValueAnimatorAlongWidth$lambda$1$lambda$0(view, valueAnimator);
                return;
        }
    }
}
