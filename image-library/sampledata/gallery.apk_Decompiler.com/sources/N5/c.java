package n5;

import com.samsung.android.gallery.app.ui.list.search.category.people.CreatureHidePresenter;
import com.samsung.android.gallery.module.data.MediaItem;
import java.util.HashMap;
import java.util.function.BiConsumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements BiConsumer {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2670a;
    public final /* synthetic */ CreatureHidePresenter b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ MediaItem[] f2671c;

    public /* synthetic */ c(CreatureHidePresenter creatureHidePresenter, MediaItem[] mediaItemArr, int i2) {
        this.f2670a = i2;
        this.b = creatureHidePresenter;
        this.f2671c = mediaItemArr;
    }

    public final void accept(Object obj, Object obj2) {
        switch (this.f2670a) {
            case 0:
                this.b.lambda$restoreChangedCreatureMapIfNeeded$5(this.f2671c, (Long) obj, (Boolean) obj2);
                return;
            default:
                this.b.lambda$restoreChangedCreatureMapIfNeeded$6(this.f2671c, (String) obj, (HashMap) obj2);
                return;
        }
    }
}
