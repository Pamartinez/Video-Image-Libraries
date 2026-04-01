package com.samsung.android.gallery.app.ui.dialog.creature;

import android.graphics.Bitmap;
import com.samsung.android.gallery.app.ui.dialog.creature.RemoveCreatureDialog;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements Runnable {
    public final /* synthetic */ RemoveCreatureDialog.ViewHolderCreature d;
    public final /* synthetic */ Bitmap e;

    public /* synthetic */ c(RemoveCreatureDialog.ViewHolderCreature viewHolderCreature, Bitmap bitmap) {
        this.d = viewHolderCreature;
        this.e = bitmap;
    }

    public final void run() {
        this.d.bindImage(this.e);
    }
}
