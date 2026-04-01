package com.samsung.android.gallery.app.ui.list.search.pictures.headerview;

import android.view.View;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class f implements View.OnLayoutChangeListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    public /* synthetic */ f(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    public final void onLayoutChange(View view, int i2, int i7, int i8, int i10, int i11, int i12, int i13, int i14) {
        int i15 = this.d;
        Object obj = this.e;
        switch (i15) {
            case 0:
                ((SearchCreatureSlideshow) obj).lambda$new$2(view, i2, i7, i8, i10, i11, i12, i13, i14);
                return;
            default:
                ((SearchCreatureSlideshow2) obj).lambda$new$2(view, i2, i7, i8, i10, i11, i12, i13, i14);
                return;
        }
    }
}
