package ic;

import androidx.dynamicanimation.animation.DynamicAnimation;
import com.samsung.android.gallery.widget.videoview.mediaplayer.GestureDelegate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements DynamicAnimation.OnAnimationUpdateListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ GestureDelegate e;

    public /* synthetic */ a(GestureDelegate gestureDelegate, int i2) {
        this.d = i2;
        this.e = gestureDelegate;
    }

    public final void onAnimationUpdate(DynamicAnimation dynamicAnimation, float f, float f5) {
        int i2 = this.d;
        GestureDelegate gestureDelegate = this.e;
        switch (i2) {
            case 0:
                gestureDelegate.lambda$flingTo$0(dynamicAnimation, f, f5);
                return;
            default:
                gestureDelegate.lambda$flingTo$1(dynamicAnimation, f, f5);
                return;
        }
    }
}
