package com.samsung.android.gallery.widget.databinding;

import android.view.View;
import android.widget.FrameLayout;
import androidx.viewbinding.ViewBinding;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class FlexModeTouchPadBinding implements ViewBinding {
    private final FrameLayout rootView;

    private FlexModeTouchPadBinding(FrameLayout frameLayout) {
        this.rootView = frameLayout;
    }

    public static FlexModeTouchPadBinding bind(View view) {
        if (view != null) {
            return new FlexModeTouchPadBinding((FrameLayout) view);
        }
        throw new NullPointerException("rootView");
    }

    public FrameLayout getRoot() {
        return this.rootView;
    }
}
