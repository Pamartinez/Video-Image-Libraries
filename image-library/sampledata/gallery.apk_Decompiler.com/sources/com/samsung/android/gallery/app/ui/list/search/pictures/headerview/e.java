package com.samsung.android.gallery.app.ui.list.search.pictures.headerview;

import android.view.View;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements View.OnClickListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    public /* synthetic */ e(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    public final void onClick(View view) {
        int i2 = this.d;
        Object obj = this.e;
        switch (i2) {
            case 0:
                ((SearchCreatureSlideshow) obj).onSlideshowClicked(view);
                return;
            default:
                ((SearchCreatureSlideshow2) obj).onSlideshowClicked(view);
                return;
        }
    }
}
