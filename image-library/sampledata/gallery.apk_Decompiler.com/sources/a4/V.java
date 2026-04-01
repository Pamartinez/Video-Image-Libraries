package A4;

import com.samsung.android.gallery.app.ui.list.abstraction.ListMenuUpdater;
import java.util.function.BooleanSupplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class V implements BooleanSupplier {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2241a;
    public final /* synthetic */ ListMenuUpdater b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ int f2242c;

    public /* synthetic */ V(ListMenuUpdater listMenuUpdater, int i2, int i7) {
        this.f2241a = i7;
        this.b = listMenuUpdater;
        this.f2242c = i2;
    }

    public final boolean getAsBoolean() {
        switch (this.f2241a) {
            case 0:
                return this.b.lambda$updateEffectMenu$15(this.f2242c);
            case 1:
                return this.b.lambda$updateEffectMenu$16(this.f2242c);
            case 2:
                return this.b.lambda$updateMotionPhotoMenu$11(this.f2242c);
            case 3:
                return this.b.lambda$updateMotionPhotoMenu$12(this.f2242c);
            default:
                return this.b.lambda$updateMotionPhotoMenu$13(this.f2242c);
        }
    }
}
