package com.samsung.android.gallery.app.ui.list.stories.category.category;

import android.view.View;
import android.view.ViewGroup;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements Runnable {
    public final /* synthetic */ StoriesCatOnDemandViewHolder d;
    public final /* synthetic */ View e;
    public final /* synthetic */ ViewGroup f;

    public /* synthetic */ e(StoriesCatOnDemandViewHolder storiesCatOnDemandViewHolder, View view, ViewGroup viewGroup) {
        this.d = storiesCatOnDemandViewHolder;
        this.e = view;
        this.f = viewGroup;
    }

    public final void run() {
        this.d.lambda$bindSearchToolbarAsync$1(this.e, this.f);
    }
}
