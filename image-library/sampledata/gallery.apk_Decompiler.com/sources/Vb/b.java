package Vb;

import android.content.res.Resources;
import com.samsung.android.gallery.database.dal.mp.impl.StoryHelperBaseImpl;
import com.samsung.android.gallery.module.thumbnail.IconResources;
import com.samsung.o3dp.lib3dphotography.animation.AnimationParams;
import com.samsung.o3dp.lib3dphotography.animation.Animator;
import java.util.HashMap;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2890a;
    public final /* synthetic */ int b;

    public /* synthetic */ b(int i2, int i7) {
        this.f2890a = i7;
        this.b = i2;
    }

    public final Object apply(Object obj) {
        int i2 = this.f2890a;
        int i7 = this.b;
        switch (i2) {
            case 0:
                return Integer.valueOf(((Resources) obj).getDimensionPixelSize(i7));
            case 1:
                return Animator.lambda$getInterpolatedParams$3(i7, (AnimationParams) obj);
            case 2:
                return Animator.lambda$getInterpolatedParams$4(i7, (AnimationParams) obj);
            case 3:
                return IconResources.createIconBitmap(-1, i7);
            default:
                return StoryHelperBaseImpl.CmhNotifier.lambda$getMethod$0(i7, (HashMap) obj);
        }
    }
}
