package W5;

import com.samsung.android.gallery.app.ui.list.stories.category.category.StoriesCatBaseViewHolder;
import com.samsung.android.gallery.module.dataset.MediaData;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements MediaData.SimpleOnDataChangeListener {
    public final /* synthetic */ StoriesCatBaseViewHolder d;

    public /* synthetic */ a(StoriesCatBaseViewHolder storiesCatBaseViewHolder) {
        this.d = storiesCatBaseViewHolder;
    }

    public final void onDataChanged() {
        this.d.onDataChanged();
    }
}
