package com.samsung.android.gallery.app.ui.tipcard;

import android.view.View;
import com.samsung.android.gallery.widget.utils.ViewUtils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    public /* synthetic */ b(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    public final void run() {
        int i2 = this.d;
        Object obj = this.e;
        switch (i2) {
            case 0:
                ViewUtils.setVisibility(((TipCardViewHolder) obj).getItemView(), 8);
                return;
            default:
                ViewUtils.setVisibility((View) obj, 8);
                return;
        }
    }
}
