package com.samsung.android.gallery.widget.floatingui;

import A4.C0367b;
import android.app.Activity;
import android.view.View;
import com.samsung.android.gallery.widget.R$id;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import java.util.List;
import q2.u;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FloatingViewsInvalidator implements Runnable {
    private static final List<Integer> sFloatingViews = List.of(Integer.valueOf(R$id.sesl_floating_toolbar_layout), Integer.valueOf(R$id.tab_layout_floating_container), Integer.valueOf(R$id.bottom_bar_floating_container), Integer.valueOf(R$id.bottom_move_bar_floating_container));
    private final Activity mActivity;

    public FloatingViewsInvalidator(Activity activity) {
        this.mActivity = activity;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$run$0(Integer num) {
        View findViewById = this.mActivity.findViewById(num.intValue());
        if (findViewById instanceof u) {
            u uVar = (u) findViewById;
            if (ViewUtils.isVisible(uVar)) {
                uVar.h();
            }
        }
    }

    public void run() {
        if (this.mActivity != null) {
            sFloatingViews.forEach(new C0367b(28, this));
        }
    }
}
