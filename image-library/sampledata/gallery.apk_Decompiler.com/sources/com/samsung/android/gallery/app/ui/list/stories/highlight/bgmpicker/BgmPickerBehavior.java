package com.samsung.android.gallery.app.ui.list.stories.highlight.bgmpicker;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.samsung.android.gallery.app.ui.list.stories.highlight.bottomsheet.behavior.StoryHighlightBehavior;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class BgmPickerBehavior<V extends View> extends StoryHighlightBehavior {
    public BgmPickerBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public static BgmPickerBehavior from(View view) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof CoordinatorLayout.LayoutParams) {
            CoordinatorLayout.Behavior behavior = ((CoordinatorLayout.LayoutParams) layoutParams).getBehavior();
            if (behavior instanceof BgmPickerBehavior) {
                return (BgmPickerBehavior) behavior;
            }
            throw new IllegalArgumentException("The view is not associated with BgmPickerBehavior");
        }
        throw new IllegalArgumentException("The view is not a child of CoordinatorLayout");
    }
}
