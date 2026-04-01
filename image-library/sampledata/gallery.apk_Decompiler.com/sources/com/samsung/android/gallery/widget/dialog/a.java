package com.samsung.android.gallery.widget.dialog;

import android.view.View;
import androidx.appcompat.app.AlertDialog;
import com.samsung.android.gallery.widget.dialog.AppChooserBuilder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements View.OnClickListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    public /* synthetic */ a(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    public final void onClick(View view) {
        int i2 = this.d;
        Object obj = this.e;
        switch (i2) {
            case 0:
                ((AppChooserBuilder.AppLabelHolder) obj).onIconClicked(view);
                return;
            default:
                OverlayTipBuilder.lambda$bindView$1((AlertDialog) obj, view);
                return;
        }
    }
}
