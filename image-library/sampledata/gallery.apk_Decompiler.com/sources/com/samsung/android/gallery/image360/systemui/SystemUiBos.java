package com.samsung.android.gallery.image360.systemui;

import N2.j;
import android.app.Activity;
import android.graphics.Rect;
import android.view.View;
import android.widget.RelativeLayout;
import com.samsung.android.gallery.image360.R$color;
import com.samsung.android.gallery.image360.R$id;
import com.samsung.android.gallery.widget.utils.ViewUtils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SystemUiBos extends BaseSystemUi {
    private View mNavigationBarBackground;
    private View mStatusBarBackground;

    private void updateBackgroundViewLayout(View view, Rect rect) {
        if (view != null) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
            layoutParams.removeRule(9);
            layoutParams.removeRule(10);
            layoutParams.removeRule(11);
            layoutParams.removeRule(12);
            if (rect.left > 0) {
                layoutParams.addRule(9);
                ViewUtils.setViewSize(view, Integer.valueOf(rect.left), -1);
            } else if (rect.top > 0) {
                layoutParams.addRule(10);
                ViewUtils.setViewSize(view, -1, Integer.valueOf(rect.top));
            } else if (rect.right > 0) {
                layoutParams.addRule(11);
                ViewUtils.setViewSize(view, Integer.valueOf(rect.right), -1);
            } else {
                layoutParams.addRule(12);
                ViewUtils.setViewSize(view, -1, Integer.valueOf(rect.bottom));
            }
            view.setLayoutParams(layoutParams);
        }
    }

    public void onApplyInsets(Rect rect, Rect rect2) {
        updateBackgroundViewLayout(this.mStatusBarBackground, rect);
        updateBackgroundViewLayout(this.mNavigationBarBackground, rect2);
    }

    public void setRootView(View view) {
        this.mStatusBarBackground = view.findViewById(R$id.status_bar_bg_view);
        this.mNavigationBarBackground = view.findViewById(R$id.navigation_bar_bg_view);
    }

    public void updateBarColors(Activity activity) {
        try {
            ViewUtils.setBackgroundColor(this.mStatusBarBackground, activity.getColor(R$color.viewer_status_bar_background_color));
            ViewUtils.setBackgroundColor(this.mNavigationBarBackground, activity.getColor(R$color.viewer_navigation_bar_background_color));
        } catch (Exception e) {
            j.D(e, new StringBuilder("updateBarColors e="), this.TAG);
        }
    }
}
