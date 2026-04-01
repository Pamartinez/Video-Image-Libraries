package com.samsung.android.gallery.app.ui.tipcard.abstraction;

import android.content.Context;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface TipCardDelegate {
    TipCardView getAndCheckTipCard(Context context) {
        return getAndCheckTipCard(context, (String) null);
    }

    TipCardView getAndCheckTipCard(Context context, String str);

    boolean isEmpty();

    void release();
}
