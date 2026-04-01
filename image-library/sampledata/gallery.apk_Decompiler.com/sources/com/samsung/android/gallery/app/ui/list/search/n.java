package com.samsung.android.gallery.app.ui.list.search;

import com.samsung.android.gallery.app.ui.list.search.category.ISearchHeaderView;
import com.samsung.android.gallery.widget.story.transitory.PlayableViewPagerDelegate;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class n implements Consumer {
    public final /* synthetic */ int d;

    public /* synthetic */ n(int i2) {
        this.d = i2;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                ((PlayableViewPagerDelegate) obj).stop();
                return;
            default:
                ((ISearchHeaderView) obj).dataChanged();
                return;
        }
    }
}
