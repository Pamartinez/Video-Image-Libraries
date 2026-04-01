package com.samsung.android.gallery.app.ui.list.search.toolbar;

import android.view.View;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ BaseSearchToolbarBehavior e;
    public final /* synthetic */ Object f;

    public /* synthetic */ e(BaseSearchToolbarBehavior baseSearchToolbarBehavior, Object obj, int i2) {
        this.d = i2;
        this.e = baseSearchToolbarBehavior;
        this.f = obj;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                ((BottomSearchPicturesBehavior) this.e).lambda$updateBottomPaddingWithFilter$2((View) this.f);
                return;
            default:
                ((LegacySuggestionContainerBehavior) this.e).lambda$onQueryTextChanged$0((String) this.f);
                return;
        }
    }
}
