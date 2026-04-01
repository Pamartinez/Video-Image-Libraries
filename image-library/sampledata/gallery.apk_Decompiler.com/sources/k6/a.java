package k6;

import android.content.Context;
import com.samsung.android.gallery.app.ui.list.stories.highlight.bottomsheet.GridDimenHelper;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2654a;
    public final /* synthetic */ Context b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ int f2655c;
    public final /* synthetic */ int d;

    public /* synthetic */ a(Context context, int i2, int i7, int i8) {
        this.f2654a = i8;
        this.b = context;
        this.f2655c = i2;
        this.d = i7;
    }

    public final Object apply(Object obj) {
        switch (this.f2654a) {
            case 0:
                return Integer.valueOf(GridDimenHelper.getRadius(this.b, this.f2655c, this.d));
            default:
                return Integer.valueOf(GridDimenHelper.getMargin(this.b, this.f2655c, this.d));
        }
    }
}
