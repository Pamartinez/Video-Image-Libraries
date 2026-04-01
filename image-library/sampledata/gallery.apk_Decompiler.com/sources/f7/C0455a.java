package f7;

import android.content.Context;
import com.samsung.android.gallery.app.ui.container.menu.BottomMenuItem;
import com.samsung.android.gallery.app.ui.tipcard.abstraction.TipCardView;
import java.util.function.Predicate;

/* renamed from: f7.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0455a implements Predicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2636a;
    public final /* synthetic */ Context b;

    public /* synthetic */ C0455a(Context context, int i2) {
        this.f2636a = i2;
        this.b = context;
    }

    public final boolean test(Object obj) {
        int i2 = this.f2636a;
        Context context = this.b;
        switch (i2) {
            case 0:
                return ((TipCardView) obj).checkTipCard(context);
            default:
                return ((BottomMenuItem) obj).support(context);
        }
    }
}
