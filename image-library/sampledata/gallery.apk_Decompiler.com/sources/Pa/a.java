package Pa;

import com.samsung.android.gallery.module.dataset.comparator.Comparators;
import com.samsung.android.gallery.support.library.sef.SefParser;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2861a;
    public final /* synthetic */ String b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ int f2862c;

    public /* synthetic */ a(String str, int i2, int i7) {
        this.f2861a = i7;
        this.b = str;
        this.f2862c = i2;
    }

    public final Object apply(Object obj) {
        switch (this.f2861a) {
            case 0:
                return SefParser.lambda$addData$1(this.b, this.f2862c, (String) obj);
            default:
                return Comparators.createComparator(this.b, this.f2862c);
        }
    }
}
