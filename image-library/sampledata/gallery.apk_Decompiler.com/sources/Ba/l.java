package Ba;

import android.view.View;
import androidx.dynamicanimation.animation.DynamicAnimation;
import com.samsung.android.gallery.plugins.motionphoto.MotionPhotoViewCompat;
import q2.k;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class l implements DynamicAnimation.OnAnimationUpdateListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ View e;

    public /* synthetic */ l(View view, int i2) {
        this.d = i2;
        this.e = view;
    }

    public final void onAnimationUpdate(DynamicAnimation dynamicAnimation, float f, float f5) {
        int i2 = this.d;
        View view = this.e;
        switch (i2) {
            case 0:
                ((MotionPhotoViewCompat) view).lambda$flingTo$2(dynamicAnimation, f, f5);
                return;
            case 1:
                ((MotionPhotoViewCompat) view).lambda$flingTo$3(dynamicAnimation, f, f5);
                return;
            default:
                k kVar = (k) view;
                float f8 = f / 10000.0f;
                kVar.setScaleX(f8);
                kVar.setScaleY(f8);
                return;
        }
    }
}
