package H7;

import com.samsung.android.gallery.module.abstraction.VideoPropData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.o3dp.lib3dphotography.animation.AnimationParams;
import com.samsung.o3dp.lib3dphotography.animation.Animator;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class u implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2354a;
    public final /* synthetic */ float b;

    public /* synthetic */ u(float f, int i2) {
        this.f2354a = i2;
        this.b = f;
    }

    public final Object apply(Object obj) {
        int i2 = this.f2354a;
        float f = this.b;
        switch (i2) {
            case 0:
                return Integer.valueOf((int) (((float) VideoPropData.getVideoDuration((MediaItem) obj)) * f));
            default:
                return Animator.lambda$getInterpolatedParams$8(f, (AnimationParams) obj);
        }
    }
}
