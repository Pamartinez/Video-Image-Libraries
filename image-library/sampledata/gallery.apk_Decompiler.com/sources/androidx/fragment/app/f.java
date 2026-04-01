package androidx.fragment.app;

import android.os.Bundle;
import androidx.savedstate.SavedStateRegistry;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class f implements SavedStateRegistry.SavedStateProvider {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f988a;
    public final /* synthetic */ Object b;

    public /* synthetic */ f(int i2, Object obj) {
        this.f988a = i2;
        this.b = obj;
    }

    public final Bundle saveState() {
        int i2 = this.f988a;
        Object obj = this.b;
        switch (i2) {
            case 0:
                return ((FragmentActivity) obj).lambda$init$0();
            default:
                return ((FragmentManager) obj).lambda$attachController$5();
        }
    }
}
