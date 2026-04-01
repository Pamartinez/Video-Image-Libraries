package com.samsung.android.gallery.widget.recyclerview;

import android.util.Pair;
import androidx.recyclerview.widget.RecyclerView;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Consumer {
    public final /* synthetic */ RecyclerView.RecycledViewPool d;

    public /* synthetic */ a(RecyclerView.RecycledViewPool recycledViewPool) {
        this.d = recycledViewPool;
    }

    public final void accept(Object obj) {
        this.d.setMaxRecycledViews(((Integer) ((Pair) obj).first).intValue(), ((Integer) ((Pair) obj).second).intValue());
    }
}
