package R5;

import com.samsung.android.gallery.app.ui.list.sharings.storage.SharingStorageUseFragment;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements ListViewHolder.OnItemClickListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ SharingStorageUseFragment e;

    public /* synthetic */ b(SharingStorageUseFragment sharingStorageUseFragment, int i2) {
        this.d = i2;
        this.e = sharingStorageUseFragment;
    }

    public final void onItemClick(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7) {
        int i8 = this.d;
        SharingStorageUseFragment sharingStorageUseFragment = this.e;
        switch (i8) {
            case 0:
                sharingStorageUseFragment.onListItemClick(listViewHolder, i2, mediaItem, i7);
                return;
            default:
                sharingStorageUseFragment.lambda$initFamilyTrashViewHolder$4(listViewHolder, i2, mediaItem, i7);
                return;
        }
    }
}
