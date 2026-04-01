package b6;

import com.samsung.android.gallery.app.ui.list.stories.header.StoriesPinModel;
import com.samsung.android.gallery.module.data.MediaItem;
import java.util.ArrayList;
import java.util.function.UnaryOperator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements UnaryOperator {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ ArrayList f2490a;

    public /* synthetic */ e(ArrayList arrayList) {
        this.f2490a = arrayList;
    }

    public final Object apply(Object obj) {
        return StoriesPinModel.lambda$notifyDataRangeChanged$1(this.f2490a, (MediaItem) obj);
    }
}
