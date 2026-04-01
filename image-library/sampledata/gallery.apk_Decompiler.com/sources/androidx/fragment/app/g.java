package androidx.fragment.app;

import android.content.Intent;
import android.content.res.Configuration;
import androidx.core.util.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class g implements Consumer {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f989a;
    public final /* synthetic */ FragmentActivity b;

    public /* synthetic */ g(int i2, FragmentActivity fragmentActivity) {
        this.f989a = i2;
        this.b = fragmentActivity;
    }

    public final void accept(Object obj) {
        int i2 = this.f989a;
        FragmentActivity fragmentActivity = this.b;
        switch (i2) {
            case 0:
                fragmentActivity.lambda$init$1((Configuration) obj);
                return;
            default:
                fragmentActivity.lambda$init$2((Intent) obj);
                return;
        }
    }
}
