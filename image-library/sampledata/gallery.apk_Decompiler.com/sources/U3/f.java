package U3;

import com.samsung.android.gallery.app.controller.story.StoriesPinCmd;
import java.util.function.IntToLongFunction;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class f implements IntToLongFunction {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ boolean f2446a;

    public /* synthetic */ f(boolean z) {
        this.f2446a = z;
    }

    public final long applyAsLong(int i2) {
        return StoriesPinCmd.lambda$updateStoryPin$2(this.f2446a, i2);
    }
}
