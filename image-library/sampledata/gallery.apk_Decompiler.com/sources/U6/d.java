package U6;

import com.samsung.android.gallery.app.ui.list.trash.container.TrashContainerFragment;
import com.samsung.android.gallery.module.thumbnail.IconResources;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.widget.abstraction.SharedTransition;
import com.samsung.android.gallery.widget.abstraction.TransitionData;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2452a;
    public final /* synthetic */ int b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ Object f2453c;

    public /* synthetic */ d(int i2, ThumbKind thumbKind) {
        this.f2452a = 2;
        this.b = i2;
        this.f2453c = thumbKind;
    }

    public final Object apply(Object obj) {
        switch (this.f2452a) {
            case 0:
                return ((TrashContainerFragment) this.f2453c).lambda$computeFragment$1(this.b, (Integer) obj);
            case 1:
                return SharedTransition.lambda$getTransitionSet$1((TransitionData) this.f2453c, this.b, (String) obj);
            default:
                return IconResources.createColorBitmap(this.b, ((ThumbKind) this.f2453c).size());
        }
    }

    public /* synthetic */ d(Object obj, int i2, int i7) {
        this.f2452a = i7;
        this.f2453c = obj;
        this.b = i2;
    }
}
