package Fb;

import android.graphics.PointF;
import android.view.MotionEvent;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.widget.listview.GalleryRecyclerAdapter;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements ListViewHolder.OnItemClickListener, ListViewHolder.OnItemLongClickListener, ListViewHolder.OnItemSecondaryClickListener, ListViewHolder.OnImageBindListener {
    public final /* synthetic */ GalleryRecyclerAdapter d;

    public /* synthetic */ e(GalleryRecyclerAdapter galleryRecyclerAdapter) {
        this.d = galleryRecyclerAdapter;
    }

    public void onItemClick(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7) {
        this.d.lambda$new$0(listViewHolder, i2, mediaItem, i7);
    }

    public boolean onItemLongClick(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7) {
        return this.d.lambda$new$1(listViewHolder, i2, mediaItem, i7);
    }

    public void onItemSecondaryClick(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7, PointF pointF, MotionEvent motionEvent) {
        this.d.lambda$new$2(listViewHolder, i2, mediaItem, i7, pointF, motionEvent);
    }
}
