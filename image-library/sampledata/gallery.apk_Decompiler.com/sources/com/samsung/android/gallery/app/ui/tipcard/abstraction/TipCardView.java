package com.samsung.android.gallery.app.ui.tipcard.abstraction;

import android.content.Context;
import android.view.View;
import com.samsung.android.gallery.app.ui.list.abstraction.IBaseListView;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface TipCardView {
    boolean checkTipCard(Context context);

    View createTipCardView(IBaseListView iBaseListView);

    String getTag();
}
