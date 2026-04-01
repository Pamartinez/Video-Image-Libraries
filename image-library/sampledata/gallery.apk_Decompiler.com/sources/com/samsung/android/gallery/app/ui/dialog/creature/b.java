package com.samsung.android.gallery.app.ui.dialog.creature;

import android.graphics.Bitmap;
import com.samsung.android.gallery.app.ui.dialog.creature.RemoveCreatureDialog;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.ThumbnailLoadedListener;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements ThumbnailLoadedListener {
    public final /* synthetic */ RemoveCreatureDialog.CreatureAdapter d;
    public final /* synthetic */ RemoveCreatureDialog.ViewHolderCreature e;

    public /* synthetic */ b(RemoveCreatureDialog.CreatureAdapter creatureAdapter, RemoveCreatureDialog.ViewHolderCreature viewHolderCreature) {
        this.d = creatureAdapter;
        this.e = viewHolderCreature;
    }

    public final void onLoaded(Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
        this.d.lambda$loadOrDecode$1(this.e, bitmap, uniqueKey, thumbKind);
    }
}
