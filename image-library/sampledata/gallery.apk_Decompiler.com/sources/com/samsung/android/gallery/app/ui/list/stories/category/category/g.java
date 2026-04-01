package com.samsung.android.gallery.app.ui.list.stories.category.category;

import android.view.MotionEvent;
import android.view.View;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class g implements View.OnTouchListener {
    public final /* synthetic */ StoriesCatOnDemandViewHolder d;

    public /* synthetic */ g(StoriesCatOnDemandViewHolder storiesCatOnDemandViewHolder) {
        this.d = storiesCatOnDemandViewHolder;
    }

    public final boolean onTouch(View view, MotionEvent motionEvent) {
        return this.d.lambda$initSearchToolbar$2(view, motionEvent);
    }
}
