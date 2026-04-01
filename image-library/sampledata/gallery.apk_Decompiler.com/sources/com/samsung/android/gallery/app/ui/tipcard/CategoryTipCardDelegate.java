package com.samsung.android.gallery.app.ui.tipcard;

import com.samsung.android.gallery.app.ui.list.abstraction.IBaseListView;
import com.samsung.android.gallery.app.ui.tipcard.abstraction.TipCardView;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import java.util.ArrayList;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CategoryTipCardDelegate<V extends IBaseListView> extends BaseTipCardDelegate<V> {
    public CategoryTipCardDelegate(V v) {
        super(v);
    }

    public List<TipCardView> createTipCardList() {
        if (LocationKey.isSearchCategoryScreenShot(this.mView.getLocationKey())) {
            return List.of(new ScreenshotCategoryTipCard());
        }
        return new ArrayList();
    }
}
