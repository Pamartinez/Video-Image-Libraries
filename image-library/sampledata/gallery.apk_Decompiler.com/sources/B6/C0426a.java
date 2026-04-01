package b6;

import android.graphics.PointF;
import android.view.MotionEvent;
import com.samsung.android.gallery.app.ui.list.stories.header.BasePinView;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;

/* renamed from: b6.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0426a implements ListViewHolder.OnItemClickListener, ListViewHolder.OnItemSecondaryClickListener, ListViewHolder.OnItemLongClickListener {
    public final /* synthetic */ BasePinView d;

    public /* synthetic */ C0426a(BasePinView basePinView) {
        this.d = basePinView;
    }

    public void onItemClick(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7) {
        this.d.onItemClicked(listViewHolder, i2, mediaItem, i7);
    }

    public boolean onItemLongClick(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7) {
        return this.d.onItemLongClicked(listViewHolder, i2, mediaItem, i7);
    }

    public void onItemSecondaryClick(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7, PointF pointF, MotionEvent motionEvent) {
        this.d.onItemSecondaryClicked(listViewHolder, i2, mediaItem, i7, pointF, motionEvent);
    }
}
