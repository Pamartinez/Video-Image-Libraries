package a6;

import androidx.core.util.Function;
import com.samsung.android.gallery.app.ui.list.stories.category.ondemand.OnDemandLoader;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ OnDemandLoader f2472a;

    public /* synthetic */ d(OnDemandLoader onDemandLoader) {
        this.f2472a = onDemandLoader;
    }

    public final Object apply(Object obj) {
        return this.f2472a.getStoryDataCursor(((Integer) obj).intValue());
    }
}
