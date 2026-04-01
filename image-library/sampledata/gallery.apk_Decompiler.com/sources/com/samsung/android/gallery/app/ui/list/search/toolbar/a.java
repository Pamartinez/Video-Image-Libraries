package com.samsung.android.gallery.app.ui.list.search.toolbar;

import android.view.View;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements View.OnClickListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ BaseSearchToolbarBehavior e;

    public /* synthetic */ a(BaseSearchToolbarBehavior baseSearchToolbarBehavior, int i2) {
        this.d = i2;
        this.e = baseSearchToolbarBehavior;
    }

    public final void onClick(View view) {
        int i2 = this.d;
        BaseSearchToolbarBehavior baseSearchToolbarBehavior = this.e;
        switch (i2) {
            case 0:
                ((BottomCategoryBehavior) baseSearchToolbarBehavior).lambda$updateToolbar$0(view);
                return;
            default:
                ((LegacyCategoryBehavior) baseSearchToolbarBehavior).lambda$updateToolbar$0(view);
                return;
        }
    }
}
