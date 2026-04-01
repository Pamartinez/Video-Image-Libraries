package com.samsung.android.gallery.app.ui.list.search.editcreature;

import android.graphics.Bitmap;
import com.samsung.android.gallery.app.ui.list.search.editcreature.TagCreatureAdapter;
import com.samsung.android.gallery.module.creature.base.CreatureNameData;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Runnable {
    public final /* synthetic */ TagCreatureAdapter.ViewHolder d;
    public final /* synthetic */ CreatureNameData e;
    public final /* synthetic */ Bitmap f;

    public /* synthetic */ a(TagCreatureAdapter.ViewHolder viewHolder, CreatureNameData creatureNameData, Bitmap bitmap) {
        this.d = viewHolder;
        this.e = creatureNameData;
        this.f = bitmap;
    }

    public final void run() {
        this.d.lambda$bindImage$0(this.e, this.f);
    }
}
