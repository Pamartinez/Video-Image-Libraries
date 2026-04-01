package e4;

import com.samsung.android.gallery.app.ui.abstraction.MvpBaseFragment;
import java.util.function.BooleanSupplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements BooleanSupplier {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2627a;
    public final /* synthetic */ MvpBaseFragment b;

    public /* synthetic */ b(int i2, MvpBaseFragment mvpBaseFragment) {
        this.f2627a = i2;
        this.b = mvpBaseFragment;
    }

    public final boolean getAsBoolean() {
        int i2 = this.f2627a;
        MvpBaseFragment mvpBaseFragment = this.b;
        switch (i2) {
            case 0:
                return mvpBaseFragment.supportExitPredictiveBack();
            default:
                return mvpBaseFragment.supportEnterPredictiveBack();
        }
    }
}
