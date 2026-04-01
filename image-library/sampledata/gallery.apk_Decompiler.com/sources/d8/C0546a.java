package D8;

import com.samsung.android.gallery.module.analyticsquery.AnalyticsQuery;
import java.util.function.BiFunction;

/* renamed from: D8.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0546a implements BiFunction {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2788a;
    public final /* synthetic */ int b;

    public /* synthetic */ C0546a(int i2, int i7) {
        this.f2788a = i7;
        this.b = i2;
    }

    public final Object apply(Object obj, Object obj2) {
        switch (this.f2788a) {
            case 0:
                return AnalyticsQuery.lambda$getRelationshipCount$1(this.b, (String) obj, (Integer) obj2);
            default:
                return Integer.valueOf(((Integer) obj2).intValue() + this.b);
        }
    }
}
