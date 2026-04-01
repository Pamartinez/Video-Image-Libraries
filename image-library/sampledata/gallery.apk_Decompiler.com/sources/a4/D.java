package A4;

import com.samsung.android.gallery.app.ui.list.abstraction.BaseListViewAdapter;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class D implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2237a;
    public final /* synthetic */ BaseListViewAdapter b;

    public /* synthetic */ D(int i2, BaseListViewAdapter baseListViewAdapter) {
        this.f2237a = i2;
        this.b = baseListViewAdapter;
    }

    public final Object apply(Object obj) {
        int i2 = this.f2237a;
        int intValue = ((Integer) obj).intValue();
        BaseListViewAdapter baseListViewAdapter = this.b;
        switch (i2) {
            case 0:
                return baseListViewAdapter.getMediaItemSync(intValue);
            case 1:
                return Integer.valueOf(baseListViewAdapter.getMediaItemPosition(intValue));
            default:
                return baseListViewAdapter.loadMediaItemForShare(intValue);
        }
    }
}
