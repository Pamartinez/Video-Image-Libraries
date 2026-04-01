package Fb;

import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.widget.listview.GalleryListAdapter;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements ListViewHolder.OnItemBindListener, ListViewHolder.OnItemTouchListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2818a;
    public final /* synthetic */ GalleryListAdapter b;

    public /* synthetic */ b(int i2, GalleryListAdapter galleryListAdapter) {
        this.f2818a = i2;
        this.b = galleryListAdapter;
    }

    public boolean onItemTouch(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7) {
        int i8 = this.f2818a;
        GalleryListAdapter galleryListAdapter = this.b;
        switch (i8) {
            case 1:
                return galleryListAdapter.lambda$new$1(listViewHolder, i2, mediaItem, i7);
            default:
                return galleryListAdapter.lambda$new$2(listViewHolder, i2, mediaItem, i7);
        }
    }
}
