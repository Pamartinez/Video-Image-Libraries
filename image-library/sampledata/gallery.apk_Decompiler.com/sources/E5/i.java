package e5;

import com.samsung.android.gallery.app.ui.list.pictures.PicturesPinchAnimationManagerV3;
import java.util.function.IntPredicate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class i implements IntPredicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2631a;
    public final /* synthetic */ PicturesPinchAnimationManagerV3 b;

    public /* synthetic */ i(PicturesPinchAnimationManagerV3 picturesPinchAnimationManagerV3, int i2) {
        this.f2631a = i2;
        this.b = picturesPinchAnimationManagerV3;
    }

    public final boolean test(int i2) {
        int i7 = this.f2631a;
        PicturesPinchAnimationManagerV3 picturesPinchAnimationManagerV3 = this.b;
        switch (i7) {
            case 0:
                return picturesPinchAnimationManagerV3.lambda$getPivotInfo$0(i2);
            default:
                return picturesPinchAnimationManagerV3.lambda$getPivotInfo$1(i2);
        }
    }
}
