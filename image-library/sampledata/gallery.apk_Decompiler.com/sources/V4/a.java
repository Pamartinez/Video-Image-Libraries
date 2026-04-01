package V4;

import android.content.Context;
import com.samsung.android.gallery.app.ui.list.hover.HoverHandler;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.widget.hoverview.HoverPreviewListView;
import com.samsung.android.gallery.widget.hoverview.HoverPreviewViewHolder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements HoverPreviewListView.OnLoadCompleteListener, HoverPreviewListView.OnRemoveListener, HoverPreviewListView.OnItemClickListener, HoverPreviewViewHolder.OnShareClickListener, HoverPreviewViewHolder.OnDeleteClickListener {
    public final /* synthetic */ HoverHandler d;

    public /* synthetic */ a(HoverHandler hoverHandler) {
        this.d = hoverHandler;
    }

    public void a(Context context, int i2, MediaItem mediaItem, boolean z, boolean z3) {
        this.d.onHoverPreviewItemClick(context, i2, mediaItem, z, z3);
    }

    public void onDeleteClick(int i2) {
        this.d.onHoverPreviewDeleteClick(i2);
    }

    public void onLoadCompleted() {
        this.d.onLoadCompleted();
    }

    public void onShareClick(int i2) {
        this.d.onHoverPreviewShareClick(i2);
    }
}
