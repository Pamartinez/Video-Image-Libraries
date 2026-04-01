package com.samsung.android.gallery.app.ui.dialog.creature;

import com.samsung.android.gallery.app.ui.dialog.creature.RemoveCreatureDialog;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements ListViewHolder.OnItemClickListener {
    public final /* synthetic */ RemoveCreatureDialog.CreatureAdapter d;

    public /* synthetic */ a(RemoveCreatureDialog.CreatureAdapter creatureAdapter) {
        this.d = creatureAdapter;
    }

    public final void onItemClick(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7) {
        this.d.lambda$onCreateViewHolder$0(listViewHolder, i2, mediaItem, i7);
    }
}
