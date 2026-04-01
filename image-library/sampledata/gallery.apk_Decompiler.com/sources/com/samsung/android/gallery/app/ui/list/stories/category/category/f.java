package com.samsung.android.gallery.app.ui.list.stories.category.category;

import android.view.View;
import android.view.ViewGroup;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class f implements Runnable {
    public final /* synthetic */ StoriesCatOnDemandViewHolder d;
    public final /* synthetic */ ViewGroup e;
    public final /* synthetic */ View f;
    public final /* synthetic */ View g;

    public /* synthetic */ f(StoriesCatOnDemandViewHolder storiesCatOnDemandViewHolder, ViewGroup viewGroup, View view, View view2) {
        this.d = storiesCatOnDemandViewHolder;
        this.e = viewGroup;
        this.f = view;
        this.g = view2;
    }

    public final void run() {
        this.d.lambda$bindSearchToolbarAsync$0(this.e, this.f, this.g);
    }
}
